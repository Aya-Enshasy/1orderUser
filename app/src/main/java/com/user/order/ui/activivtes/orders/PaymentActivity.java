package com.user.order.ui.activivtes.orders;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.user.order.R;
import com.user.order.databinding.ActivityPaymentBinding;
import com.user.order.model.cart.CartOrderSend;
import com.user.order.model.cart.CartSend;
import com.user.order.model.cart.OrderExtraSend;
import com.user.order.model.cart1.CartOrder;
import com.user.order.model.contact.ContactUs;
import com.user.order.model.shop.meals.ExtraItem;
import com.user.order.model.shop.meals.MealsData;
import com.user.order.ui.activivtes.cart.CartActivity;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class PaymentActivity extends AppCompatActivity {
    ActivityPaymentBinding binding;
    String shop_id, cart = "", delivary, total, type_of_receive, delivery_type, country_id, items_id, items_qty, payment_type, lat, lng, delivery, sub_total_1, sub_total_2, discount, tax, items_price, destination_lat, destination_lng, destination_address;
    Boolean RadioButtonState = false;
    public Dialog loader_dialog;
    private MealsData meals;
    double deliver = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent() != null)
            meals = (MealsData) getIntent().getSerializableExtra(Const.KEY_Meal);

        if (getIntent() != null)
            cart = getIntent().getStringExtra("cart");


        if (getIntent().getStringExtra("cart") != null) {
            shop_id = getIntent().getStringExtra("cart_shop_id");
            type_of_receive = getIntent().getStringExtra("radio");
            delivery_type = "direct";
            country_id = String.valueOf(HelperMethods.getCountrySettings(PaymentActivity.this).getId());
            items_id = getIntent().getStringExtra("cart_offer_id");
            items_qty = PreferencesManager.getStringPreferences("items_qty");
            payment_type = "on_delivery";
            lat = getIntent().getStringExtra("cart_lat");
            lng = getIntent().getStringExtra("cart_lng");
            sub_total_1 = PreferencesManager.getStringPreferences("sub1");
            sub_total_2 = PreferencesManager.getStringPreferences("sub2");
            total = PreferencesManager.getStringPreferences("total-price");
            deliver = Double.parseDouble(PreferencesManager.getStringPreferences("delivary"));
            discount = "0";
            tax = "0";
            items_price = PreferencesManager.getStringPreferences("items_price");
            destination_lat = getIntent().getStringExtra("cart_lat");
            destination_lng = getIntent().getStringExtra("cart_lng");
            destination_address = getIntent().getStringExtra("cart_address");
            binding.btnConfirm.setOnClickListener(View -> {
                clickListeners();
            });
        } else {
            if (getIntent() != null) {

                shop_id = PreferencesManager.getStringPreferences("shop_id");
                type_of_receive = PreferencesManager.getStringPreferences("type_of_receive");
                delivery_type = PreferencesManager.getStringPreferences("delivery_type");
                country_id = PreferencesManager.getStringPreferences("country_id");
                items_id = PreferencesManager.getStringPreferences("items_id");
                items_qty = PreferencesManager.getStringPreferences("items_qty");
                payment_type = PreferencesManager.getStringPreferences("payment_type");
                lat = PreferencesManager.getStringPreferences("lat");
                lng = PreferencesManager.getStringPreferences("lng");
                delivery = PreferencesManager.getStringPreferences("delivery");
                sub_total_1 = PreferencesManager.getStringPreferences("sub_total_1");
                sub_total_2 = PreferencesManager.getStringPreferences("sub_total_2");
                discount = PreferencesManager.getStringPreferences("discount");
                total = PreferencesManager.getStringPreferences("total");
                tax = PreferencesManager.getStringPreferences("tax");
                items_price = PreferencesManager.getStringPreferences("items_price");
                destination_lat = PreferencesManager.getStringPreferences("destination_lat");
                destination_lng = PreferencesManager.getStringPreferences("destination_lng");
                destination_address = PreferencesManager.getStringPreferences("destination_address");
            }
            binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (RadioButtonState == false) {
                        Toast.makeText(PaymentActivity.this, "please select payment method type", Toast.LENGTH_SHORT).show();
                    } else
                        sendPaymentData();
                }
            });
        }


        binding.RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButtonState = true;
            }
        });

        binding.ivBack.setOnClickListener(View -> {
            finish();
        });
    }

    private void sendPaymentData() {
        loaderDialog();

        HelperMethods.get1OrderAPI().sendOfferOrder(
                        shop_id,
                        type_of_receive,
                        delivery_type,
                        country_id,
                        items_id,
                        items_qty,
                        payment_type,
                        lat,
                        lng,
                        delivery,
                        sub_total_1,
                        discount,
                        sub_total_2,
                        total,
                        items_price,
                        destination_lat,
                        destination_lng,
                        destination_address,
                        tax,
                        HelperMethods.getUserToken(this),
                        HelperMethods.getAppLanguage(this))
                .enqueue(new Callback<ContactUs>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onResponse(Call<ContactUs> call, Response<ContactUs> response) {
                        loader_dialog.dismiss();
                        if (response.isSuccessful()) {
                            PreferencesManager.setStringPreferences("finish", "finish");
                            PreferencesManager.setStringPreferences(Const.KEY_OFFER, "");
                            finish();
                            Toast.makeText(PaymentActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ContactUs> call, Throwable t) {
                        loader_dialog.dismiss();
                        Log.d("TAG", "onFailure: " + t.getMessage());
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

    private void clickListeners() {

        String userJson = PreferencesManager.getStringPreferences(Const.KEY_Meal);
        Gson gson = new Gson();
        meals = gson.fromJson(userJson, MealsData.class);
        String json = PreferencesManager.getStringPreferences(Const.KEY_ORDER_LIST);
        List<CartOrder> items = gson.fromJson(json, new TypeToken<List<CartOrder>>() {
        }.getType());


        CartSend cartSend = new CartSend();
        cartSend.setShopId(Integer.getInteger(shop_id));
        cartSend.setTypeOfReceive(type_of_receive);
        cartSend.setDeliveryType(delivery_type);
        cartSend.setCouponId(Integer.valueOf(country_id));
        cartSend.setCountryId(HelperMethods.getCountrySettings(this).getId());
        cartSend.setPaymentType(payment_type);
        cartSend.setDeliveryAt("");
        cartSend.setShopLat(24.7436082);
        cartSend.setShopLng(24.7436082);
        cartSend.setDeliveryCost(10);
        cartSend.setSubTotal1(Double.valueOf(sub_total_1));
        cartSend.setDiscount(Integer.valueOf(discount));
        cartSend.setSubTotal2(Double.valueOf(sub_total_2));
        cartSend.setNotes("");
        cartSend.setDestinationLat(Double.valueOf(123));
        cartSend.setDestinationLng(Double.valueOf(123));
        cartSend.setDestinationAddress("deliveryAddress");

        HashMap<String, Object> map = new HashMap<>();
//        List<ExtraItem> item = PreferencesManager.getExtraItems(this, Const.KEY_EXTRA_ITEMS);
        for (int i = 0; i < items.size(); i++) {
            map.put("items" + "[" + i + "]" + "[id]", items.get(i).getProductId());
            map.put("items" + "[" + i + "]" + "[qty]", items.get(i).getQuantity());
            map.put("items" + "[" + i + "]" + "[price]", items.get(i).getProductPrice());

            if (items.get(i).getExtraItems() != null) {

                for (int j = 0; j < items.get(i).getExtraItems().size(); j++) {
                    map.put("items" + "[" + i + "]" + "[extra]" + "[" + j + "]" + "[item_id]", items.get(i).getExtraItems().get(j).getId());
                    map.put("items" + "[" + i + "]" + "[extra]" + "[" + j + "]" + "[qty]", items.get(i).getQuantity());
                    map.put("items" + "[" + i + "]" + "[extra]" + "[" + j + "]" + "[price]", items.get(i).getExtraItems().get(j).getPrice());

                }
            }

        }

        double tax = Double.parseDouble(PreferencesManager.getStringPreferences(Const.KEY_TAX)) / 100;
        double tax1 = Double.parseDouble(sub_total_1) * tax;

        if (type_of_receive.equals("restaurant"))
            deliver = 0.0;
        loaderDialog();
        HelperMethods.get1OrderAPI()
                .sendOrder1(HelperMethods.getAppLanguage(this),
                        HelperMethods.getUserToken(this),
                        Integer.valueOf(shop_id),
                        type_of_receive,
                        delivery_type,
                        0,
                        Integer.valueOf(country_id),
                        map, map, map, map, map, map,
                        payment_type,
                        "",
                        Double.parseDouble(lat),
                        Double.parseDouble(lng),
                        deliver,
                        Double.parseDouble(sub_total_1)
                        , Integer.parseInt(discount)
                        , Double.parseDouble(sub_total_2)
                        , Double.parseDouble(total),
                        "",
                        Double.parseDouble(lat), Double.parseDouble(lng), tax1, destination_address)
                .enqueue(new Callback<ContactUs>() {
                    @Override
                    public void onResponse(Call<ContactUs> call, Response<ContactUs> response) {
                        if (response.isSuccessful()) {
                            loader_dialog.dismiss();
                            HelperMethods.showCustomToast(PaymentActivity.this, response.body().getMessage(), true);
                            PreferencesManager.setStringPreferences(Const.KEY_ORDER_LIST, "");
                            PreferencesManager.setStringPreferences("finish_payment", "finish_payment");
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ContactUs> call, Throwable t) {
                        loader_dialog.dismiss();
                    }
                });
    }

}
