package apps.sharabash.bzender.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import apps.sharabash.bzender.R;


public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {


    private Context context;
    private final List<NotificationModel> itemList;


    public NotificationsAdapter(Context context, List<NotificationModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(view);
    }

    private void removeAt(int position) {
        itemList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, itemList.size());
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int listPosition) {


        holder.desc.setText(itemList.get(listPosition).getDesc());

        String dateStr = itemList.get(listPosition).getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        try {
            Date date = dateFormat.parse(dateStr);
            long dateSec = date.getTime();

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(dateSec);

            int year = c.get(Calendar.YEAR);

            int months = c.get(Calendar.MONTH);

            int days = c.get(Calendar.DAY_OF_MONTH);

            holder.date.setText(days + " / " + months + " / " + year);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        holder.itemView.setOnClickListener(v -> {
            NavUtils.navigateUpFromSameTask((Activity) context);
            ((Activity) context).overridePendingTransition(R.anim.pull_in_left, R.anim.pull_in_right);
        });
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView desc;
        final TextView date;


        ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            desc = itemView.findViewById(R.id.desc);

        }

        @Override
        public void onClick(View view) {
        }
    }
}
