package com.user.order.ui.activivtes.orders;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.user.order.R;
import com.user.order.databinding.ActivityPublicPaymentBinding;
import com.user.order.model.contact.ContactUs;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicPaymentActivity extends AppCompatActivity {

    ActivityPublicPaymentBinding binding;
    @OnClick(R.id.iv_back)
    void onBackClick() {
        onBackPressed();
    }
    private String orderId;
    public Dialog loader_dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPublicPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ButterKnife.bind(this);

        if (getIntent() != null)
            orderId = getIntent().getStringExtra(Const.KEY_ORDER);

        binding.btnConfirm.setOnClickListener(View->{payment();});
    }

    public void payment(){

        loaderDialog();
        HelperMethods.get1OrderAPI().clientConfirmPayment(HelperMethods.getUserToken(this), orderId, "8aar2352356316345eafadsgarher","on_delivery").enqueue(new Callback<ContactUs>() {
            @Override
            public void onResponse(Call<ContactUs> call, Response<ContactUs> response) {
                if (response.isSuccessful()){
                    loader_dialog.dismiss();
                    Toast.makeText(PublicPaymentActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    PreferencesManager.setStringPreferences("public_order","public_order");
                    finish();
                }else {
                    loader_dialog.dismiss();

                }

            }

            @Override
            public void onFailure(Call<ContactUs> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
                loader_dialog.dismiss();

            }
        });
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
