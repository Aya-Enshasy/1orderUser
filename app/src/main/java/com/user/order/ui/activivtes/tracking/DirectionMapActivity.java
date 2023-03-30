package com.user.order.ui.activivtes.tracking;

import static com.user.order.utils.Const.KEY_Destination_Lat;
import static com.user.order.utils.Const.KEY_Destination_Lng;
import static com.user.order.utils.Const.KEY_Store_Lat;
import static com.user.order.utils.Const.KEY_Store_Lng;
import static com.user.order.utils.Const.KEY_call;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import com.user.order.R;
import com.user.order.databinding.ActivityDirectionMapBinding;
import com.user.order.databinding.ActivityPaymentBinding;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DirectionMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    ActivityDirectionMapBinding binding;
    private double destinationLat, destinationLng, storeLat, storeLng;
    String res_number;
    private GoogleMap mMap;
    private LocationManager locationManager = null;
    private boolean isEnabled = false;
    private boolean isPermissionGranted = false;
    private LocationRequest locationRequest;
    public double latitude1, longitude1,lat,lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDirectionMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        init();
    }

    private void init() {
        if (getIntent() != null) {
            destinationLat = Double.parseDouble(PreferencesManager.getStringPreferences(Const.KEY_CURRENT_LATITUDE));
            destinationLng = Double.parseDouble(PreferencesManager.getStringPreferences(Const.KEY_CURRENT_LONGITUDE));
            storeLat = Double.parseDouble(getIntent().getStringExtra(KEY_Store_Lat));
            storeLng = Double.parseDouble(getIntent().getStringExtra(KEY_Store_Lng));
            res_number = getIntent().getStringExtra(KEY_call);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        clickListeners();
    }


    private void clickListeners() {
        binding.call.setOnClickListener(View -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + res_number));
            startActivity(intent);
        });

        binding.ivBack.setOnClickListener(View->{finish();});
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
                LatLng sydney = new LatLng(destinationLat, destinationLng);
                LatLng sydney1 = new LatLng(storeLat, storeLng);
                mMap.addMarker(new MarkerOptions().position(sydney1).icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_stroe)));
                mMap.addMarker(new MarkerOptions().position(sydney).icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_to_loc)));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16.0f));


                String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + destinationLat + "," + destinationLng+ "&destination=" + storeLat + "," +storeLng + "&key=" + "AIzaSyCIRYeAhC9c3u2KQ9HbUvL1pBbueLyTroA";
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
}