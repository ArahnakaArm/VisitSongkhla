package com.example.deimos.visitsongkhla;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Gallery_Helps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery__helps);
        getIncomingIntent();
    }
    private void getIncomingIntent(){

        String imageUrl= getIntent().getStringExtra("image_url");
        String imageName= getIntent().getStringExtra("image_name");

        String tel = getIntent().getStringExtra("Tel");
        String location = getIntent().getStringExtra("Local");
        String lat = getIntent().getStringExtra("Lat");
        String lng = getIntent().getStringExtra("Lng");
        final String image1 = getIntent().getStringExtra("MoreImage1");
        final String image2 = getIntent().getStringExtra("MoreImage2");
        final String image3 = getIntent().getStringExtra("MoreImage3");
        final String image4 = getIntent().getStringExtra("MoreImage4");
        final String image5 = getIntent().getStringExtra("MoreImage5");
        ArrayList<String> image = getIntent().getStringArrayListExtra("ImageMore");
        String link = getIntent().getStringExtra("Link");
        final Bundle bundle = new Bundle();



        setImage(imageUrl,imageName,tel,location,lat,lng,image1,image2,image3,image4,image5,image,link);


    }
    private void setImage(final String imageUrl, final String imageName, String tel, String location, final String lat, final String lng
            , final String image1, final String image2, final String image3, final String image4, final String image5, ArrayList<String>imagemore, final String link){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(imageName);
        TextView GelleryText = findViewById(R.id.Link);
        GelleryText.setPaintFlags(GelleryText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        GelleryText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse(link.toString());
                Intent goLink = new Intent(Intent.ACTION_VIEW,webpage);
                startActivity(goLink);
            }
        });
        TextView Location = findViewById(R.id.locationtext);
        Location.setText(" "+location);
        TextView TelText = findViewById(R.id.teltext);
        TelText.setText(" "+tel);
        TextView googleMap =findViewById(R.id.googlemap);

        googleMap.setPaintFlags(googleMap.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        googleMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:"+lat+","+lng+"?q="+imageName);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // TextView name = findViewById(R.id.tooalbar);

        //  TextView tele =findViewById(R.id.tel);
        //TextView location = findViewById(R.id.location_input);
        // tele.setText(tel);
        // location.setText(locate);

        // name.setText(imageName);
        RequestOptions options = new RequestOptions();
        options.fitCenter();
        ImageView image=findViewById(R.id.imagegall);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
