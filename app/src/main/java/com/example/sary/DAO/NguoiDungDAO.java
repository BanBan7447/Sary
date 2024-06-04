package com.example.sary.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sary.Database.DbHelper;

public class NguoiDungDAO {
    private DbHelper dbHelper;
    public NguoiDungDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    // kiểm tra thông tin đăng nhập
    public boolean KiemTraDangNhap(String username, String password){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE maThuThu = ? AND passwordThuThu = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else return false;
    }

    // Cập nhật mật khẩu
    public boolean CapNhatMatKhau(String UserName, String OldPass, String NewPass){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE maThuThu = ? AND passwordThuThu = ?", new String[]{UserName, OldPass});
        if (cursor.getCount() > 0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("passwordThuThu", NewPass);
            long check = sqLiteDatabase.update("THUTHU", contentValues, "maThuThu = ?", new String[]{UserName});
            if (check == -1){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }
}
