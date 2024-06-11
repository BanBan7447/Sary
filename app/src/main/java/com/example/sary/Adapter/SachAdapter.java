package com.example.sary.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sary.DAO.SachDAO;
import com.example.sary.Model.Sach;
import com.example.sary.R;

import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder> implements Filterable {
    private Context context;
    private ArrayList<Sach> list;
    private ArrayList<Sach> listold;

    public SachAdapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
        this.listold = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.ui_recy_quan_ly_sach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.SQL_MaSach.setText(list.get(position).getMaSach());
        holder.SQL_TenSach.setText(list.get(position).getTensach());
        holder.SQL_TacGia.setText(list.get(position).getTacgia());
        holder.SQL_NhaXuatBan.setText(list.get(position).getNhaxuatban());
        holder.SQL_TheLoai.setText(list.get(position).getTheloai());
        holder.SQL_GiaThue.setText(String.valueOf(list.get(position).getGiathue()));

        //add img
        if (list.get(position).getMaSach().equals("SAKD01"))
            holder.SQL_Pic_Book.setImageResource(R.mipmap.book_1);
        if (list.get(position).getMaSach().equals("SAKD02"))
            holder.SQL_Pic_Book.setImageResource(R.mipmap.book_2);
        if (list.get(position).getMaSach().equals("SAKD03"))
            holder.SQL_Pic_Book.setImageResource(R.mipmap.book_3);
        if (list.get(position).getMaSach().equals("SAKD04"))
            holder.SQL_Pic_Book.setImageResource(R.mipmap.book_4);
        if (list.get(position).getMaSach().equals("SAKD05"))
            holder.SQL_Pic_Book.setImageResource(R.mipmap.book_5);

        //Delete
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SachDAO sachDAO = new SachDAO(context);
                sachDAO.DeleteSach(list.get(position).getMaSach());
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        //Update
        holder.DiaFix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSach(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView SQL_MaSach, SQL_TenSach, SQL_TacGia, SQL_NhaXuatBan, SQL_TheLoai, SQL_GiaThue;
        Button DiaFix, btnDelete;
        ImageView SQL_Pic_Book;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            SQL_MaSach = itemView.findViewById(R.id.SQL_MaSach);
            SQL_TenSach = itemView.findViewById(R.id.SQL_TenSach);
            SQL_TacGia = itemView.findViewById(R.id.SQL_TacGia);
            SQL_NhaXuatBan = itemView.findViewById(R.id.SQL_NhaXuatBan);
            SQL_TheLoai = itemView.findViewById(R.id.SQL_TheLoai);
            SQL_GiaThue = itemView.findViewById(R.id.SQL_GiaThue);
            DiaFix = itemView.findViewById(R.id.DiaFix_Sach);
            btnDelete = itemView.findViewById(R.id.BtnDel_Sach);
            SQL_Pic_Book = itemView.findViewById(R.id.SQL_Pic_Book);
        }
    }

    //Hàm update Sach
    public void updateSach(int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view1 = inflater.inflate(R.layout.ui_dialog_sua_sach, null);
        builder.setView(view1);

        EditText edtMasach = view1.findViewById(R.id.EdtFix_MaSach);
        EditText edtTensach = view1.findViewById(R.id.EdtFix_TenSach);
        EditText edtTacgia = view1.findViewById(R.id.EdtFix_TacGiaSach);
        EditText edtNXB = view1.findViewById(R.id.EdtFix_NhaXuatBanSach);
        EditText edtTheloai = view1.findViewById(R.id.EdtFix_TheLoaiSach);
        EditText edtGiathue = view1.findViewById(R.id.EdtFix_GiaThueSach);
        Button btnUpdate = view1.findViewById(R.id.BtnFix_Sach);

        edtMasach.setText(list.get(i).getMaSach());
        edtTensach.setText(list.get(i).getTensach());
        edtTacgia.setText(list.get(i).getTacgia());
        edtNXB.setText(list.get(i).getNhaxuatban());
        edtTheloai.setText(list.get(i).getTheloai());
        edtGiathue.setText(String.valueOf(list.get(i).getGiathue()));
        edtMasach.setEnabled(false);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SachDAO sachDAO = new SachDAO(context);
                sachDAO.UpdateSach(list.get(i).getMaSach(), list.get(i).getTensach(), list.get(i).getTacgia(), list.get(i).getNhaxuatban(), list.get(i).getTheloai(), list.get(i).getGiathue());
                list.set(i, new Sach(edtMasach.getText().toString(), edtTensach.getText().toString(), edtTacgia.getText().toString(), edtNXB.getText().toString(), edtTheloai.getText().toString(), Integer.parseInt(edtGiathue.getText().toString())));
                notifyDataSetChanged();
                alertDialog.dismiss();
            }
        });
    }

    //Filter
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String stsea = constraint.toString();
                if (stsea.isEmpty()) {
                    list = listold;
                } else {
                    ArrayList<Sach> listclone = new ArrayList<>();
                    for (Sach sach : listold) {
                        if (sach.getTensach().toLowerCase().contains(stsea.toLowerCase())) {
                            listclone.add(sach);
                        }
                    }
                    list = listclone;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<Sach>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
