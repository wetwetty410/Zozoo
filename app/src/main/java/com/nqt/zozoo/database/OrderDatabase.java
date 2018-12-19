package com.nqt.zozoo.database;

import android.content.Context;

import com.nqt.zozoo.utils.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/19/2018.
 */

public class OrderDatabase extends DatabaseManager {
    public static final String TABLE_ORDER = "order_mon_an";
    public static final String TABLE_ORDER_ID = "id";
    public static final String TABLE_ORDER_MA = "ma_order";
    public static final String TABLE_ORDER_DANH_SACH = "danh_sach_order";
    public static final String TABLE_ORDER_MA_BAN = "ma_ban";
    public static final String TABLE_ORDER_THOI_GIAN = "time";

    public OrderDatabase(Context context) {
        super(context);
    }

    public List<Order> getAllOrder() {
        List<Order> orderList = new ArrayList<>();
        return orderList;
    }

}
