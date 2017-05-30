package com.lasanimas.simplyvaldo.mylogins.View.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lasanimas.simplyvaldo.mylogins.Adapter.RecyclerViewAdapterCategories;
import com.lasanimas.simplyvaldo.mylogins.Interfaces.FragmentCategoriesToActivityListener;
import com.lasanimas.simplyvaldo.mylogins.Interfaces.FragmentLoginsToActivityListener;
import com.lasanimas.simplyvaldo.mylogins.Interfaces.RecyclerViewCategoriesToFragmentListener;
import com.lasanimas.simplyvaldo.mylogins.Interfaces.RecyclerViewLoginsToFragmentListener;
import com.lasanimas.simplyvaldo.mylogins.Model.CategoriesHelper;
import com.lasanimas.simplyvaldo.mylogins.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class categories extends Fragment implements RecyclerViewCategoriesToFragmentListener
{
    @BindView(R.id.profileName)
    TextView profileName;

    @BindView(R.id.RecyclerViewCategories)
    RecyclerView categoriesListView;

    @BindView(R.id.emptyView)
    TextView emptyView;

    private ArrayList<String> categories;
    private CategoriesHelper categoriesHelper;
    private String type;
    private String id;

    private FragmentCategoriesToActivityListener myListener;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        Bundle bundle = this.getArguments();

        id = bundle.getString("id");

        Log.i("ID", id);
        String profilename = bundle.getString("profileName");
        myRef = database.getReference("Profiles/" + id + "/categories");
        profileName.setText(profilename);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        categoriesListView.setHasFixedSize(true);
        categoriesListView.setLayoutManager(layoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(categoriesListView.getContext(), layoutManager.getOrientation());
        categoriesListView.addItemDecoration(mDividerItemDecoration);

        getCategories();
        getCategoriesFromDatabase();
    }

    public void getCategories()
    {
        categories = new ArrayList<String>(){{
            add("EMAIL"); add("SOCIAL"); add("MEMBERSHIP");
            add("MEDIA"); add("BANK"); add("DEVICES"); add("OTHER");
        }};

        RecyclerViewAdapterCategories adapter = new RecyclerViewAdapterCategories(getActivity(), categories, categories.this);
        categoriesListView.setAdapter(adapter);
    }
    public void getCategoriesFromDatabase()
    {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                categoriesHelper = dataSnapshot.getValue(CategoriesHelper.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("DataBaseError", databaseError.getMessage());
                Log.i("DataBaseError", databaseError.getDetails());
            }
        });
    }

    @Override
    public void sendCategory(String category) {

        if(categoriesHelper != null)
        {
            switch(category)
            {
                case "EMAIL":

                    if (categoriesHelper.getEmail() != null)
                        setSpecificCategoryAdapter(categoriesHelper.getEmail());
                    else {
                        categoriesListView.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                    }

                    type = "email";
                    break;
                case "SOCIAL":

                    if (categoriesHelper.getSocial() != null)
                        setSpecificCategoryAdapter(categoriesHelper.getSocial());
                    else {
                        categoriesListView.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                    }

                    type = "social";
                    break;
                case "MEMBERSHIP":

                    if (categoriesHelper.getMembership() != null)
                        setSpecificCategoryAdapter(categoriesHelper.getMembership());
                    else {
                        categoriesListView.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                    }

                    type = "membership";
                    break;
                case "MEDIA":

                    if (categoriesHelper.getMedia() != null)
                        setSpecificCategoryAdapter(categoriesHelper.getMedia());
                    else {
                        categoriesListView.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                    }

                    type = "media";
                    break;
                case "BANK":

                    if (categoriesHelper.getBank() != null)
                        setSpecificCategoryAdapter(categoriesHelper.getBank());
                    else {
                        categoriesListView.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                    }

                    type = "bank";
                    break;
                case "DEVICES":

                    if (categoriesHelper.getDevices() != null)
                        setSpecificCategoryAdapter(categoriesHelper.getDevices());
                    else {
                        categoriesListView.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                    }

                    type = "devices";
                    break;
                case "OTHER":

                    if (categoriesHelper.getOther() != null)
                        setSpecificCategoryAdapter(categoriesHelper.getOther());
                    else {
                        categoriesListView.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                    }

                    type = "other";
                    break;
            }

            Log.i("EMPTY", "FALSE");
        }
        else
        {
            Log.i("EMPTY", "TRUE");
            categoriesListView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }

    }

    public void setSpecificCategoryAdapter(HashMap selectedCategory)
    {
        RecyclerViewAdapterCategories adapter = new RecyclerViewAdapterCategories(getActivity(), selectedCategory , categories.this);
        categoriesListView.setAdapter(adapter);
    }

    @Override
    public void SendSelectLoginInfo(String currentLoginName) {
        myListener.SendLoginName2(id, currentLoginName, type, 1);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        try {
            myListener = (FragmentCategoriesToActivityListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FragmentCategoriesToActivityListener");
        }

    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (Build.VERSION.SDK_INT < 23) {

            try {
                myListener = (FragmentCategoriesToActivityListener) activity;
            }
            catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " must implement FragmentCategoriesToActivityListener");
            }
        }
    }

}
