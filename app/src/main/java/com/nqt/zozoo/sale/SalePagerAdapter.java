package com.nqt.zozoo.sale;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nqt.zozoo.sale.LocationTableFragment;
import com.nqt.zozoo.sale.ViewOrderFoodFragment;
import com.nqt.zozoo.database.TableDatabase;

/**
 * Created by USER on 11/30/2018.
 */

public class SalePagerAdapter extends FragmentPagerAdapter {
    public static final int NUMBER_TABLE_FRAGMENT = 2;
    private Context context;
    private int numItemTable;

    public SalePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        numItemTable = new TableDatabase(context).getAllBan().size();
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:

                fragment = LocationTableFragment.newInstance(numItemTable, 0, "SO_BAN");
                return fragment;
            case 1:
                fragment = ViewOrderFoodFragment.newInstance(1, "MON_AN");
                return fragment;
            default:
                fragment = LocationTableFragment.newInstance(numItemTable, 0, "SO_BAN");
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
                return "Danh Sách Bàn";
        }
    }

    @Override
    public int getCount() {
        return NUMBER_TABLE_FRAGMENT;
    }
}
