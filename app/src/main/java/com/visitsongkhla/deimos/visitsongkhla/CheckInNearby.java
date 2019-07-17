package com.visitsongkhla.deimos.visitsongkhla;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class CheckInNearby extends AppCompatActivity {

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final String TAG = "CheckIn";
    private  RatingBar mRatingBar;
    private TextView mRatingScale;
    private EditText mFeedback;
    private Button mSendFeedback;
    private TextView mName;
    private static String Comment="comment";
    private  int rate;
    private static String UserKey;
    private static String Placesname="placename";
    private  static String UserTest="TestUser";
    static int cDay;
    static int cMonth;
    static int cSum;
    int checkLevel;
    static int cYear;
    static int cMonthh;
    Long tsLong = System.currentTimeMillis()/1000;
    String ts = tsLong.toString();
    private static final int ACTIVITY_NUM=2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkin_nearby);
        setNavi();
        final String deviceId = Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);

        //Toast.makeText(CheckIn.this,UserKey,Toast.LENGTH_LONG).show();
        //mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        //mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
        //  mFeedback = (EditText) findViewById(R.id.etFeedback);
        mSendFeedback = (Button) findViewById(R.id.btnSubmit);
        mName = (TextView)findViewById(R.id.textView2);
        SmileRating smileRating = (SmileRating) findViewById(R.id.smile_rating);
        Placesname = getIntent().getStringExtra("placename");

        Placesname = Placesname.replaceAll("[^0-9 a-z A-Z ก-ฮ  ะ-์ (-)]"," ");
        Calendar calendar = Calendar.getInstance();
        final     int thisY =calendar.get(Calendar.YEAR);
        final int thisM =calendar.get(Calendar.MONTH);
        final int thisD =calendar.get(Calendar.DAY_OF_MONTH);

        final String DATE = String.valueOf(thisD)+String.valueOf(thisM+1)+String.valueOf(thisY);
        mName.setText(Placesname);
        //Toast.makeText(this,Placesname,Toast.LENGTH_SHORT).show();
        //date

        Calendar calander = Calendar.getInstance();
        cDay = calander.get(Calendar.DAY_OF_MONTH);
        final String Day = Integer.toString(cDay);
        cMonth = calander.get(Calendar.MONTH);
        final String Month = Integer.toString(cMonth);
        cYear = calander.get(Calendar.YEAR);
        final String Year = Integer.toString(cYear);
        cSum=cDay+cMonth+cYear;
        final String Sum = Integer.toString(cSum);

        //Toast.makeText(Start.this,Day+"/"+Monthh+"/"+Year,Toast.LENGTH_LONG).show();





        //

        /*mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("Very bad");
                        rate=1;
                        break;
                    case 2:
                        mRatingScale.setText("Need some improvement");
                        rate=2;
                        break;
                    case 3:
                        mRatingScale.setText("Good");
                        rate=3;
                        break;
                    case 4:
                        mRatingScale.setText("Great");
                        rate=4;
                        break;
                    case 5:
                        mRatingScale.setText("Awesome. I love it");
                        rate=5;
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });*/

        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                switch (smiley) {
                    case SmileRating.BAD:

                        break;
                    case SmileRating.GOOD:

                        break;
                    case SmileRating.GREAT:

                        break;
                    case SmileRating.OKAY:

                        break;
                    case SmileRating.TERRIBLE:

                        break;
                }
            }


        });
        smileRating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            @Override
            public void onRatingSelected(int level, boolean reselected) {
                rate=level;
                checkLevel = level;

            }
        });

        smileRating.setNameForSmile(BaseRating.TERRIBLE,getString(R.string.R1));
        smileRating.setNameForSmile(BaseRating.BAD,getString(R.string.R2));
        smileRating.setNameForSmile(BaseRating.OKAY,getString(R.string.R3));
        smileRating.setNameForSmile(BaseRating.GOOD,getString(R.string.R4));
        smileRating.setNameForSmile(BaseRating.GREAT,getString(R.string.R5));

        mSendFeedback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(checkLevel == 0 ){
                    Toast.makeText(getApplicationContext(),getString(R.string.CRH),Toast.LENGTH_SHORT).show();
                }else {
                    //Comment = mFeedback.getText().toString();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference DeviceidRef = database.getReference("User-Places").child(deviceId).child("CheckIn");
                    DatabaseReference placeRef = database.getReference("Places").child(Placesname);
                    DatabaseReference Userdiary = database.getReference("UserAccount")/*.child(UserKey)*/;
                    String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                    Toast.makeText(getApplicationContext(), Placesname, Toast.LENGTH_SHORT);
                    String key = DeviceidRef.push().getKey();
                    HashMap<String, Object> postValues = new HashMap<>();
                    postValues.put("Rate", rate);
                    postValues.put("date", date);
                    postValues.put("title", Placesname);
                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put(ts, postValues);
                    DeviceidRef.updateChildren(childUpdates);


                    //  myRef.push().setValue(Placesname);

                    String key1 = placeRef.push().getKey();
                    HashMap<String, Object> postValues1 = new HashMap<>();
                    postValues1.put("Rate", rate);
                    Map<String, Object> childUpdates1 = new HashMap<>();
                    childUpdates1.put(key1, postValues1);
                    // childUpdates.put("/user-messages/Jirawatee/" + key, postValues);

                    placeRef.updateChildren(childUpdates1);


                    // childUpdates.put("/user-messages/Jirawatee/" + key, postValues);


                    //Toast.makeText(CheckIn.this,Placesname, Toast.LENGTH_LONG).show();
                    // myComRef.push().setValue(Comment);
              /*  } else {
                    Userdiary.push().setValue(Placesname);


                    //jn,jhk
                    String key = Userdiary.push().getKey();
                    HashMap<String, Object> postValues = new HashMap<>();
                    postValues.put("username", "Jirawatee");
                    postValues.put("text", "Hello World!");


                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put("/messages/" + key, postValues);
                    childUpdates.put("/user-messages/Jirawatee/" + key, postValues);

                    Userdiary.updateChildren(childUpdates);
                    //ghjgjh

                    mFeedback.setText("");
                    /////////////////////////
                    mRatingBar.setRating(0);
                    /////////////////
                   // Toast.makeText(CheckIn.this,Placesname, Toast.LENGTH_LONG).show();
                    myRef.push().setValue(rate);
                    myComRef.push().setValue(Comment);
                }*/


                    Intent goMap = new Intent(CheckInNearby.this, Diary.class);
                    startActivity(goMap);
                }
            }
        });
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

}
