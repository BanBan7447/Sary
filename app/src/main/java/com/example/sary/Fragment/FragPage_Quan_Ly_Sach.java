package com.example.sary.Fragment;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
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
    private SearchView searchView;
    private Toolbar toolbar;
    private SachAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewSach = inflater.inflate(R.layout.ui_frag_page_quan_ly_sach, container, false);

        rcvQuan_Ly_Sach = viewSach.findViewById(R.id.rcvQuan_Ly_Sach);
        FloatingActionButton TxtMore_Sach = viewSach.findViewById(R.id.TxtMore_Sach);

        toolbar = viewSach.findViewById(R.id.Sach_ToolBar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("");

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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvQuan_Ly_Sach.setLayoutManager(linearLayoutManager);

        adapter = new SachAdapter(getContext(), list);
        rcvQuan_Ly_Sach.setAdapter(adapter);
    }

    //Add Sach dialog
    private void Add_Sach() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //builder.setCancelable(false);
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
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.radius_dialog));


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
                } else if (edtTensach.length() == 0 || edtTacgia.length() == 0 || edtNXB.length() == 0 || edtTheloai.length() == 0 || edtGiathue.length() == 0) {
                    Toast.makeText(getContext(), "Chưa nhập thông tin của sách", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "Mã sách đã tồn tại", Toast.LENGTH_SHORT).show();
            }
        });
    }

//Search
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setIconified(true);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               adapter.getFilter().filter(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
}