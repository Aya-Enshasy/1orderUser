package com.user.order.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.user.order.R;
import com.user.order.listeners.ItemClickListener;
import com.user.order.listeners.SelectLanguageListener;
import com.user.order.model.Language;
import com.user.order.utils.HelperMethods;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LanguagesAdapter extends RecyclerView.Adapter<LanguagesAdapter.LanguagesHolder> {
    private static final String TAG = LanguagesAdapter.class.getSimpleName();

    private Activity activity;
    private List<Language> listLanguages;
    private LayoutInflater inflater;
    private RelativeLayout itemSelected = null;
    private SelectLanguageListener selectLanguageListener;

    public LanguagesAdapter(Activity activity, List<Language> listLanguages, SelectLanguageListener selectLanguageListener) {
        this.activity = activity;
        this.listLanguages = listLanguages;
        this.selectLanguageListener = selectLanguageListener;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public LanguagesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LanguagesHolder(inflater.inflate(R.layout.row_language, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LanguagesHolder holder, int position) {

        Glide.with(activity).load(listLanguages.get(position).getFlag()).placeholder(R.drawable.app_logo).into(holder.ivFlag);
        holder.tvLanguageName.setText(listLanguages.get(position).getName());
        checkLanguage(listLanguages.get(position), holder);

        holder.setItemClickListener((view, pos) -> {
            if(itemSelected != null)
                itemSelected.setBackgroundResource(R.drawable.bg_language_unselected);

            holder.relLanguage.setBackgroundResource(R.drawable.bg_language_selected);
            itemSelected = holder.relLanguage;
            selectLanguageListener.onSelectLanguageListener(listLanguages.get(position), position);
        });
    }

    @Override
    public int getItemCount() {
        return listLanguages.size();
    }

    private void checkLanguage(Language language, LanguagesHolder holder) {
        if (HelperMethods.getAppLanguage(activity).equals(language.getLangCode())) {
            Log.d(TAG, "checkLanguage AR: " + HelperMethods.getAppLanguage(activity));
            if(itemSelected != null)
                itemSelected.setBackgroundResource(R.drawable.bg_language_unselected);
            holder.relLanguage.setBackgroundResource(R.drawable.bg_language_selected);
            itemSelected = holder.relLanguage;
        }else if (HelperMethods.getAppLanguage(activity).equals(language.getLangCode())){
            Log.d(TAG, "checkLanguage EN: " + HelperMethods.getAppLanguage(activity));
            if(itemSelected != null)
                itemSelected.setBackgroundResource(R.drawable.bg_language_unselected);
            holder.relLanguage.setBackgroundResource(R.drawable.bg_language_selected);
            itemSelected = holder.relLanguage;
        }


    }

    static class LanguagesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.rel_language)
        RelativeLayout relLanguage;
        @BindView(R.id.iv_flag)
        ImageView ivFlag;
        @BindView(R.id.tv_language_name)
        TextView tvLanguageName;

        private ItemClickListener itemClickListener;

        public LanguagesHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

}
