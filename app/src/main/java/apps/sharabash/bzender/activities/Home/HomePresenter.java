package apps.sharabash.bzender.activities.Home;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONObject;

import java.util.List;

import apps.sharabash.bzender.Models.GetOffers;
import apps.sharabash.bzender.Models.home.getCategoryResponse;
import apps.sharabash.bzender.Models.push.PushNotification;
import apps.sharabash.bzender.Models.push.PushResponse;
import apps.sharabash.bzender.Network.NetworkUtil;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.Validation;
import apps.sharabash.bzender.dialog.DialogLoader;
import retrofit2.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static apps.sharabash.bzender.Utills.Constant.buildDialog;

public class HomePresenter {

    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private final homeInterface homeInterface;
    private final FragmentManager fragmentManager;
    private final DialogLoader dialogLoaderOne;
    private final DialogLoader dialogLoaderTwo;

    public HomePresenter(Context context, homeInterface homeInterface) {
        this.context = context;
        mSubscriptions = new CompositeSubscription();
        this.homeInterface = homeInterface;
        fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        fragmentManager.executePendingTransactions();
        dialogLoaderOne = new DialogLoader();
        dialogLoaderTwo = new DialogLoader();
        sharedPreferences = context.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
    }

    public void getCategory() {
        if (Validation.isConnected(context)) {
            dialogLoaderOne.show(fragmentManager, "");
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString(Constant.UserID, ""))
                    .getCategory()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            buildDialog((Activity) context).show().setCanceledOnTouchOutside(false);
        }
    }

    public void sendPush(String token, String os) {
        PushNotification pushNotification = new PushNotification();
        pushNotification.setOS(os);
        pushNotification.setToken(token);

        handlePush(pushNotification);
    }

    private void handlePush(PushNotification pushNotification) {
        if (Validation.isConnected(context)) {
            // dialogLoaderTwo.show(fragmentManager, "");
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString("UserID", ""))
                    .pushNotification(pushNotification)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            buildDialog((Activity) context).show().setCanceledOnTouchOutside(false);
        }
    }


    private void handleResponse(PushResponse pushResponse) {
        // dialogLoaderTwo.dismiss();
        Log.d("PUSH", "handleResponse: " + pushResponse.toString());
    }

    private void handleError(Throwable throwable) {
        if (dialogLoaderOne.isAdded())
            dialogLoaderOne.dismiss();

        if (dialogLoaderTwo.isAdded()) {
            dialogLoaderTwo.dismiss();
        }
        //  dialogLoaderTwo.dismiss();
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
    }

    private void handleResponse(List<getCategoryResponse> getCategoryResponse) {
        if (dialogLoaderOne.isAdded())
            dialogLoaderOne.dismiss();

        homeInterface.handleCategoryList(getCategoryResponse);

    }

    void getAllImages() {
        if (Validation.isConnected(context)) {
            dialogLoaderTwo.show(fragmentManager, "");
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString(Constant.UserID, ""))
                    .getAllImages()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseImages, this::handleError));
        } else {
            buildDialog((Activity) context).show().setCanceledOnTouchOutside(false);
        }
    }

    private void handleResponseImages(GetOffers getOffers) {
        if (dialogLoaderTwo.isAdded()) {
            dialogLoaderTwo.dismiss();
        }
        homeInterface.getAllImages(getOffers);

        Log.d("ZOFOOOOO", "handleResponseImages: " +getOffers.getOffers().get(0).getImage());
    }

}
