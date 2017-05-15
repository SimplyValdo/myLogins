package com.example.simplyvaldo.mylogins.View.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.simplyvaldo.mylogins.Adapter.PagerAdapter;
import com.example.simplyvaldo.mylogins.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class tabLayout extends Fragment
{
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tabs, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        Bundle bundle = this.getArguments();

        TabLayout.Tab firstTab = tabLayout.newTab();
        tabLayout.addTab(firstTab);

        TabLayout.Tab secondTab = tabLayout.newTab();
        tabLayout.addTab(secondTab);

        PagerAdapter adapter = new PagerAdapter(getFragmentManager(), tabLayout.getTabCount(), bundle);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);


    }
}
