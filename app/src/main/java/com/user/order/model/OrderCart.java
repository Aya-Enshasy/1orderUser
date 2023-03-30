package com.user.order.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class OrderCart {

    private int productId;

    private String productName;

    private String productImage;

    private int quantity;

    private double productPrice;

    private double totalPrice;

    private String extraItems;



    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @NonNull
    public String getProductName() {
        return productName;
    }

    public void setProductName(@NonNull String productName) {
        this.productName = productName;
    }

    @NonNull
    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(@NonNull String productImage) {
        this.productImage = productImage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @NonNull
    public String getExtraItems() {
        return extraItems;
    }

    public void setExtraItems(@NonNull String extraItems) {
        this.extraItems = extraItems;
    }
}

