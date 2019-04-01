package com.example.deimos.visitsongkhla;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    private static final int ACTIVITY_NUM=0;
    private static final String TAG = "Home";
    RecyclerView recyclerView;
    private ArrayList<String>mNames = new ArrayList<>();
    private ArrayList<String>mImageUrls = new ArrayList<>();
    private ArrayList<String>mDes = new ArrayList<>();
    private ArrayList<String>mTopic= new ArrayList<>();
    private ArrayList<String>mTel= new ArrayList<>();
    private ArrayList<String>mLocation= new ArrayList<>();
    private ArrayList<String>mLat= new ArrayList<>();
    private ArrayList<String>mLng= new ArrayList<>();
    private ArrayList<String>mImage11= new ArrayList<>();
    private ArrayList<String>mImage12= new ArrayList<>();
    private ArrayList<String>mImage13= new ArrayList<>();
    private ArrayList<String>mImage14= new ArrayList<>();
    private ArrayList<String>mImage15= new ArrayList<>();
    private ArrayList<String>mImageMore = new ArrayList<>();


    private ArrayList<String>mNames2 = new ArrayList<>();
    private ArrayList<String>mImageUrls2 = new ArrayList<>();
    private ArrayList<String>mDes2 = new ArrayList<>();
    private ArrayList<String>mTopic2= new ArrayList<>();
    private ArrayList<String>mTel2= new ArrayList<>();
    private ArrayList<String>mLocation2= new ArrayList<>();
    private ArrayList<String>mLat2= new ArrayList<>();
    private ArrayList<String>mLng2= new ArrayList<>();
    private ArrayList<String>mImage21= new ArrayList<>();
    private ArrayList<String>mImage22= new ArrayList<>();
    private ArrayList<String>mImage23= new ArrayList<>();
    private ArrayList<String>mImage24= new ArrayList<>();
    private ArrayList<String>mImage25= new ArrayList<>();


    private ArrayList<String>mNames3 = new ArrayList<>();
    private ArrayList<String>mImageUrls3 = new ArrayList<>();
    private ArrayList<String>mDes3 = new ArrayList<>();
    private ArrayList<String>mTopic3= new ArrayList<>();
    private ArrayList<String>mTel3= new ArrayList<>();
    private ArrayList<String>mLocation3= new ArrayList<>();
    private ArrayList<String>mLat3= new ArrayList<>();
    private ArrayList<String>mLng3= new ArrayList<>();
    private ArrayList<String>mImage31= new ArrayList<>();
    private ArrayList<String>mImage32= new ArrayList<>();
    private ArrayList<String>mImage33= new ArrayList<>();
    private ArrayList<String>mImage34= new ArrayList<>();
    private ArrayList<String>mImage35= new ArrayList<>();
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    public static int positionIndex = -1;
    public static int topView = -1;
    public static int positionIndex2 = -1;
    public static int topView2 = -1;
    public static int positionIndex3 = -1;
    public static int topView3 = -1;
    TextView next1;
    TextView next2;
    TextView next3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        setNavi();
        getImages();
        getImages2();
        getImages3();

        next1 = findViewById(R.id.next1);
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goMorePlaces = new Intent(Home.this,MorePlaces.class);
                goMorePlaces.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(goMorePlaces);
            }
        });

        next2= findViewById(R.id.next3);
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goMoreRes = new Intent(Home.this,ChooseFood.class);
                goMoreRes.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(goMoreRes);
            }
        });

        next3= findViewById(R.id.next2);
        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goMoreThemes = new Intent(Home.this,ChooseThemes.class);
                goMoreThemes.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(goMoreThemes);
            }
        });



    }
    public void setNavi(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(Home.this,bottomNavigationViewEx);
        Menu menu =bottomNavigationViewEx.getMenu();
        MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
    private void getImages(){
        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2Fs1.JPG?alt=media&token=e3bdab07-a887-4008-975c-6224d0d3985e");
        mNames.add(getString(R.string.HOD1_1_mNames));
        mDes.add(getString(R.string.HOD1_1_mDes));
        mTel.add("074-330267");
        mLocation.add(getString(R.string.HOD1_1_mLocation));
        mLat.add("7.1484416");
        mLng.add("100.5585634");
        mImage11.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B8%AA%E0%B8%A7%E0%B8%99%E0%B8%9B%E0%B8%A3%E0%B8%B0%E0%B8%A7%E0%B8%B1%E0%B8%95%E0%B8%B4%E0%B8%A8%E0%B8%B2%E0%B8%AA%E0%B8%95%E0%B8%A3%E0%B9%8C%2Fs1.JPG?alt=media&token=f9fbcdff-4494-406e-bfb0-d77fac7318b8");
        mImage12.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B8%AA%E0%B8%A7%E0%B8%99%E0%B8%9B%E0%B8%A3%E0%B8%B0%E0%B8%A7%E0%B8%B1%E0%B8%95%E0%B8%B4%E0%B8%A8%E0%B8%B2%E0%B8%AA%E0%B8%95%E0%B8%A3%E0%B9%8C%2Fs2.jpg?alt=media&token=c29d8992-01e3-4d0e-8e32-6deac7c0cd15");
        mImage13.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B8%AA%E0%B8%A7%E0%B8%99%E0%B8%9B%E0%B8%A3%E0%B8%B0%E0%B8%A7%E0%B8%B1%E0%B8%95%E0%B8%B4%E0%B8%A8%E0%B8%B2%E0%B8%AA%E0%B8%95%E0%B8%A3%E0%B9%8C%2Fs3.jpg?alt=media&token=fd414e85-d23d-42c1-ac40-ed916cf53535");
        mImage14.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B8%AA%E0%B8%A7%E0%B8%99%E0%B8%9B%E0%B8%A3%E0%B8%B0%E0%B8%A7%E0%B8%B1%E0%B8%95%E0%B8%B4%E0%B8%A8%E0%B8%B2%E0%B8%AA%E0%B8%95%E0%B8%A3%E0%B9%8C%2Fs4.jpg?alt=media&token=bd14437f-0ea3-45b6-8798-4805f6d0f4e4");
        mImage15.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B8%AA%E0%B8%A7%E0%B8%99%E0%B8%9B%E0%B8%A3%E0%B8%B0%E0%B8%A7%E0%B8%B1%E0%B8%95%E0%B8%B4%E0%B8%A8%E0%B8%B2%E0%B8%AA%E0%B8%95%E0%B8%A3%E0%B9%8C%2Fs5.JPEG?alt=media&token=12efcc8e-f658-479c-ba3a-b99a85a78521");

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2Ft2.jpg?alt=media&token=48250ec1-6746-4533-8671-32ceb6b53a9b");
        mNames.add(getString(R.string.HOD1_2_mNames));
        mDes.add(getString(R.string.HOD1_2_mDes));
        mTel.add("-");
        mLocation.add(getString(R.string.HOD1_2_mLocation));
        mLat.add("7.2107006");
        mLng.add("100.5871188");
        mImage11.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B9%80%E0%B8%82%E0%B8%B2%E0%B8%95%E0%B8%B1%E0%B8%87%E0%B8%81%E0%B8%A7%E0%B8%99%2Ft2.jpg?alt=media&token=cec86e8c-878b-41b8-82a1-23f6f521d56a");
        mImage12.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B9%80%E0%B8%82%E0%B8%B2%E0%B8%95%E0%B8%B1%E0%B8%87%E0%B8%81%E0%B8%A7%E0%B8%99%2Ft3.jpg?alt=media&token=bf4c431d-cfea-4a9b-ba2f-a518b65d6fb6");
        mImage13.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B9%80%E0%B8%82%E0%B8%B2%E0%B8%95%E0%B8%B1%E0%B8%87%E0%B8%81%E0%B8%A7%E0%B8%99%2Ft4.jpg?alt=media&token=272f3dd2-0d18-486e-a824-890aecab7f38");
        mImage14.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B9%80%E0%B8%82%E0%B8%B2%E0%B8%95%E0%B8%B1%E0%B8%87%E0%B8%81%E0%B8%A7%E0%B8%99%2Ft5.jpg?alt=media&token=af837248-0fb8-4509-8256-8331de3968a9");
        mImage15.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B9%80%E0%B8%82%E0%B8%B2%E0%B8%95%E0%B8%B1%E0%B8%87%E0%B8%81%E0%B8%A7%E0%B8%99%2Ft6.jpg?alt=media&token=449aef09-9683-4b9d-9617-9a6c6dc42666");

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2Fs1.jpg?alt=media&token=ed892cfa-c62e-4e51-80c8-3f6ffadbe5cd");
        mNames.add(getString(R.string.HOD1_3_mNames));
        mDes.add(getString(R.string.HOD1_3_mDes));
        mTel.add("-");
        mLocation.add(getString(R.string.HOD1_3_mNames));
        mLat.add("7.2226902");
        mLng.add("100.5802669");
        mImage11.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B9%81%E0%B8%AB%E0%B8%A5%E0%B8%A1%E0%B8%AA%E0%B8%A1%E0%B8%B4%E0%B8%AB%E0%B8%A5%E0%B8%B2%2Fs2.jpg?alt=media&token=a70577d3-3064-44b2-bee0-18c0bcca0211");
        mImage12.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B9%81%E0%B8%AB%E0%B8%A5%E0%B8%A1%E0%B8%AA%E0%B8%A1%E0%B8%B4%E0%B8%AB%E0%B8%A5%E0%B8%B2%2Fs3.jpg?alt=media&token=78a25ed0-c139-4eb2-844e-7876b9f54d71");
        mImage13.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B9%81%E0%B8%AB%E0%B8%A5%E0%B8%A1%E0%B8%AA%E0%B8%A1%E0%B8%B4%E0%B8%AB%E0%B8%A5%E0%B8%B2%2Fs4.jpg?alt=media&token=54603915-8c81-4166-bd66-b1ebcdd2b3a5");
        mImage14.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B9%81%E0%B8%AB%E0%B8%A5%E0%B8%A1%E0%B8%AA%E0%B8%A1%E0%B8%B4%E0%B8%AB%E0%B8%A5%E0%B8%B2%2Fs5.jpg?alt=media&token=9988648c-3138-4cd5-9169-8a81a1889adf");
        mImage15.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B9%81%E0%B8%AB%E0%B8%A5%E0%B8%A1%E0%B8%AA%E0%B8%A1%E0%B8%B4%E0%B8%AB%E0%B8%A5%E0%B8%B2%2Fs6.JPG?alt=media&token=16e8f6c9-1f86-420b-9058-e9e4294db5ad");

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2Fu1.jpg?alt=media&token=8eefaf85-9231-434e-844f-b57785edfbe0");
        mNames.add(getString(R.string.HOD1_4_mNames));
        mDes.add(getString(R.string.HOD1_4_mDes));
        mTel.add("-");
        mLocation.add(getString(R.string.HOD1_4_mLocation));
        mLat.add("7.1985888");
        mLng.add("100.5867381");
        mImage11.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B8%A2%E0%B9%88%E0%B8%B2%E0%B8%99%E0%B9%80%E0%B8%A1%E0%B8%B7%E0%B8%AD%E0%B8%87%E0%B9%80%E0%B8%81%E0%B9%88%E0%B9%88%E0%B8%B2%E0%B8%AA%E0%B8%87%E0%B8%82%E0%B8%A5%E0%B8%B2%2Fu1.jpg?alt=media&token=5c87213d-e770-443b-b8d1-4347e1de8f58");
        mImage12.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B8%A2%E0%B9%88%E0%B8%B2%E0%B8%99%E0%B9%80%E0%B8%A1%E0%B8%B7%E0%B8%AD%E0%B8%87%E0%B9%80%E0%B8%81%E0%B9%88%E0%B9%88%E0%B8%B2%E0%B8%AA%E0%B8%87%E0%B8%82%E0%B8%A5%E0%B8%B2%2Fu2.jpg?alt=media&token=cdefd2bc-f6d6-4554-9d7d-438133cb96d9");
        mImage13.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B8%A2%E0%B9%88%E0%B8%B2%E0%B8%99%E0%B9%80%E0%B8%A1%E0%B8%B7%E0%B8%AD%E0%B8%87%E0%B9%80%E0%B8%81%E0%B9%88%E0%B9%88%E0%B8%B2%E0%B8%AA%E0%B8%87%E0%B8%82%E0%B8%A5%E0%B8%B2%2Fu5.jpg?alt=media&token=eb0c6133-6bc5-4579-a215-02346326724e");
        mImage14.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B8%A2%E0%B9%88%E0%B8%B2%E0%B8%99%E0%B9%80%E0%B8%A1%E0%B8%B7%E0%B8%AD%E0%B8%87%E0%B9%80%E0%B8%81%E0%B9%88%E0%B9%88%E0%B8%B2%E0%B8%AA%E0%B8%87%E0%B8%82%E0%B8%A5%E0%B8%B2%2Fu6.jpg?alt=media&token=71fc7450-81d6-483e-add8-93c291f9089e");
        mImage15.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B8%A2%E0%B9%88%E0%B8%B2%E0%B8%99%E0%B9%80%E0%B8%A1%E0%B8%B7%E0%B8%AD%E0%B8%87%E0%B9%80%E0%B8%81%E0%B9%88%E0%B9%88%E0%B8%B2%E0%B8%AA%E0%B8%87%E0%B8%82%E0%B8%A5%E0%B8%B2%2Fu7.jpg?alt=media&token=da8269f7-fe93-4480-8ba6-1f79a1e9bf72");

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2Fp2.png?alt=media&token=26a9dd3c-6712-4520-a566-b9e9444a6e91");
        mNames.add(getString(R.string.HOD1_5_mNames));
        mDes.add(getString(R.string.HOD1_5_mDes));
        mTel.add("-");
        mLocation.add(getString(R.string.HOD1_5_mLocation));
        mLat.add("7.2020233");
        mLng.add("100.5880843");
        mImage11.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B8%81%E0%B8%B3%E0%B9%81%E0%B8%9E%E0%B8%87%E0%B9%80%E0%B8%A1%E0%B8%B7%E0%B8%AD%E0%B8%87%E0%B9%80%E0%B8%81%E0%B9%88%E0%B8%B2%E0%B8%AA%E0%B8%87%E0%B8%82%E0%B8%A5%E0%B8%B2%2Fp1.jpg?alt=media&token=3ee31077-5340-45fe-bb61-21e9975b9a2e");
        mImage12.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B8%81%E0%B8%B3%E0%B9%81%E0%B8%9E%E0%B8%87%E0%B9%80%E0%B8%A1%E0%B8%B7%E0%B8%AD%E0%B8%87%E0%B9%80%E0%B8%81%E0%B9%88%E0%B8%B2%E0%B8%AA%E0%B8%87%E0%B8%82%E0%B8%A5%E0%B8%B2%2Fp2.png?alt=media&token=fe8697d0-c716-4acc-b7fa-63c26ec68a28");
        mImage13.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B8%81%E0%B8%B3%E0%B9%81%E0%B8%9E%E0%B8%87%E0%B9%80%E0%B8%A1%E0%B8%B7%E0%B8%AD%E0%B8%87%E0%B9%80%E0%B8%81%E0%B9%88%E0%B8%B2%E0%B8%AA%E0%B8%87%E0%B8%82%E0%B8%A5%E0%B8%B2%2Fp3.png?alt=media&token=35e0bc0b-acd3-49ca-a13a-0ea27d8c349d");
        mImage14.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B8%81%E0%B8%B3%E0%B9%81%E0%B8%9E%E0%B8%87%E0%B9%80%E0%B8%A1%E0%B8%B7%E0%B8%AD%E0%B8%87%E0%B9%80%E0%B8%81%E0%B9%88%E0%B8%B2%E0%B8%AA%E0%B8%87%E0%B8%82%E0%B8%A5%E0%B8%B2%2Fp4.jpg?alt=media&token=653bc96a-2249-409c-b0eb-9a9d3d8787ef");
        mImage15.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FPlaces%2F%E0%B8%81%E0%B8%B3%E0%B9%81%E0%B8%9E%E0%B8%87%E0%B9%80%E0%B8%A1%E0%B8%B7%E0%B8%AD%E0%B8%87%E0%B9%80%E0%B8%81%E0%B9%88%E0%B8%B2%E0%B8%AA%E0%B8%87%E0%B8%82%E0%B8%A5%E0%B8%B2%2Fp5.jpg?alt=media&token=63104668-2404-41d8-80eb-153dab34b36e");

        initRecyclerView();
    }
    private void initRecyclerView(){
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,mNames, mImageUrls,mDes,mTel,mLocation,mLat,mLng,mImage11,mImage12,mImage13,
                mImage14,mImage15,mImageMore);
        recyclerView.setAdapter(adapter);
    }
    private void getImages2(){
        mImageUrls2.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2Fp3.jpg?alt=media&token=a604c1bd-32f2-461e-b9ac-a616d8bbea39");
        mNames2.add(getString(R.string.HOD2_1_mNames));
        mDes2.add(getString(R.string.HOD2_1_mDes));
        mTel2.add("074-305300");
        mLocation2.add(getString(R.string.HOD2_1_mLocation));
        mLat2.add("7.0362334");
        mLng2.add("100.5024745");
        mImage21.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%AA%E0%B8%A7%E0%B8%99%E0%B8%AA%E0%B8%B2%E0%B8%98%E0%B8%B2%E0%B8%A3%E0%B8%93%E0%B8%B0%E0%B8%99%E0%B8%84%E0%B8%A3%E0%B8%AB%E0%B8%B2%E0%B8%94%E0%B9%83%E0%B8%AB%E0%B8%8D%E0%B9%88%2Fp1.jpg?alt=media&token=4f8c1d04-14d0-4000-ac10-f9767bb81daf");
        mImage22.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%AA%E0%B8%A7%E0%B8%99%E0%B8%AA%E0%B8%B2%E0%B8%98%E0%B8%B2%E0%B8%A3%E0%B8%93%E0%B8%B0%E0%B8%99%E0%B8%84%E0%B8%A3%E0%B8%AB%E0%B8%B2%E0%B8%94%E0%B9%83%E0%B8%AB%E0%B8%8D%E0%B9%88%2Fp3.jpg?alt=media&token=f38f66b7-a593-4020-9a29-63c382568c7d");
        mImage23.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%AA%E0%B8%A7%E0%B8%99%E0%B8%AA%E0%B8%B2%E0%B8%98%E0%B8%B2%E0%B8%A3%E0%B8%93%E0%B8%B0%E0%B8%99%E0%B8%84%E0%B8%A3%E0%B8%AB%E0%B8%B2%E0%B8%94%E0%B9%83%E0%B8%AB%E0%B8%8D%E0%B9%88%2Fp4.jpg?alt=media&token=a945c4d0-6490-4da5-8bb8-0320c9f16cda");
        mImage24.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%AA%E0%B8%A7%E0%B8%99%E0%B8%AA%E0%B8%B2%E0%B8%98%E0%B8%B2%E0%B8%A3%E0%B8%93%E0%B8%B0%E0%B8%99%E0%B8%84%E0%B8%A3%E0%B8%AB%E0%B8%B2%E0%B8%94%E0%B9%83%E0%B8%AB%E0%B8%8D%E0%B9%88%2Fp5.jpg?alt=media&token=04033696-1296-4622-afc3-fa774b855c0b");
        mImage25.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%AA%E0%B8%A7%E0%B8%99%E0%B8%AA%E0%B8%B2%E0%B8%98%E0%B8%B2%E0%B8%A3%E0%B8%93%E0%B8%B0%E0%B8%99%E0%B8%84%E0%B8%A3%E0%B8%AB%E0%B8%B2%E0%B8%94%E0%B9%83%E0%B8%AB%E0%B8%8D%E0%B9%88%2Fp6.jpg?alt=media&token=d5816928-1bb3-44b6-a072-541ea5a2e40f");


        mImageUrls2.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2Ft1.jpg?alt=media&token=d6f40ce5-0df5-4f80-9166-59981133818b");
        mNames2.add(getString(R.string.HOD2_2_mNames));
        mDes2.add(getString(R.string.HOD2_2_mDes));
        mTel2.add("-");
        mLocation2.add(getString(R.string.HOD2_2_mLocation));
        mLat2.add("6.9499366");
        mLng2.add("100.2318391");
        mImage21.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%99%E0%B9%89%E0%B8%B3%E0%B8%95%E0%B8%81%E0%B9%82%E0%B8%95%E0%B8%99%E0%B8%87%E0%B8%B2%E0%B8%8A%E0%B9%89%E0%B8%B2%E0%B8%87%2Ft1.jpg?alt=media&token=d7f638ad-f4c7-4106-9076-b20c5c713812");
        mImage22.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%99%E0%B9%89%E0%B8%B3%E0%B8%95%E0%B8%81%E0%B9%82%E0%B8%95%E0%B8%99%E0%B8%87%E0%B8%B2%E0%B8%8A%E0%B9%89%E0%B8%B2%E0%B8%87%2Ft2.jpg?alt=media&token=3a755230-5d40-4344-9986-a57c17340efc");
        mImage23.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%99%E0%B9%89%E0%B8%B3%E0%B8%95%E0%B8%81%E0%B9%82%E0%B8%95%E0%B8%99%E0%B8%87%E0%B8%B2%E0%B8%8A%E0%B9%89%E0%B8%B2%E0%B8%87%2Ft3.jpg?alt=media&token=a6da0cfd-8dfe-4e9b-b5d8-ce2c95ccb6b3");
        mImage24.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%99%E0%B9%89%E0%B8%B3%E0%B8%95%E0%B8%81%E0%B9%82%E0%B8%95%E0%B8%99%E0%B8%87%E0%B8%B2%E0%B8%8A%E0%B9%89%E0%B8%B2%E0%B8%87%2Ft4.jpg?alt=media&token=abf631b9-44b8-497c-aa08-af2ad31dbd36");
        mImage25.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%99%E0%B9%89%E0%B8%B3%E0%B8%95%E0%B8%81%E0%B9%82%E0%B8%95%E0%B8%99%E0%B8%87%E0%B8%B2%E0%B8%8A%E0%B9%89%E0%B8%B2%E0%B8%87%2Ft5.jpg?alt=media&token=5d513f14-316d-4a03-afd5-ece40f1c0d1a");

        mImageUrls2.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2Fi5.png?alt=media&token=c01454cf-3093-4c46-b596-bbbc0da717cb");
        mNames2.add(getString(R.string.HOD2_3_mNames));
        mDes2.add(getString(R.string.HOD2_3_mDes));
        mTel2.add("-");
        mLocation2.add(getString(R.string.HOD2_3_mLocation));
        mLat2.add("7.0076388");
        mLng2.add("100.4677961");
        mImage21.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%95%E0%B8%A5%E0%B8%B2%E0%B8%94%E0%B8%81%E0%B8%B4%E0%B8%A1%E0%B8%AB%E0%B8%A2%E0%B8%87%2Fi1.jpg?alt=media&token=c8e185db-d04e-49ce-8370-f963bcf7276a");
        mImage22.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%95%E0%B8%A5%E0%B8%B2%E0%B8%94%E0%B8%81%E0%B8%B4%E0%B8%A1%E0%B8%AB%E0%B8%A2%E0%B8%87%2Fi2.jpg?alt=media&token=c6b163d0-4311-438b-bb4e-5ad498b2aeeb");
        mImage23.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%95%E0%B8%A5%E0%B8%B2%E0%B8%94%E0%B8%81%E0%B8%B4%E0%B8%A1%E0%B8%AB%E0%B8%A2%E0%B8%87%2Fi3.jpg?alt=media&token=4cf57be5-2cc4-4a7f-887e-c1cecce94d7c");
        mImage24.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%95%E0%B8%A5%E0%B8%B2%E0%B8%94%E0%B8%81%E0%B8%B4%E0%B8%A1%E0%B8%AB%E0%B8%A2%E0%B8%87%2Fi4.png?alt=media&token=83586e57-d98d-45ee-8566-79e938791a6c");
        mImage25.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%95%E0%B8%A5%E0%B8%B2%E0%B8%94%E0%B8%81%E0%B8%B4%E0%B8%A1%E0%B8%AB%E0%B8%A2%E0%B8%87%2Fi4.png?alt=media&token=83586e57-d98d-45ee-8566-79e938791a6c");


        mImageUrls2.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2Fz1.jpg?alt=media&token=8d5aea43-9d25-4893-abdc-6e24cde48f7b");
        mNames2.add(getString(R.string.HOD2_4_mNames));
        mDes2.add(getString(R.string.HOD2_4_mDes));
        mTel2.add("074-336038");
        mLocation2.add(getString(R.string.HOD2_4_mLocation));
        mLat2.add("7.1429569");
        mLng2.add("100.6021214");
        mImage21.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%AA%E0%B8%A7%E0%B8%99%E0%B8%AA%E0%B8%B1%E0%B8%95%E0%B8%A7%E0%B9%8C%E0%B8%AA%E0%B8%87%E0%B8%82%E0%B8%A5%E0%B8%B2%2Fz2.png?alt=media&token=3a3fd954-5143-4567-837f-54f86abeab5c");
        mImage22.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%AA%E0%B8%A7%E0%B8%99%E0%B8%AA%E0%B8%B1%E0%B8%95%E0%B8%A7%E0%B9%8C%E0%B8%AA%E0%B8%87%E0%B8%82%E0%B8%A5%E0%B8%B2%2Fz3.jpg?alt=media&token=35787093-bc4c-44b6-9ab5-bbbc8a8444c9");
        mImage23.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%AA%E0%B8%A7%E0%B8%99%E0%B8%AA%E0%B8%B1%E0%B8%95%E0%B8%A7%E0%B9%8C%E0%B8%AA%E0%B8%87%E0%B8%82%E0%B8%A5%E0%B8%B2%2Fz4.jpg?alt=media&token=042f201a-d1a0-4802-9833-d20e1175528d");
        mImage24.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%AA%E0%B8%A7%E0%B8%99%E0%B8%AA%E0%B8%B1%E0%B8%95%E0%B8%A7%E0%B9%8C%E0%B8%AA%E0%B8%87%E0%B8%82%E0%B8%A5%E0%B8%B2%2Fz5.jpg?alt=media&token=fdf09115-cde3-47e8-b0df-e0b3c65fc7d2");
        mImage25.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%AA%E0%B8%A7%E0%B8%99%E0%B8%AA%E0%B8%B1%E0%B8%95%E0%B8%A7%E0%B9%8C%E0%B8%AA%E0%B8%87%E0%B8%82%E0%B8%A5%E0%B8%B2%2Fz6.jpg?alt=media&token=7b1686ef-e4d1-4b38-b913-587016873da2");

        mImageUrls2.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2Fa3.jpg?alt=media&token=ba41c2da-04df-45d1-9008-eadb21cbcebe");
        mNames2.add(getString(R.string.HOD2_5_mNames));
        mDes2.add(getString(R.string.HOD2_5_mDes));
        mTel2.add("-");
        mLocation2.add(getString(R.string.HOD2_5_mLocation));
        mLat2.add("7.2253658");
        mLng2.add("100.5777553");
        mImage21.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%AA%E0%B8%96%E0%B8%B2%E0%B8%99%E0%B9%81%E0%B8%AA%E0%B8%94%E0%B8%87%E0%B8%9E%E0%B8%B1%E0%B8%99%E0%B8%98%E0%B8%B8%E0%B9%8C%E0%B8%AA%E0%B8%B1%E0%B8%95%E0%B8%A7%E0%B9%8C%E0%B8%99%E0%B9%89%E0%B8%B3%E0%B8%AA%E0%B8%87%E0%B8%82%E0%B8%A5%E0%B8%B2%2Fa1.jpg?alt=media&token=a29d0d1c-fd54-4d9b-83e6-9195ef71fd76");
        mImage22.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%AA%E0%B8%96%E0%B8%B2%E0%B8%99%E0%B9%81%E0%B8%AA%E0%B8%94%E0%B8%87%E0%B8%9E%E0%B8%B1%E0%B8%99%E0%B8%98%E0%B8%B8%E0%B9%8C%E0%B8%AA%E0%B8%B1%E0%B8%95%E0%B8%A7%E0%B9%8C%E0%B8%99%E0%B9%89%E0%B8%B3%E0%B8%AA%E0%B8%87%E0%B8%82%E0%B8%A5%E0%B8%B2%2Fa2.jpg?alt=media&token=de8de66c-64f2-4b4a-b9e8-73c97214214e");
        mImage23.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%AA%E0%B8%96%E0%B8%B2%E0%B8%99%E0%B9%81%E0%B8%AA%E0%B8%94%E0%B8%87%E0%B8%9E%E0%B8%B1%E0%B8%99%E0%B8%98%E0%B8%B8%E0%B9%8C%E0%B8%AA%E0%B8%B1%E0%B8%95%E0%B8%A7%E0%B9%8C%E0%B8%99%E0%B9%89%E0%B8%B3%E0%B8%AA%E0%B8%87%E0%B8%82%E0%B8%A5%E0%B8%B2%2Fa3.jpg?alt=media&token=dbcd90a8-9c40-44d3-95bb-26fd4f1c812a");
        mImage24.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%AA%E0%B8%96%E0%B8%B2%E0%B8%99%E0%B9%81%E0%B8%AA%E0%B8%94%E0%B8%87%E0%B8%9E%E0%B8%B1%E0%B8%99%E0%B8%98%E0%B8%B8%E0%B9%8C%E0%B8%AA%E0%B8%B1%E0%B8%95%E0%B8%A7%E0%B9%8C%E0%B8%99%E0%B9%89%E0%B8%B3%E0%B8%AA%E0%B8%87%E0%B8%82%E0%B8%A5%E0%B8%B2%2Fa4.jpg?alt=media&token=30f9c84a-cae1-43dc-ab9c-c345aac0a50c");
        mImage25.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FThemes%2F%E0%B8%AA%E0%B8%96%E0%B8%B2%E0%B8%99%E0%B9%81%E0%B8%AA%E0%B8%94%E0%B8%87%E0%B8%9E%E0%B8%B1%E0%B8%99%E0%B8%98%E0%B8%B8%E0%B9%8C%E0%B8%AA%E0%B8%B1%E0%B8%95%E0%B8%A7%E0%B9%8C%E0%B8%99%E0%B9%89%E0%B8%B3%E0%B8%AA%E0%B8%87%E0%B8%82%E0%B8%A5%E0%B8%B2%2Fa5.PNG?alt=media&token=20866d2c-93b8-4826-ac03-f43ecba8ca1b");

        initRecyclerView2();
    }
    private void initRecyclerView2(){
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,mNames2, mImageUrls2,mDes2,mTel2,mLocation2,mLat2,mLng2
                ,mImage21,mImage22,mImage23,
                mImage24,mImage25,mImageMore);
        recyclerView2.setAdapter(adapter);
    }

    private void getImages3(){
        mImageUrls3.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FRes%2F2.jpg?alt=media&token=9b52e4ca-4518-4998-a465-76e40885fcee");
        mNames3.add(getString(R.string.HOD3_1_mNames));
        mDes3.add(getString(R.string.HOD3_1_mDes));
        mTel3.add("081-9639553");
        mLocation3.add(getString(R.string.HOD3_1_mLocation));
        mLat3.add("7.0037427");
        mLng3.add("100.4783379");
        mImage31.add("");
        mImage32.add("");
        mImage33.add("");
        mImage34.add("");
        mImage35.add("");


        mImageUrls3.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FRes%2F5.jpg?alt=media&token=537be66b-ba99-4cf5-a9a6-6f8e75f85dad");
        mNames3.add(getString(R.string.HOD3_2_mNames));
        mDes3.add(getString(R.string.HOD3_2_mDes));
        mTel3.add("081-3726181");
        mLocation3.add(getString(R.string.HOD3_2_mLocation));
        mLat3.add("7.0054348");
        mLng3.add("100.4792328");
        mImage31.add("");
        mImage32.add("");
        mImage33.add("");
        mImage34.add("");
        mImage35.add("");

        mImageUrls3.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FRes%2F1.jpg?alt=media&token=a1c68749-26c9-4d16-8c17-d4a6b59a3698");
        mNames3.add(getString(R.string.HOD3_3_mNames));
        mDes3.add(getString(R.string.HOD3_3_mDes));
        mTel3.add("081-0983751");
        mLocation3.add(getString(R.string.HOD3_3_mLocation));
        mLat3.add("6.9670558");
        mLng3.add("100.4253555");
        mImage31.add("");
        mImage32.add("");
        mImage33.add("");
        mImage34.add("");
        mImage35.add("");

        mImageUrls3.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FRes%2Fkenta2.jpg?alt=media&token=b0ef2619-366b-4afe-87ab-f1e2defbd674");
        mNames3.add(getString(R.string.HOD3_4_mNames));
        mDes3.add(getString(R.string.HOD3_4_mDes));
        mTel3.add("093-6943502");
        mLocation3.add(getString(R.string.HOD3_4_mLocation));
        mLat3.add("7.0082122");
        mLng3.add("100.4744218");
        mImage31.add("");
        mImage32.add("");
        mImage33.add("");
        mImage34.add("");
        mImage35.add("");

        mImageUrls3.add("https://firebasestorage.googleapis.com/v0/b/visitsongkhla.appspot.com/o/Home%2FRes%2F%E0%B8%99%E0%B9%89%E0%B8%B3%E0%B9%80%E0%B8%84%E0%B8%B5%E0%B8%A2%E0%B8%87%E0%B8%94%E0%B8%B4%E0%B8%99.jpg?alt=media&token=da165e8e-28de-4584-862f-ecff00b4e9e7");
        mNames3.add(getString(R.string.HOD3_5_mNames));
        mDes3.add(getString(R.string.HOD3_5_mDes));
        mTel3.add("082-1799709");
        mLocation3.add(getString(R.string.HOD3_5_mLocation));
        mLat3.add("7.0918989");
        mLng3.add("100.4587059");
        mImage31.add("");
        mImage32.add("");
        mImage33.add("");
        mImage34.add("");
        mImage35.add("");

        initRecyclerView3();
    }
    private void initRecyclerView3(){
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView3 = findViewById(R.id.recyclerView3);
        recyclerView3.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,mNames3, mImageUrls3,mDes3,mTel3,mLocation3,mLat3,mLng3
                ,mImage31,mImage32,mImage33,
                mImage34,mImage35,mImageMore);
        recyclerView3.setAdapter(adapter);
    }   @Override
    protected void onPause() {
        super.onPause();
        positionIndex= layoutManager.findFirstVisibleItemPosition();
        View startView = recyclerView.getChildAt(0);
        topView = (startView == null) ? 0 : (startView.getTop() - recyclerView.getPaddingTop());

        positionIndex2= layoutManager.findFirstVisibleItemPosition();
        View startView2 = recyclerView2.getChildAt(0);
        topView3 = (startView == null) ? 0 : (startView.getLeft() - recyclerView2.getPaddingLeft());

        positionIndex2= layoutManager.findFirstVisibleItemPosition();
        View startView3 = recyclerView2.getChildAt(0);
        topView3 = (startView == null) ? 0 : (startView.getLeft() - recyclerView3.getPaddingLeft());

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (positionIndex!= -1) {
            layoutManager.scrollToPositionWithOffset(positionIndex, topView);
        }

        if (positionIndex2!= -1) {
            layoutManager.scrollToPositionWithOffset(positionIndex2, topView2);
        }

        if (positionIndex3!= -1) {
            layoutManager.scrollToPositionWithOffset(positionIndex3, topView3);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent goSet = new Intent(Home.this,Setting.class);
            startActivity(goSet);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
