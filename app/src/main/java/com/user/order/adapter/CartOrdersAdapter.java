package com.user.order.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.user.order.R;
import com.user.order.databinding.RowCartOrderBinding;
import com.user.order.listeners.CartExtraItemsClickListener;
import com.user.order.listeners.PriceListener;
import com.user.order.listeners.RefreshInterface;
import com.user.order.model.cart1.CartOrder;
import com.user.order.model.shop.meals.ExtraItem;
import com.user.order.ui.activivtes.cart.CartActivity;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import java.util.List;

public class CartOrdersAdapter extends RecyclerView.Adapter<CartOrdersAdapter.CartOrdersHolder> {

    private Activity activity;
    private List<CartOrder> list;
    private LayoutInflater inflater;
    private double totalPrice = 0.0, totalPrice1 = 0.0, qyt = 0.0, pr = 0.0, extraTotalPrice = 0;
    private int qun = 0, pt = 0;
    PriceListener listener;
    RefreshInterface refreshInterface;
    private CartExtraItemsClickListener cartExtraItemsClickListener;
    double price = 0;

    public CartOrdersAdapter(Activity activity, List<CartOrder> list, PriceListener listener, CartExtraItemsClickListener cartExtraItemsClickListener, RefreshInterface refreshInterface) {
        this.activity = activity;
        this.list = list;
        inflater = LayoutInflater.from(activity);
        this.listener = listener;
        this.refreshInterface = refreshInterface;
        this.cartExtraItemsClickListener = cartExtraItemsClickListener;
    }

    @NonNull
    @Override
    public CartOrdersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowCartOrderBinding binding = RowCartOrderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CartOrdersAdapter.CartOrdersHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartOrdersHolder holder, @SuppressLint("RecyclerView") int pos) {
        holder.binding.tvProductName.setText(list.get(pos).getProductName());
        holder.binding.tvQuantity.setText("Quantity : " + list.get(pos).getQuantity());
        if (list.get(pos).getExtraItems() != null) {
            price = (list.get(pos).getProductPrice() + list.get(pos).getExtraTotalPrice()) * list.get(pos).getQuantity();
            holder.binding.tvMealPrice.setText(price + " " + HelperMethods.getCurrency(activity).toString());

        } else {
            price = list.get(pos).getProductPrice() * list.get(pos).getQuantity();
            holder.binding.tvMealPrice.setText(price + " " + HelperMethods.getCurrency(activity).toString());

        }
        Glide.with(activity).load(list.get(pos).getProductImage()).placeholder(R.drawable.app_logo).into(holder.binding.ivSandwichImage);

        for (int i = 0; i < 1; i++) {
            totalPrice += price;
            qyt += list.get(pos).getQuantity();
        }

        PreferencesManager.setStringPreferences("size", list.size() + "");

        if (list.get(pos).getExtraItems() == null)
            holder.binding.tvExtraItems.setVisibility(View.GONE);
        else {
            holder.binding.tvExtraItems.setVisibility(View.VISIBLE);

            holder.binding.tvExtraItems.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartExtraItemsClickListener.onExtraItemClick(list.get(pos).getExtraItems(), pos);
                }
            });

        }

        listener.onItemClick(totalPrice, qyt, pr);

        holder.binding.remove.setOnClickListener(position -> {
            list.remove(pos);
            notifyDataSetChanged();
            Gson gson = new Gson();
            String lis = gson.toJson(list);
            PreferencesManager.setStringPreferences(Const.KEY_ORDER_LIST, lis);
            if (list.isEmpty() || lis.equals("")) {
                activity.finish();
            }


            refreshInterface.onItemClick(true, totalPrice, qyt, pr);


        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    static class CartOrdersHolder extends RecyclerView.ViewHolder {


        RowCartOrderBinding binding;

        public CartOrdersHolder(RowCartOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
