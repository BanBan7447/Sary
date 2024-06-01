package com.example.sary.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){
        super(context, "QUANLYTHUVIEN", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // tạo bảng loại sách
        String tLoaiSach = "CREATE TABLE LOAISACH(maloai text primary key autoincrement, tenloai text)";
        db.execSQL(tLoaiSach);
        db.execSQL("INSERT INTO LOAISACH VALUES('Business','Kinh Doanh'),('History','Lịch Sử'),('Novel','Tiểu Thuyết'),('Literatule','Văn Học'),('Science','Khoa Học'),('Language','Ngoại Ngữ')");
//
//        String tSach = "CREATE TABLE SACH(maSach integer primary key autoincrement, tensach text, tacgia text, nhaxuatban text, giathue integer, maloai integer references LOAISACH(maloai), maPhieuMuon integer references PHIEUMUON(maPhieuMuon))";
//        db.execSQL(tSach);
//
//        //role:
//        // 1 - Thành Viên
//        // 2 - Thủ thư
//        // 3 - Admin
//        String tThanhVien = "CREATE TABLE THANHVIEN(maTV integer primary key autoincrement, hotenThanhVien text, namsinhThanhVien date, diachiThanhVien text, quequanThanhVien text, emailThanhVien text, sdtThanhVien text, PasswordThanhVien text)";
//        db.execSQL(tThanhVien);
//
//        String tPhieuMuon = "CREATE TABLE PHIEUMUON(maPhieuMuon integer primary key autoincrement, ngayThue text, thoigianTra text, tienThue integer, maThanhVien integer references THANHVIEN(maTV), maThuThu integer references THUTHU(maThuThu))";
//        db.execSQL(tPhieuMuon);

        String tThuThu = "CREATE TABLE THUTHU(maThuThu integer primary key autoincrement, hotenThuThu text, emailThuThu text, sdtThuThu text, passwordThuThu text)";
        db.execSQL(tThuThu);
        db.execSQL("INSERT INTO THUTHU VALUES(1,'Nguyễn Văn A','nguyenvana@gmail.com','0123456789','1225')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS THUTHU");
            onCreate(db);
        }
    }
}
