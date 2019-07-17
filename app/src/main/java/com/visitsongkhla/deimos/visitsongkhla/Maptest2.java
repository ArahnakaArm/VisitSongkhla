package com.visitsongkhla.deimos.visitsongkhla;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.lang.reflect.Field;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;

public class Maptest2 extends AppCompatActivity implements OnMapReadyCallback,PopupMenu.OnMenuItemClickListener {
    private GoogleMap mMap;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    /////////////////////// LIKE //////////////////////////
    private static final LatLng songkhla   = new LatLng(7.207652, 100.596570);
    private static final LatLng TOP1BEST   = new LatLng(7.215199, 100.581568);
    private static final LatLng TOP2BEST   = new LatLng(7.215329, 100.596055);
    private static final LatLng TOP3BEST   = new LatLng(7.208117, 100.600841);
    private static final LatLng TOP4BEST   = new LatLng(7.210698, 100.589309);
    private static final LatLng TOP5BEST   = new LatLng(7.162471, 100.543675);
    private static final LatLng TOP6BEST   = new LatLng(7.181858, 100.542536);
    private static final LatLng TOP7BEST   = new LatLng(7.474879, 100.445639);
    private static final LatLng TOP8BEST   = new LatLng(7.142959, 100.604308);
    ////////////////////// TEMPLE /////////////////////////
    private static final LatLng TEMPLE1 = new LatLng(7.076117, 100.491965);
    private static final LatLng TEMPLE2 = new LatLng(7.013693, 100.465545);
    private static final LatLng TEMPLE3 = new LatLng(7.155165, 100.532334);
    private static final LatLng TEMPLE4 = new LatLng(7.004971, 100.516330);
    private static final LatLng TEMPLE5 = new LatLng(7.003715, 100.454021);
    private static final LatLng TEMPLE6 = new LatLng(7.004880, 100.475698);
    private static final LatLng TEMPLE7 = new LatLng(7.196538, 100.590706);
    private static final LatLng TEMPLE8 = new LatLng(7.158536, 100.556677);
    private static final LatLng TEMPLE9 = new LatLng(7.181771, 100.621692);
    ////////////////////// BEACH  /////////////////////////
    private static final LatLng BEACH1 = new LatLng(7.215329, 100.596055);
    private static final LatLng BEACH2 = new LatLng(7.474879, 100.445639);
    private static final LatLng BEACH3 = new LatLng(7.215199, 100.581568);
    private static final LatLng BEACH4 = new LatLng(7.208117, 100.600841);
    private static final LatLng BEACH5 = new LatLng(7.162471, 100.543675);
    private static final LatLng BEACH6 = new LatLng(7.463181, 100.411903);
    ///////////////////// FOREST //////////////////////////
    private static final LatLng FOREST1 = new LatLng(7.043051, 100.504517);
    private static final LatLng FOREST2 = new LatLng(7.142959, 100.604308);
    private static final LatLng FOREST3 = new LatLng(7.210698, 100.589309);
    private static final LatLng FOREST4 = new LatLng(6.949963, 100.234029);
    private static final LatLng FOREST5 = new LatLng(7.162984, 100.545003);
    private static final LatLng FOREST6 = new LatLng(7.226604, 100.577453);
    private static final LatLng FOREST7 = new LatLng(7.016946, 100.519999);
    private static final LatLng FOREST8 = new LatLng(7.1484416, 100.5585634);
    ///////////////////// MUSEUM //////////////////////////
    private static final LatLng MUSEUM1 = new LatLng(7.148305, 100.560823);
    private static final LatLng MUSEUM2 = new LatLng(7.198590, 100.588926);
    private static final LatLng MUSEUM3 = new LatLng(7.202020, 100.590272);
    private static final LatLng MUSEUM4 = new LatLng(7.225363, 100.579940);
    private static final LatLng MUSEUM5 = new LatLng(7.181858, 100.542536);
    private static final LatLng MUSEUM6 = new LatLng(7.292714, 100.481424);
    private static final LatLng MUSEUM7 = new LatLng(7.007695, 100.497205);
    private static final LatLng MUSEUM8 = new LatLng(7.001460, 100.493606);
    private static final LatLng MUSEUM9 = new LatLng(7.181864, 100.542539);
    private static final LatLng MUSEUM10 = new LatLng(7.205061, 100.596960);
    private static final LatLng MUSEUM11 = new LatLng(6.574177, 100.575289);
    private static final LatLng MUSEUM12 = new LatLng(7.202392, 100.588636);
    /////////////////////  FOOD   /////////////////////////
    private static final LatLng FOOD1 = new LatLng(6.999580, 100.473827);
    private static final LatLng FOOD2 = new LatLng(7.001943, 100.475702);
    private static final LatLng FOOD3 = new LatLng(7.003968, 100.473757);
    private static final LatLng FOOD4 = new LatLng(7.007533, 100.475726);
    private static final LatLng FOOD5 = new LatLng(7.177489, 100.544271);
    private static final LatLng FOOD6 = new LatLng(6.985921, 100.466633);
    private static final LatLng FOOD7 = new LatLng(7.1389047, 100.5256879);
    private static final LatLng FOOD8 = new LatLng(7.0140848, 100.4899853);
    private static final LatLng FOOD9 = new LatLng(7.0092824, 100.4683067);
    private static final LatLng FOOD10 = new LatLng(6.955586, 100.4023423);
    private static final LatLng FOOD11 = new LatLng(6.994188, 100.4712655);
    private static final LatLng FOOD12 = new LatLng(7.0065473, 100.4689731);
    private static final LatLng FOOD13 = new LatLng(7.1958635, 100.5885067);
    private static final LatLng FOOD14 = new LatLng(7.0054348, 100.4898569);
    private static final LatLng FOOD15 = new LatLng(7.0037704, 100.4771873);

