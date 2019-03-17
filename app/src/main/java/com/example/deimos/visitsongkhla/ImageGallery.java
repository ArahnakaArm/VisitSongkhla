package com.example.deimos.visitsongkhla;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;
import java.util.Timer;
import java.util.TimerTask;

public class ImageGallery extends AppCompatActivity {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;


    private String[] urls = new String[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galleryimage);
        String Name = getIntent().getStringExtra("Name");
        String image1 = getIntent().getStringExtra("UrlList1");
        String image2 = getIntent().getStringExtra("UrlList2");
        String image3 = getIntent().getStringExtra("UrlList3");
        String image4 = getIntent().getStringExtra("UrlList4");
        String image5 = getIntent().getStringExtra("UrlList5");
        urls[0]=image1;
        urls[1]=image2;
        urls[2]=image3;
        urls[3]=image4;
        urls[4]=image5;
        TextView textName = findViewById(R.id.placeName);
        textName.setText(Name);

        init();
    }

    private void init() {

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(ImageGallery.this,urls));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = urls.length;

        // Auto start of viewpager
      /*  final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);*/

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }
}