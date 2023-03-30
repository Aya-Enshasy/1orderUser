package com.user.order.ui.activivtes.auth;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.user.order.MapsActivity;
import com.user.order.R;
import com.user.order.databinding.ActivitySignUpBinding;
import com.user.order.databinding.ActivitySplashBinding;
import com.user.order.model.auth.Auth;
import com.user.order.model.auth.AuthData;
import com.user.order.model.city.Cities;
import com.user.order.model.city.City;
import com.user.order.model.country.Country;
import com.user.order.model.settings.Settings;
import com.user.order.ui.activivtes.settings.PrivacyActivity;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PhoneTextWatcher;
import com.user.order.utils.PreferencesManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = SignUpActivity.class.getSimpleName();

    public Dialog loader_dialog;
    String cities, city, prefix,city_id,countryCode;
    int country_id = 1;
    ActivitySignUpBinding binding;
    List<City> citiesData = new ArrayList<>();
    private boolean isShowPassword = false,isShowConfirmPassword = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();
    }

    private void initUI() {

        countryCode = PreferencesManager.loadCurrentCountryCode(this, Const.KEY_CURRENT_COUNTRY_CODE);

        binding.edPhone.addTextChangedListener(new PhoneTextWatcher(binding.edPhone));
        clickListeners();
        countrySpinner();
        getSettingsApi(country_id);


    }

    @SuppressLint("ClickableViewAccessibility")
    private void clickListeners(){
        binding.tvTerms.setOnClickListener(View->{startActivity(new Intent(this, PrivacyActivity.class));});
        binding.ivBack.setOnClickListener(View->{onBackPressed();});
        binding.btnSignUp.setOnClickListener(View->{
            String fullName = binding.inputFullName.getText().toString();
            String phone = binding.edPhone.getText().toString();
            String code = binding.edIntroductionNumber1.getText().toString();
            String address = binding.inputAddress.getText().toString();
            String password = binding.inputPassword.getText().toString();
            String confirmPassword = binding.inputConfirmPassword.getText().toString();

            if (!HelperMethods.isInternetConnected(this)) {
                HelperMethods.showCustomSnackBar(this, binding.relContainer, getString(R.string.check_internet),
                        false, Snackbar.LENGTH_SHORT);
                return;
            }

            if (!HelperMethods.validateName(this, fullName, binding.inputFullName) |
                    !HelperMethods.validatePhoneNumber(this, phone, binding.edPhone) |
                    !HelperMethods.validateAddress(this, address, binding.inputAddress) |
                    !HelperMethods.validatePassword(this, password, getString(R.string.please_enter_password), binding.inputPassword) |
                    !HelperMethods.validateConfirmPassword(this, password, confirmPassword, binding.inputConfirmPassword)) {

                return;
            }


            if (binding.cbTerms.isChecked())
                signUp(fullName, code , phone, country_id, Integer.valueOf(city_id), address, password, confirmPassword);
            else
                Toast.makeText(this, getString(R.string.plaese_agree_terms), Toast.LENGTH_SHORT).show();

            Log.d(TAG, "onSignUpClick: " + country_id);
            Log.d(TAG, "onSignUpClick: " + city_id);



        });

        binding.ivShowPassword.setOnTouchListener((v, event) -> {

            isShowPassword = !isShowPassword;
            if (isShowPassword) {
                Glide.with(this).load(R.drawable.ic_icon_show).into(binding.ivShowPassword);
                binding.inputPassword.setInputType(InputType.TYPE_CLASS_TEXT);
            } else {
                Glide.with(this).load(R.drawable.ic_icon_hide).into(binding.ivShowPassword);
                binding.inputPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            return false;
        });

        binding.ivShowConfirmPassword.setOnTouchListener((v, event) -> {

            isShowConfirmPassword = !isShowConfirmPassword;
            if (isShowConfirmPassword) {
                Glide.with(this).load(R.drawable.ic_icon_show).into(binding.ivShowConfirmPassword);
                binding.inputConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT);
            } else {
                Glide.with(this).load(R.drawable.ic_icon_hide).into(binding.ivShowConfirmPassword);
                binding.inputConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            return false;
        });

    }

    private void signUp(String fullName, String code, String phone, Integer countryId, Integer cityId, String address, String password, String confirmPassword) {

        loaderDialog();
        String fcm_token = PreferencesManager.getStringPreferences(Const.KEY_FCM_TOKEN);

        String phoneNumber = new StringBuilder()
                .append(code)
                .append(phone)
                .toString();

        HelperMethods.get1OrderAPI().signUp(HelperMethods.getAppLanguage(this),
                        fullName, phone,
                        countryId, cityId,
                        address,
                        password, confirmPassword,
                        "client",fcm_token)
                .enqueue(new Callback<Auth>() {
                    @Override
                    public void onResponse(Call<Auth> call, Response<Auth> response) {
                        loader_dialog.dismiss();
                        if(response.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            AuthData auth = response.body().getData();
                            PreferencesManager.saveUserToken(SignUpActivity.this, Const.KEY_USER_TOKEN, auth.getAccessToken());
                            PreferencesManager.saveUserData(SignUpActivity.this, Const.KEY_USER_DATA, auth.getUser());
                            startActivity(new Intent(SignUpActivity.this, MapsActivity.class));
                            finish();
                        }else{
                            loader_dialog.dismiss();
                            parseError(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<Auth> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                        loader_dialog.dismiss();
                    }
                });

    }

    private void countrySpinner() {
        List<String> countryName = new ArrayList<>();

        String CategoryList =PreferencesManager.getStringPreferences(Const.KEY_COUNTRY_LIST);

        Gson gson = new Gson();
        if (!CategoryList.isEmpty()) {
            List<Country> countryData = gson.fromJson(CategoryList, new TypeToken<List<Country>>() {
            }.getType());
            for (int i = 0; i < countryData.size(); i++) {
                countryName.add(countryData.get(i).getName());
            }
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(SignUpActivity.this,
                    R.layout.custom_spinner_dropdown, countryName);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.edIntroductionNumber.setPopupBackgroundResource(R.drawable.dialog);


            binding.edIntroductionNumber.setAdapter(spinnerArrayAdapter);
            binding.edIntroductionNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                    int id = countryData.get(pos).getId();
                    prefix = countryData.get(pos).getPhoneCode();
                    binding.edIntroductionNumber1.setText("+ "+prefix);
                    country_id = id;
                    getSettingsApi(id);
                    id = pos;
                    Log.e("country_id", country_id + "");
                    Log.e("prefix", prefix + "");
                    getCities();
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }

            });

        }
    }

    private void getCities() {
        binding.progressBar.setVisibility(View.VISIBLE);
        HelperMethods.get1OrderAPI().getCities(HelperMethods.getAppLanguage(this),country_id).enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {
                Log.d("response code", response.code() + "");
                if (response.isSuccessful()) {
                    Log.d("Success", new Gson().toJson(response.body().getCities()));
                    cities = new Gson().toJson(response.body().getCities());
                    Cities getAllCategories = response.body();
                    assert getAllCategories != null;
                    citiesData = getAllCategories.getCities();
                    spinnerCities(cities);
                    binding.progressBar.setVisibility(View.GONE);

                } else {

                }
            }

            @Override
            public void onFailure(Call<Cities> call, Throwable t) {
                Log.d("onFailure", t.getMessage() + "");
                call.cancel();
            }
        });
    }

    private void spinnerCities(String categories) {
        List<String> countryName = new ArrayList<>();
        List<String> countryId = new ArrayList<>();
        Gson gson = new Gson();
        if (!categories.isEmpty()) {
            List<City> countryData = gson.fromJson(categories, new TypeToken<List<City>>() {
            }.getType());
            for (int i = 0; i < countryData.size(); i++) {
                countryName.add(countryData.get(i).getName());
                countryId.add(String.valueOf(countryData.get(i).getId()));
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    getBaseContext(), android.R.layout.simple_spinner_dropdown_item, countryName);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            binding.spinnerCity.setAdapter(adapter);

            binding.spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    city = countryName.get(position);
                    city_id = countryId.get(position);
                    Log.e("cityId",city_id);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    protected void loaderDialog() {
        loader_dialog = new Dialog(this);
        loader_dialog.setContentView(R.layout.loader_dialog);
        loader_dialog.setCancelable(false);
        loader_dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER);
        loader_dialog.getWindow().setBackgroundDrawableResource(R.drawable.transparent);
        loader_dialog.show();
    }

    @SuppressLint("SetTextI18n")
    public void parseError(Response<?> response) {
        String message = "";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response.errorBody().string());
            message = jsonObject.getString("message");
            Log.e("jsonObject", message);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    protected void getSettingsApi(int id) {

        HelperMethods.get1OrderAPI().getSettings(HelperMethods.getAppLanguage(this),id).enqueue(new Callback<Settings>() {
            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {

                if (response.isSuccessful()) {
                    if (response.body().getData()!=null){
                    PreferencesManager.setStringPreferences(Const.SETTINGS_PRIVACY_TITLE,response.body().getData().getAppContent().getDriverPrivacyTitle());
                    PreferencesManager.setStringPreferences(Const.SETTINGS_PRIVACY_BODY,response.body().getData().getAppContent().getDriverPrivacyContent());
                    Log.e("Message", response.code() + "");}
                } else {
                    loader_dialog.dismiss();
                    Log.e("errorMessage", response.message() + "");
                }
            }

            @Override
            public void onFailure(Call<Settings> call, Throwable t) {
                t.printStackTrace();
                call.cancel();
            }
        });
    }

}
