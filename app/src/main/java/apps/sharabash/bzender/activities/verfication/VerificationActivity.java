package apps.sharabash.bzender.activities.verfication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import apps.sharabash.bzender.R;

public class VerificationActivity extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;

    private String numberOne, numberTwo, numberThree, numberFour;

    private VerficationPresenter verficationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);

        verficationPresenter = new VerficationPresenter(this);

        editText1 = findViewById(R.id.edit_txt1);
        editText2 = findViewById(R.id.edit_txt2);
        editText3 = findViewById(R.id.edit_txt3);
        editText4 = findViewById(R.id.edit_txt4);

        Button submit = findViewById(R.id.submit);

        editText1.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText1.length() == 1) {

                    editText1.clearFocus();
                    editText2.requestFocus();
                    editText2.setCursorVisible(true);

                    numberOne = s.toString();

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {


            }

            public void afterTextChanged(Editable s) {

            }
        });
        editText2.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText2.length() == 1) {

                    editText2.clearFocus();
                    editText3.requestFocus();
                    editText3.setCursorVisible(true);

                    numberTwo = s.toString();

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {


            }

            public void afterTextChanged(Editable s) {

            }
        });
        editText3.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText3.length() == 1) {

                    editText3.clearFocus();
                    editText4.requestFocus();
                    editText4.setCursorVisible(true);

                    numberThree = s.toString();

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {


            }

            public void afterTextChanged(Editable s) {

            }
        });
        editText4.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText4.length() == 1) {
                    editText4.clearFocus();

                    numberFour = s.toString();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });
        submit.setOnClickListener(view -> {

            String code = (numberOne + numberTwo + numberThree + numberFour).trim();


            verficationPresenter.verifyAccount(code);


        });

    }
}
