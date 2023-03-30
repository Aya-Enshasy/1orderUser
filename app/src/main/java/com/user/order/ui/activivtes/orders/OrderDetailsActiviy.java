package com.user.order.ui.activivtes.orders;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.user.order.listeners.ItemClickListener2;
import com.user.order.listeners.ItemClickListenerDouble;
import com.user.order.model.order.OneOrderData;
import com.user.order.model.orderdetails.Driver;
import com.user.order.ui.activivtes.tracking.DirectionMapActivity;
import com.user.order.R;
import com.user.order.adapter.CancelRadioAdapter;
import com.user.order.databinding.ActivityOrderDetailsBinding;
import com.user.order.fcm.MyEventBus;
import com.user.order.listeners.RadioListener;
import com.user.order.listeners.StringInterface;
import com.user.order.model.cancel.CancelOrderReasons;
import com.user.order.model.cancel.CancelReasons;
import com.user.order.model.contact.ContactUs;
import com.user.order.model.order1.Orders1;
import com.user.order.ui.activivtes.rating.RatingActivity;
import com.user.order.ui.activivtes.tracking.MapTrackingActivity;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsActiviy extends AppCompatActivity {

    ActivityOrderDetailsBinding binding;
    private OneOrderData orderDetails;
    private List<com.user.order.model.order1.Item> list;
    private ExtrasOrdersAdapter adapter;
    private OrderItemsAdapter orderItemsAdapter;
    public Dialog loader_dialog, cancel_dialog, extraItemsDialog;
    private List<CancelOrderReasons> cancelOrderReasonsList;
    private int reason_id, page = 1, limit = 10;
    private CancelRadioAdapter cancelRadioAdapter;
    private String orderId, invoiceNumber, driver_id, status, content_id, type, destinationLat, destinationLng, storeLat, storeLng, res_number, user_id, total_items_price, public_order_id;
    private boolean isRated = false;
    private Driver driver;
    String name,mobile,img,vehicleType,vehiclePlate,rate;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();
        clickListeners();
        getCancelOrderReasonsApi();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initUI() {

        if (getIntent() != null)
            orderDetails = (OneOrderData) getIntent().getSerializableExtra(Const.KEY_ORDER);

        if (getIntent() != null)
            orderId = getIntent().getStringExtra(Const.KEY_ORDER_ID);

        if (orderId == null&&orderDetails!=null) {
            getOrderDetails(orderDetails);
            setAdapter();
            status = orderDetails.getStatus();
            type = orderDetails.getTypeOfReceive();
            step();
        } else {
            order(orderId);
        }



    }

    private void getOrderDetails(OneOrderData orderDetails) {

        if (orderDetails != null) {

            if (orderDetails.getShop().getLogoUrl() != null)
                Glide.with(this).load(orderDetails.getShop().getLogoUrl()).placeholder(R.drawable.app_logo).into(binding.ivShopImage);
            orderId = String.valueOf(orderDetails.getId());
            content_id = orderDetails.getShop().getContentId();
            binding.tvShopName.setText(orderDetails.getShop().getName());
            binding.tvShopAddress.setText(orderDetails.getShop().getAddress());
            isRated = orderDetails.isRated();
            double price = Double.valueOf(orderDetails.getTotal()) - Double.valueOf(orderDetails.getTax()) - Double.valueOf(orderDetails.getDelivery()) - Double.valueOf(orderDetails.getDiscount())  ;

            if (orderDetails.getDriver() != null) {
                name = orderDetails.getDriver().getName();
                mobile = orderDetails.getDriver().getMobile();
                img = orderDetails.getDriver().getAvatarUrl();
                vehicleType = orderDetails.getDriver().getVehicleType();
                vehiclePlate = orderDetails.getDriver().getVehiclePlate();
                driver = orderDetails.getDriver();
                rate = orderDetails.getDriver().getRate();
                driver_id = String.valueOf(orderDetails.getDriver().getId());

            }

            String productPrice = new StringBuilder()
                    .append(new DecimalFormat("#.#").format(price))
                    .append(" ")
                    .append(HelperMethods.getCurrency(this))
                    .toString();

            String discount = new StringBuilder()
                    .append(orderDetails.getDiscount())
                    .append(" ")
                    .append(HelperMethods.getCurrency(this))
                    .toString();

            String deliveryCost = new StringBuilder()
                    .append(orderDetails.getDelivery())
                    .append(" ")
                    .append(HelperMethods.getCurrency(this))
                    .toString();

            String tax = new StringBuilder()
                    .append(orderDetails.getTax())
                    .append(" ")
                    .append(HelperMethods.getCurrency(this))
                    .toString();

            String totalCost = new StringBuilder()
                    .append(orderDetails.getTotal())
                    .append(" ")
                    .append(HelperMethods.getCurrency(this))
                    .toString();

            binding.price.setText(productPrice);
            binding.discount.setText(discount);
            binding.tax.setText(tax);
            binding.delivary.setText(deliveryCost);
            binding.totalPrice.setText(totalCost);
            destinationLng = orderDetails.getDestinationLng();
            destinationLat = orderDetails.getDestinationLat();
            storeLat = orderDetails.getStoreLat();
            storeLng = orderDetails.getStoreLng();
            res_number = orderDetails.getShop().getMobile();
            invoiceNumber = orderDetails.getInvoiceNumber();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void step() {
        if (type.equals("home")) {
            binding.stepView.setSteps(new ArrayList<String>() {{
                add(getString(R.string.pending));
                add(getString(R.string.preparing));
                add(getString(R.string.ready));
                add(getString(R.string.on_way));
                add(getString(R.string.delivered));
            }});
            binding.stepView.getState()
                    .nextStepCircleColor(getColor(R.color.colorInput))
                    .commit();

            if (status.equals("pending")) {
                binding.stepView.go(0, true);
                binding.btnCancelOrder.setVisibility(View.VISIBLE);
                binding.btnRate.setVisibility(View.GONE);
                binding.btnTraking.setVisibility(View.GONE);
                binding.btnDirection.setVisibility(View.GONE);
            } else if (status.equals("preparing")) {
                binding.stepView.go(1, true);
                binding.btnRate.setVisibility(View.GONE);
                binding.btnTraking.setVisibility(View.GONE);
                binding.btnDirection.setVisibility(View.GONE);
                binding.btnCancelOrder.setVisibility(View.VISIBLE);
            } else if (status.equals("ready")) {
                binding.stepView.go(2, true);
                binding.btnRate.setVisibility(View.GONE);
                binding.btnTraking.setVisibility(View.GONE);
                binding.btnDirection.setVisibility(View.GONE);
                binding.btnCancelOrder.setVisibility(View.VISIBLE);
            } else if (status.equals("on_the_way")) {
                binding.stepView.go(3, true);
                binding.btnRate.setVisibility(View.GONE);
                binding.btnDirection.setVisibility(View.GONE);
                binding.btnCancelOrder.setVisibility(View.GONE);
                binding.btnTraking.setVisibility(View.VISIBLE);
            } else if (status.equals("delivered")) {
                binding.stepView.go(4, true);
                binding.btnRate.setVisibility(View.VISIBLE);
                binding.btnCancelOrder.setVisibility(View.GONE);
                binding.btnDirection.setVisibility(View.GONE);
                binding.btnTraking.setVisibility(View.GONE);
            } else if (status.equals("cancelled")) {
                binding.textView11.setVisibility(View.VISIBLE);
                binding.stepView.getState()
                        .selectedTextColor(getColor(R.color.colorFindMore))
                        .selectedCircleColor(getColor(R.color.colorInput))
                        .nextStepCircleColor(getColor(R.color.colorInput))
                        .commit();
                binding.stepView.go(0, true);
                binding.btnTraking.setVisibility(View.GONE);
                binding.btnCancelOrder.setVisibility(View.GONE);
                binding.btnRate.setVisibility(View.GONE);
                binding.btnDirection.setVisibility(View.GONE);
            }

        } else {
            binding.stepView.setSteps(new ArrayList<String>() {{
                add(getString(R.string.pending));
                add(getString(R.string.preparing));
                add(getString(R.string.ready));
                add(getString(R.string.delivered));
            }});

            if (status.equals("pending")) {
                binding.stepView.go(0, true);
                binding.btnRate.setVisibility(View.GONE);
                binding.btnTraking.setVisibility(View.GONE);
            } else if (status.equals("preparing")) {
                binding.stepView.go(1, true);
                binding.btnRate.setVisibility(View.GONE);
                binding.btnTraking.setVisibility(View.GONE);
            } else if (status.equals("ready")) {
                binding.stepView.go(2, true);
                binding.btnRate.setVisibility(View.GONE);
                binding.btnTraking.setVisibility(View.GONE);
                binding.btnCancelOrder.setVisibility(View.GONE);
            } else if (status.equals("delivered")) {
                binding.stepView.go(3, true);

                binding.btnRate.setVisibility(View.GONE);
                binding.btnTraking.setVisibility(View.GONE);
                binding.btnCancelOrder.setVisibility(View.GONE);
            } else if (status.equals("cancelled")) {
                binding.stepView.getState()
                        .selectedTextColor(getColor(R.color.colorFindMore))
                        .selectedCircleColor(getColor(R.color.colorInput))
                        .nextStepCircleColor(getColor(R.color.colorInput))
                        .commit();
                binding.textView11.setVisibility(View.VISIBLE);
                binding.stepView.go(0, true);
                binding.btnTraking.setVisibility(View.GONE);
                binding.btnCancelOrder.setVisibility(View.GONE);
                binding.btnRate.setVisibility(View.GONE);
                binding.btnDirection.setVisibility(View.GONE);
            }

        }
    }

    private void setAdapter() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        binding.recyclerview.setLayoutManager(layoutManager);

        if (orderDetails !=null) {
            orderItemsAdapter = new OrderItemsAdapter(this, orderDetails.getItems(), new StringInterface() {
                @Override
                public void onItemClick(String total, String id) {
                    total_items_price = total;
                    public_order_id = id;
                    extraItemsDialog();
                    extraItemApi();
                }
            });

            binding.recyclerview.setAdapter(orderItemsAdapter);
        }
    }

    private void order(String id) {
        loaderDialog();
        HelperMethods.get1OrderAPI().order(HelperMethods.getUserToken(this), HelperMethods.getAppLanguage(this), id).enqueue(new Callback<Orders1>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<Orders1> call, Response<Orders1> response) {

                Log.e("", response.code() + "");
                if (response.isSuccessful()) {
                    loader_dialog.dismiss();
                    if (response.body().getOrder().getDriver()!= null) {
                    name = response.body().getOrder().getDriver().getName();
                    mobile = response.body().getOrder().getDriver().getMobile();
                    img = response.body().getOrder().getDriver().getAvatarUrl();
                    vehicleType = response.body().getOrder().getDriver().getVehicleType();
                    vehiclePlate = response.body().getOrder().getDriver().getVehiclePlate();
                    rate = response.body().getOrder().getDriver().getRate();}
//                    orderId = String.valueOf(response.body().getOrder().getId());
                    list = response.body().getOrder().getItems();
                    LinearLayoutManager layoutManager = new LinearLayoutManager(OrderDetailsActiviy.this,
                            RecyclerView.VERTICAL, false);
                    binding.recyclerview.setLayoutManager(layoutManager);
                    isRated = response.body().getOrder().getIsRated();
                    destinationLng = response.body().getOrder().getDestinationLng();
                    destinationLat = response.body().getOrder().getDestinationLat();
                    storeLat = response.body().getOrder().getStoreLat();
                    storeLng = response.body().getOrder().getStoreLng();

                    res_number = response.body().getOrder().getStore().getMobile();
                    orderItemsAdapter = new OrderItemsAdapter(OrderDetailsActiviy.this, list, new StringInterface() {
                        @Override
                        public void onItemClick(String total, String id) {
                            total_items_price = total;
                            public_order_id = id;
                            extraItemsDialog();
                            extraItemApi();
                        }
                    });

                    binding.recyclerview.setAdapter(orderItemsAdapter);

                    Glide.with(OrderDetailsActiviy.this).load(response.body().getOrder().getStore().getLogoUrl()).placeholder(R.drawable.app_logo).into(binding.ivShopImage);

                    binding.tvShopName.setText(response.body().getOrder().getStore().getName());
                    binding.tvShopAddress.setText(response.body().getOrder().getStore().getAddress());

                    status = response.body().getOrder().getStatus();
                    type = response.body().getOrder().getTypeOfReceive();
                    step();

                    double price = Double.valueOf(response.body().getOrder().getTotal()) - Double.valueOf(response.body().getOrder().getTax());
                    String productPrice = new StringBuilder()
                            .append(new DecimalFormat("#.#").format(price))
                            .append(" ")
                            .append(HelperMethods.getCurrency(OrderDetailsActiviy.this))
                            .toString();

                    String discount = new StringBuilder()
                            .append(response.body().getOrder().getDiscount())
                            .append(" ")
                            .append(HelperMethods.getCurrency(OrderDetailsActiviy.this))
                            .toString();

                    String deliveryCost = new StringBuilder()
                            .append(response.body().getOrder().getDelivery())
                            .append(" ")
                            .append(HelperMethods.getCurrency(OrderDetailsActiviy.this))
                            .toString();

                    String tax = new StringBuilder()
                            .append(response.body().getOrder().getTax())
                            .append(" ")
                            .append(HelperMethods.getCurrency(OrderDetailsActiviy.this))
                            .toString();

                    String totalCost = new StringBuilder()
                            .append(response.body().getOrder().getTotal())
                            .append(" ")
                            .append(HelperMethods.getCurrency(OrderDetailsActiviy.this))
                            .toString();

                    binding.price.setText(productPrice);
                    binding.discount.setText(discount);
                    binding.tax.setText(tax);
                    binding.delivary.setText(deliveryCost);
                    binding.totalPrice.setText(totalCost);
                    if (response.body().getOrder().getDriver()!=null){

                        driver = response.body().getOrder().getDriver();
                        driver_id = response.body().getOrder().getDriver().getId()+"";}
                    invoiceNumber = response.body().getOrder().getInvoiceNumber();
                    content_id = response.body().getOrder().getStore().getContentId();
                }else {  loader_dialog.dismiss();}
            }

            @Override
            public void onFailure(Call<Orders1> call, Throwable t) {
                loader_dialog.dismiss();
                call.cancel();
                Log.e("Throwable", t.getMessage() + "");


            }
        });
    }

    private void extraItemApi() {
        HelperMethods.get1OrderAPI().orders(orderId,HelperMethods.getUserToken(this), limit, page, HelperMethods.getAppLanguage(this)).enqueue(new Callback<Orders1>() {
            @Override
            public void onResponse(Call<Orders1> call, Response<Orders1> response) {
                Log.e("", response.code() + "");
                if (response.isSuccessful()) {
                    list = response.body().getOrder().getItems();
                    adapter.setData(list);
                } else
                    Log.e("message", response.message());
            }

            @Override
            public void onFailure(Call<Orders1> call, Throwable t) {
                call.cancel();
                Log.e("Throwable", t.getMessage() + "");


            }
        });
    }

    private void extraItemsDialog() {
        extraItemsDialog = new Dialog(this);
        extraItemsDialog.setContentView(R.layout.extras_dialog);
        extraItemsDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        extraItemsDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
        extraItemsDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        String currency_code = HelperMethods.getCurrency(this);

        RecyclerView recyclerView = extraItemsDialog.findViewById(R.id.recyclerView);
        TextView total_price = extraItemsDialog.findViewById(R.id.total_price);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        if(list!=null&&list.size() > 0)
            list.clear();
        adapter = new ExtrasOrdersAdapter(this, list, new ItemClickListenerDouble() {
            @Override
            public void onItemClick(Double id) {

                total_price.setText(id + " " + currency_code);

            }
        });

        recyclerView.setAdapter(adapter);

        extraItemsDialog.show();
    }

    private void clickListeners() {
        binding.btnCancelOrder.setOnClickListener(View -> {
            showCancelOrderDialog();
        });

        binding.btnDirection.setOnClickListener(View -> {
            startActivity(new Intent(getBaseContext(), DirectionMapActivity.class)
                    .putExtra(Const.KEY_Destination_Lat, destinationLat)
                    .putExtra(Const.KEY_Destination_Lng, destinationLng)
                    .putExtra(Const.KEY_Store_Lat, storeLat)
                    .putExtra(Const.KEY_Store_Lng, storeLng)
                    .putExtra(Const.KEY_call, res_number)

            );
        });
        if (isRated == false) {
        binding.btnRate.setOnClickListener(View -> {

                showAddRateDialog();

        });
        } else {
        binding.btnRate.setVisibility(View.GONE);

    }

        binding.btnTraking.setOnClickListener(View -> {
            startActivity(new Intent(getBaseContext(), MapTrackingActivity.class)
                    .putExtra(Const.KEY_order_id, orderId)
                    .putExtra(Const.KEY_invoice_num, invoiceNumber)
                    .putExtra(Const.KEY_status, status)
                    .putExtra(Const.KEY_driver_id, driver_id)
                    .putExtra(Const.KEY_Store_Lat, destinationLat)
                    .putExtra(Const.KEY_Store_Lng, destinationLng));

            PreferencesManager.setStringPreferences("driver_name",name);
            PreferencesManager.setStringPreferences("mobile",mobile);
            PreferencesManager.setStringPreferences("img",img);
            PreferencesManager.setStringPreferences("vehicleType",vehicleType);
            PreferencesManager.setStringPreferences("car_number",vehiclePlate);
            PreferencesManager.setStringPreferences("rate",rate);
            PreferencesManager.setStringPreferences(Const.KEY_Store_Lat,destinationLat);
            PreferencesManager.setStringPreferences(Const.KEY_Store_Lng,destinationLng);
            PreferencesManager.setStringPreferences(Const.KEY_ORDER_ID,orderId);


        });

        binding.ivBack.setOnClickListener(View -> {
            onBackPressed();
        });
    }

    private void showCancelOrderDialog() {
        cancel_dialog = new Dialog(this);
        cancel_dialog.setContentView(R.layout.cancel_resone_dialog);
        cancel_dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cancel_dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
        cancel_dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        RecyclerView recyclerView = cancel_dialog.findViewById(R.id.recyclerview);

        list = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        cancelRadioAdapter = new CancelRadioAdapter(this, cancelOrderReasonsList, new RadioListener() {
            @Override
            public void onClick(int id) {
                reason_id = id;
            }
        });
        recyclerView.setAdapter(cancelRadioAdapter);
        getCancelOrderReasonsApi();

        cancel_dialog.findViewById(R.id.ok_button).setOnClickListener(v -> {

            HelperMethods.get1OrderAPI().cancelOrder1(HelperMethods.getUserToken(this), orderId, String.valueOf(reason_id)).enqueue(new Callback<ContactUs>() {
                @Override
                public void onResponse(Call<ContactUs> call, Response<ContactUs> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(OrderDetailsActiviy.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                        cancel_dialog.dismiss();
                    } else {
                        parseError(response);
                    }

                }

                @Override
                public void onFailure(Call<ContactUs> call, Throwable t) {
                    Log.e("TAG", "onFailure: " + t.getMessage());
                }
            });

        });

        cancel_dialog.show();
    }

    private void getCancelOrderReasonsApi() {

        HelperMethods.get1OrderAPI()
                .getCancelReasons(HelperMethods.getAppLanguage(this), HelperMethods.getUserToken(this))
                .enqueue(new Callback<CancelReasons>() {
                    @Override
                    public void onResponse(Call<CancelReasons> call, Response<CancelReasons> response) {
                        if (response.isSuccessful()) {
                            cancelOrderReasonsList = response.body().getCancelOrderReasonsList();
                            // cancelRadioAdapter.setData(cancelOrderReasonsList);
                            Log.e("TAG", response.code() + "");
                        } else {
                            parseError(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<CancelReasons> call, Throwable t) {
                        Log.e("TAG", "onFailure: " + t.getMessage());
                    }
                });
    }

    @SuppressLint("SetTextI18n")
    public void parseError(Response<?> response) {
        String message = "";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response.errorBody().string());
            message = jsonObject.getString("message");
            Toast.makeText(OrderDetailsActiviy.this, message, Toast.LENGTH_LONG).show();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    protected void loaderDialog() {
        loader_dialog = new Dialog(this);
        loader_dialog.setContentView(R.layout.loader_dialog);
        loader_dialog.setCancelable(false);
        loader_dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER);
        loader_dialog.getWindow().setBackgroundDrawableResource(R.drawable.transparent);
        loader_dialog.show();
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
        Log.e("eventBus.getStatus()", eventBus.getType_of_receive());
        Log.e("eventBus.getStatus()", eventBus.getStatus());
        status = eventBus.getStatus();
        type = eventBus.getType_of_receive();
        orderId = eventBus.getOrder_id();
       // order(eventBus.getOrder_id());
        step();

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
                    if (driver_id != null)
                        addRate(rate, review, dialog);
                });

        dialog.show();
    }

    private void addRate(Double rate, String review, AlertDialog dialog) {

        HelperMethods.get1OrderAPI()
                .rateDeliveryDriver(HelperMethods.getAppLanguage(this),
                        HelperMethods.getUserToken(this), driver_id, rate, review, "private", orderId)
                .enqueue(new Callback<ContactUs>() {
                    @Override
                    public void onResponse(Call<ContactUs> call, Response<ContactUs> response) {

                        if (response.body() != null) {
                            HelperMethods.showCustomToast(OrderDetailsActiviy.this, response.body().getMessage(), true);
                            isRated = true;
                            binding.btnRate.setVisibility(View.GONE);
                        } else {
                            HelperMethods.showCustomToast(OrderDetailsActiviy.this, getString(R.string.rate_message_error), false);
                        }
                        dialog.dismiss();

                    }

                    @Override
                    public void onFailure(Call<ContactUs> call, Throwable t) {
                        Log.e("TAG", "onFailure: " + t.getMessage());
                    }
                });

    }
}
