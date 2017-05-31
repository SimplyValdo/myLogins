package com.lasanimas.simplyvaldo.mylogins.View.Fragments;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lasanimas.simplyvaldo.mylogins.Adapter.RecyclerViewAdapterLogins;
import com.lasanimas.simplyvaldo.mylogins.Interfaces.FragmentLoginsToActivityListener;
import com.lasanimas.simplyvaldo.mylogins.Interfaces.RecyclerViewLoginsToFragmentListener;
import com.lasanimas.simplyvaldo.mylogins.Model.CategoriesHelper;
import com.lasanimas.simplyvaldo.mylogins.R;
import com.lasanimas.simplyvaldo.mylogins.View.Activities.loginType;
import com.lasanimas.simplyvaldo.mylogins.View.Activities.viewProfile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.paperdb.Paper;

public class logins extends Fragment implements RecyclerViewLoginsToFragmentListener
{
    @BindView(R.id.newButton)
    Button newButton;
    @BindView(R.id.selectButton)
    Button selectButton;
    @BindView(R.id.deleteButton)
    Button deleteButton;
    @BindView(R.id.radioButton)
    RadioButton radioButton;

    @BindView(R.id.profileName)
    TextView profileName;

    @BindView(R.id.RecyclerViewLogins)
    RecyclerView loginsListView;

    private FragmentLoginsToActivityListener myListener;
    private RecyclerViewAdapterLogins adapter;

    private String id;
    private int size;
    private ColorStateList DefaultStateRadioButton;
    private ArrayList<String> logins;
    private HashMap<Integer, String> types;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef ;
    private List<Boolean> isFavorite;

    public logins() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_logins, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        radioButton.setVisibility(View.INVISIBLE);
        deleteButton.setVisibility(View.INVISIBLE);
        deleteButton.setEnabled(false);
        DefaultStateRadioButton = radioButton.getButtonTintList();

        Bundle bundle = this.getArguments();

        id = bundle.getString("id");

        Log.i("ID", id);
        myRef = database.getReference("Profiles/" + id + "/categories");
        String profilename = bundle.getString("profileName");
        profileName.setText(profilename);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        loginsListView.setHasFixedSize(true);
        loginsListView.setLayoutManager(layoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(loginsListView.getContext(), layoutManager.getOrientation());
        loginsListView.addItemDecoration(mDividerItemDecoration);

        getLogins();
    }

