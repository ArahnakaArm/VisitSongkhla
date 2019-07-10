package com.visitsongkhla.deimos.visitsongkhla;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapterHighlights extends FragmentStatePagerAdapter {
    int mNoOfTabs;

    public  PagerAdapterHighlights (FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.mNoOfTabs=NumberOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                HighlightTab tab1 = new HighlightTab();
                return tab1;
            case 1:
                HighlightGTab tab2 = new HighlightGTab();
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
