package com.library.apple.scify;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class ChatAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_MESSAGE_SENT = 0;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 1;
    private List<ModelChat> list;

    public ChatAdapter(List<ModelChat> list){
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {

        ModelChat modelChat = list.get(position);

        if(modelChat.getUser_type()==0){
            return VIEW_TYPE_MESSAGE_SENT;
        }
        else{
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;

        if (i == VIEW_TYPE_MESSAGE_SENT) {

            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_mess_sender,viewGroup,false);
            SentViewHolder sentViewHolder = new SentViewHolder(view, list);
            return sentViewHolder;

        }
        else if (i == VIEW_TYPE_MESSAGE_RECEIVED) {

            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_mess_receiver,viewGroup,false);
            ReceiverViewHolder receiverViewHolder = new ReceiverViewHolder(view, list);
            return receiverViewHolder;

        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ModelChat modelChat = list.get(i);

        switch (viewHolder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentViewHolder)viewHolder).tv_sender.setText(modelChat.getMess());
                break;

            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceiverViewHolder)viewHolder).tv_receiver.setText(modelChat.getMess());
                break;
        }

    }

    @Override
    public int getItemCount() {
        return list == null? 0: list.size();
    }

    public static class SentViewHolder extends RecyclerView.ViewHolder {

        TextView tv_sender;
        List<ModelChat> list;

        public SentViewHolder(@NonNull View itemView, List<ModelChat> list) {
            super(itemView);
            this.list = list;

            tv_sender = (TextView)itemView.findViewById(R.id.tv_sender);
        }
    }

    public static class ReceiverViewHolder extends RecyclerView.ViewHolder {

        TextView tv_receiver;
        List<ModelChat> list;

        public ReceiverViewHolder(@NonNull View itemView, List<ModelChat> list) {
            super(itemView);
            this.list = list;

            tv_receiver = (TextView)itemView.findViewById(R.id.tv_receiver);

        }
    }

}
