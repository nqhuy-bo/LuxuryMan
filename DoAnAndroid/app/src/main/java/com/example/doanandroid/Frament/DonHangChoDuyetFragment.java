package com.example.doanandroid.Frament;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid.R;
import com.example.doanandroid.Retrofit.ApiInterface;
import com.example.doanandroid.Retrofit.Retrofitclient;
import com.example.doanandroid.SanPhamDangCho;
import com.example.doanandroid.adapter.SPDangChoDuyetAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DonHangChoDuyetFragment extends Fragment {


    RecyclerView rcyDonHangChoDuyet;
    ArrayList<SanPhamDangCho> lstSanPhamCho ;
    SPDangChoDuyetAdapter adapter;
    ApiInterface apiInterface;
    ImageView imgQuayLaiSPChoDuyet;

    SharedPreferences sharedPref;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_donhangchoduyet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AnhXa();
        apiInterface = Retrofitclient.getClient().create(ApiInterface.class);
        lstSanPhamCho = new ArrayList<>();
        layDSDangCho();

        rcyDonHangChoDuyet.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        rcyDonHangChoDuyet.setLayoutManager(layoutManager);

        adapter = new SPDangChoDuyetAdapter(lstSanPhamCho,getContext());
        adapter.notifyDataSetChanged();
        rcyDonHangChoDuyet.setAdapter(adapter);

        imgQuayLaiSPChoDuyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayoutContent, new TaiKhoanFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }



    private void AnhXa() {
        imgQuayLaiSPChoDuyet = getView().findViewById(R.id.imageViewQuayLaiSPChoDuyet);
        rcyDonHangChoDuyet = getView().findViewById(R.id.recycleViewSanPhamChoDuyet);
    }

    public ArrayList<SanPhamDangCho> layDSDangCho()
    {
        sharedPref = getContext().getSharedPreferences("statusLogin", Context.MODE_PRIVATE);
        String sdt = sharedPref.getString("user","Chưa có");
        final ArrayList<SanPhamDangCho> lst = new ArrayList<>();
        Call<List<SanPhamDangCho>> sanpham = apiInterface.layDanhSachChuaDuyet(sdt);
        sanpham.enqueue(new Callback<List<SanPhamDangCho>>() {
            @Override
            public void onResponse(Call<List<SanPhamDangCho>> call, Response<List<SanPhamDangCho>> response) {
                for(int i = 0;i<response.body().size();i++)
                {
                    lstSanPhamCho.add(new SanPhamDangCho(response.body().get(i).getTENSANPHAM(),
                            response.body().get(i).getSOLUONG(),
                            response.body().get(i).getGIA(),
                            response.body().get(i).getHINHANH()));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<SanPhamDangCho>> call, Throwable t) {
                Log.d("UUU",t.toString());
            }
        });
        return lstSanPhamCho;
    }
}