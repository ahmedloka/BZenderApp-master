package apps.sharabash.bzender.activities.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import apps.sharabash.bzender.Models.profile.ProfileModel;
import apps.sharabash.bzender.Network.NetworkUtil;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.Validation;
import apps.sharabash.bzender.dialog.DialogLoader;
import retrofit2.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ProfilePresenter {

    private static final String TAG = "addTinder";
    private final Context mContext;
    private final CompositeSubscription mSubscriptions;

    private final SharedPreferences sharedPreferences;

    private final ProfileInterface profileInterface;

    private final DialogLoader dialogLoader;

    public ProfilePresenter(Context mContext, ProfileInterface profileInterface) {
        this.mContext = mContext;

        dialogLoader = new DialogLoader();
        this.profileInterface = profileInterface;

        mSubscriptions = new CompositeSubscription();

        sharedPreferences = mContext.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);

    }


    public void getProfileData() {
        if (Validation.isConnected(mContext)) {
            dialogLoader.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "");
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString(Constant.UserID, ""))
                    .getProfileData()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            Toast.makeText(mContext, "error happend", Toast.LENGTH_SHORT).show();
        }

    }

    private void handleError(Throwable throwable) {
        String message = "";
        if (throwable instanceof retrofit2.HttpException) {
            try {
                retrofit2.HttpException error = (retrofit2.HttpException) throwable;
                JSONObject jsonObject = new JSONObject(((HttpException) throwable).response().errorBody().string());
                message = jsonObject.getString("Message");
            } catch (Exception e) {
                message = throwable.getMessage();
            }
            Constant.getErrorDependingOnResponse(mContext, message);

        }
        // hud.dismiss();
    }

    private void handleResponse(ProfileModel profileModel) {
        dialogLoader.dismiss();
        Log.d(TAG, "handleResponseProfileData: " + profileModel.toString());
        profileInterface.getProfile(profileModel);

        SharedPreferences.Editor edit = sharedPreferences.edit();
        try {
            edit.putString(Constant.IMAGE_URL, "http://pixelserver-001-site29.ctempurl.com/Content/Images/" + profileModel.getImgUrl().trim());
            edit.apply();
        } catch (NullPointerException ignored) {
            edit.putString(Constant.IMAGE_URL, "");
            edit.apply();
        }


    }

}
