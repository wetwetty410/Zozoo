package com.nqt.zozoo.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

public class MyDatabase {
    private static final String DATABASE_NAME = "nhahang";
    private static final String TABLE_SO_BAN = "danh_sach_ban";
    private static final String CL_ID = "id";
    private static final String CL_NAME = "ten_ban";
    private static final String CL_NUMBER = "so_ban";
    private final String databasePath;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public MyDatabase(Context context) {
        databasePath = context.getFilesDir().getPath() + File.separator + DATABASE_NAME;
        this.context = context;
        copyDatabaseFromAssets(context);
    }

    private void openDatabase() {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            sqLiteDatabase = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    private void closeDatabase() {
        if (sqLiteDatabase == null || sqLiteDatabase.isOpen()) {
            if (sqLiteDatabase != null) {
                sqLiteDatabase.close();
            }
        }
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
        openDatabase();

        ContentValues values = new ContentValues();
        //   values.put(CL_ID, soBan.getId());
        values.put(CL_NAME, soBan.getTenBan());
        values.put(CL_NUMBER, soBan.getSoBan());

        sqLiteDatabase.insert(TABLE_SO_BAN, null, values);
        closeDatabase();
    }

    public SoBan getDanhSachBan(int idDanhSachBan) {
        openDatabase();

        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.query(TABLE_SO_BAN, null, CL_ID + "=?", new String[]{String.valueOf(idDanhSachBan)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        SoBan soBan = new SoBan(cursor.getString(0), cursor.getString(1), cursor.getString(2));
        closeDatabase();
        return soBan;
    }

    public List<SoBan> getAllDanhSachBan() {
        openDatabase();
        List<SoBan> soBans = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_SO_BAN;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            SoBan soBan = new SoBan(cursor.getString(0), cursor.getString(1), cursor.getString(2));
            soBans.add(soBan);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return soBans;
    }

}
