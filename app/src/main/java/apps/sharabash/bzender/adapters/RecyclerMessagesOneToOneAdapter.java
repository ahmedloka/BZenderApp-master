package apps.sharabash.bzender.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import apps.sharabash.bzender.Models.message.Message;
import apps.sharabash.bzender.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerMessagesOneToOneAdapter extends RecyclerView.Adapter<RecyclerMessagesOneToOneAdapter.AllSpacesViewHolder> {


    private final OnClickHandler onClickHanlder;
    private final List<Message> messageList;

    public RecyclerMessagesOneToOneAdapter(List<Message> messageList, OnClickHandler onClickHanlder) {
        this.onClickHanlder = onClickHanlder;
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public AllSpacesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int layout = R.layout.chat_recycler_item;
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);


        return new AllSpacesViewHolder(rootView);
    }

    public  void addItem(Message message) {
        messageList.add(message);
        notifyItemInserted(messageList.size());
    }

    @Override
    public void onBindViewHolder(@NonNull AllSpacesViewHolder homeViewHolder, int i) {
        Message message = this.messageList.get(i);

        if (Message.MSG_TYPE_RECEIVED.equals(message.getMsgType())) {
            // Show received message in left linearlayout.
            homeViewHolder.mLinearLayoutLeft.setVisibility(LinearLayout.VISIBLE);
            homeViewHolder.mTxtViewLeftMessage.setText(message.getMsgContent());
            // Remove left linearlayout.The value should be GONE, can not be INVISIBLE
            // Otherwise each iteview's distance is too big.
            homeViewHolder.mLinearLayoutRight.setVisibility(LinearLayout.GONE);

        }
        // If the message is a sent message.
        else if (Message.MSG_TYPE_SENT.equals(message.getMsgType())) {
            // Show sent message in right linearlayout.
            homeViewHolder.mLinearLayoutRight.setVisibility(LinearLayout.VISIBLE);
            homeViewHolder.mTxtViewRightMessage.setText(message.getMsgContent());
            // Remove left linearlayout.The value should be GONE, can not be INVISIBLE
            // Otherwise each iteview's distance is too big.
            homeViewHolder.mLinearLayoutLeft.setVisibility(LinearLayout.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }


    public interface OnClickHandler {
        void onClick(int position);
    }

    public class AllSpacesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView mTxtViewRightMessage;
        private final TextView mTxtViewLeftMessage;
        private final LinearLayout mLinearLayoutLeft;
        private final LinearLayout mLinearLayoutRight;
        private CircleImageView mCircleImgLeft, mCircleImgRight;


        AllSpacesViewHolder(@NonNull View itemView) {
            super(itemView);

            mTxtViewLeftMessage = itemView.findViewById(R.id.chat_left_msg_text_view);
            mTxtViewRightMessage = itemView.findViewById(R.id.chat_right_msg_text_view);


            mLinearLayoutLeft = itemView.findViewById(R.id.chat_left_msg_layout);
            mLinearLayoutRight = itemView.findViewById(R.id.chat_right_msg_layout);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            onClickHanlder.onClick(position);
            notifyDataSetChanged();

        }

    }


}
