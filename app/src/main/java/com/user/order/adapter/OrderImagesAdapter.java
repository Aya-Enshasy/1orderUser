package com.user.order.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.user.order.R;
import com.user.order.model.ImageData;
import com.user.order.model.orders.publicOrders.Attachment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderImagesAdapter extends RecyclerView.Adapter<OrderImagesAdapter.OrderImagesHolder> {
    private static final String TAG = OrderImagesAdapter.class.getSimpleName();

    private Activity activity;
    private List<Attachment> listImages;
    private int layoutRes;
    private LayoutInflater inflater;

    public OrderImagesAdapter(Activity activity, List<Attachment> listImages, int layoutRes) {
        this.activity = activity;
        this.listImages = listImages;
        this.layoutRes = layoutRes;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public OrderImagesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderImagesHolder(inflater.inflate(layoutRes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderImagesHolder holder, int position) {
        Attachment image = listImages.get(position);
        if (image != null){
            Glide.with(activity).load( listImages.get(position).getImageUrl()).placeholder(R.drawable.app_logo).into(holder.rivOrderImage);
            Log.e("TAG",  listImages.get(position).getImage()+"");
        }



    }

    @Override
    public int getItemCount() {
        return listImages.size();
    }

    static class OrderImagesHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_order_image)
        RoundedImageView rivOrderImage;

        public OrderImagesHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
