package com.nqt.zozoo.utils;


public class OrderListFood {
    private String id;
    private String maOrder;
    private String maMonAn;
    private String tenMonAn;
    private String giaTien;
    private int soLuong;
    private String trangThai;

    public OrderListFood() {
    }

    public OrderListFood(String id, String maOrder, String maMonAn, String tenMonAn, String giaTien, int soLuong) {
        this.id = id;
        this.maOrder = maOrder;
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
    }

    public OrderListFood(String id, String maOrder, String maMonAn, String tenMonAn, String giaTien, int soLuong, String trangThai) {
        this.id = id;
        this.maOrder = maOrder;
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
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

        OrderListFood list = (OrderListFood) o;

        return maMonAn.equals(list.maMonAn);
    }

    public boolean equalsMonAn(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderListFood that = (OrderListFood) o;

        return soLuong == that.soLuong && maMonAn.equals(that.maMonAn) && tenMonAn.equals(that.tenMonAn) && giaTien.equals(that.giaTien);

    }

    @Override
    public int hashCode() {
        return maMonAn.hashCode();
    }
}
