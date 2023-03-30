package com.user.order.ui.activivtes.auth;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.user.order.R;
import com.user.order.databinding.ActivityVerificationBinding;
import com.user.order.utils.Const;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

public class VerificationActivity extends AppCompatActivity {
    private static String TAG = VerificationActivity.class.getSimpleName();

    ActivityVerificationBinding binding;
    String verificationID;
    public FirebaseAuth mAuth;
    public Dialog loader_dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        openActivities();
        verificationID = getIntent().getStringExtra(Const.VERIFICATION_ID);
        resendVerifyButton();
        timer();
    }

    private void openActivities(){
        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                if (binding.firstPinView.getText().toString().equals("")) {
                    Toast.makeText(VerificationActivity.this, "Code is empty", Toast.LENGTH_SHORT).show();
                } else {
                    verificationCode();
                }}
        });

        binding.edResendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                resetCode();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void resendVerifyButton(){

        binding.edResendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                resetCode();
            }
        });
    }

    private void resetCode(){
        loaderDialog();
        Log.e("MOBILE",getIntent().getStringExtra(Const.MOBILE));
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                getIntent().getStringExtra(Const.MOBILE),
                60,
                TimeUnit.SECONDS,
                VerificationActivity.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        loader_dialog.dismiss();
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        loader_dialog.dismiss();
                    }

                    @Override
                    public void onCodeSent(@NonNull String newVerificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(newVerificationId, forceResendingToken);
                        verificationID = newVerificationId;
                        Toast.makeText(VerificationActivity.this, "OPT Sent", Toast.LENGTH_SHORT).show();
                        loader_dialog.dismiss();

                        timer();

                    }
                }
        );
    }

    private void verificationCode(){
        if (binding.firstPinView.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Code is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        String code = binding.firstPinView.getText().toString();
        if (verificationID != null){
            loaderDialog();
            PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                    verificationID,
                    code
            );
            FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            loader_dialog.dismiss();
                            if (task.isSuccessful()){
                                Intent intent = new Intent(VerificationActivity.this, ResetPasswordActivity.class);
                                intent.putExtra(Const.MOBILE,getIntent().getStringExtra(Const.MOBILE));
                                startActivity(intent);
                                finish();

                            }else {
                                Toast.makeText(VerificationActivity.this, "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }


    private void timer(){
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                binding.edResendCode.setVisibility(View.GONE);
                NumberFormat numberFormat = new DecimalFormat("00");
                long min = (millisUntilFinished/60000) % 60;
                long sec = (millisUntilFinished/1000) % 60;
                binding.timer.setText(numberFormat.format(min)+" : "+ numberFormat.format(sec));
            }

            public void onFinish() {
                binding.edResendCode.setVisibility(View.VISIBLE);
            }
        }.start();
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