package com.user.order.model.cart1;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.user.order.model.shop.meals.ExtraItem;

import java.util.List;

@Entity(foreignKeys = @ForeignKey(entity = Cart.class, parentColumns = "id", childColumns = "productId", onDelete = ForeignKey.CASCADE))
public class CartOrder {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int productId;

    @NonNull
    @ColumnInfo(name = "productName")
    private String productName;

    @NonNull
    @ColumnInfo(name = "productImage")
    private String productImage;

    @NonNull
    @ColumnInfo(name = "quantity")
    private int quantity;

    @NonNull
    @ColumnInfo(name = "productPrice")
    private double productPrice;

    @NonNull
    @ColumnInfo(name = "totalPrice")
    private double totalPrice;

    @NonNull
    @ColumnInfo(name = "extraTotalPrice")
    private double extraTotalPrice;

    @NonNull
    @ColumnInfo(name = "extraItems")
    private List<ExtraItem> extraItems;

    public double getExtraTotalPrice() {
        return extraTotalPrice;
    }

    public void setExtraTotalPrice(double extraTotalPrice) {
        this.extraTotalPrice = extraTotalPrice;
    }

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
    public List<ExtraItem> getExtraItems() {
        return extraItems;
    }

    public void setExtraItems(@NonNull List<ExtraItem> extraItems) {
        this.extraItems = extraItems;
    }
}
