package com.visitsongkhla.deimos.visitsongkhla;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.Locale;

public class MoreRestaurants extends AppCompatActivity implements CommonTab.OnFragmentInteractionListener,HaralTab.OnFragmentInteractionListener {
    private static final int ACTIVITY_NUM=1;
    Check_internet check_internet;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
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
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Intent intent1 = new Intent(getApplicationContext(), Home.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent1);
                        break;
                    case R.id.travelinfo:
                        Intent intent2 = new Intent(getApplicationContext(), com.visitsongkhla.deimos.visitsongkhla.Menu.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        break;
                    case R.id.checkinnavi:
                        Intent intent3 = new Intent(getApplicationContext(), CheckIn.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);                         startActivity(intent3);
                        Toast.makeText(getApplicationContext(), "Nearby", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.map:
                        Toast.makeText(getApplicationContext(), "Nearby", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        android.view.Menu menu =bottomNavigationView.getMenu();
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
    public  void loadLocale(){
        SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = preferences.getString("My_Lang","");
        setLocate(language);
    }
    private void setLocate(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        //Save Lan//
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

}
