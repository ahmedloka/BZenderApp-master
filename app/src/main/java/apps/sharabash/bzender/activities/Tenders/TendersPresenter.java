package apps.sharabash.bzender.activities.Tenders;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONObject;

import java.util.List;

import apps.sharabash.bzender.Models.AddTenders.TendersModelResponse;
import apps.sharabash.bzender.Models.allTinders.car.AllTender;
import apps.sharabash.bzender.Models.allTinders.electrical.AllTenderElectrical;
import apps.sharabash.bzender.Network.NetworkUtil;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.Validation;
import apps.sharabash.bzender.dialog.DialogLoader;
import retrofit2.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static apps.sharabash.bzender.Utills.Constant.buildDialog;

class TendersPresenter {

    private static final String TAG = "allTendersRequest";

    private final DialogLoader dialogLoader;
    private final DialogLoader dialogLoaderTwo;
    private final DialogLoader dialogLoaderThree;
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private final TendersInterface tendersInterface1;

    TendersPresenter(Context context, TendersInterface tendersInterface1) {
        this.context = context;
        mSubscriptions = new CompositeSubscription();
        dialogLoader = new DialogLoader();
        dialogLoaderTwo = new DialogLoader();
        dialogLoaderThree = new DialogLoader();
        this.tendersInterface1 = tendersInterface1;
        sharedPreferences = context.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
    }


    void getAllTenderItemsElectrical(String catId) {
        if (Validation.isConnected(context)) {
            dialogLoaderThree.show(((AppCompatActivity) context).getSupportFragmentManager(), "");
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString(Constant.UserID, ""))
                    .getAllTendersElectrical(catId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseAllTenderElectrical, this::handleError));
        } else {
            buildDialog((Activity) context).show().setCanceledOnTouchOutside(false);
        }
    }


    void getAllTenderItems(String catId) {
        if (Validation.isConnected(context)) {
            dialogLoader.show(((AppCompatActivity) context).getSupportFragmentManager(), "");
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString(Constant.UserID, ""))
                    .getAllTendersCar(catId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseAllTender, this::handleError));
        } else {
            buildDialog((Activity) context).show().setCanceledOnTouchOutside(false);
        }
    }

    private void handleResponseAllTender(List<AllTender> allTenders) {
        if (dialogLoader.isAdded()) {
            dialogLoader.dismiss();
        }
        Log.d(TAG, "handleResponseAllTender: " + allTenders.get(0).toString());
        tendersInterface1.getAllTenderList(allTenders);
    }

    private void handleResponseAllTenderElectrical(List<AllTenderElectrical> allTenderElectricals) {
        if (dialogLoaderThree.isAdded()) {
            dialogLoaderThree.dismiss();
        }
        Log.d(TAG, "handleResponseAllTender: " + allTenderElectricals.get(0).toString());
        tendersInterface1.getAllTenderElectrical(allTenderElectricals);

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
        if (dialogLoader.isAdded()) {
            dialogLoader.dismiss();
        }
        if (dialogLoaderTwo.isAdded()) {
            dialogLoaderTwo.dismiss();
        }
        if (dialogLoaderThree.isAdded()) {
            dialogLoaderThree.dismiss();
        }
    }

    private void handleResponse(List<TendersModelResponse> getCategoryResponse) {
        if (dialogLoaderTwo.isAdded())
            dialogLoaderTwo.dismiss();
        tendersInterface1.handleCategoryList(getCategoryResponse);

    }

}
