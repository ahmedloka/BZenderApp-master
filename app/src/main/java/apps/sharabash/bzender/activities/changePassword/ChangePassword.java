package apps.sharabash.bzender.activities.changePassword;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.ButtonBold;
import apps.sharabash.bzender.Utills.MyEditText;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener {

    private MyEditText old;
    private MyEditText newPass;
    private MyEditText confirmPassword;

    private ChangePasswordPresenter changePasswordPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initViews();
    }

    private void initViews() {


        AppCompatImageView mImgBack = findViewById(R.id.imageNavigationIcon);
        mImgBack.setOnClickListener(v -> {
            NavUtils.navigateUpFromSameTask(this);
        });

        changePasswordPresenter = new ChangePasswordPresenter(this);

        old = findViewById(R.id.old);
        newPass = findViewById(R.id.new_pass);
        confirmPassword = findViewById(R.id.confirm_password);
        ButtonBold submit = findViewById(R.id.submit);

        submit.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submit) {
            changePasswordPresenter.validationChangePassword(old.getText().toString(),
                    newPass.getText().toString(), confirmPassword.getText().toString());
        }
    }
}