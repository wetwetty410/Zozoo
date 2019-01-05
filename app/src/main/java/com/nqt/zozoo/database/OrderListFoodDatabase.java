package com.nqt.zozoo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.nqt.zozoo.utils.OrderListFood;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/19/2018.
 */

public class OrderListFoodDatabase extends DatabaseManager {
    private static final String TABLE_ORDER = "orders_list";
    private static final String TABLE_ORDER_ID = "id";
    private static final String TABLE_ORDER_MA = "ma_order";
    private static final String TABLE_ORDER_MA_MON_AN = "ma_mon_an";
    private static final String TABLE_ORDER_TEN_MON_AN = "ten_mon_an";
    private static final String TABLE_ORDER_GIA_TIEN = "gia_tien";
    private static final String TABLE_ORDER_SO_LUONG = "so_luong";

    public OrderListFoodDatabase(Context context) {
        super(context);
    }

    public void addOrderList(OrderListFood orderDanhSachMon) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_ORDER_MA, orderDanhSachMon.getMaOrder());
        values.put(TABLE_ORDER_MA_MON_AN, orderDanhSachMon.getMaMonAn());
        values.put(TABLE_ORDER_TEN_MON_AN, orderDanhSachMon.getTenMonAn());
        values.put(TABLE_ORDER_GIA_TIEN, orderDanhSachMon.getGiaTien());
        values.put(TABLE_ORDER_SO_LUONG, orderDanhSachMon.getSoLuong());
        sqLiteDatabase.insert(TABLE_ORDER, null, values);
        closeDatabase();
    }

    public List<OrderListFood> getAllOrderList() {
        openDatabase();
        List<OrderListFood> orderListDanhSachMon = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_ORDER;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            OrderListFood orderDanhSachMon = new OrderListFood(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5)
            );
            orderListDanhSachMon.add(orderDanhSachMon);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return orderListDanhSachMon;
    }

    public OrderListFood getOrderList(int idOrderList) {
        openDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_ORDER, null,
                TABLE_ORDER_ID + "=?",
                new String[]{String.valueOf(idOrderList)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        OrderListFood orderDanhSachMon = new OrderListFood(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getInt(5)
        );
        cursor.close();
        closeDatabase();
        return orderDanhSachMon;
    }

    public List<OrderListFood> getOrderListWithCodeOrder(String maOrder) {
        openDatabase();
        List<OrderListFood> orderDanhSachMons = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_ORDER, null,
                TABLE_ORDER_MA + "=?",
                new String[]{String.valueOf(maOrder)}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            OrderListFood orderDanhSachMon = new OrderListFood(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5)
            );
            orderDanhSachMons.add(orderDanhSachMon);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return orderDanhSachMons;
    }


    public void updateOrderList(OrderListFood orderDanhSachMon, String maOrderList, String maOrder) {
        openDatabase();

        ContentValues values = new ContentValues();
        values.put(TABLE_ORDER_MA, orderDanhSachMon.getMaOrder());
        values.put(TABLE_ORDER_TEN_MON_AN, orderDanhSachMon.getTenMonAn());
        values.put(TABLE_ORDER_MA_MON_AN, orderDanhSachMon.getMaMonAn());
        values.put(TABLE_ORDER_GIA_TIEN, orderDanhSachMon.getGiaTien());
        values.put(TABLE_ORDER_SO_LUONG, orderDanhSachMon.getSoLuong());
        int index = sqLiteDatabase.update(TABLE_ORDER, values, TABLE_ORDER_MA_MON_AN + "=? AND " + TABLE_ORDER_MA + "=?",
                new String[]{maOrderList, maOrder});
        if (index == 0) {
            sqLiteDatabase.insert(TABLE_ORDER, null, values);
        }
        closeDatabase();
    }

    public void deleteOrderList(String maOrder) {
        openDatabase();
        sqLiteDatabase.delete(TABLE_ORDER, TABLE_ORDER_MA + "=?",
                new String[]{maOrder});
        closeDatabase();
    }

    public void deleteOrderListWithMaMonAn(String maOrder, String maMonAn) {
        openDatabase();
        sqLiteDatabase.delete(TABLE_ORDER, TABLE_ORDER_MA_MON_AN + "=? AND " + TABLE_ORDER_MA + "=?",
                new String[]{maMonAn, maOrder});
        closeDatabase();
    }
}
