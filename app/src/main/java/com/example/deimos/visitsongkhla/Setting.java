package com.example.deimos.visitsongkhla;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

public class Setting extends AppCompatActivity implements View.OnClickListener {

    private TextView textTitle1, textTitle2, textTitle3, textTitle4 ,textTitle5;
    private CardView Card1, Card2, Card3, Card4, Card5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        textTitle1 = (TextView)findViewById(R.id.textTitle1);
        textTitle1.setOnClickListener(this);
        Card1 = (CardView)findViewById(R.id.Card1);
        Card1.setOnClickListener(this);

        textTitle2 = (TextView)findViewById(R.id.textTitle2);
        Card2 = (CardView)findViewById(R.id.Card2);
        Card2.setOnClickListener(this);

        textTitle3 = (TextView)findViewById(R.id.textTitle3);
        textTitle3.setOnClickListener(this);
        Card3 = (CardView)findViewById(R.id.Card3);
        Card3.setOnClickListener(this);

        textTitle4 = (TextView)findViewById(R.id.textTitle4);
        textTitle4.setOnClickListener(this);
        Card4 = (CardView)findViewById(R.id.Card4);
        Card4.setOnClickListener(this);

        /*textTitle5 = (TextView)findViewById(R.id.textTitle5);
        textTitle5.setOnClickListener(this);
        Card5 = (CardView)findViewById(R.id.Card5);
        Card5.setOnClickListener(this);
        if(StatusCheck.getStatus()==false){
            Card5.setVisibility(View.INVISIBLE);
            textTitle5.setVisibility(View.INVISIBLE);
        }*/
    }

    public void onClick(View view) {
        if (view == textTitle1) {
            startActivity(new Intent(this, Language.class));
        }
        if (view == Card1) {
            startActivity(new Intent(this, Language.class));
        }

        if (view == textTitle2) {
            //startActivity(new Intent(this, PattaniActivity.class));
        }
        if (view == Card2) {
            //startActivity(new Intent(this, PattaniActivity.class));
        }

        if (view == textTitle3) {
            //startActivity(new Intent(this, Agree2Activity.class));
        }
        if (view == Card3) {
            //startActivity(new Intent(this, Agree2Activity.class));
        }

        if (view == textTitle4) {
            startActivity(new Intent(this, Version.class));
        }
        if (view == Card4) {
            startActivity(new Intent(this, Version.class));
        }

        if (view == textTitle5) {
            //StatusCheck.nologin();
            //startActivity(new Intent(this, LoginActivity.class));
        }
        if (view == Card5) {
            //StatusCheck.nologin();
            //startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
