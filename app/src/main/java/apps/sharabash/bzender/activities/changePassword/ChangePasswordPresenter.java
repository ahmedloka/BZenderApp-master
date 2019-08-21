package apps.sharabash.bzender.activities.changePassword;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONObject;

import apps.sharabash.bzender.Models.changePassword.ChangePasswordModel;
import apps.sharabash.bzender.Network.NetworkUtil;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.Validation;
import apps.sharabash.bzender.dialog.DialogLoader;
import retrofit2.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static apps.sharabash.bzender.Utills.Constant.buildDialog;

class ChangePasswordPresenter {


    private static final String TAG = "addTinder";
    private final Context mContext;
    private final CompositeSubscription mSubscriptions;
    private final DialogLoader dialogLoader;
    private final SharedPreferences sharedPreferences;


    ChangePasswordPresenter(Context mContext) {
        this.mContext = mContext;
        mSubscriptions = new CompositeSubscription();
        dialogLoader = new DialogLoader();
        sharedPreferences = mContext.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);

    }

    void validationChangePassword(String oldPassword, String newPassword, String confirmPassword) {
        if (Validation.validateFields(oldPassword)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.old_pass_error));

        } else if (Validation.validateFields(newPassword)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.new_pass_error));

        } else if (Validation.validateFields(confirmPassword)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.address_is_empty));

        } else {

            ChangePasswordModel changePasswordModel = new ChangePasswordModel();

            changePasswordModel.setOldPassword(oldPassword);
            changePasswordModel.setNewPassword(newPassword);
            changePasswordModel.setConfirmPassword(confirmPassword);

            changePassword(changePasswordModel);


        }
    }

    private void changePassword(ChangePasswordModel changePasswordModel) {
        if (Validation.isConnected(mContext)) {
            dialogLoader.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "");

            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString(Constant.UserID, ""))
                    .changePassword(changePasswordModel)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            buildDialog((Activity) mContext).show().setCanceledOnTouchOutside(false);
        }
    }

    private void handleResponse(ChangePasswordModel changePasswordModel) {
        dialogLoader.dismiss();
        Log.d(TAG, "PASSWORD: " + changePasswordModel.toString());

        Constant.showSuccessDialogAndSetClassForEdit(mContext, mContext.getString(R.string.password_changed));
    }

    private void handleError(Throwable throwable) {
        dialogLoader.dismiss();
        String message = "";
        if (throwable instanceof retrofit2.HttpException) {
            try {
                retrofit2.HttpException error = (retrofit2.HttpException) throwable;
                JSONObject jsonObject = new JSONObject(((HttpException) throwable).response().errorBody().string());
                message = jsonObject.getString("Message");
            } catch (Exception e) {
                message = throwable.getMessage();
            }
            Constant.getErrorDependingOnResponse(mContext, message);

        }
    }

}
