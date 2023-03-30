package com.user.order.ui.activivtes.auth;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.user.order.R;
import com.user.order.databinding.ActivityForgetPasswordBinding;
import com.user.order.model.country.Country;
import com.user.order.utils.Const;
import com.user.order.utils.PreferencesManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ForgetPasswordActivity extends AppCompatActivity {
    private static final String TAG = ForgetPasswordActivity.class.getSimpleName();
Dialog loader_dialog;
    ActivityForgetPasswordBinding binding;
    int country_id;
    String prefix,number;
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        countrySpinner();
        openActivities();

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void openActivities(){
        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                checkDataIsNotNull();
            }
        });

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void checkDataIsNotNull(){
        if (binding.edPhone.getText().toString().equals("")){
            binding.edPhone.setError(getText(R.string.please_enter_phone));
        }else {
            sendCodeVerification();
        }
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
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(ForgetPasswordActivity.this,
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
                    Log.e("Prefix", prefix + "");
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }

            });

        }
    }

    private void sendCodeVerification(){
        String phone = binding.edPhone.getText().toString();
        String introductionNumber ="+" +prefix;
        loaderDialog();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                introductionNumber+phone,
                60,
                TimeUnit.SECONDS,
                ForgetPasswordActivity.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        loader_dialog.dismiss();
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        loader_dialog.dismiss();
                        Log.e("FirebaseException",e.toString());
                    }

                    @Override
                    public void onCodeSent(@NonNull String mVerificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(mVerificationId, forceResendingToken);
                        loader_dialog.dismiss();
                        removeFirstCharFromPhoneNumber();

                        Intent intent=new Intent(ForgetPasswordActivity.this, VerificationActivity.class);
                        intent.putExtra(Const.VERIFICATION_ID,mVerificationId);
                        intent.putExtra(Const.MOBILE,prefix +number);
                        Log.e("mVerificationId",mVerificationId);
                        startActivity(intent);
                    }
                }
        );
    }

    protected void loaderDialog() {
        loader_dialog = new Dialog(this);
        loader_dialog.setContentView(R.layout.loader_dialog);
        loader_dialog.setCancelable(false);
        loader_dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER);
        loader_dialog.getWindow().setBackgroundDrawableResource(R.drawable.transparent);
        loader_dialog.show();
    }

    private void removeFirstCharFromPhoneNumber(){
        String phone = binding.edPhone.getText().toString();
        String phone2 = binding.edPhone.getText().toString();
        if (!phone2.equals("")&&!phone.equals("")){
            String first_char = String.valueOf(phone2.charAt(0));
            if (first_char.equals("0")){
                number = phone.substring(1, phone.length());
            }else{
                number = binding.edPhone.getText().toString();
            }}
    }

}