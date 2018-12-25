package com.nqt.zozoo.utils;


public class OrderList {
    private String id;
    private String maOrder;
    private String maMonAn;
    private String tenMonAn;
    private String giaTien;
    private int soLuong;

    public OrderList() {
    }

    public OrderList(String id, String maOrder, String maMonAn, String tenMonAn, String giaTien, int soLuong) {
        this.id = id;
        this.maOrder = maOrder;
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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

    public String getMaMonAn() {
        return maMonAn;
    }

    public void setMaMonAn(String maMonAn) {
        this.maMonAn = maMonAn;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public boolean equalsMaMonAn(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderList list = (OrderList) o;

        return maMonAn.equals(list.maMonAn);
    }

    @Override
    public int hashCode() {
        return maMonAn.hashCode();
    }
}
