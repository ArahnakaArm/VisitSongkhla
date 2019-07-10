package com.visitsongkhla.deimos.visitsongkhla;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by arahnaka on 6/17/2018.
 */

public class GalleryProduct extends AppCompatActivity{
    TextView gps;
    int id;
    String IdString;
    static String image1="",image2="",image3="",image4="",image5="",CategoryTab;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallerynomoreimage);
        CategoryTab = getIntent().getStringExtra("Category");
        gps = findViewById(R.id.googlemap);
        getIncomingIntent();



    }
    private void getIncomingIntent(){

        String imageUrl= getIntent().getStringExtra("image_url");

        String imageName= getIntent().getStringExtra("image_name");
        String des = getIntent().getStringExtra("Des").replace("_b","\n");
        String tel = getIntent().getStringExtra("Tel");
        String location = getIntent().getStringExtra("Local");
        String lat = getIntent().getStringExtra("Lat");
        String lng = getIntent().getStringExtra("Lng");
        id = getIntent().getIntExtra("Id",0);

        IdString = Integer.toString(id);
         /*    image1 = getIntent().getStringExtra("MoreImage1");
             image2 = getIntent().getStringExtra("MoreImage2");
             image3 = getIntent().getStringExtra("MoreImage3");
             image4 = getIntent().getStringExtra("MoreImage4");
             image5 = getIntent().getStringExtra("MoreImage5");*/
        ArrayList<String> image = getIntent().getStringArrayListExtra("ImageMore");
        final Bundle bundle = new Bundle();


        setImage(imageUrl,imageName,des,tel,location,lat,lng,image);

    }
    private void setImage(String imageUrl, final String imageName, String des, String tel, String location, final String lat, final String lng
            ,ArrayList<String>imagemore){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(imageName);
        //  TextView GelleryText = findViewById(R.id.gallerytext);
        //  GelleryText.setPaintFlags(GelleryText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference ImageRef;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mFirebaseDatabase.getReference(CategoryTab).child("ImageMore").child(IdString);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                image1 = dataSnapshot.child("Image1").getValue(String.class);
                image2 = dataSnapshot.child("Image2").getValue(String.class);
                image3 = dataSnapshot.child("Image3").getValue(String.class);
                image4 = dataSnapshot.child("Image4").getValue(String.class);
                image5 = dataSnapshot.child("Image5").getValue(String.class);

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
      /*  GelleryText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goGalleryMore = new Intent(GalleryNomoreplace.this,ImageGallery.class);
                goGalleryMore.putExtra("Name",imageName);
                goGalleryMore.putExtra("UrlList1",image1);
                goGalleryMore.putExtra("UrlList2",image2);
                goGalleryMore.putExtra("UrlList3",image3);
                goGalleryMore.putExtra("UrlList4",image4);
                goGalleryMore.putExtra("UrlList5",image5);
                startActivity(goGalleryMore);
            }
        });/*/
        TextView Location = findViewById(R.id.locationtext);
        Location.setText(" "+location);
        TextView TelText = findViewById(R.id.teltext);
        TelText.setText(" "+tel);
        TextView googleMap =findViewById(R.id.googlemap);
        TelText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tel =getIntent().getStringExtra("Tel");
                Intent i=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+tel));
                startActivity(i);
            }
        });

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
        TextView descri = findViewById(R.id.description);
        //  TextView tele =findViewById(R.id.tel);
        //TextView location = findViewById(R.id.location_input);
        // tele.setText(tel);
        // location.setText(locate);
        descri.setText(des);
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
