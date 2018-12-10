package com.nqt.zozoo.utils;

import android.content.Context;

import com.nqt.zozoo.database.BanDatabase;

import java.util.List;
import java.util.Map;

/**
 * Created by USER on 12/3/2018.
 */

public class Ban {
    public String id;
    private String maBan;
    private String tenBan;
    private String maTang;
    private String maLoaiBan;

    public Ban(String id, String maBan, String tenBan, String maTang, String maLoaiBan) {
        this.id = id;
        this.maBan = maBan;
        this.tenBan = tenBan;
        this.maTang = maTang;
        this.maLoaiBan = maLoaiBan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public String getMaTang() {
        return maTang;
    }

    public void setMaTang(String maTang) {
        this.maTang = maTang;
    }

    public String getMaLoaiBan() {
        return maLoaiBan;
    }

    public void setMaLoaiBan(String maLoaiBan) {
        this.maLoaiBan = maLoaiBan;
    }
}
