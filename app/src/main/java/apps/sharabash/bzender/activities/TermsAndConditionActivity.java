package apps.sharabash.bzender.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;

import apps.sharabash.bzender.Models.metadataCar.MetaDataCar;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.MyTextView;
import apps.sharabash.bzender.activities.fillDataCar.FillDataCarInterface;
import apps.sharabash.bzender.activities.fillDataCar.FillDataCarPresenter;

public class TermsAndConditionActivity extends AppCompatActivity implements FillDataCarInterface {

    private MyTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_condition);

        AppCompatImageView mImgBack = findViewById(R.id.imageNavigationIcon);
        mImgBack.setOnClickListener(v -> {
            NavUtils.navigateUpFromSameTask(this);
        });

        textView = findViewById(R.id.txt_terms);

        FillDataCarPresenter presenter = new FillDataCarPresenter(this, this);
        presenter.getMetaData();
    }

    @Override
    public void getMetaCar(MetaDataCar metaDataCar) {
        String description = metaDataCar.getTermsAndCondition().get(0).getDescription();
        textView.setText(description);


    }
}
