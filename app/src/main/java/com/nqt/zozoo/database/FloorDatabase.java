package com.nqt.zozoo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.nqt.zozoo.utils.Floor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by USER on 12/10/2018.
 */

public class FloorDatabase extends DatabaseManager {
    private static final String TAG = FloorDatabase.class.getName();
    private static final String TANG = "tang";
    private static final String TANG_ID = "id";
    private static final String TANG_MA = "ma_tang";
    private static final String TANG_TEN = "ten_tang";

    public FloorDatabase(Context context) {
        super(context);
    }

    public void addTang(Floor tang) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        Map<String, Object> floorMap = new HashMap<>();
        floorMap.put(TANG_ID, tang.getId());
        floorMap.put(TANG_MA, tang.getMaTang());
        floorMap.put(TANG_TEN, tang.getTenTang());

        db.collection(TANG)
                .add(floorMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
        openDatabase();
        ContentValues values = new ContentValues();
        values.put(TANG_MA, tang.getMaTang());
        values.put(TANG_TEN, tang.getTenTang());


        sqLiteDatabase.insert(TANG, null, values);
        closeDatabase();
    }


    public void updateTang(Floor tang, String idTang) {
        openDatabase();

        ContentValues values = new ContentValues();
        values.put(TANG_MA, tang.getMaTang());
        values.put(TANG_TEN, tang.getTenTang());

        sqLiteDatabase.update(TANG, values, TANG_ID + "=?", new String[]{idTang});
        closeDatabase();
    }

    public void deleteTang(String idTang) {
        openDatabase();
        sqLiteDatabase.delete(TANG, TANG_ID + "=?", new String[]{idTang});
        closeDatabase();
    }

    public Floor getTang(String maTang) {
        openDatabase();

        Cursor cursor = sqLiteDatabase.query(TANG, null, TANG_MA + "=?", new String[]{String.valueOf(maTang)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Floor tang = new Floor(cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2));
        cursor.close();
        closeDatabase();
        return tang;
    }

    public List<Floor> getAllTang() {
        openDatabase();
        List<Floor> tangList = new ArrayList<>();
        String query = "SELECT * FROM " + TANG;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Floor tang = new Floor(cursor.getString(0),
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
