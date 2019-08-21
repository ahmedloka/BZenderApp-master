package apps.sharabash.bzender.activities.notification;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import apps.sharabash.bzender.Models.notification.NotificationResponse;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.MyTextViewBold;
import apps.sharabash.bzender.adapters.NotificationModel;
import apps.sharabash.bzender.adapters.NotificationsAdapter;

public class Notifications extends AppCompatActivity implements NotificationInterface {

    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    private int page = 0;
    private RecyclerView nearByRecyclerView;

    private ProgressBar progressBar;
    private MyTextViewBold mTxtEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        mTxtEmpty = findViewById(R.id.txt_empty);
        progressBar = findViewById(R.id.progressBar);

        AppCompatImageView mImgBack = findViewById(R.id.imageNavigationIcon);
        mImgBack.setOnClickListener(v -> {
            NavUtils.navigateUpFromSameTask(this);
        });


        NotificationPresenter notificationPresenter = new NotificationPresenter(this, this);


        nearByRecyclerView = findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Notifications.this, LinearLayoutManager.VERTICAL, false);
        nearByRecyclerView.setLayoutManager(linearLayoutManager);


        nearByRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItems = linearLayoutManager.getChildCount();
                totalItems = linearLayoutManager.getItemCount();
                scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition();

                if (dy > 0) {
                    //scrolling down
                    if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                        isScrolling = false;

                        progressBar.setVisibility(View.VISIBLE);
                        if ((Objects.requireNonNull(nearByRecyclerView.getAdapter()).getItemCount() >= 30
                                && Objects.requireNonNull(nearByRecyclerView.getAdapter()).getItemCount() < 60)) {
                            page = 1;
                            //progressBar.setVisibility(View.VISIBLE);
//                            chatRoomPresenter.getChatRoomData(senderId, page)) {
                            // dialogLoader.show(getSupportFragmentManager(), "");

                            //progressBar.setVisibility(View.VISIBLE);
                            notificationPresenter.getAllNotifications(page);
                        } else if ((Objects.requireNonNull(nearByRecyclerView.getAdapter()).getItemCount() < 90)) {
                            page = 2;
                            //progressBar.setVisibility(View.VISIBLE);
//                            chatRoomPresenter.getChatRoomData(senderId, page)) {
                            //dialogLoader.show(getSupportFragmentManager(), "");

                            //progressBar.setVisibility(View.VISIBLE);
                            notificationPresenter.getAllNotifications(page);
                        } else if ((Objects.requireNonNull(nearByRecyclerView.getAdapter()).getItemCount() < 120)) {
                            page = 3;
                            //progressBar.setVisibility(View.VISIBLE);
//                            chatRoomPresenter.getChatRoomData(senderId, page)) {
                            //dialogLoader.show(getSupportFragmentManager(), "");

                            //progressBar.setVisibility(View.VISIBLE);
                            notificationPresenter.getAllNotifications(page);
                        } else if ((Objects.requireNonNull(nearByRecyclerView.getAdapter()).getItemCount() < 150)) {
                            page = 4;
                            //progressBar.setVisibility(View.VISIBLE);
//                            chatRoomPresenter.getChatRoomData(senderId, page)) {
                            // dialogLoader.show(getSupportFragmentManager(), "");

                            //progressBar.setVisibility(View.VISIBLE);
                            notificationPresenter.getAllNotifications(page);
                        } else if ((Objects.requireNonNull(nearByRecyclerView.getAdapter()).getItemCount() < 180)) {
                            page = 5;
                            //progressBar.setVisibility(View.VISIBLE);
//                            chatRoomPresenter.getChatRoomData(senderId, page)) {
                            //dialogLoader.show(getSupportFragmentManager(), "");

                            //progressBar.setVisibility(View.VISIBLE);
                            notificationPresenter.getAllNotifications(page);
                        }

                    }

                    Log.d("PAGE", "onCreate: " + page);
                } else {
                    // scrolling up

                    progressBar.setVisibility(View.VISIBLE);
                    isScrolling = false;

                    if (page > 0) {
                        page--;

                        // dialogLoaderTwo.show(getSupportFragmentManager(), "");
                        //progressBar.setVisibility(View.VISIBLE);
                        notificationPresenter.getAllNotifications(page);
                        linearLayoutManager.setStackFromEnd(true);
                    }

                    Log.d("PAGE", "onScrolled: " + page);

                }


            }
        });

        notificationPresenter.getAllNotifications(page);


    }


    @Override
    public void getAllNotification(NotificationResponse notificationResponse) {

        if (notificationResponse.getNotifications().size() == 0) {
            //Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            Log.d("CHECK", "getMyTender: " + "nullllll");

            mTxtEmpty.setVisibility(View.VISIBLE);

            return;
        }

        mTxtEmpty.setVisibility(View.GONE);


        progressBar.setVisibility(View.GONE);

        List<NotificationModel> itemModels = new ArrayList<>();

        for (int i = 0; i < notificationResponse.getNotifications().size(); i++) {
            itemModels.add(new NotificationModel(notificationResponse.getNotifications().get(i).getMessage(),
                    notificationResponse.getNotifications().get(i).getCreationDate()));
        }


        NotificationsAdapter nearByRecyclerAdapter = new NotificationsAdapter(Notifications.this, itemModels);
        nearByRecyclerView.setAdapter(nearByRecyclerAdapter);

    }
}
