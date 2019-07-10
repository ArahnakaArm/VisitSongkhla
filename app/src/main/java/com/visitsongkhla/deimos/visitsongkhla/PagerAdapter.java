package com.visitsongkhla.deimos.visitsongkhla;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNoOfTabs;

    public  PagerAdapter (FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.mNoOfTabs=NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                CommonTab tab1 = new CommonTab();
                return tab1;
            case 1:
                HaralTab tab2 = new HaralTab();
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
