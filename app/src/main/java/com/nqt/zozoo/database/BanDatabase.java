package com.nqt.zozoo.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.nqt.zozoo.utils.Ban;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/3/2018.
 */

public class BanDatabase extends DatabaseManager {

    private static final String TABLE_BAN = "ban";
    private static final String TABLE_BAN_ID = "id";
    private static final String TABLE_BAN_MA_BAN = "ma_ban";
    private static final String TABLE_BAN_TEN_BAN = "ten_ban";
    private static final String TABLE_BAN_MA_TANG = "ma_tang";
    private static final String TABLE_BAN_MA_LOAI_BAN = "ma_loai_ban";
    private static final String TABLE_BAN_STATUS = "status_ban";

    public BanDatabase(Context context) {
        super(context);
    }


    public void addBan(Ban ban) {
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

    public void updateBan(Ban ban, String idBan) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_BAN_MA_BAN, ban.getMaBan());
        values.put(TABLE_BAN_TEN_BAN, ban.getTenBan());
        values.put(TABLE_BAN_MA_TANG, ban.getMaTang());
        values.put(TABLE_BAN_MA_LOAI_BAN, ban.getMaLoaiBan());
        sqLiteDatabase.update(TABLE_BAN, values, TABLE_BAN_ID + "=?", new String[]{idBan});
        closeDatabase();
    }

    public Ban getBan(int idDanhSachBan) {
        openDatabase();

        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.query(TABLE_BAN, null, TABLE_BAN_ID + "=?", new String[]{String.valueOf(idDanhSachBan)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Ban soBan = new Ban(cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getInt(5));
        closeDatabase();
        return soBan;
    }

    public List<Ban> getAllBan() {
        openDatabase();
        List<Ban> soBans = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_BAN;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Ban soBan = new Ban(cursor.getString(0),
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

    public List<Ban> getSoBan(String maTang) {
        openDatabase();
        List<Ban> soBans = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_BAN, null,
                TABLE_BAN_MA_TANG + "=?",
                new String[]{maTang},
                null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Ban soBan = new Ban(cursor.getString(0),
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
