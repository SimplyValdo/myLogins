package com.example.simplyvaldo.mylogins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class settings extends AppCompatActivity
{
    private TextView profiles;
    private TextView logins;
    private TextView settings;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        profiles = (TextView) findViewById(R.id.profiles);
        profiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settings.this, profiles.class);
                startActivity(intent);
            }
        });

        logins = (TextView) findViewById(R.id.logins);
        logins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settings.this, logins.class);
                startActivity(intent);
            }
        });

        settings = (TextView) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settings.this, settings.class);
                startActivity(intent);
            }
        });
    }
}
