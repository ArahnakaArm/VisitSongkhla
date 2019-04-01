package com.example.deimos.visitsongkhla;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import java.util.Locale;

public class Language extends AppCompatActivity implements View.OnClickListener{

    private CardView Lan1, Lan2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language);

        Lan1 = (CardView)findViewById(R.id.LanTH);
        Lan2 = (CardView)findViewById(R.id.LanEN);


        Lan1.setOnClickListener(this);
        Lan2.setOnClickListener(this);

    }
    public void onClick(View view) {
        if (view == Lan1) {
            setLocate("th");
            recreate();
            startActivity(new Intent(this, Home.class));
        }
        if (view == Lan2) {
            setLocate("en");
            recreate();
            startActivity(new Intent(this, Home.class));
        }
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
    //load Lan //
    public  void loadLocale(){
        SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = preferences.getString("My_Lang","");
        setLocate(language);
    }
}
