package com.user.order.model.aya;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import java.util.List;

public class CartData {

    private int id;
    private int shopId;
    private String shopImage;
    private String shopName;
    private String shopAddress;
    private double shopLat;
    private double shopLng;
    private String productName;
    private int quantity;
    private double price;
    private double total;
    private String orders;
    private List<Extra> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
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

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public List<Extra> getList() {
        return list;
    }

    public void setList(List<Extra> list) {
        this.list = list;
    }
}
