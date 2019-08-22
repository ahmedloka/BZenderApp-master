package apps.sharabash.bzender.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.Locale;
import java.util.Objects;

import apps.sharabash.bzender.Models.TendersDetails.TenderDetails;
import apps.sharabash.bzender.Models.TendersDetails.electrical.TenderDetailsElectrical;
import apps.sharabash.bzender.Models.bookCar.BookCarResponse;
import apps.sharabash.bzender.Models.bookElectrical.BookElectricalResponse;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.ButtonBook;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.MyEditText;
import apps.sharabash.bzender.Utills.MyTextView;
import apps.sharabash.bzender.activities.TenderDetails.TenderDetailsInterface;
import apps.sharabash.bzender.activities.TenderDetails.TenderDetailsPresenter;
import apps.sharabash.bzender.activities.fillDataCar.FillDataCarPresenter;

public class TenderCarDetails extends AppCompatActivity implements View.OnClickListener, TenderDetailsInterface {

    private String tenderId;

    private String language;

    private TenderDetailsPresenter tenderDetailsPresenter;

    private MyEditText etPrice;

    private MyTextView txtType;
    private MyTextView txtModel;
    private MyTextView txtYear;
    private MyTextView txtNumber;
    private MyTextView txtKilometers;
    private MyTextView txtTransmission;
    private MyTextView txtGueantee;
    private MyTextView txtLicense;
    private MyTextView txtFees;
    private MyTextView txtScan;
    private MyTextView txtEngine;
    private MyTextView txtExtra;


    private String YearOfCar = "false";
    private String NumberOfCar = "false";
    private String FromKMToKM = "false";
    private String CarType = "false";
    private String CarModel = "false";
    private String EngineCapacity = "false";
    private String TransmissionType = "false";
    private String ViolationDocument = "false";
    private String LicenseStatus = "false";
    private String RegistrationFees = "false";
    private String PossibilityOfExamination = "false";
    private String Note = "false";

    private FillDataCarPresenter fillDataCarPresenter;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideLeft(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tender_car_details);


        NestedScrollView mNestedScroll = findViewById(R.id.nested_scroll_view);
        mNestedScroll.setFocusable(false);

        LinearLayoutCompat mLinear = findViewById(R.id.linear_home);
        mLinear.requestFocus();

