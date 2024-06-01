package com.example.sary.Model;

import android.database.sqlite.SQLiteDatabase;

import com.example.sary.Database.DbHelper;

import java.util.ArrayList;

public class LoaiSach {
    private String maloai;
    private String tenloai;

    public LoaiSach(String maloai, String tenloai) {
        this.maloai = maloai;
        this.tenloai = tenloai;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

}
