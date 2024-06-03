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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sary.Adapter.SachAdapter;
import com.example.sary.DAO.SachDAO;
import com.example.sary.Model.Sach;
import com.example.sary.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragPage_Quan_Ly_Sach extends Fragment { // Chuyển trang Activity sang Fragment
    private RecyclerView rcvQuan_Ly_Sach;
    private SachDAO sachDAO;
    ArrayList<Sach> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewSach = inflater.inflate(R.layout.ui_frag_page_quan_ly_sach, container, false);

        rcvQuan_Ly_Sach = viewSach.findViewById(R.id.rcvQuan_Ly_Sach);
        FloatingActionButton TxtMore_Sach = viewSach.findViewById(R.id.TxtMore_Sach);

        sachDAO = new SachDAO(getContext());
        loadData();

        //Add Sach
        TxtMore_Sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_Sach();
            }
        });
        return viewSach;

    }

    private void loadData() {
        list = sachDAO.getDSSach();
        list.add(new Sach("SAKD01", "Nghệ thuật lấy lòng khách hàng", "Michael J.Maher", "Thế Giới", "Kinh doanh", 10000));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvQuan_Ly_Sach.setLayoutManager(linearLayoutManager);

        SachAdapter adapter = new SachAdapter(getContext(), list);
        rcvQuan_Ly_Sach.setAdapter(adapter);
    }

    //add Sach dialog
    private void Add_Sach() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.ui_dialog_them_sach, null);
        builder.setView(view);

        EditText edtMasach = view.findViewById(R.id.EdtMore_MaSach);
        EditText edtTensach = view.findViewById(R.id.EdtMore_TenSach);
        EditText edtTacgia = view.findViewById(R.id.EdtMore_TacGiaSach);
        EditText edtNXB = view.findViewById(R.id.EdtMore_NhaXuatBanSach);
        EditText edtTheloai = view.findViewById(R.id.EdtMore_TheLoaiSach);
        EditText edtGiathue = view.findViewById(R.id.EdtMore_GiaThueSach);
        Button btnAdd = view.findViewById(R.id.BtnMore_Sach);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ok = true;
                for (Sach sach : list) {
                    if (sach.getMaSach().equals(edtMasach.getText().toString())) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    sachDAO.AddSach(edtMasach.getText().toString(), edtTensach.getText().toString(), edtTacgia.getText().toString(), edtNXB.getText().toString(), edtTheloai.getText().toString(), Integer.parseInt(edtGiathue.getText().toString()));
                    loadData();
                    alertDialog.dismiss();
                } else
                    Toast.makeText(getContext(), "Mã sách đã tồn tại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}