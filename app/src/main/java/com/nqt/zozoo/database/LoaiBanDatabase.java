package com.nqt.zozoo.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.nqt.zozoo.utils.LoaiBan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/3/2018.
 */

public class LoaiBanDatabase extends DatabaseManager {

    private static final String LOAI_BAN = "loai_ban";
    private static final String LOAI_BAN_ID = "id";
    private static final String LOAI_BAN_MA = "ma_loai_ban";
    private static final String LOAI_BAN_TEN = "ten_loai_ban";

    public LoaiBanDatabase(Context context) {
        super(context);
    }


    public void addLoaiBan(LoaiBan loaiBan) {
        openDatabase();

        ContentValues values = new ContentValues();
        values.put(LOAI_BAN_MA, loaiBan.getMaLoaiBan());
        values.put(LOAI_BAN_TEN, loaiBan.getTenLoaiBan());

        sqLiteDatabase.insert(LOAI_BAN, null, values);
        closeDatabase();
    }

    public LoaiBan getLoaiBan(int idLoaiBan) {
        openDatabase();

        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.query(LOAI_BAN, null, LOAI_BAN_ID + "=?", new String[]{String.valueOf(idLoaiBan)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        LoaiBan loaiBan = new LoaiBan(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2));
        closeDatabase();
        return loaiBan;
    }

    public List<LoaiBan> getAllBan() {
        openDatabase();
        List<LoaiBan> soBans = new ArrayList<>();
        String query = "SELECT * FROM " + LOAI_BAN;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            LoaiBan loaiBan = new LoaiBan(cursor.getString(0),
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
