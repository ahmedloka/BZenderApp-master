package apps.sharabash.bzender.activities.chatAllUsers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONObject;

import apps.sharabash.bzender.Models.chatList.AllUserMessage;
import apps.sharabash.bzender.Network.NetworkUtil;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.Validation;
import apps.sharabash.bzender.activities.Complaint.AllUsersChatInterface;
import apps.sharabash.bzender.dialog.DialogLoader;
import retrofit2.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static apps.sharabash.bzender.Utills.Constant.buildDialog;

class AllUsersChatPresenter {

    private final Context mContext;
    private final CompositeSubscription mSubscriptions;
    private final AllUsersChatInterface allUsersChatInterface;
    private final SharedPreferences sharedPreferences;
    private final DialogLoader dialogLoader = new DialogLoader();

    public AllUsersChatPresenter(Context context, AllUsersChatInterface allUsersChatInterface) {
        this.mContext = context;
        this.allUsersChatInterface = allUsersChatInterface;
        mSubscriptions = new CompositeSubscription();
        sharedPreferences = mContext.getSharedPreferences("MySharedPreference", Context.MODE_PRIVATE);
    }

    public void getAllUsersMessage() {
        if (Validation.isConnected(mContext)) {
            dialogLoader.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "");
            mSubscriptions.add(NetworkUtil.getRetrofitByToken(sharedPreferences.getString(Constant.UserID, ""))
                    .getAllUserChat()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        } else {
            buildDialog((Activity) mContext).show().setCanceledOnTouchOutside(false);
        }
    }

    private void handleResponse(AllUserMessage allUserMessage) {
        if (dialogLoader.isAdded()) {
            dialogLoader.dismiss();
        }
        allUsersChatInterface.getAllUserChatData(allUserMessage);
    }


    private void handleError(Throwable throwable) {
        if (dialogLoader.isAdded()) {
            dialogLoader.dismiss();
        }
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
