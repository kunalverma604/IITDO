package com.mobquid.iitdo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mobquid.iitdo.fragments.FacebookFeedFragment;
import com.mobquid.iitdo.fragments.TwitterFeedsFragment;

public class SocialFeedsPagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;
    FragmentManager fragmentManager;

    public SocialFeedsPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount= tabCount;
        this.fragmentManager = fm;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Twitter Feeds";
            case 1: return "Facebook Feeds";
            default: return null;
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TwitterFeedsFragment tab1 = new TwitterFeedsFragment();
                return tab1;
            case 1:
                FacebookFeedFragment tab2 = new FacebookFeedFragment();
                return tab2;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }

}
