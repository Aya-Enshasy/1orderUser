package com.user.order.ui.activivtes.profile;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.hbb20.CCPCountry;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.user.order.R;
import com.user.order.model.User;
import com.user.order.model.auth.Auth;
import com.user.order.model.auth.AuthData;
import com.user.order.model.city.Cities;
import com.user.order.model.city.City;
import com.user.order.model.profile.Profile;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ProfileActivity.class.getSimpleName();
    public Dialog loader_dialog;

    @BindView(R.id.rel_container)
    RelativeLayout container;
    @BindView(R.id.civ_user)
    ShapeableImageView civUser;
    @BindView(R.id.input_full_name)
    EditText inputFullName;
    @BindView(R.id.input_address)
    EditText inputAddress;
    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.spinner_city)
    MaterialSpinner spinnerCity;

    List<City> listCities;
    private Integer cityId;

    @OnClick(R.id.iv_select_image)
    void onSelectImageClick() {
        HelperMethods.openSingleGallery(this, civUser);
    }

    @OnClick(R.id.iv_back)
    void onBackClick() {
        onBackPressed();
    }

    @OnClick(R.id.btn_save)
    void onUpdateProfileClick() {

        loaderDialog();

        String name = inputFullName.getText().toString();
        String address = inputAddress.getText().toString();
        String email = inputEmail.getText().toString();

        if (!HelperMethods.isInternetConnected(this)) {
            HelperMethods.showCustomSnackBar(this, container, getString(R.string.check_internet),
                    false, Snackbar.LENGTH_SHORT, this);
            loader_dialog.dismiss();
            return;
        }

        if (!HelperMethods.validateName(this, name, inputFullName) |
                !HelperMethods.validateAddress(this, address, inputAddress) |
                !HelperMethods.validateEmail(this, email, inputEmail)) {
            loader_dialog.dismiss();
            return;
        }

        if (HelperMethods.getCurrentUser(this) != null && HelperMethods.getUserToken(this) != null) {
            updateProfile(name, address, email, cityId, HelperMethods.getImageUrl());
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        initUI();
        getData();
        spinnerCity.setBackgroundResource(R.drawable.bg_input);
        spinnerCity.setItems(listCities);
    }

    private void initUI() {

        listCities = new ArrayList<>();

        spinnerCity.setOnItemSelectedListener((view, position, id, item) -> {
            cityId = listCities.get(position).getId();
            Log.d(TAG, "initUI: " + cityId);
        });
    }

    private void updateProfile(String name, String address, String email, Integer cityId, String imageUrl) {
        loaderDialog();
        if (HelperMethods.getCurrentUser(this).getCountryId() == null) {
            loader_dialog.dismiss();
            return;
        }

        if (HelperMethods.getCurrentUser(this).getMobile() == null) {
            loader_dialog.dismiss();
            return;
        }

        Log.d(TAG, "updateProfile: " + HelperMethods.getCurrentUser(this).getCountryId());
        Log.d(TAG, "updateProfile: " + HelperMethods.getCurrentUser(this).getMobile());

        MultipartBody.Part avatar = HelperMethods.convertFileToMultiPart(imageUrl, "avatar");
        RequestBody fullName = HelperMethods.convertToRequestBody(name);
        RequestBody userAddress = HelperMethods.convertToRequestBody(address);
        RequestBody userEmail = HelperMethods.convertToRequestBody(email);
        RequestBody phoneNumber = HelperMethods.convertToRequestBody(HelperMethods.getCurrentUser(this).getMobile());
        RequestBody countryId = HelperMethods.convertToRequestBody(HelperMethods.getCurrentUser(this).getCountryId());
        RequestBody citySelected = HelperMethods.convertToRequestBody(String.valueOf(cityId));

        HelperMethods.get1OrderAPI()
                .updateUserProfile(HelperMethods.getAppLanguage(this),
                        HelperMethods.getUserToken(this),
                        avatar, phoneNumber, fullName, userEmail, userAddress, countryId, citySelected)
                .enqueue(new Callback<Auth>() {
                    @Override
                    public void onResponse(Call<Auth> call, Response<Auth> response) {
                        loader_dialog.dismiss();
                        if (response.isSuccessful()) {
                            if (response.body().getSuccess().equals("success")) {
                                AuthData auth = response.body().getData();
                                PreferencesManager.setStringPreferences(Const.KEY_USER_NAME,response.body().getData().getUser().getName());
                                PreferencesManager.setStringPreferences(Const.KEY_USER_ADDRESS,response.body().getData().getUser().getAddress());
                                PreferencesManager.setStringPreferences(Const.KEY_USER_Email,response.body().getData().getUser().getEmail());
                                PreferencesManager.setStringPreferences(Const.KEY_USER_UEL,response.body().getData().getUser().getAvatarUrl());
                                PreferencesManager.saveUserData(ProfileActivity.this, Const.KEY_USER_DATA, auth.getUser());
                                finish();
                            } else {
                                HelperMethods.showCustomToast(ProfileActivity.this, response.body().getMessage(), false);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Auth> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                        loader_dialog.dismiss();
                    }
                });


    }

    private void getData(){
        inputFullName.setText(PreferencesManager.getStringPreferences(Const.KEY_USER_NAME));
        inputAddress.setText(PreferencesManager.getStringPreferences(Const.KEY_USER_ADDRESS));
        inputEmail.setText(PreferencesManager.getStringPreferences(Const.KEY_USER_Email));
        Glide.with(this).load(PreferencesManager.getStringPreferences(Const.KEY_USER_UEL)).placeholder(R.drawable.ic_user_defult).into(civUser);
        getCities(Integer.parseInt(PreferencesManager.getStringPreferences(Const.KEY_USER_COUNTRY_ID)));

    }

    private void getCities(Integer countryId) {
        HelperMethods.get1OrderAPI()
                .getCities(HelperMethods.getAppLanguage(this), countryId)
                .enqueue(new Callback<Cities>() {
                    @Override
                    public void onResponse(Call<Cities> call, Response<Cities> response) {
                        if (response.isSuccessful()) {
                            if (listCities != null) {
                                listCities.clear();
                                listCities.addAll(response.body().cities);
                                List<String> cities = new ArrayList<>();
                                for (City city : listCities)
                                    cities.add(city.getName());

                                ArrayAdapter cityAdapter = new ArrayAdapter(ProfileActivity.this, android.R.layout.simple_spinner_item, cities);
                                spinnerCity.setAdapter(cityAdapter);

                                for (int index = 0; index < cityAdapter.getCount(); index++) {
                                    if (listCities.get(index).getId() == Integer.parseInt(PreferencesManager.getStringPreferences(Const.KEY_USER_CITY_ID)))
                                        spinnerCity.setSelectedIndex(index);
                                }
                            }


                        } else {
                            try {
                                Cities error = new Gson().fromJson(response.errorBody().string(), Cities.class);
                                Log.e(TAG, "onResponse: " + error.getCities());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Cities> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }

    @Override
    public void onClick(View v) {
        onUpdateProfileClick();
    }

    protected void loaderDialog() {
        loader_dialog = new Dialog(this);
        loader_dialog.setContentView(R.layout.loader_dialog);
        loader_dialog.setCancelable(false);
        loader_dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER);
        loader_dialog.getWindow().setBackgroundDrawableResource(R.drawable.transparent);
        loader_dialog.show();
    }
}
