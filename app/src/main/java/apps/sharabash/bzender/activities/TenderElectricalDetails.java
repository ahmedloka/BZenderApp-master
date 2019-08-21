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

import java.util.Locale;

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

public class TenderElectricalDetails extends AppCompatActivity implements TenderDetailsInterface {

    private String tenderId;

    private TenderDetailsPresenter tenderDetailsPresenter;

    private MyEditText etPrice;

    private MyTextView txtType;
    private MyTextView txtModel;
    private MyTextView txtYear;
    private MyTextView txtNumber;
    private MyTextView txtElectrical;
    private MyTextView txtOrigin;
    private MyTextView txtExtra;


    private String UnitType = "false";
    private String Model = "false";
    private final String YearOfManufacture = "false";
    private String NumberOfUnit = "false";
    private String Status = "false";
    private String OriginManufacturer = "false";
    private String Note = "false";
    private String OwnerUserId = "false";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tender_electrical_details);

        NestedScrollView mNestedScroll = findViewById(R.id.nested_scroll_view);
        mNestedScroll.setFocusable(false);

        LinearLayoutCompat mLinear = findViewById(R.id.linear_home);
        mLinear.requestFocus();

        SharedPreferences mSharedPreferences = getSharedPreferences("MySharedPreference", MODE_PRIVATE);
        String language = mSharedPreferences.getString(Constant.language, Locale.getDefault().getDisplayLanguage());

        tenderDetailsPresenter = new TenderDetailsPresenter(this, this);
        tenderId = getIntent().getStringExtra(Constant.TENDER_ID);
        tenderDetailsPresenter.getTenderDetails(tenderId);

        Log.d("TENDERID", "onCreate: " + tenderId);

        ButtonBook btnBook = findViewById(R.id.btn_book);
        btnBook.setOnClickListener(v -> {
            Log.d("CLICK", "onCreate: " + (etPrice.getText().toString() + tenderId + UnitType + Model + YearOfManufacture +
                    NumberOfUnit + Status + OriginManufacturer + Note));
            tenderDetailsPresenter.validateBookTenderElectrical(etPrice.getText().toString(), tenderId, UnitType, Model, YearOfManufacture,
                    NumberOfUnit, Status, OriginManufacturer, Note);
        });


        initViews();
    }

    private void initViews() {
        AppCompatImageView mImgBack = findViewById(R.id.imageNavigationIcon);
        mImgBack.setOnClickListener(v -> {
            NavUtils.navigateUpFromSameTask(this);
        });


        MyEditText etDesc = findViewById(R.id.et_desc);
        etPrice = findViewById(R.id.et_price);

        txtType = findViewById(R.id.txt_type);
        txtModel = findViewById(R.id.txt_model);
        txtYear = findViewById(R.id.txt_year);
        txtNumber = findViewById(R.id.txt_number);
        txtElectrical = findViewById(R.id.txt_electrical);
        txtOrigin = findViewById(R.id.txt_origin);
        txtExtra = findViewById(R.id.txt_extra);

        AppCompatCheckBox checkboxType = findViewById(R.id.checkbox_type);
        checkboxType.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                UnitType = "true";
            } else {
                UnitType = "false";
            }
        });

        AppCompatCheckBox checkboxModel = findViewById(R.id.checkbox_model);
        checkboxModel.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Model = "true";
            } else {
                Model = "false";
            }
        });

        AppCompatCheckBox checkboxYear = findViewById(R.id.checkbox_year);
        checkboxYear.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                UnitType = "true";
            } else {
                UnitType = "false";
            }
        });

        AppCompatCheckBox checkboxNumber = findViewById(R.id.checkbox_number);
        checkboxNumber.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                NumberOfUnit = "true";
            } else {
                NumberOfUnit = "false";
            }
        });

        AppCompatCheckBox checkboxElectricak = findViewById(R.id.checkbox_electricak);
        checkboxElectricak.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Status = "true";
            } else {
                Status = "false";
            }
        });


        AppCompatCheckBox checkboxOrigin = findViewById(R.id.checkbox_origin);
        checkboxOrigin.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                OriginManufacturer = "true";
            } else {
                OriginManufacturer = "false";
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

    }

    @Override
    public void handleSuccess(TenderDetails tenderDetails) {

        Log.d("TENDERIDDETAILS", "handleSuccess: " + tenderDetails.getTenderElectrical().getTenderId());

        tenderId = tenderDetails.getId();

        txtType.append("  " + tenderDetails.getTenderElectrical().getUnitType());
        txtModel.append("  " + tenderDetails.getTenderElectrical().getModel());
        txtYear.append("  " + tenderDetails.getTenderElectrical().getYearOfManufacture());
        txtNumber.append("  " + tenderDetails.getTenderElectrical().getNumberOfUnit());
        txtOrigin.append("  " + tenderDetails.getTenderElectrical().getOriginManufacturer());
        txtExtra.append("  " + tenderDetails.getTenderElectrical().getNote());
        txtElectrical.append("  " + tenderDetails.getTenderElectrical().getStatus());
    }

    @Override
    public void getDetailsElectrical(TenderDetailsElectrical tenderDetailsElectrical) {
        Log.d("DATAA", "handleSuccess: " + tenderDetailsElectrical.toString());


    }

    @Override
    public void getBookCarId(BookCarResponse bookCarResponse) {

    }

    @Override
    public void getElectricalId(BookElectricalResponse bookElectricalResponse) {
        Constant.BOOKING_ID = bookElectricalResponse.getTenderElectricalBookingId();
        Log.d("getElectricalId", "getElectricalId: " + bookElectricalResponse.getTenderElectricalBookingId());
    }
}
