package com.nqt.zozoo.utils;

import java.util.List;

/**
 * Created by USER on 12/10/2018.
 */

public class LoaiBan {
    private String id;
    private String maLoaiBan;
    private String tenLoaiBan;

    public LoaiBan(String id, String maLoaiBan, String tenLoaiBan) {
        this.id = id;
        this.maLoaiBan = maLoaiBan;
        this.tenLoaiBan = tenLoaiBan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaLoaiBan() {
        return maLoaiBan;
    }

    public void setMaLoaiBan(String maLoaiBan) {
        this.maLoaiBan = maLoaiBan;
    }

    public String getTenLoaiBan() {
        return tenLoaiBan;
    }

    public void setTenLoaiBan(String tenLoaiBan) {
        this.tenLoaiBan = tenLoaiBan;
    }
}
