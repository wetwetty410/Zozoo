package com.nqt.zozoo.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.nqt.zozoo.utils.TableType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/3/2018.
 */

public class TableTypeDatabase extends DatabaseManager {

    private static final String TABLE_TYPE = "loai_ban";
    private static final String TABLE_TYPE_ID = "id";
    private static final String TABLE_TYPE_MA = "ma_loai_ban";
    private static final String TABLE_TYPE_TEN = "ten_loai_ban";

    public TableTypeDatabase(Context context) {
        super(context);
    }


    public void addLoaiBan(TableType loaiBan) {
        openDatabase();

        ContentValues values = new ContentValues();
        values.put(TABLE_TYPE_MA, loaiBan.getMaLoaiBan());
        values.put(TABLE_TYPE_TEN, loaiBan.getTenLoaiBan());

        sqLiteDatabase.insert(TABLE_TYPE, null, values);
        closeDatabase();
    }

    public TableType getLoaiBan(int idLoaiBan) {
        openDatabase();

        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.query(TABLE_TYPE, null, TABLE_TYPE_ID + "=?", new String[]{String.valueOf(idLoaiBan)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        TableType loaiBan = new TableType(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2));
        closeDatabase();
        return loaiBan;
    }

    public List<TableType> getAllBan() {
        openDatabase();
        List<TableType> soBans = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_TYPE;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            TableType loaiBan = new TableType(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2));
            soBans.add(loaiBan);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return soBans;
    }
}
