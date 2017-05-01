package com.example.simplyvaldo.mylogins;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;


public class createProfile extends AppCompatActivity
{
    @BindView(R.id.settings)
    TextView settings;
    @BindView(R.id.profiles)
    TextView profiles;

    @BindView(R.id.done)
    Button done;
    @BindView(R.id.cancel)
    Button cancel;

    @BindView(R.id.EditTextName)
    EditText name;
    @BindView(R.id.EditTextLastName)
    EditText lastName;
    @BindView(R.id.EditTextDate)
    EditText date;
    @BindView(R.id.EditTextRelationship)
    EditText relationship;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createprofile);
        ButterKnife.bind(this);

        done.setEnabled(false);

        System.out.println( name.getText().length());


    }

    @OnTextChanged({R.id.EditTextName, R.id.EditTextLastName, R.id.EditTextDate, R.id.EditTextRelationship})
    public void onChange()
    {
      if(!name.getText().toString().isEmpty() && !lastName.getText().toString().isEmpty() &&
              !date.getText().toString().isEmpty() && !relationship.getText().toString().isEmpty())
          done.setEnabled(true);
        else
          done.setEnabled(false);
    }

    @OnClick(R.id.settings)
    public void onClickSettings()
    {
        Intent intent = new Intent(createProfile.this, settings.class);
        startActivity(intent);
    }

    @OnClick(R.id.profiles)
    public void onClickProfiles()
    {
        Intent intent = new Intent(createProfile.this, profiles.class);
        startActivity(intent);
    }

    @OnClick(R.id.done)
    public void onClickDone()
    {
        Toast.makeText(createProfile.this, "Done", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.cancel)
    public void onClickCancel()
    {
        Intent intent = new Intent(createProfile.this, profiles.class);
        startActivity(intent);
    }
}
