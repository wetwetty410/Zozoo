package com.nqt.zozoo.nhabep;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class NhaBepPagerAdapter extends FragmentPagerAdapter {
    private static final int NHA_BEP_NUM_FRAGMENT = 2;

    public NhaBepPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
             //   fragment ;
             //   return fragment;
            case 1:
             //   fragment ;
           //     return fragment;
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return NHA_BEP_NUM_FRAGMENT;
    }
}
