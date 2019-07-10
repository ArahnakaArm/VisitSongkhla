package com.visitsongkhla.deimos.visitsongkhla;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.util.Log;
import java.lang.reflect.Field;

import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;


import androidx.annotation.NonNull;

/**
 * Created by User on 5/28/2017.
 */

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHelp";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        Log.d(TAG, "setupBottomNavigationView: Setting up BottomNavigationView");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);

    }
    public static void enableNavigation(final Context context, BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        Intent intent1 = new Intent(context,Home.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        context.startActivity(intent1);
                        break;
                    case R.id.travelinfo:
                        Intent intent2 = new Intent(context,Menu.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        context.startActivity(intent2);
                        break;
                    /*case R.id.checkinnavi:                         Intent intent3 = new Intent(getApplicationContext(), CheckIn.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);                         startActivity(intent3);
                        Intent intent3 = new Intent(context,Checkin.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        context.startActivity(intent3);
                        break;
                    case R.id.map:
                        Intent intent4 = new Intent(context,Map.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        context.startActivity(intent4);
                        break;*/
                }
                return false;
            }
        });
    }
}