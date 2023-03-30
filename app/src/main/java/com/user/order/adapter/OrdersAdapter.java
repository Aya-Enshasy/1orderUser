package com.user.order.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.user.order.R;
import com.user.order.listeners.ItemClickListener;
import com.user.order.model.order.OneOrderData;
import com.user.order.ui.activivtes.auth.LoginActivity;
import com.user.order.ui.activivtes.auth.SignUpActivity;
import com.user.order.ui.activivtes.orders.OrderDetailsActiviy;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersHolder> {

    private Activity activity;
    private List<OneOrderData> listOrders;
    private LayoutInflater inflater;
    Dialog login_dialog;

    public OrdersAdapter(Activity activity, List<OneOrderData> listOrders) {
        this.activity = activity;
        this.listOrders = listOrders;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public OrdersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrdersHolder(inflater.inflate(R.layout.row_orders, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull OrdersHolder holder, int position) {

        OneOrderData order = listOrders.get(position);
        if (order != null){

            if(order.getShop().getLogoUrl() != null)
                Glide.with(activity).load(order.getShop().getLogoUrl()).placeholder(R.drawable.app_logo).into(holder.civShopImage);

            String orderPrice = new StringBuilder()
                    .append(order.getTotal())
                    .append(" ")
                    .append(HelperMethods.getCountrySettings(activity).getCurrencyCode())
                    .toString();

             holder.tvInvoiceNumber.setText(new StringBuilder().append("#").append(order.getInvoiceNumber()));
            holder.tvOrderDate.setText(HelperMethods.getDate(activity, Long.parseLong(order.getCreatedTimestamp())));
            holder.tvOrderType.setText(order.getOrderType());
            holder.tvShopName.setText(order.getShop().getName());
            holder.tvProductPrice.setText(orderPrice);
            holder.tvOrderStatus.setText(order.getStatusTranslation());
            holder.from.setText(order.getTypeOfReceive());

            if (order.getStatus().equals("cancelled")){
                holder.tvOrderStatus.setTextColor(activity.getColor(R.color.red));
            }else  if (order.getStatus().equals("delivered")||order.getStatus().equals("ready")){
                holder.tvOrderStatus.setTextColor(activity.getColor(R.color.green));
            }else    if (order.getStatus().equals("pending")||order.getStatus().equals("preparing")){
                holder.tvOrderStatus.setTextColor(activity.getColor(R.color.yellow));
            }else {
                holder.tvOrderStatus.setTextColor(activity.getColor(R.color.colorPrimary));
            }

            if (HelperMethods.getUserToken(activity)==null){
                holder.setItemClickListener((view, pos) -> {
                    loaderDialog();
                });
            }else {
                holder.setItemClickListener((view, pos) -> {
                    activity.startActivity(new Intent(activity, OrderDetailsActiviy.class)
                            .putExtra(Const.KEY_ORDER, order));
                });

            }

        }




    }

    protected void loaderDialog() {
        login_dialog = new Dialog(activity);
        login_dialog.setContentView(R.layout.login_dialog);
        login_dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        login_dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
        login_dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        Button login = login_dialog.findViewById(R.id.btn_login);
        Button signup = login_dialog.findViewById(R.id.btn_signup);

        login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, LoginActivity.class));
                activity.finish();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, SignUpActivity.class));
                activity.finish();
            }
        });

        login_dialog.show();
    }


    @Override
    public int getItemCount() {
        return listOrders.size();
    }

    static class OrdersHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.civ_shop_image)
        CircleImageView civShopImage;
        @BindView(R.id.tv_invoice_number)
        TextView tvInvoiceNumber;
        @BindView(R.id.tv_order_date)
        TextView tvOrderDate;
        @BindView(R.id.tv_shop_name)
        TextView tvShopName;
        @BindView(R.id.tv_order_type)
        TextView tvOrderType;
        @BindView(R.id.tv_order_status)
        TextView tvOrderStatus;
        @BindView(R.id.tv_product_price)
        TextView tvProductPrice;

        @BindView(R.id.from)
        TextView from;

        private ItemClickListener itemClickListener;

        public OrdersHolder(@NonNull View itemView) {
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
