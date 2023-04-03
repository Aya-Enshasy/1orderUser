package com.user.order.ui.activivtes.cart;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.user.order.MapsActivity;
import com.user.order.adapter.CartOrdersAdapter;
import com.user.order.R;
import com.user.order.adapter.ExtrasOrdersAdapter1;
import com.user.order.databinding.ActivityCartBinding;
import com.user.order.listeners.CartExtraItemsClickListener;
import com.user.order.listeners.PriceListener;
import com.user.order.listeners.RefreshInterface;
import com.user.order.model.cart1.CartOrder;
import com.user.order.model.coupon.Coupon;
import com.user.order.model.delivary.Delivary;
import com.user.order.model.shop.meals.ExtraItem;
import com.user.order.model.shop.meals.MealsData;
import com.user.order.ui.activivtes.orders.PaymentActivity;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;
    double totalPrice = 0.0, dis = 0.0;
    double direction, delivary, tax, quantity;
    String radio = "home";
    Boolean RadioButtonState = false, RadioButtonState1 = false;
    Dialog dialog, extraItemsDialog;
    private MealsData meal;
    double pr1 = 0.0;
    List<CartOrder> items;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String userJson = PreferencesManager.getStringPreferences(Const.KEY_Meal);
        Gson gson = new Gson();
        meal = gson.fromJson(userJson, MealsData.class);

        String json = PreferencesManager.getStringPreferences(Const.KEY_ORDER_LIST);
        items = gson.fromJson(json, new TypeToken<List<CartOrder>>() {
        }.getType());


        if (PreferencesManager.getStringPreferences(Const.KEY_ORDER_LIST).equals("") || items.isEmpty()) {
            binding.constraintlayout.setVisibility(View.GONE);
            binding.noData.setVisibility(View.VISIBLE);
        } else {
            initUI();
            delivary();
            clickListener();
            binding.constraintlayout.setVisibility(View.VISIBLE);
            binding.noData.setVisibility(View.GONE);
        }

    }

    private void initUI()  {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerListOrders.setHasFixedSize(true);
        binding.recyclerListOrders.setLayoutManager(layoutManager);

        binding.tvPrice1.setText(" "+HelperMethods.getCurrency(CartActivity.this));


        CartOrdersAdapter cartOrdersAdapter = new CartOrdersAdapter(this, items, new PriceListener() {
            @Override
            public void onItemClick(Double price, Double qty, Double pr) {

                double sub1 = price;
                pr1 = pr;
                tax = Double.parseDouble(PreferencesManager.getStringPreferences(Const.KEY_TAX)) / 100;
                totalPrice = price;
                quantity = qty;

                binding.tvPrice.setText(totalPrice + "");

                tax = Double.parseDouble(PreferencesManager.getStringPreferences(Const.KEY_TAX)) / 100;
                double value = totalPrice * tax;
                value = Double.parseDouble(new DecimalFormat("#.#").format(value));

                String tax1 = new StringBuilder().append(value).append(" ").append(HelperMethods.getCurrency(CartActivity.this)).toString();
                binding.tvTax.setText(tax1);

                if (RadioButtonState1 == true) {
                    double total = totalPrice + (tax * totalPrice) + 0;
                    double total_format = total - dis;
                    total_format = Double.parseDouble(new DecimalFormat("#.#").format(total_format));
                    String tot = new StringBuilder().append(total_format).append(" ").append(HelperMethods.getCurrency(CartActivity.this)).toString();
                    binding.tvTotal.setText(tot);

                } else {
                    double total = totalPrice + (tax * totalPrice) + delivary;
                    double total_format = total - dis;
                    total_format = Double.parseDouble(new DecimalFormat("#.#").format(total_format));
                    String tot = new StringBuilder().append(total_format).append(" ").append(HelperMethods.getCurrency(CartActivity.this)).toString();
                    binding.tvTotal.setText(tot);
                }


            }
        }
                , new CartExtraItemsClickListener() {
            @Override
            public void onExtraItemClick(List extraItem, int pos) {
                if (!extraItem.equals("") && extraItem != null)
                    extraItemsDialog();


            }
        }

                , new RefreshInterface() {
            @Override
            public void onItemClick(boolean check, Double price, Double qty, Double pr) {
                if (check == true) {
                    final Intent intent = getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(intent);
                    overridePendingTransition(0, 0);

                }
            }
        });
        binding.recyclerListOrders.setAdapter(cartOrdersAdapter);

        binding.tvShopName.setText(PreferencesManager.getStringPreferences("shop_name"));
        binding.tvShopAddress.setText(PreferencesManager.getStringPreferences("shop_address"));
        Glide.with(this).load(PreferencesManager.getStringPreferences("shop_image")).placeholder(R.drawable.app_logo).into(binding.ivShopImage);


        binding.tvAddressTo.setText(PreferencesManager.getStringPreferences(Const.KEY_Address));

    }

    private void showAddDiscountCodeDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_discount_code);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;


        EditText inputCode = dialog.findViewById(R.id.input_discount_code);

        dialog.findViewById(R.id.btn_send)
                .setOnClickListener(v -> {
                    String code = inputCode.getText().toString();
                    if (code.equals("")) {
                        inputCode.setError(getString(R.string.please_enter_code));
                    } else {
                        addCode(code);

                    }

                });

        dialog.show();
    }

    private void delivary() {
        String country_id = String.valueOf(HelperMethods.getCountrySettings(this).getId());

        HelperMethods.get1OrderAPI().delivary(country_id, String.valueOf(direction), HelperMethods.getUserToken(this))
                .enqueue(new Callback<Delivary>() {
                    @Override
                    public void onResponse(Call<Delivary> call, Response<Delivary> response) {

                        if (response.isSuccessful()) {
                            if (response.body().getDeliveryCost() != 0) {
                                if (!binding.tvPrice.getText().toString().equals("")) {
                                    double sub1 = Double.parseDouble(binding.tvPrice.getText().toString());
                                    tax = Double.parseDouble(PreferencesManager.getStringPreferences(Const.KEY_TAX)) / 100;

                                    double value = sub1 * tax;
                                    value = Double.parseDouble(new DecimalFormat("#.#").format(value));

                                    String tax1 = new StringBuilder().append(value).append(" ").append(HelperMethods.getCurrency(CartActivity.this)).toString();
                                    binding.tvTax.setText(tax1);

                                    String delivary_cost = new StringBuilder().append(response.body().getDeliveryCost() + "").append(" ").append(HelperMethods.getCurrency(CartActivity.this)).toString();
                                    binding.tvDelivaryCost.setText(delivary_cost);
                                    delivary = response.body().getDeliveryCost();
                                    double total = sub1 + (tax * sub1) + response.body().getDeliveryCost();
                                    double total_format = total - dis;
                                    total_format = Double.parseDouble(new DecimalFormat("#.#").format(total_format));
                                    String tot = new StringBuilder().append(total_format).append(" ").append(HelperMethods.getCurrency(CartActivity.this)).toString();
                                    binding.tvTotal.setText(tot);

                                    binding.tvTextBill.setVisibility(View.VISIBLE);
                                }
                            } else {
                                binding.tvTextBill.setVisibility(View.GONE);
                            }

                        } else
                            Log.e("search", response.message() + "");
                    }

                    @SuppressLint("CheckResult")
                    @Override
                    public void onFailure(Call<Delivary> call, Throwable t) {


                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PreferencesManager.getStringPreferences("from_home").equals("from_home")){
            PreferencesManager.setStringPreferences("from_home","");
                    initUI();
        delivary();
        }

        finishActivity();
    }

    public void finishActivity() {
        if (!PreferencesManager.getStringPreferences("finish").equals("") || PreferencesManager.getStringPreferences("finish_payment").equals("finish_payment")) {
            PreferencesManager.setStringPreferences("finish", "");
            finish();
        } else {

        }


    }

    private void clickListener() {
        binding.tvChangeDeliveryAddress.setOnClickListener(View -> {
            startActivity(new Intent(this, MapsActivity.class)
                    .putExtra(Const.KEY_MAP, Const.CREATE_ORDER)
                    .putExtra("order", "order"));
        });

        binding.ivBack.setOnClickListener(View -> {
            onBackPressed();
            PreferencesManager.setStringPreferences(Const.KEY_OFFER, "");

        });

        binding.tvAddressFrom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    RadioButtonState = true;
                    RadioButtonState1 = true;
                    radio = "restaurant";
                    binding.tvChangeDeliveryAddress.setVisibility(View.GONE);
                    String delivary_cost = new StringBuilder().append(0.0).append(" ").append(HelperMethods.getCurrency(CartActivity.this)).toString();
                    binding.tvDelivaryCost.setText(delivary_cost);
                    double pricee = Double.parseDouble(binding.tvPrice.getText().toString());
                    double total = pricee + (tax * pricee);
                    double val= total-dis;
                    double value = Double.parseDouble(new DecimalFormat("#.#").format(val));
                    double total_format = value;
                    total_format = Double.parseDouble(new DecimalFormat("#.#").format(total_format));
                    String tot = new StringBuilder().append(total_format).append(" ").append(HelperMethods.getCurrency(CartActivity.this)).toString();
                    binding.tvTotal.setText(tot);

                } else {
                    RadioButtonState1 = false;
                    String delivary_cost = new StringBuilder().append(delivary + "").append(" ").append(HelperMethods.getCurrency(CartActivity.this)).toString();
                    binding.tvDelivaryCost.setText(delivary_cost);
                    RadioButtonState = true;
                    radio = "home";
                    binding.tvChangeDeliveryAddress.setVisibility(View.VISIBLE);
                    double pricee = Double.parseDouble(binding.tvPrice.getText().toString());
                    double total = pricee + (tax * pricee) + delivary;
                    double total_format = total - dis;
                    total_format = Double.parseDouble(new DecimalFormat("#.#").format(total_format));
                    String tot = new StringBuilder().append(total_format).append(" ").append(HelperMethods.getCurrency(CartActivity.this)).toString();
                    binding.tvTotal.setText(tot);

                }
            }
        });

        binding.btnAdd.setOnClickListener(View -> {
            showAddDiscountCodeDialog();
        });

        binding.btnGoPay.setOnClickListener(View -> {

            if (PreferencesManager.getStringPreferences("isOpen").equals("1")){
            double sub1 = totalPrice;
            double tax1 = sub1 * tax;
            double sub2 = tax1 + sub1;
            double total;

            if (RadioButtonState1 == true) {
                total = sub2 + 0;
                PreferencesManager.setStringPreferences("delivery", String.valueOf(0));
            } else {
                total = sub2 + delivary;
                PreferencesManager.setStringPreferences("delivery", String.valueOf(delivary));
            }

            Gson gson = new Gson();
            String userJson = gson.toJson(meal);
            PreferencesManager.setStringPreferences(Const.KEY_Meal, userJson);
//            sub_total_1 = PreferencesManager.getStringPreferences("sub1");
//            sub_total_2 = PreferencesManager.getStringPreferences("sub2");
//            total = PreferencesManager.getStringPreferences("total-price");
//            deliver = Double.parseDouble(PreferencesManager.getStringPreferences("delivary"));
//            discount = "0";
//            tax = "0";
//            items_price = PreferencesManager.getStringPreferences("items_price");
//            destination_lat = meals.getShop().getLat();
//            destination_lng = meals.getShop().getLng();
//            destination_address = meals.getShop().getAddress();
//            binding.btnConfirm.setOnCli

            PreferencesManager.setStringPreferences("total-price", total + "");
            PreferencesManager.setStringPreferences("sub1", sub1 + "");
            PreferencesManager.setStringPreferences("sub2", sub2 + "");
            PreferencesManager.setStringPreferences("delivary", delivary + "");


            startActivity(new Intent(this, PaymentActivity.class)
                    .putExtra("cart", "cart")
                    .putExtra("cart_shop_id", PreferencesManager.getStringPreferences("shop_id1"))
                    .putExtra("cart_lat", PreferencesManager.getStringPreferences("shop_lat"))
                    .putExtra("cart_lng", PreferencesManager.getStringPreferences("shop_lng"))
                    .putExtra("cart_address", PreferencesManager.getStringPreferences("shop_address"))
                    .putExtra("cart_offer_id", PreferencesManager.getStringPreferences("shop_offerid"))
                    .putExtra("radio", radio));}
            else {
                HelperMethods.showCustomToast(this,getString(R.string.restaurant_is_closed_now), false);
            }
        });
    }

    private void addCode(String text) {
        String country_id = String.valueOf(HelperMethods.getCountrySettings(this).getId());

        HelperMethods.get1OrderAPI().code(country_id, text).enqueue(new Callback<Coupon>() {
            @Override
            public void onResponse(Call<Coupon> call, Response<Coupon> response) {

                if (response.isSuccessful()) {
                    dis = response.body().getDiscountValue();
                    binding.tvTextDiscount.setText(getString(R.string.discount));
                    binding.tvDiscount.setVisibility(View.VISIBLE);
                    String discountValue = new StringBuilder().append(response.body().getDiscountValue()).append(" ").append(HelperMethods.getCurrency(CartActivity.this)).toString();
                    binding.tvDiscount.setText(discountValue);
                    binding.btnAdd.setVisibility(View.GONE);
                    Toast.makeText(CartActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                    tax = Double.parseDouble(PreferencesManager.getStringPreferences(Const.KEY_TAX)) / 100;
                    double pricee = totalPrice;
                    double total_format = (pricee + delivary + (pricee * tax)) - response.body().getDiscountValue();
                    total_format = Double.parseDouble(new DecimalFormat("#.#").format(total_format));
                    String total = new StringBuilder().append(total_format).append(" ").append(HelperMethods.getCurrency(CartActivity.this)).toString();
                    binding.tvTotal.setText(total);
                } else {
                    parseError(response);
                }
            }

            @SuppressLint("CheckResult")
            @Override
            public void onFailure(Call<Coupon> call, Throwable t) {


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
            Log.e("jsonObject", message);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        List<ExtraItem> items = PreferencesManager.getExtraItems(CartActivity.this, Const.KEY_EXTRA_ITEMS);
        ExtrasOrdersAdapter1 adapter = new ExtrasOrdersAdapter1(this, items, new PriceListener() {
            @Override
            public void onItemClick(Double price, Double qut, Double pr) {
                total_price.setText(price + " " + currency_code);
            }
        });
        recyclerView.setAdapter(adapter);

        extraItemsDialog.show();
    }


}

