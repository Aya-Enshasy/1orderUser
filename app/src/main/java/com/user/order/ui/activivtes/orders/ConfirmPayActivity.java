package com.user.order.ui.activivtes.orders;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.user.order.MapsActivity;
import com.user.order.R;
import com.user.order.databinding.ActivityConfirmPayBinding;
import com.user.order.databinding.ActivitySplashBinding;
import com.user.order.model.delivary.Delivary;
import com.user.order.model.directions.Directions;
import com.user.order.model.offer.OffersItem;
import com.user.order.model.shop.Shop;
import com.user.order.ui.activivtes.auth.LoginActivity;
import com.user.order.ui.activivtes.auth.SignUpActivity;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class ConfirmPayActivity extends AppCompatActivity {
    ActivityConfirmPayBinding binding;
    Shop shop;
    String id = "";
    private int quantity = 1;
    String radio = "home";
    double direction;
    double delivary = 0;
    double tax;
    double price;
    Boolean RadioButtonState = false, RadioButtonState1 = false, home = false;
    String origen;
    String shopId, offerId, lat, lng, des_lat, des_lng, address;
    Dialog login_dialog;
    public Dialog loader_dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmPayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tax = Double.parseDouble(PreferencesManager.getStringPreferences(Const.KEY_TAX)) / 100;
        initUI();
        if (getIntent() != null) shop = (Shop) getIntent().getSerializableExtra(Const.KEY_SHOP);
        id = PreferencesManager.getStringPreferences(Const.KEY_OFFER);

        if (id.equals("")) {
            getOrderDetails(shop);
        } else {
            getOffers();
        }

        if (HelperMethods.getUserToken(this) != null) {
            directions();
        }


    }

    private void initUI() {

        clickListeners();
        finishActivity();


    }

    private void getOrderDetails(Shop shop) {

        if (shop != null) {
            PreferencesManager.setStringPreferences("lng", shop.getLng());
            id = String.valueOf(shop.getId());

            origen = shop.getLat() + "," + shop.getLng();
            binding.tvShopName.setText(shop.getName());
            price = Double.parseDouble(shop.getOffers().get(0).getPrice());
            String price1 = new StringBuilder().append(shop.getOffers().get(0).getPrice()).append(" ").append(HelperMethods.getCurrency(this)).toString();
            binding.tvProductPrice.setText(price1);
            binding.tvProductName.setText(shop.getOffers().get(0).getName());
            binding.tvProductDetails.setText(shop.getOffers().get(0).getDesc());
            Glide.with(this).load(shop.getLogoUrl()).placeholder(R.drawable.app_logo).into(binding.civShopImage);
            Glide.with(this).load(shop.getOffers().get(0).getImageUrl()).placeholder(R.drawable.app_logo).into(binding.ivProductImage);
            binding.tvAddressTo.setText(PreferencesManager.getStringPreferences(Const.KEY_Address));
            binding.tvPrice.setText(price1);
            double sub1 = price * quantity;
            String tax1 = new StringBuilder().append(tax * sub1).append(" ").append(HelperMethods.getCurrency(this)).toString();
            binding.tvTax.setText(tax1);
            des_lat = shop.getLat();
            des_lng = shop.getLng();
            address = shop.getAddress();
            if (shop.getIsOpen().equals("")) {
                binding.tvShopStatus.setText(R.string.close);
                binding.tvShopStatus.setTextColor(getColor(R.color.red));
            } else {
                binding.tvShopStatus.setText(R.string.open);
                binding.tvShopStatus.setTextColor(getColor(R.color.colorBlue));
            }
        }
    }

    private void clickListeners() {

        binding.ivIncrease.setOnClickListener(View -> {
            quantity++;
            if (quantity > 1) {
                binding.ivDecrease.setEnabled(true);
                binding.tvQuantity.setText(String.valueOf(quantity));
                double pr = price * quantity;
                String price1 = new StringBuilder().append(pr).append(" ").append(HelperMethods.getCurrency(this)).toString();
                binding.tvPrice.setText(price1);

                double sub1 = price * quantity;
                String tax1 = new StringBuilder().append(tax * sub1).append(" ").append(HelperMethods.getCurrency(this)).toString();
                binding.tvTax.setText(tax1);

                if (RadioButtonState1 == true) {
                    double total = pr + (tax * pr) + 0;
                    String tot = new StringBuilder().append(total + "").append(" ").append(HelperMethods.getCurrency(this)).toString();
                    binding.tvTotal.setText(tot);
                } else {
                    double total = pr + (tax * pr) + delivary;
                    String tot = new StringBuilder().append(total + "").append(" ").append(HelperMethods.getCurrency(this)).toString();
                    binding.tvTotal.setText(tot);
                }
            }
        });

        binding.ivDecrease.setOnClickListener(View -> {
            if (quantity == 1) {
                binding.ivDecrease.setEnabled(false);
                return;
            } else {
                quantity--;
                binding.tvQuantity.setText(String.valueOf(quantity));
                binding.ivDecrease.setEnabled(true);
                double pr = price * quantity;
                String price1 = new StringBuilder().append(pr).append(" ").append(HelperMethods.getCurrency(this)).toString();
                binding.tvPrice.setText(price1);

                double sub1 = price * quantity;
                String tax1 = new StringBuilder().append(tax * sub1).append(" ").append(HelperMethods.getCurrency(this)).toString();
                binding.tvTax.setText(tax1);
                ;

                if (RadioButtonState1 == true) {
                    double total = pr + (tax * pr) + 0;
                    String tot = new StringBuilder().append(total + "").append(" ").append(HelperMethods.getCurrency(this)).toString();
                    binding.tvTotal.setText(tot);
                } else {
                    double total = pr + (tax * pr) + delivary;
                    String tot = new StringBuilder().append(total + "").append(" ").append(HelperMethods.getCurrency(this)).toString();
                    binding.tvTotal.setText(tot);
                }


            }

        });

        if (HelperMethods.getUserToken(this) != null) {
            binding.btnConfirmPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double sub1 = price * quantity;
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
                    Intent intent = new Intent(getBaseContext(), PaymentActivity.class);
                    double lat = Double.parseDouble(PreferencesManager.getStringPreferences(Const.KEY_CURRENT_LATITUDE));
                    double lng = Double.parseDouble(PreferencesManager.getStringPreferences(Const.KEY_CURRENT_LONGITUDE));
                    if (id.equals("")) {
                        PreferencesManager.setStringPreferences("shop_id", shop.getId() + "");
                        PreferencesManager.setStringPreferences("items_id", shop.getOffers().get(0).getId() + "");

                    } else {
                        PreferencesManager.setStringPreferences("shop_id", id + "");
                        PreferencesManager.setStringPreferences("items_id", offerId + "");
                    }
                    PreferencesManager.setStringPreferences("type_of_receive", radio);
                    PreferencesManager.setStringPreferences("delivery_type", "direct");
                    PreferencesManager.setStringPreferences("country_id", String.valueOf(HelperMethods.getCountrySettings(ConfirmPayActivity.this).getId()));
                    PreferencesManager.setStringPreferences("items_qty", binding.tvQuantity.getText().toString());//todo
                    PreferencesManager.setStringPreferences("payment_type", "on_delivery");
                    PreferencesManager.setStringPreferences("lat", des_lat);
                    PreferencesManager.setStringPreferences("lng", des_lng);
                    PreferencesManager.setStringPreferences("sub_total_1", String.valueOf(sub1));
                    PreferencesManager.setStringPreferences("sub_total_2", String.valueOf(sub2));
                    PreferencesManager.setStringPreferences("discount", "0");
                    PreferencesManager.setStringPreferences("total", String.valueOf(total));
                    PreferencesManager.setStringPreferences("tax", String.valueOf(tax * sub1));
                    PreferencesManager.setStringPreferences("items_price", String.valueOf(price));
                    PreferencesManager.setStringPreferences("destination_lat", String.valueOf(lat));
                    PreferencesManager.setStringPreferences("destination_lng", String.valueOf(lng));
                    PreferencesManager.setStringPreferences("destination_address", address);
                    startActivity(intent);


                }
            });

        } else {
            binding.btnConfirmPay.setOnClickListener(View -> {
                loginDialog();
            });

        }

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
                    String delivary_cost = new StringBuilder().append(0.0).append(" ").append(HelperMethods.getCurrency(ConfirmPayActivity.this)).toString();
                    binding.tvDelivaryCost.setText(delivary_cost);

                    double pr = price * quantity;
                    double total = pr + (tax * pr);
                    String tot = new StringBuilder().append(total + "").append(" ").append(HelperMethods.getCurrency(ConfirmPayActivity.this)).toString();
                    binding.tvTotal.setText(tot);
                } else {
                    RadioButtonState1 = false;
                    String delivary_cost = new StringBuilder().append(delivary + "").append(" ").append(HelperMethods.getCurrency(ConfirmPayActivity.this)).toString();
                    binding.tvDelivaryCost.setText(delivary_cost);

                    RadioButtonState = true;
                    radio = "home";
                    binding.tvChangeDeliveryAddress.setVisibility(View.VISIBLE);

                    double pr = price * quantity;
                    double total = pr + (tax * pr) + delivary;
                    String tot = new StringBuilder().append(total + "").append(" ").append(HelperMethods.getCurrency(ConfirmPayActivity.this)).toString();
                    binding.tvTotal.setText(tot);
                }

                if (delivary == 0) {
                    double pr = price * quantity;
                    double total = pr + (tax * pr) + 0;
                    String tot = new StringBuilder().append(total + "").append(" ").append(HelperMethods.getCurrency(ConfirmPayActivity.this)).toString();
                    binding.tvTotal.setText(tot);

                    binding.tvTextBill.setVisibility(View.VISIBLE);
                    binding.relativeLayout.setVisibility(View.VISIBLE);
                    binding.relativeLayout2.setVisibility(View.VISIBLE);
                    binding.relativeLayout3.setVisibility(View.VISIBLE);
                    binding.relativeLayout4.setVisibility(View.VISIBLE);
                    binding.btnConfirmPay.setVisibility(View.VISIBLE);
                    binding.textView10.setVisibility(View.GONE);
                }
            }
        });

        binding.tvAddressTo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (delivary != 0.0) {

                        double pr = price * quantity;
                        double total = pr + (tax * pr) + 0;
                        String tot = new StringBuilder().append(total + "").append(" ").append(HelperMethods.getCurrency(ConfirmPayActivity.this)).toString();
                        binding.tvTotal.setText(tot);

                        binding.tvTextBill.setVisibility(View.VISIBLE);
                        binding.relativeLayout.setVisibility(View.VISIBLE);
                        binding.relativeLayout2.setVisibility(View.VISIBLE);
                        binding.relativeLayout3.setVisibility(View.VISIBLE);
                        binding.relativeLayout4.setVisibility(View.VISIBLE);
                        binding.btnConfirmPay.setVisibility(View.VISIBLE);
                        binding.textView10.setVisibility(View.GONE);
                    } else {
                        binding.tvTextBill.setVisibility(View.GONE);
                        binding.relativeLayout.setVisibility(View.GONE);
                        binding.relativeLayout2.setVisibility(View.GONE);
                        binding.relativeLayout3.setVisibility(View.GONE);
                        binding.relativeLayout4.setVisibility(View.GONE);
                        binding.btnConfirmPay.setVisibility(View.GONE);
                        binding.textView10.setVisibility(View.VISIBLE);
                        binding.textView10.setText("This destines is not support from our app");
                    }
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (id.equals("")) {
            double sub1 = price * quantity;
            String tax1 = new StringBuilder().append(tax * sub1).append(" ").append(HelperMethods.getCurrency(ConfirmPayActivity.this)).toString();
            binding.tvTax.setText(tax1);
        } else {
            getOffers();
        }
        if (HelperMethods.getUserToken(this) != null) {
            binding.tvAddressTo.setText(PreferencesManager.getStringPreferences(Const.KEY_Address));

        }
        finishActivity();
    }

    private void directions() {
        loaderDialog();
        String current_lat = PreferencesManager.getStringPreferences(Const.KEY_CURRENT_LATITUDE);
        String current_lng = PreferencesManager.getStringPreferences(Const.KEY_CURRENT_LONGITUDE);
        String destination = current_lat + "," + current_lng;

        HelperMethods.get1OrderAPI().directions(origen, destination, "driving", "AIzaSyCIRYeAhC9c3u2KQ9HbUvL1pBbueLyTroA")
                .enqueue(new Callback<Directions>() {
                    @Override
                    public void onResponse(Call<Directions> call, Response<Directions> response) {
                        loader_dialog.dismiss();

                        if (response.isSuccessful()) {
                            delivary();
                            loader_dialog.dismiss();

                            if (!response.body().getRoutes().isEmpty()) {
                                loader_dialog.dismiss();

                                double direction1 = response.body().getRoutes().get(0).getLegs().get(0).getDistance().getValue() * 0.001;
                                direction = direction1;
                                delivary();
                                binding.tvTextBill.setVisibility(View.VISIBLE);
                                binding.relativeLayout.setVisibility(View.VISIBLE);
                                binding.relativeLayout2.setVisibility(View.VISIBLE);
                                binding.relativeLayout3.setVisibility(View.VISIBLE);
                                binding.relativeLayout4.setVisibility(View.VISIBLE);
                                binding.btnConfirmPay.setVisibility(View.VISIBLE);
                                binding.textView10.setVisibility(View.GONE);
                            } else {
                                loader_dialog.dismiss();

                                home = true;
                                binding.tvTextBill.setVisibility(View.GONE);
                                binding.relativeLayout.setVisibility(View.GONE);
                                binding.relativeLayout2.setVisibility(View.GONE);
                                binding.relativeLayout3.setVisibility(View.GONE);
                                binding.relativeLayout4.setVisibility(View.GONE);
                                binding.btnConfirmPay.setVisibility(View.GONE);
                                binding.textView10.setVisibility(View.VISIBLE);
                                binding.textView10.setText("No route between store location and location destination");
                            }

                        } else {
                            loader_dialog.dismiss();
                            Log.e("messages", response.message() + "");

                        }
                    }

                    @SuppressLint("CheckResult")
                    @Override
                    public void onFailure(Call<Directions> call, Throwable t) {

                        loader_dialog.dismiss();
                    }
                });

    }

    private void delivary() {
        String country_id = String.valueOf(HelperMethods.getCountrySettings(this).getId());
        loaderDialog();
        HelperMethods.get1OrderAPI().delivary(country_id, String.valueOf(direction), HelperMethods.getUserToken(this))
                .enqueue(new Callback<Delivary>() {
                    @Override
                    public void onResponse(Call<Delivary> call, Response<Delivary> response) {
                        if (response.isSuccessful()) {
                            loader_dialog.dismiss();

                            if (response.body().getDeliveryCost() != 0) {
                                loader_dialog.dismiss();
                                String delivary_cost = new StringBuilder().append(response.body().getDeliveryCost() + "").append(" ").append(HelperMethods.getCurrency(ConfirmPayActivity.this)).toString();
                                binding.tvDelivaryCost.setText(delivary_cost);
                                delivary = response.body().getDeliveryCost();
                                double pr = price * quantity;
                                double total = pr + (tax * pr) + response.body().getDeliveryCost();
                                String tot = new StringBuilder().append(total + "").append(" ").append(HelperMethods.getCurrency(ConfirmPayActivity.this)).toString();
                                binding.tvTotal.setText(tot);

                                binding.tvTextBill.setVisibility(View.VISIBLE);
                                binding.relativeLayout.setVisibility(View.VISIBLE);
                                binding.relativeLayout2.setVisibility(View.VISIBLE);
                                binding.relativeLayout3.setVisibility(View.VISIBLE);
                                binding.relativeLayout4.setVisibility(View.VISIBLE);
                                binding.btnConfirmPay.setVisibility(View.VISIBLE);
                                binding.textView10.setVisibility(View.GONE);
                            } else {
                                loader_dialog.dismiss();

                                binding.tvTextBill.setVisibility(View.GONE);
                                binding.relativeLayout.setVisibility(View.GONE);
                                binding.relativeLayout2.setVisibility(View.GONE);
                                binding.relativeLayout3.setVisibility(View.GONE);
                                binding.relativeLayout4.setVisibility(View.GONE);
                                binding.btnConfirmPay.setVisibility(View.GONE);
                                binding.textView10.setVisibility(View.VISIBLE);
                                binding.textView10.setText("This destines is not support from our app");
                            }

                        } else {
                            loader_dialog.dismiss();
                            Log.e("search", response.message() + "");
                        }
                    }

                    @SuppressLint("CheckResult")
                    @Override
                    public void onFailure(Call<Delivary> call, Throwable t) {
                        Log.e("delivary_cost", t.getMessage() + "");
                        loader_dialog.dismiss();
                    }
                });

    }

    public void finishActivity() {
        if (!PreferencesManager.getStringPreferences("finish").equals("")) {
            PreferencesManager.setStringPreferences("finish", "");
            finish();
        } else {

        }


    }

    private void getOffers() {

        HelperMethods.get1OrderAPI().offers(id)
                .enqueue(new Callback<OffersItem>() {
                    @Override
                    public void onResponse(Call<OffersItem> call, Response<OffersItem> response) {
                        if (response.isSuccessful()) {
                            loader_dialog.dismiss();

                            lat = response.body().getItem().getShop().getLat();
                            lng = response.body().getItem().getShop().getLng();
                            address = response.body().getItem().getShop().getAddress();
                            shopId = String.valueOf(response.body().getItem().getShop().getOffers().get(0).getId());
                            des_lat = response.body().getItem().getShop().getLat();
                            des_lng = response.body().getItem().getShop().getLng();
                            directions();
                            PreferencesManager.setStringPreferences("lng", (response.body().getItem().getShop().getLng()));
                            origen = response.body().getItem().getShop().getLat() + "," + response.body().getItem().getShop().getLng();
                            id= String.valueOf(response.body().getItem().getShop().getId());
                            binding.tvShopName.setText(response.body().getItem().getName());
                            if (response.body().getItem().getShop().getIsOpen().equals("")) {
                                binding.tvShopStatus.setText(R.string.close);
                                binding.tvShopStatus.setTextColor(getColor(R.color.red));
                            } else {
                                binding.tvShopStatus.setText(R.string.open);
                                binding.tvShopStatus.setTextColor(getColor(R.color.colorBlue));
                            }
                            price = Double.parseDouble(response.body().getItem().getShop().getOffers().get(0).getPrice());
                            String price1 = new StringBuilder().append(response.body().getItem().getShop().getOffers().get(0).getPrice()).append(" ").append(HelperMethods.getCurrency(ConfirmPayActivity.this)).toString();
                            binding.tvProductPrice.setText(price1);
                            binding.tvProductName.setText(response.body().getItem().getShop().getOffers().get(0).getName());
                            binding.tvProductDetails.setText(response.body().getItem().getShop().getOffers().get(0).getDesc());

                            Glide.with(ConfirmPayActivity.this).load(response.body().getItem().getShop().getLogoUrl()).placeholder(R.drawable.app_logo).into(binding.civShopImage);
                            Glide.with(ConfirmPayActivity.this).load(response.body().getItem().getShop().getOffers().get(0).getImageUrl()).placeholder(R.drawable.app_logo).into(binding.ivProductImage);

                            binding.tvAddressTo.setText(PreferencesManager.getStringPreferences(Const.KEY_Address));
                            binding.tvPrice.setText(price1);
                            double sub1 = price * quantity;
                            String tax1 = new StringBuilder().append(tax * sub1).append(" ").append(HelperMethods.getCurrency(ConfirmPayActivity.this)).toString();
                            binding.tvTax.setText(tax1);


                        }

                    }

                    @SuppressLint("CheckResult")
                    @Override
                    public void onFailure(Call<OffersItem> call, Throwable t) {
//                        loader_dialog.dismiss();


                    }
                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        PreferencesManager.setStringPreferences(Const.KEY_OFFER, "");
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

    protected void loaderDialog() {
        loader_dialog = new Dialog(this);
        loader_dialog.setContentView(R.layout.loader_dialog);
        loader_dialog.setCancelable(false);
        loader_dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER);
        loader_dialog.getWindow().setBackgroundDrawableResource(R.drawable.transparent);
        loader_dialog.show();
    }

}
