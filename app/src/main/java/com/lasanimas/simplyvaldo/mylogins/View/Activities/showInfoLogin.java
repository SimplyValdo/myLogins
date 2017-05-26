package com.example.simplyvaldo.mylogins.View.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import com.example.simplyvaldo.mylogins.R;
import com.example.simplyvaldo.mylogins.View.Fragments.logins;
import com.example.simplyvaldo.mylogins.View.Fragments.settings;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.TabSelectionInterceptor;

import butterknife.BindView;
import butterknife.ButterKnife;


public class showInfoLogin extends AppCompatActivity
{
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_info_login);
        ButterKnife.bind(this);

        bottomBar.selectTabAtPosition(1);

        bottomBar.setTabSelectionInterceptor(new TabSelectionInterceptor() {
            @Override
            public boolean shouldInterceptTabSelection(@IdRes int oldTabId, @IdRes int newTabId) {

                Intent intent;

                switch (newTabId) {
                    case R.id.tab_profiles:
                        intent = new Intent(showInfoLogin.this, PrimaryActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.tab_logins:
                        intent = new Intent(showInfoLogin.this, logins.class);
                        startActivity(intent);
                        break;
                    case R.id.tab_settings:
                        intent = new Intent(showInfoLogin.this, settings.class);
                        startActivity(intent);
                        break;
                }

                return true;
            }
        });
    }
}
