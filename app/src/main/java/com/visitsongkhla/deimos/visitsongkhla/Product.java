package com.visitsongkhla.deimos.visitsongkhla;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.Locale;


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

    Check_internet check_internet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
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
                    case R.id.checkinnavi:                         Intent intent3 = new Intent(getApplicationContext(), CheckIn.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);                         startActivity(intent3);
                        break;
                   case R.id.map:                                                  Intent intent4 = new Intent(getApplicationContext(), Maptest2.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);                         startActivity(intent4);
                        break;
                }
                return false;
            }
        });
        android.view.Menu menu =bottomNavigationView.getMenu();
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
            mRef = mFirebaseDatabase.getReference("Travel-Product").child(getString(R.string.Language));
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
                            Intent intent = new Intent(Product.this, GalleryProduct.class);
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
                            intent.putExtra("Id",model.getId());
                            intent.putExtra("Category","Travel-Product");
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
            check_connection();
            // setup the ListView with the new obtained data
            // theList.setVisibility(View.VISIBLE);
        }

    }
    public void check_connection(){
        check_internet = new Check_internet(this);
        check_internet.execute();

    }

    public  void loadLocale(){
        SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = preferences.getString("My_Lang","");
        setLocate(language);
    }
    private void setLocate(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        //Save Lan//
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

}


