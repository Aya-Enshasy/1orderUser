package com.user.order.ui.fragments.orders;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.user.order.R;
import com.user.order.adapter.MyPagerAdapter;
import com.user.order.adapter.OrdersAdapter;
import com.user.order.adapter.PublicOrdersAdapter;
import com.user.order.databinding.FragmentOrdersBinding;
 import com.user.order.model.orders.publicOrders.PublicOrders;
import com.user.order.utils.HelperMethods;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersFragment extends Fragment  {

    FragmentOrdersBinding binding;
    MyPagerAdapter viewPagerAdapter;

    private static OrdersFragment instance;

    public static OrdersFragment getInstance() {
        return instance == null ? new OrdersFragment() : instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrdersBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewPagerAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        binding.viewpager.setAdapter(viewPagerAdapter);

        binding.tabs.setupWithViewPager(binding.viewpager);

        MyPagerAdapter adapter = new MyPagerAdapter( getChildFragmentManager());
        binding.viewpager.setAdapter(adapter);
        binding.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabs));
        binding.tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                binding.viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

}