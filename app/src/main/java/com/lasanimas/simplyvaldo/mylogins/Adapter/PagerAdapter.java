package com.example.simplyvaldo.mylogins.Adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.simplyvaldo.mylogins.View.Fragments.categories;
import com.example.simplyvaldo.mylogins.View.Fragments.logins;

public class PagerAdapter extends FragmentStatePagerAdapter
{
    private String[] tabTitles = new String[]{"Logins", "Categories"};
    private int NumberOfTabs;
    private Bundle fragmentBundle;

    public PagerAdapter(FragmentManager fm, int NumOfTabs, Bundle data) {
        super(fm);
        this.NumberOfTabs = NumOfTabs;
        this.fragmentBundle = data;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                logins tab1 = new logins();
                tab1.setArguments(fragmentBundle);
                return tab1;
            case 1:
                categories tab2 = new categories();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return NumberOfTabs;
    }
}
