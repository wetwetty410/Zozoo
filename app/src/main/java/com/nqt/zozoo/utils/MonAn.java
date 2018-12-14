package com.nqt.zozoo.utils;

/**
 * Created by USER on 12/10/2018.
 */

public class MonAn {
    private String id;
    private String maMonAn;
    private String tenMonAn;
    private String nhomMonAn;
    private int donGia;
    private String donViTinh;
    private int soLuong;

    public MonAn(String id, String maMonAn, String tenMonAn, String nhomMonAn, int donGia, String donViTinh, int soLuong) {
        this.id = id;
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.nhomMonAn = nhomMonAn;
        this.donGia = donGia;
        this.donViTinh = donViTinh;
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

    public String getNhomMonAn() {
        return nhomMonAn;
    }

    public void setNhomMonAn(String nhomMonAn) {
        this.nhomMonAn = nhomMonAn;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }
}
