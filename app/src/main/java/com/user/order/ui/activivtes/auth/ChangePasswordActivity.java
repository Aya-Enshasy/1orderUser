package com.user.order.ui.activivtes.auth;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.user.order.R;
import com.user.order.model.changePassword.ChangePassowrd;
import com.user.order.utils.HelperMethods;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ChangePasswordActivity.class.getSimpleName();
    public Dialog loader_dialog;

    @BindView(R.id.rel_container)
    RelativeLayout container;
    @BindView(R.id.input_old_password)
    EditText inputOldPassword;
    @BindView(R.id.iv_show_old_password)
    ImageView ivShowOldPassword;
    @BindView(R.id.input_new_password)
    EditText inputNewPassword;
    @BindView(R.id.iv_show_new_password)
    ImageView ivShowNewPassword;
    @BindView(R.id.input_confirm_password)
    EditText inputConfirmPassword;
    @BindView(R.id.iv_show_confirm_password)
    ImageView ivShowConfirmPassword;

    private boolean isShowOldPassword = false;
    private boolean isShowNewPassword = false;
    private boolean isShowConfirmPassword = false;

    @OnClick(R.id.iv_back)
    void onBackClick(){
        onBackPressed();
    }

    @OnClick(R.id.btn_send)
    void onChangePasswordClick() {

        String oldPassword = inputOldPassword.getText().toString();
        String newPassword = inputNewPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();

        if (!HelperMethods.isInternetConnected(this)) {
            HelperMethods.showCustomSnackBar(this, container, getString(R.string.check_internet),
                    false, Snackbar.LENGTH_SHORT, this);
            loader_dialog.dismiss();
            return;
        }

        if (!HelperMethods.validateOldPassword(this, oldPassword, inputOldPassword) |
                !HelperMethods.validatePassword(this, newPassword, getString(R.string.please_new_password), inputNewPassword) |
                !HelperMethods.validateConfirmPassword(this, newPassword, confirmPassword, inputConfirmPassword)) {
            loader_dialog.dismiss();
            return;
        }

        changePassword(oldPassword, newPassword, confirmPassword);
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        initUI();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initUI() {

        ivShowOldPassword.setOnTouchListener((v, event) -> {

            isShowOldPassword = !isShowOldPassword;
            if (isShowOldPassword) {
                Glide.with(this).load(R.drawable.ic_icon_show).into(ivShowOldPassword);
                inputOldPassword.setInputType(InputType.TYPE_CLASS_TEXT);
            } else {
                Glide.with(this).load(R.drawable.ic_icon_hide).into(ivShowOldPassword);
                inputOldPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            return false;
        });

        ivShowNewPassword.setOnTouchListener((v, event) -> {

            isShowNewPassword = !isShowNewPassword;
            if (isShowNewPassword) {
                Glide.with(this).load(R.drawable.ic_icon_show).into(ivShowNewPassword);
                inputNewPassword.setInputType(InputType.TYPE_CLASS_TEXT);
            } else {
                Glide.with(this).load(R.drawable.ic_icon_hide).into(ivShowNewPassword);
                inputNewPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            return false;
        });

        ivShowConfirmPassword.setOnTouchListener((v, event) -> {

            isShowConfirmPassword = !isShowConfirmPassword;
            if (isShowConfirmPassword) {
                Glide.with(this).load(R.drawable.ic_icon_show).into(ivShowConfirmPassword);
                inputConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT);
            } else {
                Glide.with(this).load(R.drawable.ic_icon_hide).into(ivShowConfirmPassword);
                inputConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            return false;
        });
    }

    private void changePassword(String oldPassword, String newPassword, String confirmPassword) {

loaderDialog();
HelperMethods.get1OrderAPI()
                .changePassword(
                        HelperMethods.getAppLanguage(this),
                        HelperMethods.getUserToken(this),
                        oldPassword,
                        newPassword,
                        confirmPassword)
                .enqueue(new Callback<ChangePassowrd>() {
                    @Override
                    public void onResponse(Call<ChangePassowrd> call, Response<ChangePassowrd> response) {
                        loader_dialog.dismiss();
                        if(response.isSuccessful()){
                            if (response.body().getSuccess().equals("success")){
                                HelperMethods.showCustomToast(ChangePasswordActivity.this, getString(R.string.change_password_success), true);
                                finish();
                            }else {
                                HelperMethods.showCustomToast(ChangePasswordActivity.this, response.body().getMessage(), false);
                            }

                        }else{
                        }
                    }

                    @Override
                    public void onFailure(Call<ChangePassowrd> call, Throwable t) {
                        loader_dialog.dismiss();
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });

    }

    @Override
    public void onClick(View v) {
        onChangePasswordClick();
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
