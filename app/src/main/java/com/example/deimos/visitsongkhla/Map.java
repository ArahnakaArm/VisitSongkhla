package com.example.deimos.visitsongkhla;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deimos.visitsongkhla.PlaceInfo;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class Map extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener,LocationListener,PopupMenu.OnMenuItemClickListener
        ,com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks
    {

        GoogleApiClient mGoogleApiClient2;
        LocationRequest mLocationRequest;
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
        private Marker FOR1, FOR2, FOR3, FOR4, FOR5, FOR6, FOR7;
        ///MUSEUM////
        private Marker MU1, MU2, MU3, MU4, MU5, MU6, MU7, MU8, MU9, MU10, MU11, MU12;
        ///FOOD////
        private Marker FO1, FO2, FO3, FO4, FO5;
        ///SHOP////
        private Marker  SH1, SH2, SH3, SH4, SH5, SH6, SH7;
        ///SHOP////
        private Marker  GA1, GA2, GA3, GA4, GA5;

        private static final int ACTIVITY_NUM=3;

        public void setMarkLocation(){

        }
        @Override
        public void onMapReady(GoogleMap googleMap) {
            //Toast.makeText(this, "เเผนที่พร้อมใช้งาน", Toast.LENGTH_LONG).show();
            Log.d(TAG, "onMapReady: map is ready");
            mMap = googleMap;
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            //mStar.performClick();

            if (mLocationPermissionsGranted) {
                //getDeviceLocation();
                mMap.moveCamera(CameraUpdateFactory.newLatLng(songkhla));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
                if(ClickOpenMark == true) {
                    mMap.clear();
                    ShowMarkLike(mMap);
                    ClickOpenMark = false;
                }

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    return;
                }
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                init();
            }
        }

        private static final String TAG = "Map2Activity";

        private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
        private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
        private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
        private static final float DEFAULT_ZOOM = 15f;
        private static final int PLACE_PICKER_REQUEST = 1;
        private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
                new LatLng(-40, -168), new LatLng(71, 136));


        private AutoCompleteTextView mSearchText;
        private ImageView mGps, mPlacePicker, mStar, mDelect;
        private ImageView TempleM1, BeachM1, ForestM1, MuseumM1, FoodM1, ShopM1, GasM1;

        double latitude,longitude;
        public static final int REQUEST_LOCATION_CODE = 99;
        int PROXIMITY_RADIUS = 10000;



        private Boolean mLocationPermissionsGranted = false;
        private GoogleMap mMap;
        private FusedLocationProviderClient mFusedLocationProviderClient;
        private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        setNavi();

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setNumUpdates(1);
        mLocationRequest.setMaxWaitTime(10000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mGoogleApiClient2 = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mSearchText = (AutoCompleteTextView) findViewById(R.id.input_search2);
        mGps = (ImageView) findViewById(R.id.ic_gps2);
        mPlacePicker = (ImageView) findViewById(R.id.place_picker);
        mStar = (ImageView) findViewById(R.id.star);
        mDelect = (ImageView) findViewById(R.id.ic_delect);

        TempleM1 = (ImageView)findViewById(R.id.TempleM);
        BeachM1 = (ImageView)findViewById(R.id.BeachM);
        ForestM1 = (ImageView)findViewById(R.id.ForestM);
        MuseumM1 = (ImageView)findViewById(R.id.MuseumM);
        FoodM1 = (ImageView)findViewById(R.id.FoodM);
        ShopM1 = (ImageView)findViewById(R.id.ShopM);
        GasM1 = (ImageView)findViewById(R.id.GasM);
        getLocationPermission();

    }
        private void init(){
            Log.d(TAG, "init: initializing");

            mGoogleApiClient = new GoogleApiClient
                    .Builder(this)
                    .addApi(Places.GEO_DATA_API)
                    .addApi(Places.PLACE_DETECTION_API)
                    .enableAutoManage(this, this)
                    .build();

            mSearchText.setOnItemClickListener(mAutocompleteClickListener);
            mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(this, mGoogleApiClient,
                    LAT_LNG_BOUNDS, null);

            mSearchText.setAdapter(mPlaceAutocompleteAdapter);

            mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if(actionId == EditorInfo.IME_ACTION_SEARCH
                            || actionId == EditorInfo.IME_ACTION_DONE
                            || event.getAction() == KeyEvent.ACTION_DOWN
                            || event.getAction() == KeyEvent.KEYCODE_ENTER){
                        geoLocate();
                    }

                    return false;
                }
            });

            mGps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick:  clicked gps icon");
                    getDeviceLocation();
                }
            });


            mPlacePicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                    try {
                        startActivityForResult(builder.build(Map.this), PLACE_PICKER_REQUEST);
                    } catch (GooglePlayServicesRepairableException e) {
                        Log.e(TAG, "onClick: GooglePlayServicesRepairableException: " + e.getMessage() );
                    } catch (GooglePlayServicesNotAvailableException e) {
                        Log.e(TAG, "onClick: GooglePlayServicesNotAvailableException: " + e.getMessage() );
                    }
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
            popup.setOnMenuItemClickListener(this);

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
            msongkhla = mMap.addMarker(new MarkerOptions().position(songkhla).title("จังหวัดสงขลา").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicon)));;
            msongkhla.setTag(0);
            TOP1 = mMap.addMarker(new MarkerOptions().position(TOP1BEST).title("แหลมสนอ่อน").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconstar)));
            TOP1.setTag(0);
            TOP2 = mMap.addMarker(new MarkerOptions().position(TOP2BEST).title("แหลมสมิหลา").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconstar)));
            TOP2.setTag(0);
            TOP3 = mMap.addMarker(new MarkerOptions().position(TOP3BEST).title("หาดชลาทัศน์").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconstar)));
            TOP3.setTag(0);
            TOP4 = mMap.addMarker(new MarkerOptions().position(TOP4BEST).title("เขาตังกวน").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconstar)));
            TOP4.setTag(0);
            TOP5 = mMap.addMarker(new MarkerOptions().position(TOP5BEST).title("เกาะยอ").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconstar)));
            TOP5.setTag(0);
            TOP6 = mMap.addMarker(new MarkerOptions().position(TOP6BEST).title("สถาบันทักษิณคดีศึกษา").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconstar)));
            TOP6.setTag(0);
            TOP7 = mMap.addMarker(new MarkerOptions().position(TOP7BEST).title("หาดมหาราช").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconstar)));
            TOP7.setTag(0);
            TOP8 = mMap.addMarker(new MarkerOptions().position(TOP8BEST).title("สวนสัตว์สงขลา").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconstar)));
            TOP8.setTag(0);

        }
        public void ShowMarkTemple(GoogleMap mMap){
            //////////////TEMPLE///////////////////////
            TP1 = mMap.addMarker(new MarkerOptions().position(TEMPLE1).title("มัสยิดกลางสงขลา").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicontemple)));
            TP1.setTag(0);
            TP2 = mMap.addMarker(new MarkerOptions().position(TEMPLE2).title("มัสยิตกลางดิย์นุสอิสลาม").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicontemple)));
            TP2.setTag(0);
            TP3 = mMap.addMarker(new MarkerOptions().position(TEMPLE3).title("วัดท้ายยอ").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicontemple)));
            TP3.setTag(0);
            TP4 = mMap.addMarker(new MarkerOptions().position(TEMPLE4).title("พระมหาธาตุเจดีย์ไตรภพไตรมงคล").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicontemple)));
            TP4.setTag(0);
            TP5 = mMap.addMarker(new MarkerOptions().position(TEMPLE5).title("วัดหาดใหญ่ใน").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicontemple)));
            TP5.setTag(0);
            TP6 = mMap.addMarker(new MarkerOptions().position(TEMPLE6).title("วัดแม่พระประจักษ์เมืองลูร์ด").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicontemple)));
            TP6.setTag(0);
            TP7 = mMap.addMarker(new MarkerOptions().position(TEMPLE7).title("ศาลเจ้าพ่อหลักเมืองสงขลา").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicontemple)));
            TP7.setTag(0);
            TP8 = mMap.addMarker(new MarkerOptions().position(TEMPLE8).title("วัดแหลมพ้อ").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicontemple)));
            TP8.setTag(0);
            TP9 = mMap.addMarker(new MarkerOptions().position(TEMPLE9).title("วัดเขาเก้าเส้ง").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicontemple)));
            TP9.setTag(0);

        }
        public void ShowMarkBeach(GoogleMap mMap){
            //////////////BEACH////////////////////////
            BE1 = mMap.addMarker(new MarkerOptions().position(BEACH1).title("แหลมสมิหลา").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconbeach)));
            BE1.setTag(0);
            BE2 = mMap.addMarker(new MarkerOptions().position(BEACH2).title("หาดมหาราช").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconbeach)));
            BE2.setTag(0);
            BE3 = mMap.addMarker(new MarkerOptions().position(BEACH3).title("แหลมสนอ่อน").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconbeach)));
            BE3.setTag(0);
            BE4 = mMap.addMarker(new MarkerOptions().position(BEACH4).title("หาดชลาทัศน์").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconbeach)));
            BE4.setTag(0);
            BE5 = mMap.addMarker(new MarkerOptions().position(BEACH5).title("เกาะยอ").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconbeach)));
            BE5.setTag(0);
            BE6 = mMap.addMarker(new MarkerOptions().position(BEACH6).title("อุทยานนกน้ำคูขุด").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconbeach)));
            BE6.setTag(0);

        }
        public void ShowMarkForest(GoogleMap mMap){
            //////////////FOREST///////////////////////
            FOR1 = mMap.addMarker(new MarkerOptions().position(FOREST1).title("สวนสาธารณะนครหาดใหญ่").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconforest)));
            FOR1.setTag(0);
            FOR2 = mMap.addMarker(new MarkerOptions().position(FOREST2).title("สวนสัตว์สงขลา").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconforest)));
            FOR2.setTag(0);
            FOR3 = mMap.addMarker(new MarkerOptions().position(FOREST3).title("เขาตังกวน").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconforest)));
            FOR3.setTag(0);
            FOR4 = mMap.addMarker(new MarkerOptions().position(FOREST4).title("น้ำตกโตนงาช้าง").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconforest)));
            FOR4.setTag(0);
            FOR5 = mMap.addMarker(new MarkerOptions().position(FOREST5).title("สวนลุงวี").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconforest)));
            FOR5.setTag(0);
            FOR6 = mMap.addMarker(new MarkerOptions().position(FOREST6).title("สวนสองทะเล").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconforest)));
            FOR6.setTag(0);
            FOR7 = mMap.addMarker(new MarkerOptions().position(FOREST7).title("เขาคอหงส์").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconforest)));
            FOR7.setTag(0);

        }
        public void ShowMarkMuseum(GoogleMap mMap){
            //////////////MUSEUM///////////////////////
            MU1 = mMap.addMarker(new MarkerOptions().position(MUSEUM1).title("สวนประวัติศาสตร์").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
            MU1.setTag(0);
            MU2 = mMap.addMarker(new MarkerOptions().position(MUSEUM2).title("ย่านเมืองเก่าสงขลา").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
            MU2.setTag(0);
            MU3 = mMap.addMarker(new MarkerOptions().position(MUSEUM3).title("กำแพงเมืองเก่าสงขลา").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
            MU3.setTag(0);
            MU4 = mMap.addMarker(new MarkerOptions().position(MUSEUM4).title("สถานแสดงพันธุ์สัตว์น้ำสงขลา").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
            MU4.setTag(0);
            MU5 = mMap.addMarker(new MarkerOptions().position(MUSEUM5).title("สถาบันทักษิณคดีศึกษา").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
            MU5.setTag(0);
            MU6 = mMap.addMarker(new MarkerOptions().position(MUSEUM6).title("บ้านรำแดง").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
            MU6.setTag(0);
            MU7 = mMap.addMarker(new MarkerOptions().position(MUSEUM7).title("พิพิธภัณฑสถานธรรมชาติวิทยา ๕๐ พรรษา สยามบรมราชกุมารี").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
            MU7.setTag(0);
            MU8 = mMap.addMarker(new MarkerOptions().position(MUSEUM8).title("หอศิลป์สยาม").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
            MU8.setTag(0);
            MU9 = mMap.addMarker(new MarkerOptions().position(MUSEUM9).title("พิพิธภัณฑ์คติชนวิทยา").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
            MU9.setTag(0);
            MU10 = mMap.addMarker(new MarkerOptions().position(MUSEUM10).title("พิพิธภัณฑ์ธนารักษ์").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
            MU10.setTag(0);
            MU11 = mMap.addMarker(new MarkerOptions().position(MUSEUM11).title("อุโมงประวัติศาสตร์เขาน้ำค้าง").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
            MU11.setTag(0);
            MU12 = mMap.addMarker(new MarkerOptions().position(MUSEUM12).title("พิพิธภัณฑสถานแห่งชาติสงขลา").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconmuseum)));
            MU12.setTag(0);

        }
        public void ShowMarkFood(GoogleMap mMap){
            //////////////FOOD/////////////////////////
            FO1 = mMap.addMarker(new MarkerOptions().position(FOOD1).title("หนานหยวน").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconfood)));
            FO1.setTag(0);
            FO2 = mMap.addMarker(new MarkerOptions().position(FOOD2).title("โชคดี แต่เตี้ยม").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconfood)));
            FO2.setTag(0);
            FO3 = mMap.addMarker(new MarkerOptions().position(FOOD3).title("ไก่ทอดเดชา").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconfood)));
            FO3.setTag(0);
            FO4 = mMap.addMarker(new MarkerOptions().position(FOOD4).title("เคนตะ หาดใหญ่").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconfood)));
            FO4.setTag(0);
            FO5 = mMap.addMarker(new MarkerOptions().position(FOOD5).title("น้ำเคียงดิน").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconfood)));
            FO5.setTag(0);

        }
        public void ShowMarkShop(GoogleMap mMap){
            //////////////SHOP/////////////////////////
            SH1 = mMap.addMarker(new MarkerOptions().position(SHOP1).title("ตลาดน้ำคลองแดน").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconshop)));
            SH1.setTag(0);
            SH2 = mMap.addMarker(new MarkerOptions().position(SHOP2).title("ตลาดกรีนเวย์").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconshop)));
            SH2.setTag(0);
            SH3 = mMap.addMarker(new MarkerOptions().position(SHOP3).title("ตลาดเปิดท้ายอาเซี่ยนเทรด").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconshop)));
            SH3.setTag(0);
            SH4 = mMap.addMarker(new MarkerOptions().position(SHOP4).title("ตลาดกิมหยง").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconshop)));
            SH4.setTag(0);
            SH5 = mMap.addMarker(new MarkerOptions().position(SHOP5).title("เซนทรัล เฟสติวัล หาดใหญ่").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconshop)));
            SH5.setTag(0);
            SH6 = mMap.addMarker(new MarkerOptions().position(SHOP6).title("ลีการ์เดน พลาซ่า").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconshop)));
            SH6.setTag(0);
            SH7 = mMap.addMarker(new MarkerOptions().position(SHOP7).title("ศูนย์การค้าไดอาน่าคอมเพล็กซ์").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapiconshop)));
            SH7.setTag(0);

        }
        public void ShowMarkGas(GoogleMap mMap){
            GA1 = mMap.addMarker(new MarkerOptions().position(GAS1).title("ปั๊มน้ำมันบางจาก ม.อ.").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicongas)));
            GA1.setTag(0);
            GA2 = mMap.addMarker(new MarkerOptions().position(GAS2).title("ปั้มน้ำมัน เอสโซ่").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicongas)));
            GA2.setTag(0);
            GA3 = mMap.addMarker(new MarkerOptions().position(GAS3).title("ปั๊มน้ำมันพีที").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicongas)));
            GA3.setTag(0);
            GA4 = mMap.addMarker(new MarkerOptions().position(GAS4).title("ปั๊มน้ำมันปตท.").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicongas)));
            GA4.setTag(0);
            GA5 = mMap.addMarker(new MarkerOptions().position(GAS5).title("ปั๊มน้ำมันบางจาก - หาดใหญ่ใน").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicongas)));
            GA5.setTag(0);
        }

        private String getUrl(double latitube, double longitube, String nearbyPlace)
        {
            StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
            googlePlaceUrl.append("location="+latitube+","+longitube);
            googlePlaceUrl.append("&radius="+PROXIMITY_RADIUS);
            googlePlaceUrl.append("&type="+nearbyPlace);
            googlePlaceUrl.append("&sensor=true");
            googlePlaceUrl.append("&key="+"AIzaSyAA1NFbVjPxDVa1YBkuROJYCsFC3QhM-54");

            Log.d("MapsActivity", "url = "+googlePlaceUrl.toString());

            return googlePlaceUrl.toString();
        }

        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == PLACE_PICKER_REQUEST) {
                if (resultCode == RESULT_OK) {
                    Place place = PlacePicker.getPlace(this, data);

                    PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                            .getPlaceById(mGoogleApiClient, place.getId());
                    placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
                }
            }

        }

        private void  geoLocate(){
            Log.d(TAG, "geoLocate: geolocating");

            String searchString = mSearchText.getText().toString();

            Geocoder geocoder = new Geocoder(Map.this);
            List<Address> list = new ArrayList<>();
            try {
                list = geocoder.getFromLocationName(searchString, 1);
            }catch (IOException e){
                Log.e(TAG, "geoLocate: IOException" + e.getMessage() );
            }

            if (list.size() > 0){
                Address address = list.get(0);

                Log.d(TAG, "geoLocate: found a location" + address.toString());
                //Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();

                moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM,
                        address.getAddressLine(0));
            }
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
                                Toast.makeText(Map.this, "unable to get current location",Toast.LENGTH_SHORT).show();
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

            mapFragment.getMapAsync(Map.this);
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

        private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hideSoftKeyboard();

                final AutocompletePrediction item = mPlaceAutocompleteAdapter.getItem(position);
                final String placeId = item.getPlaceId();

                PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                        .getPlaceById(mGoogleApiClient, placeId);
                placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
            }
        };

        private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
            @Override
            public void onResult(@NonNull PlaceBuffer places) {
                if (!places.getStatus().isSuccess()){
                    Log.d(TAG, "onResult: Place query did not complete successfully: " + places.getStatus().toString());
                    places.release();
                    return;
                }
                Place place = places.get(0);

                try {
                    mPlace = new PlaceInfo();
                    mPlace.setName(place.getName().toString());
                    Log.d(TAG, "onResult: name: " + place.getName());
                    mPlace.setAddress(place.getAddress().toString());
                    Log.d(TAG, "onResult: address: " + place.getAddress());
                    //mPlace.setAttributions(place.getAttributions().toString());
                    //Log.d(TAG, "onResult: Attributions: " + place.getAttributions());
                    mPlace.setId(place.getId());
                    Log.d(TAG, "onResult: id: " + place.getId());
                    mPlace.setLatlng(place.getLatLng());
                    Log.d(TAG, "onResult: Latlng: " + place.getLatLng());
                    mPlace.setRating(place.getRating());
                    Log.d(TAG, "onResult: rating: " + place.getRating());
                    mPlace.setPhoneNumber(place.getPhoneNumber().toString());
                    Log.d(TAG, "onResult: phoneNumber: " + place.getPhoneNumber());
                    mPlace.setWebsiteUri(place.getWebsiteUri());
                    Log.d(TAG, "onResult: Website url: " + place.getWebsiteUri());

                    Log.d(TAG, "onResult: place details: " + mPlace.toString());
                }catch (NullPointerException e){
                    Log.e(TAG, "onResult: NullPointerExeception: " + e.getMessage() );
                }

                moveCamera(new LatLng(place.getViewport().getCenter().latitude,
                        place.getViewport().getCenter().longitude), DEFAULT_ZOOM, mPlace);

                places.release();
            }
        };

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }



        @Override
        protected void onStart() {
            mGoogleApiClient2.connect();
            super.onStart();
        }

        @Override
        protected void onStop() {
            mGoogleApiClient2.disconnect();
            super.onStop();
        }
        @Override
        public void onLocationChanged(final Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            FirebaseDatabase database = FirebaseDatabase.getInstance();

            final DatabaseReference myRef = database.getReference("Location");
            if (location != null) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        String strLocation =
                                DateFormat.getTimeInstance().format(location.getTime()) + "\n" +
                                        "Latitude=" + location.getLatitude() + "\n" +
                                        "Longitude=" + location.getLongitude();

                        String key1 = myRef.push().getKey();
                        HashMap<String, Object> postValues1 = new HashMap<>();
                        postValues1.put("latt", location.getLatitude());
                        postValues1.put("long", location.getLongitude());

                        java.util.Map<String, Object> childUpdates1 = new HashMap<>();
                        childUpdates1.put( key1,postValues1);
                        // childUpdates.put("/user-messages/Jirawatee/" + key, postValues);

                        myRef.updateChildren(childUpdates1);
                    }
                }, 10000);
            }
        }

        @Override
        public void onConnected(@Nullable Bundle bundle) {

            if (ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                ActivityCompat.requestPermissions(Map.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

                return;
            }
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient2, mLocationRequest, this);

        }

        @Override
        public void onConnectionSuspended(int i) {
        }

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            Toast.makeText(Map.this,
                    "onConnectionFailed: \n" + connectionResult.toString(),
                    Toast.LENGTH_LONG).show();
        }

        public void setNavi(){
            BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
            BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
            BottomNavigationViewHelper.enableNavigation(Map.this,bottomNavigationViewEx);
            Menu menu =bottomNavigationViewEx.getMenu();

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
                Intent goSet = new Intent(Map.this,Setting.class);
                startActivity(goSet);
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
}
