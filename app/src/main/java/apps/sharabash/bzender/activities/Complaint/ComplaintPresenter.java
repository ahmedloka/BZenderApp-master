package apps.sharabash.bzender.activities.Complaint;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import org.json.JSONObject;

import apps.sharabash.bzender.Models.complaint.ComplaintModel;
import apps.sharabash.bzender.Models.complaint.ComplaintResponse;
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

public class ComplaintPresenter {

    private static final String TAG = "addTinder";
    private final Context mContext;
    private final CompositeSubscription mSubscriptions;
    private final DialogLoader dialogLoader;
    private final SharedPreferences sharedPreferences;


    public ComplaintPresenter(Context mContext) {
        this.mContext = mContext;
        mSubscriptions = new CompositeSubscription();
        sharedPreferences = mContext.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
        dialogLoader = new DialogLoader();
    }

    public void validationSendComplaint(String message) {
        if (Validation.validateFields(message)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.complaint_msg_empty));

            Log.d(TAG, "validationSendComplaint: + empty field ");
        } else {

            ComplaintModel complaint = new ComplaintModel();

            complaint.setMessage(message);

            Log.d(TAG, "validationAddTinder: " + complaint.toString());

            sendCompalint(complaint);

            // showDialogCreate();


        }
    }

    private void sendCompalint(ComplaintModel complaintModel) {
        if (Validation.isConnected(mContext)) {
            dialogLoader.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "");

            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString(Constant.UserID, ""))
                    .sendComplaint(complaintModel)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            buildDialog((Activity) mContext).show().setCanceledOnTouchOutside(false);
        }
    }

    private void handleResponse(ComplaintResponse complaintResponse) {
        if (dialogLoader.isAdded()) {
            dialogLoader.dismiss();
        }

        Constant.showSuccessDialogAndSetClassFor(mContext, mContext.getString(R.string.thanks_for_complain));

        Log.d(TAG, "handleResponseCompalint: " + complaintResponse.getMessage());
    }

    private void handleError(Throwable throwable) {

        if (dialogLoader.isAdded()) {
            dialogLoader.dismiss();
        }
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
        // hud.dismiss();
    }

    private void showDialog() {
        AlertDialog.Builder builder1;
        final AlertDialog dialog1;
        builder1 = new AlertDialog.Builder(mContext);
        @SuppressLint("InflateParams")
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View mview = inflater.inflate(R.layout.dialog_thank_you, null);
        builder1.setView(mview);
        Button pay = mview.findViewById(R.id.ok);

        dialog1 = builder1.create();
        Window window = dialog1.getWindow();
        if (window != null) {
            window.setGravity(Gravity.CENTER);
        }
        dialog1.show();
        pay.setOnClickListener(v1 -> {
            dialog1.dismiss();
//            Intent intent = new Intent(mContext, Home.class);
//            mContext.startActivity(intent);
//             overridePendingTransition(R.anim.pull_in_left, R.anim.pull_in_right);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            finish();
        });
    }
}
