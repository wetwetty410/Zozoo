package com.nqt.zozoo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.support.annotation.Nullable;

import com.nqt.zozoo.utils.Order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDatabase extends DatabaseManager {
    private static final String TABLE_ORDER = "orders";
    private static final String TABLE_ORDER_ID = "id";
    private static final String TABLE_ORDER_MA = "ma_order";
    private static final String TABLE_ORDER_MA_BAN = "ma_ban";
    private static final String TABLE_ORDER_NGUOI_ORDER = "nguoi_order";
    private static final String TABLE_ORDER_THOI_GIAN_CREATE = "time_create";
    private static final String TABLE_ORDER_THOI_GIAN_UPDATE = "time_update";

    public OrderDatabase(Context context) {
        super(context);
    }

    public void addOrder(Order order) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_ORDER_ID, order.getId());
        values.put(TABLE_ORDER_MA, order.getMaOrder());
        values.put(TABLE_ORDER_MA_BAN, order.getMaBan());
        values.put(TABLE_ORDER_NGUOI_ORDER, order.getNguoiOrder());
        values.put(TABLE_ORDER_THOI_GIAN_CREATE, order.getTimeCreate());
        values.put(TABLE_ORDER_THOI_GIAN_UPDATE, order.getTimeUpdate());
        sqLiteDatabase.insert(TABLE_ORDER, null, values);
        closeDatabase();
    }

    public List<Order> getAllOrder() {
        openDatabase();
        List<Order> orderList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_ORDER;
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery(query, null);

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
        Cursor cursor = sqLiteDatabase.query(TABLE_ORDER,
                null,
                TABLE_ORDER_MA_BAN + "=?",
                new String[]{String.valueOf(maBan)}, null, null, TABLE_ORDER_ID + " DESC");
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Order orders = new Order(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5)
        );
        cursor.close();
        closeDatabase();
        return orders;
    }

    public void updateOrder(Order order, int idOrder) {
        openDatabase();

        ContentValues values = new ContentValues();
        values.put(TABLE_ORDER_MA, order.getMaOrder());
        values.put(TABLE_ORDER_NGUOI_ORDER, order.getNguoiOrder());
        values.put(TABLE_ORDER_MA_BAN, order.getMaBan());
        values.put(TABLE_ORDER_THOI_GIAN_CREATE, order.getTimeCreate());
        values.put(TABLE_ORDER_THOI_GIAN_UPDATE, order.getTimeUpdate());
        sqLiteDatabase.update(TABLE_ORDER, values, TABLE_ORDER_ID + "=?",
                new String[]{String.valueOf(idOrder)});
        closeDatabase();
    }

    public void deleteOrder(String id) {
        openDatabase();
        sqLiteDatabase.delete(TABLE_ORDER, TABLE_ORDER_ID + "=?",
                new String[]{id
        });
        closeDatabase();
    }

    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }
}
