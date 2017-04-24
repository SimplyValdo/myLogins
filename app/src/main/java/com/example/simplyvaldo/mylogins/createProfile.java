package com.example.simplyvaldo.mylogins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class createProfile extends AppCompatActivity
{
    private TextView settings;
    private TextView profiles;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createprofile);

        settings = (TextView) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(createProfile.this, settings.class);
                startActivity(intent);
            }
        });

        profiles = (TextView) findViewById(R.id.profiles);
        profiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(createProfile.this, profiles.class);
                startActivity(intent);
            }
        });
    }
}
