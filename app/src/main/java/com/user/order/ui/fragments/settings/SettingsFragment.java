package com.user.order.ui.fragments.settings;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.user.order.HomeActivity;
import com.user.order.R;
import com.user.order.adapter.LanguagesAdapter;
import com.user.order.databinding.FragmentFavoriteBinding;
import com.user.order.databinding.FragmentSettingsBinding;
import com.user.order.listeners.SelectLanguageListener;
import com.user.order.model.Language;
import com.user.order.model.User;
import com.user.order.model.profile.Profile;
import com.user.order.ui.activivtes.auth.ChangePasswordActivity;
import com.user.order.ui.activivtes.auth.LoginActivity;
import com.user.order.ui.activivtes.profile.ProfileActivity;
import com.user.order.ui.activivtes.settings.ContactUsActivity;
import com.user.order.ui.activivtes.settings.PrivacyActivity;
import com.user.order.utils.Const;
import com.user.order.utils.HelperMethods;
import com.user.order.utils.PreferencesManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragment extends Fragment implements SelectLanguageListener {

    public Dialog logoutDialog,languageDialog,deleteAccountDialog;
    FragmentSettingsBinding binding;
    private String languageCode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        initUI();

        return view;
    }

    private void initUI() {

        getUser();
        clicksListeners();
    }

    private void getUser() {
        if(HelperMethods.getUserToken(getActivity()) != null){
            binding.tvLogout.setText(getString(R.string.logout));
            Glide.with(getActivity()).load(PreferencesManager.getStringPreferences(Const.KEY_USER_UEL)).placeholder(R.drawable.ic_user_defult).into(binding.civUser);
            if (!HelperMethods.isInternetConnected(getActivity())) {
                setUserInfo(HelperMethods.getCurrentUser(getActivity()));
            } else
                loadUserInfo();
        }else {
            binding.tvLogout.setText(getString(R.string.login));
            Log.d("TAG", "getUser: " + Const.DEFAULT_USER_AVATAR);
            binding.tvFullName.setText(getString(R.string.welcome_to)+getString(R.string.on_order));
            Glide.with(getActivity()).load(R.drawable.app_logo).into(binding.civUser);
            binding.relProfile.setVisibility(View.GONE);
            binding.relChangePassword.setVisibility(View.GONE);
            binding.relDeleteAccount.setVisibility(View.GONE);

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getUser();
        binding.tvFullName.setText(PreferencesManager.getStringPreferences(Const.KEY_USER_NAME));
        Glide.with(this).load(PreferencesManager.getStringPreferences(Const.KEY_USER_UEL)).placeholder(R.drawable.ic_user_defult).into(binding.civUser);
    }

    private void setUserInfo(User currentUser) {

        binding.tvFullName.setText(currentUser.getName());
//        if(currentUser.getAvatarUrl() != null)
//            Glide.with(getActivity()).load(currentUser.getAvatarUrl()).into(civUser);
    }

    private void loadUserInfo() {
        HelperMethods.get1OrderAPI()
                .getUserProfile(HelperMethods.getAppLanguage(getActivity()),
                        HelperMethods.getUserToken(getActivity()))
                .enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getSuccess().equals("success")) {
                                User currentUser = response.body().getData().getUser();
                                binding.tvFullName.setText(currentUser.getName());
                                PreferencesManager.setStringPreferences(Const.KEY_USER_CITY_ID,response.body().getData().getUser().getCity().getId()+"");
                                PreferencesManager.setStringPreferences(Const.KEY_USER_NAME,response.body().getData().getUser().getName());
                                PreferencesManager.setStringPreferences(Const.KEY_USER_ADDRESS,response.body().getData().getUser().getAddress());
                                PreferencesManager.setStringPreferences(Const.KEY_USER_Email,response.body().getData().getUser().getEmail());
                                PreferencesManager.setStringPreferences(Const.KEY_USER_UEL,response.body().getData().getUser().getAvatarUrl());
                                PreferencesManager.setStringPreferences(Const.KEY_USER_COUNTRY_ID,response.body().getData().getUser().getCountryId()+"");

                            } else {
                                HelperMethods.showCustomToast(getActivity(), response.body().getMessage(), false);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {
                        Log.e("TAG", "onFailure:  " + t.getMessage());
                    }
                });
    }

    private void showLogoutDialog() {

        logoutDialog = new Dialog(getActivity());
        logoutDialog.setContentView(R.layout.dialog_logout);
        logoutDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        logoutDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
        logoutDialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        TextView tvTextLogout = logoutDialog.findViewById(R.id.tv_text_logout);
        Button btnLogout = logoutDialog.findViewById(R.id.btn_logout);

        if(HelperMethods.getUserToken(getActivity()) != null){
            tvTextLogout.setText(getString(R.string.sure_log_out));
            btnLogout.setText(getString(R.string.logout));
        }

        logoutDialog.findViewById(R.id.btn_cancel)
                .setOnClickListener(view -> logoutDialog.dismiss());

        logoutDialog.findViewById(R.id.btn_logout)
                .setOnClickListener(v -> {
                    logoutDialog.dismiss();
                    if(HelperMethods.getUserToken(getActivity()) != null){
                        PreferencesManager.clear(getActivity(), Const.KEY_USER_TOKEN);
                        PreferencesManager.clear(getActivity(), Const.KEY_USER_DATA);
                        PreferencesManager.clear(getActivity(), Const.KEY_DELIVERY_ADDRESS);
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                    }else{
                        PreferencesManager.clear(getActivity(), Const.KEY_CURRENT_LATITUDE);
                        PreferencesManager.clear(getActivity(), Const.KEY_CURRENT_LONGITUDE);
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                    }


                });

        logoutDialog.show();
    }

    private void showDeleteDialog() {
        deleteAccountDialog = new Dialog(getActivity());
        deleteAccountDialog.setContentView(R.layout.dialog_delete_account);
        deleteAccountDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        deleteAccountDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
        deleteAccountDialog.getWindow().getAttributes().windowAnimations = R.style.animation;

//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        View viewDialog = getLayoutInflater().inflate(R.layout.dialog_delete_account, null);
//        builder.setView(viewDialog);
//        AlertDialog dialog = builder.create();
//        HelperMethods.showCustomDialog(dialog);

        deleteAccountDialog.findViewById(R.id.btn_cancel)
                .setOnClickListener(view -> deleteAccountDialog.dismiss());

        deleteAccountDialog.findViewById(R.id.btn_delete_account)
                .setOnClickListener(v -> {
                });

        deleteAccountDialog.show();
    }

    private void showLanguageDialog() {
        languageDialog = new Dialog(getActivity());
        languageDialog.setContentView(R.layout.dialog_languages);
        languageDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        languageDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
        languageDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        RecyclerView recyclerLanguages = languageDialog.findViewById(R.id.recycler_languages);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerLanguages.setHasFixedSize(true);
        recyclerLanguages.setLayoutManager(layoutManager);

        List<Language> listLanguages = new ArrayList<>();
        listLanguages.add(new Language(getString(R.string.english), "en", R.drawable.ic_icon_english));
        listLanguages.add(new Language(getString(R.string.arabic), "ar", R.drawable.ic_icon_arabic));

        LanguagesAdapter languagesAdapter = new LanguagesAdapter(getActivity(), listLanguages, this);
        recyclerLanguages.setAdapter(languagesAdapter);




        languageDialog.findViewById(R.id.btn_select_language)
                .setOnClickListener(v -> {
                    Log.d("TAG", "showLanguageDialog: " + languageCode);
                    languageDialog.dismiss();
                    saveLanguage(languageCode);
                });

        languageDialog.show();

    }

    @Override
    public void onSelectLanguageListener(Language language, int pos) {
        languageCode = language.getLangCode();
        Log.d("TAG", "onSelectLanguageListener: " + language.getLangCode());
    }

    private void saveLanguage(String languageCode) {
        Log.d("TAG", "saveLanguage: " + languageCode);
        PreferencesManager.saveLanguage(getActivity(), languageCode);
        HelperMethods.saveAppLanguage(getActivity(), languageCode);
        startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();
    }

    private void clicksListeners(){
        if(HelperMethods.getUserToken(getActivity()) != null){
            binding.relLogout.setOnClickListener(View->{showLogoutDialog();});
        }else {
            binding.relLogout.setOnClickListener(View->{startActivity(new Intent(getActivity(), LoginActivity.class));});

        }
        binding.relProfile.setOnClickListener(View->{startActivity(new Intent(getActivity(), ProfileActivity.class));});
        binding.relChangePassword.setOnClickListener(View->{startActivity(new Intent(getActivity(), ChangePasswordActivity.class));});
        binding.relPrivacy.setOnClickListener(View->{startActivity(new Intent(getActivity(), PrivacyActivity.class));});
        binding.relContactUs.setOnClickListener(View->{startActivity(new Intent(getActivity(), ContactUsActivity.class));});
        binding.relLanguage.setOnClickListener(View->{showLanguageDialog();});
        binding.relDeleteAccount.setOnClickListener(View->{showDeleteDialog();});
    }


}
