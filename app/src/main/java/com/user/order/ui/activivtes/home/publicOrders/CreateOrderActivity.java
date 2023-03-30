package com.user.order.ui.activivtes.home.publicOrders;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.user.order.HomeActivity;
import com.user.order.MapsActivity;
import com.user.order.R;
import com.user.order.adapter.OrderImagesAdapter;
import com.user.order.model.DeliveryAddress;
import com.user.order.model.DeliveryCost;
import com.user.order.model.ImageData;
import com.user.order.model.map.distance.LocationDistance;
import com.user.order.model.map.distance.route.Leg;
import com.user.order.model.map.publicShops.PublicShopData;
import com.user.order.model.orders.publicOrders.Attachment;
import com.user.order.model.orders.publicOrders.placeOrder.PublicPlaceOrder;
import com.user.order.ui.activivtes.auth.LoginActivity;
import com.user.order.ui.activivtes.auth.SignUpActivity;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateOrderActivity extends AppCompatActivity {
    private static final String TAG = CreateOrderActivity.class.getSimpleName();
    public Dialog routes,loader_dialog,login_dialog;

    @BindView(R.id.iv_shop_image)
    CircleImageView civShop;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_shop_distance)
    TextView tvShopDistance;
    @BindView(R.id.tv_delivery_address)
    TextView tvDeliveryAddress;
    @BindView(R.id.tv_other_address)
    TextView tvOtherAddress;
    @BindView(R.id.input_order_details)
    EditText inputOrderDetails;
    @BindView(R.id.tv_delivary_cost)
    TextView tvDelivaryCost;
    @BindView(R.id.tv_tax)
    TextView tvTax;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_text_title)
    TextView tv_text_title;
    @BindView(R.id.recycler_order_images)
    RecyclerView recyclerImages;

    private double distance;
    private double deliveryCost;
    private double countryTax;

    private PublicShopData shop;

    private List<Attachment> listImages;
    private OrderImagesAdapter imagesAdapter;

    @OnClick(R.id.card_select_image)
    void onSelectImageClick() {
        HelperMethods.selectMultipleImage(CreateOrderActivity.this, listImages, imagesAdapter);
        HelperMethods.listImagesUrls.clear();

    }

    @OnClick(R.id.iv_back)
    void onBackClick() {
        onBackPressed();
    }

    @OnClick(R.id.tv_cnahge_delivery_address)
    void onSelectLocation() {
        startActivity(new Intent(this, MapsActivity.class)
                .putExtra(Const.KEY_MAP, Const.CREATE_ORDER)
                .putExtra("order","order"));
    }

    @OnClick(R.id.btn_create_order)
    void onCreateOrderClick() {

        loaderDialog();
        String orderDetails = inputOrderDetails.getText().toString();


        if (HelperMethods.getUserToken(this)!=null&&!orderDetails.equals("")){
            createOrder(orderDetails);
        }else  if (deliveryCost == 0){
            HelperMethods.showCustomToast(this, getString(R.string.delivery_cost_required), false);
            loader_dialog.dismiss();
        }else  if (orderDetails.equals("")){
            inputOrderDetails.setError(getString(R.string.please_enter_order_details));
            loader_dialog.dismiss();
        }
        else if (HelperMethods.getUserToken(this)==null) {
            loginDialog();
            loader_dialog.dismiss();
        }




    }

    protected void loginDialog() {
        login_dialog = new Dialog(this);
        login_dialog.setContentView(R.layout.login_dialog);
        login_dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        login_dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
        login_dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        Button login = login_dialog.findViewById(R.id.btn_login);
        Button signup = login_dialog.findViewById(R.id.btn_signup);

        login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), SignUpActivity.class));
            }
        });

        login_dialog.show();
    }

    private void createOrder(String orderDetails) {

        String destinationLatitude = "", destinationLongitude = "", destinationAddress = "";
        String shopLatitude = "", shopLongitude = "", shopAddress = "";

        if (HelperMethods.getUserToken(this) != null) {
            destinationLatitude = String.valueOf(HelperMethods.getDeliveryAddress(this).getLatitude());
            destinationLongitude = String.valueOf(HelperMethods.getDeliveryAddress(this).getLongitude());
            destinationAddress = new StringBuilder()
                    .append(HelperMethods.getDeliveryAddress(this).getAddress())
                    .append(", ")
                    .append(HelperMethods.getDeliveryAddress(this).getOtherAddress())
                    .toString();
        }

        if (shop != null) {
            shopLatitude = String.valueOf(shop.getGeometry().getLocation().getLatitude());
            shopLongitude = String.valueOf(shop.getGeometry().getLocation().getLongitude());
            shopAddress = new StringBuilder()
                    .append(shop.getVicinity())
                    .toString();
        }


        List<MultipartBody.Part> images = new ArrayList<>();
        for (int i = 0; i < listImages.size(); i++)
            images.add(HelperMethods.convertFileToMultiPart(listImages.get(i).getImageUrl(), "attachment[]"));

        RequestBody shopName = HelperMethods.convertToRequestBody(shop.getName());
        RequestBody deliveryCostBody = HelperMethods.convertToRequestBody(String.valueOf(deliveryCost));
        RequestBody taxBody = HelperMethods.convertToRequestBody(String.valueOf(countryTax));
        RequestBody orderDetailsBody = HelperMethods.convertToRequestBody(String.valueOf(orderDetails));
        RequestBody destinationLat = HelperMethods.convertToRequestBody(destinationLatitude);
        RequestBody destinationLng = HelperMethods.convertToRequestBody(destinationLongitude);
        RequestBody destinationAddressBody = HelperMethods.convertToRequestBody(destinationAddress);
        RequestBody shopLat = HelperMethods.convertToRequestBody(shopLatitude);
        RequestBody shopLng = HelperMethods.convertToRequestBody(shopLongitude);
        RequestBody shopAddressBody = HelperMethods.convertToRequestBody(shopAddress);
        RequestBody countryId = HelperMethods.convertToRequestBody(String.valueOf(HelperMethods.getCountrySettings(this).getId()));

        HelperMethods.get1OrderAPI()
                .createOrder(HelperMethods.getAppLanguage(this),
                        HelperMethods.getUserToken(this),
                        images, shopName, deliveryCostBody, taxBody,
                        orderDetailsBody, destinationLat, destinationLng, destinationAddressBody,
                        shopLat, shopLng, shopAddressBody, countryId)
                .enqueue(new Callback<PublicPlaceOrder>() {
                    @Override
                    public void onResponse(Call<PublicPlaceOrder> call, Response<PublicPlaceOrder> response) {
                        loader_dialog.dismiss();
                        if(response.isSuccessful()){
                            if(response.body().isSuccess()){

                                HelperMethods.showCustomToast(CreateOrderActivity.this, response.body().getMessage(), true);
                                startActivity(new Intent(CreateOrderActivity.this, HomeActivity.class));
                                finish();
                            }else {
                                HelperMethods.showCustomToast(CreateOrderActivity.this, response.body().getMessage(), false);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PublicPlaceOrder> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                        loader_dialog.dismiss();
                    }
                });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {

        if (getIntent() != null)
            shop = (PublicShopData) getIntent().getSerializableExtra(Const.KEY_PUBLIC_SHOPS);


        listImages = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerImages.setHasFixedSize(true);
        recyclerImages.setLayoutManager(layoutManager);


        imagesAdapter = new OrderImagesAdapter(this, listImages, R.layout.row_order_add_images);
        recyclerImages.setAdapter(imagesAdapter);
    }

    private void getShopDistance() {

        loaderDialog();

        if (shop != null) {
            if (shop.getIcon() != null)
                Glide.with(this).load(shop.getIcon()).placeholder(R.drawable.app_logo).into(civShop);

            tvShopName.setText(shop.getName());
            tv_text_title.setText(shop.getName());
            if (HelperMethods.getDeliveryAddress(this) != null) {

                DeliveryAddress address = HelperMethods.getDeliveryAddress(this);
                Log.e(TAG, "getShopDistance: " + new Gson().toJson(address));
                tvDeliveryAddress.setText(address.getAddress());
                tvOtherAddress.setText(address.getOtherAddress());
            }
        }

        double latitude, longitude;

        if (HelperMethods.getUserToken(this) != null) {
            Log.e(TAG, "getShopDistance: " + HelperMethods.getDeliveryAddress(this).getAddress());
            latitude = HelperMethods.getDeliveryAddress(this).getLatitude();
            longitude = HelperMethods.getDeliveryAddress(this).getLongitude();
        } else {
            latitude = HelperMethods.getUserLatitude(this);
            longitude = HelperMethods.getUserLongitude(this);
        }

        String userLocation = new StringBuilder()
                .append(latitude)
                .append(",")
                .append(longitude)
                .toString();

        String shopLocation = new StringBuilder()
                .append(shop.getGeometry().getLocation().getLatitude())
                .append(",")
                .append(shop.getGeometry().getLocation().getLongitude())
                .toString();

        HelperMethods.getMapAPI()
                .getDistance(userLocation, shopLocation, "driving", Const.KEY_GOOGLE_MAP)
                .enqueue(new Callback<LocationDistance>() {
                    @Override
                    public void onResponse(Call<LocationDistance> call, Response<LocationDistance> response) {
                        loader_dialog.dismiss();
                        if (response.isSuccessful()) {
                                 if (response.body().getRoutes()==null){
                                    noRoutesDialog();
                                }

                                Leg leg = response.body().getRoutes().get(0).getLegs().get(0);
                                if (leg.getDistance().getValue() == 0) {
                                    HelperMethods.showCustomToast(CreateOrderActivity.this, "Please select another location", false);
                                } else {
                                     if (leg.getDistance().getText().endsWith("km")) {
                                         tvShopDistance.setText(leg.getDistance().getText());
                                        String dis = leg.getDistance().getText().replace(" km", "");
                                        distance = Double.valueOf(dis);
                                        getDeliveryCost(distance);
                                     } else {
                                         tvShopDistance.setText(HelperMethods.convertMeterToKilometer(CreateOrderActivity.this, leg.getDistance().getValue()));
                                        String dis = leg.getDistance().getText().replace(" m", "");
                                        distance = Double.valueOf(dis);
                                        getDeliveryCost(distance);
                                     }
                                }
                                Log.e("yyyyyyy: " , new Gson().toJson(leg));

                            }
                     }

                    @Override
                    public void onFailure(Call<LocationDistance> call, Throwable t) {
                        Log.e(  "onFailure: " , t.getMessage());
                        loader_dialog.dismiss();
                    }
                });

    }

    private void getDeliveryCost(double distance) {
        loaderDialog();
        HelperMethods.get1OrderAPI()
                .getDeliveryCost(HelperMethods.getAppLanguage(this),
                        HelperMethods.getUserToken(this),
                        HelperMethods.getCountrySettings(this).getId(),
                        distance)
                .enqueue(new Callback<DeliveryCost>() {
                    @Override
                    public void onResponse(Call<DeliveryCost> call, Response<DeliveryCost> response) {
                        loader_dialog.dismiss();
                        if (response.isSuccessful()) {
                            if (response.body().isStatus()) {
                                int defTax = Integer.parseInt(HelperMethods.getCountrySettings(CreateOrderActivity.this).getSetting().getTax());
                                double cost = response.body().getDeliveryCost();
                                 double tax = (defTax / 100.0) * cost;
                                double total = tax + cost;
                                deliveryCost = cost;
                                countryTax = tax;

                                String deliveryCost = new StringBuilder()
                                        .append(cost)
                                        .toString();

                                String taxCost = new StringBuilder()
                                        .append(tax)
                                        .append(" ")
                                        .append(HelperMethods.getCurrency(CreateOrderActivity.this))
                                        .toString();

                                String totalCost = new StringBuilder()
                                        .append(total)
                                        .append(" ")
                                        .append(HelperMethods.getCurrency(CreateOrderActivity.this))
                                        .toString();
                                Log.e("getDeliveryCost cost: " , response.body().getDeliveryCost()+"");

                                tvDelivaryCost.setText(response.body().getDeliveryCost()+" "+HelperMethods.getCurrency(CreateOrderActivity.this));
                                tvTax.setText(taxCost);
                                tvTotal.setText(totalCost);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DeliveryCost> call, Throwable t) {
                        Log.e(TAG,   t.getMessage());
//                        loader_dialog.dismiss();
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getShopDistance();
    }

    protected void noRoutesDialog() {
        routes = new Dialog(this);
        routes.setContentView(R.layout.routes_dialog);
        routes.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        routes.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
        routes.getWindow().getAttributes().windowAnimations = R.style.animation;

        routes.findViewById(R.id.cancel)
                .setOnClickListener(view -> routes.dismiss());

        routes.findViewById(R.id.change)
                .setOnClickListener(v -> {
                    startActivity(new Intent(getBaseContext(),MapsActivity.class) .putExtra("order","order"));
                });

        routes.show();
    }

    protected void loaderDialog() {
        loader_dialog = new Dialog(this);
        loader_dialog.setContentView(R.layout.loader_dialog);
        loader_dialog.setCancelable(false);
        loader_dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER);
        loader_dialog.getWindow().setBackgroundDrawableResource(R.drawable.transparent);
        loader_dialog.show();
    }
}
