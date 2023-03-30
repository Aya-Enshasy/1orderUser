package com.user.order.model.cart1;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "carts")
public class Cart implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "shopId")
    private int shopId;
    @NonNull
    @ColumnInfo(name = "shopImage")
    private String shopImage;

    @NonNull
    @ColumnInfo(name = "shopName")
    private String shopName;

    @NonNull
    @ColumnInfo(name = "shopAddress")
    private String shopAddress;

    @NonNull
    @ColumnInfo(name = "shopLat")
    private double shopLat;

    @NonNull
    @ColumnInfo(name = "shopLng")
    private double shopLng;

    @NonNull
    @ColumnInfo(name = "productName")
    private String productName;

    @NonNull
    @ColumnInfo(name = "quantity")
    private int quantity;

    @NonNull
    @ColumnInfo(name = "price")
    private double price;
    @NonNull
    @ColumnInfo(name = "total")
    private double total;

    @NonNull
    @ColumnInfo(name = "orders")
    private String orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public int getShopId() {
        return shopId;
    }

    public void setShopId(@NonNull int shopId) {
        this.shopId = shopId;
    }

    @NonNull
    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(@NonNull String shopImage) {
        this.shopImage = shopImage;
    }

    @NonNull
    public String getShopName() {
        return shopName;
    }

    public void setShopName(@NonNull String shopName) {
        this.shopName = shopName;
    }

    @NonNull
    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(@NonNull String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public double getShopLat() {
        return shopLat;
    }

    public void setShopLat(double shopLat) {
        this.shopLat = shopLat;
    }

    public double getShopLng() {
        return shopLng;
    }

    public void setShopLng(double shopLng) {
        this.shopLng = shopLng;
    }

    @NonNull
    public String getProductName() {
        return productName;
    }

    public void setProductName(@NonNull String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @NonNull
    public String getOrders() {
        return orders;
    }

    public void setOrders(@NonNull String orders) {
        this.orders = orders;
    }
}
