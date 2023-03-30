package com.user.order.listeners;

import android.widget.ImageView;

import com.user.order.model.shop.Shop;

public interface AddRemoveFromFavoriteListener {

    void onAddRemoveFromFavorite(Shop shop, ImageView imageView, int position);
}
