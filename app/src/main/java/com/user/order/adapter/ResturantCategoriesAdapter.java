package com.user.order.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.user.order.R;
import com.user.order.listeners.ItemClickListener;
import com.user.order.listeners.ItemClickListener2;
import com.user.order.listeners.SelectCategoryListener;
import com.user.order.model.categories.CategoryData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResturantCategoriesAdapter extends RecyclerView.Adapter<ResturantCategoriesAdapter.ResturantCategoriesHolder> {

    private Activity activity;
    private List<CategoryData> listCategories;
    private LayoutInflater inflater;
    private CardView itemSelected = null;
    private TextView tvItemSelected = null;
    int row_index = 0;
    ItemClickListener2 listener;
    private SelectCategoryListener categoryListener;


    public ResturantCategoriesAdapter(Activity activity, List<CategoryData> listCategories, SelectCategoryListener categoryListener,ItemClickListener2 listener) {
        this.activity = activity;
        this.listCategories = listCategories;
        this.listener = listener;
        this.categoryListener = categoryListener;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public ResturantCategoriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResturantCategoriesHolder(inflater.inflate(R.layout.row_resturant_categories, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ResturantCategoriesHolder holder, @SuppressLint("RecyclerView") int position) {

        CategoryData category = listCategories.get(position);

        if (category != null) {
            Log.e("/()",listCategories.get(position).getId()+"");

            Glide.with(activity).load(category.getImageUrl()).placeholder(R.drawable.app_logo).into(holder.ivCategory);
            holder.tvCategoryName.setText(listCategories.get(position).getName());

            if (row_index == position) {
                holder.tvCategoryName.setTextColor(activity.getColor(R.color.colorAccent));
                holder.cardCategory.setBackgroundResource(R.drawable.bg_category_selected);

            } else {
                holder.tvCategoryName.setTextColor(activity.getColor(R.color.colorDarkBlue));
                holder.cardCategory.setBackgroundResource(R.drawable.bg_category_unselected);
            }

            listener.onItemClick(category.getId());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    row_index = position;
                    notifyDataSetChanged();
                    categoryListener.onSelectCategory(category, position);
                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return listCategories.size();
    }

    static class ResturantCategoriesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.card_category)
        CardView cardCategory;
        @BindView(R.id.iv_category)
        ShapeableImageView ivCategory;
        @BindView(R.id.tv_category_name)
        TextView tvCategoryName;

        private ItemClickListener itemClickListener;

        public ResturantCategoriesHolder(@NonNull View itemView) {
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
