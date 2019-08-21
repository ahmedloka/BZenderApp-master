package apps.sharabash.bzender.activities.imagesBook;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONObject;

import apps.sharabash.bzender.Network.NetworkUtil;
import apps.sharabash.bzender.Models.imgs.UploadCarIamge;
import apps.sharabash.bzender.Models.imgs.UploadCarRequest;
import apps.sharabash.bzender.Models.imgs.UploadElectricalImage;
import apps.sharabash.bzender.Models.imgs.UploadElectricalRequest;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.Validation;
import apps.sharabash.bzender.dialog.DialogLoader;
import retrofit2.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static apps.sharabash.bzender.Utills.Constant.buildDialog;

class ImagesPresenter {
    private final DialogLoader dialogLoader;
    private final DialogLoader dialogLoaderTwo;
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;


     ImagesPresenter(Context context) {
        this.context = context;
        mSubscriptions = new CompositeSubscription();
        dialogLoader = new DialogLoader();
        dialogLoaderTwo = new DialogLoader();
        sharedPreferences = context.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
    }

    //CAR_________________
     void uploadCarImage(String id, String base46) {
        UploadCarIamge uploadCarIamge = new UploadCarIamge();
        uploadCarIamge.setImage(base46);
        uploadCarIamge.setTenderCarBookingId(id);

        if (Validation.isConnected(context)) {
            dialogLoader.show(((AppCompatActivity) context).getSupportFragmentManager(), "");
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString(Constant.UserID, ""))
                    .uploadCarImage(uploadCarIamge)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseUploadCar, this::handleError));
        } else {
            buildDialog((Activity) context).show().setCanceledOnTouchOutside(false);
        }


    }


    private void handleResponseUploadCar(UploadCarRequest uploadCarRequest) {
        if (dialogLoader.isAdded()) {
            dialogLoader.dismiss();
        }
        Log.d("RESPONSE", "handleResponseUploadElectrical: " + uploadCarRequest.toString());
    }

    //______________________
    // ELECTRICAL ____________________
     void uploadElectricalImage(String id, String base46) {
        UploadElectricalImage uploadElectricalImage = new UploadElectricalImage();
        uploadElectricalImage.setImage(base46);
        uploadElectricalImage.setTenderElectricalBookingId(id);

        if (Validation.isConnected(context)) {
            dialogLoaderTwo.show(((AppCompatActivity) context).getSupportFragmentManager(), "");
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString(Constant.UserID, ""))
                    .uploadElectricalImage(uploadElectricalImage)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseUploadElectrical, this::handleError));
        } else {
            buildDialog((Activity) context).show().setCanceledOnTouchOutside(false);
        }


    }

    private void handleResponseUploadElectrical(UploadElectricalRequest uploadElectricalRequest) {
        if (dialogLoaderTwo.isAdded()) {
            dialogLoader.dismiss();
        }

        Log.d("RESPONSE", "handleResponseUploadElectrical: " + uploadElectricalRequest.toString());
    }
    //_________________

    private void handleError(Throwable throwable) {
        if (dialogLoader.isAdded()) {
            dialogLoader.dismiss();
        }
        if (dialogLoaderTwo.isAdded()) {
            dialogLoaderTwo.dismiss();
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
            Constant.getErrorDependingOnResponse(context, message);

        }
    }


}