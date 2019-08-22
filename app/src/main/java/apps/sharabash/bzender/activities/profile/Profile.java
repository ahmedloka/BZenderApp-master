package apps.sharabash.bzender.activities.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import apps.sharabash.bzender.Models.profile.ProfileModel;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.MyTextViewBold;
import apps.sharabash.bzender.activities.editProfile.EditProfile;
import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity implements ProfileInterface, View.OnClickListener {


    private MyTextViewBold txtUserName;
    private MyTextViewBold txtTenderCount;
    private MyTextViewBold txtReversedTenderCount;
    private MyTextViewBold txtPoints;
    private MyTextViewBold txtPhoneNumber;
    private MyTextViewBold txtCityName;
    private MyTextViewBold etEmail;


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Animatoo.animateInAndOut(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();

    }

    private void initViews() {

        SharedPreferences mSharedPreferences = getSharedPreferences("MySharedPreference", MODE_PRIVATE);


        AppCompatImageView mImgBack = findViewById(R.id.imageNavigationIcon);
        mImgBack.setOnClickListener(v -> {
            NavUtils.navigateUpFromSameTask(this);
            Animatoo.animateInAndOut(this);
        });

        ProfilePresenter profilePresenter = new ProfilePresenter(this, this);
        profilePresenter.getProfileData();

        CircleImageView mProfilePic = findViewById(R.id.img_profile);
        txtUserName = findViewById(R.id.txt_user_name);
        AppCompatImageView editProfile = findViewById(R.id.edit_profile);
        txtTenderCount = findViewById(R.id.txt_tender_count);
        txtReversedTenderCount = findViewById(R.id.txt_reversed_tender_count);
        txtPoints = findViewById(R.id.txt_points);
        txtPhoneNumber = findViewById(R.id.txt_phone_number);
        txtCityName = findViewById(R.id.txt_city_name);
        etEmail = findViewById(R.id.et_email);

        editProfile.setOnClickListener(this);

        Glide.with(this)
                .load(Uri.parse(mSharedPreferences.getString(Constant.IMAGE_URL, "")))
                .apply(new RequestOptions().centerCrop())
                .placeholder(R.drawable.edit_img)
                .into(mProfilePic);


    }


    @Override
    public void getProfile(ProfileModel profileModel) {
        txtUserName.setText(profileModel.getUserName());
        txtTenderCount.setText(profileModel.getMytenderCount());
        txtReversedTenderCount.setText(profileModel.getMyReservedTenderCount());
        txtPoints.setText(profileModel.getPoints());
        txtPhoneNumber.setText(profileModel.getPhoneNumber());
        txtCityName.setText(profileModel.getCityName());
        etEmail.setText(profileModel.getEmail());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.edit_profile) {
            Intent intent = new Intent(Profile.this, EditProfile.class);
            startActivity(intent);
            Animatoo.animateSlideRight(this);
        }
    }
}
