package com.user.order.ui.fragments.order;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.user.order.adapter.OrdersAdapter;
import com.user.order.adapter.RatingAdapter;
import com.user.order.databinding.FragmentOneOrderBinding;
import com.user.order.fcm.MyEventBus;
import com.user.order.model.order.OneOrderData;
import com.user.order.model.order.Orders;
import com.user.order.model.rating.Rating;
import com.user.order.ui.activivtes.auth.LoginActivity;
import com.user.order.ui.activivtes.auth.SignUpActivity;
import com.user.order.ui.activivtes.home.publicOrders.PublicOrderDetailsActivity;
import com.user.order.ui.activivtes.rating.RatingActivity;
import com.user.order.utils.HelperMethods;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OneOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OneOrderFragment extends Fragment {
    int page = 1, limit = 10;
    boolean isLoading = false;
    boolean isLastPage = true;
    int lastVisibleItem;
    int totalItemCount;
    private List<OneOrderData> listOrders;
    FragmentOneOrderBinding binding;
    OrdersAdapter adapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OneOrderFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static OneOrderFragment newInstance(String param1, String param2) {
        OneOrderFragment fragment = new OneOrderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOneOrderBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        startShimmer();

        checkIfUserExist();

        return view;
    }

    private void initUI() {


        listOrders = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItem == (totalItemCount - 1) && !isLoading && totalItemCount != 0 && !isLastPage) {
                    page++;
                    getOrders();
                }
            }
        });

    }

    private void checkIfUserExist() {
        if (HelperMethods.getUserToken(getActivity()) == null) {
            binding.recyclerView.setVisibility(View.GONE);
            binding.empty.setVisibility(View.GONE);
            binding.imageView26.setVisibility(View.GONE);
            binding.noUser.setVisibility(View.VISIBLE);
            binding.btnLogin.setOnClickListener(View -> {
                getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
            });
            binding.btnSignup.setOnClickListener(View -> {
                getActivity().startActivity(new Intent(getActivity(), SignUpActivity.class));
            });
        } else {
            initUI();
            getOrders();
            binding.recyclerView.setVisibility(View.VISIBLE);
            binding.noUser.setVisibility(View.GONE);
        }
    }

    private void getOrders() {
        HelperMethods.get1OrderAPI()
                .getOrders(HelperMethods.getAppLanguage(getActivity()),
                        HelperMethods.getUserToken(getActivity()), limit, page)
                .enqueue(new Callback<Orders>() {
                    @Override
                    public void onResponse(Call<Orders> call, Response<Orders> response) {
                        binding.recyclerView.setVisibility(View.GONE);
                        stopShimmer();
                        if (response.isSuccessful()) {
                            listOrders.clear();
                            listOrders.addAll(response.body().getOrders().getData());

                            if (getActivity() != null) {

                                OrdersAdapter ordersAdapter = new OrdersAdapter(getActivity(), listOrders);
                                binding.recyclerView.setAdapter(ordersAdapter);

                            }
                            if (listOrders.isEmpty()) {
                                binding.recyclerView.setVisibility(View.GONE);
                                binding.imageView26.setVisibility(View.VISIBLE);
                                binding.empty.setVisibility(View.VISIBLE);
                            } else {
                                binding.recyclerView.setVisibility(View.VISIBLE);
                                binding.imageView26.setVisibility(View.GONE);
                                binding.empty.setVisibility(View.GONE);
                            }
                        } else {
                        }

                    }

                    @Override
                    public void onFailure(Call<Orders> call, Throwable t) {
                        Log.e("TAG", "onFailure: " + t.getMessage());
                        stopShimmer();
                    }
                });

    }

    @Override
    public void onResume() {
        super.onResume();
        checkIfUserExist();
    }

    @SuppressLint("SetTextI18n")
    public void parseError(Response<?> response) {
        String message = "";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response.errorBody().string());
            message = jsonObject.getString("message");
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MyEventBus eventBus) {
        getOrders();

    }

    private void startShimmer() {
        binding.shimmerLayout.startShimmer();

    }

    private void stopShimmer() {
        binding.shimmerLayout.stopShimmer();
        binding.shimmerLayout.setVisibility(View.GONE);
    }
}

