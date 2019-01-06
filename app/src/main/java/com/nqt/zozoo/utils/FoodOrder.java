package com.nqt.zozoo.utils;

import java.util.Objects;

public class FoodOrder {
    private String id;
    private String maOrder;
    private String tenBanOrder;
    private String tenMonOrder;
    private String soLuongOrder;
    private String trangThaiOrder;
    private String maBan;
    private String maMon;

    public FoodOrder() {
    }

    public FoodOrder(String id, String maOrder, String tenBanOrder, String tenMonOrder, String soLuongOrder, String trangThaiOrder, String maBan, String maMon) {
        this.id = id;
        this.maOrder = maOrder;
        this.tenBanOrder = tenBanOrder;
        this.tenMonOrder = tenMonOrder;
        this.soLuongOrder = soLuongOrder;
        this.trangThaiOrder = trangThaiOrder;
        this.maBan = maBan;
        this.maMon = maMon;
    }

    public boolean equalsStatus(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodOrder foodOrder = (FoodOrder) o;
        return Objects.equals(trangThaiOrder, foodOrder.trangThaiOrder);
    }


    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
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

    public String getTenBanOrder() {
        return tenBanOrder;
    }

    public void setTenBanOrder(String tenBanOrder) {
        this.tenBanOrder = tenBanOrder;
    }

    public String getTenMonOrder() {
        return tenMonOrder;
    }

    public void setTenMonOrder(String tenMonOrder) {
        this.tenMonOrder = tenMonOrder;
    }

    public String getSoLuongOrder() {
        return soLuongOrder;
    }

    public void setSoLuongOrder(String soLuongOrder) {
        this.soLuongOrder = soLuongOrder;
    }

    public String getTrangThaiOrder() {
        return trangThaiOrder;
    }

    public void setTrangThaiOrder(String trangThaiOrder) {
        this.trangThaiOrder = trangThaiOrder;
    }
}
