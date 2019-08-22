package apps.sharabash.bzender.activities.chatAllUsers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.List;

import apps.sharabash.bzender.Models.chatList.AllUserMessage;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.MyTextViewBold;
import apps.sharabash.bzender.activities.Complaint.AllUsersChatInterface;
import apps.sharabash.bzender.adapters.ChatListAdapter;
import apps.sharabash.bzender.adapters.ChatModel;

public class ChatListActivity extends AppCompatActivity implements AllUsersChatInterface {

    private RecyclerView nearByRecyclerView;
    private SharedPreferences sharedPreferences;
    private String USER_ID;
    private MyTextViewBold mTxtEmpty;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideUp(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mTxtEmpty = findViewById(R.id.txt_empty);

        sharedPreferences = getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
        USER_ID = sharedPreferences.getString(Constant.USER_ID_CHAT, "");

        AppCompatImageView mImgBack = findViewById(R.id.imageNavigationIcon);
        mImgBack.setOnClickListener(v -> {
            NavUtils.navigateUpFromSameTask(this);
            Animatoo.animateSlideUp(this);
        });

        AllUsersChatPresenter allUsersChatPresenter = new AllUsersChatPresenter(this, this);
        allUsersChatPresenter.getAllUsersMessage();

        nearByRecyclerView = findViewById(R.id.recycler);
        nearByRecyclerView.setLayoutManager(new LinearLayoutManager(ChatListActivity.this, LinearLayoutManager.VERTICAL, false));


    }

    @Override
    public void getAllUserChatData(AllUserMessage allUserMessage) {

        if (allUserMessage.getChatList().size() == 0) {
            //Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            Log.d("CHECK", "getMyTender: " + "nullllll");

            mTxtEmpty.setVisibility(View.VISIBLE);
            return;
        }

        mTxtEmpty.setVisibility(View.GONE);

        List<ChatModel> itemModels = new ArrayList<>();

        Log.d("<DE", "getAllUserChatData: " + USER_ID);

//        if (allUserMessage.getChatList().get(0).getReceiverId().equals(USER_ID)) {
//            itemModels.add(new ChatModel(
//                    allUserMessage.getChatList().get(0).getSenderId()
//                    , allUserMessage.getChatList().get(0).getReceiverId()
//                    , ""
//                    , ""
//                    , allUserMessage.getChatList().get(0).getBody()));
//        }
//
//        if (allUserMessage.getChatList().get(1).getReceiverId().equals(USER_ID)) {
//            itemModels.add(new ChatModel(
//                    allUserMessage.getChatList().get(1).getSenderId()
//                    , allUserMessage.getChatList().get(1).getReceiverId()
//                    , ""
//                    , ""
//                    , allUserMessage.getChatList().get(1).getBody()));
//        }

        for (int i = 0; i < allUserMessage.getChatList().size(); i++) {
            //Toast.makeText(this, "ddsd", Toast.LENGTH_SHORT).show();
            itemModels.add(new ChatModel(
                    allUserMessage.getChatList().get(i).getSenderId()
                    , allUserMessage.getChatList().get(i).getReceiverId()
                    , "B-Zender"
                    , allUserMessage.getChatList().get(i).getBody()));


        }


        ChatListAdapter nearByRecyclerAdapter = new ChatListAdapter(ChatListActivity.this, itemModels);
        nearByRecyclerView.setAdapter(nearByRecyclerAdapter);
        Constant.runLayoutAnimation(nearByRecyclerView);

    }
}
