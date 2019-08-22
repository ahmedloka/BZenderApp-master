package apps.sharabash.bzender.activities.fillDataElectrical;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import org.json.JSONObject;

import apps.sharabash.bzender.Models.FillDataElectrical;
import apps.sharabash.bzender.Models.bookElectrical.BookElectricalBody;
import apps.sharabash.bzender.Models.bookElectrical.BookElectricalResponse;
import apps.sharabash.bzender.Network.NetworkUtil;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.ButtonBook;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.MyTextView;
import apps.sharabash.bzender.Utills.MyTextViewBold;
import apps.sharabash.bzender.Utills.Validation;
import apps.sharabash.bzender.activities.Home.Home;
import apps.sharabash.bzender.dialog.DialogLoader;
import retrofit2.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static apps.sharabash.bzender.Utills.Constant.ErrorDialog;
import static apps.sharabash.bzender.Utills.Constant.buildDialog;

class FillDataElectricalPresenter {

    private static final String TAG = "Fill_data_electrical";
    private final Context mContext;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private final DialogLoader dialogLoader;
    private final DialogLoader dialogLoaderTwo;


    public FillDataElectricalPresenter(Context mContext) {
        this.mContext = mContext;

        mSubscriptions = new CompositeSubscription();
        sharedPreferences = mContext.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);


        dialogLoader = new DialogLoader();
        dialogLoaderTwo = new DialogLoader();

    }

    public void bookElectrical(String price, String type, String model, String year
            , String number, String electricalUntilStatus,
                               String country, String extraFeatures, String tenderId) {

        if (Validation.validateFields(price)) {
            ErrorDialog((Activity) mContext, mContext.getString(R.string.price_is_required));
        } else {
            BookElectricalBody bookElectricalBody = new BookElectricalBody();
            bookElectricalBody.setPrice(price);
            bookElectricalBody.setUnitType(type);
            bookElectricalBody.setModel(model);
            bookElectricalBody.setYearOfManufacture(year);
            bookElectricalBody.setNumberOfUnit(number);
            bookElectricalBody.setStatus(electricalUntilStatus);
            bookElectricalBody.setOriginManufacturer(country);
            bookElectricalBody.setNote(extraFeatures);
            bookElectricalBody.setTenderId(tenderId);


            if (Validation.isConnected(mContext)) {
                dialogLoaderTwo.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "");

                mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString("UserID", ""))
                        .bookElectrical(bookElectricalBody)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handleResponseFillElectrical, this::handleError));
            } else {
                buildDialog((Activity) mContext).show().setCanceledOnTouchOutside(false);
            }
        }
    }

    private void handleResponseFillElectrical(BookElectricalResponse bookElectricalResponse) {
        if (dialogLoaderTwo.isAdded()) {
            dialogLoaderTwo.dismiss();
        }

        Log.d(TAG, "handleResponseFillElectrical: " + bookElectricalResponse.toString());
        showDialogCreate();
    }

    public void validateFillElectrical(String type, String model, String year
            , String number, String electricalUntilStatus, String guarantee,
                                       String country, String extraFeatures, String tenderId) {

        //TODO Do not forgot Orgin Of Manufurance
        if (Validation.validateFields(type)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.type_required));

        } else if (Validation.validateFields(model)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.model_error));

        } else if (Validation.validateFields(year)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.year_error));

        } else if (Validation.validateFields(number)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.number_of_error));

        } else if (Validation.validateFields(extraFeatures)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.note_error));

        } else if (Validation.validateFields(country)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.country_error));

        } else {

            FillDataElectrical fillDataElectrical = new FillDataElectrical();
            fillDataElectrical.setUnitType(type);
            fillDataElectrical.setModel(model);
            fillDataElectrical.setYearOfManufacture(year);
            fillDataElectrical.setNumberOfUnit(number);
            fillDataElectrical.setStatus(electricalUntilStatus);
            fillDataElectrical.setTenderId(tenderId);
            fillDataElectrical.setOriginManufacturer(country);
            fillDataElectrical.setNote(extraFeatures);

            fillDataElectrical(fillDataElectrical);

        }
    }


    private void fillDataElectrical(FillDataElectrical fillDataElectrical) {
        if (Validation.isConnected(mContext)) {
            dialogLoader.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "");

            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString("UserID", ""))
                    .fillDataElectrical(fillDataElectrical)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseFillData, this::handleError));
        } else {
            buildDialog((Activity) mContext).show().setCanceledOnTouchOutside(false);
        }
    }

    private void handleError(Throwable throwable) {
        if (dialogLoaderTwo.isAdded()) {
            dialogLoaderTwo.dismiss();
        }
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
    }


    private void handleResponseFillData(FillDataElectrical fillDataElectrical) {
        if (dialogLoader.isAdded()) {
            dialogLoader.dismiss();
        }

        Log.d(TAG, "handleResponseFillData: " + fillDataElectrical.toString());

        showDialogCreate();
    }

    private void showDialogCreate() {
        MyTextViewBold txtCongrats;
        MyTextView txtDesc;
        ButtonBook mBtnOk;

        AlertDialog.Builder builder1;
        final AlertDialog dialog1;
        builder1 = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View mView = inflater.inflate(R.layout.dialog_add_tender_seccessfully, null);

        mBtnOk = mView.findViewById(R.id.btn_ok);
        txtCongrats = mView.findViewById(R.id.txt_congrats);
        txtDesc = mView.findViewById(R.id.txt_desc);

        txtCongrats.setText(mContext.getText(R.string.congratulations));
        txtDesc.setText(mContext.getText(R.string.complete_add_tinder_sec));


        builder1.setView(mView);
        builder1.setCancelable(true);

        dialog1 = builder1.create();

        Window window = dialog1.getWindow();

        window.setGravity(Gravity.CENTER);

        mBtnOk.setOnClickListener(v -> {
            dialog1.dismiss();
            Intent intent = new Intent(mContext, Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mContext.startActivity(intent);
            Animatoo.animateFade(mContext);
        });


        dialog1.show();


    }

}
