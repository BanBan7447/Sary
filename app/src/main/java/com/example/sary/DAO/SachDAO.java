package com.example.sary.DAO;

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
}
