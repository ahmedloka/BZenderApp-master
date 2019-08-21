package apps.sharabash.bzender.activities.chatRoom;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import apps.sharabash.bzender.Models.message.Message;
import apps.sharabash.bzender.Models.singleChat.SingleChatResponse;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.activities.chatAllUsers.ChatListActivity;
import apps.sharabash.bzender.adapters.RecyclerMessagesOneToOneAdapter;
import apps.sharabash.bzender.dialog.DialogLoader;
import apps.sharabash.bzender.services.SignalRService;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;
import microsoft.aspnet.signalr.client.transport.ClientTransport;

public class ChatRoom extends AppCompatActivity implements RecyclerMessagesOneToOneAdapter.OnClickHandler, View.OnClickListener, ChatRoomInterface {

    @SuppressLint("StaticFieldLeak")
    public static RecyclerView mRecyclerViewOneToOne;
    public static RecyclerMessagesOneToOneAdapter messagesOneToOneAdapter;
    private static Message message;
    private final Context mContext = this;
    private final List<Message> messageList = new ArrayList<>();
    ListView messagesView;
    ClientTransport transport;
    HubConnection connection;
    HubProxy hub;
    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    private Handler mHandler; // to display Toast message
    private EditText mEdTMsg;
    private String senderId, receiverId;
    private SignalRService mService;
    private boolean mBound = false;
    /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private final ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to SignalRService, cast the IBinder and get SignalRService instance
            SignalRService.LocalBinder binder = (SignalRService.LocalBinder) service;
            mService = binder.getService();
            //  Toast.makeText(mContext, "onServiceConnected", Toast.LENGTH_SHORT).show();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            // Toast.makeText(mContext, "Disconnected", Toast.LENGTH_SHORT).show();
            mBound = false;
        }
    };
    private List<String> sendMsgs, receivedMsgs;
    private DialogLoader dialogLoader, dialogLoaderTwo;
    private int page = 0;
    private ChatRoomPresenter chatRoomPresenter;
    private ProgressBar progressBar;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        dialogLoader = new DialogLoader();
        dialogLoaderTwo = new DialogLoader();

        sendMsgs = new ArrayList<>();
        receivedMsgs = new ArrayList<>();

        sharedPreferences = getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
        chatRoomPresenter = new ChatRoomPresenter(this, this);

        ImageView mIVSendMsg = findViewById(R.id.btn_send_message);
        mIVSendMsg.setOnClickListener(this);

        receiverId = (getIntent().getStringExtra(Constant.RECEIVER_ID));
        senderId = getIntent().getStringExtra(Constant.SENDER_ID);


        Log.d("IDRECEIVER", "onCreate: " + receiverId);
        Log.d("IDSENDER", "onCreate: " + senderId);


        Intent intentSignalR = new Intent();
        intentSignalR.setClass(mContext, SignalRService.class);
        bindService(intentSignalR, mConnection, Context.BIND_AUTO_CREATE);

        mHandler = new Handler(Looper.getMainLooper());


        AppCompatImageView mImgBack = findViewById(R.id.imageNavigationIcon);
        mImgBack.setOnClickListener(v -> {

            Intent intent = new Intent(this, ChatListActivity.class);
            startActivity(intent);
            finish();
        });


        progressBar = findViewById(R.id.progressBar);

        mEdTMsg = findViewById(R.id.et_message);
        mRecyclerViewOneToOne = findViewById(R.id.recycler_view_messages_one_to_one);
        mRecyclerViewOneToOne.setHasFixedSize(true);
        mRecyclerViewOneToOne.setItemAnimator(new SlideInUpAnimator());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setStackFromEnd(true);
        mRecyclerViewOneToOne.setLayoutManager(layoutManager);
        mRecyclerViewOneToOne.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
