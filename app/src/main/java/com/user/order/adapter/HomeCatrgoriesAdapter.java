package com.user.order.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.user.order.R;
import com.user.order.listeners.ItemClickListener;
import com.user.order.model.categories.CategoryData;
import com.user.order.ui.activivtes.shop.ShopsActivity;
import com.user.order.utils.Const;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeCatrgoriesAdapter extends RecyclerView.Adapter<HomeCatrgoriesAdapter.HomeCatrgoriesHolder> {

    private Activity activity;
    private List<CategoryData> listCategories;
    private LayoutInflater inflater;

    public HomeCatrgoriesAdapter(Activity activity, List<CategoryData> listCategories) {
        this.activity = activity;
        this.listCategories = listCategories;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public HomeCatrgoriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeCatrgoriesHolder(inflater.inflate(R.layout.row_home_categories, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCatrgoriesHolder holder, int position) {

        CategoryData category = listCategories.get(position);
        if (category != null){

            holder.tvCategoryName.setText(category.getName());
            Glide.with(activity).load(category.getImageUrl()).placeholder(R.drawable.app_logo).into(holder.ivCategoryImage);

            holder.setItemClickListener((view, pos) -> {
                activity.startActivity(new Intent(activity, ShopsActivity.class)
                        .putExtra(Const.KEY_CATEGORY, category));
            });
        }

    }

    @Override
    public int getItemCount() {
        return listCategories.size();
    }

    static class HomeCatrgoriesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_cateogry_image)
        ImageView ivCategoryImage;
        @BindView(R.id.tv_category_name)
        TextView tvCategoryName;

        private ItemClickListener itemClickListener;

        public HomeCatrgoriesHolder(@NonNull View itemView) {
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
