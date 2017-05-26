package com.lasanimas.simplyvaldo.mylogins.View.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.lasanimas.simplyvaldo.mylogins.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class loginType extends AppCompatActivity
{

    @BindView(R.id.backButton)
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logintype);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.backButton)
    public void onClickbackButton()
    {
        finish();
    }
}
