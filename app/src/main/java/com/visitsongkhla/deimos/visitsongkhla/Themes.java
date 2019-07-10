package com.visitsongkhla.deimos.visitsongkhla;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;


import com.google.android.material.tabs.TabLayout;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class Themes extends AppCompatActivity implements ArticalTab.OnFragmentInteractionListener,HistiricalTab.OnFragmentInteractionListener,NeturalTab.OnFragmentInteractionListener {
    private static final int ACTIVITY_NUM=1;
    private Check_internet check_internet;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themes);
        check_connection();
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
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //setNavi();
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.TH1)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.TH2)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.TH3)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapterThemes adapter = new PagerAdapterThemes(getSupportFragmentManager(),tabLayout.getTabCount());
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
        BottomNavigationViewHelper.enableNavigation(Themes.this,bottomNavigationViewEx);
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
