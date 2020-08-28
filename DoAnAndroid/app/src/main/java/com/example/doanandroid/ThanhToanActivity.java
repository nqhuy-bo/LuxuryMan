package com.example.doanandroid;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doanandroid.Retrofit.ApiInterface;
import com.example.doanandroid.Retrofit.Retrofitclient;
import com.google.android.material.textfield.TextInputLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThanhToanActivity extends AppCompatActivity {

    EditText edtHoTenNguoiNhan,edtDiaChiNhanHang,edtTongTien,edtSoDienThoaiNguoiNhan;
    Button btnXacNhanThanhToan;
    ApiInterface apiInterface;
    SharedPreferences pre;
    int tongTien;
    int thanhTien;
    String diaChiNhanHang;
    ArrayList<SanPham> listSP ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        AnhXa();
        listSP = new ArrayList<>();
        listSP = layDanhSachSanPhamTrongGioHang();
        apiInterface= Retrofitclient.getClient().create(ApiInterface.class);
        final String soDienThoai;
        final String tenNguoiNhan;

        pre = getSharedPreferences("statusLogin", Context.MODE_PRIVATE);
        soDienThoai = pre.getString("user","Số điện thoại người nhận");
        tenNguoiNhan = pre.getString("ten","Tên người nhận hàng");
        edtHoTenNguoiNhan.setText(tenNguoiNhan);
        for(int i=0;i<listSP.size();i++)
        {
            tongTien+=(listSP.get(i).getGIA()*listSP.get(i).getSOLUONG());
        }
        edtTongTien.setText(tongTien + "VNĐ");
        edtSoDienThoaiNguoiNhan.setText(soDienThoai+"");


        btnXacNhanThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listSP = layDanhSachSanPhamTrongGioHang();
                final String maHD = taoMaHoaDon();
                tongTien=0;
                for(int i=0;i<listSP.size();i++)
                {
                    tongTien+=(listSP.get(i).getGIA()*listSP.get(i).getSOLUONG());
                }
                //tongTien = Integer.parseInt(edtTongTien.getText().toString().trim());
                diaChiNhanHang = edtDiaChiNhanHang.getText().toString();
                Call<String> dathang = apiInterface.taoHoaDon(maHD,soDienThoai,tenNguoiNhan,tongTien,0,diaChiNhanHang);
                //Call<String> dathang = apiInterface.taoHoaDon("013652478",2000000,0,"Hà Tĩnh");
                dathang.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.body() != null) {
                            //Toast.makeText(ThanhToanActivity.this, ""+response.body().toString(), Toast.LENGTH_SHORT).show();
                            if (response.body().toString().contains("Success")) {
                                //Toast.makeText(ThanhToanActivity.this, "Đạt hàng thành công chờ nhận hàng nhé", Toast.LENGTH_SHORT).show();

                                //Thực hiện thêm vào chi tiết hóa đơn
                                for(int i=0;i<listSP.size();i++)
                                {
                                    Call<String> chitiethoadon = apiInterface.TaoChiTietHoaDon(maHD,listSP.get(i).getMASANPHAM(),listSP.get(i).getSOLUONG(),
                                            (listSP.get(i).GIA*listSP.get(i).getSOLUONG()));
                                    chitiethoadon.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            if (response.body() != null) {
                                                //Toast.makeText(ThanhToanActivity.this, ""+response.body().toString(), Toast.LENGTH_SHORT).show();
                                                if (response.body().toString().contains("Success")) {
                                                    Toast.makeText(ThanhToanActivity.this, "ok", Toast.LENGTH_SHORT).show();
                                                    WellCome.data.QueryData("DELETE FROM GIOHANG");
                                                    finish();
                                                    startActivity(new Intent(ThanhToanActivity.this,TrangChuActivity.class));
                                                }
                                                else
                                                    Toast.makeText(ThanhToanActivity.this, " lôi", Toast.LENGTH_SHORT).show();

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {
                                            Toast.makeText(ThanhToanActivity.this, "no ok", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                }

                            }else
                                Toast.makeText(ThanhToanActivity.this, "Không thêm được" + response.toString().trim(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(ThanhToanActivity.this, "Sai cm nó rồi", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }

    private void AnhXa() {

        edtDiaChiNhanHang = findViewById(R.id.editTextDiaChiNhanHang);
        edtHoTenNguoiNhan = findViewById(R.id.editTextTenNguoiNhanHang);
        edtTongTien  = findViewById(R.id.editTextTongTienThanhToan);
        edtSoDienThoaiNguoiNhan = findViewById(R.id.editTextSoDienThoaiNguoiNhan);
        btnXacNhanThanhToan  =findViewById(R.id.buttonXacNhanDonHang);
    }


    public ArrayList<SanPham> layDanhSachSanPhamTrongGioHang(){


        Cursor SP = WellCome.data.GetData("SELECT * FROM GIOHANG");

        while(SP.moveToNext()){
            listSP.add(new SanPham(SP.getInt(1),SP.getString(2),SP.getString(3),
                    SP.getString(4),SP.getInt(5),SP.getInt(6)));
            thanhTien+=SP.getInt(5)*SP.getInt(6);
        }

        return listSP;
    }

    public String taoMaHoaDon()
    {
        String ma= "";
        Calendar c = Calendar.getInstance();
        ma = c.get(Calendar.YEAR)+"" + c.get(Calendar.MONTH) + c.get(Calendar.DATE) +c.get(Calendar.HOUR) + c.get(Calendar.MINUTE) + c.get(Calendar.SECOND);
        return  ma;
    }
}