package com.example.sary.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sary.DAO.LoaiSachDAO;
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
        holder.SQL_MaTheLoaiSach.setText(list.get(position).getMaloai());
        holder.SQL_TenTheLoaiSach.setText(list.get(position).getTenloai());

        //Delete
        holder.txtDel_LoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
                loaiSachDAO.DeleteLoaiSach(position);
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        //Update
        holder.diaFix_LoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateLoaiSach(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       TextView SQL_MaTheLoaiSach, SQL_TenTheLoaiSach, diaFix_LoaiSach, txtDel_LoaiSach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            SQL_MaTheLoaiSach = itemView.findViewById(R.id.SQL_MaTheLoaiSach);
            SQL_TenTheLoaiSach = itemView.findViewById(R.id.SQL_TenTheLoaiSach);
            diaFix_LoaiSach = itemView.findViewById(R.id.DiaFix_LoaiSach);
            txtDel_LoaiSach = itemView.findViewById(R.id.txtDel_LoaiSach);
        }
    }

    //Hàm update LoaiSach
    public void updateLoaiSach(int i){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view1 = inflater.inflate(R.layout.ui_dialog_sua_loai_sach, null);
        builder.setView(view1);

        EditText edtMaloai = view1.findViewById(R.id.EdtFix_MaLoaiSach);
        EditText edtTenloai = view1.findViewById(R.id.EdtFix_TenLoaiSach);
        Button btnUpdate = view1.findViewById(R.id.BtnFix_LoaiSach);

        edtMaloai.setText(list.get(i).getMaloai());
        edtTenloai.setText(list.get(i).getTenloai());
        edtMaloai.setEnabled(false);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
                loaiSachDAO.UpdateLoaiSach(list.get(i).getMaloai(), list.get(i).getTenloai());
                list.set(i, new LoaiSach(edtMaloai.getText().toString(), edtTenloai.getText().toString()));
                notifyDataSetChanged();
                alertDialog.dismiss();
            }
        });
    }
}
