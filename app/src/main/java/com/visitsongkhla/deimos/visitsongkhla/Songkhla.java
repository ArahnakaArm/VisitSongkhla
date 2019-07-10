package com.visitsongkhla.deimos.visitsongkhla;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Songkhla extends AppCompatActivity {

    private Button buttonAgreeBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songkhla);

        buttonAgreeBack = (Button)findViewById(R.id.buttonAgreeBack);
        buttonAgreeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
