package com.nqt.zozoo.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.nqt.zozoo.utils.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/3/2018.
 */

public class TableDatabase extends DatabaseManager {

    private static final String TABLE_BAN = "ban";
    private static final String TABLE_BAN_ID = "id";
    private static final String TABLE_BAN_MA_BAN = "ma_ban";
    private static final String TABLE_BAN_TEN_BAN = "ten_ban";
    private static final String TABLE_BAN_MA_TANG = "ma_tang";
    private static final String TABLE_BAN_MA_LOAI_BAN = "ma_loai_ban";
    private static final String TABLE_BAN_STATUS = "status_ban";

    public TableDatabase(Context context) {
        super(context);
    }


    public void addBan(Table ban) {
        openDatabase();

        ContentValues values = new ContentValues();
        values.put(TABLE_BAN_MA_BAN, ban.getMaBan());
        values.put(TABLE_BAN_TEN_BAN, ban.getTenBan());
        values.put(TABLE_BAN_MA_TANG, ban.getMaTang());
        values.put(TABLE_BAN_MA_LOAI_BAN, ban.getMaLoaiBan());
        sqLiteDatabase.insert(TABLE_BAN, null, values);
        closeDatabase();
    }

    public void updateStatusBan(int status, String maBan) {
        openDatabase();

        ContentValues values = new ContentValues();
        values.put(TABLE_BAN_STATUS, status);
        sqLiteDatabase.update(TABLE_BAN, values, TABLE_BAN_MA_BAN + "=?",
                new String[]{String.valueOf(maBan)});
        closeDatabase();
    }

    public void updateBan(Table ban, String idBan) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_BAN_MA_BAN, ban.getMaBan());
        values.put(TABLE_BAN_TEN_BAN, ban.getTenBan());
        values.put(TABLE_BAN_MA_TANG, ban.getMaTang());
        values.put(TABLE_BAN_MA_LOAI_BAN, ban.getMaLoaiBan());
        sqLiteDatabase.update(TABLE_BAN, values, TABLE_BAN_ID + "=?", new String[]{idBan});
        closeDatabase();
    }

    public Table getBan(String maBan) {
        openDatabase();

        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.query(TABLE_BAN, null, TABLE_BAN_MA_BAN + "=?", new String[]{String.valueOf(maBan)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Table soBan = new Table(cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getInt(5));
        closeDatabase();
        return soBan;
    }

    public List<Table> getAllBan() {
        openDatabase();
        List<Table> soBans = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_BAN;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Table soBan = new Table(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5));
            soBans.add(soBan);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return soBans;
    }

    public List<Table> getSoBan(String maTang) {
        openDatabase();
        List<Table> soBans = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_BAN, null,
                TABLE_BAN_MA_TANG + "=?",
                new String[]{maTang},
                null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Table soBan = new Table(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5));
            soBans.add(soBan);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return soBans;
    }

}
