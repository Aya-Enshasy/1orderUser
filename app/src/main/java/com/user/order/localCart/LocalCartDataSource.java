package com.user.order.localCart;

import com.user.order.model.cart1.Cart;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class LocalCartDataSource implements CartDataSource {

    private CartDAO cartDAO;

    public LocalCartDataSource(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    @Override
    public Flowable<List<Cart>> getAllCarts() {
        return this.cartDAO.getAllCarts();
    }

    @Override
    public int getCountCartItems() {
        return this.cartDAO.getCountCartItems();
    }


    @Override
    public Single<Cart> getSingleItem(int id) {
        return this.cartDAO.getSingleItem(id);
    }

    @Override
    public Single<Cart> checkItemInCart(int id) {
        return this.cartDAO.checkItemInCart(id);
    }

    @Override
    public Completable addToCart(Cart... carts) {
        return this.cartDAO.addToCart(carts);
    }

    @Override
    public Single<Integer> updateItemCart(Cart carts) {
        return this.cartDAO.updateItemCart(carts);
    }

    @Override
    public Single<Integer> deleteItemFromCart(Cart cart) {
        return this.cartDAO.deleteItemFromCart(cart);
    }

    @Override
    public Single<Integer> clearCarts() {
        return this.cartDAO.clearCarts();
    }
}
