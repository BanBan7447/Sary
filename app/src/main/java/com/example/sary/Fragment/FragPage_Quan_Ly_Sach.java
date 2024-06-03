package com.example.sary.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sary.Adapter.SachAdapter;
import com.example.sary.DAO.SachDAO;
import com.example.sary.Model.Sach;
import com.example.sary.R;

import java.util.ArrayList;

public class FragPage_Quan_Ly_Sach extends Fragment { // Chuyển trang Activity sang Fragment
    private RecyclerView rcvQuan_Ly_Sach;
    private SachDAO sachDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewSach = inflater.inflate(R.layout.ui_frag_page_quan_ly_sach, container, false);

        rcvQuan_Ly_Sach = viewSach.findViewById(R.id.rcvQuan_Ly_Sach);
        sachDAO = new SachDAO(getContext());
        loadData();
        return viewSach;

    }

    private void loadData() {
        ArrayList<Sach> list = sachDAO.getDSSach();
        list.add(new Sach("SAKD01","Nghệ thuật lấy lòng khách hàng","Michael J.Maher","Thế Giới","Kinh doanh", 10000));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvQuan_Ly_Sach.setLayoutManager(linearLayoutManager);

        SachAdapter adapter = new SachAdapter(getContext(), list);
        rcvQuan_Ly_Sach.setAdapter(adapter);
    }
}