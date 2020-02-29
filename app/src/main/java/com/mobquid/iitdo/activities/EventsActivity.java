package com.mobquid.iitdo.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.adapters.SocialFeedsPagerAdapter;
import com.mobquid.iitdo.fragments.PastEventsFragment;
import com.mobquid.iitdo.fragments.UpcomingEventsFragment;
import com.mobquid.iitdo.models.Events;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsActivity extends AppCompatActivity {

    @BindView(R.id.events_tl)
    TabLayout eventsTl;
    @BindView(R.id.events_vp)
    ViewPager eventsVp;
    EventsPagerAdapter eventsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Events");
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);
        eventsTl.addTab(eventsTl.newTab().setText("Upcoming Events"));
        eventsTl.addTab(eventsTl.newTab().setText("Past Events"));
        eventsTl.setTabGravity(TabLayout.GRAVITY_FILL);

        eventsPagerAdapter = new EventsPagerAdapter(eventsTl.getTabCount(), getSupportFragmentManager());
        eventsVp.setAdapter(eventsPagerAdapter);
        eventsTl.setupWithViewPager(eventsVp);
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    public class EventsPagerAdapter extends FragmentStatePagerAdapter {

        int tabCount;
        FragmentManager fragmentManager;

        public EventsPagerAdapter(int tabCount, FragmentManager fragmentManager) {
            super(fragmentManager);
            this.tabCount = tabCount;
            this.fragmentManager = fragmentManager;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return "Upcoming Events";
                case 1: return "Past Events";
                default: return null;
            }
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    UpcomingEventsFragment tab1 = new UpcomingEventsFragment();
                    return tab1;
                case 1:
                    PastEventsFragment tab2 = new PastEventsFragment();
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

}
