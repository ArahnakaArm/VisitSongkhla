package com.visitsongkhla.deimos.visitsongkhla;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.Locale;

public class Menu extends AppCompatActivity {
    ViewFlipper viewFlipper;
    private static final int ACTIVITY_NUM=1;

    LinearLayout goResAc,goHotel,goDiary,goThemes,goProduct,goHighlight,goNew,goService,goPackage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.menu);
        setNavi();
        int images[]={R.drawable.head2,R.drawable.navel,R.drawable.tail2};
        viewFlipper = findViewById(R.id.viewflipper);

       /* for(int i=0;i<images.length;i++){
            flipperimage(images[i]);
        }*/
        for(int image:images){
            flipperimage(image);
        }
        goHighlight= findViewById(R.id.but_high);
        goHighlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goRes = new Intent(Menu.this,Highlights.class);
                startActivity(goRes);
            }
        });
        goNew= findViewById(R.id.but_news);
        goNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goRes = new Intent(Menu.this,News.class);
                startActivity(goRes);
            }
        });
        goResAc= findViewById(R.id.but_food);
        goResAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goRes = new Intent(Menu.this,ChooseFood.class);
                startActivity(goRes);
            }
        });

        goHotel = findViewById(R.id.but_hotel);
        goHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goHotel = new Intent(Menu.this,Hotel.class);
                startActivity(goHotel);
            }
        });
        goDiary = findViewById(R.id.but_diary);
        goDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goDiary = new Intent(Menu.this,Diary.class);
                startActivity(goDiary);
            }
        });
        goThemes = findViewById(R.id.but_theme);
        goThemes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goThemes = new Intent(Menu.this,ChooseThemes.class);
                startActivity(goThemes);
            }
        });

        goService = findViewById(R.id.but_service);
        goService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goThemes = new Intent(Menu.this,Helps.class);
                startActivity(goThemes);
            }
        });
        goPackage = findViewById(R.id.but_pack);
        goPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goPackage = new Intent(Menu.this,Package.class);
                startActivity(goPackage);
            }
        });

        goProduct = findViewById(R.id.but_product);
        goProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goProduct = new Intent(Menu.this,Product.class);
                startActivity(goProduct);
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
    public void flipperimage(int images){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(images);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3500);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);

    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent goSet = new Intent(Menu.this,Setting.class);
            startActivity(goSet);
            return true;
        }
        return super.onOptionsItemSelected(item);
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
