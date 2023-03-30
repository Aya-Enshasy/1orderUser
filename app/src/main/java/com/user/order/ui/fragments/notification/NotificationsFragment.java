package com.user.order.ui.fragments.notification;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.user.order.R;
import com.user.order.adapter.NotificationsAdapter;
import com.user.order.databinding.FragmentNotificationsBinding;
import com.user.order.model.notifications.Data;
import com.user.order.model.notifications.Notifications;
import com.user.order.ui.activivtes.auth.LoginActivity;
import com.user.order.ui.activivtes.auth.SignUpActivity;
import com.user.order.utils.HelperMethods;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {
    private static final String TAG = NotificationsFragment.class.getSimpleName();

    FragmentNotificationsBinding binding;
    private List<Data> listNotifications;
    NotificationsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        initUI();
        startShimmer();
        return view;
    }

    private void initUI() {

        listNotifications = new ArrayList<>();
        setAdapter();
        checkIfUserExist();

    }
    private void setAdapter() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL, false);
        binding.recyclerNotification.setLayoutManager(layoutManager);

        adapter = new NotificationsAdapter(getActivity(), listNotifications);
        binding.recyclerNotification.setAdapter(adapter);


    }

    private void getNotifications() {
        HelperMethods.get1OrderAPI().
                getNotifications(HelperMethods.getAppLanguage(getActivity()), HelperMethods.getUserToken(getActivity()),"*/*")
                .enqueue(new Callback<Notifications>() {
            @Override
            public void onResponse(Call<Notifications> call, Response<Notifications> response) {
                Log.e("", response.code()+"");
                if(response.isSuccessful()){
                    stopShimmer();
                    binding.recyclerNotification.startLayoutAnimation();
                    listNotifications = response.body().getData();
                    adapter.setData(listNotifications);
                    if(listNotifications.isEmpty()){
                        binding.recyclerNotification.setVisibility(View.GONE);
                        binding.reLNoNotifications.setVisibility(View.VISIBLE);
                    }else{
                        binding.recyclerNotification.setVisibility(View.VISIBLE);
                        binding.reLNoNotifications.setVisibility(View.GONE);
                    }
                }

                else{
                }
            }
            @Override
            public void onFailure(Call<Notifications> call, Throwable t) {
                call.cancel();
                Log.e("Throwable", t.getMessage()+"");
                stopShimmer();

            }
        });
    }

    private void checkIfUserExist(){
        if (HelperMethods.getUserToken(getActivity()) == null){
            binding.recyclerNotification.setVisibility(View.GONE);
            binding.reLNoNotifications.setVisibility(View.GONE);
            binding.noUser.setVisibility(View.VISIBLE);
            binding.btnLogin.setOnClickListener(View->{
                getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
            });
            binding.btnSignup.setOnClickListener(View->{
                getActivity().startActivity(new Intent(getActivity(), SignUpActivity.class));

            });
        }else {
            getNotifications();
            binding.recyclerNotification.setVisibility(View.VISIBLE);
            binding.ivNoUser.setVisibility(View.GONE);
        }
    }

    private void startShimmer() {
        binding.shimmerLayout.startShimmer();

    }

    private void stopShimmer() {
        binding.shimmerLayout.stopShimmer();
        binding.shimmerLayout.setVisibility(View.GONE);
    }
}
