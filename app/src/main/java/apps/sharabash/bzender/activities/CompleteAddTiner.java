package apps.sharabash.bzender.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.ButtonBook;
import apps.sharabash.bzender.Utills.MyTextView;
import io.ghyeok.stickyswitch.widget.StickySwitch;

public class CompleteAddTiner extends AppCompatActivity implements View.OnClickListener {

    private MyTextView mTxtIndividual, mTxtBussiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_add_tiner);

        initViews();
    }

    private void initViews() {
        ButtonBook mBtn = findViewById(R.id.next);

        mBtn.setOnClickListener(this);
        StickySwitch mSwitch = findViewById(R.id.sticky_switch);

        mTxtBussiness = findViewById(R.id.txt_bussiness);
        mTxtIndividual = findViewById(R.id.txt_individual);


        mTxtBussiness.setTextColor(getResources().getColor(android.R.color.white));
        mTxtBussiness.setTextSize(14);

        mSwitch.setOnSelectedChangeListener((direction, s) -> {


            if (direction == StickySwitch.Direction.LEFT) {
                Log.d("TEST", "onSelectedChange: " + s + direction);
                mTxtBussiness.setTextColor(getResources().getColor(android.R.color.white));
                mTxtIndividual.setTextColor(getResources().getColor(R.color.graycolor));

                mTxtBussiness.setTextSize(14);
                mTxtIndividual.setTextSize(12);
            } else {
                Log.d("TEST", "onSelectedChange: " + s + direction);
                mTxtIndividual.setTextColor(getResources().getColor(android.R.color.white));
                mTxtBussiness.setTextColor(getResources().getColor(R.color.graycolor));

                mTxtIndividual.setTextSize(14);
                mTxtBussiness.setTextSize(12);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.next) {//showDialogCreate();
        }
    }


}
