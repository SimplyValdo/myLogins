package com.example.simplyvaldo.mylogins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class createProfile extends AppCompatActivity
{
    private TextView settings;
    private TextView profiles;

    private Button done;
    private Button cancel;
    private Button edit;
    private Button delete;

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

        done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(createProfile.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });

        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(createProfile.this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        });

        edit = (Button) findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(createProfile.this, "Edit", Toast.LENGTH_SHORT).show();
            }
        });

        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(createProfile.this, "Delete", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
