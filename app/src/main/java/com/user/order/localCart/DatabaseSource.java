package com.user.order.localCart;

import android.app.Activity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.user.order.model.cart1.Cart;

@Database(entities = Cart.class, version = 1, exportSchema = false)
public abstract class DatabaseSource extends RoomDatabase {

    public abstract CartDAO cartDAO();

    private static DatabaseSource instance;

    public static DatabaseSource getInstance(Activity activity){
        return instance == null ? Room
                .databaseBuilder(activity, DatabaseSource.class, "1order_db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build() : instance;
    }
}
