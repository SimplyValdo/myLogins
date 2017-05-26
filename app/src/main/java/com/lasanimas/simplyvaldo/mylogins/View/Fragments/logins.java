package com.example.simplyvaldo.mylogins.View.Fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simplyvaldo.mylogins.Adapter.loginAdapter;
import com.example.simplyvaldo.mylogins.R;
import com.example.simplyvaldo.mylogins.View.Activities.loginType;
import com.example.simplyvaldo.mylogins.View.Activities.showInfoLogin;
import com.example.simplyvaldo.mylogins.View.Activities.viewProfile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class logins extends Fragment
{
    @BindView(R.id.newButton)
    Button newButton;
    @BindView(R.id.edit)
    Button edit;
    @BindView(R.id.delete)
    Button delete;

    @BindView(R.id.profileName)
    TextView profileName;

    @BindView(R.id.RecyclerViewLogins)
    RecyclerView loginsListView;

    private ArrayList<String> logins;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef ;

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

        Bundle bundle = this.getArguments();

        if(bundle != null)
        {
            String id = bundle.getString("id");

            Log.i("ID", id);
            myRef = database.getReference("Profiles/" + id + "/types");
            String profilename = bundle.getString("profileName");
            profileName.setText(profilename);
        }

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

                logins = new ArrayList<String>();

                Iterable<DataSnapshot> data = dataSnapshot.child("email").getChildren();
                Iterable<DataSnapshot> data2 = dataSnapshot.child("socialDB").getChildren();
                Iterable<DataSnapshot> data3 = dataSnapshot.child("membershipDB").getChildren();
                Iterable<DataSnapshot> data4 = dataSnapshot.child("mediaDB").getChildren();
                Iterable<DataSnapshot> data5 = dataSnapshot.child("bankDB").getChildren();
                Iterable<DataSnapshot> data6 = dataSnapshot.child("devicesDB").getChildren();
                Iterable<DataSnapshot> data7 = dataSnapshot.child("otherDB").getChildren();

                for(DataSnapshot children: data) {
                    logins.add(children.getKey());
                }

                for(DataSnapshot children: data2) {
                    logins.add(children.getKey());
                }

                for(DataSnapshot children: data3) {
                    logins.add(children.getKey());
                }

                for(DataSnapshot children: data4) {
                    logins.add(children.getKey());
                }

                for(DataSnapshot children: data5) {
                    logins.add(children.getKey());
                }

                for(DataSnapshot children: data6) {
                    logins.add(children.getKey());
                }

                for(DataSnapshot children: data7) {
                    logins.add(children.getKey());
                }

                loginAdapter adapter = new loginAdapter(getActivity(), logins);
                loginsListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @OnClick(R.id.newButton)
    public void onClickNewButton()
    {
        Intent intent = new Intent(getActivity(), loginType.class);
        startActivity(intent);
    }

    @OnClick(R.id.edit)
    public void onClickEdit()
    {
        Toast.makeText(getActivity(), "Edit", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.delete)
    public void onClickDelete()
    {
        Toast.makeText(getActivity(), "Delete", Toast.LENGTH_SHORT).show();
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

            }
        });
    }
}
