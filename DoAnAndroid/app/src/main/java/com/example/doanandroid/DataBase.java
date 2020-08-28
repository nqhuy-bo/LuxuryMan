package com.example.doanandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DataBase  extends SQLiteOpenHelper {


    public DataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String sql){
        SQLiteDatabase database= getWritableDatabase();
        database.execSQL(sql);
    }


    public void INSERT_SP_GIOHANG(int masanpham,String tensanpham,String motasanpham,String hinhanh,int gia,int soluong)
    {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO GIOHANG VALUES(null,?,?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindDouble(1, masanpham);
        statement.bindString(2, tensanpham);
        statement.bindString(3, motasanpham);
        statement.bindString(4, hinhanh);
        statement.bindDouble(5, gia);
        statement.bindDouble(6, soluong);

        statement.executeInsert();
    }

    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
