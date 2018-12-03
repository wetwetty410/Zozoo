package com.nqt.zozoo.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nqt.zozoo.banhang.quanlyban.SoBanContent;

import java.util.ArrayList;
import java.util.List;

import static com.nqt.zozoo.banhang.quanlyban.SoBanContent.*;

/**
 * Created by USER on 12/3/2018.
 */

public class MyDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "khachsan";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_SO_BAN = "danh sach ban";
    public static final String CL_ID = "id";
    public static final String CL_NAME = "ten_ban";
    public static final String CL_NUMBER = "so_ban";

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDanhSachBan =
                String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT)", TABLE_SO_BAN, CL_ID, CL_NAME, CL_NUMBER);
        db.execSQL(createDanhSachBan);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropDanhSachBan =
                String.format("DROP TABLE IF EXISTS %s", TABLE_SO_BAN);
        db.execSQL(dropDanhSachBan);

        onCreate(db);
    }

    public void addDanhSachBan(SoBan soBan) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CL_ID, soBan.getId());
        values.put(CL_NAME, soBan.getTenBan());
        values.put(CL_NUMBER, soBan.getSoBan());

        db.insert(TABLE_SO_BAN, null, values);
        db.close();
    }

    public SoBan getDanhSachBan(int idDanhSachBan) {
        SQLiteDatabase db = this.getReadableDatabase();

        @SuppressLint("Recycle") Cursor cursor = db.query(TABLE_SO_BAN, null, CL_ID + "=?", new String[]{String.valueOf(idDanhSachBan)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        SoBan soBan = new SoBan(cursor.getString(0), cursor.getString(1), cursor.getString(2));
        return soBan;
    }

    public List<SoBan> getAllDanhSachBan() {
        List<SoBan> soBans = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_SO_BAN;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            SoBan soBan = new SoBan(cursor.getString(0), cursor.getString(1), cursor.getString(2));
            soBans.add(soBan);
            cursor.moveToFirst();
        }
        return soBans;
    }
}