        SharedPreferences mSharedPreferences = getSharedPreferences("MySharedPreference", MODE_PRIVATE);
        language = mSharedPreferences.getString(Constant.language, Locale.getDefault().getDisplayLanguage());
        initViews();

    }

    private void initViews() {
        AppCompatImageView mImgBack = findViewById(R.id.imageNavigationIcon);
        mImgBack.setOnClickListener(v -> {
            NavUtils.navigateUpFromSameTask(this);
            Animatoo.animateSlideLeft(this);
        });


        tenderDetailsPresenter = new TenderDetailsPresenter(this, this);
        String tenderId = getIntent().getStringExtra(Constant.TENDER_ID);
        tenderDetailsPresenter.getTenderDetails(tenderId);

        MyEditText etDesc = findViewById(R.id.et_desc);
        etPrice = findViewById(R.id.et_price);

        txtType = findViewById(R.id.txt_type);
        txtModel = findViewById(R.id.txt_model);
        txtYear = findViewById(R.id.txt_year);
        txtNumber = findViewById(R.id.txt_number);
        txtKilometers = findViewById(R.id.txt_kilometers);
        txtTransmission = findViewById(R.id.txt_transmission);
        txtLicense = findViewById(R.id.txt_license);
        txtFees = findViewById(R.id.txt_fees);
        txtScan = findViewById(R.id.txt_scan);
        txtEngine = findViewById(R.id.txt_engine);
        txtExtra = findViewById(R.id.txt_extra);

        AppCompatCheckBox checkboxType = findViewById(R.id.checkbox_type);
        checkboxType.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                CarType = "true";
            } else {
                CarType = "false";
            }
        });

        AppCompatCheckBox checkboxModel = findViewById(R.id.checkbox_model);
        checkboxModel.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                CarModel = "true";
            } else {
                CarModel = "false";
            }
        });

        AppCompatCheckBox checkboxYear = findViewById(R.id.checkbox_year);
        checkboxYear.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                YearOfCar = "true";
            } else {
                YearOfCar = "false";
            }
        });

        AppCompatCheckBox checkboxNumber = findViewById(R.id.checkbox_number);
        checkboxNumber.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                NumberOfCar = "true";
            } else {
                NumberOfCar = "false";
            }
        });

        AppCompatCheckBox checkboxKilometers = findViewById(R.id.checkbox_kilometers);
        checkboxKilometers.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                FromKMToKM = "true";
            } else {
                FromKMToKM = "false";
            }
        });

        AppCompatCheckBox checkboxTransmission = findViewById(R.id.checkbox_transmission);
        checkboxTransmission.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                TransmissionType = "true";
            } else {
                TransmissionType = "false";
            }
        });

        /////////////////////////////////////


        AppCompatCheckBox checkboxLicense = findViewById(R.id.checkbox_license);
        checkboxLicense.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                LicenseStatus = "true";
            } else {
                LicenseStatus = "false";
            }
        });

        AppCompatCheckBox checkboxFees = findViewById(R.id.checkbox_fees);
        checkboxFees.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                RegistrationFees = "true";
            } else {
                RegistrationFees = "false";
            }
        });

        AppCompatCheckBox checkboxCanScan = findViewById(R.id.checkbox_can_scan);
        checkboxCanScan.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                PossibilityOfExamination = "true";
            } else {
                PossibilityOfExamination = "false";
            }
        });

        AppCompatCheckBox checkboxEngine = findViewById(R.id.checkbox_engine);
        checkboxEngine.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                EngineCapacity = "true";
            } else {
                EngineCapacity = "false";
            }
        });

        AppCompatCheckBox checkboxExtra = findViewById(R.id.checkbox_extra);
        checkboxExtra.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Note = "true";
            } else {
                Note = "false";
            }
        });


        ButtonBook btnBook = findViewById(R.id.btn_book);
        btnBook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_book) {
            Log.d("TENDER_ID", "onClick: " + tenderId);

            tenderDetailsPresenter.validateBookCarTender(Objects.requireNonNull(etPrice.getText()).toString(), tenderId, CarType, CarModel, YearOfCar, NumberOfCar, FromKMToKM, TransmissionType
                    , LicenseStatus, RegistrationFees, PossibilityOfExamination, EngineCapacity, Note);
        }
    }

    @Override
    public void handleSuccess(TenderDetails tenderDetails) {
        Log.d("DATAA", "handleSuccess: " + tenderDetails.getTenderCar().toString());
//[ ViolationDocument = false, EngineCapacity = engine capacity here,TenderId = 10362, TransmissionType = false, CarModelId = 1,
// Note = write all features here, LicenseStatus = true,
//   Id = 14, PossibilityOfExamination = false, CarTypeId = 2]

//        private MyTextView txtType;
//        private MyTextView txtModel;
//        private MyTextView txtYear;
//        private MyTextView txtNumber;
//        private MyTextView txtKilometers;
//        private MyTextView txtTransmission;
//        private MyTextView txtGueantee;
//        private MyTextView txtLicense;
//        private MyTextView txtFees;
//        private MyTextView txtScan;
//        private MyTextView txtEngine;
//        private MyTextView txtExtra;


        if (language.equals("ar")) {
            txtType.append("  " + tenderDetails.getTenderCar().getCarType().getNameLT());
            txtModel.append("  " + tenderDetails.getTenderCar().getCarModel().getNameLT());
        } else {
            txtType.append("  " + tenderDetails.getTenderCar().getCarType().getName());
            txtModel.append("  " + tenderDetails.getTenderCar().getCarModel().getName());
        }

        tenderId = tenderDetails.getId();

        txtYear.append("  " + tenderDetails.getTenderCar().getYearOfCar());
        txtNumber.append("  " + tenderDetails.getTenderCar().getNumberOfCar());
        txtKilometers.append("  " + tenderDetails.getTenderCar().getFromKM() + " to " + tenderDetails.getTenderCar().getToKM());
        if (tenderDetails.getTenderCar().getTransmissionType().equals("false")) {
            txtTransmission.append("  " + getString(R.string.used));
        } else {
            txtTransmission.append("  " + getString(R.string.neww));
        }
        //setText(txtGueantee,tenderDetails.getTenderCar());
        if (tenderDetails.getTenderCar().getLicenseStatus().equals("false")) {
            txtLicense.append("  " + getString(R.string.expired));
        } else {
            txtLicense.append("  " + getString(R.string.ongoing));
        }

        if (tenderDetails.getTenderCar().getRegistrationFees().equals("false")) {
            txtFees.append("  " + getString(R.string.unpaid));
        } else {
            txtFees.append("  " + getString(R.string.paid));
        }
        txtScan.append("  " + tenderDetails.getTenderCar().getPossibilityOfExamination());
        txtEngine.append("  " + tenderDetails.getTenderCar().getEngineCapacity());
        txtExtra.append("  " + tenderDetails.getTenderCar().getNote());
    }

    @Override
    public void getDetailsElectrical(TenderDetailsElectrical tenderDetailsElectrical) {

    }

    @Override
    public void getBookCarId(BookCarResponse bookCarResponse) {
        Constant.CAR_BOOKING_ID = bookCarResponse.getTenderCarBookingId();
        Log.d("getBookCarId", "getBookCarId: " + bookCarResponse.getTenderCarBookingId());
    }

    @Override
    public void getElectricalId(BookElectricalResponse bookElectricalResponse) {

    }

}
