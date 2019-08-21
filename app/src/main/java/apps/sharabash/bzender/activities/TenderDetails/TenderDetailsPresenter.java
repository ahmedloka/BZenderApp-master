package apps.sharabash.bzender.activities.TenderDetails;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONObject;

import apps.sharabash.bzender.Models.TendersDetails.TenderDetails;
import apps.sharabash.bzender.Models.TendersDetails.electrical.TenderDetailsElectrical;
import apps.sharabash.bzender.Models.bookCar.BookCarBody;
import apps.sharabash.bzender.Models.bookCar.BookCarResponse;
import apps.sharabash.bzender.Models.bookElectrical.BookElectricalBody;
import apps.sharabash.bzender.Models.bookElectrical.BookElectricalResponse;
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

public class TenderDetailsPresenter {

    private final DialogLoader dialogLoader;
    private final DialogLoader dialogLoaderTwo;
    private final DialogLoader dialogLoaderThree;
    private final DialogLoader dialogLoaderFour;
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private final TenderDetailsInterface tendersInterface1;

    public TenderDetailsPresenter(Context context, TenderDetailsInterface tendersInterface1) {
        this.context = context;
        mSubscriptions = new CompositeSubscription();
        dialogLoader = new DialogLoader();
        dialogLoaderTwo = new DialogLoader();
        dialogLoaderThree = new DialogLoader();
        dialogLoaderFour = new DialogLoader();
        this.tendersInterface1 = tendersInterface1;
        sharedPreferences = context.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
    }

    public void getTenderElectricalDetails(String tenderId) {
        if (Validation.isConnected(context)) {
            dialogLoaderThree.show(((AppCompatActivity) context).getSupportFragmentManager(), "");
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString(Constant.UserID, ""))
                    .getTenderElectricalDetails(tenderId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseDetailsElectrical, this::handleError));
        } else {
            buildDialog((Activity) context).show().setCanceledOnTouchOutside(false);
        }
    }

    private void handleResponseDetailsElectrical(TenderDetailsElectrical tenderDetailsElectrical) {
        if (dialogLoaderThree.isAdded()) {
            dialogLoaderThree.dismiss();
        }

        tendersInterface1.getDetailsElectrical(tenderDetailsElectrical);


    }


    public void getTenderDetails(String tenderId) {
        if (Validation.isConnected(context)) {
            dialogLoader.show(((AppCompatActivity) context).getSupportFragmentManager(), "");
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString(Constant.UserID, ""))
                    .getTenderCarDetails(tenderId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            buildDialog((Activity) context).show().setCanceledOnTouchOutside(false);
        }
    }


    public void validateBookTenderElectrical(String price, String tenderId, String type, String model, String year, String number, String status
            , String origin, String extraa) {
        if (Validation.validateFields(price)) {
            Constant.showErrorDialog(context, context.getString(R.string.price_is_required));
        } else {
            BookElectricalBody bookTenderElectricalBody = new BookElectricalBody();
            bookTenderElectricalBody.setTenderId(tenderId);
            bookTenderElectricalBody.setUnitType(type);
            bookTenderElectricalBody.setModel(model);
            bookTenderElectricalBody.setYearOfManufacture(year);
            bookTenderElectricalBody.setNumberOfUnit(number);
            bookTenderElectricalBody.setOriginManufacturer(origin);
            bookTenderElectricalBody.setStatus(status);
            bookTenderElectricalBody.setNote(extraa);
            bookTenderElectricalBody.setPrice(price);

            bookTenderElectrical(bookTenderElectricalBody);
        }

    }

    private void bookTenderElectrical(BookElectricalBody bookTenderElectricalBody) {


        if (Validation.isConnected(context)) {
            dialogLoaderThree.show(((AppCompatActivity) context).getSupportFragmentManager(), "");
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString(Constant.UserID, ""))
                    .bookElectrical(bookTenderElectricalBody)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseBookElectrical, this::handleError));
        } else {
            buildDialog((Activity) context).show().setCanceledOnTouchOutside(false);
        }
    }

    private void handleResponseBookElectrical(BookElectricalResponse bookElectricalResponse) {
        Log.d("RESPONSE", "handleResponseBookElectrical: " + bookElectricalResponse.toString());
        if (dialogLoaderThree.isAdded()) {
            dialogLoaderThree.dismiss();
        }

        tendersInterface1.getElectricalId(bookElectricalResponse);


        Constant.showSuccessTenderBookDialog(context, context.getString(R.string.success_message), bookElectricalResponse.getTenderElectricalBookingId()
                , Constant.ELECTRICAL);

    }

    //TODO will make price here
    public void validateBookCarTender(String price, String tenderId, String type, String model, String year, String number, String keloMeters,
                                      String transmission, String licience, String fees, String canSacn, String engineCapacity, String extraa) {

        if (Validation.validateFields(price)) {
            Constant.showErrorDialog(context, context.getString(R.string.price_is_required));
        } else {
            BookCarBody bookTenderBody = new BookCarBody();
            bookTenderBody.setTenderId(tenderId);
            bookTenderBody.setCarType(type);
            bookTenderBody.setCarModel(model);
            bookTenderBody.setYearOfCar(year);
            bookTenderBody.setNumberOfCar(number);
            bookTenderBody.setFromKMToKM(keloMeters);
            bookTenderBody.setTransmissionType(transmission);
            bookTenderBody.setLicenseStatus(licience);
            bookTenderBody.setLicenseStatus(fees);
            bookTenderBody.setPossibilityOfExamination(canSacn);
            bookTenderBody.setEngineCapacity(engineCapacity);
            bookTenderBody.setNote(extraa);
            bookTenderBody.setPrice(price);


            bookTenderCar(bookTenderBody);
        }
    }

    private void bookTenderCar(BookCarBody bookTenderBody) {


        if (Validation.isConnected(context)) {
            dialogLoaderTwo.show(((AppCompatActivity) context).getSupportFragmentManager(), "");
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString(Constant.UserID, ""))
                    .bookCar(bookTenderBody)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseBooking, this::handleError));
        } else {
            buildDialog((Activity) context).show().setCanceledOnTouchOutside(false);
        }
    }

    private void handleResponseBooking(BookCarResponse bookCarResponse) {
        if (dialogLoaderTwo.isAdded()) {
            dialogLoaderTwo.dismiss();
        }
        tendersInterface1.getBookCarId(bookCarResponse);

        Constant.showSuccessTenderBookDialog(context, context.getString(R.string.success_message),
                bookCarResponse.getTenderCarBookingId()
                , Constant.CAR);

        Log.d("HANDLE RESPONSE", "handleResponseBooking: " + bookCarResponse.toString());
    }


    private void handleError(Throwable throwable) {
        if (dialogLoader.isAdded()) {
            dialogLoader.dismiss();
        }
        if (dialogLoaderTwo.isAdded()) {
            dialogLoaderTwo.dismiss();
        }
        if (dialogLoaderThree.isAdded()) {
            dialogLoaderThree.dismiss();
        }

        if (dialogLoaderFour.isAdded()) {
            dialogLoaderFour.dismiss();
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

    private void handleResponse(TenderDetails tenderDetails) {
        if (dialogLoader.isAdded()) {
            dialogLoader.dismiss();
        }
        tendersInterface1.handleSuccess(tenderDetails);
    }

}
