package apps.sharabash.bzender.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import apps.sharabash.bzender.Models.allTinders.AllTenderRecyclerItem;
import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.CountTinerDownTimer;
import apps.sharabash.bzender.Utills.MyTextViewBold;


public class AllTenderRecyclerAdapter extends RecyclerView.Adapter<AllTenderRecyclerAdapter.ViewHolder> { //implements AllTendersInterface

    private static final String TAG = "AdapterAllTenders";

    private static String TIMER_DAYS;
    private static String TIMER_HRS;
    private static String TIMER_MINS;
    private static String TIMER_SECS;
    private final OnClickHandler onClickHandler;


    private Context context;
    private final List<AllTenderRecyclerItem> allTenderRecyclerItems;

    public AllTenderRecyclerAdapter(Context context, List<AllTenderRecyclerItem> allTenderRecyclerItems, OnClickHandler onClickHandler) {
        this.onClickHandler = onClickHandler;
        this.context = context;
        this.allTenderRecyclerItems = allTenderRecyclerItems;

    }

    @Override
    public int getItemCount() {
        return allTenderRecyclerItems == null ? 0 : allTenderRecyclerItems.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details, parent, false);
        return new ViewHolder(view);
    }

    private void removeAt(int position) {
        allTenderRecyclerItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, allTenderRecyclerItems.size());
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int listPosition) {


        holder.name.setText(allTenderRecyclerItems.get(listPosition).getName());
        holder.startDate.setText(allTenderRecyclerItems.get(listPosition).getStartDate());
        holder.endDate.setText(allTenderRecyclerItems.get(listPosition).getEndDate());


        String strDate = allTenderRecyclerItems.get(listPosition).getStartDate();
        String endDate = allTenderRecyclerItems.get(listPosition).getEndDate();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            Date dateOne = dateFormat.parse(strDate);
            Date dateTwo = dateFormat.parse(endDate);

            Log.d("startDate", "getAllTenderList: " + dateOne);
            Log.d("startDate", "getAllTenderList: " + dateOne.getTime());

            Log.d("startDate", "getAllTenderList: " + dateTwo);
            Log.d("startDate", "getAllTenderList: " + dateTwo.getTime());


            long difference = dateTwo.getTime() - dateOne.getTime();
            Log.d("difference", "getAllTenderList: " + difference);

            CountTinerDownTimer countDownTimer = new CountTinerDownTimer(difference, 1000, holder.txtDays, holder.txtHrs, holder.txtMins, holder.txtSecs);
            countDownTimer.start();

            Log.d(TAG, "onBindViewHolder: " + TIMER_DAYS);

//            Timer t = new Timer();
//            t.scheduleAtFixedRate(new TimerTask() {
//
//                                      @Override
//                                      public void run() {
//                                          //Called each time when 1000 milliseconds (1 second) (the period parameter)
//                                          ((Activity) context).runOnUiThread(() -> {
//                                              holder.txtDays.setText(TIMER_DAYS);
//                                              holder.txtHrs.setText(TIMER_HRS);
//                                              holder.txtMins.setText(TIMER_MINS);
//                                              holder.txtSecs.setText(TIMER_SECS);
//                                          });
//                                      }
//
////                                  },
//                    0,
//                    1000);


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
//        Log.d(TAG, "setTimerDays: " + TIMER_DAYS);
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

//    @Override
//    public void setTimerTextAll(String timerTextAll) {
//
//    }

    public interface OnClickHandler {
        void onClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView name;
        final TextView startDate;
        final TextView endDate;
        final TextView count;
        //SimpleRatingBar offer_rate;

        private final MyTextViewBold txtDays;
        private final MyTextViewBold txtHrs;
        private final MyTextViewBold txtMins;
        private final MyTextViewBold txtSecs;


        ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            startDate = itemView.findViewById(R.id.startDate);
            endDate = itemView.findViewById(R.id.endDate);
            count = itemView.findViewById(R.id.count);

            txtDays = itemView.findViewById(R.id.txt_days);
            txtHrs = itemView.findViewById(R.id.txt_hrs);
            txtMins = itemView.findViewById(R.id.txt_mins);
            txtSecs = itemView.findViewById(R.id.txt_secs);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            onClickHandler.onClick(position);
            notifyDataSetChanged();
        }
    }


}
