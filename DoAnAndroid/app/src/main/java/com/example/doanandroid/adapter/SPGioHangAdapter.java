package com.example.doanandroid.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.doanandroid.Frament.ActivityDetail;
import com.example.doanandroid.Frament.GioHangFrament;
import com.example.doanandroid.R;
import com.example.doanandroid.SanPham;
import com.example.doanandroid.WellCome;
import com.squareup.picasso.Picasso;


import java.util.List;

public class SPGioHangAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<SanPham> sanPhamList;
    Bitmap bitmap;

    public SPGioHangAdapter(Context context, int layout, List<SanPham> sanPhamList) {
        this.context = context;
        this.layout = layout;
        this.sanPhamList = sanPhamList;
    }

    public SPGioHangAdapter() {
    }

    @Override
    public int getCount() {
        return sanPhamList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public static class ViewHolder{
        TextView txtTenSP,txtGia,txtSoLuongSP;
        Button btnXoa;
        ImageView imgHinhSP;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            holder  = new ViewHolder();
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.txtTenSP =convertView.findViewById(R.id.textViewTenSPGioHang);
            holder.txtGia = convertView.findViewById(R.id.textViewGiaGioHang);
            holder.imgHinhSP = convertView.findViewById(R.id.imageViewItemGioHang);

            holder.txtSoLuongSP = convertView.findViewById(R.id.textViewSoLuongGioHang);

            convertView.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) convertView.getTag();
        }

        final SanPham sanPham =sanPhamList.get(position);
        holder.txtTenSP.setText(sanPham.getTENSANPHAM());
        holder.txtGia.setText(sanPham.getGIA()+"VNĐ");
        holder.txtSoLuongSP.setText("Số lượng :" +sanPham.getSOLUONG());
        Picasso.with(context)
                .load(sanPham.getHINHANH())
                .placeholder(R.drawable.ic_wait)
                .error(R.drawable.ic_offline)
                .into(holder.imgHinhSP);
        //new GetImageFromUrl(holder.imgHinhSP).execute(sanPham.getHINHANH());
        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Thông báo !!!");
                dialog.setMessage("Bạn muốn xóa sản phẩm "+sanPham.getTENSANPHAM()+" khỏi giỏ hàng ???");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WellCome.data.QueryData("DELETE FROM GIOHANG WHERE MASANPHAM = '"+ sanPham.getMASANPHAM()+"'");

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
        return convertView;
    }
}
