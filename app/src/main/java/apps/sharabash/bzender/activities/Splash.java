package apps.sharabash.bzender.activities;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.crashlytics.android.Crashlytics;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import apps.sharabash.bzender.Models.GetOffers;
import apps.sharabash.bzender.Models.home.getCategoryResponse;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.NetworkChangeReceiver;
import apps.sharabash.bzender.activities.Home.homeInterface;
import io.fabric.sdk.android.Fabric;

public class Splash extends AppCompatActivity implements homeInterface {


    public static final String TAG = "SPLASH";
    public static ImageView gifImageView;
    private static ImageView splashBG;
    private BroadcastReceiver mNetworkReceiver;

    private String language ;


    public static void showLoader(boolean value) {

        if (value) {

            Handler handler = new Handler();
            Runnable delayRunnable = () -> gifImageView.setVisibility(View.VISIBLE);
            handler.postDelayed(delayRunnable, 3000);

        } else {
            gifImageView.setVisibility(View.VISIBLE);

            //gifImageView.setImageResource(R.drawable.loadder_giff);


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash);
        Constant.hideStatusBar(this);

        SharedPreferences mSharedPreferences = getSharedPreferences("MySharedPreference", MODE_PRIVATE);

        Constant.changeLang(this, Objects.requireNonNull(mSharedPreferences.getString(Constant.language, Locale.getDefault().getDisplayLanguage())));


        mNetworkReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcast();

        SplashPresenter splashPresenter = new SplashPresenter(this);

        gifImageView = findViewById(R.id.gif_loader);
        splashBG = findViewById(R.id.spalsh_bg);

        setGifImages();


    }

    private void setGifImages() {
        Glide.with(this).asGif()
                .apply(new RequestOptions()
                        .override(200, 600))
                .listener(new RequestListener<GifDrawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {

                        if (resource instanceof GifDrawable) {
                            resource.setLoopCount(Animation.INFINITE);
                        }
                        return false;
                    }
                }).load(R.drawable.loadder_giff).into(gifImageView);


        Glide.with(this).asGif()
                .apply(new RequestOptions()
                        .fitCenter())
                .listener(new RequestListener<GifDrawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {

                        if (resource instanceof GifDrawable) {
                            resource.setLoopCount(0);
                        }
                        return false;
                    }
                })
                .load(R.drawable.splash_gif).into(splashBG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterNetworkChanges();

    }

    private void registerNetworkBroadcast() {
        registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }

    private void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void handleCategoryList(List<getCategoryResponse> getCategoryResponses) {

    }

    @Override
    public void getAllImages(GetOffers getOffers) {

    }
}
