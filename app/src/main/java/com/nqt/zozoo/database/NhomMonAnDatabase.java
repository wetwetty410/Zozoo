package com.nqt.zozoo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nqt.zozoo.utils.NhomMonAn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/12/2018.
 */

public class NhomMonAnDatabase extends DatabaseManager {
    private static final String NHOM_MON_AN = "nhom_mon_an";
    private static final String NHOM_MON_AN_ID = "id";
    private static final String NHOM_MON_AN_MA = "ma_nhom";
    private static final String NHOM_MON_AN_TEN = "ten_nhom";

    public NhomMonAnDatabase(Context context) {
        super(context);
    }

    public NhomMonAn getNhomMonAn(int idNhomMonAn) {
        openDatabase();
        Cursor cursor = sqLiteDatabase.query(NHOM_MON_AN, null, NHOM_MON_AN_ID + "=?", new String[]{String.valueOf(idNhomMonAn)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        NhomMonAn nhomMonAn = new NhomMonAn(cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2));
        cursor.close();
        closeDatabase();
        return nhomMonAn;
    }

    public void deleteNhomMonAn(String maNhom) {
        openDatabase();
        sqLiteDatabase.delete(NHOM_MON_AN, NHOM_MON_AN_MA + "+?",
                new String[]{maNhom});
        closeDatabase();
    }

    public void addNhomMonAn(NhomMonAn nhomMonAn) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put(NHOM_MON_AN_MA, nhomMonAn.getMaNhonMonAn());
        values.put(NHOM_MON_AN_TEN, nhomMonAn.getTenNhonMonAn());

        sqLiteDatabase.insert(NHOM_MON_AN, null, values);
        closeDatabase();
    }

    public List<NhomMonAn> getAllNhomMonAn() {
        openDatabase();
        List<NhomMonAn> nhomMonAnList = new ArrayList<>();
        String query = "SELECT * FROM " + NHOM_MON_AN;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            NhomMonAn nhomMonAn = new NhomMonAn(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2));
            nhomMonAnList.add(nhomMonAn);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return nhomMonAnList;
    }
}
