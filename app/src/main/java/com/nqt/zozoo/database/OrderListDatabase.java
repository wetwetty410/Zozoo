package com.nqt.zozoo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.nqt.zozoo.utils.OrderList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/19/2018.
 */

public class OrderListDatabase extends DatabaseManager {
    private static final String TABLE_ORDER = "order_list";
    private static final String TABLE_ORDER_ID = "id";
    private static final String TABLE_ORDER_MA = "ma_order";
    private static final String TABLE_ORDER_MA_MON_AN = "ma_mon_an";
    private static final String TABLE_ORDER_TEN_MON_AN = "ten_mon_an";
    private static final String TABLE_ORDER_GIA_TIEN = "gia_tien";
    private static final String TABLE_ORDER_SO_LUONG = "so_luong";

    public OrderListDatabase(Context context) {
        super(context);
    }

    public void addOrerList(OrderList orderList) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_ORDER_MA, orderList.getMaOrder());
        values.put(TABLE_ORDER_MA_MON_AN, orderList.getMaMonAn());
        values.put(TABLE_ORDER_TEN_MON_AN, orderList.getTenMonAn());
        values.put(TABLE_ORDER_GIA_TIEN, orderList.getGiaTien());
        values.put(TABLE_ORDER_SO_LUONG, orderList.getSoLuong());
        sqLiteDatabase.insert(TABLE_ORDER, null, values);

        closeDatabase();
    }

    public List<OrderList> getAllOrderList() {
        openDatabase();
        List<OrderList> orderListList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_ORDER;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            OrderList orderList = new OrderList(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5)
            );
            orderListList.add(orderList);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return orderListList;
    }

    public OrderList getOrderList(int idOrderList) {
        openDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_ORDER, null,
                TABLE_ORDER_ID + "=?",
                new String[]{String.valueOf(idOrderList)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        OrderList orderList = new OrderList(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getInt(5)
        );
        cursor.close();
        closeDatabase();
        return orderList;
    }

    public List<OrderList> getOrderListWithCodeOrder(String maOrder) {
        openDatabase();
        List<OrderList> orderLists = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_ORDER, null,
                TABLE_ORDER_MA + "=?",
                new String[]{String.valueOf(maOrder)}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            OrderList orderList = new OrderList(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5)
            );
            orderLists.add(orderList);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return orderLists;
    }


    public void updateOrderList(OrderList orderList, int idOrderList) {
        openDatabase();

        ContentValues values = new ContentValues();
        values.put(TABLE_ORDER_MA, orderList.getMaOrder());
        values.put(TABLE_ORDER_MA_MON_AN, orderList.getMaMonAn());
        values.put(TABLE_ORDER_TEN_MON_AN, orderList.getTenMonAn());
        values.put(TABLE_ORDER_GIA_TIEN, orderList.getGiaTien());
        values.put(TABLE_ORDER_SO_LUONG, orderList.getSoLuong());
        sqLiteDatabase.update(TABLE_ORDER, values, TABLE_ORDER_ID + "=?",
                new String[]{String.valueOf(idOrderList)});
        closeDatabase();
    }

    public void deleteOrderList(String maOrder) {
        openDatabase();
        sqLiteDatabase.delete(TABLE_ORDER, TABLE_ORDER_MA + "=?",
                new String[]{maOrder});
        closeDatabase();
    }
}
