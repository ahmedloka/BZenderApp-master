package apps.sharabash.bzender.activities.AddTender;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

import apps.sharabash.bzender.Models.AddTenders.AddTinderPojo;
import apps.sharabash.bzender.Models.AddTenders.AddTinderResponse;
import apps.sharabash.bzender.Models.AddTenders.AllCitiesModel;
import apps.sharabash.bzender.Network.NetworkUtil;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.Validation;
import apps.sharabash.bzender.activities.fillDataCar.FillDataCarActivity;
import apps.sharabash.bzender.activities.fillDataElectrical.FillDataElectricalActivity;
import apps.sharabash.bzender.dialog.DialogLoader;
import retrofit2.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static apps.sharabash.bzender.Utills.Constant.SELECTED_TENDER_TYPE;
import static apps.sharabash.bzender.Utills.Constant.buildDialog;

public class AddTinderPresenter {

    private static final String TAG = "addTinder";
    private final Context mContext;
    private final CompositeSubscription mSubscriptions;
    private final AddTinderInterface addTinderInterface;
    private final DialogLoader dialogLoader;
    private final DialogLoader dialogLoaderTwo;
    private final SharedPreferences sharedPreferences;


    public AddTinderPresenter(Context mContext, AddTinderInterface addTinderInterface) {
        this.mContext = mContext;
        mSubscriptions = new CompositeSubscription();
        this.addTinderInterface = addTinderInterface;
        sharedPreferences = mContext.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);

        dialogLoader = new DialogLoader();
        dialogLoaderTwo = new DialogLoader();


    }

    public void validationAddTinder(String tenderName, String desc
            , String cityId, String startTime, String endTime,
                                    String categoryID, String address) {
        if (Validation.validateFields(tenderName)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.title_is_empty));
        } else if (Validation.validateFields(categoryID) || cityId.equals("-1")) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.cat_id_empty));

        } else if (Validation.validateFields(cityId) || categoryID.equals("-1")) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.city_is_empty));

        } else if (Validation.validateFields(address)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.address_is_empty));

        } else if (Validation.validateFields(startTime)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.start_date_error));

        } else if (Validation.validateFields(endTime)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.end_date_error));

        } else if (Validation.validateFields(desc)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.desc_error));


        } else {

            AddTinderPojo addTinderPojo = new AddTinderPojo();

            addTinderPojo.setTenderName(tenderName);
            addTinderPojo.setTenderDescrioption(desc);
            addTinderPojo.setCityId(cityId);
            addTinderPojo.setStartDateTender(startTime);
            addTinderPojo.setEndDateTender(endTime);
            addTinderPojo.setCategoryID(categoryID);
            addTinderPojo.setAddress(address);

            Log.d(TAG, "validationAddTinder: " + addTinderPojo.toString());

            addTinder(addTinderPojo);

        }
    }


    private void addTinder(AddTinderPojo addTinderPojo) {
        if (Validation.isConnected(mContext)) {
            dialogLoader.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "");

            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString("UserID", ""))
                    .addTinder(addTinderPojo)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            buildDialog((Activity) mContext).show().setCanceledOnTouchOutside(false);
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
            Constant.getErrorDependingOnResponse(mContext, message);

        }
        dialogLoader.dismiss();
        dialogLoaderTwo.dismiss();


    }

    private void handleResponse(AddTinderResponse addTinderResponse) {
        dialogLoader.dismiss();


        Log.d(TAG, "TENDER ID: " + addTinderResponse.getTenderId());
        Log.d(TAG, "handleResponse: + success");
        Log.d(TAG, "handleResponse: " + SELECTED_TENDER_TYPE);

        Constant.ADD_TENDER_ID = addTinderResponse.getTenderId();

        Intent intent;
        if (SELECTED_TENDER_TYPE == 0) {
            intent = new Intent(mContext, FillDataCarActivity.class);
        } else {
            intent = new Intent(mContext, FillDataElectricalActivity.class);
        }
        mContext.startActivity(intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        ((Activity) mContext).finish();

    }


    public void getAllCities() {
        if (Validation.isConnected(mContext)) {
            dialogLoaderTwo.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "");
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString(Constant.UserID, ""))
                    .getAllCities()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            Toast.makeText(mContext, "error happend", Toast.LENGTH_SHORT).show();
        }

    }

    private void handleResponse(List<AllCitiesModel> allCitiesModel) {
        if (dialogLoaderTwo.isAdded()) {
            dialogLoaderTwo.dismiss();
        }
        dialogLoaderTwo.dismiss();
        Log.d(TAG + "cities", "handleResponse: " + allCitiesModel.get(0).getEnglishName());
        addTinderInterface.getAllCities(allCitiesModel);

    }


}






