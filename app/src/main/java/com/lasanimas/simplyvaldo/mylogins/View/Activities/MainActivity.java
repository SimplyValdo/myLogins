package com.lasanimas.simplyvaldo.mylogins.View.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.lasanimas.simplyvaldo.mylogins.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity
{
    @BindView(R.id.login)
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Paper.init(this);
    }

    @OnClick(R.id.login)
    public void OnClickLogin()
    {
        Intent intent = new Intent(MainActivity.this, PrimaryActivity.class);
        startActivity(intent);
    }
}
