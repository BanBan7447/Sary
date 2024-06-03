package com.example.sary.Fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sary.Adapter.LoaiSachAdapter;
import com.example.sary.DAO.LoaiSachDAO;
import com.example.sary.Model.LoaiSach;
import com.example.sary.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FragPage_Quan_Ly_Loai_Sach extends Fragment { // Chuyển trang Activity sang Fragment
    private LoaiSachDAO loaiSachDAO;
    private RecyclerView rcvQuan_Ly_Loai_Sach;
    private ArrayList<LoaiSach> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewLoaiSach = inflater.inflate(R.layout.ui_frag_page_quan_ly_loai_sach, container, false);

        rcvQuan_Ly_Loai_Sach = viewLoaiSach.findViewById(R.id.rcvQuan_Ly_Loai_Sach);
        FloatingActionButton TxtMore_LoaiSach = viewLoaiSach.findViewById(R.id.TxtMore_LoaiSach);

        loaiSachDAO = new LoaiSachDAO(getContext());
        loadData();

        //add LoaiSach
        TxtMore_LoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_LoaiSach();
            }
        });
        return viewLoaiSach;
    }

    private void loadData() {
        list = loaiSachDAO.getDSLoaiSach();

        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        rcvQuan_Ly_Loai_Sach.setLayoutManager(manager);

        LoaiSachAdapter adapter = new LoaiSachAdapter(getContext(), list);
        rcvQuan_Ly_Loai_Sach.setAdapter(adapter);
    }

    //add LoaiSach dialog
    private void Add_LoaiSach() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.ui_dialog_them_loai_sach, null);
        builder.setView(view);

        EditText editMaloai = view.findViewById(R.id.EdtMore_MaLoaiSach);
        EditText editTenLoai = view.findViewById(R.id.EdtMore_TenLoaiSach);

        Button btnAdd = view.findViewById(R.id.BtnMore_LoaiSach);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ok = true;
                for (LoaiSach ls : list) {
                    if (ls.getMaloai().equals(editMaloai.getText().toString())) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    loaiSachDAO.AddLoaiSach(editMaloai.getText().toString(), editTenLoai.getText().toString());
                    loadData();
                    alertDialog.dismiss();
                } else
                    Toast.makeText(getContext(), "Mã loại sách đã tồn tại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}