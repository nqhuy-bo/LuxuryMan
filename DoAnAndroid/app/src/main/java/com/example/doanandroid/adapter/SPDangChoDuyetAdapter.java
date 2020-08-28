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

import com.example.doanandroid.R;
import com.example.doanandroid.SanPhamDangCho;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SPDangChoDuyetAdapter extends RecyclerView.Adapter<SPDangChoDuyetAdapter.MyViewHolder1>{
    private SPGioHanggAdapter.OnItemClickListener mListenergiohang;
    private Context context;
    private int layout;
    // private List<SanPham> sanPhamList;
    Bitmap bitmap;
    //  private CustomSanPhamAdapter.OnItemClickListener mListener;
    ArrayList<SanPhamDangCho> arrayList;


    public interface OnItemClickListener{
        void onItemClick(int position);
        void deleteClick(int position);
    }
    public  void setOnItemClickListenner(SPGioHanggAdapter.OnItemClickListener listenner){
        mListenergiohang =  listenner;
    }
    public SPDangChoDuyetAdapter(ArrayList<SanPhamDangCho> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context  =context;
    }

    @NonNull
    @Override
    public SPDangChoDuyetAdapter.MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview  = layoutInflater.inflate(R.layout.custom_dongsanphamdangcho,parent,false);
        return new SPDangChoDuyetAdapter.MyViewHolder1(itemview,mListenergiohang);

    }

    @Override
    public void onBindViewHolder(@NonNull SPDangChoDuyetAdapter.MyViewHolder1 holder, int position) {

        holder.txtTenSP.setText(arrayList.get(position).getTENSANPHAM());
        holder.txtGia.setText(arrayList.get(position).getGIA()+" VNĐ");
        holder.txtSoLuongSP.setText("Số lượng :" +arrayList.get(position).getSOLUONG());
        Log.d("AAA",arrayList.get(position).getGIA()+" VNĐ");
        SanPhamDangCho sanPham =arrayList.get(position);
        Picasso.with(context)
                .load(sanPham.getHINHANH())
                .placeholder(R.drawable.loaddddddd)
                .error(R.drawable.ic_offline)
                .into(holder.imgHinhSP);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder1 extends RecyclerView.ViewHolder {
        TextView txtTenSP,txtGia,txtSoLuongSP,txtDangCho;
        Button btnXoa;
        ImageView imgHinhSP;
        ImageView deletesp;
        public MyViewHolder1(@NonNull View itemView, final SPGioHanggAdapter.OnItemClickListener listener) {
            super(itemView);
            txtTenSP= itemView.findViewById(R.id.textViewTenSPGioHangDangCho);
            txtGia =itemView.findViewById(R.id.textViewGiaGioHangDangCho);
            imgHinhSP = itemView.findViewById(R.id.imageViewItemGioHangDangCho);
            txtDangCho = itemView.findViewById(R.id.textViewDangCho);
            txtSoLuongSP = itemView.findViewById(R.id.textViewSoLuongGioHangDangCho);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    if(position != RecyclerView.NO_POSITION)
//                    {
//                        listener.onItemClick(position);
//                    }
//                }
//            });




        }
    }
}
