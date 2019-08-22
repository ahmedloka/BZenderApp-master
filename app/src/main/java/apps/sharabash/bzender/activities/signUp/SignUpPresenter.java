package apps.sharabash.bzender.activities.signUp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import org.json.JSONObject;

import java.util.List;

import apps.sharabash.bzender.Models.signUp.CountryCodeResponse;
import apps.sharabash.bzender.Models.signUp.SignUpRequest;
import apps.sharabash.bzender.Models.signUp.signUpResponse;
import apps.sharabash.bzender.Network.NetworkUtil;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.ButtonBook;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.MyTextView;
import apps.sharabash.bzender.Utills.MyTextViewBold;
import apps.sharabash.bzender.Utills.Validation;
import apps.sharabash.bzender.activities.verfication.VerificationActivity;
import apps.sharabash.bzender.dialog.DialogLoader;
import retrofit2.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static apps.sharabash.bzender.Utills.Constant.buildDialog;
import static apps.sharabash.bzender.Utills.Constant.mobile;

public class SignUpPresenter {

    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private final SignUpInterface signUpInterface;
    //private FragmentManager fragmentManager;

    private String passwordForSent;

    private final DialogLoader dialogLoader;
    private final DialogLoader dialogLoaderTwo;

    public SignUpPresenter(Context context, SignUpInterface signUpInterface) {
//        fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
//        fragmentManager.executePendingTransactions();
        this.context = context;
        this.signUpInterface = signUpInterface;
        mSubscriptions = new CompositeSubscription();
        dialogLoader = new DialogLoader();
        dialogLoaderTwo = new DialogLoader();
        sharedPreferences = context.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
    }

