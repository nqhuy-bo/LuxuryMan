package com.example.doanandroid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SanPhamDangCho implements Serializable {
    @Expose
    @SerializedName("TENSANPHAM")
    String TENSANPHAM;

    @Expose
    @SerializedName("SOLUONG")
    int SOLUONG;

    @Expose
    @SerializedName("GIA")
    int GIA;

    @Expose
    @SerializedName("HINHANH")
    String HINHANH;

    public SanPhamDangCho() {
    }

    public SanPhamDangCho(String TENSANPHAM, int SOLUONG, int GIA, String HINHANH) {
        this.TENSANPHAM = TENSANPHAM;
        this.SOLUONG = SOLUONG;
        this.GIA = GIA;
        this.HINHANH = HINHANH;
    }

    public String getTENSANPHAM() {
        return TENSANPHAM;
    }

    public void setTENSANPHAM(String TENSANPHAM) {
        this.TENSANPHAM = TENSANPHAM;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public int getGIA() {
        return GIA;
    }

    public void setGIA(int GIA) {
        this.GIA = GIA;
    }

    public String getHINHANH() {
        return HINHANH;
    }

    public void setHINHANH(String HINHANH) {
        this.HINHANH = HINHANH;
    }
}
