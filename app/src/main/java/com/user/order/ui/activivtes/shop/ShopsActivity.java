package com.user.order.ui.activivtes.shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.user.order.R;
import com.user.order.adapter.ShopsAdapter;
import com.user.order.listeners.AddRemoveFromFavoriteListener;
import com.user.order.model.categories.CategoryData;
import com.user.order.model.favorites.AddToFavorite;
import com.user.order.model.shop.Shop;
import com.user.order.model.shop.ShopsByCategory;
import com.user.order.ui.activivtes.auth.LoginActivity;
import com.user.order.ui.activivtes.auth.SignUpActivity;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopsActivity extends AppCompatActivity implements AddRemoveFromFavoriteListener {
    private static final String TAG = ShopsActivity.class.getSimpleName();

    @BindView(R.id.recycler_shops)
    RecyclerView recyclerShops;

    @BindView(R.id.tv_text_title)
    TextView tv_text_title;

    @BindView(R.id.no_user)
    ConstraintLayout no_user;

    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.btn_signup)
    Button btn_signup;

    private CategoryData category;

    private List<Shop> listShops;
    private ShopsAdapter shopsAdapter;

    @OnClick(R.id.iv_back)
    void onBackClick(){
        onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);
        ButterKnife.bind(this);

        checkIfUserExist();

    }

    private void checkIfUserExist(){
        if (HelperMethods.getUserToken(this) == null){
            HelperMethods.dismissProgress(ShopsActivity.this);
            recyclerShops.setVisibility(View.GONE);
            no_user.setVisibility(View.VISIBLE);
            btn_login.setOnClickListener(View->{
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
            });
            btn_signup.setOnClickListener(View->{
                startActivity(new Intent(getBaseContext(), SignUpActivity.class));
            });
        }else {
            initUI();
            getShops();
            recyclerShops.setVisibility(View.VISIBLE);
            no_user.setVisibility(View.GONE);
        }
    }

    private void initUI() {

        if (getIntent() != null)
            category = (CategoryData) getIntent().getSerializableExtra(Const.KEY_CATEGORY);

        listShops = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerShops.setHasFixedSize(true);
        recyclerShops.setLayoutManager(layoutManager);

        tv_text_title.setText(category.getName());
    }

    private void getShops() {

        HelperMethods.showProgress(this);

        double latitude, longitude;

        if (HelperMethods.getUserToken(this) != null) {
            latitude = HelperMethods.getDeliveryAddress(this).getLatitude();
            longitude = HelperMethods.getDeliveryAddress(this).getLongitude();
        } else {
            latitude = HelperMethods.getUserLatitude(this);
            longitude = HelperMethods.getUserLongitude(this);
        }

        HelperMethods.get1OrderAPI()
                .getShopsByCategory(HelperMethods.getAppLanguage(this),
                        HelperMethods.getUserToken(this),
                        category.getId(), latitude, longitude,1,HelperMethods.getCountrySettings(this).getId())
                .enqueue(new Callback<ShopsByCategory>() {
                    @Override
                    public void onResponse(Call<ShopsByCategory> call, Response<ShopsByCategory> response) {
                        HelperMethods.dismissProgress(ShopsActivity.this);
                        listShops.clear();
                        if (response.isSuccessful()){
                            listShops.addAll(response.body().shops);

                            shopsAdapter = new ShopsAdapter(ShopsActivity.this,listShops, ShopsActivity.this);
                            recyclerShops.setAdapter(shopsAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<ShopsByCategory> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                        HelperMethods.dismissProgress(ShopsActivity.this);
                    }
                });
    }

    @Override
    public void onAddRemoveFromFavorite(Shop shop, ImageView imageView, int position) {
        if (shop.getIsFavorite().equals("1"))
            addRemoveFromFavorite(true, shop, imageView, position);
        else
            addRemoveFromFavorite(false, shop, imageView, position);
    }



    private void addRemoveFromFavorite(boolean isRemove, Shop shop, ImageView imageView, int position) {

        HelperMethods.get1OrderAPI()
                .removeFavorites(HelperMethods.getAppLanguage(this),
                        HelperMethods.getUserToken(this),
                        shop.getId())
                .enqueue(new Callback<AddToFavorite>() {
                    @Override
                    public void onResponse(Call<AddToFavorite> call, Response<AddToFavorite> response) {
                        if (response.isSuccessful()){
                            if (response.body().isSuccess()){
                                if (isRemove)
                                    Glide.with(ShopsActivity.this).load(R.drawable.ic_icon_add_favorite).into(imageView);
                                else
                                    Glide.with(ShopsActivity.this).load(R.drawable.ic_icon_added_favorite).into(imageView);
                                getShops();
                            }else{
                                HelperMethods.showCustomToast(ShopsActivity.this, response.body().getMessage(), false);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AddToFavorite> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });

    }

    @Override
    protected void onRestart() {
        checkIfUserExist();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        finishActivity();
    }
    public void finishActivity() {
        if (PreferencesManager.getStringPreferences("finish_payment").equals("finish_payment")) {
            PreferencesManager.setStringPreferences("finish_payment", "");
            finish();
        }
    }
}