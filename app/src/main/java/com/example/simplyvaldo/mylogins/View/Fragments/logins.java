package com.example.simplyvaldo.mylogins.View.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simplyvaldo.mylogins.Adapter.loginsHolder;
import com.example.simplyvaldo.mylogins.R;
import com.example.simplyvaldo.mylogins.View.Activities.loginType;
import com.example.simplyvaldo.mylogins.View.Activities.showInfoLogin;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    @BindView(R.id.dummy)
    TextView dummy;

    @BindView(R.id.RecyclerViewLogins)
    RecyclerView loginsListView;

    public static final int LOGIN_REQUEST_NEWLOGIN = 1;

    private FirebaseRecyclerAdapter<String, loginsHolder> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_logins, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Profiles/-Kj_piOJofz3Z3zt9n0v");

        loginsListView.setHasFixedSize(true);
        loginsListView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new FirebaseRecyclerAdapter<String, loginsHolder>(
                String.class,
                R.layout.listview_logins,
                loginsHolder.class,
                myRef
        ) {
            @Override
            protected void populateViewHolder(loginsHolder viewHolder, String model, int position) {

                Log.i("Hello", model);
                viewHolder.textView.setText(model);
                Log.i("Hello", model);
            }
        };

        loginsListView.setAdapter(adapter);
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

    @OnClick(R.id.dummy)
    public void onClickDummy()
    {
        Intent intent = new Intent(getActivity(), showInfoLogin.class);
        startActivity(intent);
    }
}
