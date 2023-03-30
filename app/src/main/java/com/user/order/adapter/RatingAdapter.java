package com.user.order.adapter;

import android.app.Activity;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.user.order.R;
import com.user.order.databinding.RowCartOrderBinding;
import com.user.order.databinding.RowRatingBinding;
import com.user.order.model.rating.Ratings;
import com.user.order.model.rating.RatingsData;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.RatingHolder> {

    private Activity activity;
    private List<RatingsData> listRating;
    private LayoutInflater inflater;

    public RatingAdapter(Activity activity, List<RatingsData> listRating) {
        this.activity = activity;
        this.listRating = listRating;
        inflater = LayoutInflater.from(activity);
    }

    public void setData(List<RatingsData> listRating) {
        this.listRating = listRating;
        notifyDataSetChanged();
    }

    public void addToList(List<RatingsData> listRating) {
        listRating.addAll(listRating);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RatingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowRatingBinding binding = RowRatingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RatingAdapter.RatingHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull RatingHolder holder, int position) {
        holder.binding.tvRateContent.setText(listRating.get(position).getReview());
        if (listRating.get(position).getUser()!=null) {
            holder.binding.tvUserName.setText(listRating.get(position).getUser().getName());
            Glide.with(activity).load(listRating.get(position).getUser().getAvatarUrl()).placeholder(R.drawable.ic_user_defult).into(holder.binding.civUser);
        }

        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(Long.parseLong(listRating.get(position).getCreatedTimestamp()) * 1000L);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        holder.binding.tvRateDate.setText(date);

        double value=Double.parseDouble(listRating.get(position).getRate());
        value =Double.parseDouble(new DecimalFormat("#.#").format(value));
        holder.binding.tvRate.setText(value+ "");
        holder.binding.ratingBar.setRating(Float.parseFloat(listRating.get(position).getRate()));



    }

    @Override
    public int getItemCount() {
        return listRating!=null?listRating.size():0;
    }

    static class RatingHolder extends RecyclerView.ViewHolder {

        RowRatingBinding binding;

        public RatingHolder(RowRatingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
