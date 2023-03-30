package com.user.order.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.user.order.R;
import com.user.order.listeners.ItemClickListener2;
import com.user.order.model.favorites.FavoritesData;
import com.user.order.ui.activivtes.shop.ShopDetailsActivity;
import com.user.order.utils.Const;
import com.whinc.widget.ratingbar.RatingBar;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.OrdersHolder> {

    private Activity activity;
    private List<FavoritesData> listFavorites;
    private LayoutInflater inflater;
    private ItemClickListener2 listener;

    public FavoritesAdapter(Activity activity, List<FavoritesData> listFavorites, ItemClickListener2 listener) {
        this.activity = activity;
        this.listFavorites = listFavorites;
        inflater = LayoutInflater.from(activity);
        this.listener = listener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<FavoritesData> listFavorites) {
        this.listFavorites = listFavorites;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrdersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrdersHolder(inflater.inflate(R.layout.row_favorites, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersHolder holder, int position) {

        FavoritesData favorite = listFavorites.get(position);

        if (favorite.getShop() != null) {

            Glide.with(activity).load(favorite.getShop().getLogoUrl()).placeholder(R.drawable.logo).into(holder.civShopImage);

            holder.tvShopName.setText(favorite.getShop().getName());
            holder.tvShopAddress.setText(favorite.getShop().getAddress());


            if (favorite.getShop().getIsFavorite().equals("1"))
                Glide.with(activity).load(R.drawable.ic_icon_added_favorite).into(holder.ivFavorite);
            else
                Glide.with(activity).load(R.drawable.ic_icon_add_favorite).into(holder.ivFavorite);

            holder.ratingBar.setCount((int) Double.parseDouble(favorite.getShop().getRate()));

            double value = Double.parseDouble(favorite.getShop().getRate());
            value = Double.parseDouble(new DecimalFormat("#.#").format(value));
            holder.tvRate.setText(value + "");

            holder.ivFavorite.setOnClickListener(View -> {listener.onItemClick(favorite.getShop().getId());});

            holder.itemView.setOnClickListener(View->{
                activity.startActivity(new Intent(activity, ShopDetailsActivity.class)
                        .putExtra(Const.KEY_SHOP, listFavorites.get(position).getShop()));
            });
        }

    }

    @Override
    public int getItemCount() {
        return listFavorites.size();
    }

    static class OrdersHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.civ_shop_image)
        CircleImageView civShopImage;
        @BindView(R.id.tv_shop_name)
        TextView tvShopName;
        @BindView(R.id.tv_shop_address)
        TextView tvShopAddress;
        @BindView(R.id.iv_favorite)
        ImageView ivFavorite;
        @BindView(R.id.ratingBar)
        RatingBar ratingBar;
        @BindView(R.id.tv_rate)
        TextView tvRate;

        public OrdersHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}