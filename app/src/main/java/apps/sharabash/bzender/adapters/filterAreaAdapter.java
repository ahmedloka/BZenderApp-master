package apps.sharabash.bzender.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import apps.sharabash.bzender.R;
import apps.sharabash.bzender.interfaces.RecyclerViewButtonClickListener;
import de.hdodenhof.circleimageview.CircleImageView;


public class filterAreaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener,View.OnLongClickListener {

    private ArrayList<filterAreaModelRecycler> dataSet;

    private View.OnClickListener itemListener;
    private RecyclerViewButtonClickListener btnListener;

    public filterAreaAdapter(ArrayList<filterAreaModelRecycler> data, Context context) {
        this.dataSet = data;
        Context mContext = context;
        int total_types = dataSet.size();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }


    static class TextTypeViewHolder extends RecyclerView.ViewHolder {



        final CardView cardView;
        final TextView CitytName;
        CircleImageView merchantImage;
        CircleImageView categoryLogo;
        TextView categoryName;
        TextView fristDesc;
        String id;




        TextTypeViewHolder(View itemView) {
            super(itemView);


            this.cardView = itemView.findViewById(R.id.card_view);


            this.CitytName   = itemView.findViewById(R.id.CitytName);


        }

    }







    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

        View view;

                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_recycler_row, parent, false);
                return new TextTypeViewHolder(view);




    }


    @Override
    public void onBindViewHolder(@NotNull final RecyclerView.ViewHolder holder, final int listPosition) {


        if (itemListener != null) {
            holder.itemView.setOnClickListener(itemListener);
        }
        if (btnListener != null) {

        }
        filterAreaModelRecycler object = dataSet.get(listPosition);

        if (object != null)
        {
                  //   setImg(object.MerchantLogo, ((TextTypeViewHolder) holder).merchantImage);
                    ((TextTypeViewHolder) holder).CitytName.setText(object.CityName);
                    String id = dataSet.get(listPosition).flag;

//                     setImg(object.categoryLogo, ((TextTypeViewHolder) holder).categoryLogo);
//                    ((TextTypeViewHolder) holder).categoryName.setText(object.categoryname);
//                    ((TextTypeViewHolder) holder).fristDesc.setText(object.fristDesc);


        }
    }

    public void update(ArrayList<filterAreaModelRecycler> list )
    {
        this.dataSet=list;
        this.notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setOnItemClickListener(View.OnClickListener clickListener) {
        this.itemListener = clickListener;
    }

    public void setBtnLocationListener(RecyclerViewButtonClickListener listener) {
        this.btnListener = listener;
    }

}
