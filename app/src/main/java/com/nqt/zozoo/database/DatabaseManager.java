package com.nqt.zozoo.database;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by USER on 12/10/2018.
 */

public class DatabaseManager {
    private static final String DATABASE_NAME = "nhahang";
    private final String databasePath;
    private Context context;
    protected SQLiteDatabase sqLiteDatabase;

    public DatabaseManager(Context context) {
        databasePath = context.getFilesDir().getPath() + File.separator + DATABASE_NAME;
        this.context = context;
        copyDatabaseFromAssets(context);
    }

    void openDatabase() {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            sqLiteDatabase = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    void closeDatabase() {
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
}
