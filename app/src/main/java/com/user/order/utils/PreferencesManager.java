package com.user.order.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.user.order.model.DeliveryAddress;
import com.user.order.model.User;
import com.user.order.model.country.Country;
import com.user.order.model.settings.AppContent;
import com.user.order.model.shop.meals.ExtraItem;
import com.user.order.model.shop.meals.MealsData;

import java.util.List;

public class PreferencesManager {

    private static Context context;
    private static SharedPreferences sharedPreferences = null;

    private static void setSharedPreferences(Context activity){
        if (sharedPreferences == null)
            sharedPreferences = activity.getSharedPreferences("Koshk", Activity.MODE_PRIVATE);
    }

    public static void saveAppData(Context activity, String key, String value){
        setSharedPreferences(activity);
        if (sharedPreferences != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
        }else
            setSharedPreferences(activity);
    }

    public static boolean setStringPreferences(String key, String value) {
        setSharedPreferences(context);
        return sharedPreferences.edit().putString(key, value).commit();
    }

    public static String getStringPreferences(String key) {
        setSharedPreferences(context);
        return sharedPreferences.getString(key, "");
    }


    public static String loadAppData(Activity activity, String key){
        setSharedPreferences(activity);
        if (sharedPreferences != null)
            return sharedPreferences.getString(key, "");
        else{
            setSharedPreferences(activity);
            return null;
        }
    }

    public static void saveCurrentCountryCode(Activity activity, String key, String value){
        setSharedPreferences(activity);
        if (sharedPreferences != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
        }else
            setSharedPreferences(activity);
    }

    public static String loadCurrentCountryCode(Activity activity, String key){
        setSharedPreferences(activity);
        if (sharedPreferences != null)
            return sharedPreferences.getString(key, "");
        else{
            setSharedPreferences(activity);
            return null;
        }
    }

    public static void saveUserToken(Activity activity, String key, String value){
        setSharedPreferences(activity);
        if (sharedPreferences != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
        }else
            setSharedPreferences(activity);
    }

    public static String loadUserToken(Activity activity, String key){
        setSharedPreferences(activity);
        if (sharedPreferences != null)
            return sharedPreferences.getString(key, "");
        else{
            setSharedPreferences(activity);
            return null;
        }
    }

    public static void saveUserData(Activity activity, String key, User user){
        setSharedPreferences(activity);
        if (sharedPreferences != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, new Gson().toJson(user));
            editor.apply();
        }else
            setSharedPreferences(activity);
    }

    public static User getUserData(Context activity, String key){
        setSharedPreferences(activity);
        if (sharedPreferences != null){
            String userData = sharedPreferences.getString(key, "");
            return new Gson().fromJson(userData, User.class);
        }else{
            setSharedPreferences(activity);
            return null;
        }
    }

    public static void saveCountrySettings(Activity activity, String key, Country settings){
        setSharedPreferences(activity);
        if (sharedPreferences != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, new Gson().toJson(settings));
            editor.apply();
        }else
            setSharedPreferences(activity);
    }

    public static Country getCountrySettings(Activity activity, String key){
        setSharedPreferences(activity);
        if (sharedPreferences != null){
            String settings = sharedPreferences.getString(key, "");
            return new Gson().fromJson(settings, Country.class);
        }else{
            setSharedPreferences(activity);
            return null;
        }
    }

    public static void saveAppContent(Activity activity, String key, AppContent appContent){
        setSharedPreferences(activity);
        if (sharedPreferences != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, new Gson().toJson(appContent));
            editor.apply();
        }else
            setSharedPreferences(activity);
    }

    public static AppContent getAppContent(Activity activity, String key){
        setSharedPreferences(activity);
        if (sharedPreferences != null){
            String appContent = sharedPreferences.getString(key, "");
            return new Gson().fromJson(appContent, AppContent.class);
        }else{
            setSharedPreferences(activity);
            return null;
        }
    }

    public static void saveDeliveryAddress(Activity activity, String key, DeliveryAddress address){
        setSharedPreferences(activity);
        if (sharedPreferences != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, new Gson().toJson(address));
            editor.apply();
        }else
            setSharedPreferences(activity);
    }

    public static DeliveryAddress getDeliveryAddress(Activity activity, String key){
        setSharedPreferences(activity);
        if (sharedPreferences != null){
            String address = sharedPreferences.getString(key, "");
            return new Gson().fromJson(address, DeliveryAddress.class);
        }else{
            setSharedPreferences(activity);
            return null;
        }
    }

    public static void saveLanguage(Activity activity, String value){
        setSharedPreferences(activity);
        if (sharedPreferences != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Const.KEY_LANGUAGE, value);
            editor.apply();;
        }else
            setSharedPreferences(activity);
    }

    public static String loadLanguage(Activity activity, String key){
        setSharedPreferences(activity);
        String language = "";
        if(sharedPreferences != null){
            language = sharedPreferences.getString(key, HelperMethods.getDeviceLanguage());
            return language;
        }else {
            setSharedPreferences(activity);
            return language;
        }
    }

    public static void saveAllowPermission(Activity activity, String key, boolean isAllow){
        setSharedPreferences(activity);
        if (sharedPreferences != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(key, isAllow);
            editor.apply();
        }else
            setSharedPreferences(activity);
    }

    public static boolean isAllowPermission(Activity activity, String key){
        setSharedPreferences(activity);
        boolean isAllow = false;
        if (sharedPreferences != null){
            isAllow = sharedPreferences.getBoolean(key, isAllow);
            return isAllow;
        }else{
            setSharedPreferences(activity);
            return isAllow;
        }
    }

    public static void saveExtraItems(Activity activity, String key, List<ExtraItem> items){
        setSharedPreferences(activity);
        if (sharedPreferences != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, new Gson().toJson(items));
            editor.apply();
        }else
            setSharedPreferences(activity);
    }

    public static List<ExtraItem> getExtraItems(Activity activity, String key){
        setSharedPreferences(activity);
        if (sharedPreferences != null){
            String strIds = sharedPreferences.getString(key,"");
            List<ExtraItem> items =  new Gson().fromJson(strIds, new TypeToken<List<ExtraItem>>(){}.getType());
            return items;
        }else {
            setSharedPreferences(activity);
            return null;
        }
    }

    public static List<MealsData> getMealsData(Activity activity, String key){
        setSharedPreferences(activity);
        if (sharedPreferences != null){
            String strIds = sharedPreferences.getString(key,"");
            List<MealsData> items =  new Gson().fromJson(strIds, new TypeToken<List<MealsData>>(){}.getType());
            return items;
        }else {
            setSharedPreferences(activity);
            return null;
        }
    }

    public static void clear(Activity activity, String key){
        setSharedPreferences(activity);
        if (sharedPreferences != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(key);
            editor.apply();
        }else
            setSharedPreferences(activity);
    }

    public static void clear(Activity activity){
        setSharedPreferences(activity);
        if (sharedPreferences != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
        }else
            setSharedPreferences(activity);
    }
}
