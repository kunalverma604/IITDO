package com.mobquid.iitdo.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.fragments.BoardMembersFragment;
import com.mobquid.iitdo.fragments.BoardsFragment;
import com.mobquid.iitdo.fragments.PastEventsFragment;
import com.mobquid.iitdo.fragments.PatronsFragment;
import com.mobquid.iitdo.fragments.UpcomingEventsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KeyPeopleActivity extends AppCompatActivity {

    @BindView(R.id.key_people_tl)
    TabLayout keyPeopleTl;
    @BindView(R.id.key_people_vp)
    ViewPager keyPeopleVp;
    KeyPeoplePagerAdapter keyPeoplePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Key People");
        setContentView(R.layout.activity_key_people);
        ButterKnife.bind(this);
        keyPeopleTl.addTab(keyPeopleTl.newTab().setText("Patrons, Advisors & Chairman"));
        keyPeopleTl.addTab(keyPeopleTl.newTab().setText("Board Members"));
        keyPeopleTl.addTab(keyPeopleTl.newTab().setText("IITDO Broads"));
        keyPeopleTl.setTabGravity(TabLayout.GRAVITY_FILL);

        keyPeoplePagerAdapter = new KeyPeoplePagerAdapter(keyPeopleTl.getTabCount(), getSupportFragmentManager());
        keyPeopleVp.setAdapter(keyPeoplePagerAdapter);
        keyPeopleTl.setupWithViewPager(keyPeopleVp);
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    public class KeyPeoplePagerAdapter extends FragmentStatePagerAdapter {

        int tabCount;
        FragmentManager fragmentManager;

        public KeyPeoplePagerAdapter(int tabCount, FragmentManager fragmentManager) {
            super(fragmentManager);
            this.tabCount = tabCount;
            this.fragmentManager = fragmentManager;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return "Patrons, Advisors & Chairman";
                case 1: return "Board Members";
                case 2: return "IITDO Broads";
                default: return null;
            }
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    PatronsFragment tab1 = new PatronsFragment();
                    return tab1;
                case 1:
                    BoardMembersFragment tab2 = new BoardMembersFragment();
                    return tab2;
                case 2:
                    BoardsFragment tab3 = new BoardsFragment();
                    return tab3;
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
