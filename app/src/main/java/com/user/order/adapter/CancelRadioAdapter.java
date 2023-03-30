package com.user.order.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.user.order.R;
import com.user.order.databinding.RadioBinding;
import com.user.order.listeners.RadioListener;
import com.user.order.model.cancel.CancelOrderReasons;

import java.util.List;

public class CancelRadioAdapter extends RecyclerView.Adapter<CancelRadioAdapter.viewHolder> {

    Context context;
    private List<CancelOrderReasons> list;
    public int mSelectedItem = -1;
    RadioListener listener;

    public CancelRadioAdapter(Context context, List<CancelOrderReasons> list,RadioListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<CancelOrderReasons> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CancelRadioAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RadioBinding binding = RadioBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CancelRadioAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.radioButton.setText(list.get(position).getReason());

        if (mSelectedItem == position) {
            holder.binding.radioButton.setChecked(true);
        } else {
            holder.binding.radioButton.setChecked(false);
        }

        holder.binding.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectedItem=position ;
                notifyDataSetChanged();
                listener.onClick(list.get(position).getId());
            }
        });


        }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }


     class viewHolder extends RecyclerView.ViewHolder {
        RadioBinding binding;

        public viewHolder(RadioBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }


}
