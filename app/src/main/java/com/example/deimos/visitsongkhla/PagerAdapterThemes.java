package com.example.deimos.visitsongkhla;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapterThemes extends FragmentStatePagerAdapter {
    int mNoOfTabs;

    public  PagerAdapterThemes (FragmentManager fm,int NumberOfTabs){
        super(fm);
        this.mNoOfTabs=NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                NeturalTab tab1 = new NeturalTab();
                return tab1;
            case 1:
                HistiricalTab tab2 = new HistiricalTab();
                return tab2;
            case 2:
                ArticalTab tab3 = new ArticalTab();
                return tab3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
