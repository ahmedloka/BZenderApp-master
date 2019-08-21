package apps.sharabash.bzender.activities;

import android.content.Context;
import android.content.SharedPreferences;

import rx.subscriptions.CompositeSubscription;

class SplashPresenter {

    private static final String TAG = "SplashPresenter";

    SplashPresenter(Context mContext) {
        Context mContext1 = mContext;


        CompositeSubscription mSubscriptions = new CompositeSubscription();

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
    }

}
