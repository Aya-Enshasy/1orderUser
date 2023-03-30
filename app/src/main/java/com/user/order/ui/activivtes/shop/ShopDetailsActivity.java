package com.user.order.ui.activivtes.shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.user.order.R;
import com.user.order.adapter.ResturantCategoriesAdapter;
import com.user.order.adapter.ResturantMealAdapter;
import com.user.order.adapter.ResturantOffersAdapter;
import com.user.order.listeners.ItemClickListener2;
import com.user.order.listeners.SelectCategoryListener;
import com.user.order.model.categories.CategoryData;
import com.user.order.model.favorites.AddToFavorite;
import com.user.order.model.shop.Shop;
import com.user.order.model.shop.ShopDetails;
import com.user.order.model.shop.meals.Meals;
import com.user.order.model.shop.meals.MealsData;
import com.user.order.ui.activivtes.cart.CartActivity;
import com.user.order.ui.activivtes.rating.RatingActivity;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;
import com.user.order.utils.PreferencesManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopDetailsActivity extends AppCompatActivity implements SelectCategoryListener {
    private static final String TAG = ShopDetailsActivity.class.getSimpleName();

    @BindView(R.id.tv_text_title)
    TextView tvTextTitle;
    @BindView(R.id.civ_shop_image)
    CircleImageView civShopImage;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_text_offers)
    TextView tv_text_offers;
    @BindView(R.id.iv_favorite)
    ImageView ivFavorite;
    @BindView(R.id.tv_shop_address)
    TextView tvShopAddress;
    @BindView(R.id.tv_shop_status)
    TextView tvShopStatus;
    @BindView(R.id.ratingBar)
    AppCompatRatingBar ratingBar;
    @BindView(R.id.tv_rate)
    TextView tvRate;
    @BindView(R.id.view_pager_resturant_offers)
    ViewPager2 pagerOffers;
    @BindView(R.id.dots_indicator)
    WormDotsIndicator dotsIndicator;
    @BindView(R.id.recycler_categories)
    RecyclerView recyclerCategories;
    @BindView(R.id.recycler_sandwich)
    RecyclerView recyclerMeal;
    private Shop shop;
    private List<CategoryData> listCategories;
    private List<MealsData> listMeals;
    ResturantMealAdapter adapter;
