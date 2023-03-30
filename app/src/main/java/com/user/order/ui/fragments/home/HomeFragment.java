package com.user.order.ui.fragments.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.user.order.MapsActivity;
import com.user.order.R;
import com.user.order.adapter.HomeCatrgoriesAdapter;
import com.user.order.adapter.HomeOffersAdapter;
import com.user.order.adapter.PublicShopsAdapter;
import com.user.order.model.categories.Categories;
import com.user.order.model.categories.CategoryData;
import com.user.order.model.offers.OfferData;
import com.user.order.model.offers.Offers;
import com.user.order.model.map.publicShops.PublicShopData;
import com.user.order.model.map.publicShops.PublicShops;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;
import com.user.order.utils.PreferencesManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = HomeFragment.class.getSimpleName();

    @BindView(R.id.tv_tap_1order)
    TextView tvTab1Order;
    @BindView(R.id.view_tap_1order)
    View viewTab1Order;
    @BindView(R.id.tv_tap_public)
    TextView tvTabPublic;
    @BindView(R.id.view_tap_public)
    View viewTabPublic;
    @BindView(R.id.tv_tab_selected)
    TextView tabSelect;
    @BindView(R.id.searchView3)
    SearchView searchView;
    @BindView(R.id.view_pager_products)
    ViewPager2 pagerProducts;
    @BindView(R.id.dots_indicator)
    WormDotsIndicator dotsIndicator;
    @BindView(R.id.recycler_categories)
    RecyclerView recyclerCategories;
    @BindView(R.id.scroll_1order)
    NestedScrollView scroll1Order;
    @BindView(R.id.recycler_public)
    RecyclerView recyclerPublic;
    @BindView(R.id.location)
    ImageView location;

    private View layoutView;
    private Typeface fontBold, fontSemiBold;
    PublicShopsAdapter publicShopsAdapter;
    private List<CategoryData> listCategories;
    private List<OfferData> listOffers;
    private List<PublicShopData> listMapsShops;


    private static HomeFragment instance;

    public static HomeFragment getInstance() {
        return instance == null ? new HomeFragment() : instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.fragment_home, container, false);
        this.layoutView = layoutView;
        ButterKnife.bind(this, layoutView);
        initUI(layoutView);
        load1OrderData(layoutView);
        getPublicShops(layoutView);
        setSearchView();


        location.setOnClickListener(View->{
            startActivity(new Intent(getActivity(), MapsActivity.class) .putExtra("order","order"));
        });

        return layoutView;
    }

    private void setSearchView() {
        searchView.setQueryHint("Search");

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            public boolean onClose() {
                initUI(layoutView);
                load1OrderData(layoutView);
                getPublicShops(layoutView);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                HelperMethods.showProgress(layoutView);
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(layoutView.getWindowToken(), 0);
                search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void search(String c) {

        String search = searchView.getQuery().toString();
        HelperMethods.get1OrderAPI().search("31.515055, 34.452029", "100000",
                        search, "restaurant", "AIzaSyCIRYeAhC9c3u2KQ9HbUvL1pBbueLyTroA")
                .enqueue(new Callback<PublicShops>() {
                    @Override
                    public void onResponse(Call<PublicShops> call, Response<PublicShops> response) {
                        HelperMethods.dismissProgress(layoutView);

                        if (response.isSuccessful()) {
                            Log.e("search", response.body().getStatus() + "");
                            listMapsShops.clear();
                            listMapsShops = response.body().getResults();
                            PublicShopsAdapter publicShopsAdapter = new PublicShopsAdapter(getActivity(), listMapsShops);
                            recyclerPublic.setAdapter(publicShopsAdapter);
                            publicShopsAdapter.setData(listMapsShops);
                        } else
                            Log.e("search", response.message() + "");
                    }

                    @SuppressLint("CheckResult")
                    @Override
                    public void onFailure(Call<PublicShops> call, Throwable t) {
                        HelperMethods.dismissProgress(layoutView);

                        Log.e("search", t.getMessage() + "");

                    }
                });

    }

    private void load1OrderData(View layoutView) {

        getCategories(layoutView);
        getOffers(layoutView);
    }

    private void initUI(View layoutView) {

        HelperMethods.showProgress(layoutView);

        listCategories = new ArrayList<>();
        listOffers = new ArrayList<>();
        listMapsShops = new ArrayList<>();

        fontBold = ResourcesCompat.getFont(getActivity(), R.font.montserrat_bold);
        fontSemiBold = ResourcesCompat.getFont(getActivity(), R.font.montserrat_semi_bold);
        tvTab1Order.setTypeface(fontBold);

        tvTab1Order.setOnClickListener(this);
        tvTabPublic.setOnClickListener(this);

        pagerProducts.setClipToPadding(false);
        pagerProducts.setClipChildren(false);
        pagerProducts.setOffscreenPageLimit(3);
        pagerProducts.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);
        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(10));
        transformer.addTransformer((page, position) -> {
            float f = 1 - Math.abs(position);
            page.setScaleY(0.85f + f * 0.15f);
        });
        pagerProducts.setPageTransformer(transformer);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerCategories.setHasFixedSize(true);
        recyclerCategories.setLayoutManager(layoutManager);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerPublic.setHasFixedSize(true);
        recyclerPublic.setLayoutManager(linearLayoutManager);

    }

    private void getCategories(View layoutView) {

        double latitude = 0, longitude=0;

        if (HelperMethods.getUserToken(getActivity()) != null) {
            latitude = HelperMethods.getDeliveryAddress(getActivity()).getLatitude();
            longitude = HelperMethods.getDeliveryAddress(getActivity()).getLongitude();
        } else {
//            latitude = HelperMethods.getUserLatitude(getActivity());
//            longitude = HelperMethods.getUserLongitude(getActivity());
        }

        Log.d(TAG, "getCategories: " + latitude + "," + longitude);

        HelperMethods.get1OrderAPI()
                .getCategories(HelperMethods.getAppLanguage(getActivity()),
                        latitude, longitude, HelperMethods.getCountrySettings(getActivity()).getId())
                .enqueue(new Callback<Categories>() {
                    @Override
                    public void onResponse(Call<Categories> call, Response<Categories> response) {
                        HelperMethods.dismissProgress(layoutView);
                        if (response.isSuccessful()) {
                            if (response.body().getCategories().size() > 0) {
                                listCategories.clear();
                                listCategories.addAll(response.body().categories);
                                Log.d(TAG, "onResponse: " + listCategories.size());
                                HomeCatrgoriesAdapter homeCatrgoriesAdapter = new HomeCatrgoriesAdapter(getActivity(), listCategories);
                                recyclerCategories.setAdapter(homeCatrgoriesAdapter);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Categories> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                        HelperMethods.dismissProgress(layoutView);
                    }
                });
    }

    private void getOffers(View layoutView) {


        double latitude=0, longitude=0;

        if (HelperMethods.getUserToken(getActivity()) != null) {
            latitude = HelperMethods.getDeliveryAddress(getActivity()).getLatitude();
            longitude = HelperMethods.getDeliveryAddress(getActivity()).getLongitude();
        } else {
//            latitude = HelperMethods.getUserLatitude(getActivity());
//            longitude = HelperMethods.getUserLongitude(getActivity());
        }

        Log.d(TAG, "getOffes: " + latitude + "," + longitude);
        HelperMethods.get1OrderAPI()
                .getOffers(HelperMethods.getAppLanguage(getActivity()),
                        latitude, longitude, HelperMethods.getCountrySettings(getActivity()).getId())
                .enqueue(new Callback<Offers>() {
                    @Override
                    public void onResponse(Call<Offers> call, Response<Offers> response) {
                        HelperMethods.dismissProgress(layoutView);
                        if (response.isSuccessful()) {
                            if (response.body().getOffers().size() > 0) {
                                listOffers.clear();
                                listOffers.addAll(response.body().getOffers());
                                HomeOffersAdapter homeOffersAdapter = new HomeOffersAdapter(getActivity(), listOffers, pagerProducts);
                                pagerProducts.setAdapter(homeOffersAdapter);
                                dotsIndicator.attachTo(pagerProducts);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Offers> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                        HelperMethods.dismissProgress(layoutView);
                    }
                });
    }

    private void getPublicShops(View layoutView) {

        HelperMethods.showProgress(layoutView);

        double latitude=0, longitude=0;

        if (HelperMethods.getUserToken(getActivity()) != null) {
            latitude = HelperMethods.getDeliveryAddress(getActivity()).getLatitude();
            longitude = HelperMethods.getDeliveryAddress(getActivity()).getLongitude();
        } else {
//            latitude = HelperMethods.getUserLatitude(getActivity());
//            longitude = HelperMethods.getUserLongitude(getActivity());
        }

        String location = new StringBuilder()
                .append(latitude)
                .append(",")
                .append(longitude)
                .toString();

        HelperMethods.getMapAPI()
                .getPublicShops(HelperMethods.getAppLanguage(getActivity()),
                        location, "distance", "restaurant,food", Const.KEY_GOOGLE_MAP)
                .enqueue(new Callback<PublicShops>() {
                    @Override
                    public void onResponse(Call<PublicShops> call, Response<PublicShops> response) {
                        HelperMethods.dismissProgress(layoutView);
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equals("OK")) {
                                listMapsShops.clear();
                                listMapsShops.addAll(response.body().getResults());
                                PublicShopsAdapter publicShopsAdapter = new PublicShopsAdapter(getActivity(), listMapsShops);
                                recyclerPublic.setAdapter(publicShopsAdapter);
                            } else {
                                HelperMethods.showCustomToast(getActivity(), response.body().getStatus(), false);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PublicShops> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: Click");
        if (view.getId() == R.id.tv_tap_1order) {
            viewTabPublic.setBackgroundResource(R.drawable.bg_tab_unselected);
            viewTab1Order.setBackgroundResource(R.drawable.bg_tab_selected);

            tvTab1Order.setTypeface(fontBold);
            tvTabPublic.setTypeface(fontSemiBold);

            tabSelect.animate().x(0).setDuration(60);

            scroll1Order.setVisibility(View.VISIBLE);
            recyclerPublic.setVisibility(View.GONE);
            searchView.setVisibility(View.GONE);
            load1OrderData(layoutView);
        } else {
            if (view.getId() == R.id.tv_tap_public) {
                viewTabPublic.setBackgroundResource(R.drawable.bg_tab_selected);
                viewTab1Order.setBackgroundResource(R.drawable.bg_tab_unselected);

                tvTab1Order.setTypeface(fontSemiBold);
                tvTabPublic.setTypeface(fontBold);

                int size = tvTab1Order.getWidth();
                tabSelect.animate().x(size).setDuration(60);

                scroll1Order.setVisibility(View.GONE);
                recyclerPublic.setVisibility(View.VISIBLE);
                getPublicShops(layoutView);
                searchView.setVisibility(View.VISIBLE);

            }
        }


    }

    @Override
    public void onResume() {
        super.onResume();

        if (PreferencesManager.getStringPreferences("from_home").equals("from_home")){
            PreferencesManager.setStringPreferences("from_home","");
            final Intent intent = getActivity().getIntent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            getActivity().finish();
            getActivity().overridePendingTransition(0, 0);
            startActivity(intent);
            getActivity().overridePendingTransition(0, 0);

        }
    }
}
