package com.lasanimas.simplyvaldo.mylogins.View.Fragments;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.lasanimas.simplyvaldo.mylogins.Interfaces.FragmentToActivityListener;
import com.lasanimas.simplyvaldo.mylogins.Model.profilesDB;
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

    private FragmentToActivityListener myListener;

    private FirebaseRecyclerAdapter<profilesDB, profilesHolder> adapter;
    private ArrayList<String> selectedProfiles = new ArrayList<String>();

    public profiles() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profiles, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        deleteButton.setVisibility(View.INVISIBLE);
        radioButton.setVisibility(View.INVISIBLE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Profiles");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        profileList.setHasFixedSize(true);
        profileList.setLayoutManager(layoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(profileList.getContext(), layoutManager.getOrientation());
        profileList.addItemDecoration(mDividerItemDecoration);


        adapter = new FirebaseRecyclerAdapter<profilesDB, profilesHolder>(
                profilesDB.class,
                R.layout.listview_profiles,
                profilesHolder.class,
                myRef
        ) {
            @Override
            protected void populateViewHolder(profilesHolder viewHolder, final profilesDB model, final int position)
            {
                viewHolder.profilePic.setImageResource(R.drawable.profile_pic);
                viewHolder.profileName.setText(model.getName());

                if(deleteButton.getVisibility() == View.INVISIBLE)
                {
                    viewHolder.checkBox.setVisibility(View.INVISIBLE);
                    radioButton.setVisibility(View.INVISIBLE);
                }
                else
                {
                    viewHolder.checkBox.setVisibility(View.VISIBLE);
                    radioButton.setVisibility(View.VISIBLE);
                }

                if(radioButton.isChecked())
                {
                    viewHolder.checkBox.setChecked(true);

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

                viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
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

                viewHolder.container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        myListener.SendProfileName(getRef(position).getKey(),model.getName());
                    }
                });
            }

            @Override
            public void onDataChanged()
            {
                if (adapter.getItemCount() == 0)
                {
                    selectButton.setEnabled(false);
                }
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

        try
        {
            myListener = (FragmentToActivityListener) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString() + " must implement FragmentToActivityListener");
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
