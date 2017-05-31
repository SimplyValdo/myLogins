package com.lasanimas.simplyvaldo.mylogins.Adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.lasanimas.simplyvaldo.mylogins.View.Fragments.categories;
import com.lasanimas.simplyvaldo.mylogins.View.Fragments.logins;
import com.lasanimas.simplyvaldo.mylogins.View.Fragments.viewLogin;

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

                if(fragmentBundle.getString("LayoutLogins").equals("viewLoginDetails"))
                {
                    Log.i("Called", "From Logins");
                    viewLogin tab0 = new viewLogin();
                    tab0.setArguments(fragmentBundle);
                    return tab0;
                }
                else
                {
                    logins tab0 = new logins();
                    tab0.setArguments(fragmentBundle);
                    return tab0;
                }

            case 1:
                if(fragmentBundle.getString("LayoutLogins2").equals("viewLoginDetails2"))
                {
                    viewLogin tab0 = new viewLogin();
                    tab0.setArguments(fragmentBundle);
                    return tab0;
                }
                else
                {
                    categories tab0 = new categories();
                    tab0.setArguments(fragmentBundle);
                    return tab0;
                }
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
