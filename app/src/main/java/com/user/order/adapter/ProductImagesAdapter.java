package com.user.order.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.user.order.R;
import com.user.order.model.ImageMeal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductImagesAdapter extends RecyclerView.Adapter<ProductImagesAdapter.ProductImagesHolder> {

    private Activity activity;
    private List<ImageMeal> listImages;
    LayoutInflater inflater;
    private ViewPager2 viewPager;

    public ProductImagesAdapter(Activity activity, List<ImageMeal> listImages, ViewPager2 viewPager) {
        this.activity = activity;
        this.listImages = listImages;
        this.viewPager = viewPager;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public ProductImagesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductImagesHolder(inflater.inflate(R.layout.row_product_images, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductImagesHolder holder, int position) {

        ImageMeal image = listImages.get(position);

                Glide.with(activity).load(image.getImageUrl()).placeholder(R.drawable.app_logo).into(holder.ivProductImage);

    }

    @Override
    public int getItemCount() {
        return listImages != null ? listImages.size() : 0;
    }

    static class ProductImagesHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_product_image)
        ImageView ivProductImage;

        public ProductImagesHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
