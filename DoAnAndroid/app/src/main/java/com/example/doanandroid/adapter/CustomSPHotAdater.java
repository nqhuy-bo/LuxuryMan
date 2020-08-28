package com.example.doanandroid.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanandroid.R;
import com.example.doanandroid.SanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomSPHotAdater extends  RecyclerView.Adapter<CustomSPHotAdater.ViewHolder>{

    private CustomSPHotAdater.OnItemClickListener mListener;
    ArrayList<SanPham> arrayList;
    Context context;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public  void setOnItemClickListenner(CustomSPHotAdater.OnItemClickListener listenner){
        mListener = listenner;
    }
    public CustomSPHotAdater(ArrayList<SanPham> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context  =context;
    }

    @NonNull
    @Override
    public CustomSPHotAdater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview  = layoutInflater.inflate(R.layout.custom_sp_hot,parent,false);
        return new CustomSPHotAdater.ViewHolder(itemview,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomSPHotAdater.ViewHolder holder, int position) {

        holder.txtTenSP.setText(arrayList.get(position).getTENSANPHAM());
        holder.txtGia.setText(arrayList.get(position).getGIA()+" VNĐ");
        Log.d("AAA",arrayList.get(position).getGIA()+" VNĐ");
        SanPham sanPham =arrayList.get(position);
        Picasso.with(context)
                .load(sanPham.getHINHANH())
                .placeholder(R.drawable.loaddddddd)
                .error(R.drawable.ic_offline)
                .into(holder.imgHinh);



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtTenSP,txtGia;
        ImageView imgHinh;


        public  ViewHolder(@NonNull View itemView,final CustomSPHotAdater.OnItemClickListener listenner) {
            super(itemView);
            txtTenSP= itemView.findViewById(R.id.txtSanPham_sphot);
            txtGia= itemView.findViewById(R.id.txtGia_sphot);
            imgHinh= itemView.findViewById(R.id.imgHinhAnh_sphot);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listenner != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            listenner.onItemClick(position);
                        }
                    }
                }
            });
        }
    }



}




