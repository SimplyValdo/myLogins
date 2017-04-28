package com.example.simplyvaldo.mylogins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class logins extends AppCompatActivity {

    @BindView(R.id.settings)
    TextView settings;
    @BindView(R.id.profiles)
    TextView profiles;

    @BindView(R.id.newButton)
    Button newButton;
    @BindView(R.id.edit)
    Button edit;
    @BindView(R.id.delete)
    Button delete;

    @BindView(R.id.dummy)
    TextView dummy;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logins);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.settings)
    public void onClickSettings()
    {
        Intent intent = new Intent(logins.this, settings.class);
        startActivity(intent);
    }

    @OnClick(R.id.profiles)
    public void onClickProfiles()
    {
        Intent intent = new Intent(logins.this, profiles.class);
        startActivity(intent);
    }

    @OnClick(R.id.newButton)
    public void onClickNewButton()
    {
        Intent intent = new Intent(logins.this, loginType.class);
        startActivity(intent);
    }

    @OnClick(R.id.edit)
    public void onClickEdit()
    {
        Toast.makeText(logins.this, "Edit", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.delete)
    public void onClickDelete()
    {
        Toast.makeText(logins.this, "Delete", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.dummy)
    public void onClickDummy()
    {
        Intent intent = new Intent(logins.this, showInfoLogin.class);
        startActivity(intent);
    }

}
