package com.mobquid.iitdo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobquid.iitdo.R;
import com.mobquid.iitdo.adapters.MajorItemsRecyclerViewAdapter;
import com.mobquid.iitdo.adapters.NavExpandableListAdapter;
import com.mobquid.iitdo.adapters.SlidingImageAdapter;
import com.mobquid.iitdo.adapters.SocialFeedsPagerAdapter;
import com.mobquid.iitdo.models.KeySectionsResponse;
import com.mobquid.iitdo.models.MajorItems;
import com.mobquid.iitdo.models.SliderImages;
import com.mobquid.iitdo.models.SliderImagesResponse;
import com.mobquid.iitdo.models.SuccessResponse;
import com.mobquid.iitdo.retrofit.Api;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HomeActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener, NavigationView.OnNavigationItemSelectedListener {

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.6f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;

    DrawerLayout mDrawer;
    NavigationView navigationView;

    NavExpandableListAdapter navExpandableListAdapter;
    @BindView(R.id.expandableListViewNew)
    ExpandableListView expandableListView;
    @BindView(R.id.image_slider_vp)
    ViewPager imageSliderVp;
    @BindView(R.id.image_slider_indicator)
    CirclePageIndicator imageSliderIndicator;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    private List<String> listDataHeader;
    private List<Integer> groupIcons;
    private HashMap<String, List<String>> listDataChild;

    @BindView(R.id.major_items_rv)
    RecyclerView majorItemsRv;
    List<MajorItems> majorItemsList = new ArrayList<>();

    @BindView(R.id.social_feeds_vp)
    ViewPager socialFeedsVp;
    @BindView(R.id.social_feeds_tl)
    TabLayout socialFeedsTl;
    SocialFeedsPagerAdapter socialFeedsPagerAdapter;
    List<SliderImages> sliderImagesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bindActivity();
        ButterKnife.bind(this);


        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.color_white));
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        mAppBarLayout.addOnOffsetChangedListener(this);
        populateExpandableList();
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);
        setHomeScreenData();
    }

    private void bindActivity() {
        mToolbar        = (Toolbar) findViewById(R.id.main_toolbar);
        mTitle          = (TextView) findViewById(R.id.main_textview_title);
        mTitleContainer = (LinearLayout) findViewById(R.id.main_linearlayout_title);
        mAppBarLayout   = (AppBarLayout) findViewById(R.id.main_appbar);
        mDrawer         = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView  = (NavigationView) findViewById(R.id.nav_view);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {
            if(!mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }
        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if(mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }
        } else {
            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    private void populateExpandableList() {

        prepareMenuData();
        navExpandableListAdapter = new NavExpandableListAdapter(this, listDataHeader, listDataChild, groupIcons);
        expandableListView.setAdapter(navExpandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                switch (groupPosition) {
                    case 0:
                        mDrawer.closeDrawer(GravityCompat.START);
                        break;
                    case 8:
                        Intent contactIntent = new Intent(getApplicationContext(), ContactActivity.class);
                        startActivity(contactIntent);
                        break;
                }
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                switch (groupPosition) {
                    case (1):
                        if (childPosition == 0) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "about");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_about_1));
                            bundle.putInt("subcategoryat", 0);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 1) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "about");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_about_2));
                            bundle.putInt("subcategoryat", 1);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 2) {
                            Intent membersIntent = new Intent(getApplicationContext(), MembersActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "about");
                            bundle.putString("subcategory", "Patrons, Advisors & Chairman");
                            bundle.putInt("subcategoryat", 2);
                            membersIntent.putExtras(bundle);
                            startActivity(membersIntent);
                        } else if (childPosition == 3) {
                            Intent membersIntent = new Intent(getApplicationContext(), MembersActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "about");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_about_4));
                            bundle.putInt("subcategoryat", 3);
                            membersIntent.putExtras(bundle);
                            startActivity(membersIntent);
                        } else if (childPosition == 4) {
                            Intent membersIntent = new Intent(getApplicationContext(), MembersActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "about");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_about_5));
                            bundle.putInt("subcategoryat", 3);
                            membersIntent.putExtras(bundle);
                            startActivity(membersIntent);
                        }
                        break;
                    case (2):
                        if (childPosition == 0) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "sectors");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_sectors_1));
                            bundle.putInt("subcategoryat", 0);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 1) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "sectors");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_sectors_2));
                            bundle.putInt("subcategoryat", 1);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 2) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "sectors");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_sectors_3));
                            bundle.putInt("subcategoryat", 2);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 3) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "sectors");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_sectors_4));
                            bundle.putInt("subcategoryat", 3);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 4) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "sectors");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_sectors_5));
                            bundle.putInt("subcategoryat", 4);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 5) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "sectors");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_sectors_6));
                            bundle.putInt("subcategoryat", 5);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 6) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "sectors");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_sectors_7));
                            bundle.putInt("subcategoryat", 6);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 7) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "sectors");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_sectors_8));
                            bundle.putInt("subcategoryat", 7);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 8) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "sectors");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_sectors_9));
                            bundle.putInt("subcategoryat", 8);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 9) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "sectors");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_sectors_10));
                            bundle.putInt("subcategoryat", 9);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 10) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "sectors");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_sectors_11));
                            bundle.putInt("subcategoryat", 10);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 11) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "sectors");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_sectors_12));
                            bundle.putInt("subcategoryat", 11);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        }
                        break;
                    case (3):
                        if (childPosition == 0) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "services");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_services_1));
                            bundle.putInt("subcategoryat", 0);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 1) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "services");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_services_2));
                            bundle.putInt("subcategoryat", 1);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 2) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "services");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_services_3));
                            bundle.putInt("subcategoryat", 2);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 3) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "services");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_services_4));
                            bundle.putInt("subcategoryat", 3);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 4) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "services");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_services_5));
                            bundle.putInt("subcategoryat", 4);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 5) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "services");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_services_6));
                            bundle.putInt("subcategoryat", 5);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 6) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "services");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_services_7));
                            bundle.putInt("subcategoryat", 6);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 7) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "services");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_services_8));
                            bundle.putInt("subcategoryat", 7);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 8) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "services");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_services_9));
                            bundle.putInt("subcategoryat", 8);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 9) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "services");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_services_10));
                            bundle.putInt("subcategoryat", 9);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        }
                        break;
                    case (4):
                        if (childPosition == 0) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "international");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_international_1));
                            bundle.putInt("subcategoryat", 0);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 1) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "international");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_international_2));
                            bundle.putInt("subcategoryat", 1);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 2) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "international");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_international_3));
                            bundle.putInt("subcategoryat", 2);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 3) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "international");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_international_4));
                            bundle.putInt("subcategoryat", 3);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 4) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "international");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_international_5));
                            bundle.putInt("subcategoryat", 4);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 5) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "international");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_international_6));
                            bundle.putInt("subcategoryat", 5);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        } else if (childPosition == 6) {
                            Intent informationalIntent = new Intent(getApplicationContext(), InformationalActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "international");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_international_7));
                            bundle.putInt("subcategoryat", 6);
                            informationalIntent.putExtras(bundle);
                            startActivity(informationalIntent);
                        }
                        break;
                    case (5):
                        if (childPosition == 0) {
                            Intent eventsIntent = new Intent(getApplicationContext(), EventsActivity.class);
                            startActivity(eventsIntent);
                        } else if (childPosition == 1) {
                            Intent eventsIntent = new Intent(getApplicationContext(), EventsActivity.class);
                            startActivity(eventsIntent);
                        }
                        break;
                    case (6):
                        if (childPosition == 0) {
//                            Intent membersIntent = new Intent(getApplicationContext(), MembersActivity.class);
//                            Bundle bundle = new Bundle();
//                            bundle.putString("category", "membership");
//                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_membership_1));
//                            bundle.putInt("subcategoryat", 0);
//                            membersIntent.putExtras(bundle);
//                            startActivity(membersIntent);
                        } else if (childPosition == 1) {
                            Intent joinIntent = new Intent(getApplicationContext(), JoinIITDOActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("category", "membership");
                            bundle.putString("subcategory", getResources().getString(R.string.subcategories_membership_2));
                            bundle.putInt("subcategoryat", 1);
                            joinIntent.putExtras(bundle);
                            startActivity(joinIntent);
                        }
                        break;
                    case (7):
                        if (childPosition == 0) {
                            Intent photoGalleryIntent = new Intent(getApplicationContext(), PhotoGalleryActivity.class);
                            startActivity(photoGalleryIntent);
                        } else if (childPosition == 1) {
                            Intent newsletterIntent = new Intent(getApplicationContext(), NewsLetterActivity.class);
                            startActivity(newsletterIntent);
                        }
                }
                return false;
            }
        });

    }

    public void prepareMenuData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        groupIcons = new ArrayList<Integer>();

        groupIcons.add(R.drawable.cust_home);
        groupIcons.add(R.drawable.nav_about);
        groupIcons.add(R.drawable.nav_sectors);
        groupIcons.add(R.drawable.nav_services);
        groupIcons.add(R.drawable.nav_international);
        groupIcons.add(R.drawable.nav_events);
        groupIcons.add(R.drawable.nav_membership);
        groupIcons.add(R.drawable.nav_publications);
        groupIcons.add(R.drawable.nav_contact);

        listDataHeader.add(getResources().getString(R.string.categories_1));
        listDataHeader.add(getResources().getString(R.string.categories_2));
        listDataHeader.add(getResources().getString(R.string.categories_3));
        listDataHeader.add(getResources().getString(R.string.categories_4));
        listDataHeader.add(getResources().getString(R.string.categories_5));
        listDataHeader.add(getResources().getString(R.string.categories_6));
        listDataHeader.add(getResources().getString(R.string.categories_7));
        listDataHeader.add(getResources().getString(R.string.categories_9));
        listDataHeader.add(getResources().getString(R.string.categories_10));

        // Subcategories -- About
        List<String> about = new ArrayList<String>();
        about.add(getResources().getString(R.string.subcategories_about_1));
        about.add(getResources().getString(R.string.subcategories_about_2));
        about.add(getResources().getString(R.string.subcategories_about_3));
        about.add(getResources().getString(R.string.subcategories_about_4));
        about.add(getResources().getString(R.string.subcategories_about_5));

        // Subcategories -- Sectors
        List<String> sectors = new ArrayList<String>();
        sectors.add(getResources().getString(R.string.subcategories_sectors_1));
        sectors.add(getResources().getString(R.string.subcategories_sectors_2));
        sectors.add(getResources().getString(R.string.subcategories_sectors_3));
        sectors.add(getResources().getString(R.string.subcategories_sectors_4));
        sectors.add(getResources().getString(R.string.subcategories_sectors_5));
        sectors.add(getResources().getString(R.string.subcategories_sectors_6));
        sectors.add(getResources().getString(R.string.subcategories_sectors_7));
        sectors.add(getResources().getString(R.string.subcategories_sectors_8));
        sectors.add(getResources().getString(R.string.subcategories_sectors_9));
        sectors.add(getResources().getString(R.string.subcategories_sectors_10));
        sectors.add(getResources().getString(R.string.subcategories_sectors_11));
        sectors.add(getResources().getString(R.string.subcategories_sectors_12));

        // Subcategories -- Services
        List<String> services = new ArrayList<String>();
        services.add(getResources().getString(R.string.subcategories_services_1));
        services.add(getResources().getString(R.string.subcategories_services_2));
        services.add(getResources().getString(R.string.subcategories_services_3));
        services.add(getResources().getString(R.string.subcategories_services_4));
        services.add(getResources().getString(R.string.subcategories_services_5));
        services.add(getResources().getString(R.string.subcategories_services_6));
        services.add(getResources().getString(R.string.subcategories_services_7));
        services.add(getResources().getString(R.string.subcategories_services_8));
        services.add(getResources().getString(R.string.subcategories_services_9));
        services.add(getResources().getString(R.string.subcategories_services_10));

        // Subcategories -- International
        List<String> international = new ArrayList<String>();
        international.add(getResources().getString(R.string.subcategories_international_1));
        international.add(getResources().getString(R.string.subcategories_international_2));
        international.add(getResources().getString(R.string.subcategories_international_3));
        international.add(getResources().getString(R.string.subcategories_international_4));
        international.add(getResources().getString(R.string.subcategories_international_5));
        international.add(getResources().getString(R.string.subcategories_international_6));
        international.add(getResources().getString(R.string.subcategories_international_7));

        // Subcategories -- Events
        List<String> events = new ArrayList<String>();
        events.add(getResources().getString(R.string.subcategories_events_1));
        events.add(getResources().getString(R.string.subcategories_events_2));

        // Subcategories -- Membership
        List<String> membership = new ArrayList<String>();
        membership.add(getResources().getString(R.string.subcategories_membership_1));
        membership.add(getResources().getString(R.string.subcategories_membership_2));

        // Subcategories -- Publications
        List<String> publications = new ArrayList<String>();
        publications.add(getResources().getString(R.string.subcategories_publications_1));
        publications.add(getResources().getString(R.string.subcategories_publications_4));

        listDataChild.put(listDataHeader.get(0), new ArrayList<String>());
        listDataChild.put(listDataHeader.get(1), about);
        listDataChild.put(listDataHeader.get(2), sectors);
        listDataChild.put(listDataHeader.get(3), services);
        listDataChild.put(listDataHeader.get(4), international);
        listDataChild.put(listDataHeader.get(5), events);
        listDataChild.put(listDataHeader.get(6), membership);
        listDataChild.put(listDataHeader.get(7), publications);
        listDataChild.put(listDataHeader.get(8), new ArrayList<String>());
    }

    public void setHomeScreenData() {
    // Part-1 :: Automatic Image Slider starts
//        List<String> imagesList = new ArrayList<>();
//        imagesList.add("https://picsum.photos/id/1011/640/400");
//        imagesList.add("https://picsum.photos/id/1001/640/400");
//        imagesList.add("https://picsum.photos/id/1002/640/400");
//        imagesList.add("https://picsum.photos/id/1003/640/400");
//        imagesList.add("https://picsum.photos/id/1004/640/400");
//        imagesList.add("https://picsum.photos/id/1005/640/400");

        fetchSliderImages();


    // Part-1 :: Automatic Image Slider ends

    // Part-2 :: Major Items RecyclerView starts
        fetchKeySections();
    // Part-2 :: Major Items RecyclerView ends

    // Part-3 :: Social Media Feeds starts
        socialFeedsTl.addTab(socialFeedsTl.newTab().setText("Twitter Feeds"));
        socialFeedsTl.addTab(socialFeedsTl.newTab().setText("Facebook Feeds"));
        socialFeedsTl.setTabGravity(TabLayout.GRAVITY_FILL);

        socialFeedsPagerAdapter = new SocialFeedsPagerAdapter(getSupportFragmentManager(), socialFeedsTl.getTabCount());
        socialFeedsVp.setAdapter(socialFeedsPagerAdapter);
        socialFeedsTl.setupWithViewPager(socialFeedsVp);
    // Part-3 :: Social Media Feeds ends

    }

    public void fetchKeySections() {
        Api.getClient().getKeySections(new Callback<KeySectionsResponse>() {
            @Override
            public void success(KeySectionsResponse keySectionsResponse, Response response) {
                majorItemsList = keySectionsResponse.getMajorItems();
                MajorItemsRecyclerViewAdapter majorItemsRecyclerViewAdapter = new MajorItemsRecyclerViewAdapter(getApplicationContext(), majorItemsList);
                majorItemsRv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
                majorItemsRv.setAdapter(majorItemsRecyclerViewAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("apihome1", error.toString());
            }
        });
    }

    public void fetchSliderImages() {
        Api.getClient().getSliderImages(new Callback<SliderImagesResponse>() {
            @Override
            public void success(SliderImagesResponse sliderImagesResponse, Response response) {
                sliderImagesList = sliderImagesResponse.getSliderImagesList();
                System.out.println("sliderImagesList :: "+sliderImagesList.size());
                imageSliderVp.setAdapter(new SlidingImageAdapter(sliderImagesList, getApplicationContext()));
                imageSliderIndicator.setViewPager(imageSliderVp);

                final float density = getResources().getDisplayMetrics().density;
                //Set circle indicator radius
                imageSliderIndicator.setRadius(4 * density);
                NUM_PAGES = sliderImagesList.size();
                // Auto start of viewpager
                final Handler handler = new Handler();
                final Runnable Update = new Runnable() {
                    public void run() {
                        if (currentPage == NUM_PAGES) {
                            currentPage = 0;
                        }
                        imageSliderVp.setCurrentItem(currentPage++, true);
                    }
                };
                Timer swipeTimer = new Timer();
                swipeTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(Update);
                    }
                }, 3000, 3000);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("apihome2", error.toString());
            }
        });
    }
}
