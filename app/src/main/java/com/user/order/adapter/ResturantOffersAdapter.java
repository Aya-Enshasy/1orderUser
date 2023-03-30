package com.user.order.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.user.order.R;
import com.user.order.databinding.RowResturantOffersBinding;
import com.user.order.listeners.ItemClickListener;
import com.user.order.model.categories.CategoryData;
import com.user.order.model.offers.Offer;
import com.user.order.ui.activivtes.orders.ConfirmPayActivity;
import com.user.order.ui.activivtes.productDetails.ProductDetailsActivity;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import java.util.List;

public class ResturantOffersAdapter extends RecyclerView.Adapter<ResturantOffersAdapter.viewHolder> {

    private Activity activity;
    private List<Offer> list;
    private LayoutInflater inflater;
    private ViewPager2 viewPager;

    public ResturantOffersAdapter(Activity activity, List<Offer> list, ViewPager2 viewPager) {
        this.activity = activity;
        this.list = list;
        this.viewPager = viewPager;
        inflater = LayoutInflater.from(activity);
    }


    @NonNull
    @Override
    public ResturantOffersAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowResturantOffersBinding binding = RowResturantOffersBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Offer offer = list.get(position);
        if (offer!=null){
            Glide.with(activity).load(offer.getImageUrl()).placeholder(R.drawable.app_logo).into(holder.binding.ivCateogryImage);
            holder.binding.tvProductName.setText(offer.getName());
            holder.binding.tvProductPrice.setText(offer.getPrice()+ HelperMethods.getCurrency(activity));

            holder.itemView.setOnClickListener(View-> {
                PreferencesManager.setStringPreferences(Const.KEY_OFFER,offer.getId()+"");
                activity.startActivity(new Intent(activity, ConfirmPayActivity.class));
            });
        }


    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }



    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RowResturantOffersBinding binding;
        private ItemClickListener itemClickListener;

        public viewHolder(RowResturantOffersBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

}
