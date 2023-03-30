package com.user.order.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.user.order.R;
import com.user.order.listeners.ItemClickListener;
import com.user.order.model.orders.publicOrders.OrderData;
import com.user.order.ui.activivtes.home.publicOrders.PublicOrderDetailsActivity;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PublicOrdersAdapter extends RecyclerView.Adapter<PublicOrdersAdapter.PublicOrdersHolder> {

    private Activity activity;
    private List<OrderData> listOrders;
    private LayoutInflater inflater;

    public PublicOrdersAdapter(Activity activity, List<OrderData> listOrders) {
        this.activity = activity;
        this.listOrders = listOrders;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public PublicOrdersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PublicOrdersHolder(inflater.inflate(R.layout.row_public_orders, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull PublicOrdersHolder holder, int position) {

        OrderData order = listOrders.get(position);
        if (order != null) {

            String orderPrice = new StringBuilder()
                    .append(order.getTotal())
                    .append(" ")
                    .append(HelperMethods.getCountrySettings(activity).getCurrencyCode())
                    .toString();

            holder.tvInvoiceNumber.setText(new StringBuilder().append("#").append(order.getInvoiceNumber()));
            holder.tvOrderDate.setText(HelperMethods.getDate(activity, Long.parseLong(order.getCreatedTimestamp())));
            holder.tvShopName.setText(order.getStoreName());
            holder.tvOrderPrice.setText(orderPrice);
            holder.tvOrderType.setText(activity.getString(R.string.public_order));

            holder.setItemClickListener((view, pos) -> {
                activity.startActivity(new Intent(activity, PublicOrderDetailsActivity.class)
                        .putExtra(Const.KEY_PUBLIC_ORDER, order));
             });

            if (order.getStatus().equals("pending")){
                holder.tvOrderStatus.setTextColor(activity.getColor(R.color.yellow));
                holder.tvOrderStatus.setText(order.getStatusTranslation());
            }
            else if (order.getStatus().equals("cancelled")){
                holder.tvOrderStatus.setText(order.getStatusTranslation());
                holder.tvOrderStatus.setTextColor(activity.getColor(R.color.red));
            }
            else if (order.getStatus().equals("in_the_way_to_client")){
                holder.tvOrderStatus.setText(order.getStatusTranslation());
                holder.tvOrderStatus.setTextColor(activity.getColor(R.color.colorBlue));
            }
            else if (order.getPurchaseInvoiceValue().equals("")&&order.getClientPaidInvoice().equals("0")&&order.getStatus().equals("in_the_way_to_store")){
                holder.tvOrderStatus.setText(order.getStatusTranslation());
                holder.tvOrderStatus.setTextColor(activity.getColor(R.color.colorDarkBlue));
            }else if (!order.getPurchaseInvoiceValue().equals("")&&order.getClientPaidInvoice().equals("0")&&order.getStatus().equals("in_the_way_to_store")){
                holder.tvOrderStatus.setText(R.string.confirm_payment);
                holder.tvOrderStatus.setTextColor(activity.getColor(R.color.colorDarkBlue));
            }else if (!order.getPurchaseInvoiceValue().equals("")&&order.getClientPaidInvoice().equals("1")&&order.getStatus().equals("in_the_way_to_store")){
                holder.tvOrderStatus.setText(order.getStatusTranslation());
                holder.tvOrderStatus.setTextColor(activity.getColor(R.color.green));;
            }
           else if (order.getClientDeliverd().equals("0")&&order.getStatus().equals("delivered")){
                holder.tvOrderStatus.setText(R.string.waiting_client);
                holder.tvOrderStatus.setTextColor(activity.getColor(R.color.colorBlue));
            }
           else if (!order.getClientDeliverd().equals("0")&&order.getStatus().equals("delivered")){
                holder.tvOrderStatus.setText(order.getStatusTranslation());
                holder.tvOrderStatus.setTextColor(activity.getColor(R.color.green));
            }


        }


    }

    @Override
    public int getItemCount() {
        return listOrders.size();
    }

    static class PublicOrdersHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_invoice_number)
        TextView tvInvoiceNumber;
        @BindView(R.id.tv_order_date)
        TextView tvOrderDate;
        @BindView(R.id.tv_shop_name)
        TextView tvShopName;
        @BindView(R.id.tv_order_price)
        TextView tvOrderPrice;
        @BindView(R.id.tv_order_type)
        TextView tvOrderType;
        @BindView(R.id.tv_order_status)
        TextView tvOrderStatus;

        private ItemClickListener itemClickListener;

        public PublicOrdersHolder(@NonNull View itemView) {
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
