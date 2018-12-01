package com.nqt.zozoo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by USER on 11/30/2018.
 */

public class BanHangPagerAdapter extends FragmentPagerAdapter {
    public static final int BAN_HANG_NUM_FRAGMENT = 4;

    public BanHangPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return BAN_HANG_NUM_FRAGMENT;
    }
}
