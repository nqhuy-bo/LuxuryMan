package com.example.doanandroid.Retrofit;

import com.example.doanandroid.KhachHang;
import com.example.doanandroid.SanPham;
import com.example.doanandroid.SanPhamDangCho;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("dangnhap.php")
    Call<List<KhachHang>> loginUser(@Field("SODIENTHOAI") String SODIENTHOAI
            , @Field("MATKHAU") String MATKHAU);


    @FormUrlEncoded
    @POST
    Call<SanPham> getSanPham(@Field("MASANPHAM") String MASANPHAM);

    @FormUrlEncoded
    @POST("timkiem.php")
    Call<List<SanPham>> searchSanPham(@Field("TEXT") String TENSANPHAM);


    @GET("getdata.php")
    Call<List<SanPham>> getAllSanPham();

    @FormUrlEncoded
    @POST("GetSPTHEOLOAI.php")
    Call<List<SanPham>> LocSanPhamTheoLoai(@Field("MALOAI") int MALOAI);


    @GET("SanPhamMoi.php")
    Call<List<SanPham>> LaySanPhamMoi();

    @GET("SanPhamBanChay.php")
    Call<List<SanPham>> LaySanPhamBanChay();

    @FormUrlEncoded
    @POST("TaoHoaDon.php")
    Call<String> taoHoaDon(@Field("mahoadon") String mahoadon,@Field("sodienthoai") String sodienthoai,@Field("hotennguoinhanhang") String hotennguoinhanhang,@Field("tongtien") int tongtien,
                           @Field("trangthai") int trangthai,@Field("diachigiaohang") String diachigiaohang);


    @FormUrlEncoded
    @POST("ThemChiTietHoaDon.php")
    Call<String> TaoChiTietHoaDon(@Field("mahoadon") String mahoadon,@Field("masanpham") int masanpham,@Field("soluong") int soluong,
                                  @Field("gia") int gia);

    @FormUrlEncoded
    @POST("layDanhSachSPDangChoDuyet.php")
    Call<List<SanPhamDangCho>> layDanhSachChuaDuyet(@Field("sodienthoai") String sodienthoai);

    @FormUrlEncoded
    @POST("layDanhSachLichSuDaMua.php")
    Call<List<SanPhamDangCho>> layDanhSachLichSuDaMua(@Field("sodienthoai") String sodienthoai);
}
