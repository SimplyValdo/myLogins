package com.example.simplyvaldo.mylogins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class logins extends AppCompatActivity {

    private TextView settings;
    private TextView profiles;
    private Button newButton;

    private TextView dummy;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logins);

        settings = (TextView) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(logins.this, settings.class);
                startActivity(intent);
            }
        });

        profiles = (TextView) findViewById(R.id.profiles);
        profiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(logins.this, profiles.class);
                startActivity(intent);
            }
        });

        newButton = (Button) findViewById(R.id.newButton);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(logins.this, loginType.class);
                startActivity(intent);
            }
        });

        dummy =(TextView) findViewById(R.id.dummy);
        dummy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(logins.this, showInfoLogin.class);
                startActivity(intent);
            }
        });

    }
}
