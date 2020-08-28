package com.example.doanandroid.Frament;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.doanandroid.CustomSanPhamAdapter;
import com.example.doanandroid.R;
import com.example.doanandroid.Retrofit.ApiInterface;
import com.example.doanandroid.Retrofit.Retrofitclient;
import com.example.doanandroid.SanPham;
import com.example.doanandroid.TimKiemActivity;
import com.example.doanandroid.TrangChuActivity;
import com.example.doanandroid.adapter.CustomSPHotAdater;
import com.example.doanandroid.adapter.CustomSPMoiAdater;
import com.example.doanandroid.adapter.CustomSPVip;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuanJeanFragment extends Fragment {

    private View view;
    RecyclerView recyclerViewSanPhamMoi,recyclerViewSanPhamBanChay,recyclerViewSanPhamQuan;
    final ArrayList<SanPham> sanPhamMoiArrayList = new ArrayList<>();
    final ArrayList<SanPham> sanPhamBanChayArrayList = new ArrayList<>();
    final ArrayList<SanPham> sanPhamQuanArrayList = new ArrayList<>();
    CustomSPMoiAdater customSanPhamMoiAdapter;
    CustomSPHotAdater customSanPhamBanChayAdapter;
    CustomSPVip customSanPhamQuanAdapter;
    ApiInterface apInterface;
    public QuanJeanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_quan_jean, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apInterface = Retrofitclient.getClient().create(ApiInterface.class);

        recyclerViewSanPhamMoi = view.findViewById(R.id.recycleViewSanPhamMoi);
        recyclerViewSanPhamBanChay = view.findViewById(R.id.recycleViewSanPhamBanChay);
        recyclerViewSanPhamQuan = view.findViewById(R.id.recycleViewSanPhamQuanDai);

        recyclerViewSanPhamBanChay.setHasFixedSize(true);
        recyclerViewSanPhamBanChay.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));


        recyclerViewSanPhamMoi.setHasFixedSize(true);
        recyclerViewSanPhamMoi.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

        recyclerViewSanPhamQuan.setHasFixedSize(true);
        recyclerViewSanPhamQuan.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

        customSanPhamMoiAdapter = new CustomSPMoiAdater(sanPhamMoiArrayList,getContext());
        customSanPhamBanChayAdapter = new CustomSPHotAdater(sanPhamBanChayArrayList,getContext());
        customSanPhamQuanAdapter= new CustomSPVip(sanPhamQuanArrayList,getContext());

        recyclerViewSanPhamMoi.setAdapter(customSanPhamMoiAdapter);
        recyclerViewSanPhamBanChay.setAdapter(customSanPhamBanChayAdapter);
        recyclerViewSanPhamQuan.setAdapter(customSanPhamQuanAdapter);