/*
                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrollOutItems = layoutManager.findFirstVisibleItemPosition();

                if (dy > 0) {
                    // scrolling up
                    if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                        isScrolling = false;

                        if ((mRecyclerViewOneToOne.getAdapter().getItemCount() >= 30)) {
                            page++;
                            //progressBar.setVisibility(View.VISIBLE);
//                            chatRoomPresenter.getChatRoomData(senderId, page)) {
                            page++;
                            dialogLoader.show(getSupportFragmentManager(), "");

                            //progressBar.setVisibility(View.VISIBLE);
                            chatRoomPresenter.getChatRoomData(senderId, page);
                        }

                    }
                } else {
                    //scrolling down
                    if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                        isScrolling = false;
                        if (page > 0) {
                            page--;
                            dialogLoaderTwo.show(getSupportFragmentManager(), "");
                            //progressBar.setVisibility(View.VISIBLE);
                            chatRoomPresenter.getChatRoomData(senderId, page);
                        }
                    }
                }*/


            }
        });

        chatRoomPresenter.getChatRoomData(senderId, 0);


    }


    @Override
    protected void onResume() {
        super.onResume();
        mBound = false;
        Intent intent = new Intent();
        intent.setClass(mContext, SignalRService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
        super.onStop();
    }

    private void sendMessage() {
        if (mBound) {
            // Call a method from the SignalRService.
            // However, if this call were something that might hang, then this request should
            // occur in a separate thread to avoid slowing down the activity performance.
            if (!mEdTMsg.getText().toString().isEmpty()) {
                String message = mEdTMsg.getText().toString();
                //String reciveridentityId, String myID, String message
                mService.sendMessage(senderId, receiverId, message);

                Log.d("TEEEST", "sendMessage: " + message + Message.MSG_TYPE_SENT + senderId);
                messagesOneToOneAdapter = new RecyclerMessagesOneToOneAdapter(messageList, this);
                messagesOneToOneAdapter.addItem(new Message(Message.MSG_TYPE_SENT, message));
                mRecyclerViewOneToOne.setAdapter(messagesOneToOneAdapter);
                mRecyclerViewOneToOne.smoothScrollToPosition(messagesOneToOneAdapter.getItemCount() - 1);


                mEdTMsg.getText().clear();

            }

        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_send_message) {
            sendMessage();
        }
    }

    @Override
    public void getChatRoomData(SingleChatResponse singleChatResponse) {


        Log.d("TEEEST", "getChatRoomData: " + singleChatResponse.getChatList().get(0).getMessageDate());

        for (int i = 0; i < singleChatResponse.getChatList().size(); i++) {
            if (singleChatResponse.getChatList().get(i).getSenderId().equals(senderId)) {
                messageList.add(new Message(Message.MSG_TYPE_RECEIVED , singleChatResponse.getChatList().get(i).getBody()));
              //  receivedMsgs.add(singleChatResponse.getChatList().get(i).getBody());
            }else {
                messageList.add(new Message(Message.MSG_TYPE_SENT , singleChatResponse.getChatList().get(i).getBody()));
            }

            if (!singleChatResponse.getChatList().get(i).getSenderId().equals(senderId)) {

                // sendMsgs.add(singleChatResponse.getChatList().get(i).getBody());
            }
        }


    }

    @Override
    public void finishGetDate() {
        //progressBar.setVisibility(View.GONE);

        messagesOneToOneAdapter = new RecyclerMessagesOneToOneAdapter(messageList, this);
//        for (int i = 0; i < sendMsgs.size(); i++) {
//            messagesOneToOneAdapter.addItem(new Message(Message.MSG_TYPE_SENT, sendMsgs.get(i)));
//        }
//
//        for (int i = 0; i < receivedMsgs.size(); i++) {
//            messagesOneToOneAdapter.addItem(new Message(Message.MSG_TYPE_RECEIVED, receivedMsgs.get(i)));
//        }


        mRecyclerViewOneToOne.setAdapter(messagesOneToOneAdapter);

        Log.d("count", "initViews: " + messagesOneToOneAdapter.getItemCount());
        if (messagesOneToOneAdapter.getItemCount() > 1)
            mRecyclerViewOneToOne.smoothScrollToPosition(messagesOneToOneAdapter.getItemCount() - 1);

    }

    @Override
    public void onClick(int position) {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this, ChatListActivity.class);
        startActivity(intent);
        finish();
    }
}
