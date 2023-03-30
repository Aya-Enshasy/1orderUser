package com.user.order.ui.activivtes.settings;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.user.order.R;
import com.user.order.model.contact.ContactUs;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsActivity extends AppCompatActivity {
    public Dialog loader_dialog;
    @BindView(R.id.input_full_name)
    EditText inputFullName;
    @BindView(R.id.input_phone)
    EditText inputPhone;
    @BindView(R.id.input_title)
    EditText inputTitle;
    @BindView(R.id.input_message)
    EditText inputMessage;
    @BindView(R.id.tv_text_name)
    TextView name;
    @BindView(R.id.tv_text_phone)
    TextView phone;
    @BindView(R.id.send_btn)
    Button send_btn;
    @BindView(R.id.card_facebook)
    ConstraintLayout cardFacebook;
    @BindView(R.id.card_wahtsapp)
    ConstraintLayout cardWhatsApp;
    @BindView(R.id.card_twitter)
    ConstraintLayout cardTwitter;
    @BindView(R.id.card_instagram)
    ConstraintLayout cardInstagram;

    @OnClick(R.id.iv_back)
    void onBackClick() {
        onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        ButterKnife.bind(this);

        contactUsBtn();
        checkIfTokenNotNull();
        socialMedia();

    }

    private void checkIfTokenNotNull(){
        if (HelperMethods.getUserToken(this)!=null){
            inputFullName.setVisibility(View.GONE);
            inputPhone.setVisibility(View.GONE);
            name.setVisibility(View.GONE);
            phone.setVisibility(View.GONE);
        }

    }

    private void contactUsBtn() {
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputFullName.getText().toString().equals("")&&HelperMethods.getUserToken(ContactUsActivity.this).equals(""))
                    inputFullName.setError(getString(R.string.please_enter_name));
                else if (inputPhone.getText().toString().equals("")&&HelperMethods.getUserToken(ContactUsActivity.this).equals(""))
                    inputPhone.setError(getString(R.string.please_enter_phone));
               if (inputTitle.getText().toString().equals(""))
                    inputTitle.setError(getString(R.string.please_enter_title));
                else if (inputMessage.getText().toString().equals(""))
                    inputMessage.setError(getString(R.string.please_enter_message));
                else
                getContactUsApi();
            }
        });
    }

    private void getContactUsApi() {
        loaderDialog();
        if (HelperMethods.getUserToken(this)==null){
            HelperMethods.get1OrderAPI()
                    .getContact(HelperMethods.getAppLanguage(this),
                            "",
                            String.valueOf(HelperMethods.getCountrySettings(this).getId()),
                            inputFullName.getText().toString(),
                            inputMessage.getText().toString(),
                            inputTitle.getText().toString(),
                            inputPhone.getText().toString(),
                            ""
                    )
                    .enqueue(new Callback<ContactUs>() {
                        @Override
                        public void onResponse(Call<ContactUs> call, Response<ContactUs> response) {
                            if (response.isSuccessful()) {
                                loader_dialog.dismiss();
                                finish();
                                Toast.makeText(ContactUsActivity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }else {
                                loader_dialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<ContactUs> call, Throwable t) {
                            loader_dialog.dismiss();

                        }
                    });
        }
        else {
            HelperMethods.get1OrderAPI()
                    .getContact(HelperMethods.getAppLanguage(this),
                            HelperMethods.getUserToken(this),
                            String.valueOf(HelperMethods.getCountrySettings(this).getId()),
                            HelperMethods.getCurrentUser(this).getName(),
                            inputMessage.getText().toString(),
                            inputTitle.getText().toString(),
                            HelperMethods.getCurrentUser(this).getMobile(),
                            String.valueOf(HelperMethods.getCurrentUser(this).getId())

                    )
                    .enqueue(new Callback<ContactUs>() {
                        @Override
                        public void onResponse(Call<ContactUs> call, Response<ContactUs> response) {
                            if (response.isSuccessful()) {
                                loader_dialog.dismiss();
                                finish();
                                Toast.makeText(ContactUsActivity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }else {
                                loader_dialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<ContactUs> call, Throwable t) {
                            loader_dialog.dismiss();

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

    private void socialMedia(){
        cardTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String twitter =HelperMethods.getAppContent(ContactUsActivity.this).getTwitterLink();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(twitter));
                startActivity(i);
            }
        });

        cardInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telegram =HelperMethods.getAppContent(ContactUsActivity.this).getInstagramLink();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(telegram));
                startActivity(i);
            }
        });

        cardFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String facebook = HelperMethods.getAppContent(ContactUsActivity.this).getFacebookLink();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(facebook));
                startActivity(i);
            }
        });

        cardWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String whatsApp = PreferencesManager.getStringPreferences(Const.KEY_WHATSAPP);
                String url = "https://wa.me/"+whatsApp;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });
    }
}
