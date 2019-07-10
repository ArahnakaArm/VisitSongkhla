package com.visitsongkhla.deimos.visitsongkhla;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.Locale;

public class ChooseThemes extends AppCompatActivity {
    private static final int ACTIVITY_NUM=1;
    private CardView card1;
    private CardView card2;
    private CardView card3;
    private String chooseString = "default";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.choosethemes);
        setNavi();

        card1 = findViewById(R.id.Card1);
        card2 = findViewById(R.id.Card2);
        card3 = findViewById(R.id.Card3);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goIntent = new Intent(ChooseThemes.this,Themes.class);
                StringChooseThemes.setFisrtTheme(getString(R.string.CT1));
                startActivity(goIntent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goIntent = new Intent(ChooseThemes.this,Themes.class);
                StringChooseThemes.setFisrtTheme(getString(R.string.CT2));
                chooseString = "หาดใหญ่";
                startActivity(goIntent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goIntent = new Intent(ChooseThemes.this,Themes.class);
                StringChooseThemes.setFisrtTheme(getString(R.string.CT3));
                chooseString = "อื่น";
                startActivity(goIntent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });



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
                    case R.id.checkinnavi:                         Intent intent3 = new Intent(getApplicationContext(), CheckIn.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);                         startActivity(intent3);
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
