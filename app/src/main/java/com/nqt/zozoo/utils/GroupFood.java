package com.nqt.zozoo.utils;

import android.content.Context;

import com.nqt.zozoo.database.DatabaseManager;

/**
 * Created by USER on 12/12/2018.
 */

public class GroupFood {
    private String id;
    private String maNhonMonAn;
    private String tenNhonMonAn;

    public GroupFood(String id, String maNhonMonAn, String tenNhonMonAn) {
        this.id = id;
        this.maNhonMonAn = maNhonMonAn;
        this.tenNhonMonAn = tenNhonMonAn;
    }

    public GroupFood() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaNhonMonAn() {
        return maNhonMonAn;
    }

    public void setMaNhonMonAn(String maNhonMonAn) {
        this.maNhonMonAn = maNhonMonAn;
    }

    public String getTenNhonMonAn() {
        return tenNhonMonAn;
    }

    public void setTenNhonMonAn(String tenNhonMonAn) {
        this.tenNhonMonAn = tenNhonMonAn;
    }
}
