package com.example.deimos.visitsongkhla;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class Language extends AppCompatActivity implements View.OnClickListener{

    private CardView Lan1, Lan2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language);

        Lan1 = (CardView)findViewById(R.id.Lan1);
        Lan2 = (CardView)findViewById(R.id.Lan2);


        Lan1.setOnClickListener(this);
        Lan2.setOnClickListener(this);

    }
    public void onClick(View view) {
        if (view == Lan1) {
            /*setLocate("th");
            recreate();
            startActivity(new Intent(this, MainActivity.class));*/
        }
        if (view == Lan2) {
            /*setLocate("en");
            recreate();
            startActivity(new Intent(this, MainActivity.class));*/
        }
    }
}
