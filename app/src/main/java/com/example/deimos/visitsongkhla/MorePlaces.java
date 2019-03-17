package com.example.deimos.visitsongkhla;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;



public class MorePlaces extends AppCompatActivity {
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter<CommonModel, MorePlaces.NewsViewHolder> RVAdapter;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    private static final int ACTIVITY_NUM = 1;
    public static int positionIndex = -1;
    public static int topView = -1;
    LinearLayoutManager linearLayoutManager ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places_list);
        setNavi();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Home-Attract").child("TH");
        mRef.keepSynced(true);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerView.hasFixedSize();
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        /*
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        */
        /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Home-Attract").child("TH").child("สวนสัตว์สงขลา").child("title");
        myRef.setValue("Hello, World!");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        */
        FirebaseRecyclerOptions foodOptions = new FirebaseRecyclerOptions.Builder<CommonModel>().setQuery(mRef, CommonModel.class).build();
        RVAdapter = new FirebaseRecyclerAdapter<CommonModel, MorePlaces.NewsViewHolder>(foodOptions) {
            @Override
            protected void onBindViewHolder(@NonNull MorePlaces.NewsViewHolder holder, int position, final CommonModel model) {
                holder.setTitle(model.getTitle());
                holder.setImage(getApplicationContext(), model.getUrl());
                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String url = model.getUrl();
                        Intent intent = new Intent(MorePlaces.this, Gallery_MorePlaces.class);
                        intent.putExtra("image_name", model.getTitle());
                        intent.putExtra("image_url", model.getUrl());
                        intent.putExtra("Des", model.getDes());
                        intent.putExtra("Local",model.getLocation());
                        intent.putExtra("Tel",model.getTel());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public MorePlaces.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_places, parent, false);

                return new MorePlaces.NewsViewHolder(view);
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

        public void setImage(Context ctx, String image) {
            ImageView post_image = (ImageView) mView.findViewById(R.id.image_item);
            Picasso.get().load(image).into(post_image);
        }
    }
    public void setNavi(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(MorePlaces.this,bottomNavigationViewEx);
        Menu menu =bottomNavigationViewEx.getMenu();

        MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
  /*  @Override
    public void onStart() {

        RVAdapter.startListening();
        super.onStart();
    }

    @Override
    public void onStop() {

        RVAdapter.stopListening();
        super.onStop();


    }*/

    @Override
    protected void onPause() {
        super.onPause();
        positionIndex= linearLayoutManager.findFirstVisibleItemPosition();
        View startView = mRecyclerView.getChildAt(0);
        topView = (startView == null) ? 0 : (startView.getTop() - mRecyclerView.getPaddingTop());

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (positionIndex!= -1) {
            linearLayoutManager.scrollToPositionWithOffset(positionIndex, topView);
        }

    }
}
