package com.example.simplyvaldo.mylogins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class showInfoLogin extends AppCompatActivity
{
    @BindView(R.id.profiles)
    TextView profiles;
    @BindView(R.id.logins)
    TextView logins;
    @BindView(R.id.settings)
    TextView settings;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_info_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.profiles)
    public void OnClickProfiles()
    {
        Intent intent = new Intent(showInfoLogin.this, profiles.class);
        startActivity(intent);
    }

    @OnClick(R.id.logins)
    public void OnClickLogins()
    {
        Intent intent = new Intent(showInfoLogin.this, logins.class);
        startActivity(intent);
    }

    @OnClick(R.id.settings)
    public void OnClickSettings()
    {
        Intent intent = new Intent(showInfoLogin.this, settings.class);
        startActivity(intent);
    }
}
