package com.user.order.ui.activivtes.productDetails;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.user.order.R;
import com.user.order.adapter.ProductImagesAdapter;
import com.user.order.adapter.ProductIngredientAdapter;
import com.user.order.databinding.ActivityProductDetailsBinding;
import com.user.order.listeners.CheckBoxInterface;
import com.user.order.listeners.SelectExtraItemsListener;
import com.user.order.model.aya.CartData;
import com.user.order.model.aya.Data;
import com.user.order.model.cart1.Cart;
import com.user.order.model.cart1.CartOrder;
import com.user.order.model.shop.meals.ExtraItem;
import com.user.order.model.shop.meals.MealsData;
import com.user.order.ui.activivtes.cart.CartActivity;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class ProductDetailsActivity extends AppCompatActivity implements SelectExtraItemsListener {
    private static final String TAG = ProductDetailsActivity.class.getSimpleName();
    ActivityProductDetailsBinding binding;

    private MealsData meal;
    private int productQuantity;
    public static int quantity = 1;
    private double productPrice = 0.0;
    public static double total;
    double price1 = 0;
    private CompositeDisposable disposable;
    List<ExtraItem> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUI();
        getProductDetails(meal);
        onClickListeners();


    }

    @SuppressLint("CheckResult")
    private void onClickListeners() {
        binding.ivBack.setOnClickListener(View -> {
            onBackPressed();
        });

        binding.ivDecrease.setOnClickListener(View -> {

            if (HelperMethods.getUserToken(this) != null) {

                Log.d(TAG, "onDecreaseClick: " + productQuantity);
                if (quantity == 1) {
                    binding.ivDecrease.setEnabled(false);
                    return;
                } else {
                    quantity--;
                    productPrice = Double.parseDouble(meal.getPrice());
                    total = (productPrice + price1) * quantity;
                    binding.tvQuantity.setText(String.valueOf(quantity));
                    String priceTotal = new StringBuilder()
                            .append(HelperMethods.formatTotal(total))
                            .append(" ")
                            .append(HelperMethods.getCurrency(this))
                            .toString();
                    binding.tvTotal.setText(priceTotal);
                    binding.ivIncrease.setEnabled(true);
                    quantity = Integer.parseInt(binding.tvQuantity.getText().toString());

                }
                Log.d(TAG, "onDecreaseClick: " + quantity);
            }


        });

        binding.ivIncrease.setOnClickListener(View -> {

            if (HelperMethods.getUserToken(this) != null) {

                quantity++;
                productPrice = Double.parseDouble(meal.getPrice());
                Log.e("kcdnkldcn", price1 + "");
                total = (productPrice + price1) * quantity;
                Log.d(TAG, "onIncreaseClick total: " + total);
                String priceTotal = new StringBuilder()
                        .append(HelperMethods.formatTotal(total))
                        .append(" ")
                        .append(HelperMethods.getCurrency(this))
                        .toString();
                binding.tvTotal.setText(priceTotal);

                Log.d(TAG, "onIncreaseClick: " + quantity);
                if (quantity > 1) {
                    binding.ivDecrease.setEnabled(true);
                    binding.tvQuantity.setText(String.valueOf(quantity));
                }
                quantity = Integer.parseInt(binding.tvQuantity.getText().toString());

            }


        });

        binding.btnAddToCart.setOnClickListener(View -> {
            if (meal != null && !PreferencesManager.getStringPreferences("shop_id1").equals("")) {
                if ((meal.getShop().getId() != Integer.parseInt(PreferencesManager.getStringPreferences("shop_id1")))
                        &&!PreferencesManager.getStringPreferences(Const.KEY_ORDER_LIST).equals("")) {
                    deleteItemDialog();
                }  else {
                    showGoToCartDialog();

                }
            }
        });
    }

    private void getProductDetails(MealsData meal) {

        if (meal != null) {

            ProductImagesAdapter imagesAdapter = new ProductImagesAdapter(this, meal.getImages(), binding.viewPagerProductImages);

            binding.viewPagerProductImages.setAdapter(imagesAdapter);
            binding.dotsIndicator.attachTo(binding.viewPagerProductImages);

            String price = new StringBuilder()
                    .append(meal.getPrice())
                    .append(" ")
                    .append(HelperMethods.getCurrency(this))
                    .toString();
            String priceTotal = new StringBuilder()
                    .append(HelperMethods.formatTotal(Double.parseDouble(meal.getPrice())))
                    .append(" ")
                    .append(HelperMethods.getCurrency(this))
                    .toString();

            binding.tvProductName.setText(meal.getName());
            binding.tvProductPrice.setText(price);
            binding.tvProductDescription.setText(meal.getDesc());
            binding.tvTotal.setText(priceTotal);

            //todo extra item
            ProductIngredientAdapter ingredientAdapter = new ProductIngredientAdapter(this, meal.getExtraItems(), new SelectExtraItemsListener() {
                @Override
                public void onExtraItemsSelected(double price, double qty, ExtraItem extraItem, List<ExtraItem> items, int pos, boolean isCheck) {
                    price1 = price;
                    double tot = Double.parseDouble(meal.getPrice()) * Double.parseDouble(binding.tvQuantity.getText().toString());
                    String priceTotal = new StringBuilder()
                            .append(tot + price)
                            .append(" ")
                            .append(HelperMethods.getCurrency(ProductDetailsActivity.this))
                            .toString();
                    binding.tvTotal.setText(priceTotal);
                    list = items;
                    if (isCheck == true)
                        PreferencesManager.saveExtraItems(ProductDetailsActivity.this, Const.KEY_EXTRA_ITEMS, items);
//                    else {
//                       PreferencesManager.saveExtraItems(ProductDetailsActivity.this, Const.KEY_EXTRA_ITEMS, null);
//
//                    }
                }

            }, new CheckBoxInterface() {
                @Override
                public void onClick(boolean check) {
                    if (check == true){
                        binding.tvTextIngredients.setVisibility(View.GONE);
                        binding.tvTextOptions.setVisibility(View.GONE);
                    }
                }
            }

            );
            binding.recyclerIngredients.setAdapter(ingredientAdapter);
        }
    }

    private void initUI() {

        if (getIntent() != null)
            meal = (MealsData) getIntent().getSerializableExtra(Const.KEY_MEAL_SHOP);

        disposable = new CompositeDisposable();

        binding.cardProductDetails.setBackgroundResource(R.drawable.bg_card);
        binding.tvQuantity.setText(String.valueOf(quantity));

        binding.viewPagerProductImages.setClipToPadding(false);
        binding.viewPagerProductImages.setClipChildren(false);
        binding.viewPagerProductImages.setOffscreenPageLimit(3);
        binding.viewPagerProductImages.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);
        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(10));
        transformer.addTransformer((page, position) -> {
            float f = 1 - Math.abs(position);
            page.setScaleY(0.85f + f * 0.15f);
        });

        binding.viewPagerProductImages.setPageTransformer(transformer);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerIngredients.setHasFixedSize(true);
        binding.recyclerIngredients.setLayoutManager(layoutManager);

        Log.d(TAG, "initUI Cart Count: " + HelperMethods.getCarts(this).getCountCartItems());
        quantity = Integer.parseInt(binding.tvQuantity.getText().toString());
    }

    @SuppressLint("CheckResult")
    private void showGoToCartDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View viewDialog = getLayoutInflater().inflate(R.layout.dialog_add_to_cart, null);
        builder.setView(viewDialog);
        AlertDialog dialog = builder.create();
        HelperMethods.showCustomDialog(dialog);

        viewDialog.findViewById(R.id.btn_continue)
                .setOnClickListener(view -> {
                    dialog.dismiss();

                    CartOrder cartOrder = new CartOrder();
                    cartOrder.setProductId(meal.getId());
                    cartOrder.setProductName(meal.getName());
                    cartOrder.setProductImage(meal.getImageUrl());
                    cartOrder.setQuantity(Integer.parseInt(binding.tvQuantity.getText().toString()));
                    cartOrder.setProductPrice(Double.parseDouble(meal.getPrice()));
                    cartOrder.setTotalPrice((productPrice + price1) * quantity);
                    cartOrder.setExtraItems(list);
                    List<CartOrder> orders = new ArrayList<>();
                    List<CartOrder> newObjects = new ArrayList<CartOrder>();

                    //room
                    Cart cartItem = new Cart();
                    cartItem.setShopId(meal.getShop().getId());
                    cartItem.setShopImage(meal.getShop().getLogoUrl());
                    cartItem.setShopName(meal.getShop().getName());
                    cartItem.setShopAddress(meal.getShop().getAddress());
                    cartItem.setShopLat(Double.parseDouble(meal.getShop().getLat()));
                    cartItem.setShopLng(Double.parseDouble(meal.getShop().getLng()));
                    cartItem.setProductName(meal.getName());
                    cartItem.setQuantity(quantity);
                    cartOrder.setExtraTotalPrice(price1);
                    cartItem.setPrice(Double.parseDouble(meal.getPrice()));
                    cartItem.setTotal((productPrice + price1) * quantity);
                    cartItem.setOrders(new Gson().toJson(orders));


                    String json = PreferencesManager.getStringPreferences(Const.KEY_ORDER_LIST);
                    Gson gson = new Gson();
                    List<CartOrder> item = gson.fromJson(json, new TypeToken<List<CartOrder>>() {
                    }.getType());
                    newObjects.add(cartOrder);
                    if (item != null)
                        orders.addAll(item);


                    orders.addAll(newObjects);
                    String userJson = gson.toJson(cartOrder);
                    PreferencesManager.setStringPreferences(Const.KEY_Meal, userJson);
                    String list = gson.toJson(orders);
                    PreferencesManager.setStringPreferences(Const.KEY_ORDER_LIST, list);

                    PreferencesManager.setStringPreferences("shop_name", meal.getShop().getName());
                    PreferencesManager.setStringPreferences("shop_address", meal.getShop().getAddress());
                    PreferencesManager.setStringPreferences("shop_image", meal.getShop().getCoverImageUrl());
                    PreferencesManager.setStringPreferences("shop_id1", meal.getShop().getId() + "");
                    Log.e("getShop",meal.getShop().getId() +"");

//                    CartOrder cartOrder = new CartOrder();
//                    cartOrder.setProductId(meal.getId());
//                    cartOrder.setProductName(meal.getName());
//                    cartOrder.setProductImage(meal.getImageUrl());
//                    cartOrder.setQuantity(Integer.parseInt(binding.tvQuantity.getText().toString()));
//                    cartOrder.setProductPrice(Double.parseDouble(meal.getPrice()));
//                    cartOrder.setTotalPrice((productPrice + price1) * quantity);
//                    cartOrder.setExtraItems(list);
//                    List<CartOrder> orders = new ArrayList<>();
//                    List<CartOrder> newObjects = new ArrayList<CartOrder>();
//
//                    //room
////                    Cart cartItem = new Cart();
////                    cartItem.setShopId(meal.getShop().getId());
////                    cartItem.setShopImage(meal.getShop().getLogoUrl());
////                    cartItem.setShopName(meal.getShop().getName());
////                    cartItem.setShopAddress(meal.getShop().getAddress());
////                    cartItem.setShopLat(Double.parseDouble(meal.getShop().getLat()));
////                    cartItem.setShopLng(Double.parseDouble(meal.getShop().getLng()));
////                    cartItem.setProductName(meal.getName());
////                    cartItem.setQuantity(quantity);
////                    cartItem.setPrice(Double.parseDouble(meal.getPrice()));
////                    cartItem.setTotal((productPrice + price1) * quantity);
////                    cartItem.setOrders(new Gson().toJson(orders));
//
//
////                    String json = PreferencesManager.getStringPreferences(Const.KEY_ORDER_LIST);
//                    Gson gson = new Gson();
////                    List<CartOrder> item = gson.fromJson(json, new TypeToken<List<CartOrder>>() {
////                    }.getType());
//                    newObjects.add(cartOrder);
////                    if (item != null)
////                        orders.addAll(item);
//
//
//                    orders.addAll(newObjects);
//                    String userJson = gson.toJson(cartOrder);
//                    PreferencesManager.setStringPreferences(Const.KEY_Meal, userJson);
//                    String list = gson.toJson(orders);
//                    PreferencesManager.setStringPreferences(Const.KEY_ORDER_LIST, list);
//
//                    PreferencesManager.setStringPreferences("shop_name", meal.getShop().getName());
//                    PreferencesManager.setStringPreferences("shop_address", meal.getShop().getAddress());
//                    PreferencesManager.setStringPreferences("shop_image", meal.getShop().getCoverImageUrl());
//                    PreferencesManager.setStringPreferences("shop_id1", meal.getShop().getId() + "");


                });

        viewDialog.findViewById(R.id.btn_go_to_cart)
                .setOnClickListener(v -> {
                    dialog.dismiss();
                    List<ExtraItem> items = PreferencesManager.getExtraItems(this, Const.KEY_EXTRA_ITEMS);
//
//                    CartOrder cartOrder = new CartOrder();
//                    cartOrder.setProductId(meal.getId());
//                    cartOrder.setProductName(meal.getName());
//                    cartOrder.setProductImage(meal.getImageUrl());
//                    cartOrder.setQuantity(Integer.parseInt(binding.tvQuantity.getText().toString()));
//                    cartOrder.setProductPrice(Double.parseDouble(meal.getPrice()));
//                    cartOrder.setTotalPrice((productPrice + price1) * quantity);
//                    cartOrder.setExtraItems(items);
//                    List<CartOrder> orders = new ArrayList<>();
//                    ArrayList<CartOrder> newObjects = new ArrayList<CartOrder>();
//
//
//                    //room
//                    Cart cartItem = new Cart();
//                    cartItem.setShopId(meal.getShop().getId());
//                    cartItem.setShopImage(meal.getShop().getLogoUrl());
//                    cartItem.setShopName(meal.getShop().getName());
//                    cartItem.setShopAddress(meal.getShop().getAddress());
//                    cartItem.setShopLat(Double.parseDouble(meal.getShop().getLat()));
//                    cartItem.setShopLng(Double.parseDouble(meal.getShop().getLng()));
//                    cartItem.setProductName(meal.getName());
//                    cartItem.setQuantity(quantity);
//                    cartItem.setPrice(Double.parseDouble(meal.getPrice()));
//                    cartItem.setTotal((productPrice + price1) * quantity);
//                    cartItem.setOrders(new Gson().toJson(orders));
//                    PreferencesManager.setStringPreferences("tott", (productPrice + price1) * quantity + "");
//
//                    String json = PreferencesManager.getStringPreferences(Const.KEY_ORDER_LIST);
//                    Gson gson = new Gson();
//                    List<CartOrder> item = gson.fromJson(json, new TypeToken<List<CartOrder>>() {
//                    }.getType());
//                    newObjects.add(cartOrder);
//                    if (item != null)
//                        orders.addAll(item);
//
//
//                    orders.addAll(newObjects);
//                    String userJson = gson.toJson(meal);
//                    PreferencesManager.setStringPreferences(Const.KEY_Meal, userJson);
//                    String list = gson.toJson(orders);
//                    PreferencesManager.setStringPreferences(Const.KEY_ORDER_LIST, list);

                    CartOrder cartOrder = new CartOrder();
                    cartOrder.setProductId(meal.getId());
                    cartOrder.setProductName(meal.getName());
                    cartOrder.setProductImage(meal.getImageUrl());
                    cartOrder.setQuantity(Integer.parseInt(binding.tvQuantity.getText().toString()));
                    cartOrder.setProductPrice(Double.parseDouble(meal.getPrice()));
                    cartOrder.setTotalPrice((productPrice + price1) * quantity);
                    cartOrder.setExtraItems(list);
                    cartOrder.setExtraTotalPrice(price1);
                    List<CartOrder> orders = new ArrayList<>();
                    List<CartOrder> newObjects = new ArrayList<CartOrder>();

                    //room
                    Cart cartItem = new Cart();
                    cartItem.setShopId(meal.getShop().getId());
                    cartItem.setShopImage(meal.getShop().getLogoUrl());
                    cartItem.setShopName(meal.getShop().getName());
                    cartItem.setShopAddress(meal.getShop().getAddress());
                    cartItem.setShopLat(Double.parseDouble(meal.getShop().getLat()));
                    cartItem.setShopLng(Double.parseDouble(meal.getShop().getLng()));
                    cartItem.setProductName(meal.getName());
                    cartItem.setQuantity(quantity);
                    cartItem.setPrice(Double.parseDouble(meal.getPrice()));
                    cartItem.setTotal((productPrice + price1) * quantity);
                    cartItem.setOrders(new Gson().toJson(orders));


                    String json = PreferencesManager.getStringPreferences(Const.KEY_ORDER_LIST);
                    Gson gson = new Gson();
                    List<CartOrder> item = gson.fromJson(json, new TypeToken<List<CartOrder>>() {
                    }.getType());
                    newObjects.add(cartOrder);
                    if (item != null)
                        orders.addAll(item);


                    orders.addAll(newObjects);
                    String userJson = gson.toJson(cartOrder);
                    PreferencesManager.setStringPreferences(Const.KEY_Meal, userJson);
                    String list = gson.toJson(orders);
                    PreferencesManager.setStringPreferences(Const.KEY_ORDER_LIST, list);

                    PreferencesManager.setStringPreferences("shop_name", meal.getShop().getName());
                    PreferencesManager.setStringPreferences("shop_address", meal.getShop().getAddress());
                    PreferencesManager.setStringPreferences("shop_image", meal.getShop().getCoverImageUrl());
                    PreferencesManager.setStringPreferences("shop_id1", meal.getShop().getId() + "");
                    Log.e("getShop2",meal.getShop().getId() +"");


                    startActivity(new Intent(this, CartActivity.class).putExtra(Const.KEY_SHOP, meal.getShop()));




                });

        dialog.show();
    }

    @Override
    public void onExtraItemsSelected(double price, double qty, ExtraItem extraItem, List<ExtraItem> items, int pos, boolean isCheck) {

    }


    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
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


    @SuppressLint("CheckResult")
    private void deleteItemDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View viewDialog = getLayoutInflater().inflate(R.layout.dialog_remove_cart, null);
        builder.setView(viewDialog);
        AlertDialog dialog = builder.create();
        HelperMethods.showCustomDialog(dialog);

        viewDialog.findViewById(R.id.btn_delete)
                .setOnClickListener(view -> {
                    dialog.dismiss();
                    PreferencesManager.setStringPreferences(Const.KEY_ORDER_LIST, "");
                    final Intent intent = getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                });

        viewDialog.findViewById(R.id.btn_continue)
                .setOnClickListener(v -> {
                    dialog.dismiss();
                });

        dialog.show();
    }
}