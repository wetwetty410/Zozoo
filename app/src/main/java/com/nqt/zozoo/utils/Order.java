package com.nqt.zozoo.utils;


public class Order {
    private String id;
    private String maOrder;
    private String maBan;
    private String nguoiOrder;
    private String SoLuongMon;
    private String soLuongDo;
    private String tongTien;
    private String timeCreate;
    private String timeUpdate;

    public Order() {
    }

    public Order(String id, String maOrder, String maBan, String nguoiOrder, String soLuongMon, String soLuongDo, String tongTien, String timeCreate, String timeUpdate) {
        this.id = id;
        this.maOrder = maOrder;
        this.maBan = maBan;
        this.nguoiOrder = nguoiOrder;
        SoLuongMon = soLuongMon;
        this.soLuongDo = soLuongDo;
        this.tongTien = tongTien;
        this.timeCreate = timeCreate;
        this.timeUpdate = timeUpdate;
    }

    public String getSoLuongMon() {
        return SoLuongMon;
    }

    public void setSoLuongMon(String soLuongMon) {
        SoLuongMon = soLuongMon;
    }

    public String getSoLuongDo() {
        return soLuongDo;
    }

    public void setSoLuongDo(String soLuongDo) {
        this.soLuongDo = soLuongDo;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    public String getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(String timeUpdate) {
        this.timeUpdate = timeUpdate;
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

    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    public String getNguoiOrder() {
        return nguoiOrder;
    }

    public void setNguoiOrder(String nguoiOrder) {
        this.nguoiOrder = nguoiOrder;
    }

}
