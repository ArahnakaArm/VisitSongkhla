package com.visitsongkhla.deimos.visitsongkhla;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapterHotel extends FragmentStatePagerAdapter {
    int mNoOfTabs;

    public  PagerAdapterHotel (FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.mNoOfTabs=NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                HatyaiHotelTab tab1 = new HatyaiHotelTab();
                return tab1;
            case 1:
                SongkhlaHotelTab tab2 = new SongkhlaHotelTab();
                return tab2;
            case 2:
                EtcHotelTab tab3 = new EtcHotelTab();
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
