package com.app.merchant.ui.chat;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.app.merchant.R;
import com.app.merchant.databinding.ItemMessageOtherBinding;
import com.app.merchant.databinding.ItemMessageUserBinding;
import com.app.merchant.network.request.ChatMessage;
import com.app.merchant.utility.AppConstants;
import com.app.merchant.utility.CommonUtility;

import java.util.List;


public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater mInflator;
    private final List<ChatMessage> messageList;
    private Activity activity;

    public MessageAdapter(Activity activity, List<ChatMessage> messageList) {
        this.activity = activity;
        mInflator = LayoutInflater.from(activity);
        this.messageList = messageList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == AppConstants.VIEW_TYPE_OTHERS_MESSAGE) {
            ItemMessageOtherBinding itemOtherBinding = DataBindingUtil.inflate(mInflator, R.layout.item_message_other, parent, false);
            return new MessageOthersHolder(itemOtherBinding);
        } else if (viewType == AppConstants.VIEW_TYPE_USER_MESSAGE) {
            ItemMessageUserBinding itemOtherBinding = DataBindingUtil.inflate(mInflator, R.layout.item_message_user, parent, false);
            return new MessageUserHolder(itemOtherBinding);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MessageOthersHolder) {
            ((MessageOthersHolder) holder).setData(position);
        } else {
            ((MessageUserHolder) holder).setData(position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return messageList.get(position).getMessageType();
    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(messageList) ? messageList.size() : 0;
    }

    class MessageUserHolder extends RecyclerView.ViewHolder {
        private ItemMessageUserBinding itemView;
        public MessageUserHolder(ItemMessageUserBinding itemView) {
            super(itemView.getRoot());
            this.itemView=itemView;
        }

        public void setData(int position) {
            if (CommonUtility.isNotNull(messageList) && messageList.size() > position) {
                itemView.setChatMessage(messageList.get(position));
            }
        }
    }

    class MessageOthersHolder extends RecyclerView.ViewHolder {
        private ItemMessageOtherBinding itemView;

        public MessageOthersHolder(ItemMessageOtherBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }

        public void setData(int position) {
            if (CommonUtility.isNotNull(messageList) && messageList.size() > position) {
                itemView.setChatMessage(messageList.get(position));
            }
        }
    }
}