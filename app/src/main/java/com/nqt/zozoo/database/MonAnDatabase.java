package com.nqt.zozoo.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.nqt.zozoo.utils.MonAn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/3/2018.
 */

public class MonAnDatabase extends DatabaseManager {

    private static final String MON_AN = "mon_an";
    private static final String MON_AN_ID = "id";
    private static final String MON_AN_MA = "ma_mon_an";
    private static final String MON_AN_TEN = "ten_mon_an";
    private static final String MON_AN_NHOM = "nhom_mon_an";
    private static final String MON_AN_DON_GIA = "don_gia";
    private static final String MON_AN_DON_VI_TINH = "don_vi_tinh";

    public MonAnDatabase(Context context) {
        super(context);
    }


    public void addMonAn(MonAn monAn) {
        openDatabase();

        ContentValues values = new ContentValues();
        values.put(MON_AN_MA, monAn.getMaMonAn());
        values.put(MON_AN_TEN, monAn.getTenMonAn());
        values.put(MON_AN_NHOM, monAn.getNhomMonAn());
        values.put(MON_AN_DON_GIA, monAn.getDonGia());
        values.put(MON_AN_DON_VI_TINH, monAn.getDonViTinh());

        sqLiteDatabase.insert(MON_AN, null, values);
        closeDatabase();
    }

    public MonAn getMonAn(int idMonAn) {
        openDatabase();

        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.query(MON_AN, null, MON_AN_ID + "=?", new String[]{String.valueOf(idMonAn)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        MonAn monAn = new MonAn(cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                cursor.getString(5));
        cursor.close();
        closeDatabase();
        return monAn;
    }

    public List<MonAn> getAllBan() {
        openDatabase();
        List<MonAn> monAnList = new ArrayList<>();
        String query = "SELECT * FROM " + MON_AN;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            MonAn monAn = new MonAn(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5));
            monAnList.add(monAn);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return monAnList;
    }

}
