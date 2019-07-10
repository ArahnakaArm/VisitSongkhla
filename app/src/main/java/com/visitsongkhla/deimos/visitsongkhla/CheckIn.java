package com.visitsongkhla.deimos.visitsongkhla;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rtchagas.pingplacepicker.PingPlacePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CheckIn extends AppCompatActivity {
    private Button mPlacePicker;
    private static final int ACTIVITY_NUM=2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkin);
        setNavi();
        mPlacePicker=(Button) findViewById(R.id.button1);
        final Animation animAlpha = AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
        mPlacePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animAlpha);
                float alpha = 0.45f;
                float alpha2 = 1.00f;
                AlphaAnimation alphaUp = new AlphaAnimation(alpha, alpha2);
                alphaUp.setDuration(1000);
                alphaUp.setFillAfter(true);
                view.startAnimation(alphaUp);
                showPlacePicker();

            }


        });

    }
    private void showPlacePicker() {
        PingPlacePicker.IntentBuilder builder = new PingPlacePicker.IntentBuilder();
        builder.setAndroidApiKey("AIzaSyBg9g4o6-vVxylkWfD57rZAnLL4OPNxOb8")
                .setGeocodingApiKey("AIzaSyDKbjyDV5yZSU5YrAxDuBwaJk7TO5UzFqI");
        try {
            Intent placeIntent = builder.build(this);
            startActivityForResult(placeIntent, 1001);
        }
        catch (Exception ex) {
            // Google Play services is not available...
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 1001) && (resultCode == RESULT_OK)) {
            Place place = PingPlacePicker.Companion.getPlace(data);
            if (place != null) {
                Intent goRate = new Intent(getApplicationContext(),CheckInNearby.class);
                goRate.putExtra("placename",place.getName());
                startActivity(goRate);
              //  Toast.makeText(this, "You selected the place: " + place.getName(), Toast.LENGTH_SHORT).show();
            }
        }
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

                        Intent intent3 = new Intent(getApplicationContext(), CheckIn.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent3);
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
}