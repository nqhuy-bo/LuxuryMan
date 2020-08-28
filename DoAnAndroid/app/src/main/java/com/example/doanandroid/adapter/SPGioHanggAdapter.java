package com.example.doanandroid.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid.CustomAdaptor;
import com.example.doanandroid.CustomSanPhamAdapter;
import com.example.doanandroid.R;
import com.example.doanandroid.SanPham;
import com.example.doanandroid.WellCome;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SPGioHanggAdapter extends RecyclerView.Adapter<SPGioHanggAdapter.MyViewHolder1> {

    private OnItemClickListener mListenergiohang;
    private Context context;
    private int layout;
    // private List<SanPham> sanPhamList;
    Bitmap bitmap;
  //  private CustomSanPhamAdapter.OnItemClickListener mListener;
    ArrayList<SanPham> arrayList;


    public interface OnItemClickListener{
        void onItemClick(int position);
        void deleteClick(int position);
    }
    public  void setOnItemClickListenner(OnItemClickListener listenner){
        mListenergiohang =  listenner;
    }
    public SPGioHanggAdapter(ArrayList<SanPham> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context  =context;
    }

    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview  = layoutInflater.inflate(R.layout.custom_dong_sanpham,parent,false);
        return new SPGioHanggAdapter.MyViewHolder1(itemview,mListenergiohang);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder1 holder, int position) {

        holder.txtTenSP.setText(arrayList.get(position).getTENSANPHAM());
        holder.txtGia.setText(arrayList.get(position).getGIA()+" VNĐ");
        holder.txtSoLuongSP.setText("Số lượng :" +arrayList.get(position).getSOLUONG());
        Log.d("AAA",arrayList.get(position).getGIA()+" VNĐ");
        SanPham sanPham =arrayList.get(position);
        Picasso.with(context)
                .load(sanPham.getHINHANH())
                .placeholder(R.drawable.ic_wait)
                .error(R.drawable.ic_offline)
                .into(holder.imgHinhSP);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder1 extends RecyclerView.ViewHolder {
        TextView txtTenSP,txtGia,txtSoLuongSP;
        Button btnXoa;
        ImageView imgHinhSP;
        ImageView deletesp;
        public MyViewHolder1(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            txtTenSP= itemView.findViewById(R.id.textViewTenSPGioHang);
            txtGia =itemView.findViewById(R.id.textViewGiaGioHang);
            imgHinhSP = itemView.findViewById(R.id.imageViewItemGioHang);

            txtSoLuongSP = itemView.findViewById(R.id.textViewSoLuongGioHang);
            deletesp = itemView.findViewById(R.id.ImageXoaSPGioHang);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                    {
                        listener.onItemClick(position);
                    }
                }
            });

            deletesp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                    {
                        listener.deleteClick(position);

                    }
                }
            });


        }
    }
}
