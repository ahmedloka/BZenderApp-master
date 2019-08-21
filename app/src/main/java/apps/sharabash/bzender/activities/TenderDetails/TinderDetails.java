package apps.sharabash.bzender.activities.TenderDetails;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import apps.sharabash.bzender.Models.TendersDetails.TenderDetails;
import apps.sharabash.bzender.Models.TendersDetails.electrical.TenderDetailsElectrical;
import apps.sharabash.bzender.Models.bookCar.BookCarResponse;
import apps.sharabash.bzender.Models.bookElectrical.BookElectricalResponse;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.activities.TenderCarDetails;
import apps.sharabash.bzender.activities.TenderElectricalDetails;

public class TinderDetails extends AppCompatActivity implements TenderDetailsInterface, View.OnClickListener {

    private TextView mTxtStart;
    private TextView mTxtEnd;
    private TextView mLocationName;
    private TextView mCatId;
    private TextView mTenderName;
    private TextView mDesc;

    private String language;
    private String tenderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinder_details);

        SharedPreferences mSharedPreferences = getSharedPreferences("MySharedPreference", MODE_PRIVATE);
        language = mSharedPreferences.getString(Constant.language, Locale.getDefault().getDisplayLanguage());

        TenderDetailsPresenter tenderDetailsPresenter = new TenderDetailsPresenter(this, this);
        tenderId = getIntent().getStringExtra(Constant.TENDER_ID);
        tenderDetailsPresenter.getTenderDetails(tenderId);

        initViews();

    }

    private void initViews() {

        AppCompatImageView mImgBack = findViewById(R.id.imageNavigationIcon);
        mImgBack.setOnClickListener(v -> {
            NavUtils.navigateUpFromSameTask(this);
        });

        mTxtStart = findViewById(R.id.startDate);
        mTxtEnd = findViewById(R.id.endDate);
        mLocationName = findViewById(R.id.location_name);
        mCatId = findViewById(R.id.category_name);
        mTenderName = findViewById(R.id.tender_name);
        mDesc = findViewById(R.id.desc);
        Button mBtnViewFile = findViewById(R.id.download_file);
        mBtnViewFile.setOnClickListener(this);
    }

    @Override
    public void handleSuccess(TenderDetails tenderDetails) {
        Log.d("SUCCESS", "handleSuccess: " + tenderDetails.toString());
        //[TenderCar = null, Description = desc, SponserCount = 0, PathFile = null, ImageUrl = null, EndDate = 2019-07-15T00:00:00,
        // CityEnglishName = null, StartDate = 2015-03-12T00:00:00, TenderName = title, Payed = false, statusId = 6, TenderElectrical = null
        // , CountryArabicName = NewYork, Id = 10391, CityArabicName = null, statudName = Pending, CountryEnglishName = cairo, TenderType = null]
        mTxtStart.setText(tenderDetails.getStartDate());
        mTxtEnd.setText(tenderDetails.getEndDate());
        if (language.equals("ar"))
            mLocationName.setText(tenderDetails.getCityArabicName());
        else
            mLocationName.setText(tenderDetails.getCountryEnglishName());

        mTenderName.setText(tenderDetails.getTenderName());
        mDesc.setText(tenderDetails.getDescription());

        if (Constant.categoryId.equals(String.valueOf(10021))) {
            if (language.equals("ar")) {
                mCatId.setText("سيارات");
            } else {
                mCatId.setText("Cars");
            }
        } else {
            if (language.equals("ar")) {
                mCatId.setText("الكترونيات");
            } else {
                mCatId.setText("Electronics");
            }
        }

    }

    @Override
    public void getDetailsElectrical(TenderDetailsElectrical tenderDetailsElectrical) {

    }

    @Override
    public void getBookCarId(BookCarResponse bookCarResponse) {

    }

    @Override
    public void getElectricalId(BookElectricalResponse bookElectricalResponse) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.download_file) {
            Intent intent;

            if (Constant.categoryId.equals(String.valueOf(10021))) {
                intent = new Intent(this, TenderCarDetails.class);
                intent.putExtra(Constant.TENDER_ID, tenderId);
                startActivity(intent);
                finish();
            } else {
                intent = new Intent(this, TenderElectricalDetails.class);
                intent.putExtra(Constant.TENDER_ID, tenderId);
                startActivity(intent);
                finish();
            }
        }
    }

//    @Override
//    public void handleSuccess(TenderDetails tenderDetails) {
//        if (tenderDetails != null) {
//            if (tenderDetails.getStartDate() != null) {
//                mTxtStart.setText(parseDateTo_yyyyMMdd( tenderDetails.getStartDate()));
//            }
//            if (tenderDetails.getEndDate() != null) {
//                mTxtEnd.setText(parseDateTo_yyyyMMdd(tenderDetails.getEndDate()));
//            }
//            if (tenderDetails.getAddress() != null) {
//                mLocationName.setText(tenderDetails.getAddress());
//            }
//            if (tenderDetails.getCategoryEnglishName() != null) {
//                mCatId.setText(tenderDetails.getCategoryEnglishName());
//            }
//            if (tenderDetails.getTenderNameEnglish() != null) {
//                mTenderName.setText(tenderDetails.getTenderNameEnglish());
//            }
//            if (tenderDetails.getDescription() != null) {
//                mDesc.setText(tenderDetails.getDescription());
//            }
//            if (tenderDetails.getPayed() != null) {
//                if (tenderDetails.getPayed().equals("true"))
//                {
//                    mBtnViewFile.setEnabled(true);
//                }
//                else
//                {
//                    mBtnViewFile.setOnClickListener(v -> {
//                        AlertDialog.Builder builder1;
//                        final AlertDialog dialog1;
//                        builder1 = new AlertDialog.Builder(TinderDetails.this);
//                        @SuppressLint("InflateParams")
//                        View mview = getLayoutInflater().inflate(R.layout.dailog_complete_add_tinder, null);
//                        builder1.setView(mview);
//                        Button  pay = mview.findViewById(R.id.pay);
//
//                        dialog1 = builder1.create();
//                        Window window = dialog1.getWindow();
//                        if (window != null) {
//                            window.setGravity(Gravity.CENTER);
//                        }
//                        dialog1.show();
//                        pay.setOnClickListener(v1 -> {
//                            final int GET_NEW_CARD = 2;
//                            Intent intent = new Intent(TinderDetails.this, CardEditActivity.class);
//                            startActivityForResult(intent,GET_NEW_CARD);
//                            dialog1.dismiss();
//                        });
//                    });
//                }
//            }
//
//        }
//    }
}
