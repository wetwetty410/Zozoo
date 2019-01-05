package com.nqt.zozoo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.nqt.zozoo.utils.FoodStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 1/5/2019.
 */

public class FoodStatusDatabase extends DatabaseManager {
    public static final String FOOD_STATUS = "food_status";
    public static final String FOOD_STATUS_ID = "id";
    public static final String FOOD_STATUS_ID_STATUS = "id_status";
    public static final String FOOD_STATUS_NAME_STATUS = "name_status";

    public FoodStatusDatabase(Context context) {
        super(context);
    }

    public List<FoodStatus> getAllFoodStatus() {
        openDatabase();
        List<FoodStatus> foodStatuses = new ArrayList<>();
        String sql = "SELECT * FROM " + FOOD_STATUS;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            FoodStatus foodStatus = new FoodStatus(
                    cursor.getString(cursor.getColumnIndex(FOOD_STATUS_ID)),
                    cursor.getString(cursor.getColumnIndex(FOOD_STATUS_ID_STATUS)),
                    cursor.getString(cursor.getColumnIndex(FOOD_STATUS_NAME_STATUS))
            );
            cursor.moveToNext();
            foodStatuses.add(foodStatus);
        }
        cursor.close();
        closeDatabase();
        return foodStatuses;
    }

    public HashMap<String, String> mapAllFoodStatus() {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        List<FoodStatus> foodStatuses = getAllFoodStatus();
        for (FoodStatus foodStatus : foodStatuses) {
            stringStringHashMap.put(foodStatus.getIdStatus(), foodStatus.getNameStatus());
        }
        return stringStringHashMap;
    }
}
