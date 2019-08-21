package apps.sharabash.bzender.activities.myTender;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import apps.sharabash.bzender.Models.booking.MyTender;
import apps.sharabash.bzender.Models.my_tenders.MyBookingBody;
import apps.sharabash.bzender.Models.my_tenders.MyTendersBody;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.MyTextViewBold;
import apps.sharabash.bzender.activities.myBooking.MyTenderInterface;
import apps.sharabash.bzender.activities.myBooking.MyTenderPresenter;
import apps.sharabash.bzender.adapters.MyTenderAdapter;

public class MyTendersActivity extends AppCompatActivity implements MyTenderInterface {

    private RecyclerView nearByRecyclerView;
    private MyTextViewBold mTxtEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tinders);

        initViews();
    }

    private void initViews() {
        mTxtEmpty = findViewById(R.id.txt_empty);
        AppCompatImageView mImgBack = findViewById(R.id.imageNavigationIcon);
        mImgBack.setOnClickListener(v -> {
            NavUtils.navigateUpFromSameTask(this);
        });

        MyTenderPresenter myTenderPresenter = new MyTenderPresenter(this, this);
        myTenderPresenter.getMyTender();
        nearByRecyclerView = findViewById(R.id.recycler);


    }

    @Override
    public void getMyooking(MyBookingBody myBookingBody) {

    }

    @Override
    public void getMyTender(List<MyTendersBody> myTendersBody) {

        if (myTendersBody.size() == 0) {
            //Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            Log.d("CHECK", "getMyTender: " + "nullllll");

            mTxtEmpty.setVisibility(View.VISIBLE);
            return;
        }

        mTxtEmpty.setVisibility(View.GONE);
        Log.d("TEST DATA GOT", "getMyTender: " + myTendersBody.get(0).getOwnerUserId());
        List<MyTender> iteMyTender = new ArrayList<>();


        String cat = null;

        for (int i = 0; i < myTendersBody.size(); i++) {
            if (myTendersBody.get(i).getCategoryID().equals("10021")) {
                cat = getString(R.string.cat_cars);
            } else if (myTendersBody.get(i).getCategoryID().equals("10022")) {
                cat = getString(R.string.cat_electronincs);
            }

            iteMyTender.add(new MyTender(
                    myTendersBody.get(i).getStartDateTender()
                    , myTendersBody.get(i).getEndDateTender()
                    , myTendersBody.get(i).getTenderName()
                    , myTendersBody.get(i).getCategoryID()
                    , myTendersBody.get(i).getId()
                    , cat
                    , myTendersBody.get(i).getStatusId()));

            Log.d("CHECK", "getMyTender: " + myTendersBody.get(i).getStatusId());

        }

        nearByRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        MyTenderAdapter myTenderAdapter = new MyTenderAdapter(this, iteMyTender);

        nearByRecyclerView.setAdapter(myTenderAdapter);

    }
}