//        sanPhamBanChayArrayList.add(new SanPham(1,1,100000,"Quần short","Quần short nam thời trang, lựa chọn hàng đầu cho những tín đồ thời trang mùa hè",
//                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSjrupOhagxSFeFnLtSSDU-ZXqiiNFO7DxTPQ&usqp=CAU"));
//        sanPhamMoiArrayList.add(new SanPham(1,1,100000,"Quần short","Quần short nam thời trang, lựa chọn hàng đầu cho những tín đồ thời trang mùa hè",
//                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSjrupOhagxSFeFnLtSSDU-ZXqiiNFO7DxTPQ&usqp=CAU"));

        customSanPhamBanChayAdapter.notifyDataSetChanged();
        customSanPhamMoiAdapter.notifyDataSetChanged();
        customSanPhamQuanAdapter.notifyDataSetChanged();
        loadSanPhamBanChay();
        loadSanPhamMoi();
        loadSapPhamQuan(1);


    }

    public void loadSanPhamMoi()
    {
        Call<List<SanPham>> sanpham = apInterface.LaySanPhamMoi();

        sanpham.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                //sanPhamBanChayArrayList.clear();
                for(int i=0;i<response.body().size();i++)
                {

                    sanPhamMoiArrayList.add(new SanPham(response.body().get(i).getMASANPHAM(),
                            response.body().get(i).getMALOAI(),response.body().get(i).getGIA(),
                            response.body().get(i).getTENSANPHAM(),response.body().get(i).getMOTASANPHAM(),
                            response.body().get(i).getHINHANH()));
                    Log.d("BBBA",response.body().get(i).getMASANPHAM()+"");
                    customSanPhamMoiAdapter.notifyDataSetChanged();
                }



                customSanPhamMoiAdapter.setOnItemClickListenner(new CustomSPMoiAdater.OnItemClickListener() {
                                                                    @Override
                                                                    public void onItemClick(int position) {
                                                                        SanPham sp ;
                                                                        Intent intent = new Intent(getActivity(), ActivityDetail.class);
                                                                        sp= sanPhamMoiArrayList.get(position);
                                                                        intent.putExtra("MASP",sp.getMASANPHAM());
                                                                        intent.putExtra("TENSP",sp.getTENSANPHAM());
                                                                        intent.putExtra("GIA",sp.getGIA());
                                                                        intent.putExtra("HINH",sp.getHINHANH());
                                                                        intent.putExtra("MALOAI",sp.getMALOAI());
                                                                        intent.putExtra("MOTA",sp.getMOTASANPHAM());
                                                                        startActivity(intent);
                                                                        Toast.makeText(getActivity(), ""+sp.getTENSANPHAM(), Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }
                );

            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                Toast.makeText(getActivity(), "Không có kết nối. Kiểm tra kết nối của bạn", Toast.LENGTH_SHORT).show();
                Log.d("SSS",t.toString());
            }
        });
    }

    public void loadSanPhamBanChay()
    {
        Call<List<SanPham>> sanpham = apInterface.LaySanPhamBanChay();

        sanpham.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                //sanPhamBanChayArrayList.clear();
                for(int i=0;i<response.body().size();i++)
                {

                    sanPhamBanChayArrayList.add(new SanPham(response.body().get(i).getMASANPHAM(),
                            response.body().get(i).getMALOAI(),response.body().get(i).getGIA(),
                            response.body().get(i).getTENSANPHAM(),response.body().get(i).getMOTASANPHAM(),
                            response.body().get(i).getHINHANH()));
                    Log.d("BBBA",response.body().get(i).getMASANPHAM()+"");
                    customSanPhamBanChayAdapter.notifyDataSetChanged();
                }



                customSanPhamBanChayAdapter.setOnItemClickListenner(new CustomSPHotAdater.OnItemClickListener() {
                                                                        @Override
                                                                        public void onItemClick(int position) {
                                                                            SanPham sp ;
                                                                            Intent intent = new Intent(getActivity(), ActivityDetail.class);
                                                                            sp= sanPhamBanChayArrayList.get(position);
                                                                            intent.putExtra("MASP",sp.getMASANPHAM());
                                                                            intent.putExtra("TENSP",sp.getTENSANPHAM());
                                                                            intent.putExtra("GIA",sp.getGIA());
                                                                            intent.putExtra("HINH",sp.getHINHANH());
                                                                            intent.putExtra("MALOAI",sp.getMALOAI());
                                                                            intent.putExtra("MOTA",sp.getMOTASANPHAM());
                                                                            startActivity(intent);
                                                                            Toast.makeText(getActivity(), ""+sp.getTENSANPHAM(), Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }
                );

            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                Toast.makeText(getActivity(), "Không có kết nối. Kiểm tra kết nối của bạn", Toast.LENGTH_SHORT).show();
                Log.d("SSS",t.toString());
            }
        });
    }


    private void loadSapPhamQuan(int MALOAI)
    {
        Call<List<SanPham>> sanpham = apInterface.LocSanPhamTheoLoai(MALOAI);

        sanpham.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                sanPhamQuanArrayList.clear();
                for(int i=0;i<response.body().size();i++)
                {
                    sanPhamQuanArrayList.add(new SanPham(response.body().get(i).getMASANPHAM(),
                            response.body().get(i).getMALOAI(),response.body().get(i).getGIA(),
                            response.body().get(i).getTENSANPHAM(),response.body().get(i).getMOTASANPHAM(),
                            response.body().get(i).getHINHANH()));
                    Log.d("BBB",response.body().get(i).getMASANPHAM()+"");
                    customSanPhamQuanAdapter.notifyDataSetChanged();
                }



                customSanPhamQuanAdapter.setOnItemClickListenner(new CustomSPVip.OnItemClickListener() {
                                                                     @Override
                                                                     public void onItemClick(int position) {
                                                                         SanPham sp ;
                                                                         Intent intent = new Intent(getContext(), ActivityDetail.class);
                                                                         sp= sanPhamQuanArrayList.get(position);
                                                                         intent.putExtra("MASP",sp.getMASANPHAM());
                                                                         intent.putExtra("TENSP",sp.getTENSANPHAM());
                                                                         intent.putExtra("GIA",sp.getGIA());
                                                                         intent.putExtra("HINH",sp.getHINHANH());
                                                                         intent.putExtra("MALOAI",sp.getMALOAI());
                                                                         intent.putExtra("MOTA",sp.getMOTASANPHAM());
                                                                         startActivity(intent);
                                                                         Toast.makeText(getContext(), ""+sp.getTENSANPHAM(), Toast.LENGTH_SHORT).show();
                                                                     }
                                                                 }
                );

            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                Toast.makeText(getContext(), "Không có kết nối. Kiểm tra kết nối của bạn", Toast.LENGTH_SHORT).show();
                Log.d("SSS",t.toString());
            }
        });
    }
}
