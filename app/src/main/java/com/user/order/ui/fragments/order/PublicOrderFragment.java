package com.user.order.ui.fragments.order;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.user.order.adapter.PublicOrdersAdapter;
import com.user.order.databinding.FragmentPublicOrderBinding;
import com.user.order.fcm.MyEventBus;
import com.user.order.model.orders.publicOrders.OrderData;
import com.user.order.model.orders.publicOrders.PublicOrders;
import com.user.order.ui.activivtes.auth.LoginActivity;
import com.user.order.ui.activivtes.auth.SignUpActivity;
import com.user.order.utils.HelperMethods;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PublicOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PublicOrderFragment extends Fragment {

    private List<OrderData> listPublicOrders;
    FragmentPublicOrderBinding binding;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PublicOrderFragment() {
        // Required empty public constructor
    }

    public static PublicOrderFragment newInstance(String param1, String param2) {
        PublicOrderFragment fragment = new PublicOrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPublicOrderBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        startShimmer();
        checkIfUserExist();
        return view;
    }

    private void initUI() {

        listPublicOrders = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(layoutManager);

    }

    private void checkIfUserExist(){
        if (HelperMethods.getUserToken(getActivity()) == null){
            binding.recyclerView.setVisibility(View.GONE);
            binding.empty.setVisibility(View.GONE);
            binding.imageView26.setVisibility(View.GONE);
            binding.noUser.setVisibility(View.VISIBLE);
            binding.btnLogin.setOnClickListener(View->{
                getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
            });
            binding.btnSignup.setOnClickListener(View->{
                getActivity().startActivity(new Intent(getActivity(), SignUpActivity.class));
            });
        }else {
            initUI();
            getPublicOrders();
            binding.recyclerView.setVisibility(View.VISIBLE);
            binding.ivNoUser.setVisibility(View.GONE);
        }
    }

    private void getPublicOrders() {

        HelperMethods.get1OrderAPI().getPublicOrders(HelperMethods.getAppLanguage(getActivity()), HelperMethods.getUserToken(getActivity()))
                .enqueue(new Callback<PublicOrders>() {
                    @Override
                    public void onResponse(Call<PublicOrders> call, Response<PublicOrders> response) {
                        binding.recyclerView.setVisibility(View.GONE);
                        stopShimmer();
                        if (response.isSuccessful()) {
                                listPublicOrders.clear();
                                listPublicOrders.addAll(response.body().getData().getData());
                                PublicOrdersAdapter ordersAdapter = new PublicOrdersAdapter(getActivity(), listPublicOrders);
                                binding.recyclerView.setAdapter(ordersAdapter);

                                if(listPublicOrders.isEmpty()){
                                    binding.recyclerView.setVisibility(View.GONE);
                                    binding.empty.setVisibility(View.VISIBLE);
                                    binding.imageView26.setVisibility(View.VISIBLE);
                                }else{
                                    binding.recyclerView.setVisibility(View.VISIBLE);
                                    binding.empty.setVisibility(View.GONE);
                                    binding.imageView26.setVisibility(View.GONE);

                            } }else {
//                                HelperMethods.showCustomToast(getActivity(), response.body().getMessage(), false);
                            }

                    }

                    @Override
                    public void onFailure(Call<PublicOrders> call, Throwable t) {
                        Log.d("TAG", "onFailure: " + t.getMessage());
                        stopShimmer();
                    }
                });

    }


    @Override
    public void onResume() {
        super.onResume();

        if (HelperMethods.getUserToken(getActivity()) != null) {
            getPublicOrders();
        } else {
            binding.imageView26.setVisibility(View.GONE);
            binding.recyclerView.setVisibility(View.GONE);
            binding.empty.setVisibility(View.GONE);
            binding.noUser.setVisibility(View.VISIBLE);

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MyEventBus eventBus) {
        getPublicOrders();

    }

    private void startShimmer() {
        binding.shimmerLayout.startShimmer();

    }

    private void stopShimmer() {
        binding.shimmerLayout.stopShimmer();
        binding.shimmerLayout.setVisibility(View.GONE);
    }
}
