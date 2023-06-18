package com.example.randomapp.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductSQLHelper extends SQLiteOpenHelper {
    public static final String TABLE = " product ";
    public static final String ID = " id ";
    public static final String NAME = " name ";
    public static final String BRAND = " brand ";
    public static final String IS_REGULATED = " is_regulated ";
    public static final String RATE = " rate ";
    public static final String CREATED_AT = " created_at ";
    public static final String UPDATED_AT = " updated_at ";
    private static final String DATABASE_NAME = "randomappdb";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_DATABASE = " create table "
            + TABLE + "("
            + ID + "  bigint primary key autoincrement , "
            + NAME + " text not null , "
            + BRAND + " text not null , "
            + IS_REGULATED + " boolean not null , "
            + RATE + " double not null , "
            + CREATED_AT + " date not null , "
            + UPDATED_AT + " date not null ); ";

    public ProductSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate ( SQLiteDatabase database ) {
        database . execSQL ( CREATE_DATABASE );
    }

    @Override
    public void onUpgrade ( SQLiteDatabase db , int oldVersion , int newVersion ) {
        db. execSQL (" DROP TABLE IF EXISTS " + TABLE);
        onCreate (db);
    }

}
