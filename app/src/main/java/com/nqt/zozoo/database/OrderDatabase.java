package com.nqt.zozoo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

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

    public void addOrer(Order order) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_ORDER_MA, order.getMaOrder());
        values.put(TABLE_ORDER_DANH_SACH, order.getDanhSachOrder());
        values.put(TABLE_ORDER_MA_BAN, order.getMaBan());
        values.put(TABLE_ORDER_THOI_GIAN, order.getTime());
        sqLiteDatabase.insert(TABLE_ORDER, null, values);

        closeDatabase();
    }

    private List<Order> getAllOrder() {
        openDatabase();
        List<Order> orderList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_ORDER;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Order order = new Order(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
            orderList.add(order);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return orderList;
    }

    private Order getOrder(int idOrder) {
        openDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_ORDER, null,
                TABLE_ORDER_ID + "=?",
                new String[]{String.valueOf(idOrder)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Order order = new Order(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4)
        );
        cursor.close();
        closeDatabase();
        return order;
    }

    public void updateOrder(Order order, int idOrder) {
        openDatabase();

        ContentValues values = new ContentValues();
        values.put(TABLE_ORDER_MA, order.getMaOrder());
        values.put(TABLE_ORDER_DANH_SACH, order.getDanhSachOrder());
        values.put(TABLE_ORDER_MA_BAN, order.getMaBan());
        values.put(TABLE_ORDER_THOI_GIAN, order.getTime());
        sqLiteDatabase.update(TABLE_ORDER, values, TABLE_ORDER_ID + "=?",
                new String[]{String.valueOf(idOrder)});
        closeDatabase();
    }

    public void deleteOrder(String maBan) {
        openDatabase();
        sqLiteDatabase.delete(TABLE_ORDER, TABLE_ORDER_MA_BAN + "=?",
                new String[]{maBan});
        closeDatabase();
    }
}
