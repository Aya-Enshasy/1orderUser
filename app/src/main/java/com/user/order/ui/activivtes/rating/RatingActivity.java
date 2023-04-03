package com.user.order.ui.activivtes.rating;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.user.order.R;
import com.user.order.adapter.RatingAdapter;
import com.user.order.model.contact.ContactUs;
import com.user.order.model.orderdetails.Driver;
import com.user.order.model.rating.AddRating;
import com.user.order.model.rating.Rating;
import com.user.order.model.rating.RatingsData;
import com.user.order.model.shop.Shop;
import com.user.order.ui.activivtes.orders.OrderDetailsActiviy;
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
import retrofit2.http.Field;
import retrofit2.http.Header;

public class RatingActivity extends AppCompatActivity {
    private static final String TAG = RatingActivity.class.getSimpleName();

    @BindView(R.id.recycler_rating)
    RecyclerView recyclerRating;
    @BindView(R.id.reL_no_rate)
    RelativeLayout relNoReviews;
    private List<RatingsData> listRatings;
    private Shop shop;
    String id = "0";
    RatingAdapter adapter;
    int page = 1, limit = 10;
    boolean isLoading = false;
    boolean isLastPage = true;
    int lastVisibleItem;
    int totalItemCount;
    List<RatingsData> list;

    @OnClick(R.id.iv_back)
    void onBackClick() {
        onBackPressed();
    }

    @OnClick(R.id.btn_add_rate)
    void onAddRateClick() {
        showAddRateDialog();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        ButterKnife.bind(this);

        initUI();

    }


    private void adapter() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,
                RecyclerView.VERTICAL,false);
        recyclerRating.setLayoutManager(layoutManager);

        adapter = new RatingAdapter(this, list);

        recyclerRating.setAdapter(adapter);

        recyclerRating.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItem == (totalItemCount - 1) && !isLoading && totalItemCount != 0 && !isLastPage) {
                    page++;
                    getRatings();
                }
            }
        });


    }

    private void getRatings() {
        HelperMethods.showProgress(this);

        HelperMethods.get1OrderAPI().getRatings(HelperMethods.getUserToken(this), id)
                .enqueue(new Callback<Rating>() {
            @Override
            public void onResponse(Call<Rating> call, Response<Rating> response) {
                if (response.isSuccessful()) {
                    HelperMethods.dismissProgress(RatingActivity.this);

                    if (!response.body().getRatings().getData().isEmpty()){
                        Log.e("", response.body().getRatings().getData()+"");
                        relNoReviews.setVisibility(View.GONE);

                        list = response.body().getRatings().getData();
                        isLoading = false;
                        if (page == 1) {
                            adapter.setData(response.body().getRatings().getData());
                        }
                        else{
                            if (!list.isEmpty())
                                adapter.addToList(response.body().getRatings().getData());
                        }
                        if (response.body().getRatings().getLastPage() == page) {
                            isLastPage = true;
                            Log.e("lastPage", isLastPage + "");
                        }
                        else {
                            isLastPage = false;

                        }
                    }

                    else {
                        HelperMethods.dismissProgress(RatingActivity.this);
                        relNoReviews.setVisibility(View.VISIBLE);
                        Log.e("response", response.message()+"");

                    }}
            }
            @Override
            public void onFailure(Call<Rating> call, Throwable t) {
                call.cancel();
                Log.e("Throwable", t.getMessage()+"");
                HelperMethods.dismissProgress(RatingActivity.this);


            }
        });
    }

    private void initUI() {
        listRatings = new ArrayList<>();

        id = PreferencesManager.getStringPreferences(Const.KEY_Rate_id);

        if (getIntent() != null){
            shop = (Shop) getIntent().getSerializableExtra(Const.KEY_SHOP);
            id= String.valueOf(shop.getId());
        }


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerRating.setHasFixedSize(true);
        recyclerRating.setLayoutManager(layoutManager);

        adapter();
        getRatings();
    }

    private void showAddRateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_rate, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        HelperMethods.showCustomDialog(dialog);

        EditText inputReview = dialogView.findViewById(R.id.input_review);
        AppCompatRatingBar rateShop = dialogView.findViewById(R.id.ratingBar);

        dialogView.findViewById(R.id.iv_cancel)
                .setOnClickListener(v -> {
                    dialog.dismiss();
                });

        dialogView.findViewById(R.id.btn_send)
                .setOnClickListener(v -> {
                    String review = inputReview.getText().toString();
                    double rate = rateShop.getRating();
                    addRate(rate, review, dialog);
                });

        dialog.show();
    }

    private void addRate(Double rate, String review, AlertDialog dialog) {

        HelperMethods.get1OrderAPI()
                .addRating(HelperMethods.getAppLanguage(this),
                        HelperMethods.getUserToken(this),
                        id, rate, review)
                .enqueue(new Callback<AddRating>() {
                    @Override
                    public void onResponse(Call<AddRating> call, Response<AddRating> response) {

                        if (response.body() != null) {
                            if (response.body().isSuccess()) {
                                HelperMethods.showCustomToast(RatingActivity.this, response.body().getMessage(), true);

                            }
                        } else {
                            HelperMethods.showCustomToast(RatingActivity.this, getString(R.string.rate_message_error), false);
                        }
                        dialog.dismiss();
                        getRatings();

                    }

                    @Override
                    public void onFailure(Call<AddRating> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });

    }


}
