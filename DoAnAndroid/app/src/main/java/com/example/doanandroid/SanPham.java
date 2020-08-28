package com.example.doanandroid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SanPham implements Serializable {

    @Expose
    @SerializedName("TENSANPHAM")
    String TENSANPHAM;
    @Expose
    @SerializedName("MOTASANPHAM")
    String MOTASANPHAM;

    @Expose
    @SerializedName("HINHANH")
    String HINHANH;

    @Expose
    @SerializedName("MASANPHAM")
    int MASANPHAM;

    @Expose
    @SerializedName("MALOAI")
    int MALOAI;

    @Expose
    @SerializedName("GIA")
    int GIA;

    @Expose
    @SerializedName("SOLUONG")
    int SOLUONG;



    public SanPham(int MASANPHAM, int MALOAI, int GIA, String TENSANPHAM, String MOTASANPHAM, String HINHANH) {
        this.MASANPHAM = MASANPHAM;
        this.MALOAI = MALOAI;
        this.GIA = GIA;
        this.TENSANPHAM = TENSANPHAM;
        this.MOTASANPHAM = MOTASANPHAM;
        this.HINHANH = HINHANH;
    }

    public SanPham(int MASANPHAM, String TENSANPHAM, String MOTASANPHAM, String HINHANH,int GIA,int SOLUONG) {
        this.MASANPHAM = MASANPHAM;
        this.TENSANPHAM = TENSANPHAM;
        this.MOTASANPHAM = MOTASANPHAM;
        this.HINHANH = HINHANH;
        this.GIA = GIA;
        this.SOLUONG = SOLUONG;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public SanPham() {
    }
    public void changeText1(String text)
    {
        this.TENSANPHAM = text;
    }

    public int getMASANPHAM() {
        return MASANPHAM;
    }

    public void setMASANPHAM(int MASANPHAM) {
        this.MASANPHAM = MASANPHAM;
    }

    public int getMALOAI() {
        return MALOAI;
    }

    public void setMALOAI(int MALOAI) {
        this.MALOAI = MALOAI;
    }

    public int getGIA() {
        return GIA;
    }

    public void setGIA(int GIA) {
        this.GIA = GIA;
    }

    public String getTENSANPHAM() {
        return TENSANPHAM;
    }

    public void setTENSANPHAM(String TENSANPHAM) {
        this.TENSANPHAM = TENSANPHAM;
    }

    public String getMOTASANPHAM() {
        return MOTASANPHAM;
    }

    public void setMOTASANPHAM(String MOTASANPHAM) {
        this.MOTASANPHAM = MOTASANPHAM;
    }

    public String getHINHANH() {
        return HINHANH;
    }

    public void setHINHANH(String HINHANH) {
        this.HINHANH = HINHANH;
    }
}
