package com.visitsongkhla.deimos.visitsongkhla;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class PagerAdapterDiary extends FragmentStatePagerAdapter {
    int mNoOfTabs;

    public  PagerAdapterDiary (FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.mNoOfTabs=NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                HistoryDiaryTab tab1 = new HistoryDiaryTab();
                return tab1;
            case 1:
                FormDiaryTab tab2 = new FormDiaryTab();
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
