package com.user.order.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.user.order.R;
import com.user.order.databinding.ExtrasItemBinding;
import com.user.order.databinding.RowHomeOffersBinding;
import com.user.order.listeners.ItemClickListener;
import com.user.order.model.offers.OfferData;
import com.user.order.ui.activivtes.orders.ConfirmPayActivity;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeOffersAdapter extends RecyclerView.Adapter<HomeOffersAdapter.HomeOffersHolder> {

    private Activity activity;
    private List<OfferData> listOffers;
    private ViewPager2 viewPager;

    public HomeOffersAdapter(Activity activity, List<OfferData> listOffers, ViewPager2 viewPager) {
        this.activity = activity;
        this.listOffers = listOffers;
        this.viewPager = viewPager;
    }

    @NonNull
    @Override
    public HomeOffersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowHomeOffersBinding binding = RowHomeOffersBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new HomeOffersAdapter.HomeOffersHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull HomeOffersHolder holder, int position) {

        OfferData offer = listOffers.get(position);

        if (offer != null) {

            if (offer.getImageUrl() != null)
                Glide.with(activity).load(offer.getImageUrl()).placeholder(R.drawable.app_logo).into(holder.binding.rivOfferImage);
            if (offer.getShop().getLogoUrl() != null)
                Glide.with(activity).load(offer.getShop().getLogoUrl()).placeholder(R.drawable.app_logo).into(holder.binding.civShopImage);

            holder.binding.tvOfferName.setText(offer.getName());
            String price = new StringBuilder()
                    .append(offer.getPrice())
                    .append(" ")
                    .append(HelperMethods.getCurrency(activity))
                    .toString();
            holder.binding.tvOfferPrice.setText(price);
            holder.binding.tvShopName.setText(offer.getShop().getName());

            if (offer.getShop().getIsOpen().equals("")){
                holder.binding.tvShopStatus.setText(activity.getString(R.string.close));
                holder.binding.tvShopStatus.setTextColor(activity.getColor(R.color.red));
            }
            else {
                holder.binding.tvShopStatus.setTextColor(activity.getColor(R.color.colorBlue));
                holder.binding.tvShopStatus.setText(activity.getString(R.string.open));
            }
            holder.setItemClickListener((view, pos) -> {
                activity.startActivity(new Intent(activity, ConfirmPayActivity.class).putExtra(Const.KEY_SHOP,offer.getShop()));
            });
        }


    }

    @Override
    public int getItemCount() {
        return listOffers.size();
    }

    static class HomeOffersHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RowHomeOffersBinding binding;

        private ItemClickListener itemClickListener;

        public HomeOffersHolder(RowHomeOffersBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            itemView.setOnClickListener(this);
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
