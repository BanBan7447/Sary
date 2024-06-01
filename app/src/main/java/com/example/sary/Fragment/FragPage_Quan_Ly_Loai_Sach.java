package com.example.sary.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sary.Adapter.LoaiSachAdapter;
import com.example.sary.DAO.LoaiSachDAO;
import com.example.sary.Model.LoaiSach;
import com.example.sary.R;

import java.util.ArrayList;


public class FragPage_Quan_Ly_Loai_Sach extends Fragment { // Chuyển trang Activity sang Fragment
    private LoaiSachDAO loaiSachDAO;
    private RecyclerView rcvQuan_Ly_Loai_Sach;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewLoaiSach = inflater.inflate(R.layout.ui_frag_page_quan_ly_loai_sach, container, false);

        rcvQuan_Ly_Loai_Sach = viewLoaiSach.findViewById(R.id.rcvQuan_Ly_Loai_Sach);
        loaiSachDAO = new LoaiSachDAO(getContext());

    return viewLoaiSach;
    }
    private void loadData(){
        ArrayList<LoaiSach> list = loaiSachDAO.getDSLoaiSach();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvQuan_Ly_Loai_Sach.setLayoutManager(linearLayoutManager);

        LoaiSachAdapter adapter = new LoaiSachAdapter(getContext(), list);
        rcvQuan_Ly_Loai_Sach.setAdapter(adapter);
    }

}