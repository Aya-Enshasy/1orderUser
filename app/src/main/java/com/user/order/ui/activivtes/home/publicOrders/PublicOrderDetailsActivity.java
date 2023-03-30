package com.user.order.ui.activivtes.home.publicOrders;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.user.order.R;
import com.user.order.adapter.CancelRadioAdapter;
import com.user.order.adapter.OrderImagesAdapter;
import com.user.order.fcm.MyEventBus;
import com.user.order.listeners.RadioListener;
import com.user.order.model.cancel.CancelOrderReasons;
import com.user.order.model.cancel.CancelReasons;
import com.user.order.model.contact.ContactUs;
import com.user.order.model.orderdetails.OrderDetails;
import com.user.order.model.orders.publicOrders.Attachment;
import com.user.order.model.orders.publicOrders.OrderData;
import com.user.order.ui.activivtes.chat.ChatActivity;
import com.user.order.ui.activivtes.orders.OrderDetailsActiviy;
import com.user.order.ui.activivtes.orders.PublicPaymentActivity;
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
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicOrderDetailsActivity extends AppCompatActivity {
    private static final String TAG = PublicOrderDetailsActivity.class.getSimpleName();

    @BindView(R.id.tv_text_title)
    TextView tvTtitle;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_location_address)
    TextView tvShopAddress;
    @BindView(R.id.tv_user_location_address)
    TextView tvUserAddress;
    @BindView(R.id.tv_order_description)
    TextView tvOrderDescription;
    @BindView(R.id.recycler_order_images)
    RecyclerView recyclerImages;
    @BindView(R.id.tv_product_price)
    TextView tvPrice;
    @BindView(R.id.tv_tax)
    TextView tvTax;
    @BindView(R.id.tv_delivary)
    TextView tvDelivary;
    @BindView(R.id.tv_total_price)
    TextView tvTotal;
    @BindView(R.id.tv_text_order_images)
    TextView tv_text_order_details;
    @BindView(R.id.btn_go_pay)
    Button goToPay;
    @BindView(R.id.btn_location)
    Button location;
    @BindView(R.id.btn_cancel_order)
    Button cancel;
    @BindView(R.id.iv_chat)
    ImageView ivChat;

    private OrderData orderDetails;
    private String orderId, clientId, InvoiceNumber;
    private Dialog cancel_dialog;
    private List<Attachment> listImages;
    private OrderImagesAdapter imagesAdapter;
    private int reason_id;
    private List<CancelOrderReasons> cancelOrderReasonsList;
    private CancelRadioAdapter cancelRadioAdapter;
    String status;
    String name,mobile,img,vehicleType,vehiclePlate,rate="0",destinationLat, destinationLng,invoice_num,driverId;
    public Dialog loader_dialog;

    @OnClick(R.id.iv_back)
    void onBackClick() {
        onBackPressed();
    }

    @OnClick(R.id.btn_cancel_order)
    void onCancelOrderClick() {
    }

    @OnClick(R.id.iv_chat)
    void onChatClick() {
        startActivity(new Intent(this, ChatActivity.class)
                .putExtra(Const.KEY_INVOICE_NUMBER, InvoiceNumber)
                .putExtra(Const.KEY_CLIENT_ID, clientId)
                .putExtra(Const.KEY_ORDER_ID, orderId)
                .putExtra(Const.KEY_PUBLIC_CHAT, Const.KEY_PUBLIC_CHAT)
                .putExtra(Const.KEY_STATUS, status)
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_order_details);
        ButterKnife.bind(this);
        initUI();
        getCancelOrderReasonsApi();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initUI() {

        if (getIntent() != null) {
            orderDetails = (OrderData) getIntent().getSerializableExtra(Const.KEY_PUBLIC_ORDER);
        }
        if (getIntent() != null)
            orderId = getIntent().getStringExtra(Const.KEY_ORDER_ID);

        if (orderId == null) {
            getOrderDetails();
        } else {
            getOrderDetailsFromNotification();
        }

        listImages = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerImages.setHasFixedSize(true);
        recyclerImages.setLayoutManager(layoutManager);


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getOrderDetails() {

        if (orderDetails != null) {
            if (orderDetails.getDriver() != null) {
                name = orderDetails.getDriver().getName();
                mobile = orderDetails.getDriver().getMobile();
                img = orderDetails.getDriver().getAvatarUrl();
                vehicleType = orderDetails.getDriver().getVehicleType();
                vehiclePlate = orderDetails.getDriver().getVehiclePlate();
                rate = orderDetails.getDriver().getRate();
                driverId = String.valueOf(orderDetails.getDriver().getId());

            }  invoice_num = orderDetails.getInvoiceNumber();
            orderId = String.valueOf(orderDetails.getId());
            tvTtitle.setText(new StringBuilder().append("#").append(orderDetails.getInvoiceNumber()));
            tvShopName.setText(orderDetails.getStoreName());
            tvShopAddress.setText(orderDetails.getStoreAddress());
            tvUserAddress.setText(orderDetails.getDestinationAddress());
            tvOrderDescription.setText(orderDetails.getNote());
            status = orderDetails.getStatus();
            if (orderDetails.getDriver()!=null)
            clientId = orderDetails.getDriver().getId()+"";
            InvoiceNumber = orderDetails.getInvoiceNumber();
            String clientPaidInvoice = orderDetails.getClientPaidInvoice();
            Log.e("status1", status);
            Log.e("status1", orderDetails.getPurchaseInvoiceValue());
            destinationLng = orderDetails.getDestinationLng();
            destinationLat = orderDetails.getDestinationLat();
            if (status.equals("cancelled")) {
                goToPay.setVisibility(View.GONE);
                location.setVisibility(View.GONE);
                cancel.setVisibility(View.VISIBLE);
                cancel.setText(R.string.canceled);
                cancel.setTextColor(this.getColor(R.color.red));
                cancel.setBackgroundResource(R.drawable.cancel_btn2);
            } else if (status.equals("in_the_way_to_client")) {
                goToPay.setVisibility(View.GONE);
                location.setVisibility(View.VISIBLE);
                location.setOnClickListener(View -> {
                    startActivity(new Intent(getBaseContext(), MapTrackingActivity.class));
                    PreferencesManager.setStringPreferences("driver_name",name);
                    PreferencesManager.setStringPreferences("mobile",mobile);
                    PreferencesManager.setStringPreferences("img",img);
                    PreferencesManager.setStringPreferences("vehicleType",vehicleType);
                    PreferencesManager.setStringPreferences("car_number",vehiclePlate);
                    PreferencesManager.setStringPreferences("rate",rate);
                    PreferencesManager.setStringPreferences(Const.KEY_Store_Lat,destinationLat);
                    PreferencesManager.setStringPreferences(Const.KEY_Store_Lng,destinationLng);
                    PreferencesManager.setStringPreferences(Const.KEY_INVOICE_NUMBER,invoice_num);
                    PreferencesManager.setStringPreferences(Const.KEY_CLIENT_ID,driverId);
                    PreferencesManager.setStringPreferences(Const.KEY_ORDER_ID,orderId);
                    PreferencesManager.setStringPreferences(Const.KEY_STATUS,status);

                });
                cancel.setVisibility(View.GONE);
            } else if (status.equals("pending")) {
                goToPay.setVisibility(View.GONE);
                location.setVisibility(View.GONE);
                cancel.setVisibility(View.VISIBLE);
                ivChat.setVisibility(View.GONE);

                cancel.setOnClickListener(View -> {
                    showCancelOrderDialog();
                });
            }
            else if (status.equals("in_the_way_to_store")) {

                if (!orderDetails.getPurchaseInvoiceValue().equals("") && clientPaidInvoice.equals("0")) {
                    goToPay.setVisibility(View.VISIBLE);
                    location.setVisibility(View.VISIBLE);
                    location.setOnClickListener(View -> {
                        startActivity(new Intent(getBaseContext(), MapTrackingActivity.class));
                        PreferencesManager.setStringPreferences(Const.KEY_Store_Lat,destinationLat);
                        PreferencesManager.setStringPreferences(Const.KEY_Store_Lng,destinationLng);
//
                        PreferencesManager.setStringPreferences("driver_name",name);
                        PreferencesManager.setStringPreferences("mobile",mobile);
                        PreferencesManager.setStringPreferences("img",img);
                        PreferencesManager.setStringPreferences("vehicleType",vehicleType);
                        PreferencesManager.setStringPreferences("car_number",vehiclePlate);
                        PreferencesManager.setStringPreferences("rate",rate);
                        PreferencesManager.setStringPreferences(Const.KEY_INVOICE_NUMBER,invoice_num);
                        PreferencesManager.setStringPreferences(Const.KEY_CLIENT_ID,driverId);
                        PreferencesManager.setStringPreferences(Const.KEY_ORDER_ID,orderId);
                        PreferencesManager.setStringPreferences(Const.KEY_STATUS,status);
                    });

                    cancel.setVisibility(View.VISIBLE);
                    cancel.setOnClickListener(View -> {
                        showCancelOrderDialog();
                    });
                    goToPay.setOnClickListener(View -> {
                        startActivity(new Intent(getBaseContext(), PublicPaymentActivity.class).putExtra(Const.KEY_ORDER, orderId));
                    });

                } else {
                    goToPay.setVisibility(View.GONE);
                    location.setVisibility(View.VISIBLE);
                    location.setOnClickListener(View -> {
                        startActivity(new Intent(getBaseContext(), MapTrackingActivity.class));
                                PreferencesManager.setStringPreferences("driver_name",name);
                        PreferencesManager.setStringPreferences("mobile",mobile);
                        PreferencesManager.setStringPreferences("img",img);
                        PreferencesManager.setStringPreferences("vehicleType",vehicleType);
                        PreferencesManager.setStringPreferences("car_number",vehiclePlate);
                        PreferencesManager.setStringPreferences("rate",rate);
                        PreferencesManager.setStringPreferences(Const.KEY_Store_Lat,destinationLat);
                        PreferencesManager.setStringPreferences(Const.KEY_Store_Lng,destinationLng);
                        PreferencesManager.setStringPreferences(Const.KEY_INVOICE_NUMBER,invoice_num);
                        PreferencesManager.setStringPreferences(Const.KEY_CLIENT_ID,driverId);
                        PreferencesManager.setStringPreferences(Const.KEY_ORDER_ID,orderId);
                        PreferencesManager.setStringPreferences(Const.KEY_STATUS,status);
                    });

                    cancel.setVisibility(View.VISIBLE);
                    cancel.setOnClickListener(View -> {
                        showCancelOrderDialog();
                    });
                }


            }

            else if (status.equals("delivered")) {
                if (orderDetails.getClientDeliverd().equals("0")) {
                    cancel.setVisibility(View.GONE);
                    location.setVisibility(View.GONE);
                    goToPay.setVisibility(View.VISIBLE);
                    goToPay.setText(getText(R.string.delivered));
                    goToPay.setOnClickListener(View -> {
                        delivered();
                        finish();
                    });
                } else {
                    goToPay.setVisibility(View.GONE);
                    location.setVisibility(View.GONE);
                    cancel.setVisibility(View.GONE);
                }

            }

            imagesAdapter = new OrderImagesAdapter(this, orderDetails.getAttachments(), R.layout.row_order_images);
            recyclerImages.setAdapter(imagesAdapter);

            if (orderDetails.getAttachments().isEmpty()) {
                tv_text_order_details.setVisibility(View.GONE);
            } else {
                tv_text_order_details.setVisibility(View.VISIBLE);

            }
            double price = Double.valueOf(orderDetails.getTotal()) - Double.valueOf(orderDetails.getTax());

            String productPrice = new StringBuilder()
                    .append(price)
                    .append(" ")
                    .append(HelperMethods.getCurrency(this))
                    .toString();

            String deliveryCost = new StringBuilder()
                    .append(orderDetails.getDeliveryCost())
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

            tvPrice.setText(productPrice);
            tvTax.setText(tax);
            tvDelivary.setText(deliveryCost);
            tvTotal.setText(totalCost);

        }

    }

    private void showCancelOrderDialog() {
        cancel_dialog = new Dialog(this);
        cancel_dialog.setContentView(R.layout.cancel_resone_dialog);
        cancel_dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cancel_dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
        cancel_dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        RecyclerView recyclerView = cancel_dialog.findViewById(R.id.recyclerview);


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
            HelperMethods.get1OrderAPI().cancelOrder(HelperMethods.getUserToken(this), orderId, String.valueOf(reason_id)).enqueue(new Callback<ContactUs>() {
                @Override
                public void onResponse(Call<ContactUs> call, Response<ContactUs> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(PublicOrderDetailsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
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

    private void delivered() {
        HelperMethods.get1OrderAPI().delivered(HelperMethods.getAppLanguage(this), HelperMethods.getUserToken(this), orderId)
                .enqueue(new Callback<ContactUs>() {
                    @Override
                    public void onResponse(Call<ContactUs> call, Response<ContactUs> response) {
                        if (response.isSuccessful()) {
                            if (response.isSuccessful()) {
                                HelperMethods.showCustomToast(PublicOrderDetailsActivity.this, response.body().getMessage(), true);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ContactUs> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }

    private void getCancelOrderReasonsApi() {
        HelperMethods.get1OrderAPI()
                .getCancelReasons(HelperMethods.getAppLanguage(this), HelperMethods.getUserToken(this))
                .enqueue(new Callback<CancelReasons>() {
                    @Override
                    public void onResponse(Call<CancelReasons> call, Response<CancelReasons> response) {
                        if (response.isSuccessful()) {
                            cancelOrderReasonsList = response.body().getCancelOrderReasonsList();
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
            Toast.makeText(PublicOrderDetailsActivity.this, message, Toast.LENGTH_LONG).show();
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
        orderId = eventBus.getPublic_order_id();
        Log.e("eventBus.getStatus()", eventBus.getPublic_order_id());
        getOrderDetails();
    }

    private void getOrderDetailsFromNotification() {
        loaderDialog();

        HelperMethods.get1OrderAPI().publicOrderDetails(orderId, HelperMethods.getUserToken(this))
                .enqueue(new Callback<OrderDetails>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onResponse(Call<OrderDetails> call, Response<OrderDetails> response) {

                        if (response.isSuccessful()) {
                            loader_dialog.dismiss();
                            if (response.body().getData().getDriver()!=null){
                            name = response.body().getData().getDriver().getName();
                            mobile = response.body().getData().getDriver().getMobile();
                                invoice_num = response.body().getData().getInvoiceNumber();
                                driverId = String.valueOf(response.body().getData().getDriver().getId());
                            img = response.body().getData().getDriver().getAvatarUrl();
                            vehicleType = response.body().getData().getDriver().getVehicleType();
                            vehiclePlate = response.body().getData().getDriver().getVehiclePlate();
                            rate = response.body().getData().getDriver().getRate();
                          }
                            tvTtitle.setText(new StringBuilder().append("#").append(response.body().getData().getInvoiceNumber()));
                            tvShopName.setText(response.body().getData().getStoreName());
                            tvShopAddress.setText(response.body().getData().getStoreAddress());
                            tvUserAddress.setText(response.body().getData().getDestinationAddress());
                            tvOrderDescription.setText(response.body().getData().getNote());
                            if (response.body().getData().getDriver()!=null)
                            clientId = response.body().getData().getDriver().getId()+"";
                            InvoiceNumber = response.body().getData().getInvoiceNumber();
                            status = response.body().getData().getStatus();
                            destinationLat = response.body().getData().getDestinationLat();
                            destinationLng = response.body().getData().getDestinationLng();

                            String clientPaidInvoice = response.body().getData().getClientPaidInvoice();
                            Log.e("status1", status);
                            Log.e("status1", response.body().getData().getPurchaseInvoiceValue());

                            if (status.equals("cancelled")) {
                                goToPay.setVisibility(View.GONE);
                                location.setVisibility(View.GONE);
                                cancel.setVisibility(View.VISIBLE);
                                cancel.setText(R.string.canceled);
                                cancel.setTextColor(getColor(R.color.red));
                                cancel.setBackgroundResource(R.drawable.cancel_btn2);
                            } else if (status.equals("in_the_way_to_client")) {
                                goToPay.setVisibility(View.GONE);
                                location.setVisibility(View.VISIBLE);
                                location.setOnClickListener(View -> {
                                    startActivity(new Intent(getBaseContext(), MapTrackingActivity.class));
                                    PreferencesManager.setStringPreferences("driver_name",name);
                                    PreferencesManager.setStringPreferences("mobile",mobile);
                                    PreferencesManager.setStringPreferences("img",img);
                                    PreferencesManager.setStringPreferences("vehicleType",vehicleType);
                                    PreferencesManager.setStringPreferences("car_number",vehiclePlate);
                                    PreferencesManager.setStringPreferences("rate",rate);
                                    PreferencesManager.setStringPreferences(Const.KEY_Store_Lat,destinationLat);
                                    PreferencesManager.setStringPreferences(Const.KEY_Store_Lng,destinationLng);
                                    PreferencesManager.setStringPreferences(Const.KEY_INVOICE_NUMBER,invoice_num);
                                    PreferencesManager.setStringPreferences(Const.KEY_CLIENT_ID,driverId);
                                    PreferencesManager.setStringPreferences(Const.KEY_ORDER_ID,orderId);
                                    PreferencesManager.setStringPreferences(Const.KEY_STATUS,status);
                                });
                                cancel.setVisibility(View.GONE);
                            } else if (status.equals("pending")) {
                                goToPay.setVisibility(View.GONE);
                                ivChat.setVisibility(View.GONE);
                                location.setVisibility(View.GONE);
                                cancel.setVisibility(View.VISIBLE);

                                cancel.setOnClickListener(View -> {
                                    showCancelOrderDialog();
                                });
                            } else if (status.equals("in_the_way_to_store")) {

                                if (!response.body().getData().getPurchaseInvoiceValue().equals("") && clientPaidInvoice.equals("0")) {
                                    goToPay.setVisibility(View.VISIBLE);
                                    location.setVisibility(View.VISIBLE);
                                    location.setOnClickListener(View -> {
                                        startActivity(new Intent(getBaseContext(), MapTrackingActivity.class));
                                        PreferencesManager.setStringPreferences("driver_name",name);
                                        PreferencesManager.setStringPreferences("mobile",mobile);
                                        PreferencesManager.setStringPreferences("img",img);
                                        PreferencesManager.setStringPreferences("vehicleType",vehicleType);
                                        PreferencesManager.setStringPreferences("car_number",vehiclePlate);
                                        PreferencesManager.setStringPreferences("rate",rate);
                                        PreferencesManager.setStringPreferences(Const.KEY_Store_Lat,destinationLat);
                                        PreferencesManager.setStringPreferences(Const.KEY_Store_Lng,destinationLng);
                                        PreferencesManager.setStringPreferences(Const.KEY_INVOICE_NUMBER,invoice_num);
                                        PreferencesManager.setStringPreferences(Const.KEY_CLIENT_ID,driverId);
                                        PreferencesManager.setStringPreferences(Const.KEY_ORDER_ID,orderId);
                                        PreferencesManager.setStringPreferences(Const.KEY_STATUS,status);
                                    });

                                    cancel.setVisibility(View.VISIBLE);
                                    cancel.setOnClickListener(View -> {
                                        showCancelOrderDialog();
                                    });
                                    goToPay.setOnClickListener(View -> {
                                        startActivity(new Intent(getBaseContext(), PublicPaymentActivity.class).putExtra(Const.KEY_ORDER, orderId));
                                    });

                                } else {
                                    goToPay.setVisibility(View.GONE);
                                    location.setVisibility(View.VISIBLE);
                                    location.setOnClickListener(View -> {
                                        startActivity(new Intent(getBaseContext(), MapTrackingActivity.class));
                                        PreferencesManager.setStringPreferences("driver_name",name);
                                        PreferencesManager.setStringPreferences("mobile",mobile);
                                        PreferencesManager.setStringPreferences("img",img);
                                        PreferencesManager.setStringPreferences("vehicleType",vehicleType);
                                        PreferencesManager.setStringPreferences("car_number",vehiclePlate);
                                        PreferencesManager.setStringPreferences("rate",rate);
                                        PreferencesManager.setStringPreferences(Const.KEY_Store_Lat,destinationLat);
                                        PreferencesManager.setStringPreferences(Const.KEY_Store_Lng,destinationLng);
                                        PreferencesManager.setStringPreferences(Const.KEY_INVOICE_NUMBER,invoice_num);
                                        PreferencesManager.setStringPreferences(Const.KEY_CLIENT_ID,driverId);
                                        PreferencesManager.setStringPreferences(Const.KEY_ORDER_ID,orderId);
                                        PreferencesManager.setStringPreferences(Const.KEY_STATUS,status);
                                    });

                                    cancel.setVisibility(View.VISIBLE);
                                    cancel.setOnClickListener(View -> {
                                        showCancelOrderDialog();
                                    });
                                }


                            } else if (status.equals("delivered")) {
                                if (response.body().getData().getClientDeliverd().equals("0")) {
                                    cancel.setVisibility(View.GONE);
                                    location.setVisibility(View.GONE);
                                    goToPay.setVisibility(View.VISIBLE);
                                    goToPay.setText(getText(R.string.delivered));
                                    goToPay.setOnClickListener(View -> {
                                        delivered();
                                        finish();
                                    });
                                } else {
                                    goToPay.setVisibility(View.GONE);
                                    location.setVisibility(View.GONE);
                                    cancel.setVisibility(View.GONE);
                                }

                            }
//                                    listImages.clear();
//                                    for (Attachment attachment : response.body().getData().getAttachments()) {
//                                        listImages.add(new Attachment(attachment.getImageUrl()));
//                                    }


                            imagesAdapter = new OrderImagesAdapter(PublicOrderDetailsActivity.this, response.body().getData().getAttachments(), R.layout.row_order_images);
                            recyclerImages.setAdapter(imagesAdapter);

                            if (response.body().getData().getAttachments().isEmpty()) {
                                tv_text_order_details.setVisibility(View.GONE);
                            } else {
                                tv_text_order_details.setVisibility(View.VISIBLE);

                            }
                            double price = Double.valueOf(response.body().getData().getTotal()) - Double.valueOf(response.body().getData().getTax());

                            String productPrice = new StringBuilder()
                                    .append(price)
                                    .append(" ")
                                    .append(HelperMethods.getCurrency(PublicOrderDetailsActivity.this))
                                    .toString();

                            String deliveryCost = new StringBuilder()
                                    .append(response.body().getData().getDeliveryCost())
                                    .toString();

                            String tax = new StringBuilder()
                                    .append(response.body().getData().getTax())
                                    .append(" ")
                                    .append(HelperMethods.getCurrency(PublicOrderDetailsActivity.this))
                                    .toString();

                            String totalCost = new StringBuilder()
                                    .append(response.body().getData().getTotal())
                                    .append(" ")
                                    .append(HelperMethods.getCurrency(PublicOrderDetailsActivity.this))
                                    .toString();

                            tvPrice.setText(productPrice);
                            tvTax.setText(tax);
                            tvDelivary.setText(deliveryCost);
                            tvTotal.setText(totalCost);


                        }

                        else {
                            loader_dialog.dismiss();
                            Log.e(";.;;;;;",response.message());
//                            HelperMethods.showCustomToast(PublicOrderDetailsActivity.this, response.body().getMessage(), false);
                        }
                    }


                    @Override
                    public void onFailure(Call<OrderDetails> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                        loader_dialog.dismiss();
                    }
                });


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
    protected void onResume() {
        super.onResume();
        if (PreferencesManager.getStringPreferences("public_order").equals("public_order")){
            PreferencesManager.setStringPreferences("public_order","");

                    goToPay.setVisibility(View.GONE);
                    location.setVisibility(View.VISIBLE);
                    location.setOnClickListener(View -> {
                        startActivity(new Intent(getBaseContext(), MapTrackingActivity.class));
                        PreferencesManager.setStringPreferences("driver_name",name);
                        PreferencesManager.setStringPreferences("mobile",mobile);
                        PreferencesManager.setStringPreferences("img",img);
                        PreferencesManager.setStringPreferences("vehicleType",vehicleType);
                        PreferencesManager.setStringPreferences("car_number",vehiclePlate);
                        PreferencesManager.setStringPreferences("rate",rate);
                        PreferencesManager.setStringPreferences(Const.KEY_Store_Lat,destinationLat);
                        PreferencesManager.setStringPreferences(Const.KEY_Store_Lng,destinationLng);
                        PreferencesManager.setStringPreferences(Const.KEY_INVOICE_NUMBER,invoice_num);
                        PreferencesManager.setStringPreferences(Const.KEY_CLIENT_ID,driverId);
                        PreferencesManager.setStringPreferences(Const.KEY_ORDER_ID,orderId);
                        PreferencesManager.setStringPreferences(Const.KEY_STATUS,status);
                    });

                    cancel.setVisibility(View.VISIBLE);
                    cancel.setOnClickListener(View -> {
                        showCancelOrderDialog();
                    });

        }

    }
}
