package com.example.doanandroid;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.MyViewHolder> {
    private OnItemClickListener mListener;
    private Context context;
    private List<App> apps;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public  void setOnItemClickListenner(OnItemClickListener listenner){
        mListener = listenner;
    }
    public CustomAdaptor(Context context, List<App> apps) {
        this.context = context;
        this.apps = apps;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mName;
        ImageView mImage;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            mName = itemView.findViewById(R.id.name);
//            mSize = itemView.findViewById(R.id.size);
            mImage = itemView.findViewById(R.id.image);

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
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_list_item,parent,false);

        return new MyViewHolder(v,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        App app = apps.get(position);

        holder.mName.setText(app.getName());
//        holder.mSize.setText(app.getSize()+" MB");
        holder.mImage.setImageResource(app.getImage());


    }

    @Override
    public int getItemCount() {
        return apps.size();
    }


}
