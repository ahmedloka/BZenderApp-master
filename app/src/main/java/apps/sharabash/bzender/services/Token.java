package apps.sharabash.bzender.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import apps.sharabash.bzender.Utills.Constant;

import static android.content.Context.MODE_PRIVATE;

public class Token {
    private static final String TAG = "TOKEN";
    private SharedPreferences mSharedPreferences;

    public void getToken(Context context) {

        mSharedPreferences = context.getSharedPreferences("MySharedPreference", MODE_PRIVATE);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "getInstanceId failed", task.getException());
                        return;
                    }

                    // Get new Instance ID token
                    String token = task.getResult().getToken();


                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putString(Constant.devicetoken, token);
                    Log.d(TAG, "getToken: " + token);
                    Log.d(TAG, "getTokenShared: " + mSharedPreferences.getString(Constant.devicetoken, ""));
                    editor.apply();
                });

    }
}
