package com.example.deimos.visitsongkhla;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MoreRestaurants extends AppCompatActivity implements CommonTab.OnFragmentInteractionListener,HaralTab.OnFragmentInteractionListener {
    private static final int ACTIVITY_NUM=1;
    Check_internet check_internet;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.morerestaurants);
        setNavi();
        check_connection();
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.RE1)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.RE2)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(StringChooseThemes.getTheme() == getString(R.string.CT1)){
            toolbar.setTitle(getString(R.string.MCT1));
        }
        else if(StringChooseThemes.getTheme() == getString(R.string.CT2)){
            toolbar.setTitle(getString(R.string.MCT2));
        }
        else {
            toolbar.setTitle(getString(R.string.MCT3));
        }
        //toolbar.setTitle(StringChooseThemes.getTheme());
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavi();

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
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
    public void onFragmentInteraction(Uri uri) {

    }
    public void setNavi(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(MoreRestaurants.this,bottomNavigationViewEx);
        Menu menu =bottomNavigationViewEx.getMenu();
        MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        return true;
    }

    public void check_connection(){
        check_internet = new Check_internet(this);
        check_internet.execute();

    }

}
