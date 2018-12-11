package com.nqt.zozoo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nqt.zozoo.banhang.BanHangSoBanFragment;
import com.nqt.zozoo.banhang.MonAnFragment;
import com.nqt.zozoo.database.BanDatabase;
import com.nqt.zozoo.utils.MonAn;

/**
 * Created by USER on 11/30/2018.
 */

public class BanHangPagerAdapter extends FragmentPagerAdapter {
    public static final int BAN_HANG_NUM_FRAGMENT = 3;
    private Context context;
    private int numItemSoBan;

    public BanHangPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        numItemSoBan = new BanDatabase(context).getAllBan().size();
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = BanHangSoBanFragment.newInstance(numItemSoBan, 0, "SO_BAN");
                return fragment;
            case 1:
                fragment = MonAnFragment.newInstance(1, "MON_AN");
                return fragment;
            default:
                fragment = BanHangSoBanFragment.newInstance(numItemSoBan, 0, "SO_BAN");
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Danh Sách Bàn";
            case 1:
                return "Món Ăn";
            default:
                return "Bàn";
        }
    }

    @Override
    public int getCount() {
        return BAN_HANG_NUM_FRAGMENT;
    }
}
