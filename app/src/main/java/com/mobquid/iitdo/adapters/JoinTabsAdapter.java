package com.mobquid.iitdo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mobquid.iitdo.fragments.FragmentJoinForm;
import com.mobquid.iitdo.fragments.JoinAboutFragment;
import com.mobquid.iitdo.fragments.JoinBrochureFragment;
import com.mobquid.iitdo.fragments.JoinPackagesFragment;

public class JoinTabsAdapter extends FragmentStatePagerAdapter {

    int tabs;

    public JoinTabsAdapter(FragmentManager fm, int tabs){
        super(fm);
        this.tabs = tabs;
    }
    @Override
    public int getCount() {
        return tabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                JoinAboutFragment aboutFragment = new JoinAboutFragment();
                return aboutFragment;
            case 1:
                JoinPackagesFragment benefitsFragment = new JoinPackagesFragment();
                return benefitsFragment;
            case 2:
                FragmentJoinForm membershipFormFragment = new FragmentJoinForm();
                return membershipFormFragment;
            default:
                return null;
        }
    }

}
