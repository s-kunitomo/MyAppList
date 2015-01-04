package com.example.shoji.myapplist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shoji on 2015/01/04.
 * Used in Database.java
 */
public class MyDbHelper extends SQLiteOpenHelper
{
    public static final String DB_NAME = "myapp.db";
    public static final int DB_VERSION = 2;

    public MyDbHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // create table
        db.execSQL(MyAppContract.Users.CREATE_TABLE);

        // init table
        db.execSQL(MyAppContract.Users.INIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // delete old table
        db.execSQL(MyAppContract.Users.DROP_TABLE);

        // onCreate
        this.onCreate(db);
    }
}
