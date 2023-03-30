package com.user.order.localCart;

import com.user.order.model.cart1.Cart;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface CartDataSource {

    // get all Items from Cart
    Flowable<List<Cart>> getAllCarts();

    // get count of cart
    int getCountCartItems();

    // Check item in Single or not by Cart Id
    Single<Cart> getSingleItem(int id);

    // Check item in exsist or not by Cart Id
    Single<Cart> checkItemInCart(int shopId);

    // add Items in Cart
    Completable addToCart(Cart... carts);

    // update from cart
    Single<Integer> updateItemCart(Cart carts);

    // delete item from Cart
    Single<Integer> deleteItemFromCart(Cart cart);

    // clear Cart
    Single<Integer> clearCarts();
}