    /////////////////////  SHOP   /////////////////////////
    private static final LatLng SHOP1 = new LatLng(7.914164, 100.308775);
    private static final LatLng SHOP2 = new LatLng(6.997046, 100.487036);
    private static final LatLng SHOP3 = new LatLng(6.994744, 100.484619);
    private static final LatLng SHOP4 = new LatLng(7.007638, 100.469986);
    private static final LatLng SHOP5 = new LatLng(6.991594, 100.482941);
    private static final LatLng SHOP6 = new LatLng(7.005588, 100.471721);
    private static final LatLng SHOP7 = new LatLng(6.997726, 100.478616);
    /////////////////////  GAS   /////////////////////////
    private static final LatLng GAS1 = new LatLng(7.008915, 100.494852);
    private static final LatLng GAS2 = new LatLng(6.998113, 100.480091);
    private static final LatLng GAS3 = new LatLng(7.027391, 100.476024);
    private static final LatLng GAS4 = new LatLng(7.006292, 100.462003);
    private static final LatLng GAS5 = new LatLng(7.006077, 100.462003);
    ///LIKE//////
    private Marker msongkhla,TOP1,TOP2,TOP3,TOP4,TOP5,TOP6,TOP7,TOP8;
    ///TENPLE////
    private Marker TP1, TP2, TP3, TP4, TP5, TP6, TP7, TP8, TP9;
    ///BEACH/////
    private Marker BE1, BE2, BE3, BE4, BE5, BE6;
    ///FOREST////
    private Marker FOR1, FOR2, FOR3, FOR4, FOR5, FOR6, FOR7, FOR8;
    ///MUSEUM////
    private Marker MU1, MU2, MU3, MU4, MU5, MU6, MU7, MU8, MU9, MU10, MU11, MU12;
    ///FOOD////
    private Marker FO1, FO2, FO3, FO4, FO5, FO6, FO7, FO8, FO9, FO10, FO11, FO12, FO13, FO14, FO15;
    ///SHOP////
    private Marker  SH1, SH2, SH3, SH4, SH5, SH6, SH7;
    ///SHOP////
    private Marker  GA1, GA2, GA3, GA4, GA5;

    private static final int ACTIVITY_NUM=3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        setNavi();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mGps = (ImageView) findViewById(R.id.ic_gps2);
        mPlacePicker = (ImageView) findViewById(R.id.place_picker);
        mStar = (ImageView) findViewById(R.id.star);
        mDelect = (ImageView) findViewById(R.id.ic_delect);

