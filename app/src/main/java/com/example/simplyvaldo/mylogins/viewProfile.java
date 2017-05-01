package com.example.simplyvaldo.mylogins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    @BindView(R.id.textViewR1)
    TextView textViewR1;
    @BindView(R.id.textViewR2)
    TextView textViewR2;
    @BindView(R.id.textViewR3)
    TextView textViewR3;
    @BindView(R.id.textViewR4)
    TextView textViewR4;

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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewprofile);
        ButterKnife.bind(this);

        editText1.setVisibility(View.GONE);
        editText2.setVisibility(View.GONE);
        editText3.setVisibility(View.GONE);
        editText4.setVisibility(View.GONE);

        textViewL1.setText("Name");
        textViewL2.setText("Last Name");
        textViewL3.setText("Date Created");
        textViewL4.setText("Relationship");

        FireBaseKey = this.getIntent().getStringExtra("key");
        String name = this.getIntent().getStringExtra("name");
        String lastName = this.getIntent().getStringExtra("lastName");
        String dateCreation = this.getIntent().getStringExtra("dateCreation");
        String relationship = this.getIntent().getStringExtra("relationship");

        textViewR1.setText(name);
        textViewR2.setText(lastName);
        textViewR3.setText(dateCreation);
        textViewR4.setText(relationship);

        editText1.setText(name);
        editText2.setText(lastName);
        editText3.setText(dateCreation);
        editText4.setText(relationship);
    }

    @OnClick(R.id.backButton)
    public void onClickBackButton()
    {
        Intent intent = new Intent(this, profiles.class);
        startActivity(intent);
    }

    @OnClick(R.id.editButton)
    public void OnClickEditButton()
    {
        if(editButton.getText().toString().equals("EDIT"))
        {
            textViewR1.setVisibility(View.GONE);
            textViewR2.setVisibility(View.GONE);
            textViewR3.setVisibility(View.GONE);
            textViewR4.setVisibility(View.GONE);

            editText1.setVisibility(View.VISIBLE);
            editText2.setVisibility(View.VISIBLE);
            editText3.setVisibility(View.VISIBLE);
            editText4.setVisibility(View.VISIBLE);

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

            textViewR1.setText(editText1.getText());
            textViewR2.setText(editText2.getText());
            textViewR3.setText(editText3.getText());
            textViewR4.setText(editText4.getText());

            textViewR1.setVisibility(View.VISIBLE);
            textViewR2.setVisibility(View.VISIBLE);
            textViewR3.setVisibility(View.VISIBLE);
            textViewR4.setVisibility(View.VISIBLE);

            editText1.setVisibility(View.GONE);
            editText2.setVisibility(View.GONE);
            editText3.setVisibility(View.GONE);
            editText4.setVisibility(View.GONE);

            editButton.setText("EDIT");
        }
    }

    @OnClick(R.id.deleteButton)
    public void onClickDeleteButton()
    {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Profiles/");
        myRef.child(FireBaseKey).removeValue();

        Intent intent = new Intent(this, profiles.class);
        startActivity(intent);
    }
}
