package com.user.order.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.user.order.R;
import com.user.order.listeners.CountrySelectedItem;
import com.user.order.listeners.ItemClickListener;
import com.user.order.model.country.Country;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryHolder> {

    private Activity activity;
    private List<Country> listCountries;
    private LayoutInflater inflater;
    private CountrySelectedItem countrySelectedItem;
    private AlertDialog dialog;

    public CountryAdapter(Activity activity, List<Country> listCountries, CountrySelectedItem countrySelectedItem, AlertDialog dialog) {
        this.activity = activity;
        this.listCountries = listCountries;
        this.countrySelectedItem = countrySelectedItem;
        this.dialog = dialog;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryHolder(inflater.inflate(R.layout.row_country, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {

        Country country = listCountries.get(position);
        if (country != null){
            setData(country, holder);
            setAction(country, holder);
        }

    }

    private void setData(Country country, CountryHolder holder) {
        String name = new StringBuilder()
                .append(country.getName())
                .append(" (")
                .append(country.getCode())
                .append(")")
                .toString();
        holder.tvCountryName.setText(name);
        holder.tvCountryCode.setText(country.getPhoneCode());
    }

    private void setAction(Country country, CountryHolder holder) {
        holder.setItemClickListener((view, pos) -> {
            countrySelectedItem.onCountrySelectedListener(country, pos);
            dialog.dismiss();
        });
    }

    @Override
    public int getItemCount() {
        return listCountries.size();
    }

    static class CountryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_country_name)
        TextView tvCountryName;
        @BindView(R.id.tv_country_code)
        TextView tvCountryCode;

        private ItemClickListener itemClickListener;

        public CountryHolder(@NonNull View itemView) {
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
