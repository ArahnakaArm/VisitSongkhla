package com.example.deimos.visitsongkhla;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
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
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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



public class Product extends AppCompatActivity {
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter<CommonModel, Product.NewsViewHolder> RVAdapter;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    private static final int ACTIVITY_NUM = 1;
    public static int positionIndex = -1;
    public static int topView = -1;
    StaggeredGridLayoutManager linearLayoutManager ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
        setNavi();

        new Product.MyTasks(Product.this).execute((Void) null);


    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String title) {
            TextView post_title = (TextView) mView.findViewById(R.id.tv_name);
            post_title.setText(title);
        }

        public void setImage(Context ctx, String image) {
            ImageView post_image = (ImageView) mView.findViewById(R.id.iv_movie_poster);
            Picasso.get().load(image).into(post_image);
        }
    }
    public void setNavi(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(Product.this,bottomNavigationViewEx);
        Menu menu =bottomNavigationViewEx.getMenu();

        MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
/*
    @Override
    protected void onPause() {
        super.onPause();
        positionIndex= linearLayoutManager.findFirstVisibleItemPositions(0);
        View startView = mRecyclerView.getChildAt(0);
        topView = (startView == null) ? 0 : (startView.getTop() - mRecyclerView.getPaddingTop());

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (positionIndex!= -1) {
            linearLayoutManager.scrollToPositionWithOffset(positionIndex, topView);
        }

    }*/
    private class MyTasks extends AsyncTask<Void, Void, Void> {

        private Product mActivity;
        private FrameLayout mFrameOverlay;
        ListView theList;
        public MyTasks(Product activity) {
            mActivity = activity;
        }

        @Override
        protected void onPreExecute() {
            // the AsyncTask it's about to start so show the overlay
            mFrameOverlay = (FrameLayout) mActivity.findViewById(R.id.overlay);
            // set a touch listener and consume the event so the ListView
            // doesn't get clicked
            mFrameOverlay.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            // do heavy work
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mRef = mFirebaseDatabase.getReference("Travel-Product").child("TH");
            mRef.keepSynced(true);
            mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
            mRecyclerView.hasFixedSize();
            linearLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);

            FirebaseRecyclerOptions foodOptions = new FirebaseRecyclerOptions.Builder<CommonModel>().setQuery(mRef, CommonModel.class).build();
            RVAdapter = new FirebaseRecyclerAdapter<CommonModel, Product.NewsViewHolder>(foodOptions) {
                @Override
                protected void onBindViewHolder(@NonNull Product.NewsViewHolder holder, int position, final CommonModel model) {
                    holder.setTitle(model.getTitle());
                    holder.setImage(getApplicationContext(), model.getUrl());
                    holder.mView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final String url = model.getUrl();
                            Intent intent = new Intent(Product.this, Gallery_MorePlaces.class);
                            intent.putExtra("image_name", model.getTitle());
                            intent.putExtra("image_url", model.getUrl());
                            intent.putExtra("Des", model.getDes());
                            intent.putExtra("Local",model.getLocation());
                            intent.putExtra("Tel",model.getTel());
                            intent.putExtra("Lat",model.getLat());
                            intent.putExtra("Lng",model.getLng());
                            intent.putExtra("MoreImage1",model.getMoreUrl1());
                            intent.putExtra("MoreImage2",model.getMoreUrl2());
                            intent.putExtra("MoreImage3",model.getMoreUrl3());
                            intent.putExtra("MoreImage4",model.getMoreUrl4());
                            intent.putExtra("MoreImage5",model.getMoreUrl5());
                            startActivity(intent);
                        }
                    });

                }

                @NonNull
                @Override
                public Product.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.product_item, parent, false);

                    return new Product.NewsViewHolder(view);
                }
            };
            mRecyclerView.setAdapter(RVAdapter);

            RVAdapter.startListening();





           /* String[] obtainedData = { "D1", "D2", "D3" };
            theList = (ListView) mActivity
                    .findViewById(R.id.second_list);
            theList.setAdapter(new ArrayAdapter<String>(mActivity,
                    android.R.layout.simple_list_item_1, obtainedData));
            theList.setVisibility(View.GONE);*/
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //remove the overlay
            mFrameOverlay.setVisibility(View.GONE);
            // setup the ListView with the new obtained data
            // theList.setVisibility(View.VISIBLE);
        }

    }
}

