package com.visitsongkhla.deimos.visitsongkhla;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapterHelps extends FragmentStatePagerAdapter {
    int mNoOfTabs;

    public  PagerAdapterHelps (FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.mNoOfTabs=NumberOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                HospitalTab tab1 = new HospitalTab();
                return tab1;
            case 1:
                PoliceTab tab2 = new PoliceTab();
                return tab2;
            case 2:
                HelpCenterTab tab3 = new HelpCenterTab();
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
