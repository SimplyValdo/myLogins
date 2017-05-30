package com.lasanimas.simplyvaldo.mylogins.View.Fragments;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.lasanimas.simplyvaldo.mylogins.Helper.ItemTouchHelperCallback;
import com.lasanimas.simplyvaldo.mylogins.Holder.profilesHolder;
import com.lasanimas.simplyvaldo.mylogins.Interfaces.FragmentLoginsToActivityListener;
import com.lasanimas.simplyvaldo.mylogins.Model.ProfilesDB;
import com.lasanimas.simplyvaldo.mylogins.R;
import com.lasanimas.simplyvaldo.mylogins.View.Activities.createProfile;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class profiles extends Fragment
{
    @BindView(R.id.newButton)
    Button newButton;
    @BindView(R.id.selectButton)
    Button selectButton;
    @BindView(R.id.deleteButton)
    Button deleteButton;

    @BindView(R.id.radioButton)
    RadioButton radioButton;

    @BindView(R.id.RecyclerViewProfiles)
    RecyclerView profileList;

    private ColorStateList DefaultStateRadioButton;
    private FragmentLoginsToActivityListener myListener;

    private FirebaseRecyclerAdapter<ProfilesDB, profilesHolder> adapter;
    private ArrayList<String> selectedProfiles = new ArrayList<String>();

    public profiles() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_profiles, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        deleteButton.setVisibility(View.INVISIBLE);
        radioButton.setVisibility(View.INVISIBLE);
        DefaultStateRadioButton = radioButton.getButtonTintList();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Profiles");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        profileList.setHasFixedSize(true);
        profileList.setLayoutManager(layoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(profileList.getContext(), layoutManager.getOrientation());
        profileList.addItemDecoration(mDividerItemDecoration);


        adapter = new FirebaseRecyclerAdapter<ProfilesDB, profilesHolder>(
                ProfilesDB.class,
                R.layout.listview_profiles,
                profilesHolder.class,
                myRef
        ) {
            @Override
            protected void populateViewHolder(profilesHolder viewHolder, final ProfilesDB model, int position)
            {
                final String key = getRef(position).getKey();

                viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(!selectedProfiles.contains(key))
                            selectedProfiles.add(key);
                        else {
                            selectedProfiles.remove(key);
                            radioButton.setButtonTintList(ColorStateList.valueOf(Color.GREEN));
                        }

                        if(selectedProfiles.isEmpty()){
                            deleteButton.setEnabled(false);
                            radioButton.setChecked(isChecked = false);
                            radioButton.setButtonTintList(DefaultStateRadioButton);
                        }
                        else
                            deleteButton.setEnabled(true);

                        if(selectedProfiles.size() == adapter.getItemCount()) {
                            radioButton.setChecked(isChecked = true);
                            radioButton.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.colorAccent)));
                        }
                    }
                });

                viewHolder.profileName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        myListener.SendProfileName(key ,model.getName());
                    }
                });

                viewHolder.profilePic.setImageResource(R.drawable.profile_pic);
                viewHolder.profileName.setText(model.getName());

                if(deleteButton.getVisibility() == View.INVISIBLE)
                {
                    viewHolder.checkBox.setVisibility(View.INVISIBLE);
                    viewHolder.profileName.setClickable(true);
                    radioButton.setVisibility(View.INVISIBLE);
                }
                else
                {
                    viewHolder.profileName.setClickable(false);
                    viewHolder.checkBox.setVisibility(View.VISIBLE);
                    radioButton.setVisibility(View.VISIBLE);
                }

                if(radioButton.isChecked())
                {
                    viewHolder.checkBox.setChecked(true);

                    if(!selectedProfiles.contains(key))
                        selectedProfiles.add(key);
                }
                else
                    selectedProfiles.clear();

                if(selectedProfiles.isEmpty())
                    deleteButton.setEnabled(false);
                else
                    deleteButton.setEnabled(true);

            }

            @Override
            public void onDataChanged()
            {
                if (adapter.getItemCount() == 0)
                    selectButton.setEnabled(false);
                else
                    selectButton.setEnabled(true);
            }
        };

        profileList.setAdapter(adapter);
        //TextView emptyView = (TextView)findViewById(R.id.emptyView);
        //profileList.setEmptyView(emptyView); */

        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelper helper=new ItemTouchHelper(callback);
        helper.attachToRecyclerView(profileList);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        try {
            myListener = (FragmentLoginsToActivityListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FragmentLoginsToActivityListener");
        }

    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (Build.VERSION.SDK_INT < 23) {

            try {
                myListener = (FragmentLoginsToActivityListener) activity;
            }
            catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " must implement FragmentLoginsToActivityListener");
            }
        }
    }

    @OnClick(R.id.newButton)
    public void OnClickNewButton()
    {
        Intent intent = new Intent(getActivity(), createProfile.class);
        startActivity(intent);
    }

    @OnClick(R.id.selectButton)
    public void OnClickSelectButton()
    {
        if(deleteButton.getVisibility() == View.INVISIBLE) {
            deleteButton.setVisibility(View.VISIBLE);
            selectButton.setText("CANCEL");
        }
        else {
            if (radioButton.isChecked()) {
                radioButton.setChecked(false);
                isChecked ^= true;
            }

            radioButton.setButtonTintList(DefaultStateRadioButton);
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

        for(String currentProfile: selectedProfiles) {
            myRef.child(currentProfile).removeValue();
        }

        profileList.setAdapter(adapter);
        selectButton.setText("SELECT");
        radioButton.setChecked(isChecked = false);
        radioButton.setButtonTintList(DefaultStateRadioButton);
        deleteButton.setVisibility(View.INVISIBLE);
        radioButton.setVisibility(View.INVISIBLE);
    }

    boolean isChecked = false;

    @OnClick(R.id.radioButton)
    public void onClickRadioButton() {
        isChecked ^= true;

        if (isChecked) {
            radioButton.setChecked(true);
            radioButton.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.colorAccent)));
        }
        else {
            radioButton.setChecked(false);
            radioButton.setButtonTintList(DefaultStateRadioButton);
        }

        profileList.setAdapter(adapter);
    }
}
