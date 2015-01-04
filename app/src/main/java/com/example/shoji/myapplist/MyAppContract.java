package com.example.shoji.myapplist;

import android.provider.BaseColumns;

/**
 * Created by Shoji on 2015/01/04.
 */
public final class MyAppContract
{
    // コンストラクタ
    public MyAppContract() {}

    public static abstract class Users implements BaseColumns
    {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SCORE = "score";

        public static final String CREATE_TABLE =
                "create table " + TABLE_NAME + " (" +
                COLUMN_ID + " integer primary key autoincrement, " +
                COLUMN_NAME + " text, " +
                COLUMN_SCORE + " integer)" ;
        public static final String INIT_TABLE =
                "insert into " + TABLE_NAME + " (" + COLUMN_NAME + ", " + COLUMN_SCORE + ") values " +
                "('taguchi', 42), ('fkoji', 82), ('dotinstall', 63)";
        public static final String DROP_TABLE =
                "drop table if exists " + TABLE_NAME;
    }
}
