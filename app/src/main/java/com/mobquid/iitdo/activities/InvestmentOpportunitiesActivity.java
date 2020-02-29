package com.mobquid.iitdo.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.fragments.SectorWiseIOFragment;
import com.mobquid.iitdo.fragments.StateWiseIOFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InvestmentOpportunitiesActivity extends AppCompatActivity {

    InvestmentOpportunitiesAdapter investmentOpportunitiesAdapter;
    @BindView(R.id.invest_opprtunities_tl)
    TabLayout investOpprtunitiesTl;
    @BindView(R.id.invest_opprtunities_vp)
    ViewPager investOpprtunitiesVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Investment Opportunities");
        setContentView(R.layout.activity_investment_opportunities);
        ButterKnife.bind(this);
        investOpprtunitiesTl.addTab(investOpprtunitiesTl.newTab().setText("States Wise"));
        investOpprtunitiesTl.addTab(investOpprtunitiesTl.newTab().setText("Sector Wise"));
        investOpprtunitiesTl.setTabGravity(TabLayout.GRAVITY_FILL);

        investmentOpportunitiesAdapter = new InvestmentOpportunitiesAdapter(investOpprtunitiesTl.getTabCount(), getSupportFragmentManager());
        investOpprtunitiesVp.setAdapter(investmentOpportunitiesAdapter);
        investOpprtunitiesTl.setupWithViewPager(investOpprtunitiesVp);
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    public class InvestmentOpportunitiesAdapter extends FragmentStatePagerAdapter {

        int tabCount;
        FragmentManager fragmentManager;

        public InvestmentOpportunitiesAdapter(int tabCount, FragmentManager fragmentManager) {
            super(fragmentManager);
            this.tabCount = tabCount;
            this.fragmentManager = fragmentManager;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return "States Wise";
                case 1: return "Sector Wise";
                default: return null;
            }
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    StateWiseIOFragment tab1 = new StateWiseIOFragment();
                    return tab1;
                case 1:
                    SectorWiseIOFragment tab2 = new SectorWiseIOFragment();
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
