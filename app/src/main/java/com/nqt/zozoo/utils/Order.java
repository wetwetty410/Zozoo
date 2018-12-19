package com.nqt.zozoo.utils;

/**
 * Created by USER on 12/19/2018.
 */

public class Order {
    private String id;
    private String maOrder;
    private String danhSachOrder;
    private String maBan;
    private String time;

    public Order(String id, String maOrder, String danhSachOrder, String maBan, String time) {
        this.id = id;
        this.maOrder = maOrder;
        this.danhSachOrder = danhSachOrder;
        this.maBan = maBan;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaOrder() {
        return maOrder;
    }

    public void setMaOrder(String maOrder) {
        this.maOrder = maOrder;
    }

    public String getDanhSachOrder() {
        return danhSachOrder;
    }

    public void setDanhSachOrder(String danhSachOrder) {
        this.danhSachOrder = danhSachOrder;
    }

    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", maOrder='" + maOrder + '\'' +
                ", danhSachOrder='" + danhSachOrder + '\'' +
                ", maBan='" + maBan + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
