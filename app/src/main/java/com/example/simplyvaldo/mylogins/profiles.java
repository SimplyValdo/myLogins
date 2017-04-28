package com.example.simplyvaldo.mylogins;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.PropertyName;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class profiles extends AppCompatActivity
{
    @BindView(R.id.profiles)
    TextView profiles;
    @BindView(R.id.settings)
    TextView settings;
    @BindView(R.id.newButton)
    Button newButton;
    @BindView(R.id.selectButton)
    Button selectButton;
    @BindView(R.id.deleteButton)
    Button deleteButton;

    @BindView(R.id.pListView)
    ListView profileList;

    FirebaseListAdapter<ProfilesDB> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiles);
        ButterKnife.bind(this);

        deleteButton.setVisibility(View.INVISIBLE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Profiles");

        adapter = new FirebaseListAdapter<ProfilesDB>(
                this,
                ProfilesDB.class,
                R.layout.listview_profiles,
                myRef
        ){
            @Override
            protected void populateView(View v, ProfilesDB model, int position)
            {
                ImageView profilePic = (ImageView) v.findViewById(R.id.profilePic);
                profilePic.setImageResource(R.drawable.profile_pic);

                TextView textView = (TextView) v.findViewById(R.id.profileName);
                textView.setText(model.getName());

                CheckBox checkBox;
                checkBox = (CheckBox) v.findViewById(R.id.checkbox);

                if(deleteButton.getVisibility() == View.INVISIBLE)
                {
                    checkBox.setVisibility(View.INVISIBLE);
                }
                else
                {
                    checkBox.setVisibility(View.VISIBLE);
                }
            }
        };

        profileList.setAdapter(adapter);
    }

    @OnClick(R.id.profiles)
    public void OnClickProfiles()
    {
        Intent intent = new Intent(profiles.this, profiles.class);
        startActivity(intent);
    }

    @OnClick(R.id.settings)
    public void OnClickSettings()
    {
        Intent intent = new Intent(profiles.this, settings.class);
        startActivity(intent);
    }

    @OnClick(R.id.newButton)
    public void OnClickNewButton()
    {
        Intent intent = new Intent(profiles.this, createProfile.class);
        startActivity(intent);
    }

    @OnClick(R.id.selectButton)
    public void OnClickSelectButton()
    {
        if(deleteButton.getVisibility() == View.INVISIBLE)
        {
            deleteButton.setVisibility(View.VISIBLE);
            selectButton.setText("UNSELECT");
        }
        else
        {
            deleteButton.setVisibility(View.INVISIBLE);
            selectButton.setText("SELECT");
        }

        profileList.setAdapter(adapter);
    }
}