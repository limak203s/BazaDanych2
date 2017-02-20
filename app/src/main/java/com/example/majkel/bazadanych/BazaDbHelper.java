package com.example.majkel.bazadanych;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by MAJKEL on 30.10.2016.
 */

public class BazaDbHelper extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME = "baza.db";
    public static final int DATABASE_VERSION = 1;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + Baza.TABLE_NAME + " ("
            + Baza._ID + " INTEGER PRIMARY KEY, "
            + Baza.TEKST + " TEXT"
            +" )";

    public static final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + Baza.TABLE_NAME;

    public BazaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }


    public class Baza implements BaseColumns{
        public static final String TABLE_NAME = "baza";
        public static final String TEKST = "tekst";
    }
}
