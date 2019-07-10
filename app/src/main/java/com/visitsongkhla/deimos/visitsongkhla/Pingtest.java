package com.visitsongkhla.deimos.visitsongkhla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.app.Activity;
import com.google.android.libraries.places.api.model.Place;
import com.rtchagas.pingplacepicker.PingPlacePicker;
import androidx.appcompat.app.AppCompatActivity;


public class Pingtest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pingtest);
        showPlacePicker();

    } private void showPlacePicker() {
        PingPlacePicker.IntentBuilder builder = new PingPlacePicker.IntentBuilder();
        builder.setAndroidApiKey("AIzaSyBg9g4o6-vVxylkWfD57rZAnLL4OPNxOb8")
                .setGeocodingApiKey("AIzaSyDKbjyDV5yZSU5YrAxDuBwaJk7TO5UzFqI");
        try {
            Intent placeIntent = builder.build(this);
            startActivityForResult(placeIntent, 1001);
        }
        catch (Exception ex) {
            // Google Play services is not available...
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 1001) && (resultCode == RESULT_OK)) {
            Place place = PingPlacePicker.Companion.getPlace(data);
            if (place != null) {
                Toast.makeText(this, "You selected the place: " + place.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