Boolean x = true;
    @BindView(R.id.rating)
    ConstraintLayout rating;
    int id;

    @OnClick(R.id.iv_back)
    void onBackClick() {
        onBackPressed();
    }

    @OnClick(R.id.iv_favorite)
    void onFavoriteClick() {
        if (shop.getIsFavorite().equals("1"))
            addRemoveFromFavorite(true, shop, ivFavorite);
        else
            addRemoveFromFavorite(false, shop, ivFavorite);
    }

    @OnClick(R.id.card_cart)
    void onCartClick() {
        PreferencesManager.setStringPreferences("shop_name", shop.getName());
        PreferencesManager.setStringPreferences("shop_address", shop.getAddress());
        PreferencesManager.setStringPreferences("shop_image", shop.getCoverImageUrl());
        PreferencesManager.setStringPreferences("shop_id1", shop.getId() + "");
        Log.e("getShop1",shop.getId() +"");

        PreferencesManager.setStringPreferences("shop_lat", shop.getLat() + "");
        PreferencesManager.setStringPreferences("shop_lng", shop.getLng() + "");
        if (shop.getOffers().get(0)!=null)
        PreferencesManager.setStringPreferences("shop_offerid", shop.getOffers().get(0).getId() + "");

        startActivity(new Intent(this, CartActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);
        ButterKnife.bind(this);
        initUI();
        getShopDetails();

    }

    private void initUI() {

        if (getIntent() != null)
            shop = (Shop) getIntent().getSerializableExtra(Const.KEY_SHOP);

        rating.setOnClickListener(v -> {
            startActivity(new Intent(this, RatingActivity.class)
                    .putExtra(Const.KEY_Rate_id,shop.getId())
                    .putExtra(Const.KEY_SHOP, shop));
            PreferencesManager.setStringPreferences(Const.KEY_Rate_id,shop.getId()+"");

        });

        listCategories = new ArrayList<>();
        listMeals = new ArrayList<>();

        tvTextTitle.setText(shop.getName());

        offersSlider();
        sandwichAdapter();
        restaurantCategoriesAdapter();
    }

    private void offersSlider() {
        if (shop.getOffers().isEmpty()) {
            tv_text_offers.setVisibility(View.GONE);
            dotsIndicator.setVisibility(View.GONE);
        } else {
            pagerOffers.setClipToPadding(false);
            pagerOffers.setClipChildren(false);
            pagerOffers.setOffscreenPageLimit(3);
            pagerOffers.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);
            CompositePageTransformer transformer = new CompositePageTransformer();
            transformer.addTransformer(new MarginPageTransformer(10));
            transformer.addTransformer((page, position) -> {
                float f = 1 - Math.abs(position);
                page.setScaleY(0.85f + f * 0.15f);
            });

            pagerOffers.setPageTransformer(transformer);
            ResturantOffersAdapter offersAdapter = new ResturantOffersAdapter(this, shop.getOffers(), pagerOffers);

            pagerOffers.setAdapter(offersAdapter);
            dotsIndicator.attachTo(pagerOffers);
        }

    }

    private void sandwichAdapter() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerMeal.setLayoutManager(layoutManager);

        adapter = new ResturantMealAdapter(this, listMeals);

        recyclerMeal.setAdapter(adapter);
    }

    private void restaurantCategoriesAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerCategories.setHasFixedSize(true);
        recyclerCategories.setLayoutManager(layoutManager);


        ResturantCategoriesAdapter categoriesAdapter = new ResturantCategoriesAdapter(ShopDetailsActivity.this, shop.getSub_category(), new SelectCategoryListener() {
            @Override
            public void onSelectCategory(CategoryData category, int position) {
                id = category.getId();
                getMeals(id);
            }
        }, new ItemClickListener2() {
            @Override
            public void onItemClick(int id) {
                if (x==true)
                getMeals(id);
            }
        });
        recyclerCategories.setAdapter(categoriesAdapter);

    }

    private void getShopDetails() {

        if (shop.getLogoUrl() != null)
            Glide.with(ShopDetailsActivity.this).load(shop.getLogoUrl()).placeholder(R.drawable.app_logo).into(civShopImage);

        tvShopName.setText(shop.getName());
        tvShopAddress.setText(shop.getAddress());

        if (shop.getIsOpen().equals("1")){
            tvShopStatus.setText(getString(R.string.open));
            tvShopStatus.setTextColor(getColor(R.color.colorBlue));
        }
        else {
            tvShopStatus.setText(getString(R.string.close));
            tvShopStatus.setTextColor(getColor(R.color.red));

        }
        PreferencesManager.setStringPreferences("isOpen",shop.getIsOpen());
        if (shop.getIsFavorite().equals("1"))
            Glide.with(ShopDetailsActivity.this).load(R.drawable.ic_icon_added_favorite).into(ivFavorite);
        else
            Glide.with(ShopDetailsActivity.this).load(R.drawable.ic_icon_add_favorite).into(ivFavorite);

        ratingBar.setRating(Float.valueOf(shop.getRate()));
        ratingBar.setOnClickListener(View -> {
            startActivity(new Intent(this, RatingActivity.class)
                    .putExtra(Const.KEY_Rate_id,shop.getId())
                    .putExtra(Const.KEY_SHOP, shop));
            PreferencesManager.setStringPreferences(Const.KEY_Rate_id,shop.getId()+"");

        });
        tvRate.setText(HelperMethods.formatTotal(Double.parseDouble(shop.getRate())));

    }

    private void addRemoveFromFavorite(boolean isRemove, Shop shop, ImageView imageView) {

        HelperMethods.get1OrderAPI()
                .removeFavorites(HelperMethods.getAppLanguage(this),
                        HelperMethods.getUserToken(this),
                        shop.getId())
                .enqueue(new Callback<AddToFavorite>() {
                    @Override
                    public void onResponse(Call<AddToFavorite> call, Response<AddToFavorite> response) {
                        if (response.isSuccessful()) {
                            if (response.body().isSuccess()) {
                                if (isRemove)
                                    Glide.with(ShopDetailsActivity.this).load(R.drawable.ic_icon_add_favorite).into(imageView);
                                else
                                    Glide.with(ShopDetailsActivity.this).load(R.drawable.ic_icon_added_favorite).into(imageView);
                                getShop();
                            } else {
                                HelperMethods.showCustomToast(ShopDetailsActivity.this, response.body().getMessage(), false);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AddToFavorite> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });

    }

    private void getShop() {

        double latitude, longitude;

        if (HelperMethods.getUserToken(this) != null) {
            latitude = HelperMethods.getDeliveryAddress(this).getLatitude();
            longitude = HelperMethods.getDeliveryAddress(this).getLongitude();
        } else {
            latitude = HelperMethods.getUserLatitude(this);
            longitude = HelperMethods.getUserLongitude(this);
        }

        HelperMethods.get1OrderAPI()
                .getShopDetails(HelperMethods.getAppLanguage(this),
                        shop.getId(), latitude, longitude)
                .enqueue(new Callback<ShopDetails>() {
                    @Override
                    public void onResponse(Call<ShopDetails> call, Response<ShopDetails> response) {
                    }

                    @Override
                    public void onFailure(Call<ShopDetails> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }

    @Override
    public void onSelectCategory(CategoryData category, int position) {
        getMeals(category.getId());
    }

    private void getMeals(int id) {
        HelperMethods.get1OrderAPI()
                .getMeals(HelperMethods.getAppLanguage(this), shop.getId(), id)
                .enqueue(new Callback<Meals>() {
                    @Override
                    public void onResponse(Call<Meals> call, Response<Meals> response) {
                        if (response.isSuccessful()) {
                            x =false;
                            recyclerMeal.startLayoutAnimation();
                            listMeals = response.body().getItems().getMeals();
                            adapter.setData(listMeals);
                        }
                    }

                    @Override
                    public void onFailure(Call<Meals> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        finishActivity();
    }
    public void finishActivity() {
        if (PreferencesManager.getStringPreferences("finish_payment").equals("finish_payment")) {
            finish();
        } else {

        }
    }
}