    public void getLogins()
    {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                CategoriesHelper categoriesHelper = dataSnapshot.getValue(CategoriesHelper.class);
                Paper.book().write("categories",categoriesHelper);

                int counter = 0;
                logins = new ArrayList<String>();
                types = new HashMap<Integer, String>();

                Iterable<DataSnapshot> data = dataSnapshot.child("email").getChildren();
                Iterable<DataSnapshot> data2 = dataSnapshot.child("social").getChildren();
                Iterable<DataSnapshot> data3 = dataSnapshot.child("membership").getChildren();
                Iterable<DataSnapshot> data4 = dataSnapshot.child("media").getChildren();
                Iterable<DataSnapshot> data5 = dataSnapshot.child("bank").getChildren();
                Iterable<DataSnapshot> data6 = dataSnapshot.child("devices").getChildren();
                Iterable<DataSnapshot> data7 = dataSnapshot.child("other").getChildren();

                for(DataSnapshot children: data) {
                    logins.add(children.getKey());
                    types.put(counter++, "email");
                }

                for(DataSnapshot children: data2) {
                    logins.add(children.getKey());
                    types.put(counter++, "social");
                }

                for(DataSnapshot children: data3) {
                    logins.add(children.getKey());
                    types.put(counter++, "membership");
                }

                for(DataSnapshot children: data4) {
                    logins.add(children.getKey());
                    types.put(counter++, "media");
                }

                for(DataSnapshot children: data5) {
                    logins.add(children.getKey());
                    types.put(counter++, "bank");
                }

                for(DataSnapshot children: data6) {
                    logins.add(children.getKey());
                    types.put(counter++, "devices");
                }

                for(DataSnapshot children: data7) {
                    logins.add(children.getKey());
                    types.put(counter++, "other");
                }

                size = logins.size();
                adapter = new RecyclerViewAdapterLogins(getActivity(), logins, types, logins.this);
                loginsListView.setAdapter(adapter);

                isFavorite = new ArrayList<>();
                for(int i = 0; i < size; i++)
                {
                    isFavorite.add(false);
                }

                if(!Paper.book().exist("favorites"))
                {
                    Paper.book().write("favorites", isFavorite);
                    Log.i("I'm inside", "I don't exist");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.i("DataBaseError", databaseError.getMessage());
                Log.i("DataBaseError", databaseError.getDetails());
            }
        });
    }

    @OnClick(R.id.newButton)
    public void onClickNewButton()
    {
        Intent intent = new Intent(getActivity(), loginType.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @OnClick(R.id.selectButton)
    public void onClickSelectButton()
    {
        if(deleteButton.getVisibility() == View.INVISIBLE)
        {
            adapter.checkBoxesVisibility(true);
            deleteButton.setVisibility(View.VISIBLE);
            radioButton.setVisibility(View.VISIBLE);
            selectButton.setText("CANCEL");
        }
        else
        {
            if (radioButton.isChecked()) {
                radioButton.setChecked(false);
                isChecked ^= true;
            }

            adapter.checkBoxesVisibility(false);
            adapter.setAllCheckBoxes(false);
            deleteButton.setVisibility(View.INVISIBLE);
            radioButton.setVisibility(View.INVISIBLE);
            radioButton.setButtonTintList(DefaultStateRadioButton);
            selectButton.setText("SELECT");
        }
    }

    @OnClick(R.id.deleteButton)
    public void onClickDeleteButton()
    {
        adapter.deleteSelectedLogins(id);
        selectButton.setText("SELECT");
        radioButton.setChecked(isChecked = false);
        radioButton.setButtonTintList(DefaultStateRadioButton);
        deleteButton.setVisibility(View.INVISIBLE);
        radioButton.setVisibility(View.INVISIBLE);
    }

    boolean isChecked = false;

    @OnClick(R.id.radioButton)
    public void onClickRadioButton()
    {
        isChecked ^= true;

        if (isChecked) {
            radioButton.setChecked(true);
            radioButton.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.colorAccent)));
            adapter.setAllCheckBoxes(true);
        }
        else {
            radioButton.setChecked(false);
            radioButton.setButtonTintList(DefaultStateRadioButton);
            adapter.setAllCheckBoxes(false);
        }
    }

    @OnClick(R.id.profileName)
    public void onProfileName()
    {
        Bundle bundle = this.getArguments();
        final String id = bundle.getString("id");
        myRef = database.getReference("Profiles/" + id);
        final Intent intent = new Intent(getActivity(), viewProfile.class);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                intent.putExtra("key", id);
                intent.putExtra("name", dataSnapshot.child("name").getValue().toString());
                intent.putExtra("lastName", dataSnapshot.child("lastName").getValue().toString());
                intent.putExtra("dateCreation",  dataSnapshot.child("dateCreation").getValue().toString());
                intent.putExtra("relationship", dataSnapshot.child("relationship").getValue().toString());
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.i("DataBaseError", databaseError.getMessage());
                Log.i("DataBaseError", databaseError.getDetails());
            }
        });
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

    @Override
    public void onResume() {
        super.onResume();

        Log.i("Resume", "Resume");
        getLogins();
    }

    @Override
    public void SendSelectLoginInfo(String currentLoginName, String type) {

        myListener.SendLoginName(id, currentLoginName, type, 0);
    }

    @Override
    public void setStateDeleteButton(boolean status) {
        deleteButton.setEnabled(status);
    }

    @Override
    public void setStateSomeCheckedRadioButton(String color) {

        switch(color)
        {
            case "default":
                radioButton.setButtonTintList(DefaultStateRadioButton);
                radioButton.setChecked(isChecked = false);
                break;
            case "green":
                radioButton.setButtonTintList(ColorStateList.valueOf(Color.GREEN));
                break;
        }
    }

    @Override
    public void allCheckBoxesCheckedManually() {
        radioButton.setChecked(isChecked = true);
        radioButton.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.colorAccent)));
    }
}
