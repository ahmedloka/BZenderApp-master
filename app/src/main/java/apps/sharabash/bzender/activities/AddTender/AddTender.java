package apps.sharabash.bzender.activities.AddTender;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import apps.sharabash.bzender.Models.AddTenderType;
import apps.sharabash.bzender.Models.AddTenders.AllCitiesModel;
import apps.sharabash.bzender.Models.CustomerAddress;
import apps.sharabash.bzender.Models.GetOffers;
import apps.sharabash.bzender.Models.home.getCategoryResponse;
import apps.sharabash.bzender.Models.metadataCar.CarModels;
import apps.sharabash.bzender.Models.metadataCar.CarTypes;
import apps.sharabash.bzender.Models.metadataCar.MetaDataCar;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.ButtonBook;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.DatePickerFragment;
import apps.sharabash.bzender.Utills.MyEditText;
import apps.sharabash.bzender.Utills.MyTextView;
import apps.sharabash.bzender.activities.Home.HomePresenter;
import apps.sharabash.bzender.activities.Home.homeInterface;
import apps.sharabash.bzender.activities.fillDataCar.FillDataCarInterface;
import apps.sharabash.bzender.activities.fillDataCar.FillDataCarPresenter;
import apps.sharabash.bzender.adapters.filterAreaAdapter;
import apps.sharabash.bzender.adapters.filterAreaModelRecycler;
import rx.subscriptions.CompositeSubscription;

public class AddTender extends AppCompatActivity implements homeInterface, View.OnClickListener, AddTinderInterface, DatePickerDialog.OnDateSetListener, FillDataCarInterface {

    private static final String TAG = "tag";
    private final ArrayList<filterAreaModelRecycler> DialogList = new ArrayList<>();
    Button btnCreate;
    CustomerAddress customerAddress;
    List<CarModels> carModelsList = new ArrayList<>();
    List<CarTypes> carTypesList = new ArrayList<>();
    private Date dateStartt, dateEnddd;
    private SharedPreferences mSharedPreferences;
    private RecyclerView DialogRecyclerView;
    private filterAreaAdapter filterAreaAdapter1;
    private String language;
    private String categoryId = "-1";
    private String CityId = "-1";
    private TextView mTxtTinderType;
    private List<getCategoryResponse> getCategoryResponses1;
    private List<AllCitiesModel> getAllCitiesForDialog;
    private String selectedDateStart;
    private String getSelectedDateEnd;
    private int dateId;
    private AddTinderPresenter addTinderPresenter;
    private MyEditText mTinderTitle;
    private MyEditText mLocation;
    private MyTextView mMTxtStartTime;
    private MyTextView mMTxtEndTime;
    // FOR FILL DATA
    private MyEditText mDesc;
    private DialogFragment dialogFragment, dialogFragmentTwo;

    //


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tinder);


        mSharedPreferences = getSharedPreferences("MySharedPreference", MODE_PRIVATE);
        language = mSharedPreferences.getString(Constant.language, Locale.getDefault().getDisplayLanguage());

        initView();

        CompositeSubscription mSubscriptions = new CompositeSubscription();


        MyEditText mEditTExtLocation = findViewById(R.id.location);
        Typeface font = Typeface.createFromAsset(getAssets(), "Aileron-Light.otf");
        mEditTExtLocation.setTypeface(font);

        //btnCreate = findViewById(R.id.create);
        mTxtTinderType = findViewById(R.id.tender_type);
        getCategoryResponses1 = new ArrayList<>();
        HomePresenter homePresenter = new HomePresenter(this, this);
        homePresenter.getCategory();
        addTinderPresenter.getAllCities();
