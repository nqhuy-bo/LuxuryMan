package com.example.doanandroid;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KhachHang {
    @Expose
    @SerializedName("HOTEN")
    private String HOTEN;
    @Expose
    @SerializedName("SODIENTHOAI")
    private String SODIENTHOAI;
    @Expose
    @SerializedName("MATKHAU")
    private String MATKHAU;


    public KhachHang() {
    }

    public KhachHang(String HOTEN, String SODIENTHOAI, String MATKHAU) {
        this.HOTEN = HOTEN;
        this.SODIENTHOAI = SODIENTHOAI;
        this.MATKHAU = MATKHAU;
    }

    public String getHOTEN() {
        return HOTEN;
    }

    public void setHOTEN(String HOTEN) {
        this.HOTEN = HOTEN;
    }

    public String getSODIENTHOAI() {
        return SODIENTHOAI;
    }

    public void setSODIENTHOAI(String SODIENTHOAI) {
        this.SODIENTHOAI = SODIENTHOAI;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }
}
