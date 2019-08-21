package apps.sharabash.bzender.activities.forget_password;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.ButtonBook;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.MyEditText;

public class ForgetPassword extends AppCompatActivity implements View.OnClickListener {

    private ForgetPasswordPresenter forgetPasswordPresenter;

    private MyEditText mETMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);


        forgetPasswordPresenter = new ForgetPasswordPresenter(this);

        mETMail = findViewById(R.id.email_address);

        TextView back = findViewById(R.id.back);

        back.setOnClickListener(v -> {
            NavUtils.navigateUpFromSameTask(this);
        });

        ButtonBook mtnBook = findViewById(R.id.reset_password);
        mtnBook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.reset_password) {
            if (!Objects.requireNonNull(mETMail.getText()).toString().isEmpty()) {
                if (!Patterns.EMAIL_ADDRESS.matcher(mETMail.getText().toString()).matches()) {
                    Constant.getErrorDependingOnResponse(this, "21");
                } else {
                    forgetPasswordPresenter.forgetPassword(mETMail.getText().toString());
                }
            }
        }
    }
}
