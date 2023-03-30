package com.user.order.ui.activivtes.orders;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.user.order.databinding.OrderItemsBinding;
import com.user.order.listeners.StringInterface;
import com.user.order.model.order1.Item;
import com.user.order.utils.HelperMethods;

import java.util.List;

public class OrderItemsAdapter  extends RecyclerView.Adapter<OrderItemsAdapter.viewHolder> {

    Context context;
    private List<Item> list ;
    private static StringInterface listener ;

    public OrderItemsAdapter(Context context,List<Item> list,StringInterface listener) {
        this.context=context;
        this.list=list;
        this.listener=listener;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Item> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void addToList(List<Item> myList){
        list.addAll(myList);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public OrderItemsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        OrderItemsBinding binding = OrderItemsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new OrderItemsAdapter.viewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OrderItemsAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.foodName.setText(list.get(position).getName());

        holder.binding.amount.setText(list.get(position).getQty());
        double price = Double.parseDouble(list.get(position).getPrice())*Double.parseDouble(list.get(position).getQty());
        holder.binding.foodPrice.setText(price+" "+ HelperMethods.getCurrency((Activity) context));

        if (list.get(position).getExtraItems()!=null&&list.get(position).getExtraItems().size()==0){
            holder.binding.extraItems.setVisibility(View.GONE);
        }else {
            holder.binding.extraItems.setVisibility(View.VISIBLE);
            holder.binding.extraItems.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(list!=null&&list.size() > 0)
                    listener.onItemClick(list.get(position).getPrice(),list.get(position).getOrderId());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }


    public class viewHolder extends RecyclerView.ViewHolder{
        OrderItemsBinding binding;
        public viewHolder(OrderItemsBinding  binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }


}
