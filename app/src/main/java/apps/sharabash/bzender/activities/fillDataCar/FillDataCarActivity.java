package apps.sharabash.bzender.activities.fillDataCar;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import apps.sharabash.bzender.Models.metadataCar.MetaDataCar;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.ButtonBook;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.MyEditText;

public class FillDataCarActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, FillDataCarInterface {

    private static final String TAG = "FILL_DATA_AC";


    private final List<String> wheelDataModel = new ArrayList<>();
    private final List<String> wheelDataType = new ArrayList<>();

    private String wheelModelId;
    private String wheelTypeId;

    private AppCompatImageView imageNavigationIcon;
    private AppCompatRadioButton radioBtnManual;
    private AppCompatRadioButton radioBtnAuto;
    private MyEditText etEngineCapacity;
    private MyEditText etExtraFeat;


    private FillDataCarPresenter fillDataCarPresenter;

    private String violation = "false";
    private String licence = "false";
    private String fees = "false";
    private String thePossibleOf = "false";

    private String transmissionType = "true";

    private String carType;
    private String carModel;
    private String yearOfCar;
    private String numberOfCar;

    private String engineCapacity = "0 to 800";
    private String fromToKm = "0 to 999";


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideLeft(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_data_car);


        fillDataCarPresenter = new FillDataCarPresenter(this, this);

