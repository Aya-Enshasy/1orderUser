package com.user.order.ui.activivtes.tracking;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.PolyUtil;
import com.user.order.R;
import com.user.order.databinding.ActivityMapTakingBinding;
import com.user.order.model.orderdetails.Driver;
import com.user.order.ui.activivtes.cart.CartActivity;
import com.user.order.ui.activivtes.chat.ChatActivity;
import com.user.order.ui.activivtes.settings.ContactUsActivity;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MapTrackingActivity extends AppCompatActivity implements OnMapReadyCallback {
    ActivityMapTakingBinding binding;
    private Driver user;
    private GoogleMap mMap;
    private LocationManager locationManager = null;
    private boolean isEnabled = false;
    private boolean isPermissionGranted = false;
    private LocationRequest locationRequest;
    String invoice_num, order_id, status, user_id;
    public double latitude1, longitude1,lat,lng;
    double value =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapTakingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);


        getCurrentLocation();

        data();
        clickListeners();
    }


    private void data() {
        if (getIntent() != null) {
            user = (Driver) getIntent().getSerializableExtra(Const.KEY_DRIVER);
            invoice_num = getIntent().getStringExtra(Const.KEY_order_id);
            order_id = getIntent().getStringExtra(Const.KEY_invoice_num);
            status = getIntent().getStringExtra(Const.KEY_status);
            user_id = getIntent().getStringExtra(Const.KEY_user_id);
            lat = Double.parseDouble(PreferencesManager.getStringPreferences(Const.KEY_Store_Lat));
            lng = Double.parseDouble(PreferencesManager.getStringPreferences(Const.KEY_Store_Lng));


            binding.userName.setText( PreferencesManager.getStringPreferences("driver_name"));
            binding.mobileNumber.setText("+" +  PreferencesManager.getStringPreferences("mobile"));
            binding.carType.setText("Car Type : " +  PreferencesManager.getStringPreferences("car_number"));
            binding.carNumber.setText("Car Number : " + PreferencesManager.getStringPreferences("vehicleType"));
            if (!PreferencesManager.getStringPreferences("rate").equals("")){
             value = Double.parseDouble(PreferencesManager.getStringPreferences("rate"));
            binding.rate.setText( PreferencesManager.getStringPreferences("rate"));
            }else {
                value = 0.0;
                value = Double.parseDouble(new DecimalFormat("#.#").format(value));
                binding.rate.setText(value + "");
            }
            Glide.with(this).load(PreferencesManager.getStringPreferences("img")).placeholder(R.drawable.ic_user_defult).into(binding.ivUserImage);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        checkPermission();

        if (mMap != null) {
            MapsInitializer.initialize(this);

            LocationManager loc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
                return;
            }
            if (loc != null) {
                LatLng sydney = new LatLng(latitude1, longitude1);
                LatLng sydney1 = new LatLng(lat, lng);
                mMap.addMarker(new MarkerOptions().position(sydney1).icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_stroe)));
                mMap.addMarker(new MarkerOptions().position(sydney).icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_car)));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16.0f));


                String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + latitude1 + "," + longitude1+ "&destination=" + lat + "," +lng + "&key=" + "AIzaSyCIRYeAhC9c3u2KQ9HbUvL1pBbueLyTroA";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Parse the response to get the polyline points
                                List<LatLng> points = new ArrayList<>();
                                JSONArray routes = response.optJSONArray("routes");
                                if (routes != null && routes.length() > 0) {
                                    JSONArray legs = routes.optJSONObject(0).optJSONArray("legs");
                                    if (legs != null && legs.length() > 0) {
                                        JSONArray steps = legs.optJSONObject(0).optJSONArray("steps");
                                        if (steps != null && steps.length() > 0) {
                                            for (int i = 0; i < steps.length(); i++) {
                                                JSONObject step = steps.optJSONObject(i);
                                                if (step != null) {
                                                    JSONObject polyline = step.optJSONObject("polyline");
                                                    if (polyline != null) {
                                                        String encodedPoints = polyline.optString("points");
                                                        List<LatLng> decodedPoints = PolyUtil.decode(encodedPoints);
                                                        points.addAll(decodedPoints);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                // Draw the polyline on the map
                                PolylineOptions polylineOptions = new PolylineOptions()
                                        .addAll(points)
                                        .width(5) // Replace with the desired width of the line
                                        .color(Color.RED); // Replace with the desired color of the line
                                Polyline polyline = googleMap.addPolyline(polylineOptions);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle the error
                            }
                        });
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(jsonObjectRequest);

            }

        }

    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (HelperMethods.getUserLatitude(this) != null) {

                latitude1 = Double.parseDouble(PreferencesManager.getStringPreferences("latitude"));
                longitude1 = Double.parseDouble(PreferencesManager.getStringPreferences("longitude"));
                LatLng currentlyLng = new LatLng(latitude1, longitude1);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentlyLng, 16.0f));
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, Const.CODE_REQUEST_LOCATION);
        }
    }

    private void clickListeners() {
        binding.mobileNumber.setOnClickListener(View -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + PreferencesManager.getStringPreferences("mobile")));
            startActivity(intent);
        });

        binding.whatsapp.setOnClickListener(View -> {
            String whatsApp = PreferencesManager.getStringPreferences(Const.KEY_WHATSAPP);
            String url = "https://wa.me/"+whatsApp;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });

        binding.chat.setOnClickListener(View -> {
            Intent intent = new Intent(getBaseContext(), ChatActivity.class)
                    .putExtra(Const.KEY_INVOICE_NUMBER, PreferencesManager.getStringPreferences(Const.KEY_INVOICE_NUMBER))
                    .putExtra(Const.KEY_driver_id,  PreferencesManager.getStringPreferences(Const.KEY_driver_id))
                    .putExtra(Const.KEY_ORDER_ID,  PreferencesManager.getStringPreferences(Const.KEY_ORDER_ID))
                    .putExtra(Const.KEY_CHAT, PreferencesManager.getStringPreferences(Const.KEY_CHAT))
                    .putExtra(Const.KEY_STATUS,  PreferencesManager.getStringPreferences(Const.KEY_STATUS));
            startActivity(intent);
        });

        binding.ivBack.setOnClickListener(View -> {
            onBackPressed();
        });
    }

    private void getCurrentLocation() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                if (isGPSEnabled()) {

                    LocationRequest locationRequest = LocationRequest.create();
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    locationRequest.setInterval(10000);

                    FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        fusedLocationClient.requestLocationUpdates(locationRequest, new LocationCallback() {
                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                Location location = locationResult.getLastLocation();
                                if (location != null) {
                                    double latitude = location.getLatitude();
                                    double longitude = location.getLongitude();
                                    latitude1 = latitude;
                                    longitude1 = longitude;
                                }
                            }
                        }, Looper.myLooper());
                    }


                } else {
                    turnOnGPS();
                }

            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    private void turnOnGPS() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(this)
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
//                    Toast.makeText(getActivity(), "GPS is already tured on", Toast.LENGTH_SHORT).show();
//
                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(MapTrackingActivity.this, 2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //Device does not have location
                            break;
                    }
                }
            }
        });

    }

    private boolean isGPSEnabled() {
        LocationManager locationManager = null;
        boolean isEnabled = false;

        if (locationManager == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;

    }

    private BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}