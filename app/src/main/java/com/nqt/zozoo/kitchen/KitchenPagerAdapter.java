package com.nqt.zozoo.kitchen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class KitchenPagerAdapter extends FragmentPagerAdapter {
    private static final int NHA_BEP_NUM_FRAGMENT = 2;

    public KitchenPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = KitchenFragment.newInstance();
                //   return fragment;
            case 1:
                //   fragment ;
                //     return fragment;
            default:
                fragment = KitchenFragment.newInstance();
                break;
        }
        return fragment;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Bàn Order";

            case 1:
                return "Order Nhanh";
            default:
                return "Bàn Order";
        }
    }

    @Override
    public int getCount() {
        return NHA_BEP_NUM_FRAGMENT;
    }
}
