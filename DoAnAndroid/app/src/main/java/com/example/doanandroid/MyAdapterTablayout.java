package com.example.doanandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.doanandroid.Frament.QuanJeanFragment;

public class MyAdapterTablayout extends FragmentStatePagerAdapter {
    private String listTab[] = {"Quần Jean","Áo Sơ Mi"};
    private QuanJeanFragment quanJeanFragment;
    public MyAdapterTablayout(FragmentManager fm) {
        super(fm);
        quanJeanFragment = new QuanJeanFragment();

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

            if(position==1)
                return quanJeanFragment;
            else
            {
                //TODO nothing
            }
            return null;

    }

    @Override
    public int getCount() {
        return listTab.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}
