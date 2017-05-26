package com.lasanimas.simplyvaldo.mylogins.View.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lasanimas.simplyvaldo.mylogins.Adapter.RecyclerViewAdapterCategories;
import com.lasanimas.simplyvaldo.mylogins.Adapter.RecyclerViewAdapterLogins;
import com.lasanimas.simplyvaldo.mylogins.Interfaces.RecyclerViewToFragmentListener;
import com.lasanimas.simplyvaldo.mylogins.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class categories extends Fragment
{
    @BindView(R.id.profileName)
    TextView profileName;

    @BindView(R.id.RecyclerViewCategories)
    RecyclerView categoriesListView;

    private ArrayList<String> categories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        Bundle bundle = this.getArguments();

        String id = bundle.getString("id");

        Log.i("ID", id);
        String profilename = bundle.getString("profileName");
        profileName.setText(profilename);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        categoriesListView.setHasFixedSize(true);
        categoriesListView.setLayoutManager(layoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(categoriesListView.getContext(), layoutManager.getOrientation());
        categoriesListView.addItemDecoration(mDividerItemDecoration);

        getCategories();
    }

    public void getCategories()
    {
        categories = new ArrayList<String>(){{
            add("EMAIL"); add("SOCIAL"); add("MEMBERSHIP");
            add("MEDIA"); add("BANK"); add("DEVICES"); add("OTHER");
        }};

        RecyclerViewAdapterCategories adapter = new RecyclerViewAdapterCategories(getActivity(), categories);
        categoriesListView.setAdapter(adapter);
    }
}
