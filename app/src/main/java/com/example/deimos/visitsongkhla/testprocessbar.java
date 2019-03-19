package com.example.deimos.visitsongkhla;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

public class testprocessbar extends AppCompatActivity {

    private String[] mItems = { "Item no.1", "Item no.2", "Item no.3",
            "Item no.4", "Item no.5", "Item no.6", "Item no.7", "Item no.8",
            "Item no.9", "Item no.10", "Item no.11", };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.processbartest);
        new MyTask(testprocessbar.this).execute((Void) null);
    }

    private class MyTask extends AsyncTask<Void, Void, Void> {

        private testprocessbar mActivity;
        private FrameLayout mFrameOverlay;
        ListView theList;
        public MyTask(testprocessbar activity) {
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
            String[] obtainedData = { "D1", "D2", "D3" };
             theList = (ListView) mActivity
                    .findViewById(R.id.second_list);
            theList.setAdapter(new ArrayAdapter<String>(mActivity,
                    android.R.layout.simple_list_item_1, obtainedData));
            theList.setVisibility(View.GONE);
            try {
                Thread.sleep(2000);
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
            theList.setVisibility(View.VISIBLE);
        }

    }
}