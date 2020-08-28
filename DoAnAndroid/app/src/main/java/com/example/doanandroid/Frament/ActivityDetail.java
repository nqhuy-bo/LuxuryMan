package com.example.doanandroid.Frament;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


import com.example.doanandroid.DataBase;
import com.example.doanandroid.R;
import com.example.doanandroid.SanPham;
import com.example.doanandroid.TrangChuActivity;
import com.example.doanandroid.WellCome;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ActivityDetail extends AppCompatActivity implements View.OnClickListener {

    ImageView imgHinhDetail,quaylai,imgGioHangDetail;
    TextView txtGiaDetail,txtTenDetail,txtMoTaDetail,txtSoLuongDaital,txttrangChuSoLuong;
    TextView edtSoLuongDetail;
    Animation topAnim, bottomAnim;
    androidx.cardview.widget.CardView cardView;
    ImageButton btnCongDetail,btnTruDetail;
    Button btnMuaNgayDetail,btnThemVaoGioDetail;
    int MASP = -1;
    int MALOAI = -1,gia;
    String hinh,tenSP,motaSP;
    ArrayList<SanPham> listSP;
    androidx.cardview.widget.CardView detalsoluongtang,trangchusoluongtang;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_detail);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        AnhXa();
        LoadDataFromIntent();
//        getSupportActionBar().setTitle("Chi tiết sản phẩm");
        XuLyClick();

        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), TrangChuActivity.class));
                overridePendingTransition(R.anim.zoom_out,R.anim.star_animation);

            }
        });
