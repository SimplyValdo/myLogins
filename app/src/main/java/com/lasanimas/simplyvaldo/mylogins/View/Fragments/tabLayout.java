package com.lasanimas.simplyvaldo.mylogins.View.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lasanimas.simplyvaldo.mylogins.Adapter.PagerAdapter;
import com.lasanimas.simplyvaldo.mylogins.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class tabLayout extends Fragment
{
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    FragmentManager fragmentManager;
    PagerAdapter adapter;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tabs, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        fragmentManager = getFragmentManager();
        bundle = this.getArguments();

        setPageAdapter();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0 && fragmentManager.findFragmentByTag("viewLoginDetails") != null)
                {
                    fragmentManager.popBackStack();
                    adapter = new PagerAdapter(getChildFragmentManager(), tabLayout.getTabCount(), bundle);
                }
            }
        });
    }

    public void setPageAdapter()
    {
       if (fragmentManager.findFragmentByTag("viewLoginDetails") != null)
           bundle.putString("Layout","viewLoginDetails");
       else
           bundle.putString("Layout","Default");

        TabLayout.Tab firstTab = tabLayout.newTab();
        tabLayout.addTab(firstTab);

        TabLayout.Tab secondTab = tabLayout.newTab();
        tabLayout.addTab(secondTab);

        adapter = new PagerAdapter(getChildFragmentManager(), tabLayout.getTabCount(), bundle);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapter);
    }
}
