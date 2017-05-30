package com.lasanimas.simplyvaldo.mylogins.View.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lasanimas.simplyvaldo.mylogins.R;
import com.lasanimas.simplyvaldo.mylogins.View.Activities.PrimaryActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class viewLogin extends Fragment
{
    @BindView(R.id.label1)
    TextView label1;
    @BindView(R.id.label2)
    TextView label2;
    @BindView(R.id.label3)
    TextView label3;

    @BindViews({R.id.data3, R.id.data1, R.id.data2})
    List<EditText> datas;

    @BindView(R.id.backButton)
    Button backButton;
    @BindView(R.id.editButton)
    Button editButton;
    @BindView(R.id.deleteButton)
    Button deleteButton;

    private FirebaseDatabase database;
    private DatabaseReference myRef ;
    private Bundle bundle;
    FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        bundle = this.getArguments();
        database = FirebaseDatabase.getInstance();
        return inflater.inflate(R.layout.fragment_viewlogin, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        datas.get(0).setFocusable(false);
        datas.get(0).setCursorVisible(false);

        datas.get(1).setFocusable(false);
        datas.get(1).setCursorVisible(false);

        datas.get(2).setFocusable(false);
        datas.get(2).setCursorVisible(false);

        myRef = database.getReference("Profiles/" + bundle.getString("id") + "/categories/" + bundle.getString("type") + "/"
            + bundle.getString("currentLoginName"));

        RetrieveDataFromFireBase();
    }

    public void RetrieveDataFromFireBase()
    {
        loadLabels(bundle.getString("type"));

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> currentLogin = dataSnapshot.getChildren();

                int counter = 0;
                for(DataSnapshot children: currentLogin) {
                    datas.get(counter++).setText(children.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }
    public void loadLabels(String type)
    {
        switch(type)
        {
            case "email":
                label1.setText("Email");
                label2.setText("Password");
                label3.setText("Date Creation");
                break;
            case "social":
                label1.setText("Email");
                label2.setText("Password");
                label3.setText("Date Creation");
                break;
            case "membership":
                label1.setText("Username");
                label2.setText("Password");
                label3.setText("Date Creation");
                break;
            case "media":
                label1.setText("Username");
                label2.setText("Password");
                label3.setText("Date Creation");
                break;
            case "bank":
                label1.setText("Username");
                label2.setText("Password");
                label3.setText("Date Creation");
                break;
            case "devices":
                label1.setText("Username");
                label2.setText("Password");
                label3.setText("Date Creation");
                break;
            case "other":
                label1.setText("Username");
                label2.setText("Password");
                label3.setText("Date Creation");
                break;
        }
    }

    @OnClick(R.id.backButton)
    public void onClickBackButton()
    {
        getActivity().getFragmentManager().popBackStack();
    }

    @OnClick(R.id.editButton)
    public void OnClickEditButton()
    {
        if(editButton.getText().toString().equals("EDIT"))
        {
            datas.get(0).setFocusable(true);
            datas.get(0).setCursorVisible(true);
            datas.get(0).setFocusableInTouchMode(true);
            datas.get(0).requestFocus();

            datas.get(1).setFocusable(true);
            datas.get(1).setCursorVisible(true);
            datas.get(1).setFocusableInTouchMode(true);
            datas.get(1).requestFocus();

            datas.get(2).setFocusable(true);
            datas.get(2).setCursorVisible(true);
            datas.get(2).setFocusableInTouchMode(true);
            datas.get(2).requestFocus();

            editButton.setText("DONE");
        }
        else
        {
            switch(bundle.getString("type"))
            {
                case "email":
                    myRef.child("email").setValue(datas.get(1).getText().toString());
                    myRef.child("password").setValue(datas.get(2).getText().toString());
                    break;
                case "social":
                    myRef.child("email").setValue(datas.get(1).getText().toString());
                    myRef.child("password").setValue(datas.get(2).getText().toString());
                    break;
                case "membership":
                    myRef.child("username").setValue(datas.get(1).getText().toString());
                    myRef.child("password").setValue(datas.get(2).getText().toString());
                    break;
                case "media":
                    myRef.child("username").setValue(datas.get(1).getText().toString());
                    myRef.child("password").setValue(datas.get(2).getText().toString());
                    break;
                case "bank":
                    myRef.child("username").setValue(datas.get(1).getText().toString());
                    myRef.child("password").setValue(datas.get(2).getText().toString());
                    break;
                case "devices":
                    myRef.child("username").setValue(datas.get(1).getText().toString());
                    myRef.child("password").setValue(datas.get(2).getText().toString());
                    break;
                case "other":
                    myRef.child("id").setValue(datas.get(1).getText().toString());
                    myRef.child("password").setValue(datas.get(2).getText().toString());
                    break;
            }


            datas.get(0).setFocusable(false);
            datas.get(0).setCursorVisible(false);

            datas.get(1).setFocusable(false);
            datas.get(1).setCursorVisible(false);

            datas.get(2).setFocusable(false);
            datas.get(2).setCursorVisible(false);

            editButton.setText("EDIT");
        }
    }

    @OnClick(R.id.deleteButton)
    public void onClickDeleteButton()
    {
        myRef.removeValue();
        getActivity().getFragmentManager().popBackStack();
    }
}
