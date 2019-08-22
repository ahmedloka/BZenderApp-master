package apps.sharabash.bzender.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.List;

import apps.sharabash.bzender.R;
import apps.sharabash.bzender.Utills.Constant;
import apps.sharabash.bzender.activities.Tenders.AllTenderActivity;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {


    private Context context;
    private final List<String> nameList;


    public HomeAdapter(Context context, List<String> nameList) {
        this.context = context;
        this.nameList = nameList;
    }

    @Override
    public int getItemCount() {
        return nameList == null ? 0 : nameList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        return new ViewHolder(view);
    }

    private void removeAt(int position) {
        nameList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, nameList.size());
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int listPosition) {

        switch (listPosition) {
            case 0:
                holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.cars));
                break;
            case 1:
                holder.img.setImageDrawable(context.getResources().getDrawable(R.drawable.electrical));
                break;
        }

        holder.name.setText(nameList.get(listPosition));

        holder.itemView.setOnClickListener(v -> {
            if (listPosition == 0) {
                Constant.categoryId = "10021";
            } else {
                Constant.categoryId = "10022";
            }
            Intent intent = new Intent(context, AllTenderActivity.class);
            context.startActivity(intent);
            Animatoo.animateFade(context);
        });


    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView img;
        final TextView name;


        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);

        }

        @Override
        public void onClick(View view) {
        }
    }
}
