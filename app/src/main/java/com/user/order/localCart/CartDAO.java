package com.user.order.localCart;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.user.order.model.cart1.Cart;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface CartDAO {

    // get all Items from Entertainment
    @Query("SELECT * From carts")
    Flowable<List<Cart>> getAllCarts();

    // get count of cart
    @Query("SELECT COUNT(*) from carts")
    int getCountCartItems();

    // Check item in Single or not by leagueId
    @Query("SELECT * FROM carts WHERE id=:id")
    Single<Cart> getSingleItem(int id);

    // Check item in exsist or not by leagueId
    @Query("SELECT * FROM carts WHERE shopId=:shopId")
    Single<Cart> checkItemInCart(int shopId);

    // add Items in Entertainment
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addToCart(Cart... carts);

    // update from cart
    @Update(onConflict = OnConflictStrategy.REPLACE)
    Single<Integer> updateItemCart(Cart carts);

    // delete item from Entertainment
    @Delete
    Single<Integer> deleteItemFromCart(Cart cart);

    // clear Entertainment
    @Query("DELETE FROM carts")
    Single<Integer> clearCarts();
}
