package com.user.order;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.user.order.model.DeliveryAddress;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final String TAG = MapsActivity.class.getSimpleName();

    private MapView mapView;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_text_address)
    TextView tvAddress;
    @BindView(R.id.input_address)
    EditText inputAddress;
    private GoogleMap mMap;
    private LocationManager locationManager = null;
    private boolean isEnabled = false;
    private boolean isPermissionGranted = false;
    private LocationRequest locationRequest;
    private int maxResults = 1;
    private String keyFrom;

    @OnClick(R.id.iv_back)
    void onBackClick() {
        onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.card_current_location)
    void onCurrentLocationCLick() {
        checkPermission();
    }

    @OnClick(R.id.btn_add_address)
    void onAddAddressClick() {
        String address = inputAddress.getText().toString();

        if (!HelperMethods.validateAddress(this, address, inputAddress)) {
            return;
        }

        if (getIntent().getStringExtra("order") != null) {
            DeliveryAddress currentAddress = HelperMethods.getDeliveryAddress(this);
            DeliveryAddress deliveryAddress = new DeliveryAddress();
            deliveryAddress.setAddress(currentAddress.getAddress());
            deliveryAddress.setOtherAddress(inputAddress.getText().toString());
            deliveryAddress.setLatitude(currentAddress.getLatitude());
            deliveryAddress.setLongitude(currentAddress.getLongitude());

            PreferencesManager.saveDeliveryAddress(this, Const.KEY_DELIVERY_ADDRESS, deliveryAddress);
            PreferencesManager.saveAppData(this, Const.KEY_CURRENT_LATITUDE, String.valueOf(currentAddress.getLatitude()));
            PreferencesManager.saveAppData(this, Const.KEY_CURRENT_LONGITUDE, String.valueOf(currentAddress.getLongitude()));

            PreferencesManager.setStringPreferences("from_home","from_home");
            finish();
        }
        else {
            DeliveryAddress currentAddress = HelperMethods.getDeliveryAddress(this);
            DeliveryAddress deliveryAddress = new DeliveryAddress();
            deliveryAddress.setAddress(currentAddress.getAddress());
            deliveryAddress.setOtherAddress(inputAddress.getText().toString());
            deliveryAddress.setLatitude(currentAddress.getLatitude());
            deliveryAddress.setLongitude(currentAddress.getLongitude());

            PreferencesManager.saveDeliveryAddress(this, Const.KEY_DELIVERY_ADDRESS, deliveryAddress);
            PreferencesManager.saveAppData(this, Const.KEY_CURRENT_LATITUDE, String.valueOf(currentAddress.getLatitude()));
            PreferencesManager.saveAppData(this, Const.KEY_CURRENT_LONGITUDE, String.valueOf(currentAddress.getLongitude()));

            startActivity(new Intent(MapsActivity.this, HomeActivity.class));
            finish();
        }
        PreferencesManager.setStringPreferences(Const.KEY_Address, tvAddress.getText().toString());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        initUI();
    }

    private void initUI() {

        if (getIntent() != null)
            keyFrom = getIntent().getStringExtra(Const.KEY_MAP);

        if (keyFrom != null) {
            if (keyFrom.equals(Const.AUTH)) {
                ivBack.setVisibility(View.VISIBLE);
                tvAddress.setVisibility(View.VISIBLE);
            } else {
                ivBack.setVisibility(View.VISIBLE);
                tvAddress.setVisibility(View.VISIBLE);
            }
        }


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        checkPermission();

        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

                LatLng center = mMap.getCameraPosition().target;
                String sLatitude = String.format("%.6f", center.latitude);
                String sLongitude = String.format("%.6f", center.longitude);
                StringBuilder mLatLng = new StringBuilder();
                mLatLng.append(sLatitude);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(center);
                DeliveryAddress deliveryAddress = new DeliveryAddress();
                Geocoder geocoder = new Geocoder(MapsActivity.this, HelperMethods.getAppLocale(MapsActivity.this));

                try {
                    List<Address> listAddress = geocoder.getFromLocation(Double.parseDouble(sLatitude), Double.parseDouble(sLongitude), maxResults);

                    if (listAddress.size() > 0) {

                        Address address = listAddress.get(0);

                        if (keyFrom != null) {
                            if (keyFrom.equals(Const.AUTH)) {
//                                inputAddress.setText(address.getAddressLine(0));
                            } else {
                                tvAddress.setText(address.getAddressLine(0));
                            }
                        }

                        deliveryAddress.setAddress(address.getAddressLine(0));
                        deliveryAddress.setOtherAddress(inputAddress.getText().toString());
                        deliveryAddress.setLatitude(Double.valueOf(sLatitude));
                        deliveryAddress.setLongitude(Double.valueOf(sLongitude));

                        PreferencesManager.saveDeliveryAddress(MapsActivity.this, Const.KEY_DELIVERY_ADDRESS, deliveryAddress);


                        markerOptions.title(address.getAddressLine(0));


                        DeliveryAddress delivery = PreferencesManager.getDeliveryAddress(MapsActivity.this, Const.KEY_DELIVERY_ADDRESS);
                        Log.d(TAG, "onMapReady12: " + delivery.getAddress());
                        PreferencesManager.setStringPreferences(Const.KEY_Address, delivery.getAddress());
                        tvAddress.setText(delivery.getAddress());
                        Log.d(TAG, "onMapReady: " + delivery.getLatitude() + "," + delivery.getLongitude());


                    }

                } catch (IOException e) {
                    Log.e(TAG, "onMapReady: " + e.getMessage());
                    Log.e(TAG, "onMapReady: " + e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        });

    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (HelperMethods.getUserLatitude(this) != null) {
                double lat = Double.parseDouble(PreferencesManager.getStringPreferences(Const.KEY_CURRENT_LATITUDE));
                double lng = Double.parseDouble(PreferencesManager.getStringPreferences(Const.KEY_CURRENT_LONGITUDE));
                LatLng currentlatLng = new LatLng(lat, lng);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentlatLng, 16.0f));
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, Const.CODE_REQUEST_LOCATION);
        }
    }

}