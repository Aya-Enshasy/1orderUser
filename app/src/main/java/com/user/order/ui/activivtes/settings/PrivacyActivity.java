package com.user.order.ui.activivtes.settings;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.user.order.R;
import com.user.order.model.settings.Settings;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrivacyActivity extends AppCompatActivity {

    @BindView(R.id.card_privacy)
    CardView cardPrivacy;
    @BindView(R.id.tv_privacy)
    TextView tvPrivacy;
    @BindView(R.id.tv_text_title)
    TextView tvTextTitle;

    @OnClick(R.id.iv_back)
    void onBackClick(){
        onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        ButterKnife.bind(this);

        cardPrivacy.setBackgroundResource(R.drawable.bg_card);

        String title =  PreferencesManager.getStringPreferences(Const.SETTINGS_PRIVACY_TITLE);
        String body =  PreferencesManager.getStringPreferences(Const.SETTINGS_PRIVACY_BODY);
        tvTextTitle.setText(title);
        tvPrivacy.setText(body);

        if (HelperMethods.getUserToken(this)==null||tvPrivacy.getText().toString().equals(""))getSettingsApi();

    }

    protected void getSettingsApi() {

        HelperMethods.get1OrderAPI().getSettings(HelperMethods.getAppLanguage(this),1).enqueue(new Callback<Settings>() {
            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {

                if (response.isSuccessful()) {
                    if (response.body().getData()!=null){
                        tvTextTitle.setText(response.body().getData().getAppContent().getDriverPrivacyTitle());
                        tvPrivacy.setText(response.body().getData().getAppContent().getDriverPrivacyContent());
                        Log.e("Message", response.code() + "");}
                } else {

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
