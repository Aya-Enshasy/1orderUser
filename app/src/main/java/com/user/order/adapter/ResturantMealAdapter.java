package com.user.order.adapter;

import android.annotation.SuppressLint;
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
import com.user.order.model.shop.meals.MealsData;
import com.user.order.ui.activivtes.productDetails.ProductDetailsActivity;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResturantMealAdapter extends RecyclerView.Adapter<ResturantMealAdapter.ResturantSandwichHolder> {

    private Activity activity;
    private List<MealsData> listMeals;
    private LayoutInflater inflater;

    public ResturantMealAdapter(Activity activity, List<MealsData> listMeals) {
        this.activity = activity;
        this.listMeals = listMeals;
        inflater = LayoutInflater.from(activity);
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<MealsData> listMeals) {
        this.listMeals = listMeals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ResturantSandwichHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResturantSandwichHolder(inflater.inflate(R.layout.row_resturant_sandwich, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ResturantSandwichHolder holder, int position) {

        MealsData meals = listMeals.get(position);
        if (meals != null) {

            if (meals.getImageUrl() != null)
                Glide.with(activity).load(meals.getImageUrl()).placeholder(R.drawable.app_logo).into(holder.ivMealImage);

            holder.tvMealName.setText(meals.getName());

            String mealPrice = new StringBuilder()
                    .append(meals.getPrice())
                    .append(" ")
                    .append(HelperMethods.getCurrency(activity))
                    .toString();
            holder.tvMealPrice.setText(mealPrice);

            holder.itemView.setOnClickListener(View -> {
                activity.startActivity(new Intent(activity, ProductDetailsActivity.class)
                        .putExtra(Const.KEY_MEAL_SHOP, meals));
            });

        }


    }

    @Override
    public int getItemCount() {
        return listMeals.size();
    }

    static class ResturantSandwichHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_sandwich_image)
        ImageView ivMealImage;
        @BindView(R.id.tv_sandwich_name)
        TextView tvMealName;
        @BindView(R.id.tv_sandwich_price)
        TextView tvMealPrice;

        private ItemClickListener itemClickListener;

        public ResturantSandwichHolder(@NonNull View itemView) {
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
