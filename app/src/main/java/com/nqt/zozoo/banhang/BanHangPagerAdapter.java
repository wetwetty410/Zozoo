package com.nqt.zozoo.banhang;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nqt.zozoo.database.BanDatabase;

/**
 * Created by USER on 11/30/2018.
 */

public class BanHangPagerAdapter extends FragmentPagerAdapter {
    public static final int BAN_HANG_NUM_FRAGMENT = 3;
    private Context context;
    private int numItemSoBan;
    ;

    public BanHangPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        numItemSoBan = new BanDatabase(context).getAllBan().size();
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = BanHangSoBanFragment.newInstance(numItemSoBan, 0, "SO_BAN");
        switch (position) {
            case 0:
                fragment = BanHangSoBanFragment.newInstance(numItemSoBan, 0, "SO_BAN");
                return fragment;
            case 1:
                break;
            default:
                fragment = BanHangSoBanFragment.newInstance(numItemSoBan, 0, "SO_BAN");
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Bàn";
            default:
                return "Bàn";
        }
    }

    @Override
    public int getCount() {
        return BAN_HANG_NUM_FRAGMENT;
    }
}