        TempleM1 = (ImageView) findViewById(R.id.TempleM);
        BeachM1 = (ImageView) findViewById(R.id.BeachM);
        ForestM1 = (ImageView) findViewById(R.id.ForestM);
        MuseumM1 = (ImageView) findViewById(R.id.MuseumM);
        FoodM1 = (ImageView) findViewById(R.id.FoodM);
        ShopM1 = (ImageView) findViewById(R.id.ShopM);
        GasM1 = (ImageView) findViewById(R.id.GasM);
        getLocationPermission();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLng(songkhla));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
        if (ClickOpenMark == true) {
            mMap.clear();
            ShowMarkLike(mMap);
            ClickOpenMark = false;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        init();
    }

    private static final String TAG = "Map2Activity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;
    private static final int PLACE_PICKER_REQUEST = 1;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40, -168), new LatLng(71, 136));

    private ImageView mGps, mPlacePicker, mStar, mDelect;
    private ImageView TempleM1, BeachM1, ForestM1, MuseumM1, FoodM1, ShopM1, GasM1;
    double latitude, longitude;
    public static final int REQUEST_LOCATION_CODE = 99;
    int PROXIMITY_RADIUS = 10000;

    private Boolean mLocationPermissionsGranted = false;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private GoogleApiClient mGoogleApiClient;
    private PlaceInfo mPlace;
    private Marker mMarker;
    public boolean ClickOpenMark = true;
    public boolean ClickOpenMarkTP = true;
    public boolean ClickOpenMarkBE = true;
    public boolean ClickOpenMarkFO = true;
    public boolean ClickOpenMarkSH = true;
    public boolean ClickOpenMarkFOR = true;
    public boolean ClickOpenMarkMU = true;
    public boolean ClickOpenMarkGA = true;
    private void init(){
        Log.d(TAG, "init: initializing");



        mGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick:  clicked gps icon");
                getDeviceLocation();
            }
        });




        mStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClickOpenMark == true) {
                    ShowMarkLike(mMap);
                    ClickOpenMark = false;

                }
                else if (ClickOpenMark == false){
                    ClickOpenMark = true;
                    msongkhla.remove();
                    TOP1.remove();
                    TOP2.remove();
                    TOP3.remove();
                    TOP4.remove();
                    TOP5.remove();
                    TOP6.remove();
                    TOP7.remove();
                    TOP8.remove();
                }
            }
        });

        mDelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.clear();
                ClickOpenMark = true;
                ClickOpenMarkTP = true;
                ClickOpenMarkBE = true;
                ClickOpenMarkFO = true;
                ClickOpenMarkSH = true;
                ClickOpenMarkFOR = true;
                ClickOpenMarkMU = true;
                ClickOpenMarkGA = true;
            }
        });

        TempleM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClickOpenMarkTP == true) {
                    ShowMarkTemple(mMap);
                    ClickOpenMarkTP = false;

                }
                else if (ClickOpenMarkTP == false){
                    ClickOpenMarkTP = true;
                    TP1.remove();
                    TP2.remove();
                    TP3.remove();
                    TP4.remove();
                    TP5.remove();
                    TP6.remove();
                    TP7.remove();
                    TP8.remove();
                    TP9.remove();

                }
            }
        });
        BeachM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClickOpenMarkBE == true) {
                    ShowMarkBeach(mMap);
                    ClickOpenMarkBE = false;
                }
                else if (ClickOpenMarkBE == false){
                    ClickOpenMarkBE = true;
                    BE1.remove();
                    BE2.remove();
                    BE3.remove();
                    BE4.remove();
                    BE5.remove();
                    BE6.remove();

                }
            }
        });
        ForestM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClickOpenMarkFOR == true) {
                    ShowMarkForest(mMap);
                    ClickOpenMarkFOR = false;
                }
                else if (ClickOpenMarkFOR == false){
                    ClickOpenMarkFOR = true;
                    FOR1.remove();
                    FOR2.remove();
                    FOR3.remove();
                    FOR4.remove();
                    FOR5.remove();
                    FOR6.remove();
                    FOR7.remove();
                    FOR8.remove();

                }
            }
        });
        MuseumM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClickOpenMarkMU == true) {
                    ShowMarkMuseum(mMap);
                    ClickOpenMarkMU = false;
                }
                else if (ClickOpenMarkMU == false){
                    ClickOpenMarkMU = true;
                    MU1.remove();
                    MU2.remove();
                    MU3.remove();
                    MU4.remove();
                    MU5.remove();
                    MU6.remove();
                    MU7.remove();
                    MU8.remove();
                    MU9.remove();
                    MU10.remove();
                    MU11.remove();
                    MU12.remove();
                }
            }
        });
        FoodM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClickOpenMarkFO == true) {
                    ShowMarkFood(mMap);
                    ClickOpenMarkFO = false;
                }
                else if (ClickOpenMarkFO == false){
                    ClickOpenMarkFO = true;
                    FO1.remove();
                    FO2.remove();
                    FO3.remove();
                    FO4.remove();
                    FO5.remove();
                    FO6.remove();
                    FO7.remove();
                    FO8.remove();
                    FO9.remove();
                    FO10.remove();
                    FO11.remove();
                    FO12.remove();
                    FO13.remove();
                    FO14.remove();
                    FO15.remove();

                }
            }
        });
        ShopM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClickOpenMarkSH == true) {
                    ShowMarkShop(mMap);
                    ClickOpenMarkSH = false;
                }
                else if (ClickOpenMarkSH == false){
                    ClickOpenMarkSH = true;
                    SH1.remove();
                    SH2.remove();
                    SH3.remove();
                    SH4.remove();
                    SH5.remove();
                    SH6.remove();
                    SH7.remove();

                }
            }
        });
        GasM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClickOpenMarkGA == true) {
                    ShowMarkGas(mMap);
                    ClickOpenMarkGA = false;
                }
                else if (ClickOpenMarkGA == false){
                    ClickOpenMarkGA = true;
                    GA1.remove();
                    GA2.remove();
                    GA3.remove();
                    GA4.remove();
                    GA5.remove();
                }
            }
        });
        hideSoftKeyboard();
    }
    public void onZoom(View view){
        if(view.getId() == R.id.zoomin){
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }
        if(view.getId() == R.id.zoomout){
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }

    public void ShowPopupMenu(View v){
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menumap, popup.getMenu());
        popup.setOnMenuItemClickListener(Maptest2.this);

        Object menuHelper;
        Class[] argTypes;
        try {
            Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
            fMenuHelper.setAccessible(true);
            menuHelper = fMenuHelper.get(popup);
            argTypes = new Class[]{boolean.class};
            menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
        } catch (Exception e) {

        }
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.TempleM:
                if(ClickOpenMarkTP == true) {
                    ShowMarkTemple(mMap);
                    ClickOpenMarkTP = false;
                }
                else if (ClickOpenMarkTP == false){
                    ClickOpenMarkTP = true;
                    TP1.remove();
                    TP2.remove();
                    TP3.remove();
                    TP4.remove();
                    TP5.remove();
                    TP6.remove();
                    TP7.remove();
                    TP8.remove();
                    TP9.remove();

                }
                return true;
            case R.id.BeachM:
                if(ClickOpenMarkBE == true) {
                    ShowMarkBeach(mMap);
                    ClickOpenMarkBE = false;
                }
                else if (ClickOpenMarkBE == false){
                    ClickOpenMarkBE = true;
                    BE1.remove();
                    BE2.remove();
                    BE3.remove();
                    BE4.remove();
                    BE5.remove();
                    BE6.remove();

                }
                return true;
            case R.id.ForestM:
                if(ClickOpenMarkFOR == true) {
                    ShowMarkForest(mMap);
                    ClickOpenMarkFOR = false;
                }
                else if (ClickOpenMarkFOR == false){
                    ClickOpenMarkFOR = true;
                    FOR1.remove();
                    FOR2.remove();
                    FOR3.remove();
                    FOR4.remove();
                    FOR5.remove();
                    FOR6.remove();
                    FOR7.remove();
                    FOR8.remove();
                }
                return true;
            case R.id.MuseumM:
                if(ClickOpenMarkMU == true) {
                    ShowMarkMuseum(mMap);
                    ClickOpenMarkMU = false;
                }
                else if (ClickOpenMarkMU == false){
                    ClickOpenMarkMU = true;
                    MU1.remove();
                    MU2.remove();
                    MU3.remove();
                    MU4.remove();
                    MU5.remove();
                    MU6.remove();
                    MU7.remove();
                    MU8.remove();
                    MU9.remove();
                    MU10.remove();
                    MU11.remove();
                    MU12.remove();
                }
                return true;
            case R.id.FoodM:
                if(ClickOpenMarkFO == true) {
                    ShowMarkFood(mMap);
                    ClickOpenMarkFO = false;
                }
                else if (ClickOpenMarkFO == false){
                    ClickOpenMarkFO = true;
                    FO1.remove();
                    FO2.remove();
                    FO3.remove();
                    FO4.remove();
                    FO5.remove();
                    FO6.remove();
                    FO7.remove();
                    FO8.remove();
                    FO9.remove();
                    FO10.remove();
                    FO11.remove();
                    FO12.remove();
                    FO13.remove();
                    FO14.remove();
                    FO15.remove();

                }
                return true;
            case R.id.ShopM:
                if(ClickOpenMarkSH == true) {
                    ShowMarkShop(mMap);
                    ClickOpenMarkSH = false;
                }
                else if (ClickOpenMarkSH == false){
                    ClickOpenMarkSH = true;
                    SH1.remove();
                    SH2.remove();
                    SH3.remove();
                    SH4.remove();
                    SH5.remove();
                    SH6.remove();
                    SH7.remove();

                }
                return true;
            case R.id.GasM:
                if(ClickOpenMarkGA == true) {
                    ShowMarkGas(mMap);
                    ClickOpenMarkGA = false;
                }
                else if (ClickOpenMarkGA == false){
                    ClickOpenMarkGA = true;
                    GA1.remove();
                    GA2.remove();
                    GA3.remove();
                    GA4.remove();
                    GA5.remove();
                }
                return true;
            default:
                return false;
        }
    }

    public  void ShowMarkLike(GoogleMap mMap){
        msongkhla = mMap.addMarker(new MarkerOptions().position(songkhla).title(getString(R.string.MPS1)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicon)));;
        msongkhla.setTag(0);
        TOP1 = mMap.addMarker(new MarkerOptions().position(TOP1BEST).title(getString(R.string.MPS2)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconstar)));
        TOP1.setTag(0);
        TOP2 = mMap.addMarker(new MarkerOptions().position(TOP2BEST).title(getString(R.string.MPS3)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconstar)));
        TOP2.setTag(0);
        TOP3 = mMap.addMarker(new MarkerOptions().position(TOP3BEST).title(getString(R.string.MPS4)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconstar)));
        TOP3.setTag(0);
        TOP4 = mMap.addMarker(new MarkerOptions().position(TOP4BEST).title(getString(R.string.MPS5)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconstar)));
        TOP4.setTag(0);
        TOP5 = mMap.addMarker(new MarkerOptions().position(TOP5BEST).title(getString(R.string.MPS6)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconstar)));
        TOP5.setTag(0);
        TOP6 = mMap.addMarker(new MarkerOptions().position(TOP6BEST).title(getString(R.string.MPS7)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconstar)));
        TOP6.setTag(0);
        TOP7 = mMap.addMarker(new MarkerOptions().position(TOP7BEST).title(getString(R.string.MPS8)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconstar)));
        TOP7.setTag(0);
        TOP8 = mMap.addMarker(new MarkerOptions().position(TOP8BEST).title(getString(R.string.MPS9)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconstar)));
        TOP8.setTag(0);

    }
    public void ShowMarkTemple(GoogleMap mMap){
        //////////////TEMPLE///////////////////////
        TP1 = mMap.addMarker(new MarkerOptions().position(TEMPLE1).title(getString(R.string.MPT1)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicontemple)));
        TP1.setTag(0);
        TP2 = mMap.addMarker(new MarkerOptions().position(TEMPLE2).title(getString(R.string.MPT2)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicontemple)));
        TP2.setTag(0);
        TP3 = mMap.addMarker(new MarkerOptions().position(TEMPLE3).title(getString(R.string.MPT3)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicontemple)));
        TP3.setTag(0);
        TP4 = mMap.addMarker(new MarkerOptions().position(TEMPLE4).title(getString(R.string.MPT4)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicontemple)));
        TP4.setTag(0);
        TP5 = mMap.addMarker(new MarkerOptions().position(TEMPLE5).title(getString(R.string.MPT5)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicontemple)));
        TP5.setTag(0);
        TP6 = mMap.addMarker(new MarkerOptions().position(TEMPLE6).title(getString(R.string.MPT6)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicontemple)));
        TP6.setTag(0);
        TP7 = mMap.addMarker(new MarkerOptions().position(TEMPLE7).title(getString(R.string.MPT7)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicontemple)));
        TP7.setTag(0);
        TP8 = mMap.addMarker(new MarkerOptions().position(TEMPLE8).title(getString(R.string.MPT8)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicontemple)));
        TP8.setTag(0);
        TP9 = mMap.addMarker(new MarkerOptions().position(TEMPLE9).title(getString(R.string.MPT9)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicontemple)));
        TP9.setTag(0);

    }
    public void ShowMarkBeach(GoogleMap mMap){
        //////////////BEACH////////////////////////
        BE1 = mMap.addMarker(new MarkerOptions().position(BEACH1).title(getString(R.string.MPB1)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconbeach)));
        BE1.setTag(0);
        BE2 = mMap.addMarker(new MarkerOptions().position(BEACH2).title(getString(R.string.MPB2)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconbeach)));
        BE2.setTag(0);
        BE3 = mMap.addMarker(new MarkerOptions().position(BEACH3).title(getString(R.string.MPB3)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconbeach)));
        BE3.setTag(0);
        BE4 = mMap.addMarker(new MarkerOptions().position(BEACH4).title(getString(R.string.MPB4)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconbeach)));
        BE4.setTag(0);
        BE5 = mMap.addMarker(new MarkerOptions().position(BEACH5).title(getString(R.string.MPB5)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconbeach)));
        BE5.setTag(0);
        BE6 = mMap.addMarker(new MarkerOptions().position(BEACH6).title(getString(R.string.MPB6)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconbeach)));
        BE6.setTag(0);

    }
    public void ShowMarkForest(GoogleMap mMap){
        //////////////FOREST///////////////////////
        FOR1 = mMap.addMarker(new MarkerOptions().position(FOREST1).title(getString(R.string.MPF1)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconforest)));
        FOR1.setTag(0);
        FOR2 = mMap.addMarker(new MarkerOptions().position(FOREST2).title(getString(R.string.MPF2)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconforest)));
        FOR2.setTag(0);
        FOR3 = mMap.addMarker(new MarkerOptions().position(FOREST3).title(getString(R.string.MPF3)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconforest)));
        FOR3.setTag(0);
        FOR4 = mMap.addMarker(new MarkerOptions().position(FOREST4).title(getString(R.string.MPF4)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconforest)));
        FOR4.setTag(0);
        FOR5 = mMap.addMarker(new MarkerOptions().position(FOREST5).title(getString(R.string.MPF5)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconforest)));
        FOR5.setTag(0);
        FOR6 = mMap.addMarker(new MarkerOptions().position(FOREST6).title(getString(R.string.MPF6)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconforest)));
        FOR6.setTag(0);
        FOR7 = mMap.addMarker(new MarkerOptions().position(FOREST7).title(getString(R.string.MPF7)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconforest)));
        FOR7.setTag(0);
        FOR8 = mMap.addMarker(new MarkerOptions().position(FOREST8).title(getString(R.string.MPF8)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconforest)));
        FOR8.setTag(0);
    }
    public void ShowMarkMuseum(GoogleMap mMap){
        //////////////MUSEUM///////////////////////
        MU1 = mMap.addMarker(new MarkerOptions().position(MUSEUM1).title(getString(R.string.MPM1)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
        MU1.setTag(0);
        MU2 = mMap.addMarker(new MarkerOptions().position(MUSEUM2).title(getString(R.string.MPM2)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
        MU2.setTag(0);
        MU3 = mMap.addMarker(new MarkerOptions().position(MUSEUM3).title(getString(R.string.MPM3)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
        MU3.setTag(0);
        MU4 = mMap.addMarker(new MarkerOptions().position(MUSEUM4).title(getString(R.string.MPM4)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
        MU4.setTag(0);
        MU5 = mMap.addMarker(new MarkerOptions().position(MUSEUM5).title(getString(R.string.MPM5)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
        MU5.setTag(0);
        MU6 = mMap.addMarker(new MarkerOptions().position(MUSEUM6).title(getString(R.string.MPM6)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
        MU6.setTag(0);
        MU7 = mMap.addMarker(new MarkerOptions().position(MUSEUM7).title(getString(R.string.MPM7)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
        MU7.setTag(0);
        MU8 = mMap.addMarker(new MarkerOptions().position(MUSEUM8).title(getString(R.string.MPM8)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
        MU8.setTag(0);
        MU9 = mMap.addMarker(new MarkerOptions().position(MUSEUM9).title(getString(R.string.MPM9)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
        MU9.setTag(0);
        MU10 = mMap.addMarker(new MarkerOptions().position(MUSEUM10).title(getString(R.string.MPM10)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
        MU10.setTag(0);
        MU11 = mMap.addMarker(new MarkerOptions().position(MUSEUM11).title(getString(R.string.MPM11)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
        MU11.setTag(0);
        MU12 = mMap.addMarker(new MarkerOptions().position(MUSEUM12).title(getString(R.string.MPM12)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
        MU12.setTag(0);

    }
    public void ShowMarkFood(GoogleMap mMap){
        //////////////FOOD/////////////////////////
        FO1 = mMap.addMarker(new MarkerOptions().position(FOOD1).title(getString(R.string.MPFF1)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconfood)));
        FO1.setTag(0);
        FO2 = mMap.addMarker(new MarkerOptions().position(FOOD2).title(getString(R.string.MPFF2)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconfood)));
        FO2.setTag(0);
        FO3 = mMap.addMarker(new MarkerOptions().position(FOOD3).title(getString(R.string.MPFF3)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconfood)));
        FO3.setTag(0);
        FO4 = mMap.addMarker(new MarkerOptions().position(FOOD4).title(getString(R.string.MPFF4)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconfood)));
        FO4.setTag(0);
        FO5 = mMap.addMarker(new MarkerOptions().position(FOOD5).title(getString(R.string.MPFF5)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconfood)));
        FO5.setTag(0);
        FO6 = mMap.addMarker(new MarkerOptions().position(FOOD6).title(getString(R.string.MPFF6)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconfood)));
        FO6.setTag(0);
        FO7 = mMap.addMarker(new MarkerOptions().position(FOOD7).title(getString(R.string.MPFF7)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconfood)));
        FO7.setTag(0);
        FO8 = mMap.addMarker(new MarkerOptions().position(FOOD8).title(getString(R.string.MPFF8)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconfood)));
        FO8.setTag(0);
        FO9 = mMap.addMarker(new MarkerOptions().position(FOOD9).title(getString(R.string.MPFF9)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconfood)));
        FO9.setTag(0);
        FO10 = mMap.addMarker(new MarkerOptions().position(FOOD10).title(getString(R.string.MPFF10)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconfood)));
        FO10.setTag(0);
        FO11 = mMap.addMarker(new MarkerOptions().position(FOOD11).title(getString(R.string.MPFF11)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconfood)));
        FO11.setTag(0);
        FO12 = mMap.addMarker(new MarkerOptions().position(FOOD12).title(getString(R.string.MPFF12)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconfood)));
        FO12.setTag(0);
        FO13 = mMap.addMarker(new MarkerOptions().position(FOOD13).title(getString(R.string.MPFF13)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconfood)));
        FO13.setTag(0);
        FO14 = mMap.addMarker(new MarkerOptions().position(FOOD14).title(getString(R.string.MPFF14)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconfood)));
        FO14.setTag(0);
        FO15 = mMap.addMarker(new MarkerOptions().position(FOOD15).title(getString(R.string.MPFF15)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconfood)));
        FO15.setTag(0);

    }
    public void ShowMarkShop(GoogleMap mMap){
        //////////////SHOP/////////////////////////
        SH1 = mMap.addMarker(new MarkerOptions().position(SHOP1).title(getString(R.string.MPSS1)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconshop)));
        SH1.setTag(0);
        SH2 = mMap.addMarker(new MarkerOptions().position(SHOP2).title(getString(R.string.MPSS2)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconshop)));
        SH2.setTag(0);
        SH3 = mMap.addMarker(new MarkerOptions().position(SHOP3).title(getString(R.string.MPSS3)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconshop)));
        SH3.setTag(0);
        SH4 = mMap.addMarker(new MarkerOptions().position(SHOP4).title(getString(R.string.MPSS4)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconshop)));
        SH4.setTag(0);
        SH5 = mMap.addMarker(new MarkerOptions().position(SHOP5).title(getString(R.string.MPSS5)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconshop)));
        SH5.setTag(0);
        SH6 = mMap.addMarker(new MarkerOptions().position(SHOP6).title(getString(R.string.MPSS6)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconshop)));
        SH6.setTag(0);
        SH7 = mMap.addMarker(new MarkerOptions().position(SHOP7).title(getString(R.string.MPSS7)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconshop)));
        SH7.setTag(0);

    }
    public void ShowMarkGas(GoogleMap mMap){
        GA1 = mMap.addMarker(new MarkerOptions().position(GAS1).title(getString(R.string.MPG1)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicongas)));
        GA1.setTag(0);
        GA2 = mMap.addMarker(new MarkerOptions().position(GAS2).title(getString(R.string.MPG2)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicongas)));
        GA2.setTag(0);
        GA3 = mMap.addMarker(new MarkerOptions().position(GAS3).title(getString(R.string.MPG3)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicongas)));
        GA3.setTag(0);
        GA4 = mMap.addMarker(new MarkerOptions().position(GAS4).title(getString(R.string.MPG4)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicongas)));
        GA4.setTag(0);
        GA5 = mMap.addMarker(new MarkerOptions().position(GAS5).title(getString(R.string.MPG5)).icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicongas)));
        GA5.setTag(0);
    }
    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if(mLocationPermissionsGranted){

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()&& task.getResult() != null){
                            Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                    DEFAULT_ZOOM,
                                    "My Location");

                        }else {
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(Maptest2.this, "unable to get current location",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }

    private  void moveCamera(LatLng latLng, float zoom, PlaceInfo placeInfo){
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        mMap.clear();
        ClickOpenMark = true;

        /////////////BUG//////////////////////
        //mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(Map2Activity.this));


        if (placeInfo != null){
            try{
                String snippet = "Address: " + placeInfo.getAddress() + "\n" +
                        "Phone Number: " + placeInfo.getPhoneNumber() + "\n" +
                        "Website: " + placeInfo.getWebsiteUri() + "\n" +
                        "Price Ratind: " + placeInfo.getRating() + "\n" ;
                MarkerOptions options = new MarkerOptions()
                        .position(latLng)
                        .title(placeInfo.getName())
                        .snippet(snippet);
                mMarker = mMap.addMarker(options);

            }catch (NullPointerException e){
                Log.e(TAG, "moveCamera: NullPointerException: " + e.getMessage() );
            }
        }else{
            mMap.addMarker(new MarkerOptions().position(latLng));
        }

        hideSoftKeyboard();
    }

    private  void moveCamera(LatLng latLng, float zoom, String title){
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        if (!title.equals("My Location")){
            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title);
            mMap.addMarker(options);
        }

        hideSoftKeyboard();
    }

    private void initMap(){
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(Maptest2.this);
    }

    private void  getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initMap();
            }else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        switch (requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if (grantResults.length > 0){
                    for (int i = 0; i < grantResults.length; i++){
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    initMap();
                }
            }
        }
    }

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent goSet = new Intent(Maptest2.this,Setting.class);
            startActivity(goSet);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}