package apps.sharabash.bzender.activities.verfication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONObject;

import apps.sharabash.bzender.Models.VerifyBody;
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

class VerficationPresenter {

    private static final String TAG = "allTendersRequest";

    private final DialogLoader dialogLoader;
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;

    VerficationPresenter(Context context) {
        this.context = context;
        mSubscriptions = new CompositeSubscription();
        dialogLoader = new DialogLoader();
        sharedPreferences = context.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
    }

    void verifyAccount(String code) {
        if (Validation.validateFields(code)) {
            Constant.showErrorDialog(context, context.getString(R.string.code_error));
        } else {
            if (Validation.isConnected(context)) {
                dialogLoader.show(((AppCompatActivity) context).getSupportFragmentManager(), "");
                mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                        .verifyAcc(code, sharedPreferences.getString(Constant.Useremail, "")
                                , sharedPreferences.getString(Constant.PASSWORD, ""))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handleResponse, this::handleError));

                Log.d("DATATHATSENT", "onCreate: " + sharedPreferences.getString(Constant.Useremail, "")
                        + sharedPreferences.getString(Constant.PASSWORD, "") + code);
            } else {
                buildDialog((Activity) context).show().setCanceledOnTouchOutside(false);
            }
        }

    }


    private void handleError(Throwable throwable) {
        String message = "";
        if (throwable instanceof retrofit2.HttpException) {
            try {
                retrofit2.HttpException error = (retrofit2.HttpException) throwable;
                JSONObject jsonObject = new JSONObject(((HttpException) throwable).response().errorBody().string());
                message = jsonObject.getString("Message");
            } catch (Exception e) {
                message = throwable.getMessage();
            }
            Constant.showErrorDialog(context, context.getString(R.string.error_verfication));

        }
        if (dialogLoader.isAdded()) {
            dialogLoader.dismiss();
        }
    }

    private void handleResponse(VerifyBody verifyBody) {
        Log.d(TAG, "handleResponse: " + verifyBody.toString());

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Constant.NAME = verifyBody.getFullName();
        editor.putString(Constant.Username , verifyBody.getFullName());
        editor.putString(Constant.UserID, verifyBody.getToken());
        editor.apply();

        Constant.showSuccessDialogForVerfication(context, context.getString(R.string.account_verified), verifyBody.getFullName());


    }
}
