package com.user.order.ui.activivtes.chat;

import static com.user.order.ui.activivtes.chat.ChatActivity.userid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.user.order.R;
import com.user.order.databinding.ReceiveMassageBinding;
import com.user.order.databinding.SendMessageBinding;
import com.user.order.model.ListMassageData;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MessageAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<ListMassageData> list;
    public int ITEM_1 = HelperMethods.getCurrentUser((Activity) context).getId();

    public static final int ITEM_2 = 2;


    public MessageAdapter(Context context, List<ListMassageData> list) {
        this.context = context;
        this.list = list;

    }


    class SendMassageViewHolder extends RecyclerView.ViewHolder {
        SendMessageBinding binding;

        public SendMassageViewHolder(SendMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

    class ReceiveMassageViewHolder extends RecyclerView.ViewHolder {
        ReceiveMassageBinding binding;

        public ReceiveMassageViewHolder(ReceiveMassageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int id) {
        if (id == ITEM_2) {
            ReceiveMassageBinding binding = ReceiveMassageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ReceiveMassageViewHolder(binding);

        } else {
            SendMessageBinding binding = SendMessageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new SendMassageViewHolder(binding);
        }
    }

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ListMassageData listMassageData = list.get(position);

        if (listMassageData == null)
            return;

        if (getItemViewType(position) == ITEM_1) {
            // ITEM1;

            //sendMessage
            if (listMassageData.getMsg().equals("")) {
                ((SendMassageViewHolder) holder).binding.sendMessage.setVisibility(View.GONE);
                ((SendMassageViewHolder) holder).binding.time.setVisibility(View.GONE);
            } else {
                ((SendMassageViewHolder) holder).binding.sendMessage.setVisibility(View.VISIBLE);
                ((SendMassageViewHolder) holder).binding.time.setVisibility(View.VISIBLE);
                ((SendMassageViewHolder) holder).binding.sendMessage.setText(listMassageData.getMsg());
            }
            Calendar cal = Calendar.getInstance(Locale.ENGLISH);
            cal.setTimeInMillis(list.get(position).getTime() * 1000L);
            String date = DateFormat.format("hh:mm a", cal).toString();
            ((SendMassageViewHolder) holder).binding.time.setText(date);

            //image
            if (list.get(position).getImg().equals("")) {
                ((SendMassageViewHolder) holder).binding.image.setVisibility(View.GONE);
            } else {
                ((SendMassageViewHolder) holder).binding.image.setVisibility(View.VISIBLE);
                Glide.with(context).load(list.get(position).getImg())
                        .placeholder(R.drawable.logo)
                        .into(((SendMassageViewHolder) holder).binding.image);
            }
        } else {
            // ITEM2;
            if (listMassageData.getMsg().equals("")) {
                ((ReceiveMassageViewHolder) holder).binding.recieveMessage.setVisibility(View.GONE);
                ((ReceiveMassageViewHolder) holder).binding.time.setVisibility(View.GONE);
            } else {
                ((ReceiveMassageViewHolder) holder).binding.recieveMessage.setVisibility(View.VISIBLE);
                ((ReceiveMassageViewHolder) holder).binding.time.setVisibility(View.VISIBLE);
                ((ReceiveMassageViewHolder) holder).binding.recieveMessage.setText(listMassageData.getMsg());
                Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                cal.setTimeInMillis(list.get(position).getTime() * 1000L);
                String date = DateFormat.format("hh:mm a", cal).toString();
                ((ReceiveMassageViewHolder) holder).binding.time.setText(date);
            }
            //image
            if (list.get(position).getImg().equals("")) {
                ((ReceiveMassageViewHolder) holder).binding.image.setVisibility(View.GONE);
            } else {
                ((ReceiveMassageViewHolder) holder).binding.image.setVisibility(View.VISIBLE);
                Glide.with(context).load(list.get(position).getImg()).into(((ReceiveMassageViewHolder) holder).binding.image);
            }


        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        int user_id = HelperMethods.getCurrentUser(context).getId();

        if (list.get(position).getIdSender() == user_id) {
            return ITEM_1;
        } else {
            return ITEM_2;
        }

    }

}



