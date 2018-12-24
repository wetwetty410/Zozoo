package com.nqt.zozoo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.nqt.zozoo.utils.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDatabase extends DatabaseManager {
    private static final String TABLE_ORDER = "order";
    private static final String TABLE_ORDER_ID = "id";
    private static final String TABLE_ORDER_MA = "ma_order";
    private static final String TABLE_ORDER_MA_BAN = "ma_ban";
    private static final String TABLE_ORDER_NGUOI_ORDER = "nguoi_order";
    private static final String TABLE_ORDER_THOI_GIAN = "time_create";

    public OrderDatabase(Context context) {
        super(context);
    }

    public void addOrer(Order order) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_ORDER_MA, order.getMaOrder());
        values.put(TABLE_ORDER_NGUOI_ORDER, order.getNguoiOrder());
        values.put(TABLE_ORDER_MA_BAN, order.getMaBan());
        values.put(TABLE_ORDER_THOI_GIAN, order.getTimeCreate());
        sqLiteDatabase.insert(TABLE_ORDER, null, values);

        closeDatabase();
    }

    public List<Order> getAllOrder() {
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
                    cursor.getString(4),
                    cursor.getString(5));
            orderList.add(order);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return orderList;
    }

    public Order getOrder(String maBan) {
        openDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_ORDER, null,
                TABLE_ORDER_MA_BAN + "=?",
                new String[]{String.valueOf(maBan)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Order order = new Order(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5)
        );
        cursor.close();
        closeDatabase();
        return order;
    }

    public void updateOrder(Order order, int idOrder) {
        openDatabase();

        ContentValues values = new ContentValues();
        values.put(TABLE_ORDER_MA, order.getMaOrder());
        values.put(TABLE_ORDER_NGUOI_ORDER, order.getNguoiOrder());
        values.put(TABLE_ORDER_MA_BAN, order.getMaBan());
        values.put(TABLE_ORDER_THOI_GIAN, order.getTimeCreate());
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
