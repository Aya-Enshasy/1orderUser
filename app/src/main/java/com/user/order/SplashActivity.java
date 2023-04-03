package com.user.order;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.user.order.databinding.ActivityChatBinding;
import com.user.order.databinding.ActivitySplashBinding;
import com.user.order.model.User;
import com.user.order.model.country.Countries;
import com.user.order.model.country.Country;
import com.user.order.model.profile.Profile;
import com.user.order.model.settings.AppContent;
import com.user.order.model.settings.CountryCode;
import com.user.order.model.settings.Settings;
import com.user.order.ui.activivtes.auth.LoginActivity;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();

    private LocationManager locationManager = null;
    private boolean isEnabled = false;
    private boolean isPermissionGranted = false;
    private LocationRequest locationRequest;
    ActivitySplashBinding binding;
    List<Country> countryData;
    double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        HelperMethods.checkAppLanguage(this);
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getCountryCode();
        initLocationRequest();
        getCurrentLocation();
        animation();
        String token = PreferencesManager.getStringPreferences(Const.KEY_FCM_TOKEN);
        Log.e("FCM_TOKEN", token);
        countrySpinner();

    }

    private void animation() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startEnterAnimation();

            }
        }, 1000);

    }

    private void startEnterAnimation() {
        binding.imageView3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_in));
        binding.imageView3.setVisibility(View.VISIBLE);
    }

    private void startExitAnimation() {
        binding.imageView3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_out));
        binding.imageView3.setVisibility(View.INVISIBLE);
    }

    private void runActivity() {
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
//                startActivity(new Intent(SplashActivity.this, MapsActivity.class)
//                        .putExtra(Const.KEY_MAP, Const.AUTH));
//                PreferencesManager.setStringPreferences(Const.KEY_CURRENT_LATITUDE, String.valueOf(lat));
//                PreferencesManager.setStringPreferences(Const.KEY_CURRENT_LONGITUDE, String.valueOf(lng));
//
//                finish();
                if (PreferencesManager.loadUserToken(SplashActivity.this, Const.KEY_USER_TOKEN) != null && !PreferencesManager.loadUserToken(SplashActivity.this, Const.KEY_USER_TOKEN).equals("")) {
                    if (HelperMethods.getDeliveryAddress(SplashActivity.this) != null) {
                        loadUserInfo();
                        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(SplashActivity.this, MapsActivity.class)
                                .putExtra(Const.KEY_MAP, Const.AUTH));
                        PreferencesManager.setStringPreferences(Const.KEY_CURRENT_LATITUDE, String.valueOf(lat));
                        PreferencesManager.setStringPreferences(Const.KEY_CURRENT_LONGITUDE, String.valueOf(lng));

                        finish();
                    }
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    PreferencesManager.setStringPreferences(Const.KEY_CURRENT_LATITUDE, String.valueOf(lat));
                    PreferencesManager.setStringPreferences(Const.KEY_CURRENT_LONGITUDE, String.valueOf(lng));
                    finish();
                }
