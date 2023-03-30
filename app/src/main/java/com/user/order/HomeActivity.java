package com.user.order;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.user.order.model.country.Country;
import com.user.order.model.settings.AppContent;
import com.user.order.model.settings.Settings;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = HomeActivity.class.getSimpleName();

    @BindView(R.id.btn_navigation)
    BottomNavigationView btnNavigation;

    private AppBarConfiguration appBarConfiguration;
    public NavController navController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initNavigation();
        Log.d(TAG, "onCreate TOKEN: " + HelperMethods.getUserToken(this));
        String country_id = String.valueOf(HelperMethods.getCountrySettings(this).getId());

        getSettings(Integer.parseInt(country_id));
    }

    private void initNavigation() {

        // TODO BottomNavigationView

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_orders,
                R.id.nav_favorite, R.id.nav_notifications,
                R.id.nav_settings
        ).build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragments);
        NavigationUI.setupWithNavController(btnNavigation, navController);


        btnNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    navController.navigate(R.id.nav_home);
                    break;
                case R.id.nav_orders:
                    navController.navigate(R.id.nav_orders);
                    break;
                case R.id.nav_favorite:
                    navController.navigate(R.id.nav_favorite);
                    break;
                case R.id.nav_notifications:
                    navController.navigate(R.id.nav_notifications);
                    break;
                case R.id.nav_settings:
                    navController.navigate(R.id.nav_settings);
                    break;
            }
            return false;
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        String country_id = String.valueOf(HelperMethods.getCountrySettings(this).getId());
        getSettings(Integer.parseInt(country_id));

    }

    private void getSettings(int countryId) {

        HelperMethods.get1OrderAPI()
                .getSettings(HelperMethods.getAppLanguage(this), countryId)
                .enqueue(new Callback<Settings>() {
                    @Override
                    public void onResponse(Call<Settings> call, Response<Settings> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getSuccess().equals("success")) {
                                Log.d(TAG, "onResponse: " + response.body().getData().getCountry().getName());
                                Country country = response.body().getData().getCountry();
                                AppContent appContent = response.body().getData().getAppContent();
                                if (PreferencesManager.getCountrySettings(HomeActivity.this, Const.KEY_SETTINGS) == null)
                                    PreferencesManager.saveCountrySettings(HomeActivity.this, Const.KEY_SETTINGS, country);

                                if (PreferencesManager.getAppContent(HomeActivity.this, Const.KEY_APP_CONTENT) == null)
                                    PreferencesManager.saveAppContent(HomeActivity.this, Const.KEY_APP_CONTENT, appContent);

                                PreferencesManager.setStringPreferences(Const.KEY_TAX, response.body().getData().getCountry().getSetting().getTax());

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Settings> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }

}
