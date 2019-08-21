package apps.sharabash.bzender.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.Utills.CountTinerDownTimer;
import apps.sharabash.bzender.Utills.MyTextView;
import apps.sharabash.bzender.Utills.MyTextViewBold;

import static android.content.Context.MODE_PRIVATE;


public class MyBookingAdapter extends RecyclerView.Adapter<MyBookingAdapter.ViewHolder> {

    private static final String TAG = "MY_TENDER_ADAPTER";
    private final List<ItemBookingModel> itemList;
    private final String lang;
    private Context context;


    public MyBookingAdapter(Context context, List<ItemBookingModel> itemList) {
        this.context = context;
        this.itemList = itemList;

        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPreference", MODE_PRIVATE);
        lang = sharedPreferences.getString(Constant.language, Locale.getDefault().getDisplayLanguage());
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_tinder, parent, false);
        return new ViewHolder(view);
    }

    private void removeAt(int position) {
        itemList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, itemList.size());
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int listPosition) {

        Log.d(TAG, "onBindViewHolder: " + itemList.get(listPosition).getStatusId());

        switch (itemList.get(listPosition).getStatusId()) {
            case 4: //accepted
                if (lang.equals("ar"))
                    holder.imgTypeBg.setImageResource(R.drawable.accepted_img_ar);
                else
                    holder.imgTypeBg.setImageResource(R.drawable.accepted_img_en);
                break;
            case 6: // pending
                if (lang.equals("ar"))
                    holder.imgTypeBg.setImageResource(R.drawable.pending_img_ar);
                else
                    holder.imgTypeBg.setImageResource(R.drawable.pending_img_en);
                break;
            case 5: // rejected
                if (lang.equals("ar"))
                    holder.imgTypeBg.setImageResource(R.drawable.rejected_img_ar);
                else
                    holder.imgTypeBg.setImageResource(R.drawable.rejected_img_en);
                break;
            case 7:
                if (lang.equals("ar"))
                    holder.imgTypeBg.setImageResource(R.drawable.finished_ar);
                else
                    holder.imgTypeBg.setImageResource(R.drawable.finished_en);
                break;
            default:
                holder.imgTypeBg.setVisibility(View.GONE);
                break;
        }

        if (itemList.get(listPosition).getCatName().equals(context.getString(R.string.cat_cars))) {
            holder.imgCat.setImageDrawable(context.getResources().getDrawable(R.drawable.cars));
        } else if (itemList.get(listPosition).getCatName().equals(context.getString(R.string.cat_electronincs))) {
            holder.imgCat.setImageDrawable(context.getResources().getDrawable(R.drawable.electrical));
        }

        holder.endDate.setText(itemList.get(listPosition).getEndDate());
        holder.startDate.setText(itemList.get(listPosition).getStartDate());
        holder.txtCatName.setText(itemList.get(listPosition).getCatName());
        holder.txtTenderName.setText(itemList.get(listPosition).getName());
        holder.endDate.setText(itemList.get(listPosition).getStartDate());

        String strDate = itemList.get(listPosition).getStartDate();

        String endDate = itemList.get(listPosition).getEndDate();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            Date dateOne = dateFormat.parse(strDate);
            Date dateTwo = dateFormat.parse(endDate);

            long difference = dateTwo.getTime() - dateOne.getTime();
            Log.d("difference", "getAllTenderList: " + difference);

            CountDownTimer countDownTimer = new CountTinerDownTimer(difference, 1000, holder.txtDays, holder.txtHrs, holder.txtMins, holder.txtSecs);

            countDownTimer.start();


//            Timer t = new Timer();
//            t.scheduleAtFixedRate(new TimerTask() {
//
//                                      @Override
//                                      public void run() {
//                                          //Called each time when 1000 milliseconds (1 second) (the period parameter)
//                                          ((Activity) context).runOnUiThread(new Runnable() {
//                                              @Override
//                                              public void run() {
//                                                  holder.txtDays.setText(TIMER_DAYS);
//                                                  holder.txtHrs.setText(TIMER_HRS);
//                                                  holder.txtMins.setText(TIMER_MINS);
//                                                  holder.txtSecs.setText(TIMER_SECS);
//                                              }
//                                          });
//                                      }
//
//                                  },
//                    0,
//                    1000);
//

        } catch (ParseException e) {
            e.printStackTrace();
            Log.d("startDate", "getAllTenderList: " + e.getMessage());
        }


    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

//    @Override
//    public void setTimerDays(String timerTxt) {
//
//        TIMER_DAYS = timerTxt;
//    }
//
//    @Override
//    public void setTimerHours(String timerHours) {
//        TIMER_HRS = timerHours;
//    }
//
//    @Override
//    public void setTimerMinuts(String timerMinutes) {
//        TIMER_MINS = timerMinutes;
//    }
//
//    @Override
//    public void setTimerSeconds(String timerSeconds) {
//        TIMER_SECS = timerSeconds;
//
//    }
//
//    @Override
//    public void setTimerTextAll(String timerTextAll) {
//        TIMER_TEXT = timerTextAll;
//    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private AppCompatImageView imgTypeBg;
        private AppCompatImageView imgCat;
        private MyTextViewBold txtCatName;
        private MyTextViewBold txtTenderName;
        private MyTextViewBold txtDays;
        private MyTextViewBold txtHrs;
        private MyTextViewBold txtMins;
        private MyTextViewBold txtSecs;
        private MyTextView startDate;
        private LinearLayoutCompat linearLayoutCompat;
        private MyTextView endDate;


        ViewHolder(View itemView) {
            super(itemView);

            initViews(itemView);
        }

        private void initViews(View itemView) {
            imgTypeBg = itemView.findViewById(R.id.img_type_bg);
            imgCat = itemView.findViewById(R.id.img_cat);
            txtCatName = itemView.findViewById(R.id.txt_cat_name);
            txtTenderName = itemView.findViewById(R.id.txt_tender_name);
            txtDays = itemView.findViewById(R.id.txt_days);
            txtHrs = itemView.findViewById(R.id.txt_hrs);
            txtMins = itemView.findViewById(R.id.txt_mins);
            txtSecs = itemView.findViewById(R.id.txt_secs);
            startDate = itemView.findViewById(R.id.startDate);
            linearLayoutCompat = itemView.findViewById(R.id.linearLayoutCompat);
            endDate = itemView.findViewById(R.id.endDate);
        }

        @Override
        public void onClick(View view) {
        }
    }
}
