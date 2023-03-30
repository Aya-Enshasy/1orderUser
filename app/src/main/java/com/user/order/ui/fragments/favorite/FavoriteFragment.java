package com.user.order.ui.fragments.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.user.order.R;
import com.user.order.adapter.FavoritesAdapter;
import com.user.order.databinding.FragmentFavoriteBinding;
import com.user.order.listeners.ItemClickListener2;
import com.user.order.model.contact.ContactUs;
import com.user.order.model.favorites.Favorites;
import com.user.order.model.favorites.FavoritesData;
import com.user.order.ui.activivtes.auth.LoginActivity;
import com.user.order.ui.activivtes.auth.SignUpActivity;
import com.user.order.utils.HelperMethods;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteFragment extends Fragment {

    FragmentFavoriteBinding binding;
    private List<FavoritesData> listFavorites;
    FavoritesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        initUI();
        checkIfUserExist();
        setAdapter();
        return view;
    }

    private void initUI() {
        startShimmer();
        listFavorites = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerFavorite.setHasFixedSize(true);
        binding.recyclerFavorite.setLayoutManager(layoutManager);


    }

    private void setAdapter() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL, false);
        binding.recyclerFavorite.setLayoutManager(layoutManager);

        adapter = new FavoritesAdapter(getActivity(), listFavorites, new ItemClickListener2() {
            @Override
            public void onItemClick(int id) {
                deleteFavorites(id);
            }
        });
        binding.recyclerFavorite.setAdapter(adapter);


    }

    private void getFavorites() {
        HelperMethods.get1OrderAPI().getFavorites(HelperMethods.getAppLanguage(getActivity()), HelperMethods.getUserToken(getActivity())).enqueue(new Callback<Favorites>() {
            @Override
            public void onResponse(Call<Favorites> call, Response<Favorites> response) {
                Log.e("", response.code()+"");
                stopShimmer();
                if (response.isSuccessful()) {
                    binding.recyclerFavorite.startLayoutAnimation();
                    listFavorites = response.body().getFavorites();
                    adapter.setData(listFavorites);

                    if(listFavorites.isEmpty()){
                        binding.recyclerFavorite.setVisibility(View.GONE);
                        binding.relNoFavorites.setVisibility(View.VISIBLE);
                    }else{
                        binding.recyclerFavorite.setVisibility(View.VISIBLE);
                        binding.relNoFavorites.setVisibility(View.GONE);
                    }
                }
                else {
                    stopShimmer();
                }
            }
            @Override
            public void onFailure(Call<Favorites> call, Throwable t) {
                call.cancel();
                Log.e("Throwable", t.getMessage()+"");

                stopShimmer();
            }
        });
    }

    private void deleteFavorites(int faveId) {
        HelperMethods.get1OrderAPI().deleteFavorites(HelperMethods.getAppLanguage(getActivity()),
                        HelperMethods.getUserToken(getActivity()),
                        String.valueOf(faveId))
                .enqueue(new Callback<ContactUs>() {
                    @Override
                    public void onResponse(Call<ContactUs> call, Response<ContactUs> response) {
                        if(response.isSuccessful()){
                            getFavorites();
                        }else
                            Log.e("message",response.message());

                    }

                    @Override
                    public void onFailure(Call<ContactUs> call, Throwable t) {
                        Log.e("TAG", "onFailure: " + t.getMessage());
                    }
                });
    }

    private void checkIfUserExist(){
        if (HelperMethods.getUserToken(getActivity()) == null){
            binding.recyclerFavorite.setVisibility(View.GONE);
            binding.relNoFavorites.setVisibility(View.GONE);
            binding.noUser.setVisibility(View.VISIBLE);
            binding.btnLogin.setOnClickListener(View->{
                getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
            });
            binding.btnSignup.setOnClickListener(View->{
                getActivity().startActivity(new Intent(getActivity(), SignUpActivity.class));
            });
        }else {
            getFavorites();
            binding.recyclerFavorite.setVisibility(View.VISIBLE);
          //  binding.relNoFavorites.setVisibility(View.VISIBLE);
            binding.ivNoUser.setVisibility(View.GONE);
        }
    }

    private void startShimmer() {
        binding.shimmerLayout.startShimmer();
        binding.relNoFavorites.setVisibility(View.GONE);

    }

    private void stopShimmer() {
        binding.shimmerLayout.stopShimmer();
        binding.shimmerLayout.setVisibility(View.GONE);
    }
}