    void validationSignUp(String email, String phoneNumber, String password, String fullName, String cityId, String countryCode, String confirmPassword, String base64) {
        if (Validation.validateFields(fullName)) {
            Constant.showErrorDialog(context, context.getString(R.string.user_name_error));
        } else if (Validation.validateFields(email)) {
            Constant.showErrorDialog(context, context.getString(R.string.email_error));
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Constant.showErrorDialog(context, context.getString(R.string.email_inavlid));
        } else if (Validation.validateFields(password)) {
            Constant.showErrorDialog(context, context.getString(R.string.password_error));
        } else if (Validation.validateFields(confirmPassword)) {
            Constant.showErrorDialog(context, context.getString(R.string.confirm_pass_error));
        } else if (!password.equals(confirmPassword)) {
            Constant.showErrorDialog(context, context.getString(R.string.confirm_pass_error_match));
        } else if (Validation.validateFields(email)) {
            Constant.showErrorDialog(context, context.getString(R.string.email_error));
        } else if (Validation.validateFields(countryCode)) {
            Constant.showErrorDialog(context, context.getString(R.string.country_code_error));

        } else if (Validation.validateFields(mobile)) {
            Constant.showErrorDialog(context, context.getString(R.string.mobile_number_error));
        } else if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
            Constant.showErrorDialog(context, context.getString(R.string.mobile_number_inavlid));


        } else {

            SignUpRequest signUpRequest = new SignUpRequest();
            signUpRequest.setPhoneNumber(phoneNumber);
            signUpRequest.setPassword(password);
            signUpRequest.setFullName(fullName);
            signUpRequest.setEmail(email);
            signUpRequest.setImagedata(base64);
            signUpRequest.setCityId(cityId);
            signUpRequest.setCountryCodeId(countryCode);

            passwordForSent = password;

            signUp(signUpRequest);

        }
    }

    private void signUp(SignUpRequest signUpRequest) {
        if (Validation.isConnected(context)) {
            dialogLoader.show(((AppCompatActivity) context).getSupportFragmentManager(), "");

            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .signUp(signUpRequest)
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
                message = jsonObject.getString("Message");
            } catch (Exception e) {
                message = throwable.getMessage();
            }
            Constant.getErrorDependingOnResponse(context, message);

        }
        if (dialogLoader.isAdded())
            dialogLoader.dismiss();
        if (dialogLoaderTwo.isAdded())
            dialogLoaderTwo.dismiss();


    }

    private void handleResponse(signUpResponse signUpResponse) {
        if (dialogLoader.isAdded())
            dialogLoader.dismiss();

        Log.d("SIGNUP", "handleResponse: " + signUpResponse.toString());
        SharedPreferences.Editor edit = sharedPreferences.edit();

        edit.putString(Constant.UserID, signUpResponse.getToken());
        edit.putString(Constant.Username, signUpResponse.getFullName());
        edit.putString(Constant.Useremail, signUpResponse.getEmail());
        edit.putString(Constant.PASSWORD, passwordForSent);
        edit.putString(Constant.USER_ID_CHAT , signUpResponse.getUserId());
        edit.putString(Constant.IMAGE_URL, "http://pixelserver-001-site29.ctempurl.com/Content/Images/" + signUpResponse.getImageUrl().trim());
        edit.apply();

        Constant.NAME = signUpResponse.getFullName();


        Log.d("EMAIL_+_PASS", "handleResponse: " + sharedPreferences.getString(Constant.Useremail, "")
                + sharedPreferences.getString(Constant.PASSWORD, ""));

        String points = signUpResponse.getPoints();

        showDialogCreate(points);


    }

    public void getAllCountriesCode() {
        if (Validation.isConnected(context)) {

            dialogLoaderTwo.show(((AppCompatActivity) context).getSupportFragmentManager(), "");
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString(Constant.UserID, ""))
                    .getCountriesCode()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            buildDialog((Activity) context).show().setCanceledOnTouchOutside(false);
        }
    }

    private void handleResponse(List<CountryCodeResponse> countryCodeResponses) {
        if (dialogLoaderTwo.isAdded())
            dialogLoaderTwo.dismiss();
        signUpInterface.getAllCodes(countryCodeResponses);
        Log.d("CountriesCode", "handleResponse: " + countryCodeResponses.get(0).toString());
    }


    private void showDialogCreate(String points) {
        MyTextViewBold txtCongrats;
        MyTextView txtDesc;
        ButtonBook mBtnOk;

        AlertDialog.Builder builder1;
        final AlertDialog dialog1;
        builder1 = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View mView = inflater.inflate(R.layout.dialog_add_tender_seccessfully, null);

        mBtnOk = mView.findViewById(R.id.btn_ok);
        txtCongrats = mView.findViewById(R.id.txt_congrats);
        txtDesc = mView.findViewById(R.id.txt_desc);

        txtCongrats.setText(context.getText(R.string.congratulations));

        SpannableStringBuilder builder = new SpannableStringBuilder();


        String white = context.getText(R.string.register_successfully).toString();
        SpannableString whiteSpannable = new SpannableString(white);
        whiteSpannable.setSpan(new ForegroundColorSpan(Color.WHITE), 0, white.length(), 0);
        builder.append(whiteSpannable);


        String green = " " + points + " ";
        SpannableString redSpannable = new SpannableString(green);
        redSpannable.setSpan(new ForegroundColorSpan(Color.GREEN), 0, green.length() - 5, 0);
        builder.append(redSpannable);


        String whiteTwo = " " + context.getString(R.string.point);
        SpannableString whiteTwoSpannable = new SpannableString(whiteTwo);
        whiteTwoSpannable.setSpan(new ForegroundColorSpan(Color.WHITE), 0, whiteTwo.length(), 0);
        builder.append(whiteTwoSpannable);

        txtDesc.setText(builder, TextView.BufferType.SPANNABLE);

        builder1.setView(mView);
        builder1.setCancelable(true);

        dialog1 = builder1.create();

        Window window = dialog1.getWindow();

        window.setGravity(Gravity.CENTER);

        mBtnOk.setOnClickListener(v -> {
            Intent intent = new Intent(context, VerificationActivity.class);
            context.startActivity(intent);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            ((Activity) context).finish();
            Animatoo.animateSlideRight(context);

            dialog1.dismiss();
        });


        dialog1.show();


    }
}
