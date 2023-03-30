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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.user.order.HomeActivity;
import com.user.order.MapsActivity;
import com.user.order.R;
import com.user.order.databinding.ActivityLoginBinding;
import com.user.order.databinding.ActivitySignUpBinding;
import com.user.order.model.auth.Auth;
import com.user.order.model.auth.AuthData;
import com.user.order.model.country.Country;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PhoneTextWatcher;
import com.user.order.utils.PreferencesManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    public Dialog loader_dialog;
    ActivityLoginBinding binding;
    String prefix,countryCode;
    int country_id = 1;
    private boolean isShowPassword = false;
    private List<Country> listCounreies;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();
        countrySpinner();
        clickListeners();
        Log.d(TAG, "onCreate LAT: " + PreferencesManager.loadAppData(this, Const.KEY_CURRENT_LATITUDE));
        Log.d(TAG, "onCreate LNG: " + PreferencesManager.loadAppData(this, Const.KEY_CURRENT_LONGITUDE));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initUI() {
        listCounreies = new ArrayList<>();

        countryCode = PreferencesManager.loadCurrentCountryCode(this, Const.KEY_CURRENT_COUNTRY_CODE);
        Log.d(TAG, "initUI: " + countryCode);
        binding.edPhone.addTextChangedListener(new PhoneTextWatcher(binding.edPhone));


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
    }

    private void login(String code, String phone, String password) {

        loaderDialog();
        String fcm_token = PreferencesManager.getStringPreferences(Const.KEY_FCM_TOKEN);


        String phoneNumber = new StringBuilder().append(code).append(phone).toString();
        Log.d(TAG, "login: " + phoneNumber);
        Log.d(TAG, "login: " + password);
        HelperMethods.get1OrderAPI()
                .login(HelperMethods.getAppLanguage(this),
                        phoneNumber, password,fcm_token)
                .enqueue(new Callback<Auth>() {
                    @Override
                    public void onResponse(Call<Auth> call, Response<Auth> response) {
                        loader_dialog.dismiss();
                        if (response.isSuccessful()){
                            if (response.body().getSuccess().equals("success")){
                                AuthData auth = response.body().getData();
                                PreferencesManager.saveUserToken(LoginActivity.this, Const.KEY_USER_TOKEN, auth.getAccessToken());
                                PreferencesManager.saveUserData(LoginActivity.this, Const.KEY_USER_DATA, auth.getUser());
                                startActivity(new Intent(LoginActivity.this, MapsActivity.class).putExtra(Const.KEY_MAP, Const.AUTH));
                                finish();
                            }else {
                                HelperMethods.showCustomToast(LoginActivity.this, response.body().getMessage(), false);
                            }

                        }else{
                            try {
                                Auth auth = new Gson().fromJson(response.errorBody().string(), Auth.class);
                                Log.e(TAG, "onResponse: " + auth.getData());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Auth> call, Throwable t) {
                        loader_dialog.dismiss();
                        Log.e(TAG, "onFailure: " + t.getMessage());
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
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(LoginActivity.this,
                    R.layout.custom_spinner_dropdown, countryName);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.edIntroductionNumber.setPopupBackgroundResource(R.drawable.dialog);


            binding.edIntroductionNumber.setAdapter(spinnerArrayAdapter);
            binding.edIntroductionNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                    int id = countryData.get(pos).getId();
                    prefix = countryData.get(pos).getPhoneCode();
                    binding.edIntroductionNumber1.setText("+"+prefix);
                    country_id = id;
                    id = pos;
                    Log.e("country_id", country_id + "");
                    Log.e("prefix", prefix + "");
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
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

    private void clickListeners(){
        binding.tvForgetPassword.setOnClickListener(View->{startActivity(new Intent(this, ForgetPasswordActivity.class));});
        binding.tvSignUp.setOnClickListener(View->{startActivity(new Intent(this, SignUpActivity.class));});
        binding.btnSkip.setOnClickListener(View->{  startActivity(new Intent(this, HomeActivity.class));finish();});
        binding.btnLogin.setOnClickListener(View-> {
            String phone = binding.edPhone.getText().toString();
            String password = binding.inputPassword.getText().toString();

            if (!HelperMethods.isInternetConnected(this)) {
                HelperMethods.showCustomSnackBar(this, binding.relContainer, getString(R.string.check_internet),
                        false, Snackbar.LENGTH_SHORT);
                loader_dialog.dismiss();
                return;
            }

            if (!HelperMethods.validatePhoneNumber(this, phone, binding.edPhone) |
                    !HelperMethods.validatePassword(this, password, getString(R.string.please_enter_password), binding.inputPassword)) {
                return;
            }

            login(prefix, phone, password);
        }    );
    }


}
