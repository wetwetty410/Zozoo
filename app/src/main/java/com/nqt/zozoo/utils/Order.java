package com.nqt.zozoo.utils;


public class Order {
    private String id;
    private String maOrder;
    private String maBan;
    private String nguoiOrder;
    private String timeCreate;
    private String timeUpdate;

    public Order(String id, String maOrder, String maBan, String nguoiOrder, String timeCreate, String timeUpdate) {
        this.id = id;
        this.maOrder = maOrder;
        this.maBan = maBan;
        this.nguoiOrder = nguoiOrder;
        this.timeCreate = timeCreate;
        this.timeUpdate = timeUpdate;
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
