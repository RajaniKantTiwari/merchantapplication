package com.app.merchant.ui.dashboard.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.app.merchant.R;
import com.app.merchant.databinding.SearchRowItemBinding;
import com.app.merchant.network.response.UserResponse;
import com.app.merchant.network.response.UserSearchResponse;
import com.app.merchant.utility.CommonUtility;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private final LayoutInflater mInflater;
    private final ArrayList<UserSearchResponse> userList;
    private SearchRowItemBinding mBinding;
    private SearchListener searchListener;

    public interface SearchListener {

        void onSearchItemClicked(int adapterPosition);
    }

    public SearchAdapter(AppCompatActivity activity, ArrayList<UserSearchResponse> merchantList, SearchListener searchListener) {
        mInflater = LayoutInflater.from(activity);
        this.searchListener = searchListener;
        this.userList = merchantList;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding = DataBindingUtil.inflate(mInflater, R.layout.search_row_item, parent, false);
        return new SearchViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        if (CommonUtility.isNotNull(userList) && userList.size() > position) {
            holder.setData(userList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return CommonUtility.isNotNull(userList) ? userList.size() : 0;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private SearchRowItemBinding itemView;

        public SearchViewHolder(SearchRowItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
            itemView.tvSearchItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (CommonUtility.isNotNull(searchListener)) {
                searchListener.onSearchItemClicked(getAdapterPosition());
            }
        }
        public void setData(UserSearchResponse merchantResponse) {
            itemView.setSearchData(merchantResponse);
        }
    }
}