//

            }
        }, 5000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startExitAnimation();

            }
        }, 3000);
    }

    private void getSettings(int countryId) {

        HelperMethods.get1OrderAPI()
                .getSettings(HelperMethods.getAppLanguage(this), countryId)
                .enqueue(new Callback<Settings>() {
                    @Override
                    public void onResponse(Call<Settings> call, Response<Settings> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getSuccess().equals("success")) {
                                Log.d(TAG, "onResponse: " + response.body().getData().getCountry().getName());
                                Log.e("getMobile: " ,response.body().getData().getAppContent().getMobile());
                                Country country = response.body().getData().getCountry();

                                AppContent appContent = response.body().getData().getAppContent();
                                if (PreferencesManager.getCountrySettings(SplashActivity.this, Const.KEY_SETTINGS) == null)
                                    PreferencesManager.saveCountrySettings(SplashActivity.this, Const.KEY_SETTINGS, country);

                                if (PreferencesManager.getAppContent(SplashActivity.this, Const.KEY_APP_CONTENT) == null) {
                                    PreferencesManager.saveAppContent(SplashActivity.this, Const.KEY_APP_CONTENT, appContent);
                                    PreferencesManager.setStringPreferences(Const.KEY_WHATSAPP, response.body().getData().getAppContent().getMobile());
                                }
                                PreferencesManager.setStringPreferences(Const.KEY_TAX, response.body().getData().getCountry().getSetting().getTax());

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Settings> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }

    private void getCountryCode() {
        HelperMethods.getIPAPI()
                .getCountryCode()
                .enqueue(new Callback<CountryCode>() {
                    @Override
                    public void onResponse(Call<CountryCode> call, Response<CountryCode> response) {
                        if (response.isSuccessful()) {
                            PreferencesManager.saveCurrentCountryCode(SplashActivity.this, Const.KEY_CURRENT_COUNTRY_CODE, response.body().getCountryCode());
                            Log.d(TAG, "onResponse getCountryCode: " + PreferencesManager.loadCurrentCountryCode(SplashActivity.this, Const.KEY_CURRENT_COUNTRY_CODE));
                            getSettings(1);
                        }
                    }

                    @Override
                    public void onFailure(Call<CountryCode> call, Throwable t) {

                    }
                });
    }

    private void countrySpinner() {
        HelperMethods.get1OrderAPI().getCountries(HelperMethods.getAppLanguage(this)).enqueue(new Callback<Countries>() {
            @Override
            public void onResponse(Call<Countries> call, retrofit2.Response<Countries> response) {

                if (response.isSuccessful()) {
                    countryData = response.body().getCountries();

                    if (countryData != null && countryData.size() > 0) {
                        Gson gson = new Gson();
                        String list = gson.toJson(countryData);
                        PreferencesManager.setStringPreferences(Const.KEY_COUNTRY_LIST, list);
                        Log.e("Message", response.body().getMessage() + "");

                    }
                } else {
                    Log.e("errorMessage", response.code() + "");
                }
            }


            @Override
            public void onFailure(Call<Countries> call, Throwable t) {
                t.printStackTrace();
                Log.e("error", t.getMessage());
            }
        });

    }

    private void initLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);
    }

    private void getCurrentLocation() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                if (isGPSEnable()) {

                    LocationServices.getFusedLocationProviderClient(this)
                            .requestLocationUpdates(locationRequest,
                                    new LocationCallback() {
                                        @Override
                                        public void onLocationResult(@NonNull LocationResult locationResult) {
                                            super.onLocationResult(locationResult);

                                            LocationServices.getFusedLocationProviderClient(SplashActivity.this)
                                                    .removeLocationUpdates(this);
                                            if (locationResult != null && locationResult.getLocations().size() > 0) {
                                                 lat = locationResult.getLastLocation().getLatitude();
                                                lng = locationResult.getLastLocation().getLongitude();
                                                int index = locationResult.getLocations().size() - 1;
                                                double latitude = locationResult.getLocations().get(index).getLatitude();
                                                double longitude = locationResult.getLocations().get(index).getLongitude();
                                                PreferencesManager.setStringPreferences("latitude", latitude+"");
                                                PreferencesManager.setStringPreferences("longitude", longitude+"");
                                                Log.d(TAG, "onLocationResult: " + "ALLOW");
                                                runActivity();

                                            }
                                        }
                                    }
                                    , Looper.getMainLooper());
                } else {
                    trunOnGPS();
                }
            } else {
                ActivityCompat.requestPermissions(this, new String[]{
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                }, Const.CODE_REQUEST_LOCATION);
            }
        }
    }

    private void trunOnGPS() {

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
                .setAlwaysShow(true);

        Task<LocationSettingsResponse> taskResult = LocationServices.getSettingsClient(this)
                .checkLocationSettings(builder.build());

        taskResult.addOnCompleteListener(task -> {
            try {
                LocationSettingsResponse response = task.getResult(ApiException.class);
                Toast.makeText(SplashActivity.this, "GPS is already tured on", Toast.LENGTH_SHORT).show();
            } catch (ApiException e) {
                switch (e.getStatusCode()) {
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                            resolvableApiException.startResolutionForResult(SplashActivity.this, Const.REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        //Device does not have location
                        break;
                }
            }
        });
    }

    private boolean isGPSEnable() {

        if (locationManager == null)
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Const.CODE_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(this, "Permission Needed To Run The App", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void loadUserInfo() {
        HelperMethods.get1OrderAPI()
                .getUserProfile(HelperMethods.getAppLanguage(this),
                        HelperMethods.getUserToken(this))
                .enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                        if (response.isSuccessful()) {
                            PreferencesManager.setStringPreferences(Const.KEY_USER_NAME,response.body().getData().getUser().getName());
                            PreferencesManager.setStringPreferences(Const.KEY_USER_ADDRESS,response.body().getData().getUser().getAddress());
                            PreferencesManager.setStringPreferences(Const.KEY_USER_Email,response.body().getData().getUser().getEmail());
                            PreferencesManager.setStringPreferences(Const.KEY_USER_UEL,response.body().getData().getUser().getAvatarUrl());
                            PreferencesManager.setStringPreferences(Const.KEY_USER_CITY_ID,response.body().getData().getUser().getCity().getId()+"");
                            PreferencesManager.setStringPreferences(Const.KEY_USER_COUNTRY_ID,response.body().getData().getUser().getCountryId()+"");
                                User currentUser = response.body().getData().getUser();
                                PreferencesManager.setStringPreferences(Const.KEY_USER_UEL, currentUser.getAvatarUrl());
                            } else {
//                                HelperMethods.showCustomToast(SplashActivity.this, response.body().getMessage(), false);

                        }
                    }

                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {
                        Log.e("TAG", "onFailure:  " + t.getMessage());
                    }
                });
    }

}