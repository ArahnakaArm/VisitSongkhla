package com.example.deimos.visitsongkhla;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by arahnaka on 6/17/2018.
 */

public class Gallery extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        getIncomingIntent();

    }
    private void getIncomingIntent(){

        String imageUrl= getIntent().getStringExtra("image_url");
        String imageName= getIntent().getStringExtra("image_name");
        String des = getIntent().getStringExtra("Des").replace("_b","\n");
        String tel = getIntent().getStringExtra("Tel");


        setImage(imageUrl,imageName,des,tel);


    }
    private void setImage(String imageUrl, String imageName, String des,String tel){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(imageName);
        TextView TelText = findViewById(R.id.teltext);
        TelText.setText(tel);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // TextView name = findViewById(R.id.toolbar);
        TextView descri = findViewById(R.id.description);
        //  TextView tele =findViewById(R.id.tel);
        //TextView location = findViewById(R.id.location_input);
        // tele.setText(tel);
        // location.setText(locate);
        descri.setText("  "+des);
        // name.setText(imageName);
        ImageView image=findViewById(R.id.imagegall);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);
    }
}
