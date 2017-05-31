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

import com.lasanimas.simplyvaldo.mylogins.Adapter.RecyclerViewAdapterFavorites;
import com.lasanimas.simplyvaldo.mylogins.Model.BankDB;
import com.lasanimas.simplyvaldo.mylogins.Model.CategoriesHelper;
import com.lasanimas.simplyvaldo.mylogins.Model.DevicesDB;
import com.lasanimas.simplyvaldo.mylogins.Model.EmailDB;
import com.lasanimas.simplyvaldo.mylogins.Model.MediaDB;
import com.lasanimas.simplyvaldo.mylogins.Model.MembershipDB;
import com.lasanimas.simplyvaldo.mylogins.Model.OtherDB;
import com.lasanimas.simplyvaldo.mylogins.Model.SocialDB;
import com.lasanimas.simplyvaldo.mylogins.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;


public class favorites extends Fragment
{
    @BindView(R.id.emptyViewFavorites)
    TextView emptyViewFavorites;

    @BindView(R.id.RecyclerViewFavorites)
    RecyclerView RecyclerViewFavorites;


    private RecyclerViewAdapterFavorites adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerViewFavorites.setHasFixedSize(true);
        RecyclerViewFavorites.setLayoutManager(layoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(RecyclerViewFavorites.getContext(), layoutManager.getOrientation());
        RecyclerViewFavorites.addItemDecoration(mDividerItemDecoration);

        CategoriesHelper categoriesHelper = Paper.book().read("categories");
        List<Boolean> isFavorite = Paper.book().read("favorites");

        ArrayList<String> logins = new ArrayList();

        if(areAllFalse(isFavorite))
        {
            if (categoriesHelper.getEmail() != null)
            {
                for (Map.Entry<String, EmailDB> entry : categoriesHelper.getEmail().entrySet()) {
                    logins.add(entry.getKey());
                }
            }

            if(categoriesHelper.getSocial() != null)
            {
                for (Map.Entry<String, SocialDB> entry : categoriesHelper.getSocial().entrySet()) {
                    logins.add(entry.getKey());
                }
            }

            if( categoriesHelper.getMembership() != null)
            {
                for (Map.Entry<String, MembershipDB> entry : categoriesHelper.getMembership().entrySet()) {
                    logins.add(entry.getKey());
                }
            }

            if(categoriesHelper.getMedia() != null)
            {
                for (Map.Entry<String, MediaDB> entry : categoriesHelper.getMedia().entrySet()) {
                    logins.add(entry.getKey());
                }
            }

            if(categoriesHelper.getBank() != null)
            {
                for (Map.Entry<String, BankDB> entry : categoriesHelper.getBank().entrySet()) {
                    logins.add(entry.getKey());
                }
            }

            if(categoriesHelper.getDevices() != null)
            {
                for (Map.Entry<String, DevicesDB> entry : categoriesHelper.getDevices().entrySet()) {
                    logins.add(entry.getKey());
                }
            }

            if(categoriesHelper.getOther() != null)
            {
                for (Map.Entry<String, OtherDB> entry : categoriesHelper.getOther().entrySet()) {
                    logins.add(entry.getKey());
                }
            }

            Log.i("EMPTY", "FALSE");
        }
        else
        {
            RecyclerViewFavorites.setVisibility(View.GONE);
            emptyViewFavorites.setVisibility(View.VISIBLE);
            Log.i("EMPTY", "TRUE");
        }



        adapter = new RecyclerViewAdapterFavorites(getActivity(), logins, isFavorite, categoriesHelper);
        RecyclerViewFavorites.setAdapter(adapter);

    }

    public Boolean areAllFalse(List<Boolean> list)
    {
        if(list != null)
        {
            for(Boolean b : list) if(b) return true;
            return false;
        }

        return false;
    }


}
