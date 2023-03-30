package com.user.order.ui.activivtes.orders;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.user.order.databinding.ExtrasItemBinding;
import com.user.order.listeners.ItemClickListener2;
import com.user.order.listeners.ItemClickListenerDouble;
import com.user.order.listeners.MyInterface;
import com.user.order.model.order1.Item;
import com.user.order.model.order1.Orders1;
import com.user.order.model.shop.meals.ExtraItem;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import java.util.List;

public class ExtrasOrdersAdapter extends RecyclerView.Adapter<ExtrasOrdersAdapter.viewHolder> {

    Context context;
    private List<Item> list ;
double totalPrice=0;
    ItemClickListenerDouble listener;

    public ExtrasOrdersAdapter(Context context, List<Item> list, ItemClickListenerDouble listener) {
        this.context=context;
        this.list=list;
        this.listener=listener;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Item> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExtrasOrdersAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ExtrasItemBinding binding = ExtrasItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExtrasOrdersAdapter.viewHolder holder, int position) {

        if (list.get(position).getExtraItems()!=null) {

            holder.binding.foodName.setText(list.get(position).getExtraItems().get(position).getName());
            holder.binding.foodAmount.setText(list.get(position).getExtraItems().get(position).getQty());
            holder.binding.price.setText(list.get(position).getExtraItems().get(position).getPrice() + " " + HelperMethods.getCurrency((Activity) context));

            for (int i = 0; i < 1; i++) {
                totalPrice += Double.parseDouble(list.get(position).getExtraItems().get(position).getPrice());
            }

            listener.onItemClick(totalPrice);
        }
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }


    public class viewHolder extends RecyclerView.ViewHolder{
        ExtrasItemBinding binding;
        public viewHolder(ExtrasItemBinding  binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }


}
