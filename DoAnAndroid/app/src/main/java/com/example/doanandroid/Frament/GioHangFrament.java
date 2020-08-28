package com.example.doanandroid.Frament;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid.CustomSanPhamAdapter;
import com.example.doanandroid.DangNhapActivity;
import com.example.doanandroid.R;
import com.example.doanandroid.SanPham;
import com.example.doanandroid.ThanhToanActivity;
import com.example.doanandroid.TrangChuActivity;
import com.example.doanandroid.WellCome;
import com.example.doanandroid.adapter.SPGioHangAdapter;
import com.example.doanandroid.adapter.SPGioHanggAdapter;

import java.util.ArrayList;

public class GioHangFrament extends Fragment {

    RecyclerView rcyDanhSachSanPham;
    ListView lstDanhSachSanPham;
    Button btnThanhToan,btnTiepTucMuaHang;
    TextView txtTongTien,txtTrangThaiGio;
    ArrayList<SanPham> listSP ;
    ArrayList<SanPham> soLuong;
    SPGioHangAdapter adapter;
    SPGioHanggAdapter gioHanggAdapter;
    com.airbnb.lottie.LottieAnimationView lotte;
    LinearLayout thanhtoan;
    boolean xoa=false;
    TextView txttrangChuSoLuong;
    SharedPreferences sharedPref;




    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_giohang,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AnhXa();

        listSP= new ArrayList<>();
        listSP = layDanhSachSanPhamTrongGioHang();

        soLuong= new ArrayList<>();
   //     soLuong = layDanhSachSanPhamTrongGioHang1();

        if(listSP.isEmpty())
        {

            lotte.setVisibility(View.VISIBLE);
            txtTrangThaiGio.setVisibility(View.VISIBLE);
            thanhtoan.setVisibility(View.GONE);
        }
        else {
            lotte.setVisibility(View.GONE);
             txtTrangThaiGio.setVisibility(View.GONE);
            thanhtoan.setVisibility(View.VISIBLE);
          //  thanhtoan.setVisibility(View.VISIBLE);
        }

     //   adapter = new SPGioHangAdapter(getContext(),R.layout.custom_dong_sanpham,listSP);
      //  lstDanhSachSanPham.setAdapter(adapter);


        rcyDanhSachSanPham.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        rcyDanhSachSanPham.setLayoutManager(layoutManager);
        gioHanggAdapter = new SPGioHanggAdapter(listSP,getContext());
        gioHanggAdapter.notifyDataSetChanged();
        rcyDanhSachSanPham.setAdapter(gioHanggAdapter);
       // adapter.notifyDataSetChanged();
//
//        lstDanhSachSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Toast.makeText(getContext(), "heelo ", Toast.LENGTH_SHORT).show();
//            }
//        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPref = getContext().getSharedPreferences("statusLogin", Context.MODE_PRIVATE);
                String status = sharedPref.getString("status", "false");
                String ten = sharedPref.getString("ten","false");
                String sdt = sharedPref.getString("user","Chưa có");
                if(status.equals("true")) {
                    layDanhSachSanPhamTrongGioHang();
                    startActivity(new Intent(getActivity(), ThanhToanActivity.class));
                    Toast.makeText(getContext(), "Bạn chọn thanh toán.Nhưng toi chưa làm", Toast.LENGTH_SHORT).show();
                }
                else
                    startActivity(new Intent(getActivity(), DangNhapActivity.class));


            }
        });



        gioHanggAdapter.setOnItemClickListenner(new SPGioHanggAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                SanPham sp ;
                Intent intent = new Intent(getContext(), ActivityDetail.class);
                sp= listSP.get(position);
                intent.putExtra("MASP",sp.getMASANPHAM());
                intent.putExtra("TENSP",sp.getTENSANPHAM());
                intent.putExtra("GIA",sp.getGIA());
                intent.putExtra("HINH",sp.getHINHANH());
                intent.putExtra("MALOAI",sp.getMALOAI());
                intent.putExtra("MOTA",sp.getMOTASANPHAM());
                startActivity(intent);
               // getActivity().finish();
                Toast.makeText(getContext(), ""+sp.getTENSANPHAM(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void deleteClick(final int position) {
                final SanPham sanPham =listSP.get(position);
                listSP = layDanhSachSanPhamTrongGioHang();
                if(listSP.isEmpty())
                {

                    lotte.setVisibility(View.VISIBLE);
                    txtTrangThaiGio.setVisibility(View.VISIBLE);
                    thanhtoan.setVisibility(View.GONE);
                }
                else {
                    lotte.setVisibility(View.GONE);
                    txtTrangThaiGio.setVisibility(View.GONE);
                    thanhtoan.setVisibility(View.VISIBLE);
                    //  thanhtoan.setVisibility(View.VISIBLE);
                }
           //     soLuong = layDanhSachSanPhamTrongGioHang1();



                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("Thông báo !!!");
                dialog.setMessage("Bạn muốn xóa sản phẩm "+sanPham.getTENSANPHAM()+" khỏi giỏ hàng ???");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WellCome.data.QueryData("DELETE FROM GIOHANG WHERE MASANPHAM = '"+ sanPham.getMASANPHAM()+"'");

                listSP.remove(position);
                gioHanggAdapter.notifyItemRemoved(position);
                WellCome.data.QueryData("DELETE FROM GIOHANG WHERE MASANPHAM = '"+ sanPham.getMASANPHAM()+"'");
                ((TrangChuActivity)getActivity()).layDanhSachSanPhamTrongGioHang();
                    }
                });

                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


                dialog.show();


            }
        });



    }




    private void AnhXa() {
       // lstDanhSachSanPham = getView().findViewById(R.id.listViewGioHang);
        btnThanhToan = getView().findViewById(R.id.buttonThanhToanGioHang);
     //   btnTiepTucMuaHang = getView().findViewById(R.id.buttonTiepTucMua);
        txtTongTien = getView().findViewById(R.id.textViewGiaTri);
        txtTrangThaiGio =  getView().findViewById(R.id.textViewTrangThaiGioHang);
        rcyDanhSachSanPham = getView().findViewById(R.id.listViewGioHang);
        lotte = getView().findViewById(R.id.animationView);
        thanhtoan=getView().findViewById(R.id.thanhtoangiohang);
        //txttrangChuSoLuong= getView().findViewById(R.id.trangchusoluong);

    }

    public ArrayList<SanPham> layDanhSachSanPhamTrongGioHang(){


        Cursor SP = WellCome.data.GetData("SELECT * FROM GIOHANG");
        int tongtien = 0;
        while(SP.moveToNext()){
            listSP.add(new SanPham(SP.getInt(1),SP.getString(2),SP.getString(3),
                    SP.getString(4),SP.getInt(5),SP.getInt(6)));
            tongtien+=SP.getInt(5)*SP.getInt(6);
        }

        txtTongTien.setText(tongtien+" VNĐ");
        return listSP;
    }



}

