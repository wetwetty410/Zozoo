package com.nqt.zozoo.banhang;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nqt.zozoo.database.MyDatabase;

/**
 * Created by USER on 11/30/2018.
 */

public class BanHangPagerAdapter extends FragmentPagerAdapter {
    public static final int BAN_HANG_NUM_FRAGMENT = 4;
    private Context context;

    public BanHangPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = BanHangSoBanFragment.newInstance(10, 0, "SO_BAN");
        switch (position) {
            case 0:
//                MyDatabase myDatabase = new MyDatabase(context);
//                int num = myDatabase.getAllDanhSachBan().size();
                fragment = BanHangSoBanFragment.newInstance(10, 0, "SO_BAN");
                return fragment;
            case 1:
                break;
            default:
                fragment = BanHangSoBanFragment.newInstance(10, 0, "SO_BAN");
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return BAN_HANG_NUM_FRAGMENT;
    }
}
