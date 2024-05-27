package com.example.sary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.sary.Fragment.FragPage_Quan_Ly_Loai_Sach;
import com.example.sary.Fragment.FragPage_Quan_Ly_Phieu_Muon;
import com.example.sary.Fragment.FragPage_Quan_Ly_Sach;
import com.example.sary.Fragment.FragPage_Quan_Ly_Thanh_Vien;
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
                }

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.Page_Replace, fragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.drawer_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
}