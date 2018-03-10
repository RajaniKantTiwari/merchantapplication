package com.app.merchant.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.app.merchant.R;
import com.app.merchant.databinding.HelpSupportRowBinding;
import com.app.merchant.utility.CommonUtility;


/**
 * Created by ashok on 25/12/17.
 */

public class HelpSupportAdapter extends RecyclerView.Adapter<HelpSupportAdapter.CartHolder> {
    private final LayoutInflater mInflater;
    private HelpSupportRowBinding mBinding;
    private HelpSupportListener listener;
    public interface HelpSupportListener{

        void itemClicked(int adapterPosition, boolean isChecked);
    }
    public HelpSupportAdapter(AppCompatActivity activity, HelpSupportListener listener){
        mInflater=LayoutInflater.from(activity);
        this.listener=listener;
    }
    @Override
    public CartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding=DataBindingUtil.inflate(mInflater, R.layout.help_support_row, parent, false);
        return new CartHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(CartHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
    class CartHolder extends RecyclerView.ViewHolder{
       public CartHolder(HelpSupportRowBinding itemView) {
           super(itemView.getRoot());
           itemView.radioHelp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   if(CommonUtility.isNotNull(listener)){
                       listener.itemClicked(getAdapterPosition(),isChecked);
                   }
               }
           });

       }
   }
}
