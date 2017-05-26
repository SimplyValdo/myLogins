package com.lasanimas.simplyvaldo.mylogins.View.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lasanimas.simplyvaldo.mylogins.R;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class viewLogin extends Fragment
{

    @BindView(R.id.label1)
    TextView label1;
    @BindView(R.id.label2)
    TextView label2;
    @BindView(R.id.label3)
    TextView label3;
    @BindView(R.id.data1)
    TextView data1;
    @BindView(R.id.data2)
    TextView data2;
    @BindView(R.id.data3)
    TextView data3;

    private ArrayList<String> data;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef ;
    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_viewlogin, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        bundle = this.getArguments();
        myRef = database.getReference("Profiles/" + bundle.getString("id") + "/categories/" + bundle.getString("type") + "/"
            + bundle.getString("currentLoginName"));

        RetrieveDataFromFireBase();
    }

    public void RetrieveDataFromFireBase()
    {
        data = new ArrayList<String>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> currentLogin = dataSnapshot.getChildren();

                for(DataSnapshot children: currentLogin) {
                    data.add(children.getValue().toString());
                }

                loadData(bundle.getString("type"));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }
    public void loadData(String type)
    {
        switch(type)
        {
            case "email":
                label1.setText("Email");
                label2.setText("Password");
                data1.setText(data.get(0));
                data2.setText(data.get(1));
                break;
            case "social":
                label1.setText("Email");
                label2.setText("Password");
                data1.setText(data.get(0));
                data2.setText(data.get(1));
                break;
            case "membership":
                label1.setText("Username");
                label2.setText("Password");
                data1.setText(data.get(0));
                data2.setText(data.get(1));
                break;
            case "media":
                label1.setText("Username");
                label2.setText("Password");
                data1.setText(data.get(0));
                data2.setText(data.get(1));
                break;
            case "bank":
                label1.setText("Username");
                label2.setText("Password");
                data1.setText(data.get(0));
                data2.setText(data.get(1));
                break;
            case "devices":
                label1.setText("Username");
                label2.setText("Password");
                data1.setText(data.get(0));
                data2.setText(data.get(1));
                break;
            case "other":
                label1.setText("Username");
                label2.setText("Password");
                data1.setText(data.get(0));
                data2.setText(data.get(1));
                break;
        }
    }
}
