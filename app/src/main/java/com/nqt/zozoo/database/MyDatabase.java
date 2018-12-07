package com.nqt.zozoo.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.nqt.zozoo.banhang.quanlyban.SoBanContent.SoBan;

/**
 * Created by USER on 12/3/2018.
 */

public class MyDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "nhahang";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_SO_BAN = "danh_sach_ban";
    public static final String CL_ID = "id";
    public static final String CL_NAME = "ten_ban";
    public static final String CL_NUMBER = "so_ban";
    private final String databasePath;
    private SQLiteDatabase sqLiteDatabase;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        databasePath = context.getFilesDir().getPath() + File.separator + DATABASE_NAME;
        copyDatabaseFromAssets(context);
    }

    private void openDatabase() {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            //  sqLiteDatabase=SQLiteDatabase.openDatabase(databasePath)
        }
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

    private void copyDatabaseFromAssets(Context context) {
        File file = new File(databasePath);
        if (file.exists()) {
            return;
        }

        try {
            //get inputStream
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(DATABASE_NAME, AssetManager.ACCESS_STREAMING);

            //create outputStream
            FileOutputStream outputStream = new FileOutputStream(file);

            byte buff[] = new byte[1024];
            int length;
            while ((length = inputStream.read(buff)) != -1) {
                outputStream.write(buff, 0, length);
            }

            inputStream.close();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addDanhSachBan(SoBan soBan) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //   values.put(CL_ID, soBan.getId());
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
            cursor.moveToNext();
        }
       cursor.close();
        return soBans;
    }

}
