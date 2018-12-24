package com.nqt.zozoo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.nqt.zozoo.utils.Tang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/10/2018.
 */

public class TangDatabase extends DatabaseManager {
    private static final String TANG = "tang";
    private static final String TANG_ID = "id";
    private static final String TANG_MA = "ma_tang";
    private static final String TANG_TEN = "ten_tang";

    public TangDatabase(Context context) {
        super(context);
    }

    public void addTang(Tang tang) {
        openDatabase();

        ContentValues values = new ContentValues();
        values.put(TANG_MA, tang.getMaTang());
        values.put(TANG_TEN, tang.getTenTang());


        sqLiteDatabase.insert(TANG, null, values);
        closeDatabase();
    }

    public Tang getTang(int idDanhSachBan) {
        openDatabase();

        Cursor cursor = sqLiteDatabase.query(TANG, null, TANG_ID + "=?", new String[]{String.valueOf(idDanhSachBan)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Tang tang = new Tang(cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2));
        closeDatabase();
        return tang;
    }

    public List<Tang> getAllTang() {
        openDatabase();
        List<Tang> tangList = new ArrayList<>();
        String query = "SELECT * FROM " + TANG;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Tang tang = new Tang(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2));
            tangList.add(tang);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return tangList;
    }

}
