package com.user.order.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.user.order.R;
import com.user.order.listeners.AddRemoveFromFavoriteListener;
import com.user.order.listeners.ItemClickListener;
import com.user.order.model.shop.Shop;
import com.user.order.ui.activivtes.rating.RatingActivity;
import com.user.order.ui.activivtes.shop.ShopDetailsActivity;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.ShopHolder> {

    private Activity activity;
    private List<Shop> listShops;
    private LayoutInflater inflater;
    private AddRemoveFromFavoriteListener favoriteListener;

    public ShopsAdapter(Activity activity, List<Shop> listShops, AddRemoveFromFavoriteListener favoriteListener) {
        this.activity = activity;
        this.listShops = listShops;
        this.favoriteListener = favoriteListener;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public ShopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShopHolder(inflater.inflate(R.layout.row_shop, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ShopHolder holder, int position) {

        Shop shop = listShops.get(position);
        if (shop != null) {

            if (shop.getLogoUrl() != null)
                Glide.with(activity).load(shop.getLogoUrl()).placeholder(R.drawable.app_logo).into(holder.civShopImage);

            holder.tvShopName.setText(shop.getName());
            holder.tvShopAddress.setText(shop.getAddress());

            if (shop.getIsOpen().equals("1")) {
                holder.tvShopStatus.setText(activity.getString(R.string.open));
            } else {
                holder.tvShopStatus.setText(activity.getString(R.string.close));
                holder.tvShopStatus.setTextColor(activity.getColor(R.color.red));

            }
            if (shop.getIsFavorite().equals("1"))
                Glide.with(activity).load(R.drawable.ic_fav).into(holder.ivFavorite);
            else
                Glide.with(activity).load(R.drawable.ic_notfav).into(holder.ivFavorite);

            holder.ratingBar.setRating(Float.parseFloat(shop.getRate()));
            holder.tvRate.setText(HelperMethods.formatTotal(Double.parseDouble(shop.getRate())));

        }


        holder.setItemClickListener((view, pos) -> {
            activity.startActivity(new Intent(activity, ShopDetailsActivity.class)
                    .putExtra(Const.KEY_SHOP, shop));
        });

        holder.rating.setOnClickListener(v -> {
            activity.startActivity(new Intent(activity, RatingActivity.class)
                    .putExtra(Const.KEY_Rate_id, shop.getId())
                    .putExtra("fromShop", "fromShop")
                    .putExtra(Const.KEY_SHOP, shop));
            PreferencesManager.setStringPreferences(Const.KEY_Rate_id, shop.getId()+"");
        });

        holder.ivFavorite.setOnClickListener(v -> {
            favoriteListener.onAddRemoveFromFavorite(shop, holder.ivFavorite, position);
        });

    }

    @Override
    public int getItemCount() {
        return listShops.size();
    }


    static class ShopHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.civ_shop_image)
        CircleImageView civShopImage;
        @BindView(R.id.tv_shop_name)
        TextView tvShopName;
        @BindView(R.id.iv_favorite)
        ImageView ivFavorite;
        @BindView(R.id.tv_shop_address)
        TextView tvShopAddress;
        @BindView(R.id.tv_shop_status)
        TextView tvShopStatus;
        @BindView(R.id.ratingBar)
        AppCompatRatingBar ratingBar;

        @BindView(R.id.tv_rate)
        TextView tvRate;
        @BindView(R.id.rating)
        ConstraintLayout rating;

        private ItemClickListener itemClickListener;

        public ShopHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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
