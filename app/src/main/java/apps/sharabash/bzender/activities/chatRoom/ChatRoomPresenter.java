package apps.sharabash.bzender.activities.chatRoom;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONObject;

import apps.sharabash.bzender.Models.singleChat.SingleChatResponse;
import apps.sharabash.bzender.Network.NetworkUtil;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.Validation;
import apps.sharabash.bzender.dialog.DialogLoader;
import retrofit2.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static apps.sharabash.bzender.Utills.Constant.buildDialog;

public class ChatRoomPresenter {
    private final Context mContext;
    private final CompositeSubscription mSubscriptions;
    private final ChatRoomInterface chatRoomInterface;
    private final SharedPreferences sharedPreferences;
    private final DialogLoader dialogLoader;
    String senderId;
    int page;

    public ChatRoomPresenter(Context context, ChatRoomInterface chatRoomInterface) {
        this.mContext = context;
        this.chatRoomInterface = chatRoomInterface;
        mSubscriptions = new CompositeSubscription();
        dialogLoader = new DialogLoader();
        sharedPreferences = mContext.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
    }

    public void getChatRoomData(String senderId, int page) {
        if (!dialogLoader.isAdded())
            dialogLoader.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "");

        this.senderId = senderId;
        this.page = page;
        if (Validation.isConnected(mContext)) {
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString(Constant.UserID, ""))
                    .getChatChatRoomData(senderId, page)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            buildDialog((Activity) mContext).show().setCanceledOnTouchOutside(false);
        }
    }

    private void handleResponse(SingleChatResponse singleChatResponse) {

        Log.d("RESPONSE", "handleResponse: " + singleChatResponse.toString());
        chatRoomInterface.getChatRoomData(singleChatResponse);
        if (singleChatResponse.getChatList().size() == 30) {
            page++;
            getChatRoomData(senderId, page);
        } else if (singleChatResponse.getChatList().size() < 30) {

            if (dialogLoader.isAdded()) {
                dialogLoader.dismiss();
            }
            chatRoomInterface.finishGetDate();
        }
    }

    private void handleError(Throwable throwable) {
//        if (dialogLoader.isAdded()) {
//            dialogLoader.dismiss();
//        }

        Log.d("RESPONSE", "handleResponse: " + throwable.getMessage());
        String message = "";
        if (throwable instanceof retrofit2.HttpException) {
            try {
                retrofit2.HttpException error = (retrofit2.HttpException) throwable;
                JSONObject jsonObject = new JSONObject(((HttpException) throwable).response().errorBody().string());
                message = jsonObject.getString("Message");
            } catch (Exception e) {
                message = throwable.getMessage();
            }
            Constant.getErrorDependingOnResponse(mContext, message);

        }
    }


}
