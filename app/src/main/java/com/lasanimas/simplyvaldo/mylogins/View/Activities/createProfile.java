package com.lasanimas.simplyvaldo.mylogins.View.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lasanimas.simplyvaldo.mylogins.Model.profilesDB;
import com.lasanimas.simplyvaldo.mylogins.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;


public class createProfile extends AppCompatActivity
{
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
        setContentView(R.layout.activity_createprofile);
        ButterKnife.bind(this);

        done.setEnabled(false);
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


    @OnClick(R.id.done)
    public void onClickDone()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Profiles/");

        profilesDB newProfile = new profilesDB();
        newProfile.setName(name.getText().toString());
        newProfile.setLastName(lastName.getText().toString());
        newProfile.setDateCreation(date.getText().toString());
        newProfile.setRelationship(relationship.getText().toString());

        DatabaseReference id = myRef.push();

        Log.i("ID", id.getKey());
        id.setValue(newProfile);

        Intent intent = new Intent(createProfile.this, PrimaryActivity.class);
        startActivity(intent);

        Toast.makeText(this, "New Profile has been created", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.cancel)
    public void onClickCancel()
    {
        Intent intent = new Intent(createProfile.this, PrimaryActivity.class);
        startActivity(intent);
    }
}