//        imgGioHangDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), ));
//                overridePendingTransition(R.anim.zoom_out,R.anim.star_animation);
//                finish();
//            }
//        });



        cardView.setAnimation(bottomAnim);

        listSP= new ArrayList<>();
        listSP = layDanhSachSanPhamTrongGioHang();
        if(listSP.isEmpty())
        {


            detalsoluongtang.setVisibility(View.GONE);

        }
        else {
            detalsoluongtang.setVisibility(View.VISIBLE);
            // trangchusoluongtang.setVisibility(View.VISIBLE);
        }


    }

    private void XuLyClick() {
        btnCongDetail.setOnClickListener(this);
        btnTruDetail.setOnClickListener(this);
        btnMuaNgayDetail.setOnClickListener(this);
        btnThemVaoGioDetail.setOnClickListener(this);
        imgGioHangDetail.setOnClickListener(this);

    }

    private void LoadDataFromIntent() {
        Intent intent = getIntent();
        txtTenDetail.setText(intent.getStringExtra("TENSP"));
        txtGiaDetail.setText((intent.getIntExtra("GIA",0))+"VNĐ");
        txtMoTaDetail.setText(intent.getStringExtra("MOTA"));
        tenSP = intent.getStringExtra("TENSP");
        motaSP = intent.getStringExtra("MOTA");
        gia = intent.getIntExtra("GIA",0);
        MALOAI = intent.getIntExtra("MALOAI",-1);
        MASP = intent.getIntExtra("MASP",-1);
        hinh = intent.getStringExtra("HINH");
        Picasso.with(this)
                .load(hinh)
                .placeholder(R.drawable.ic_wait)
                .error(R.drawable.ic_offline)
                .into(imgHinhDetail);

    }

    private void AnhXa() {
        imgHinhDetail = findViewById(R.id.imageViewDetail);
        txtGiaDetail = findViewById(R.id.textViewGiaDetail);
        txtTenDetail = findViewById(R.id.textViewTenDetail);
        txtMoTaDetail = findViewById(R.id.textViewMoTaDetail);
        edtSoLuongDetail = findViewById(R.id.editTextSoLuongDetail);
        btnCongDetail = findViewById(R.id.imageButtonCongDetail);
        btnTruDetail = findViewById(R.id.imageButtonTruDetail);
        cardView = findViewById(R.id.cardView);
        btnMuaNgayDetail = findViewById(R.id.buttonMuaNgayDetail);
        btnThemVaoGioDetail = findViewById(R.id.buttonThemVaoGioDetail);
        quaylai = findViewById(R.id.imageViewQuayLai);
        txtSoLuongDaital= findViewById(R.id.daitelsoluong);
        detalsoluongtang = findViewById(R.id.daitalsoluongtang);
        imgGioHangDetail = findViewById(R.id.imageViewGioHang);
        trangchusoluongtang= findViewById(R.id.trangchusoluongtang);
        txttrangChuSoLuong= findViewById(R.id.trangchusoluong);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imageViewGioHang:
                Intent intent = new Intent(this,TrangChuActivity.class);
                intent.putExtra("giohang",1);
                startActivity(intent);
                finish();
                break;
            case R.id.imageButtonCongDetail:
                int sl = Integer.parseInt(edtSoLuongDetail.getText().toString());
                sl = sl+1;
                edtSoLuongDetail.setText(sl+"");
                break;
            case R.id.imageButtonTruDetail:
                int sll = Integer.parseInt(edtSoLuongDetail.getText().toString());
                if(sll>0)
                    sll = sll-1;
                edtSoLuongDetail.setText(sll+"");
                break;
            case R.id.buttonMuaNgayDetail:
                Toast.makeText(this, "Bạn chọn mua ngay", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonThemVaoGioDetail:
                if(Integer.parseInt(edtSoLuongDetail.getText().toString())==0)
                {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(ActivityDetail.this);
                    dialog.setTitle("Ôi không tôi không biết bạn muốn mua bao nhiêu chiếc !!!");
                    dialog.setMessage("Chọn số lượng bạn muốn thêm vào giỏ đi nào !");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    dialog.show();
                }
                else {
                    Cursor check = WellCome.data.GetData("SELECT * FROM GIOHANG WHERE MASANPHAM ='"+MASP+"'");
                    if(check.moveToNext())
                    {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(ActivityDetail.this);
                        dialog.setTitle("Thông báo !!!");
                        dialog.setMessage("Sản phẩm đã có trong giỏ hảng bạn có muốn thêm "+edtSoLuongDetail.getText()+" cái vào giỏ ?");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int soluong = Integer.parseInt(edtSoLuongDetail.getText().toString());
                                Cursor sp = WellCome.data.GetData("SELECT * FROM GIOHANG WHERE MASANPHAM ='"+MASP+"'");
                                double sl =0;
                                while (sp.moveToNext())
                                {
                                    sl = sp.getDouble(6);
                                }

                                WellCome.data.QueryData("UPDATE GIOHANG" +" SET SOLUONG = " +(soluong+sl)+
                                        " WHERE MASANPHAM='"+MASP+"'");
                                listSP = layDanhSachSanPhamTrongGioHang();
                                detalsoluongtang.setVisibility(View.VISIBLE);
                                //  trangchusoluongtang.setVisibility(View.VISIBLE);
                            }
                        });

                        dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        dialog.show();
                    }
                    else {
                        int soluong = Integer.parseInt(edtSoLuongDetail.getText().toString());
                        WellCome.data.INSERT_SP_GIOHANG(MASP, tenSP, motaSP, hinh, gia, soluong);
                        Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        listSP = layDanhSachSanPhamTrongGioHang();
                        detalsoluongtang.setVisibility(View.VISIBLE);
                        // trangchusoluongtang.setVisibility(View.VISIBLE);
                        //    txttrangChuSoLuong.setText(txtSoLuongDaital+"");
                    }
                }
                break;
        }
    }

    public ArrayList<SanPham> layDanhSachSanPhamTrongGioHang(){


        Cursor SP = WellCome.data.GetData("SELECT * FROM GIOHANG");
        int tongtien = 0;
        while(SP.moveToNext()){
            listSP.add(new SanPham(SP.getInt(1),SP.getString(2),SP.getString(3),
                    SP.getString(4),SP.getInt(5),SP.getInt(6)));
            tongtien+=SP.getInt(6);
        }

        txtSoLuongDaital.setText(tongtien+"");
        return listSP;
    }
}
