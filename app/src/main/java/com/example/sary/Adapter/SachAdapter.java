package com.example.sary.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sary.Model.Sach;
import com.example.sary.R;

import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Sach> list;

    public SachAdapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.ui_recy_quan_ly_sach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.SQL_MaSach.setText("Mã sách: " + list.get(position).getMaSach());
        holder.SQL_TenSach.setText("Tên sách: " + list.get(position).getTensach());
        holder.SQL_TacGia.setText("Tác giả: " + list.get(position).getTacgia());
        holder.SQL_NhaXuatBan.setText("Nhà xuất bản: " + list.get(position).getNhaxuatban());
        holder.SQL_TheLoai.setText("Thể loại: " + list.get(position).getTheloai());
        holder.SQL_GiaThue.setText("Giá thuê: " + list.get(position).getGiathue());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView SQL_MaSach, SQL_TenSach, SQL_TacGia, SQL_NhaXuatBan, SQL_TheLoai, SQL_GiaThue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            SQL_MaSach = itemView.findViewById(R.id.SQL_MaSach);
            SQL_TenSach = itemView.findViewById(R.id.SQL_TenSach);
            SQL_TacGia = itemView.findViewById(R.id.SQL_TacGia);
            SQL_NhaXuatBan = itemView.findViewById(R.id.SQL_NhaXuatBan);
            SQL_TheLoai = itemView.findViewById(R.id.SQL_TheLoai);
            SQL_GiaThue = itemView.findViewById(R.id.SQL_GiaThue);
        }
    }
}
