package com.example.sary.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sary.Database.DbHelper;
import com.example.sary.Model.LoaiSach;

import java.util.ArrayList;

public class LoaiSachDAO {
    private DbHelper dbHelper;
    public LoaiSachDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<LoaiSach> getDSLoaiSach(){
        ArrayList<LoaiSach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISACH", null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                list.add(new LoaiSach(cursor.getString(0), cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    //Add
    public boolean AddLoaiSach(String maloai, String tenloai) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maloai", maloai);
        values.put("tenloai", tenloai);
        long check = db.insert("LOAISACH", null, values);
        return check > 0;
    }

    //Update
    public boolean UpdateLoaiSach(String maloai, String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenloai", name);
        long check = db.update("LOAISACH", values, "maloai=?", new String[]{String.valueOf(maloai)});
        return check > 0;
    }

    //Delete
    public boolean DeleteLoaiSach(int maloai) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long check = db.delete("LOAISACH", "maloai=? ", new String[]{String.valueOf(maloai)});
        return check > 0;
    }
}
