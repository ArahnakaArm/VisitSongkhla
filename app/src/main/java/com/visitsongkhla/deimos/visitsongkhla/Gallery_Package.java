package com.visitsongkhla.deimos.visitsongkhla;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by arahnaka on 6/17/2018.
 */

public class Gallery_Package extends AppCompatActivity{
    private FirebaseRecyclerAdapter<CommonModel, Gallery_Package.NewsViewHolder> RVAdapter;
    LinearLayoutManager linearLayoutManager ;
    DatabaseReference mRef,ImageRef;
    RecyclerView mRecyclerView;
    String title;
    FirebaseDatabase mFirebaseDatabase;
    int id;
    String IdString;
    static String image1="",image2="",image3="",image4="",image5="",CategoryTab;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linearLayoutManager = new LinearLayoutManager(Gallery_Package.this);
        setContentView(R.layout.gallerypackage);
        String imageUrl= getIntent().getStringExtra("image_url");
        String imageName= getIntent().getStringExtra("image_name");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(imageName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RequestOptions options = new RequestOptions();
        options.fitCenter();
        ImageView image=findViewById(R.id.imagegall);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Travel-Package").child(getString(R.string.Language)).child(imageName).child("Des");

        mRef.keepSynced(true);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerView.hasFixedSize();
        linearLayoutManager = new LinearLayoutManager(Gallery_Package.this);
        mRecyclerView.setLayoutManager(linearLayoutManager);








        FirebaseRecyclerOptions foodOptions = new FirebaseRecyclerOptions.Builder<CommonModel>().setQuery(mRef, CommonModel.class).build();
        RVAdapter = new FirebaseRecyclerAdapter<CommonModel, Gallery_Package.NewsViewHolder>(foodOptions) {
            @Override
            protected void onBindViewHolder(@NonNull Gallery_Package.NewsViewHolder holder, int position, final CommonModel model) {
                title = model.getTitle();

                holder.setTitle(model.getTitle());
                holder.setImage(getApplicationContext(), model.getUrl());
                holder.setDes(model.getDes().replace("_b","\n").replace("_p","=>"));
                holder.setImage2(getApplicationContext(),model.getUrl2());
                holder.setImage3(getApplicationContext(),model.getUrl3());
            }


            @NonNull
            @Override
            public Gallery_Package.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_gallery, parent, false);

                return new Gallery_Package.NewsViewHolder(view);
            }
        };
        mRecyclerView.setAdapter(RVAdapter);

        RVAdapter.startListening();



    }
    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String title) {
            TextView post_title = (TextView) mView.findViewById(R.id.name_item);
            post_title.setText(title);
        }
        public  void setDes(String Des){
            TextView des = (TextView)mView.findViewById(R.id.des_item);
            des.setText(Des);
        }

        public void setImage(Context ctx, String image) {
            ImageView post_image = (ImageView) mView.findViewById(R.id.image);
            Picasso.get().load(image).into(post_image);
        }
        public void setImage2(Context ctx, String image) {
            ImageView post_image2 = (ImageView) mView.findViewById(R.id.image2);
            Picasso.get().load(image).into(post_image2);
        }
        public void setImage3(Context ctx, String image) {
            ImageView post_image3 = (ImageView) mView.findViewById(R.id.image3);
            Picasso.get().load(image).into(post_image3);
        }
    }
    private void getIncomingIntent(){

       String imageUrl= getIntent().getStringExtra("image_url");

        String imageName= getIntent().getStringExtra("image_name");
        String des = getIntent().getStringExtra("Des").replace("_b","\n");
       /* String tel = getIntent().getStringExtra("Tel");
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


        setImage(imageUrl,imageName,des);

    }
    private void setImage(String imageUrl, final String imageName, String des){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(imageName);
       /* TextView GelleryText = findViewById(R.id.gallerytext);
        GelleryText.setPaintFlags(GelleryText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference ImageRef;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = mFirebaseDatabase.getReference(CategoryTab).child("ImageMore").child(IdString);

     /*   myRef.addValueEventListener(new ValueEventListener() {
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
        GelleryText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goGalleryMore = new Intent(Gallery_Package.this,ImageGallery.class);
                goGalleryMore.putExtra("Name",imageName);
                goGalleryMore.putExtra("UrlList1",image1);
                goGalleryMore.putExtra("UrlList2",image2);
                goGalleryMore.putExtra("UrlList3",image3);
                goGalleryMore.putExtra("UrlList4",image4);
                goGalleryMore.putExtra("UrlList5",image5);
                startActivity(goGalleryMore);
            }
        });*/
      //  TextView Location = findViewById(R.id.locationtext);
      //  Location.setText(" "+location);
       // TextView TelText = findViewById(R.id.teltext);
      //  TelText.setText(" "+tel);
       // TextView googleMap =findViewById(R.id.googlemap);
        /*TelText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tel =getIntent().getStringExtra("Tel");
                Intent i=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+tel));
                startActivity(i);
            }
        });
/*
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
*/
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // TextView name = findViewById(R.id.tooalbar);
       // TextView descri = findViewById(R.id.description);
        //  TextView tele =findViewById(R.id.tel);
        //TextView location = findViewById(R.id.location_input);
        // tele.setText(tel);
        // location.setText(locate);
      //  descri.setText("  "+des);
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
