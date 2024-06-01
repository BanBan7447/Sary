package com.example.sary.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sary.Model.LoaiSach;
import com.example.sary.R;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder>{
    private Context context;
    private ArrayList<LoaiSach> list;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.ui_recy_quan_ly_loai_sach, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.SQL_MaTheLoaiSach.setText("Mã: " + list.get(position).getMaloai());
        holder.SQL_TenTheLoaiSach.setText("Thể Loại: " + list.get(position).getTenloai());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       TextView SQL_MaTheLoaiSach, SQL_TenTheLoaiSach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            SQL_MaTheLoaiSach = itemView.findViewById(R.id.SQL_MaTheLoaiSach);
            SQL_TenTheLoaiSach = itemView.findViewById(R.id.SQL_TenTheLoaiSach);
        }
    }
}
