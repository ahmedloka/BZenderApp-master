package apps.sharabash.bzender.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.Constant;

public class ChooseHowItWork extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Animatoo.animateInAndOut(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_how_it_work);

        LinearLayout tender_maker = findViewById(R.id.tender_maker);
        LinearLayout bidder = findViewById(R.id.bidder);

        tender_maker.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HowAddTender.class);
            intent.putExtra(Constant.Username, getIntent().getStringExtra(Constant.Username));
            startActivity(intent);
            finish();

        });
        bidder.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HowBookTender.class);
            intent.putExtra(Constant.Username, getIntent().getStringExtra(Constant.Username));
            startActivity(intent);
            finish();

        });
    }
}
