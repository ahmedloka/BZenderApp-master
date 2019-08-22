package apps.sharabash.bzender.activities.Home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import apps.sharabash.bzender.Models.GetOffers;
import apps.sharabash.bzender.Models.home.getCategoryResponse;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.MyTextViewBold;
import apps.sharabash.bzender.activities.AboutUs;
import apps.sharabash.bzender.activities.AddTender.AddTender;
import apps.sharabash.bzender.activities.ChooseHowItWork;
import apps.sharabash.bzender.activities.ContactUs;
import apps.sharabash.bzender.activities.Setting;
import apps.sharabash.bzender.activities.chatAllUsers.ChatListActivity;
import apps.sharabash.bzender.activities.login.Login;
import apps.sharabash.bzender.activities.myBooking.MyBooking;
import apps.sharabash.bzender.activities.myTender.MyTendersActivity;
import apps.sharabash.bzender.activities.notification.Notifications;
import apps.sharabash.bzender.activities.profile.Profile;
import apps.sharabash.bzender.adapters.HomeAdapter;
import apps.sharabash.bzender.adapters.SlidingImage_Adapter;
import apps.sharabash.bzender.services.Token;
import de.hdodenhof.circleimageview.CircleImageView;
import libs.mjn.prettydialog.PrettyDialog;

public class Home extends AppCompatActivity implements homeInterface {

