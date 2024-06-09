package com.example.sary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sary.DAO.NguoiDungDAO;
import com.example.sary.Fragment.FragPage_Quan_Ly_Loai_Sach;
import com.example.sary.Fragment.FragPage_Quan_Ly_Phieu_Muon;
import com.example.sary.Fragment.FragPage_Quan_Ly_Sach;
import com.example.sary.Fragment.FragPage_Quan_Ly_Thanh_Vien;
import com.example.sary.Fragment.FragPage_ThongKeDoanhThu;
import com.example.sary.Fragment.FragPage_Top_Sach;
import com.google.android.material.navigation.NavigationView;

public class Page_Home extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_page_home);


        drawerLayout = findViewById(R.id.DrawerLayout);

        Toolbar toolbar = findViewById(R.id.Drawer_ToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_menu);

        navigationView = findViewById(R.id.Drawer_Navi);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.Drawer_QuanLyPhieuMuon){
                    fragment = new FragPage_Quan_Ly_Phieu_Muon();
                } else if (item.getItemId() == R.id.Drawer_QuanLyLoaiSach) {
                    fragment = new FragPage_Quan_Ly_Loai_Sach();
                } else if (item.getItemId() == R.id.Drawer_QuanLySach) {
                    fragment = new FragPage_Quan_Ly_Sach();
                } else if (item.getItemId() == R.id.Drawer_QuanLyThanhVien) {
                    fragment = new FragPage_Quan_Ly_Thanh_Vien();
                } else if (item.getItemId() == R.id.Drawer_ThongKeDoanhThu) {
                    fragment = new FragPage_ThongKeDoanhThu();
                } else if (item.getItemId() == R.id.Drawer_Top_Sach) {
                    fragment = new FragPage_Top_Sach();
                } else if (item.getItemId() == R.id.Drawer_ChangePass) {
                    ShowDialogDoiMatKhau();
                }else if (item.getItemId() == R.id.Drawer_Thoat){
                    finishAffinity();
                    return true;
                }

                if (fragment != null){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.Page_Replace, fragment).commit();
                    toolbar.setTitle(item.getTitle());
                }
                drawerLayout.closeDrawer(GravityCompat.START);

                return false;
            }
        });

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.Page_Replace, new FragPage_Quan_Ly_Phieu_Muon())
                    .commit();
            toolbar.setTitle(("Quản lý phiếu mượn"));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void ShowDialogDoiMatKhau(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View viewChangePass = inflater.inflate(R.layout.ui_dialog_doi_mat_khau, null);
        builder.setView(viewChangePass);

        EditText edtOldPass = viewChangePass.findViewById(R.id.ChangePass_Old);
        EditText edtNewPass = viewChangePass.findViewById(R.id.ChangePass_New);
        EditText edtAgainPass = viewChangePass.findViewById(R.id.ChangePass_Again);

        builder.setView(viewChangePass);

//        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });

//        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String oldPass = edtOldPass.getText().toString();
//                String newPass = edtNewPass.getText().toString();
//                String againPass = edtAgainPass.getText().toString();
//
//                if (newPass.equals(againPass)){
//                    SharedPreferences sharedPreferences = getSharedPreferences("ThongTin", MODE_PRIVATE);
//                    String MaThuThu = sharedPreferences.getString("maThuThu", "");
//                    // cập nhật
//                    NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(Page_Home.this);
//                    boolean check = nguoiDungDAO.CapNhatMatKhau(MaThuThu, oldPass, newPass);
//
//                    if (check){
//                        Toast.makeText(Page_Home.this, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(Page_Home.this, Page_Sign_In.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
//
//                    }else {
//                        Toast.makeText(Page_Home.this, "Cập nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();
//                    }
//
//                }else {
//                    Toast.makeText(Page_Home.this, "Nhập mật khẩu không trùng", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


        Button btnChangPass = viewChangePass.findViewById(R.id.btn_ChangePass);
        btnChangPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = edtOldPass.getText().toString();
                String newPass = edtNewPass.getText().toString();
                String againPass = edtAgainPass.getText().toString();

                if (newPass.equals(againPass)){
                    SharedPreferences sharedPreferences = getSharedPreferences("ThongTin", MODE_PRIVATE);
                    String MaThuThu = sharedPreferences.getString("maThuThu", "");
                    // cập nhật
                    NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(Page_Home.this);
                    boolean check = nguoiDungDAO.CapNhatMatKhau(MaThuThu, oldPass, newPass);

                    if (check){
                        Toast.makeText(Page_Home.this, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Page_Home.this, Page_Sign_In.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    }else {
                        Toast.makeText(Page_Home.this, "Cập nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(Page_Home.this, "Nhập mật khẩu không trùng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.radius_dialog));

    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.drawer_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
}