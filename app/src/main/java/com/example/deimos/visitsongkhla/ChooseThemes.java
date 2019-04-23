package com.example.deimos.visitsongkhla;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class ChooseThemes extends AppCompatActivity {
    private static final int ACTIVITY_NUM=1;
    private CardView card1;
    private CardView card2;
    private CardView card3;
    private String chooseString = "default";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(ChooseThemes.this,bottomNavigationViewEx);
        android.view.Menu menu =bottomNavigationViewEx.getMenu();
        MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
