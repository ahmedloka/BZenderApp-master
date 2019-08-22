package apps.sharabash.bzender.activities.fillDataCar;

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

import apps.sharabash.bzender.Models.FillDataCar;
import apps.sharabash.bzender.Models.bookCar.BookCarBody;
import apps.sharabash.bzender.Models.bookCar.BookCarResponse;
import apps.sharabash.bzender.Models.metadataCar.MetaDataCar;
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

public class FillDataCarPresenter {

    private static final String TAG = "META_CAR";

    private final Context mContext;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private final FillDataCarInterface fillDataCarInterface;
    private final DialogLoader dialogLoader;
    private final DialogLoader dialogLoaderTwo;
    private final DialogLoader dialogLoaderThree;


    public FillDataCarPresenter(Context context, FillDataCarInterface fillDataCarInterface) {

        this.mContext = context;
        mSubscriptions = new CompositeSubscription();
        sharedPreferences = context.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
        this.fillDataCarInterface = fillDataCarInterface;


        dialogLoader = new DialogLoader();
        dialogLoaderTwo = new DialogLoader();
        dialogLoaderThree = new DialogLoader();

    }

    public void bookCar(String price, String YearOfCar, String NumberOfCar, String FromKMToKM, String CarType, String CarModel, String EngineCapacity,
                        String TransmissionType, String ViolationDocument, String LicenseStatus, String RegistrationFees, String PossibilityOfExamination
            , String Note, String TenderId) {

        if (Validation.validateFields(price)) {
            ErrorDialog((Activity) mContext, mContext.getString(R.string.price_is_required));
        } else {
            BookCarBody bookCarBody = new BookCarBody();
            bookCarBody.setYearOfCar(YearOfCar);
            bookCarBody.setNumberOfCar(NumberOfCar);
            bookCarBody.setFromKMToKM(FromKMToKM);
            bookCarBody.setCarType(CarType);
            bookCarBody.setCarModel(CarModel);
            bookCarBody.setEngineCapacity(EngineCapacity);
            bookCarBody.setNote(Note);
            bookCarBody.setTransmissionType(TransmissionType);
            bookCarBody.setViolationDocument(ViolationDocument);
            bookCarBody.setLicenseStatus(LicenseStatus);
            bookCarBody.setRegistrationFees(RegistrationFees);
            bookCarBody.setPossibilityOfExamination(PossibilityOfExamination);
            bookCarBody.setTenderId(TenderId);

            if (Validation.isConnected(mContext)) {
                dialogLoaderThree.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "");
                mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                        .bookCar(bookCarBody)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handleResponseBookCar, this::handleError));
            } else {
                buildDialog((Activity) mContext).show().setCanceledOnTouchOutside(false);
            }
        }
    }

    private void handleResponseBookCar(BookCarResponse bookCarResponse) {
        if (dialogLoaderThree.isAdded()) {
            dialogLoaderThree.dismiss();
        }

        Log.d("BOOKSUCCESS", "handleResponseBookCar: " + bookCarResponse.toString());
        showDialogCreate();
    }

    public void getMetaData() {
        if (Validation.isConnected(mContext)) {
            dialogLoader.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "");
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getMetaData()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseMetaCar, this::handleError));
        } else {
            buildDialog((Activity) mContext).show().setCanceledOnTouchOutside(false);
        }
    }


    private void handleResponseMetaCar(MetaDataCar metaDataCar) {
        Log.d(TAG, "handleResponseMetaCar: " + metaDataCar.toString());
        dialogLoader.dismiss();
        fillDataCarInterface.getMetaCar(metaDataCar);
    }

    public void validationAddTinder(String yearOfCar, String NumberOfCar, String FromToKM
            , String CarTypeId, String CarModelId,
                                    String TenderId, String EngineCapacity, String TransmissionType, String ViolationDocument,
                                    String LicenseStatus, String RegistrationFees, String PossibilityOfExamination, String note) {
        if (Validation.validateFields(yearOfCar)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.year_of_car_error));

        } else if (Validation.validateFields(NumberOfCar)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.numer_of_car_error));

        } else if (Validation.validateFields(FromToKM)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.from_km_is_required));

        } else if (Validation.validateFields(CarTypeId)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.car_typr_error));

        } else if (Validation.validateFields(CarModelId)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.car_model_error));

        } else if (Validation.validateFields(note)) {
            Constant.showErrorDialog(mContext, mContext.getString(R.string.note_error));

        } else {

            FillDataCar fillDataCar = new FillDataCar();
            fillDataCar.setYearOfCar(yearOfCar);
            fillDataCar.setNumberOfCar(NumberOfCar);
            fillDataCar.setFromKM(FromToKM);
            Log.d(TAG, "validationAddTinder: " + FromToKM);
            fillDataCar.setCarTypeId(CarTypeId);
            fillDataCar.setCarModelId(CarModelId);
            fillDataCar.setTenderId(TenderId);
            fillDataCar.setEngineCapacity(EngineCapacity);
            fillDataCar.setTransmissionType(TransmissionType);
            fillDataCar.setNote(note);
            fillDataCar.setViolationDocument(ViolationDocument);
            fillDataCar.setLicenseStatus(LicenseStatus);
            fillDataCar.setRegistrationFees(RegistrationFees);
            fillDataCar.setPossibilityOfExamination(PossibilityOfExamination);

            fillDataCar(fillDataCar);

        }
    }

    private void fillDataCar(FillDataCar fillDataCar) {
        if (Validation.isConnected(mContext)) {
            dialogLoaderTwo.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "");

            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString("UserID", ""))
                    .fillDataCar(fillDataCar)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseFillData, this::handleError));
        } else {
            buildDialog((Activity) mContext).show().setCanceledOnTouchOutside(false);
        }
    }

    private void handleResponseFillData(FillDataCar fillDataCar) {
        if (dialogLoaderTwo.isAdded()) {
            dialogLoaderTwo.dismiss();
        }
        Log.d(TAG, "handleResponseFillData: " + fillDataCar.toString());

        showDialogCreate();

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
