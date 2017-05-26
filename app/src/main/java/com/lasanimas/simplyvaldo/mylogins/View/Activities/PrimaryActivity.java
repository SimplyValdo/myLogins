package com.example.simplyvaldo.mylogins.View.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.simplyvaldo.mylogins.Interfaces.FragmentListener;
import com.example.simplyvaldo.mylogins.R;
import com.example.simplyvaldo.mylogins.View.Fragments.favorites;
import com.example.simplyvaldo.mylogins.View.Fragments.profiles;
import com.example.simplyvaldo.mylogins.View.Fragments.settings;
import com.example.simplyvaldo.mylogins.View.Fragments.tabLayout;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrimaryActivity extends AppCompatActivity implements FragmentListener
{
    private String currentID;

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

        commitFragmentLayout(new profiles(),"profilesTag");

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch(tabId)
                {
                    case R.id.tab_profiles:
                        fragmentTransaction.replace(R.id.Fragment, new profiles(), "profilesTag");
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                    case R.id.tab_Favorites:
                        fragmentTransaction.replace(R.id.Fragment, new favorites(), "favoritesTag");
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                    case R.id.tab_settings:
                        fragmentTransaction.replace(R.id.Fragment, new settings(), "settingsTag");
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                }
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch(tabId)
                {
                    case R.id.tab_profiles:
                        fragmentTransaction.replace(R.id.Fragment, new profiles(), "profilesTag");
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                    case R.id.tab_Favorites:
                        break;
                    case R.id.tab_settings:
                        break;
                }
            }
        });
    }

    public static Context getContext() {
        return mContext;
    }


    @Override
    public void SendProfileName(String id, String name) {

        currentID = id;
        Bundle args = new Bundle();
        args.putString("id", id);
        args.putString("profileName", name);
        tabLayout fragment = new tabLayout();
        fragment.setArguments(args);
        commitFragmentLayout(fragment, "currentLoginTag");
    }

    public void commitFragmentLayout(Fragment fragment, String tag)
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.Fragment, fragment, tag);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
