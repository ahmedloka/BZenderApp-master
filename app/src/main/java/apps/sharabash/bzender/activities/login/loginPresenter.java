package apps.sharabash.bzender.activities.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;

import org.json.JSONObject;

import apps.sharabash.bzender.Models.login.loginRequestModel;
import apps.sharabash.bzender.Models.login.loginResponse;
import apps.sharabash.bzender.Network.NetworkUtil;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.Validation;
import apps.sharabash.bzender.activities.Home.Home;
import apps.sharabash.bzender.dialog.DialogLoader;
import retrofit2.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static apps.sharabash.bzender.Utills.Constant.buildDialog;

class loginPresenter {

    private final DialogLoader dialogLoader;
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private String paswordFor;
    private String emailFor;

    loginPresenter(Context context, loginInterface loginInterface) {
        this.context = context;
        mSubscriptions = new CompositeSubscription();
        dialogLoader = new DialogLoader();
        apps.sharabash.bzender.activities.login.loginInterface loginInterface1 = loginInterface;
        sharedPreferences = context.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
    }

    void validation(String userName, String password) {
        if (Validation.validateFields(userName)) {
            Constant.showErrorDialog(context, context.getString(R.string.email_error));
        } else if (!Patterns.EMAIL_ADDRESS.matcher(userName).matches()) {
            Constant.showErrorDialog(context, context.getString(R.string.email_inavlid));
        } else if (Validation.validateFields(password)) {
            Constant.showErrorDialog(context, context.getString(R.string.password_error));
        } else if (password.length() < 6) {
            Constant.showErrorDialog(context, context.getString(R.string.password_size));
        } else {
            loginRequestModel loginRequestModel = new loginRequestModel();
            loginRequestModel.setEmail(userName);
            loginRequestModel.setPassword(password);
            login(loginRequestModel);

            emailFor = userName;
            paswordFor = password;

        }
    }

    private void login(loginRequestModel loginRequestModel) {
        if (Validation.isConnected(context)) {
            dialogLoader.show(((AppCompatActivity) context).getSupportFragmentManager(), "");
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .login(loginRequestModel)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            buildDialog((Activity) context).show().setCanceledOnTouchOutside(false);
        }
    }

    private void handleError(Throwable throwable) {
        String message = "";
        if (throwable instanceof retrofit2.HttpException) {
            try {
                retrofit2.HttpException error = (retrofit2.HttpException) throwable;
                JSONObject jsonObject = new JSONObject(((HttpException) throwable).response().errorBody().string());
                message = jsonObject.getString("message");
            } catch (Exception e) {
                message = throwable.getMessage();
            }

            if (message.equals("10")) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Constant.Useremail, emailFor);
                editor.putString(Constant.PASSWORD, paswordFor);

                editor.apply();

                Log.d("TAG", "handleError: " + sharedPreferences.getString(Constant.Useremail, "") +
                        sharedPreferences.getString(Constant.PASSWORD, ""));
                Constant.showErrorDialogVerification(context);
            } else {
                Constant.getErrorDependingOnResponse(context, message);
            }

        }
        dialogLoader.dismiss();
    }

    private void handleResponse(loginResponse loginResponse) {

        dialogLoader.dismiss();
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(Constant.UserID, loginResponse.getToken());
        edit.putString(Constant.Username, loginResponse.getFullName());
        Log.d("RESPONSE", "handleResponse: " + loginResponse.getUserId());

        try {
            edit.putString(Constant.IMAGE_URL, "http://pixelserver-001-site29.ctempurl.com/Content/Images/" + loginResponse.getImageUrl().trim());
        } catch (NullPointerException ignored) {
            edit.putString(Constant.IMAGE_URL, "");
        }

        Constant.NAME = loginResponse.getFullName();
        edit.putString(Constant.USER_ID_CHAT , loginResponse.getUserId());
        edit.apply();
        Log.d("TEST SENDER ID", "handleResponse: " + sharedPreferences.getString(Constant.USER_ID_CHAT, ""));

        Intent intent = new Intent(context, Home.class);
        intent.putExtra(Constant.Username, loginResponse.getFullName());
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.pull_in_left, R.anim.pull_in_right);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        ((Activity) context).finish();
    }

}