//        btnCreate.setOnClickListener(v -> {
//
//        });
        mTxtTinderType.setOnClickListener(v -> show_dialig());


        // FOR FILL DATA

        FillDataCarPresenter fillDataCarPresenter = new FillDataCarPresenter(this, this);
        fillDataCarPresenter.getMetaData();

    }

    @Override
    public void handleCategoryList(List<getCategoryResponse> getCategoryResponses) {
        getCategoryResponses1 = getCategoryResponses;
    }

    @Override
    public void getAllImages(GetOffers getOffers) {

    }

    private void show_dialig() {
        AlertDialog.Builder builder1;
        final AlertDialog dialog1;
        builder1 = new AlertDialog.Builder(AddTender.this);
        View mview = getLayoutInflater().inflate(R.layout.area_dialog, null);
        TextView header = mview.findViewById(R.id.DialogHeader);
        TextView All = mview.findViewById(R.id.All);
        All.setVisibility(View.GONE);
        header.setText("Select Tender Type");
        if (language.equals("en")) {
            header.setText("Select Tender Type");
        } else if (language.equals("ar")) {
            header.setText("اختر نوع المناقصة");
        }
        DialogRecyclerView = mview.findViewById(R.id.Recycler_Dialog_cities);
        filterAreaAdapter1 = new filterAreaAdapter(DialogList, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), OrientationHelper.VERTICAL, false);

        DialogRecyclerView.setLayoutManager(linearLayoutManager);
        DialogRecyclerView.setItemAnimator(new DefaultItemAnimator());
        initionilizationOFFilter();
        DialogRecyclerView.setAdapter(filterAreaAdapter1);
        builder1.setView(mview);
        dialog1 = builder1.create();
        Window window = dialog1.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog1.show();
        filterAreaAdapter1.setOnItemClickListener(v1 -> {
            int position = DialogRecyclerView.getChildAdapterPosition(v1);
            Log.d(TAG, "selectedTenderType: " + position);

            Constant.SELECTED_TENDER_TYPE = position;

            categoryId = String.valueOf(DialogList.get(position).id);
            mTxtTinderType.setText(DialogList.get(position).CityName);

            dialog1.dismiss();
        });

    }


    private void show_dialigForCities() {
        AlertDialog.Builder builder1;
        final AlertDialog dialog1;
        builder1 = new AlertDialog.Builder(AddTender.this);
        View mview = getLayoutInflater().inflate(R.layout.area_dialog, null);
        TextView header = mview.findViewById(R.id.DialogHeader);
        TextView All = mview.findViewById(R.id.All);
        All.setVisibility(View.GONE);
        if (language.equals("ar")) {
            header.setText("اختر المحافظة");
        } else {
            header.setText("Select City");
        }
        DialogRecyclerView = mview.findViewById(R.id.Recycler_Dialog_cities);
        filterAreaAdapter1 = new filterAreaAdapter(DialogList, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), OrientationHelper.VERTICAL, false);

        DialogRecyclerView.setLayoutManager(linearLayoutManager);
        DialogRecyclerView.setItemAnimator(new DefaultItemAnimator());
        initionilizationOFCities();
        DialogRecyclerView.setAdapter(filterAreaAdapter1);
        builder1.setView(mview);
        dialog1 = builder1.create();
        Window window = dialog1.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog1.show();
        filterAreaAdapter1.setOnItemClickListener(v1 -> {
            int position = DialogRecyclerView.getChildAdapterPosition(v1);
            String position1 = (String.valueOf(position));

            CityId = getAllCitiesForDialog.get(position).getId();
            mLocation.setText(DialogList.get(position).CityName);


            dialog1.dismiss();
        });

    }

    private void initionilizationOFFilter() {
        DialogList.clear();
        List<AddTenderType> addTenderTypeList = new ArrayList<>();
        for (int k = 0; k < 2; k++) {

            if (language.equals("ar")) {
                addTenderTypeList.add(new AddTenderType(10021, "سيارات"));
                addTenderTypeList.add(new AddTenderType(10022, "الكترونيات"));

                DialogList.add(new filterAreaModelRecycler(
                        addTenderTypeList.get(k).getName()
                        , ""
                        , -1
                        , addTenderTypeList.get(k).getId()));
            } else {
                addTenderTypeList.add(new AddTenderType(10021, "Cars"));
                addTenderTypeList.add(new AddTenderType(10022, "Electronics"));

                DialogList.add(new filterAreaModelRecycler(
                        addTenderTypeList.get(k).getName()
                        , ""
                        , -1
                        , addTenderTypeList.get(k).getId()));
            }
        }
        filterAreaAdapter1.update(DialogList);


    }

    private void initionilizationOFCities() {
        DialogList.clear();
        for (int k = 0; k < getAllCitiesForDialog.size(); k++) {
            if (language.equals("ar")) {
                DialogList.add(new filterAreaModelRecycler(
                        getAllCitiesForDialog.get(k).getArabicName()
                        , ""
                        , -1
                        , Integer.parseInt(getAllCitiesForDialog.get(k).getId())));
            } else {
                DialogList.add(new filterAreaModelRecycler(
                        getAllCitiesForDialog.get(k).getEnglishName()
                        , ""
                        , -1
                        , Integer.parseInt(getAllCitiesForDialog.get(k).getId())));
            }
        }
        filterAreaAdapter1.update(DialogList);


    }

    private void initView() {

        addTinderPresenter = new AddTinderPresenter(AddTender.this, this);

        dialogFragment = new DatePickerFragment();
        dialogFragmentTwo = new DatePickerFragment();

        AppCompatImageView mImgBack = findViewById(R.id.imageNavigationIcon);
        mImgBack.setOnClickListener(v -> {
            NavUtils.navigateUpFromSameTask(this);
            Animatoo.animateSlideDown(this);
        });

        mTinderTitle = findViewById(R.id.tinder_title);
        MyTextView mTenderType = findViewById(R.id.tender_type);
        mLocation = findViewById(R.id.location);

        mMTxtStartTime = findViewById(R.id.mTxtStartTime);
        mMTxtEndTime = findViewById(R.id.mTxtEndTime);
        mDesc = findViewById(R.id.desc);
        ButtonBook mCreate = findViewById(R.id.create);

        mCreate.setOnClickListener(this);


        mMTxtStartTime.setClickable(true);
        mMTxtStartTime.setFocusable(false);
        mMTxtStartTime.setFocusableInTouchMode(false);

        mMTxtStartTime.setOnClickListener(this);

        mMTxtEndTime.setClickable(true);
        mMTxtEndTime.setFocusable(false);
        mMTxtEndTime.setFocusableInTouchMode(false);

        mMTxtEndTime.setOnClickListener(this);

        mLocation.setClickable(true);
        mLocation.setFocusable(false);
        mLocation.setFocusableInTouchMode(false);

        mLocation.setOnClickListener(this);

        DatePickerDialog.OnDateSetListener mStartDateListener = (view, year, month, dayOfMonth) -> {

        };

        DatePickerDialog.OnDateSetListener mEndDateListener = (view, year, month, dayOfMonth) -> {

        };
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create:
                Log.d(TAG, "onClick: ");
                Log.d(TAG, "Title: " + mTinderTitle.getText().toString());
                Log.d(TAG, "desc: " + mDesc.getText().toString());
                Log.d(TAG, "startDate: " + selectedDateStart);
                Log.d(TAG, "endTime: " + getSelectedDateEnd);
                Log.d(TAG, "cityId: " + CityId);
                Log.d(TAG, "catyId: " + categoryId);
                Log.d(TAG, "address: " + mLocation.getText().toString());

                addTinderPresenter.validationAddTinder(mTinderTitle.getText().toString(), mDesc.getText().toString(), CityId, selectedDateStart
                        , getSelectedDateEnd, categoryId, mLocation.getText().toString());
                //showDialogCreate();
                break;
            case R.id.location:
                //addTinderPresenter.getAllCities();
                show_dialigForCities();
                break;
            case R.id.mTxtStartTime:
                dateId = 1;
                if (!dialogFragment.isAdded())
                    dialogFragment.show(getSupportFragmentManager(), "date picker");
                else
                    return;
                break;
            case R.id.mTxtEndTime:
                dateId = 2;
                if (!dialogFragmentTwo.isAdded())
                    if (mMTxtStartTime.getText().length() == 0) {
                        Constant.showErrorDialog(this, getString(R.string.pls_choose_start));
                    } else {
                        dialogFragmentTwo.show(getSupportFragmentManager(), "date picker");
                    }
                else
                    return;
                break;
        }
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date choosenDate = c.getTime();

        String currentDateString = sdf.format(c.getTime());

        int y = Calendar.getInstance().get(Calendar.YEAR);
        int d = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int m = Calendar.getInstance().get(Calendar.MONTH);

        Date currentDate = Calendar.getInstance().getTime();


        if (currentDate.getTime() >= choosenDate.getTime()) {
            Constant.showErrorDialog(this, getString(R.string.error_date_one));
            return;
        }
        long oneDay = 86400000;
        if (currentDate.getTime() + oneDay >= choosenDate.getTime()) {
            Constant.showErrorDialog(this, getString(R.string.error_date_one));
            return;
        }
        if (year != y) {
            Constant.showErrorDialog(this, getString(R.string.error_date_year));
            return;
        }
        if (month >= m + 4) {
            Constant.showErrorDialog(this, getString(R.string.error_month));
            return;
        }
        {

            String date = sdf.format(c.getTime());

            try {
                Date endDate = null, startDate = null;
                if (dateId == 1) {
                    startDate = sdf.parse(date);
                    dateStartt = startDate;

                    mMTxtStartTime.setText(currentDateString);

                    selectedDateStart = date;
                } else {
                    endDate = sdf.parse(date);
                    dateEnddd = endDate;
                }

                Log.d(TAG, "onDateSet: " + dateStartt + "///" + dateEnddd);

                if (dateEnddd != null && dateStartt != null) {

                    if (mMTxtEndTime.getText().length() > 0 && mMTxtStartTime.getText().length() == 0) {
                        Constant.showErrorDialog(this, getString(R.string.pls_choose_start));
                        mMTxtEndTime.setText(" ");
                        return;
                    }

                    if (dateEnddd.getTime() <= dateStartt.getTime()) {

                        Constant.showErrorDialog(this, getString(R.string.error_end_date_one));
                        return;
                    }
                    long fiveDays = 432000000;
                    if (dateStartt.getTime() + fiveDays > dateEnddd.getTime()) {
                        Constant.showErrorDialog(this, getString(R.string.error_end_date_two));
                        return;
                    }

                    long tenDays = 864000000;
                    if (dateEnddd.getTime() - tenDays > dateStartt.getTime()) {
                        Constant.showErrorDialog(this, getString(R.string.error_end_date_three));
                        return;
                    }

                }
                if (dateId != 1) {

                    mMTxtEndTime.setText(currentDateString);
                    getSelectedDateEnd = date;
                }


//                } else {
//                    Constant.showErrorDialog(this, getString(R.string.pls_choose_start));
//                }

            } catch (ParseException e) {
                Log.d(TAG, "onDateSet: " + e.getMessage());

                e.printStackTrace();
            }

        }


    }


    @Override
    public void getAllCities(List<AllCitiesModel> getAllCities) {
        Log.d(TAG, "getAllCities: " + getAllCities.get(0).toString());

        getAllCitiesForDialog = getAllCities;
    }

    @Override
    public void getMetaCar(MetaDataCar metaDataCar) {

        mSharedPreferences = getSharedPreferences("tokenDetail", MODE_PRIVATE);

        for (int i = 0; i < metaDataCar.getCarTypes().size(); i++) {
            if (language.equals("ar")) {
                Constant.wheelDataCarType.add(metaDataCar.getCarTypes().get(i));
            } else {
                Constant.wheelDataCarType.add(metaDataCar.getCarTypes().get(i));
            }

            Log.d(TAG, "TYPE__: " + Constant.wheelDataCarType.get(i));
        }

        for (int i = 0; i < metaDataCar.getCarModels().size(); i++) {
            if (language.equals("ar")) {
                Constant.wheelDataCarModel.add(metaDataCar.getCarModels().get(i));
            } else {
                Constant.wheelDataCarModel.add(metaDataCar.getCarModels().get(i));
            }
            Log.d(TAG, "MODEL__: " + Constant.wheelDataCarModel.get(i));
        }


    }
}

//tenant_developer@outlook.com