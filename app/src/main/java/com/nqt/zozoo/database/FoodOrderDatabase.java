package com.nqt.zozoo.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.nqt.zozoo.utils.FoodOrder;

import java.util.ArrayList;
import java.util.List;

public class FoodOrderDatabase extends DatabaseManager {
    private static final String MON_ORDER = "mon_order";
    private static final String MON_ORDER_ID = "id";
    private static final String MON_ORDER_TEN_BAN = "ten_ban_order";
    private static final String MON_ORDER_MA_ORDER = "ma_order";
    private static final String MON_ORDER_TEN_MON = "ten_mon_order";
    private static final String MON_ORDER_SO_LUONG = "so_luong_order";
    private static final String MON_ORDER_TRANG_THAI = "trang_thai";
    private static final String MON_ORDER_MA_BAN = "ma_ban";
    private static final String MON_ORDER_MA_MON = "ma_mon_order";

    public FoodOrderDatabase(Context context) {
        super(context);
    }

    public List<FoodOrder> getMonOrderWithMaBan(String maBan) {
        openDatabase();
        List<FoodOrder> monOrders = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(MON_ORDER, null,
                MON_ORDER_MA_BAN + "=?", new String[]{maBan}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            FoodOrder monOrder = new FoodOrder(
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_ID)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_MA_ORDER)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_TEN_BAN)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_TEN_MON)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_SO_LUONG)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_TRANG_THAI)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_MA_BAN)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_MA_MON))
            );
            monOrders.add(monOrder);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return monOrders;
    }

    public void updateStatus(String status, String maMon, String tenTang) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put(MON_ORDER_TRANG_THAI, status);
        sqLiteDatabase.update(MON_ORDER, values, MON_ORDER_MA_MON + "=? AND " + MON_ORDER_TEN_BAN + "=? ",
                new String[]{maMon, tenTang});
        closeDatabase();
    }

    public List<FoodOrder> getAllMonOrder() {
        openDatabase();
        List<FoodOrder> monOrders = new ArrayList<>();
        String sql = "SELECT * FROM " + MON_ORDER;
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            FoodOrder monOrder = new FoodOrder(
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_ID)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_MA_ORDER)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_TEN_BAN)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_TEN_MON)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_SO_LUONG)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_TRANG_THAI)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_MA_BAN)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_MA_MON))
            );
            monOrders.add(monOrder);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return monOrders;
    }

    public List<String> getBanOrder() {
        openDatabase();
        List<String> monOrders = new ArrayList<>();
        String sql = "SELECT DISTINCT(" + MON_ORDER_TEN_BAN + ") FROM " + MON_ORDER;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String monOrder = cursor.getString(0);
            monOrders.add(monOrder);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return monOrders;
    }

    public List<FoodOrder> getMonOrderWithBan(String tenBan) {
        openDatabase();
        List<FoodOrder> monOrders = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(MON_ORDER, null, MON_ORDER_TEN_BAN + "=?",
                new String[]{tenBan}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            FoodOrder monOrder = new FoodOrder(
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_ID)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_MA_ORDER)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_TEN_BAN)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_TEN_MON)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_SO_LUONG)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_TRANG_THAI)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_MA_BAN)),
                    cursor.getString(cursor.getColumnIndex(MON_ORDER_MA_MON))
            );
            monOrders.add(monOrder);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return monOrders;
    }

    public void addMonOrder(FoodOrder monOrder) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put(MON_ORDER_MA_ORDER, monOrder.getMaOrder());
        values.put(MON_ORDER_TEN_BAN, monOrder.getTenBanOrder());
        values.put(MON_ORDER_TEN_MON, monOrder.getTenMonOrder());
        values.put(MON_ORDER_SO_LUONG, monOrder.getSoLuongOrder());
        values.put(MON_ORDER_TRANG_THAI, monOrder.getTrangThaiOrder());
        values.put(MON_ORDER_MA_BAN, monOrder.getMaBan());
        values.put(MON_ORDER_MA_MON, monOrder.getMaMon());
        sqLiteDatabase.insert(MON_ORDER, null, values);
        closeDatabase();
    }

    public void deleteMonOrder(String maBan, String maOrder, String maMon) {
        openDatabase();
        sqLiteDatabase.delete(MON_ORDER,
                MON_ORDER_MA_BAN + "=? AND " + MON_ORDER_MA_ORDER + "=? AND " + MON_ORDER_MA_MON + "=?",
                new String[]{maBan, maOrder, maMon});
        closeDatabase();
    }
}