        initViews();


    }

    private void initViews() {

        SharedPreferences mSharedPreferences = getSharedPreferences("tokenDetail", MODE_PRIVATE);
        String language = mSharedPreferences.getString(Constant.language, Locale.getDefault().getDisplayLanguage());


        AppCompatImageView mImgBack = findViewById(R.id.imageNavigationIcon);
        mImgBack.setOnClickListener(v -> {
            NavUtils.navigateUpFromSameTask(this);
            Animatoo.animateSlideLeft(this);
        });

        RadioGroup radioGroupManuAuto = findViewById(R.id.radio_group_manu_auto);

        radioBtnManual = findViewById(R.id.radio_btn_manual);
        radioBtnManual.setOnClickListener(this);

        radioBtnAuto = findViewById(R.id.radio_btn_auto);
        radioBtnAuto.setOnClickListener(this);


        AppCompatCheckBox checkboxVio = findViewById(R.id.checkbox_vio);
        checkboxVio.setOnCheckedChangeListener(this);

        AppCompatCheckBox checkboxLice = findViewById(R.id.checkbox_lice);
        checkboxLice.setOnCheckedChangeListener(this);

        AppCompatCheckBox checkboxRegis = findViewById(R.id.checkbox_regis);
        checkboxRegis.setOnCheckedChangeListener(this);

        AppCompatCheckBox checkboxExam = findViewById(R.id.checkbox_exam);
        checkboxExam.setOnCheckedChangeListener(this);

        etExtraFeat = findViewById(R.id.et_extra_feat);

        ButtonBook btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(this);

        WheelView<String> wheelViewCarTypes = findViewById(R.id.wheelview);
        WheelView<String> wheelviewCarModels = findViewById(R.id.wheelviewTwo);

        WheelView<String> wheelViewYearOfCar = findViewById(R.id.wheelview_three);
        WheelView<String> wheelViewNumberOfCar = findViewById(R.id.wheelview_four);

        //1
        wheelViewCarTypes.setWheelAdapter(new ArrayWheelAdapter(this));
        wheelViewCarTypes.setSkin(WheelView.Skin.Holo);

        WheelView.WheelViewStyle styleOne = new WheelView.WheelViewStyle();
        setWheelStyle(styleOne);

        wheelViewCarTypes.setStyle(styleOne);
        wheelViewCarTypes.setWheelClickable(true);


        for (int i = 0; i < Constant.wheelDataCarType.size(); i++) {
            if (language.equals("ar")) {
                wheelDataType.add(Constant.wheelDataCarType.get(i).getNameLT());
            } else {
                wheelDataType.add(Constant.wheelDataCarType.get(i).getName());
            }
        }

        wheelViewCarTypes.setWheelData(wheelDataType);

        wheelViewCarTypes.setOnWheelItemSelectedListener((position, s) -> {

            carType = String.valueOf(++position);
        });

        //2
        wheelviewCarModels.setWheelAdapter(new ArrayWheelAdapter(this));
        wheelviewCarModels.setSkin(WheelView.Skin.Holo);


        wheelviewCarModels.setStyle(styleOne);

        wheelviewCarModels.setWheelClickable(true);


        for (int i = 0; i < Constant.wheelDataCarModel.size(); i++) {
            if (language.equals("ar")) {
                wheelDataModel.add(Constant.wheelDataCarModel.get(i).getNameLT());
            } else {
                wheelDataModel.add(Constant.wheelDataCarModel.get(i).getName());
            }
        }

        wheelviewCarModels.setWheelData(wheelDataModel);

        wheelviewCarModels.setOnWheelItemSelectedListener((position, s) -> {
            carModel = String.valueOf(++position);

        });

        //3
        wheelViewYearOfCar.setWheelAdapter(new ArrayWheelAdapter(this));
        wheelViewYearOfCar.setSkin(WheelView.Skin.Holo);

        wheelViewYearOfCar.setStyle(styleOne);
        wheelViewYearOfCar.setWheelClickable(true);


        List<String> listDataThree = new ArrayList<>();

        addWheelYears(listDataThree);
        wheelViewYearOfCar.setWheelData(listDataThree);
        wheelViewYearOfCar.setOnWheelItemSelectedListener((position, s) -> {
            yearOfCar = s;

        });


        //4
        wheelViewNumberOfCar.setWheelAdapter(new ArrayWheelAdapter(this));
        wheelViewNumberOfCar.setSkin(WheelView.Skin.Holo);

        wheelViewNumberOfCar.setStyle(styleOne);
        wheelViewNumberOfCar.setWheelClickable(true);


        List<String> listDataFour = new ArrayList<>();
        for (int i = 1; i < 101; i++) {
            listDataFour.add(String.valueOf(i));
        }

        wheelViewNumberOfCar.setWheelData(listDataFour);
        wheelViewNumberOfCar.setOnWheelItemSelectedListener((position, s) -> {
            numberOfCar = s;
        });


        AppCompatSpinner spinnerTwo = findViewById(R.id.spinner_two);


        List<String> spinnerDataTwo = new ArrayList<>();
        spinnerDataTwo.add("0 to 800");
        spinnerDataTwo.add("1000 to 1300");
        spinnerDataTwo.add("1400 to 1600");
        spinnerDataTwo.add("1800 to 2000");
        spinnerDataTwo.add("2200 to 2800");
        spinnerDataTwo.add("+30000");


        engineCapacity = spinnerDataTwo.get(0);

        ArrayAdapter<String> dataAdapterTwo = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, spinnerDataTwo);
        dataAdapterTwo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTwo.setAdapter(dataAdapterTwo);

        spinnerTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);

                engineCapacity = spinnerDataTwo.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        AppCompatSpinner spinner = findViewById(R.id.spinner);

        List<String> spinnerData = new ArrayList<>();
        spinnerData.add("0 to 999");
        spinnerData.add("1000 to 4999");
        spinnerData.add("5000 to 9999");
        spinnerData.add("10000 to 19999");
        spinnerData.add("20000 to 29999");
        spinnerData.add("30000 to 39999");
        spinnerData.add("40000 to 49999");
        spinnerData.add("60000 to 69999");
        spinnerData.add("70000 to 79999");
        spinnerData.add("80000 to 89999");
        spinnerData.add("100000 to 109999");
        spinnerData.add("110000 to 119999");
        spinnerData.add("120000 to 129999");
        spinnerData.add("130000 to 139999");
        spinnerData.add("140000 to 149999");
        spinnerData.add("150000 to 159999");
        spinnerData.add("160000 to 169999");
        spinnerData.add("170000 to 179999");
        spinnerData.add("180000 to 189999");
        spinnerData.add("190000 to 199999");

        fromToKm = spinnerData.get(0);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, spinnerData);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);

                fromToKm = spinnerData.get(position);
                Log.d(TAG, "onItemSelected: " + fromToKm);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addWheelYears(List<String> listDataThree) {
        for (int i = 1989; i < 2021; i++) {
            listDataThree.add(String.valueOf(i));
        }
    }

    private void setWheelStyle(WheelView.WheelViewStyle styleOne) {
        styleOne.selectedTextColor = Color.parseColor("#00609A");
        styleOne.textColor = Color.parseColor("#BABABA");
        styleOne.backgroundColor = Color.parseColor("#FFFFFF");
        styleOne.holoBorderColor = Color.parseColor("#00609A");
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.checkbox_vio:
                if (isChecked) {
                    violation = "true";
                } else {
                    violation = "false";
                }


                break;
            case R.id.checkbox_regis:
                if (isChecked) {
                    fees = "true";
                } else {
                    fees = "false";
                }
                break;
            case R.id.checkbox_lice:
                if (isChecked) {
                    licence = "true";
                } else {
                    licence = "true";
                }

                break;
            case R.id.checkbox_exam:
                if (isChecked) {
                    thePossibleOf = "true";
                } else {
                    thePossibleOf = "false ";
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio_btn_manual:
                radioBtnManual.setChecked(true);
                radioBtnAuto.setChecked(false);

                transmissionType = "true";


                break;
            case R.id.radio_btn_auto:
                radioBtnAuto.setChecked(true);
                Log.d(TAG, "onClick: " + radioBtnAuto.getText().toString());
                radioBtnManual.setChecked(false);

                transmissionType = "false";
                break;
            case R.id.btn_next:
                Log.d(TAG, ":DATA THAT SEND " + yearOfCar + numberOfCar + fromToKm + carType + carModel
                        + Constant.ADD_TENDER_ID + engineCapacity + transmissionType + violation
                        + licence + fees + thePossibleOf + etExtraFeat.getText().toString());
                fillDataCarPresenter.validationAddTinder(yearOfCar, numberOfCar, fromToKm, carType, carModel
                        , String.valueOf(Constant.ADD_TENDER_ID), engineCapacity, transmissionType, violation
                        , licence, fees, thePossibleOf, etExtraFeat.getText().toString());
                break;
        }
    }


    @Override
    public void getMetaCar(MetaDataCar metaDataCar) {

    }
}
