package com.example.deimos.visitsongkhla;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapterDiary extends FragmentStatePagerAdapter {
    int mNoOfTabs;

    public  PagerAdapterDiary (FragmentManager fm,int NumberOfTabs){
        super(fm);
        this.mNoOfTabs=NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FormDiaryTab tab1 = new FormDiaryTab();
                return tab1;
            case 1:
                HistoryDiaryTab tab2 = new HistoryDiaryTab();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
