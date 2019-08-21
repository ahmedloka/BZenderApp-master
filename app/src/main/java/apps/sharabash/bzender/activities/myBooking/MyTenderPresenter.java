package apps.sharabash.bzender.activities.myBooking;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

import apps.sharabash.bzender.Models.my_tenders.MyBookingBody;
import apps.sharabash.bzender.Models.my_tenders.MyTendersBody;
import apps.sharabash.bzender.Network.NetworkUtil;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.Validation;
import apps.sharabash.bzender.dialog.DialogLoader;
import retrofit2.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MyTenderPresenter {

    private static final String TAG = "addTinder";
    private final Context mContext;
    private final CompositeSubscription mSubscriptions;
    private final MyTenderInterface myTenderInterface;
    private final SharedPreferences sharedPreferences;
    private final DialogLoader dialogLoader;
    private final DialogLoader dialogLoaderTwo;

    public MyTenderPresenter(Context mContext, MyTenderInterface myTenderInterface) {
        this.mContext = mContext;

        dialogLoader = new DialogLoader();
        dialogLoaderTwo = new DialogLoader();

        mSubscriptions = new CompositeSubscription();
        this.myTenderInterface = myTenderInterface;

        sharedPreferences = mContext.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);

    }

    public void getMyooking() {
        if (Validation.isConnected(mContext)) {
            dialogLoader.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "");
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString(Constant.UserID, ""))
                    .getMyBooking()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            Toast.makeText(mContext, "error happend", Toast.LENGTH_SHORT).show();
        }

    }


    public void getMyTender() {
        if (Validation.isConnected(mContext)) {
            dialogLoaderTwo.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "");
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString(Constant.UserID, ""))
                    .getMyTender()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseMyTender, this::handleError));
        } else {
           // Toast.makeText(mContext, "error happend", Toast.LENGTH_SHORT).show();
        }

    }

    private void handleResponseMyTender(List<MyTendersBody> myTendersBody) {
        if (dialogLoaderTwo.isAdded()) {
            dialogLoaderTwo.dismiss();
        }

        Log.d(TAG, "handleResponseMyTender: " + myTendersBody.toString());
        myTenderInterface.getMyTender(myTendersBody);

    }


    private void handleResponse(MyBookingBody myTenderModels) {
        if (dialogLoader.isAdded())
            dialogLoader.dismiss();

        myTenderInterface.getMyooking(myTenderModels);
    }

    private void handleError(Throwable throwable) {
        if (dialogLoader.isAdded())
            dialogLoader.dismiss();

        if (dialogLoaderTwo.isAdded()) {
            dialogLoaderTwo.dismiss();
        }


        String message = "";
        if (throwable instanceof retrofit2.HttpException) {
            try {
                retrofit2.HttpException error = (retrofit2.HttpException) throwable;
                JSONObject jsonObject = new JSONObject(((HttpException) throwable).response().errorBody().string());
                message = jsonObject.getString("Message");
                Log.d(TAG, "handleResponseMyTender: " + message);

            } catch (Exception e) {
                message = throwable.getMessage();
                Log.d(TAG, "handleResponseMyTender: " + message);

            }
            Constant.getErrorDependingOnResponse(mContext, message);

        }
        //hud.dismiss();

    }


}
