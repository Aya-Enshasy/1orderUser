package com.user.order.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;
import com.user.order.R;
import com.user.order.databinding.ExtrasItemBinding;
import com.user.order.databinding.RowPublicShopsBinding;
import com.user.order.listeners.ItemClickListener;
import com.user.order.model.map.publicShops.PublicShopData;
import com.user.order.ui.activivtes.home.publicOrders.CreateOrderActivity;
import com.user.order.utils.Const;
import com.user.order.utils.PreferencesManager;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PublicShopsAdapter extends RecyclerView.Adapter<PublicShopsAdapter.PublicResturantHolder> {

    private Activity activity;
    private List<PublicShopData> listMapShops;
    private LayoutInflater inflater;
    double direction;
    double earthRadius = 3958.75;

    public PublicShopsAdapter(Activity activity, List<PublicShopData> listMapShops) {
        this.activity = activity;
        this.listMapShops = listMapShops;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<PublicShopData> listMapShops) {
        this.listMapShops = listMapShops;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PublicResturantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowPublicShopsBinding binding = RowPublicShopsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PublicShopsAdapter.PublicResturantHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicResturantHolder holder, int position) {

        PublicShopData shop = listMapShops.get(position);
        if (shop != null) {

            if (shop.getIcon() != null) {
                Glide.with(activity).load(shop.getIcon()).placeholder(R.drawable.app_logo).into(holder.binding.ivShopImage);
            }

            holder.binding.tvShopName.setText(shop.getName());
            holder.binding.tvShopAddress.setText(shop.getVicinity());


            holder.setItemClickListener((view, pos) -> {
                activity.startActivity(new Intent(activity, CreateOrderActivity.class)
                        .putExtra(Const.KEY_PUBLIC_SHOPS, shop));
            });


            String current_lat = PreferencesManager.getStringPreferences(Const.KEY_CURRENT_LATITUDE);
            String current_lng = PreferencesManager.getStringPreferences(Const.KEY_CURRENT_LONGITUDE);

//            double max_lat = Math.max(Double.parseDouble(current_lat),shop.getGeometry().getLocation().getLatitude());
//            double min_lat = Math.min(Double.parseDouble(current_lat),shop.getGeometry().getLocation().getLatitude());
//
//            double max_lng = Math.max(Double.parseDouble(current_lng),shop.getGeometry().getLocation().getLongitude());
//            double min_lng = Math.min(Double.parseDouble(current_lng),shop.getGeometry().getLocation().getLongitude());

            int Radius = 6371;// radius of earth in Km
            double lat1 = Double.parseDouble(current_lat);
            double lat2 = shop.getGeometry().getLocation().getLatitude();
            double lon1 = Double.parseDouble(current_lng);
            double lon2 = shop.getGeometry().getLocation().getLongitude();
            double dLat = Math.toRadians(lat2 - lat1);
            double dLon = Math.toRadians(lon2 - lon1);
            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                    + Math.cos(Math.toRadians(lat1))
                    * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                    * Math.sin(dLon / 2);
            double c = 2 * Math.asin(Math.sqrt(a));
            double valueResult = Radius * c;
            double km = valueResult / 1;
            DecimalFormat newFormat = new DecimalFormat("####");
            int kmInDec = Integer.valueOf(newFormat.format(km));
            double meter = valueResult % 1000;
            int meterInDec = Integer.valueOf(newFormat.format(meter));
            Log.e("Radius Value", "" + valueResult + "   KM  " + kmInDec
                    + " Meter   " + meterInDec);

            holder.binding.tvShopDistance.setText(kmInDec + " KM");


            LatLng latLngFrom = new LatLng(Double.parseDouble(current_lat), Double.parseDouble(current_lng));
            LatLng latLngTo = new LatLng(shop.getGeometry().getLocation().getLatitude(), shop.getGeometry().getLocation().getLongitude());
            Log.e("max_lat", SphericalUtil.computeDistanceBetween(latLngTo, latLngFrom) + "");
            double value = SphericalUtil.computeDistanceBetween(latLngFrom, latLngTo);
            value = Double.parseDouble(new DecimalFormat("#.#").format(value));

        }


    }

    @Override
    public int getItemCount() {
        return listMapShops.size();
    }

    static class PublicResturantHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemClickListener itemClickListener;
        RowPublicShopsBinding binding;

        public PublicResturantHolder(RowPublicShopsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

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