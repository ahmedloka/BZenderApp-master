package apps.sharabash.bzender.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;
import java.util.Objects;

import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.activities.Home.Home;
import apps.sharabash.bzender.activities.changePassword.ChangePassword;

public class Setting extends AppCompatActivity {

    private Dialog dialog;

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        AppCompatImageView mImgBack = findViewById(R.id.imageNavigationIcon);
        mImgBack.setOnClickListener(v -> {
            finish();

            Intent intent = new Intent(getApplicationContext(), Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        sharedPreferences = getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        // complaint = findViewById(R.id.complaint);
        TextView change_password = findViewById(R.id.change_password);
        TextView change_lang = findViewById(R.id.change_lang);

//        complaint.setOnClickListener(v -> {
//            Intent intent = new Intent(Setting.this, Complaint.class);
//            startActivity(intent);
//
//        });

        change_password.setOnClickListener(v -> {
            Intent intent = new Intent(Setting.this, ChangePassword.class);
            startActivity(intent);

        });

        LinearLayout mTermsAndConditions = findViewById(R.id.terms_and_conditions);
        mTermsAndConditions.setOnClickListener(v -> {
            Intent intent = new Intent(Setting.this, TermsAndConditionActivity.class);
            startActivity(intent);
        });


        change_lang.setOnClickListener(v -> {
            Rect displayRectangle = new Rect();
            Window window = Objects.requireNonNull(getWindow());
            window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
            //  builder                        = new AlertDialog.Builder(getContext());
            @SuppressLint("InflateParams")
            View mview = getLayoutInflater().inflate(R.layout.dialog_change_lang, null);
            dialog = new BottomSheetDialog(Setting.this);
            dialog.setContentView(mview);
            dialog.show();
            AppCompatTextView arabic = dialog.findViewById(R.id.arabic);
            AppCompatTextView english = dialog.findViewById(R.id.english);
            Button cancel = dialog.findViewById(R.id.cancel);
            arabic.setOnClickListener(v1 -> {

                Log.d("lang", "onCreate: " + "you click me");
                editor.putString(Constant.language, "ar");
                editor.apply();

                Constant.changeLang(this, Objects.requireNonNull(sharedPreferences.getString(Constant.language, Locale.getDefault().getDisplayLanguage())));


                dialog.dismiss();

                Intent intent = new Intent(getApplicationContext(), Setting.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);



                /*if (Language.equals("ar"))
                {
                    showLoader.cancel();
                }
                else {
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putString(Constant.language, "ar");
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), Settings.class);
                    startActivity(intent);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                    finish();
                }*/
            });
            english.setOnClickListener(v12 -> {

                Log.d("lang", "onCreate: " + "you click me");

                editor.putString(Constant.language, "en");
                editor.apply();

                Constant.changeLang(this, Objects.requireNonNull(sharedPreferences.getString(Constant.language, Locale.getDefault().getDisplayLanguage())));

                dialog.dismiss();

                Intent intent = new Intent(getApplicationContext(), Setting.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);


                /*if (Language.equals("en"))
                {
                    showLoader.cancel();
                }
                else
                {
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putString(Constant.language, "en");
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(),Settings.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                    finish();
                }*/
            });
            cancel.setOnClickListener(view -> dialog.cancel());
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();

        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

//    private void changeLang(String countryCode) {
//        Resources res = getResources();
//        DisplayMetrics dm = res.getDisplayMetrics();
//        android.content.res.Configuration conf = res.getConfiguration();
//        conf.setLocale(new Locale(countryCode.toLowerCase()));
//        res.updateConfiguration(conf, dm);
//    }


}
