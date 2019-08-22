package apps.sharabash.bzender.activities.fillDataElectrical;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.Log;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;
import com.ybs.countrypicker.CountryPicker;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.ButtonBook;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.MyEditText;
import apps.sharabash.bzender.Utills.MyTextView;

public class FillDataElectricalActivity extends AppCompatActivity implements View.OnClickListener {

    private FillDataElectricalPresenter fillDataElectricalPresenter;

    private AppCompatRadioButton radioBtnNew;
    private AppCompatRadioButton radioBtnUsed;
    private AppCompatRadioButton radioBtnYes;
    private AppCompatRadioButton radioBtnNo;
    private MyTextView checkbox;
    private MyEditText etNote;

    private String status = "true";
    private String guaratntee = "true";
    private String year;
    private String number;
    private String model;
    private String country = "test";
    private String type;

    private CountryPicker picker;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideLeft(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_data_electrical);

        fillDataElectricalPresenter = new FillDataElectricalPresenter(this);

        initViews();

    }

    private void initViews() {

        SharedPreferences mSharedPreferences = getSharedPreferences("MySharedPreference", MODE_PRIVATE);
        String language = mSharedPreferences.getString(Constant.language, Locale.getDefault().getDisplayLanguage());


        AppCompatImageView mImgBack = findViewById(R.id.imageNavigationIcon);
        mImgBack.setOnClickListener(v -> {
            NavUtils.navigateUpFromSameTask(this);
            Animatoo.animateSlideLeft(this);
        });
        String text;
        if (language.equals("ar")) {
            text = "اختر البلد";
        } else {
            text = "Select Country";
        }
        picker = CountryPicker.newInstance(text);
        picker.setListener((name, code, dialCode, flagDrawableResID) -> {
            country = name;
            Log.d("Country", "initViews: " + name);
            if (!country.equals("test")) {
                checkbox.setText(country);
            }
            picker.dismiss();
        });

        radioBtnNew = findViewById(R.id.radio_btn_new);
        radioBtnNew.setOnClickListener(this);

        radioBtnUsed = findViewById(R.id.radio_btn_used);
        radioBtnUsed.setOnClickListener(this);

        radioBtnYes = findViewById(R.id.radio_btn_yes);
        radioBtnYes.setOnClickListener(this);

        radioBtnNo = findViewById(R.id.radio_btn_no);
        radioBtnNo.setOnClickListener(this);


        checkbox = findViewById(R.id.checkbox);
        checkbox.setOnClickListener(this);

        etNote = findViewById(R.id.et_note);

        ButtonBook btnSend = findViewById(R.id.btn_send);
        btnSend.setOnClickListener(this);


        WheelView<String> wheelView = findViewById(R.id.wheelview);
        WheelView<String> wheelviewTwo = findViewById(R.id.wheelviewTwo);

        WheelView<String> wheelViewThree = findViewById(R.id.wheelview_three);
        WheelView<String> wheelViewFour = findViewById(R.id.wheelview_four);

        wheelView.setWheelAdapter(new ArrayWheelAdapter(this));
        wheelView.setSkin(WheelView.Skin.Holo);
        WheelView.WheelViewStyle styleOne = getWheelViewStyle();
        wheelView.setStyle(styleOne);
        wheelView.setWheelClickable(true);


        List<String> listData = new ArrayList<>();
        listData.add("TV - Audio - Video");
        listData.add("Computers - Tablets");
        listData.add("Video games - Consoles");
        listData.add("Cameras - Imaging");
        listData.add("Home Appliances");

        wheelView.setWheelData(listData);
        wheelView.setOnWheelItemSelectedListener((position, s) -> {
            model = s;
        });

        wheelviewTwo.setWheelAdapter(new ArrayWheelAdapter(this));
        wheelviewTwo.setSkin(WheelView.Skin.Holo);

        WheelView.WheelViewStyle styleTwo = getWheelViewStyle();
        wheelviewTwo.setStyle(styleOne);
        //wheelviewTwo.setOnWheelItemSelectedListener(this);


        List<String> listDataTwo = new ArrayList<>();
        listDataTwo.add("BMW");
        listDataTwo.add("AUDI");
        listDataTwo.add("BYD");

        wheelviewTwo.setWheelData(listDataTwo);
        wheelviewTwo.setOnWheelItemSelectedListener((position, s) -> {
            type = s;
        });


        wheelViewThree.setWheelAdapter(new ArrayWheelAdapter(this));
        wheelViewThree.setSkin(WheelView.Skin.Common);
        wheelViewThree.setSkin(WheelView.Skin.Holo);
        wheelViewThree.setStyle(styleOne);
        wheelViewThree.setWheelClickable(true);


        List<String> listDataThree = new ArrayList<>();
        for (int i = 1989; i < 2021; i++) {
            listDataThree.add(String.valueOf(i));
        }
        wheelViewThree.setWheelData(listDataThree);

        wheelViewThree.setOnWheelItemSelectedListener((position, s) -> {
            year = s;
        });


        wheelViewFour.setWheelAdapter(new ArrayWheelAdapter(this));
        wheelViewFour.setSkin(WheelView.Skin.Common);
        wheelViewFour.setSkin(WheelView.Skin.Holo);
        wheelViewFour.setStyle(styleOne);
        wheelViewFour.setWheelClickable(true);


        List<String> listDataFour = new ArrayList<>();
        for (int i = 1; i < 101; i++) {
            listDataFour.add(String.valueOf(i));
        }

        wheelViewFour.setWheelData(listDataFour);

        wheelViewFour.setOnWheelItemSelectedListener((position, s) -> {
            number = s;
        });


    }

    @NotNull
    private WheelView.WheelViewStyle getWheelViewStyle() {
        WheelView.WheelViewStyle styleOne = new WheelView.WheelViewStyle();
        styleOne.selectedTextColor = Color.parseColor("#00609A");
        styleOne.textColor = Color.parseColor("#BABABA");
        styleOne.backgroundColor = Color.parseColor("#FFFFFF");
        styleOne.holoBorderColor = Color.parseColor("#00609A");
        return styleOne;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio_btn_new:
                radioBtnNew.setChecked(true);
                radioBtnUsed.setChecked(false);

                status = "true";
                break;
            case R.id.radio_btn_used:
                radioBtnNew.setChecked(false);
                radioBtnUsed.setChecked(true);

                status = "false";
                break;

            case R.id.radio_btn_yes:
                radioBtnYes.setChecked(true);
                radioBtnNo.setChecked(false);

                guaratntee = "true";
                break;
            case R.id.radio_btn_no:
                radioBtnNo.setChecked(true);
                radioBtnYes.setChecked(false);

                guaratntee = "false";
                break;

            case R.id.btn_send:

                Log.d("TESTELECT", "onClick: " + "   Status   " + status + "  TenderId    " + (Constant.ADD_TENDER_ID) +
                        "    UnitType    " + type + "     Note     " + etNote.getText().toString() + "    MODEL    "
                        + model + "    NumberOfUnit    " + number
                        + "    YEAR    " + year + "    ORIGIN    " + country);
                fillDataElectricalPresenter.validateFillElectrical(type, model, year, number, status
                        , guaratntee, country, etNote.getText().toString(), String.valueOf(Constant.ADD_TENDER_ID));
                break;

            case R.id.checkbox:

                if (!country.equals("test")) {
                    checkbox.setText(country);
                }
                picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");

                break;
        }
    }
}
