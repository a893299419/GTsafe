package com.example.apple.gtsafe.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by siokagami on 15/4/26.
 */
public class DBHelper extends SQLiteOpenHelper
{
    private final static String DATABASE_NAME = "gtsafe.db";
    private final static int DATABASE_VERSION = 1;

    public  DBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE IF NOT EXISTS gt_logview"+
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT,addTime TEXT,status TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS gt_contact"+
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id TEXT,name TEXT,type TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS gt_attr"+
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT,id TEXT,name TEXT,father TEXT,score TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS gt_loginfo"+
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT ,contact TEXT,attr TEXT,type TEXT,logid TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS gt_yesterday"+
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT ,contact TEXT,val TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2)
    {

    }


}

