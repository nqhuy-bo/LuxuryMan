package com.example.doanandroid.Frament;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.doanandroid.DangNhapActivity;
import com.example.doanandroid.R;

import static android.content.Context.MODE_PRIVATE;

public class TaiKhoanFragment extends Fragment {
    Button btnDangNhapDangKi;
    TextView txtTenKhachHang,txtSoDienThoai,txtDonHangDangChoDuyt,txtLichSuMuaHang;
    ImageView imgExitTaiKhoan;
    SharedPreferences sharedPref;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_taikhoan, container, false);
        btnDangNhapDangKi = v.findViewById(R.id.buttonDangNhapDangKiTaiKhoan);
        txtTenKhachHang = v.findViewById(R.id.textViewTenKhachHang);
        txtSoDienThoai = v.findViewById(R.id.textViewSoDienThoai);
        imgExitTaiKhoan = v.findViewById(R.id.imageExitTaiKhoan);
        txtDonHangDangChoDuyt = v.findViewById(R.id.textViewDonHangDangChoDuyet);
        txtLichSuMuaHang = v.findViewById(R.id.textViewDonHangCuaToi);


        sharedPref = getContext().getSharedPreferences("statusLogin", Context.MODE_PRIVATE);
        String status = sharedPref.getString("status", "false");
        String ten = sharedPref.getString("ten","false");
        String sdt = sharedPref.getString("user","Chưa có");
        if(status.equals("true")) {
            txtSoDienThoai.setVisibility(View.VISIBLE);
            txtTenKhachHang.setVisibility(View.VISIBLE);
            txtSoDienThoai.setText(txtSoDienThoai.getText() + sdt);
            txtTenKhachHang.setText(txtTenKhachHang.getText() + ten);
            btnDangNhapDangKi.setVisibility(View.GONE);
        }
        else
        {
            txtSoDienThoai.setVisibility(View.GONE);
            txtTenKhachHang.setVisibility(View.GONE);
            btnDangNhapDangKi.setVisibility(View.VISIBLE);
        }

        btnDangNhapDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DangNhapActivity.class));
            }
        });

        imgExitTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pre = getContext().getSharedPreferences("statusLogin",MODE_PRIVATE);
                sharedPref = getContext().getSharedPreferences("infomation", MODE_PRIVATE);
                //tạo đối tượng để lưuu
                SharedPreferences.Editor editor = pre.edit();
                editor.clear();
                editor.commit();
                //getActivity().finish();
                startActivity(new Intent(getActivity(),DangNhapActivity.class));
            }
        });

        txtDonHangDangChoDuyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayoutContent, new DonHangChoDuyetFragment());
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });


        txtLichSuMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayoutContent, new LichSuMuaHangFragment());
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });


        return v;
    }
}

