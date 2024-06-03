package com.example.sary.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sary.Database.DbHelper;
import com.example.sary.Model.Sach;

import java.util.ArrayList;

public class SachDAO {
    private DbHelper dbHelper;
    public SachDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<Sach> getDSSach(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SACH", null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    //Add
    public boolean AddSach(String masach, String tensach, String tacgia, String nxb, String theloai, int gia) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maSach", masach);
        values.put("tensach", tensach);
        values.put("tacgia", tacgia);
        values.put("nhaxuatban", nxb);
        values.put("theloai", theloai);
        values.put("giathue", gia);
        long check = db.insert("SACH", null, values);
        return check > 0;
    }
    //Update
    public boolean UpdateSach(String masach, String tensach, String tacgia, String nxb, String theloai, int gia) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tensach", tensach);
        values.put("tacgia", tacgia);
        values.put("nhaxuatban", nxb);
        values.put("theloai", theloai);
        values.put("giathue", gia);
        long check = db.update("SACH", values, "maSach=?", new String[]{String.valueOf(masach)});
        return check > 0;
    }

    //Delete
    public boolean DeleteSach(String masach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long check = db.delete("SACH", "maSach=? ", new String[]{String.valueOf(masach)});
        return check > 0;
    }
}
