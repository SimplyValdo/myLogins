package com.example.simplyvaldo.mylogins.View.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.simplyvaldo.mylogins.Model.ProfilesDB;
import com.example.simplyvaldo.mylogins.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class viewProfile extends AppCompatActivity
{
    @BindView(R.id.textViewL1)
    TextView textViewL1;
    @BindView(R.id.textViewL2)
    TextView textViewL2;
    @BindView(R.id.textViewL3)
    TextView textViewL3;
    @BindView(R.id.textViewL4)
    TextView textViewL4;

    @BindView(R.id.editText1)
    EditText editText1;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.editText3)
    TextView editText3;
    @BindView(R.id.editText4)
    TextView editText4;

    @BindView(R.id.backButton)
    Button backButton;
    @BindView(R.id.editButton)
    Button editButton;
    @BindView(R.id.deleteButton)
    Button deleteButton;

    FirebaseDatabase database;
    DatabaseReference myRef;
    String FireBaseKey;
    int originalDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewprofile);
        ButterKnife.bind(this);

        FireBaseKey = this.getIntent().getStringExtra("key");
        String name = this.getIntent().getStringExtra("name");
        String lastName = this.getIntent().getStringExtra("lastName");
        String dateCreation = this.getIntent().getStringExtra("dateCreation");
        String relationship = this.getIntent().getStringExtra("relationship");

        textViewL1.setText("Name: ");
        textViewL2.setText("Last Name: ");
        textViewL3.setText("Date Created: ");
        textViewL4.setText("Relationship: ");

        editText1.setFocusable(false);
        editText1.setCursorVisible(false);

        editText2.setFocusable(false);
        editText2.setCursorVisible(false);

        editText3.setFocusable(false);
        editText3.setCursorVisible(false);

        editText4.setFocusable(false);
        editText4.setCursorVisible(false);

        editText1.setText(name);
        editText2.setText(lastName);
        editText3.setText(dateCreation);
        editText4.setText(relationship);
    }

    @OnClick(R.id.backButton)
    public void onClickBackButton()
    {
        Intent intent = new Intent(this, PrimaryActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.editButton)
    public void OnClickEditButton()
    {
        if(editButton.getText().toString().equals("EDIT"))
        {
            editText1.setFocusable(true);
            editText1.setCursorVisible(true);
            editText1.setFocusableInTouchMode(true);
            editText1.requestFocus();

            editText2.setFocusable(true);
            editText2.setCursorVisible(true);
            editText2.setFocusableInTouchMode(true);
            editText2.requestFocus();


            editText3.setFocusable(true);
            editText3.setCursorVisible(true);
            editText3.setFocusableInTouchMode(true);
            editText3.requestFocus();


            editText4.setFocusable(true);
            editText4.setCursorVisible(true);
            editText4.setFocusableInTouchMode(true);
            editText4.requestFocus();

            editButton.setText("DONE");
        }
        else
        {
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("Profiles/" + FireBaseKey);

            ProfilesDB updateProfile = new ProfilesDB();
            updateProfile.setName(editText1.getText().toString());
            updateProfile.setLastName(editText2.getText().toString());
            updateProfile.setDateCreation(editText3.getText().toString());
            updateProfile.setRelationship(editText4.getText().toString());

            myRef.setValue(updateProfile);

            editText1.setFocusable(false);
            editText1.setCursorVisible(false);

            editText2.setFocusable(false);
            editText2.setCursorVisible(false);

            editText3.setFocusable(false);
            editText3.setCursorVisible(false);

            editText4.setFocusable(false);
            editText4.setCursorVisible(false);

            editButton.setText("EDIT");
        }
    }

    @OnClick(R.id.deleteButton)
    public void onClickDeleteButton()
    {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Profiles/");
        myRef.child(FireBaseKey).removeValue();

        Intent intent = new Intent(this, PrimaryActivity.class);
        startActivity(intent);
    }
}
