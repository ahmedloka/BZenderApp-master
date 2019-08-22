package apps.sharabash.bzender.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.net.URLEncoder;

import apps.sharabash.bzender.Models.metadataCar.MetaDataCar;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.MyEditText;
import apps.sharabash.bzender.Utills.MyTextViewBold;
import apps.sharabash.bzender.activities.Complaint.ComplaintPresenter;
import apps.sharabash.bzender.activities.fillDataCar.FillDataCarInterface;
import apps.sharabash.bzender.activities.fillDataCar.FillDataCarPresenter;
import apps.sharabash.bzender.activities.webViewContactUs.WebViewActivity;

public class ContactUs extends AppCompatActivity implements View.OnClickListener, FillDataCarInterface {

    private static final String TAG = "webViewActivity";

    private MyEditText mEditTextMsg;

    private ComplaintPresenter complaintPresenter;

    private String whatsAppNumber;

    private MyTextViewBold txtAddress;
    private MyTextViewBold txtPhoneNumber;
    private MyTextViewBold txtEmail;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateInAndOut(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        initViews();
    }

    private void initViews() {

        AppCompatImageView mImgBack = findViewById(R.id.imageNavigationIcon);
        mImgBack.setOnClickListener(v -> {
            NavUtils.navigateUpFromSameTask(this);
            Animatoo.animateInAndOut(this);
        });


        FillDataCarPresenter presenter = new FillDataCarPresenter(this, this);
        presenter.getMetaData();


        txtAddress = findViewById(R.id.txt_address);
        txtPhoneNumber = findViewById(R.id.txt_phone_number);
        txtEmail = findViewById(R.id.txt_email);

        AppCompatImageView fb = findViewById(R.id.fb);
        AppCompatImageView whatsApp = findViewById(R.id.what_app);
        AppCompatImageView twitter = findViewById(R.id.twitter);
        AppCompatImageView instgram = findViewById(R.id.instgram);

        fb.setOnClickListener(this);
        whatsApp.setOnClickListener(this);
        twitter.setOnClickListener(this);
        instgram.setOnClickListener(this);


        complaintPresenter = new ComplaintPresenter(this);

        mEditTextMsg = findViewById(R.id.message);

        mEditTextMsg = findViewById(R.id.message);
        Button mBtnSend = findViewById(R.id.send);


        mBtnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, WebViewActivity.class);
        String url;
        switch (v.getId()) {
            case R.id.fb:
                url = Constant.FACE_BOOK_URL;
                intent.putExtra(Constant.URL_KEY, url);

                startActivity(intent);
                Animatoo.animateSlideRight(this);
                break;
            case R.id.instgram:
                url = Constant.INSTGRAM_URL;
                intent.putExtra(Constant.URL_KEY, url);

                startActivity(intent);
                Animatoo.animateSlideRight(this);
                break;
            case R.id.twitter:
                url = Constant.TWITTER_URL;
                intent.putExtra(Constant.URL_KEY, url);

                startActivity(intent);
                Animatoo.animateSlideRight(this);
                break;
            case R.id.what_app:

                openWhatsAppConversationUsingUri(whatsAppNumber);
                //startActivity(intent);


                break;

            case R.id.send:
                if (mEditTextMsg.getText().length() > 0 || mEditTextMsg.getText() != null) {
                    complaintPresenter.validationSendComplaint(mEditTextMsg.getText().toString());
                } else {
                    return;
                }
                mEditTextMsg.getText().clear();
                break;
        }


    }

    private void openWhatsAppConversationUsingUri(String numberWithCountryCode) {

        boolean installed = appInstalledOrNot();
        if (installed) {
            PackageManager packageManager = getPackageManager();
            Intent i = new Intent(Intent.ACTION_VIEW);

            try {
                String url = "https://api.whatsapp.com/send?phone=" + numberWithCountryCode + "&text=" + URLEncoder.encode("Hi !", "UTF-8");
                i.setPackage("com.whatsapp");
                i.setData(Uri.parse(url));
                if (i.resolveActivity(packageManager) != null) {
                    startActivity(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG, "openWhatsAppConversationUsingUri: " + "errrrror");
            }
        } else {
            Constant.showErrorDialog(this, getString(R.string.please_downlaod_whats_app));
        }

    }

    private boolean appInstalledOrNot() {
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    @Override
    public void getMetaCar(MetaDataCar metaDataCar) {
        String address = metaDataCar.getContactUs().get(0).getAddress();
        String phone = metaDataCar.getContactUs().get(0).getPhone();
        String instgramUrl = metaDataCar.getContactUs().get(0).getInstagram();
        String fbUrl = metaDataCar.getContactUs().get(0).getFaceBookUrl();
        String twitterUrl = metaDataCar.getContactUs().get(0).getTwitterUrl();
        whatsAppNumber = 2 + metaDataCar.getContactUs().get(0).getWhatsApp().trim();
        String email = metaDataCar.getContactUs().get(0).getEmail();

        txtAddress.setText(address);
        txtPhoneNumber.setText(phone);
        txtEmail.setText(email);

        Constant.FACE_BOOK_URL = fbUrl;
        Constant.TWITTER_URL = twitterUrl;
        Constant.WHATS_APP_NUMBER = whatsAppNumber;
        Log.d(TAG, "getMetaCar: " + whatsAppNumber);
        Constant.INSTGRAM_URL = instgramUrl;


    }
}
