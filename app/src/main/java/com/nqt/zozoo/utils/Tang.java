package com.nqt.zozoo.utils;

/**
 * Created by USER on 12/10/2018.
 */

public class Tang {
    private String id;
    private String maTang;
    private String tenTang;

    public Tang(String id, String maTang, String tenTang) {
        this.id = id;
        this.maTang = maTang;
        this.tenTang = tenTang;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaTang() {
        return maTang;
    }

    public void setMaTang(String maTang) {
        this.maTang = maTang;
    }

    public String getTenTang() {
        return tenTang;
    }

    public void setTenTang(String tenTang) {
        this.tenTang = tenTang;
    }
}
