package apps.sharabash.bzender.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;

import org.jetbrains.annotations.NotNull;

import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.activities.Home.Home;
import apps.sharabash.bzender.activities.login.Login;
import apps.sharabash.bzender.holder.PrefManager;

public class WelcomeActivity extends AppCompatActivity {

    private ImageView imageView;
    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private int[] layouts;
    private Button btnSkip, btnNext;
    //  viewpager change listener
    private final ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            /*PlayGifView pGif = findViewById(R.id.viewGif);
            pGif.setImageResource(R.drawable.slide_1);

            Glide.with(getActivity()).load(R.raw.alarm).asGif().into(btnAlert);*/
            //Toast.makeText(WelcomeActivity.this, position+"", Toast.LENGTH_SHORT).show();
            if (position == 0) {
                DrawableImageViewTarget imageViewTarget = new DrawableImageViewTarget(imageView);
                Glide.with(WelcomeActivity.this).load(R.drawable.slide_1).into(imageViewTarget);
            } else if (position == 1) {
                DrawableImageViewTarget imageViewTarget = new DrawableImageViewTarget(imageView);
                Glide.with(WelcomeActivity.this).load(R.drawable.slide_2).into(imageViewTarget);
            } else if (position == 2) {
                DrawableImageViewTarget imageViewTarget = new DrawableImageViewTarget(imageView);
                Glide.with(WelcomeActivity.this).load(R.drawable.slide_3).into(imageViewTarget);
            } else if (position == 3) {
                DrawableImageViewTarget imageViewTarget = new DrawableImageViewTarget(imageView);
                Glide.with(WelcomeActivity.this).load(R.drawable.slide_4).into(imageViewTarget);
            }


            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_welcome);

        viewPager = findViewById(R.id.view_pager);
        dotsLayout = findViewById(R.id.layoutDots);
        btnSkip = findViewById(R.id.btn_skip);
        btnNext = findViewById(R.id.btn_next);


        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.welcome_side1,
                R.layout.welcome_side2,
                R.layout.welcome_side3,
                R.layout.welcome_side4};

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(v -> launchHomeScreen());

        btnNext.setOnClickListener(v -> {
            // checking for last page
            // if last page home screen will be launched
            int current = getItem();
            if (current < layouts.length) {
                // move to next screen
                viewPager.setCurrentItem(current);
            } else {
                launchHomeScreen();
            }
        });


    }

    private void addBottomDots(int currentPage) {
        TextView[] dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem() {
        return viewPager.getCurrentItem() + 1;
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);

        SharedPreferences mSharedPreferences = getSharedPreferences("MySharedPreference", MODE_PRIVATE);
        String token = mSharedPreferences.getString(Constant.UserID, "");
       // Toast.makeText(this, token, Toast.LENGTH_SHORT).show();

        if (token != null) {
            if (!token.equals("")) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pull_in_left, R.anim.pull_in_right);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
            } else {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pull_in_left, R.anim.pull_in_right);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
            }
        }
//        startActivity(new Intent(WelcomeActivity.this, Home.class));
//        finish();
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View pager adapter
     */
    class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        MyViewPagerAdapter() {
        }

        @NotNull
        @Override
        public Object instantiateItem(@NotNull ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);

            imageView = view.findViewById(R.id.img);
            if (position == 0) {
                DrawableImageViewTarget imageViewTarget = new DrawableImageViewTarget(imageView);
                Glide.with(WelcomeActivity.this).load(R.drawable.slide_1).into(imageViewTarget);
            }


            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NotNull View view, @NotNull Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}