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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.user.order.R;
import com.user.order.databinding.ActivityResetPasswordBinding;
import com.user.order.model.auth.ResetPassword;
import com.user.order.model.reset.ResetPasswordRequest;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;

import retrofit2.Call;
import retrofit2.Callback;

public class ResetPasswordActivity extends AppCompatActivity {
    private static final String TAG = ResetPasswordActivity.class.getSimpleName();
    ActivityResetPasswordBinding binding;
    Dialog loader_dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        openActivities();

    }

    private void openActivities() {
        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                String newPassword = binding.newPassword.getText().toString().trim();
                String confirmPassword = binding.confirmPassword.getText().toString().trim();
                if (newPassword.equals("")) {
                    binding.newPassword.setError(getText(R.string.please_enter_password));
                } else if (confirmPassword.equals("")) {
                    binding.confirmPassword.setError(getText(R.string.please_enter_confirm_password));
                } else if (binding.newPassword.getText().toString().length() <= 6) {
                    binding.newPassword.setError(getText(R.string.password_must_be_6_chat));
                } else if (!(binding.newPassword.getText().toString().equals(binding.confirmPassword.getText().toString()))) {
                    binding.newPassword.setError(getText(R.string.two_password_not_match));
                } else {
                    apiReset();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void apiReset() {
        String password_str = binding.newPassword.getText().toString();
        String new_password_str = binding.confirmPassword.getText().toString();
        String phone = getIntent().getStringExtra(Const.MOBILE);
        if (!new_password_str.isEmpty() && !password_str.isEmpty()) {
            loaderDialog();
            ResetPasswordRequest user = new ResetPasswordRequest(password_str, new_password_str, phone);
            HelperMethods.get1OrderAPI().resetPassword(user, HelperMethods.getAppLanguage(this)).enqueue(new Callback<ResetPassword>() {
                @Override
                public void onResponse(Call<ResetPassword> call, retrofit2.Response<ResetPassword> response) {

                    Log.e("code", String.valueOf(response.code()));
                    if (response.isSuccessful()) {
                        loader_dialog.dismiss();
                        if (response.body().getData() != null) {
                            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(), response.body().getMessage() + "", Toast.LENGTH_LONG).show();
                            Log.e("Success", new Gson().toJson(response.body()));
                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMessage() + "", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        loader_dialog.dismiss();
                        Log.e("errorMessage", response.message() + "");

                    }
                }

                @Override
                public void onFailure(Call<ResetPassword> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(getBaseContext(), t.getMessage() + "", Toast.LENGTH_SHORT).show();
                    call.cancel();
                    loader_dialog.dismiss();
                }
            });

        } else {
            loader_dialog.dismiss();
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
}