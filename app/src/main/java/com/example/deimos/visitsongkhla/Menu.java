package com.example.deimos.visitsongkhla;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class Menu extends AppCompatActivity {
    ViewFlipper viewFlipper;
    private static final int ACTIVITY_NUM=1;
    LinearLayout goResAc,goHotel,goDiary,goThemes;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        goResAc= findViewById(R.id.but_food);
        goResAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goRes = new Intent(Menu.this,MoreRestaurants.class);
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
                Intent goThemes = new Intent(Menu.this,Themes.class);
                startActivity(goThemes);
            }
        });
    }
    public void setNavi(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(Menu.this,bottomNavigationViewEx);
        android.view.Menu menu =bottomNavigationViewEx.getMenu();

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
}
