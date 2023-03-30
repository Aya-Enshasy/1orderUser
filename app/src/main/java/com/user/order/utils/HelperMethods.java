package com.user.order.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.chaos.view.PinView;
import com.google.android.material.snackbar.Snackbar;
import com.user.order.R;
import com.user.order.adapter.OrderImagesAdapter;
import com.user.order.localCart.CartDataSource;
import com.user.order.localCart.DatabaseSource;
import com.user.order.localCart.LocalCartDataSource;
import com.user.order.model.DeliveryAddress;
import com.user.order.model.ImageData;
import com.user.order.model.User;
import com.user.order.model.country.Country;
import com.user.order.model.orders.publicOrders.Attachment;
import com.user.order.model.settings.AppContent;
import com.user.order.services.api.I1OrderAPI;
import com.user.order.services.api.IPAPI;
import com.user.order.services.api.RetrofitClient;
import com.user.order.services.api.RetrofitIP;
import com.user.order.services.map.IMapAPI;
import com.user.order.services.map.RetrofitMap;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;

import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class HelperMethods {
    private static final String TAG = HelperMethods.class.getSimpleName();

    public static Double LATITUDE;
    public static Double LONGITUDE;
    public static String ADDRESS;
    public static String OTHER_ADDRESS;
    private static String imageUrl;
    public static List<Attachment> listImagesUrls = new ArrayList<>();

    public static String getImageUrl() {
        return imageUrl;
    }

    public static void setImageUrl(String imageUrl) {
        HelperMethods.imageUrl = imageUrl;
    }

    public static I1OrderAPI get1OrderAPI() {
        return RetrofitClient.getClient().create(I1OrderAPI.class);
    }

    public static IPAPI getIPAPI() {
        return RetrofitIP.getClient().create(IPAPI.class);
    }

    public static IMapAPI getMapAPI() {
        return RetrofitMap.getClient().create(IMapAPI.class);
    }

    public static CartDataSource getCarts(Activity activity) {
        return new LocalCartDataSource(DatabaseSource.getInstance(activity).cartDAO());
    }

    public static User getCurrentUser(Context activity) {
        if (PreferencesManager.getUserData(activity, Const.KEY_USER_DATA) != null && !PreferencesManager.getUserData(activity, Const.KEY_USER_DATA).equals(""))
            return PreferencesManager.getUserData(activity, Const.KEY_USER_DATA);
        else
            return null;
    }


    public static Country getCountrySettings(Activity activity) {
        if (PreferencesManager.getCountrySettings(activity, Const.KEY_SETTINGS) != null && !PreferencesManager.getCountrySettings(activity, Const.KEY_SETTINGS).equals(""))
            return PreferencesManager.getCountrySettings(activity, Const.KEY_SETTINGS);
        else
            return null;
    }

    public static AppContent getAppContent(Activity activity) {
        if (PreferencesManager.getAppContent(activity, Const.KEY_APP_CONTENT) != null && !PreferencesManager.getAppContent(activity, Const.KEY_APP_CONTENT).equals(""))
            return PreferencesManager.getAppContent(activity, Const.KEY_APP_CONTENT);
        else
            return null;
    }

    public static String getUserToken(Activity activity) {
        if (PreferencesManager.loadUserToken(activity, Const.KEY_USER_TOKEN) != null && !PreferencesManager.loadUserToken(activity, Const.KEY_USER_TOKEN).equals(""))
            return "Bearer " + PreferencesManager.loadUserToken(activity, Const.KEY_USER_TOKEN);
        else
            return null;
    }

    public static Double getUserLatitude(Activity activity) {
        if (PreferencesManager.loadAppData(activity, Const.KEY_CURRENT_LATITUDE) != null && !PreferencesManager.loadAppData(activity, Const.KEY_CURRENT_LATITUDE).equals(""))
            return Double.valueOf(PreferencesManager.loadAppData(activity, Const.KEY_CURRENT_LATITUDE));
        else
            return null;
    }

    public static Double getUserLongitude(Activity activity) {
        if (PreferencesManager.loadAppData(activity, Const.KEY_CURRENT_LONGITUDE) != null && !PreferencesManager.loadAppData(activity, Const.KEY_CURRENT_LONGITUDE).equals(""))
            return Double.valueOf(PreferencesManager.loadAppData(activity, Const.KEY_CURRENT_LONGITUDE));
        else
            return null;
    }

    public static DeliveryAddress getDeliveryAddress(Activity activity) {
        if (PreferencesManager.getDeliveryAddress(activity, Const.KEY_DELIVERY_ADDRESS) != null && !PreferencesManager.getDeliveryAddress(activity, Const.KEY_DELIVERY_ADDRESS).equals(""))
            return PreferencesManager.getDeliveryAddress(activity, Const.KEY_DELIVERY_ADDRESS);
        else
            return null;
    }

    public static String getCurrency(Activity activity) {
        if(PreferencesManager.getCountrySettings(activity, Const.KEY_SETTINGS) != null){
            return PreferencesManager.getCountrySettings(activity, Const.KEY_SETTINGS).getCurrencyCode();
        }else {
            return Const.KEY_CURRENCY;
        }
    }

    public static void saveAppLanguage(Activity activity, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        activity.getResources().updateConfiguration(configuration, activity.getResources().getDisplayMetrics());
    }

    public static String getCountryCode(Activity activity) {
        if (PreferencesManager.loadCurrentCountryCode(activity, Const.KEY_CURRENT_COUNTRY_CODE) != null && !PreferencesManager.loadCurrentCountryCode(activity, Const.KEY_CURRENT_COUNTRY_CODE).equals(""))
            return PreferencesManager.loadCurrentCountryCode(activity, Const.KEY_CURRENT_COUNTRY_CODE);
        else
            return null;
    }

    public static void checkAppLanguage(Activity activity) {
//        Log.d(TAG, "checkAppLanguage DEVICE LANGUAGE: " + Locale.getDefault().getLanguage());
//        Log.d(TAG, "checkAppLanguage: " + PreferencesManager.loadLanguage(activity, Const.KEY_LANGUAGE));
        if (PreferencesManager.loadLanguage(activity, Const.KEY_LANGUAGE) != null && !PreferencesManager.loadLanguage(activity, Const.KEY_LANGUAGE).equals(""))
            HelperMethods.saveAppLanguage(activity, PreferencesManager.loadLanguage(activity, Const.KEY_LANGUAGE));
        else
            HelperMethods.saveAppLanguage(activity, getDeviceLanguage());
    }

    public static String getAppLanguage(Activity activity) {
        if (PreferencesManager.loadLanguage(activity, Const.KEY_LANGUAGE).equals(Const.KEY_LANGUAGE_AR))
            return Const.KEY_LANGUAGE_AR;
        else if (PreferencesManager.loadLanguage(activity, Const.KEY_LANGUAGE).equals(Const.KEY_LANGUAGE_EN))
            return Const.KEY_LANGUAGE_EN;
        else
            return getDeviceLanguage();
    }

    public static Locale getAppLocale(Activity activity) {
        return new Locale(getAppLanguage(activity));
    }

    public static String getDeviceLanguage() {
        return Locale.getDefault().getLanguage();
    }

    private static String getDistance(double fromLat, double fromLon, double toLat, double toLon) {
        double theta = fromLon - toLon;
        double dist = Math.sin(deg2rad(fromLat))
                * Math.sin(deg2rad(toLat))
                + Math.cos(deg2rad(fromLat))
                * Math.cos(deg2rad(toLat))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return formatDistance(dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    private static String formatDistance(double distance) {
        if (distance != 0) {
            DecimalFormat dFormat = new DecimalFormat("#,##");
            dFormat.setRoundingMode(RoundingMode.UP);
            return new StringBuilder(dFormat.format(distance)).toString().replace(",", ".");
        } else
            return "0.0";
    }

    public static String formatTotal(double total) {
        NumberFormat formatter = new DecimalFormat("#0.0");
        return formatter.format(total);
    }

    public static String getLocationDistance(Activity activity, double toShopLat, double toShopLng){
        String distance = "";
        if(HelperMethods.getUserToken(activity) != null){
            if (HelperMethods.getDeliveryAddress(activity).getLatitude() != null && HelperMethods.getDeliveryAddress(activity).getLatitude() != null){
                String getDistance = HelperMethods.getDistance(HelperMethods.getDeliveryAddress(activity).getLatitude(), HelperMethods.getDeliveryAddress(activity).getLatitude(), toShopLat, toShopLng);
                distance = new StringBuilder()
                        .append(getDistance)
                        .append(" ")
                        .append(activity.getString(R.string.km))
                        .toString();
                return distance;
            }
        }else {
            if(HelperMethods.getUserLatitude(activity) != null && HelperMethods.getUserLongitude(activity) != null){
                String getDistance = HelperMethods.getDistance(HelperMethods.getUserLatitude(activity), HelperMethods.getUserLongitude(activity), toShopLat, toShopLng);
                distance = new StringBuilder()
                        .append(getDistance)
                        .append(" ")
                        .append(activity.getString(R.string.km))
                        .toString();
                return distance;
            }
        }
        return distance;
    }

    public static String convertMeterToKilometer(Activity activity, float meter) {
        float km = (float) (meter * 0.001f);
        return new StringBuilder()
                .append(formatDistance(km))
                .append(" ")
                .append(activity.getString(R.string.km))
                .toString();
    }

    public static void showCustomDialog(AlertDialog dialog) {
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

    public static void showProgress(Activity activity) {
        CardView cardProgress = activity.findViewById(R.id.card_progress);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            cardProgress.setElevation(8);
        else
            cardProgress.setElevation(0);
        cardProgress.setVisibility(View.VISIBLE);
    }

    public static void showProgress(View layoutView) {
        CardView cardProgress = layoutView.findViewById(R.id.card_progress);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            cardProgress.setElevation(8);
        else
            cardProgress.setElevation(0);
        cardProgress.setVisibility(View.VISIBLE);
    }

    public static void dismissProgress(Activity activity) {
        CardView cardProgress = activity.findViewById(R.id.card_progress);
        cardProgress.setVisibility(View.GONE);
    }

    public static void dismissProgress(View layoutView) {
        CardView cardProgress = layoutView.findViewById(R.id.card_progress);
        cardProgress.setVisibility(View.GONE);
    }

    public static void showCustomSnackBar(Context context, View view,
                                          @NonNull String message, @NonNull boolean isSuccessful, int duration, View.OnClickListener listener) {

        Snackbar mSnackBar = Snackbar.make(view, "", duration);

        int layout;
        if (isSuccessful)
            layout = R.layout.snack_success_layout;
        else
            layout = R.layout.snack_error_layout;

        View snackView = LayoutInflater.from(context)
                .inflate(layout, null);

        mSnackBar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) mSnackBar.getView();
        snackbarLayout.setPadding(0, 0, 0, 0);

        FrameLayout.LayoutParams mFrameParams = (FrameLayout.LayoutParams) mSnackBar.getView().getLayoutParams();
        mFrameParams.gravity = Gravity.BOTTOM;
        mSnackBar.getView().setLayoutParams(mFrameParams);

        CardView cardSuccess = snackView.findViewById(R.id.card_success);
        TextView tvMessage = snackView.findViewById(R.id.tv_message);
        TextView retry = snackView.findViewById(R.id.tv_click_retry);
        tvMessage.setText(message);
        if (listener != null) {
            retry.setVisibility(View.VISIBLE);
            retry.setOnClickListener(listener);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            cardSuccess.setElevation(8);
        else
            cardSuccess.setElevation(0);

        snackbarLayout.addView(snackView, 0);
        mSnackBar.show();

    }

    public static void showCustomSnackBar(Context context, View view,
                                          @NonNull String message, @NonNull boolean isSuccessful, int duration) {

        Snackbar mSnackBar = Snackbar.make(view, "", duration);

        int layout;
        if (isSuccessful)
            layout = R.layout.snack_success_layout;
        else
            layout = R.layout.snack_error_layout;

        View snackView = LayoutInflater.from(context)
                .inflate(layout, null);

        mSnackBar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) mSnackBar.getView();
        snackbarLayout.setPadding(0, 0, 0, 0);

        FrameLayout.LayoutParams mFrameParams = (FrameLayout.LayoutParams) mSnackBar.getView().getLayoutParams();
        mFrameParams.gravity = Gravity.BOTTOM;
        mSnackBar.getView().setLayoutParams(mFrameParams);

        TextView tvMessage = snackView.findViewById(R.id.tv_message);
        TextView retry = snackView.findViewById(R.id.tv_click_retry);
        tvMessage.setText(message);
        retry.setVisibility(View.GONE);


        snackbarLayout.addView(snackView, 0);
        mSnackBar.show();

    }

    public static void showCustomToast(Activity activity, String message, boolean isSuccess){
        Toast toast = new Toast(activity);
        int layout;
        if (isSuccess)
            layout = R.layout.toast_success_layout;
        else
            layout = R.layout.toast_error_layout;
        View toastView = activity.getLayoutInflater().inflate(layout, null);
        toast.setView(toastView);
        TextView tvMessage = toastView.findViewById(R.id.tv_message);
        tvMessage.setText(message);

        toast.setDuration(isSuccess ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0,0);
        toast.show();
    }

    public static boolean isInternetConnected(Activity context) {

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected()))
            return true;
        else
            return false;
    }

    public static MultipartBody.Part convertFileToMultiPart(String filePath, String key) {
        if (filePath != null) {
            File file = new File(filePath);
            RequestBody requestBody = RequestBody.create(file, MediaType.parse("image/jpeg"));
            MultipartBody.Part fileBody = MultipartBody.Part.createFormData(key, file.getName(), requestBody);
            return fileBody;
        } else
            return null;
    }

    public static RequestBody convertToRequestBody(String body) {
        if (!body.isEmpty()) {
            RequestBody requestBody = RequestBody.create(body, MediaType.parse("multipart/form-data"));
            return requestBody;
        } else
            return null;
    }

    public static void openSingleGallery(Activity activity,ImageView imageView) {

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Album.initialize(AlbumConfig.newBuilder(activity)
                    .setAlbumLoader(new MediaLoader())
                    .build());

            Album.image(activity)
                    .singleChoice()
                    .camera(true)
                    .columnCount(3)
                    .onResult(result -> {
                        if (imageView != null) {
                            imageView.setVisibility(View.VISIBLE);
                            setImageUrl(result.get(0).getPath());
//                            imageUrl = result.get(0).getPath();
                            Log.d(TAG, "openSingleGallery: " + getImageUrl());
                            Glide.with(activity).load(getImageUrl()).into(imageView);
                        }
                    })
                    .onCancel(result -> Log.d(TAG, "openSingleGallery: " + result))
                    .start();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, Const.CODE_REQUEST_STOAREG);
//            openSingleGallery(activity, listImages, adapter, imageView);
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    public static void selectMultipleImage(Activity activity, List<Attachment> listImages, OrderImagesAdapter adapter) {

        Album.initialize(AlbumConfig.newBuilder(activity)
                .setAlbumLoader(new MediaLoader()).build());

        Album.image(activity) // Image selection.
                .multipleChoice()
                .camera(true)
                .columnCount(3)
                .selectCount(30)
                .onResult(result -> {
                    for (int i = 0; i < result.size(); i++) {
                        listImagesUrls.add(new Attachment(result.get(i).getPath()));
                    }
                    listImages.addAll(listImagesUrls);
                    adapter.notifyDataSetChanged();

                })
                .onCancel(result -> {
                    Log.e("selectMultiple: " , result);
                }).start();


    }

    public static String getDate(Activity activity, long time) {
        Calendar cal = Calendar.getInstance(getAppLocale(activity));
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd/MM/yyyy", cal).toString();
        return date;
    }

    public static boolean validatePhoneNumber(Activity context, String phoneNumber, EditText editText) {
        if (TextUtils.isEmpty(phoneNumber)) {
            editText.setError(context.getString(R.string.please_enter_phone));
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }

    public static boolean validateOldPassword(Activity context, String password, EditText editText) {
        if (TextUtils.isEmpty(password)) {
            editText.setError(context.getString(R.string.please_old_password));
            return false;
        }else if(password.length() < 6){
            editText.setError(context.getString(R.string.password_must_be_6_chat));
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }

    public static boolean validatePassword(Activity context, String password, String message, EditText editText) {
        if (TextUtils.isEmpty(password)) {
            editText.setError(message);
            return false;
        }else if(password.length() < 6){
            editText.setError(context.getString(R.string.password_must_be_6_chat));
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }

    public static boolean validateConfirmPassword(Activity context, String password, String confirmPassword, EditText editText) {
        if (TextUtils.isEmpty(confirmPassword)) {
            editText.setError(context.getString(R.string.please_enter_confirm_password));
            return false;
        }else if(confirmPassword.length() < 6){
            editText.setError(context.getString(R.string.password_must_be_6_chat));
            return false;
        }else if(!password.equals(confirmPassword)){
            editText.setError(context.getString(R.string.two_password_not_match));
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }

    public static boolean validateName(Activity context, String name, EditText editText) {
        if (TextUtils.isEmpty(name)) {
            editText.setError(context.getString(R.string.please_enter_name));
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }

    public static boolean validateAddress(Activity context, String address, EditText editText) {
        if (TextUtils.isEmpty(address)) {
            editText.setError(context.getString(R.string.please_enter_address));
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }

    public static boolean validateEmail(Activity context, String email, EditText editEmail) {

        if (TextUtils.isEmpty(email)) {
            editEmail.setError(context.getString(R.string.please_enter_email));
            return false;
        } else if (!validateEmail(email)) {
            editEmail.setError(context.getString(R.string.invalid_email));
            return false;
        } else {
            editEmail.setError(null);
            return true;
        }
    }

    public static boolean validateOTP(Activity context, String otp, PinView pinView) {
        if (TextUtils.isEmpty(otp)) {
            pinView.setError(context.getString(R.string.enter_verify_code));
            return false;
        } else {
            pinView.setError(null);
            return true;
        }
    }

    public static boolean validateOrderDetails(Activity context, String details, EditText editText) {
        if (TextUtils.isEmpty(details)) {
            editText.setError(context.getString(R.string.please_enter_order_details));
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }

    private static boolean validateEmail(String emailAddress) {
        String expression = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = emailAddress;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }


}