    @SuppressLint("StaticFieldLeak")
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private final List<String> nameList = new ArrayList<>();
    private final List<String> imagesUrl = new ArrayList<>();
    private DrawerLayout drawer;
    private AppCompatImageView imageNavigationIcon;
    private Button add_tinder;
    private LinearLayoutCompat myBooking;
    private LinearLayoutCompat myTender;
    private LinearLayoutCompat notification;
    private LinearLayoutCompat payment;
    private LinearLayoutCompat about_us;
    private LinearLayoutCompat call_us;
    private LinearLayoutCompat settings;
    private LinearLayoutCompat profile;
    private LinearLayoutCompat how_it_work;
    private LinearLayoutCompat log_out;
    private SharedPreferences mSharedPreferences;
    private LinearLayoutCompat chat;
    //KProgressHUD hud;
    private RecyclerView nearByRecyclerView;
    private MyTextViewBold mTxtUserName;
    private String language;
    private CircleImageView mProfileIg;
    private HomePresenter homePresenter;
    private HomeAdapter homeAdapter;


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_home);

        initViews();
    }

    void mainCode() {

        language = mSharedPreferences.getString(Constant.language, Locale.getDefault().getDisplayLanguage());
        Constant.changeLang(this, Objects.requireNonNull(mSharedPreferences.getString(Constant.language, Locale.getDefault().getDisplayLanguage())));


        if (language.equals("ar")) {
            nameList.add("سيارات");
            nameList.add("الكترونيات");
        } else {
            nameList.add("Cars");
            nameList.add("Electronics");
        }


        nearByRecyclerView.setLayoutManager(new LinearLayoutManager(Home.this, LinearLayoutManager.VERTICAL, false));
        nearByRecyclerView.setAdapter(homeAdapter);
        Constant.runLayoutAnimation(nearByRecyclerView);


        homePresenter.getAllImages();

        Token token = new Token();
        token.getToken(this);

        homePresenter.sendPush(mSharedPreferences.getString(Constant.devicetoken, ""), Constant.ANDROID_OS);

        Log.d("APP_TOKEN", mSharedPreferences.getString(Constant.UserID, ""));


        itemsNavigationsHandling();
    }

    private void itemsNavigationsHandling() {
        imageNavigationIcon.setOnClickListener(view1 -> {
            if (drawer.isDrawerOpen(Gravity.START)) {
                drawer.closeDrawer(Gravity.START);
            } else {
                drawer.openDrawer(Gravity.START);
            }
        });

        add_tinder.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AddTender.class);
            startActivity(intent);
            Animatoo.animateSlideUp(this);
            //finish();
        });

        how_it_work.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ChooseHowItWork.class);
            startActivity(intent);
            Animatoo.animateFade(this);
            //finish();
        });
        log_out.setOnClickListener(v -> {
            SharedPreferences.Editor edit = mSharedPreferences.edit();

            edit.putString(Constant.UserID, "");
            edit.putString(Constant.Username, "");
            edit.putString(Constant.Useremail, "");
            edit.putString(Constant.PASSWORD, "");
            edit.putString(Constant.IMAGE_URL, "");
            edit.putString(Constant.USER_ID_CHAT, "");
            edit.apply();

            Constant.NAME = "";
            Constant.OLD_BASE64 = "";

            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            finish();
            Animatoo.animateSplit(this);


        });
        mTxtUserName.setText(mSharedPreferences.getString(Constant.Username, Constant.NAME));


        Glide.with(this)
                .load(Uri.parse(mSharedPreferences.getString(Constant.IMAGE_URL, "")))
                .apply(new RequestOptions().centerCrop())
                .placeholder(R.drawable.edit_img)
                .into(mProfileIg);


        payment.setOnClickListener(v -> {
            PrettyDialog prettyDialog = new PrettyDialog(this);

            prettyDialog.setCancelable(true);
            prettyDialog
                    .setIcon(R.drawable.ic_payment)
                    .setTitle(getString(R.string.waiting_for_develop_payment_system))
                    .addButton(getString(R.string.ok), android.R.color.white, R.color.app_coloor, prettyDialog::dismiss)
                    .show();
        });

        about_us.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AboutUs.class);
            startActivity(intent);
            Animatoo.animateFade(this);
            //finish();
        });
        call_us.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ContactUs.class);
            startActivity(intent);
            Animatoo.animateFade(this);
            //finish();
        });
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Profile.class);
            startActivity(intent);
            Animatoo.animateFade(this);
            //finish();
        });
        settings.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Setting.class);
            startActivity(intent);
            Animatoo.animateFade(this);
            //finish();
        });

        chat.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ChatListActivity.class);
            startActivity(intent);
            Animatoo.animateSlideDown(this);
            //finish();
        });

        notification.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Notifications.class);
            startActivity(intent);
            Animatoo.animateFade(this);
            //finish();
        });


        myBooking.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MyBooking.class);
            startActivity(intent);
            Animatoo.animateFade(this);
        });

        myTender.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MyTendersActivity.class);
            startActivity(intent);
            Animatoo.animateFade(this);
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        mainCode();

    }


    private void initViews() {

        mSharedPreferences = getSharedPreferences("MySharedPreference", MODE_PRIVATE);

        mPager = findViewById(R.id.pager);

        homeAdapter = new HomeAdapter(Home.this, nameList);


        drawer = findViewById(R.id.drawer_layout);
        imageNavigationIcon = findViewById(R.id.imageNavigationIcon);
        add_tinder = findViewById(R.id.add_tinder);
        myBooking = findViewById(R.id.my_booking);
        myTender = findViewById(R.id.my_tender);
        notification = findViewById(R.id.notification);
        about_us = findViewById(R.id.about_us);
        payment = findViewById(R.id.payment);
        call_us = findViewById(R.id.call_us);
        settings = findViewById(R.id.settings);
        chat = findViewById(R.id.chat);
        profile = findViewById(R.id.profile);
        mTxtUserName = findViewById(R.id.text_user_name_home);
        how_it_work = findViewById(R.id.how_it_work);
        log_out = findViewById(R.id.log_out);
        nearByRecyclerView = findViewById(R.id.recycler);
        mProfileIg = findViewById(R.id.profile_img);

        homePresenter = new HomePresenter(this, this);


    }

    @Override
    public void handleCategoryList(List<getCategoryResponse> getCategoryResponse) {


    }

    @Override
    public void getAllImages(GetOffers getOffers) {
        for (int i = 0; i < getOffers.getOffers().size(); i++) {
            imagesUrl.add("http://pixelserver-001-site29.ctempurl.com/Content/OfferImages/" + getOffers.getOffers().get(i).getImage().trim());
            //http://pixelserver-001-site29.ctempurl.com//Content//OfferImages/B8401f-1268-43fe-8c38-1ee2e31c094e.jpg
        }

        for (int i = 0; i < imagesUrl.size(); i++) {
            Log.d("URL", "getAllImages: " + imagesUrl.get(i) + "\n");

        }

        mPager.setAdapter(new SlidingImage_Adapter(this, imagesUrl));

        CirclePageIndicator indicator = findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = imagesUrl.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }


}
