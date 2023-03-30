package com.user.order.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.user.order.databinding.ExtrasItemBinding;
import com.user.order.listeners.PriceListener;
import com.user.order.model.shop.meals.ExtraItem;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import java.util.List;

public class ExtrasOrdersAdapter1 extends RecyclerView.Adapter<ExtrasOrdersAdapter1.viewHolder> {
    private double totalPrice  = 0.0,qyt  = 0.0;
    PriceListener listener;
    Context context;
    private List<ExtraItem> list ;
    public ExtrasOrdersAdapter1(Context context, List<ExtraItem> list, PriceListener listener) {
        this.context=context;
        this.list=list;
        this.listener=listener;
    }



    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<ExtraItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ExtrasItemBinding binding = ExtrasItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        double price=Double.parseDouble(list.get(position).getPrice()) *Double.parseDouble(list.get(position).getQty());

        holder.binding.foodName.setText(list.get(position).getName());
        holder.binding.foodAmount.setText(list.get(position).getQty()+"");
        holder.binding.price.setText(price+" "+ HelperMethods.getCurrency((Activity) context));

        for(int i=0; i<1; i++) {
            totalPrice += price;
            qyt += Double.parseDouble(list.get(position).getQty());
            listener.onItemClick(price,qyt,0.0);
        }

    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }


    public class viewHolder extends RecyclerView.ViewHolder{
        ExtrasItemBinding binding;
        public viewHolder(ExtrasItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }


}
