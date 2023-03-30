package com.user.order.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.user.order.ui.fragments.order.OneOrderFragment;
import com.user.order.ui.fragments.order.PublicOrderFragment;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
            fragment = new OneOrderFragment();
        else if (position == 1)
            fragment = new PublicOrderFragment();

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;

        if (position == 0)
            title = "1Order";
        else if (position == 1)
            title = "Public Order";


        return title;
    }

}
