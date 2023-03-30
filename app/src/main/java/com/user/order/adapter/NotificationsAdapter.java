package com.user.order.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.user.order.R;
import com.user.order.model.notifications.Data;
import com.user.order.ui.activivtes.chat.ChatActivity;
import com.user.order.ui.activivtes.home.publicOrders.PublicOrderDetailsActivity;
import com.user.order.ui.activivtes.orders.OrderDetailsActiviy;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationsHolder> {

    private Activity activity;
    private List<Data> listNotifications;
    private LayoutInflater inflater;

    public NotificationsAdapter(Activity activity, List<Data> listNotifications) {
        this.activity = activity;
        this.listNotifications = listNotifications;
        inflater = LayoutInflater.from(activity);
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Data> listNotifications) {
        this.listNotifications = listNotifications;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NotificationsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationsHolder(inflater.inflate(R.layout.row_notifications, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsHolder holder, int position) {

        Data notification = listNotifications.get(position);
        if (notification != null){
            holder.tvTitle.setText(notification.getNotification().getStatus());
            holder.tvContent.setText(notification.getNotification().getMsg());
            holder.tvDate.setText(HelperMethods.getDate(activity, notification.getCreatedAt()));


            if (notification.getNotification().getType().equals("public") && notification.getNotification().getStatus().equals("new_message")) {
                holder.itemView.setOnClickListener(View->{
                    Intent intent = new Intent(activity, ChatActivity.class);
                    intent.putExtra(Const.KEY_INVOICE_NUMBER,notification.getNotification().getInvoiceNumber() );
                    intent.putExtra(Const.KEY_CLIENT_ID, notification.getNotification().getUserId());
                    intent.putExtra(Const.KEY_ORDER_ID, notification.getNotification().getPublicOrderId());
                    intent.putExtra(Const.KEY_PUBLIC_CHAT, Const.KEY_PUBLIC_CHAT);
                    intent.putExtra(Const.KEY_STATUS, notification.getNotification().getStatus());
                    activity.startActivity(intent);
                });

            }
            else if (notification.getNotification().getType().equals("public")&&notification.getNotification().getStatus().equals("cancelled")){
                holder.itemView.setOnClickListener(View->{
                    activity.startActivity(new Intent(activity, OrderDetailsActiviy.class)
                            .putExtra(Const.KEY_ORDER_ID, notification.getNotification().getPublicOrderId())
                    );

                });

            }
            else if (notification.getNotification().getType().equals("public")){
                holder.itemView.setOnClickListener(View->{
                    activity.startActivity(new Intent(activity, PublicOrderDetailsActivity.class)
                             .putExtra(Const.KEY_ORDER_ID, notification.getNotification().getPublicOrderId())
                    );

                });

            }
            else if (notification.getNotification().getType().equals("1order")&&listNotifications.get(position).getNotification().getStatus()!=null){

                holder.itemView.setOnClickListener(View->{
                    activity.startActivity(new Intent(activity, OrderDetailsActiviy.class)
                             .putExtra(Const.KEY_ORDER_ID, notification.getNotification().getOrderId())
                    );
                });

            }
        }

    }

    @Override
    public int getItemCount() {
        return listNotifications.size();
    }

    static class NotificationsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_notification_title)
        TextView tvTitle;
        @BindView(R.id.tv_notification_content)
        TextView tvContent;
        @BindView(R.id.tv_notification_date)
        TextView tvDate;

        public NotificationsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
