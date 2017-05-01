package com.example.simplyvaldo.mylogins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

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

    @BindView(R.id.radioButton)
    RadioButton radioButton;

    @BindView(R.id.pListView)
    ListView profileList;

    private FirebaseListAdapter<ProfilesDB> adapter;
    private ArrayList<String> selectedProfiles = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiles);
        ButterKnife.bind(this);

        deleteButton.setVisibility(View.INVISIBLE);
        radioButton.setVisibility(View.INVISIBLE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Profiles");

        adapter = new FirebaseListAdapter<ProfilesDB>(
                this,
                ProfilesDB.class,
                R.layout.listview_profiles,
                myRef
        ){
            @Override
            protected void populateView(View v, final ProfilesDB model, final int position)
            {
                ImageView profilePic = (ImageView) v.findViewById(R.id.profilePic);
                profilePic.setImageResource(R.drawable.profile_pic);

                TextView textView = (TextView) v.findViewById(R.id.profileName);
                textView.setText(model.getName());

                CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkbox);

                if(deleteButton.getVisibility() == View.INVISIBLE)
                {
                    checkBox.setVisibility(View.INVISIBLE);
                    radioButton.setVisibility(View.INVISIBLE);
                }
                else
                {
                    checkBox.setVisibility(View.VISIBLE);
                    radioButton.setVisibility(View.VISIBLE);
                }

                if(radioButton.isChecked())
                {
                    checkBox.setChecked(true);

                    String key = getRef(position).getKey();

                    if(!selectedProfiles.contains(key))
                        selectedProfiles.add(key);
                }
                else
                    selectedProfiles.clear();

                if(selectedProfiles.isEmpty())
                    deleteButton.setEnabled(false);
                else
                    deleteButton.setEnabled(true);

                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String key = getRef(position).getKey();

                        if(!selectedProfiles.contains(key))
                            selectedProfiles.add(key);
                        else
                            selectedProfiles.remove(key);

                        if(selectedProfiles.isEmpty())
                            deleteButton.setEnabled(false);
                        else
                            deleteButton.setEnabled(true);
                    }
                });

                profileList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                        ProfilesDB clickedProfile = (ProfilesDB) adapterView.getItemAtPosition(position);
                        String clickedFireBaseKey = adapter.getRef(position).getKey();

                        Intent intent = new Intent(profiles.this, viewProfile.class);
                        intent.putExtra("key", clickedFireBaseKey);
                        intent.putExtra("name", clickedProfile.getName());
                        intent.putExtra("lastName", clickedProfile.getLastName());
                        intent.putExtra("dateCreation", clickedProfile.getDateCreation());
                        intent.putExtra("relationship", clickedProfile.getRelationship());
                        startActivity(intent);
                    }
                });

            };

            @Override
            public void onDataChanged()
            {
                if (profileList.getCount() == 0)
                {
                    selectButton.setEnabled(false);
                }
                else
                    selectButton.setEnabled(true);
            }
        };

        profileList.setAdapter(adapter);
        TextView emptyView = (TextView)findViewById(R.id.emptyView);
        profileList.setEmptyView(emptyView);
    }

    @OnClick(R.id.profiles)
    public void OnClickProfiles()
    {
        Intent intent = new Intent(this, profiles.class);
        startActivity(intent);
    }

    @OnClick(R.id.settings)
    public void OnClickSettings()
    {
        Intent intent = new Intent(this, settings.class);
        startActivity(intent);
    }

    @OnClick(R.id.newButton)
    public void OnClickNewButton()
    {
        Intent intent = new Intent(this, createProfile.class);
        startActivity(intent);
    }

    @OnClick(R.id.selectButton)
    public void OnClickSelectButton()
    {
        if(deleteButton.getVisibility() == View.INVISIBLE)
        {
            deleteButton.setVisibility(View.VISIBLE);
            selectButton.setText("CANCEL");
        }
        else
        {
            if (radioButton.isChecked())
            {
                radioButton.setChecked(false);
                isChecked ^= true;
            }

            deleteButton.setVisibility(View.INVISIBLE);
            selectButton.setText("SELECT");
        }

        profileList.setAdapter(adapter);
    }

    @OnClick(R.id.deleteButton)
    public void OnClickDeleteButton()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Profiles");

        for(String currentProfile: selectedProfiles)
        {
            myRef.child(currentProfile).removeValue();
        }

        profileList.setAdapter(adapter);
    }

    boolean isChecked = false;

    @OnClick(R.id.radioButton)
    public void onClickRadioButton() {
        isChecked ^= true;

        if (isChecked)
            radioButton.setChecked(true);
        else
            radioButton.setChecked(false);

        profileList.setAdapter(adapter);
    }
}