package com.example.simplyvaldo.mylogins.View.Activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.simplyvaldo.mylogins.R;
import com.example.simplyvaldo.mylogins.View.Fragments.logins;
import com.example.simplyvaldo.mylogins.View.Fragments.profiles;
import com.example.simplyvaldo.mylogins.View.Fragments.settings;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.TabSelectionInterceptor;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrimaryActivity extends AppCompatActivity
{
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);
        mContext = getBaseContext();
        ButterKnife.bind(this);

        bottomBar.setTabSelectionInterceptor(new TabSelectionInterceptor() {
            @Override
            public boolean shouldInterceptTabSelection(@IdRes int oldTabId, @IdRes int newTabId) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch(newTabId)
                {
                    case R.id.tab_profiles:
                        bottomBar.selectTabAtPosition(0);
                        fragmentTransaction.replace(R.id.Fragment, new profiles());
                        fragmentTransaction.commit();
                        break;
                    case R.id.tab_logins:
                        bottomBar.selectTabAtPosition(1);
                        fragmentTransaction.replace(R.id.Fragment, new logins());
                        fragmentTransaction.commit();
                        break;
                    case R.id.tab_settings:
                        bottomBar.selectTabAtPosition(2);
                        fragmentTransaction.replace(R.id.Fragment, new settings());
                        fragmentTransaction.commit();
                        break;

                }

                return true;
            }
        });
    }

    public static Context getContext() {
        return mContext;
    }
}
