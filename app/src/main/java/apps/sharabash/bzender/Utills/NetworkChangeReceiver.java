package apps.sharabash.bzender.Utills;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.animation.Animation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import apps.sharabash.bzender.R;
import apps.sharabash.bzender.activities.WelcomeActivity;

import static apps.sharabash.bzender.activities.Splash.gifImageView;
import static apps.sharabash.bzender.activities.Splash.showLoader;

public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if (Validation.isConnected(context)) {
                showLoader(true);
                Log.d("CHECK", "Online Connect Intenet ");


                new Handler().postDelayed(() -> {

                    Intent startIntent = new Intent(context, WelcomeActivity.class);
                    context.startActivity(startIntent);
                    ((Activity) context).overridePendingTransition(R.anim.pull_in_left, R.anim.pull_in_right);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    ((Activity) context).finish();

                }, 5000);


            } else {
                showLoader(false);


                infinityGif(context);

                Log.d("CHECK", "Conectivity Failure !!! ");

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            Log.d("NULL", "onReceive: " + e.getMessage());
        }
    }

    private void infinityGif(Context context) {
        Glide.with(context).asGif()
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
    }

}