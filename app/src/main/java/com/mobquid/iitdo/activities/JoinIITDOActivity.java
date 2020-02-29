package com.mobquid.iitdo.activities;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.adapters.JoinTabsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JoinIITDOActivity extends AppCompatActivity {

    @BindView(R.id.tablayout_join_iitdo)
    TabLayout tablayoutJoinIitdo;
    @BindView(R.id.viewpager_join_iitdo)
    ViewPager viewpagerJoinIitdo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Join IITDO");
        setContentView(R.layout.activity_join_iitdo);
        ButterKnife.bind(this);

        tablayoutJoinIitdo.addTab(tablayoutJoinIitdo.newTab().setText("About"));
        tablayoutJoinIitdo.addTab(tablayoutJoinIitdo.newTab().setText("Packages"));
        tablayoutJoinIitdo.addTab(tablayoutJoinIitdo.newTab().setText("Membership Form"));

        JoinTabsAdapter joinTabsAdapter = new JoinTabsAdapter(getSupportFragmentManager(), tablayoutJoinIitdo.getTabCount());
        viewpagerJoinIitdo.setAdapter(joinTabsAdapter);
        viewpagerJoinIitdo.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayoutJoinIitdo));
        tablayoutJoinIitdo.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpagerJoinIitdo.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
