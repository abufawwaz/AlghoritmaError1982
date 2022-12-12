package com.roaita.imsakiyah.notes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_NOTES = "notes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_LAST_REVIEWED = "last_reviewed";
    public static final String COLUMN_TOTAL_REVIEWS = "total_reviews";
    public static final String COLUMN_CONTENT = "content";

    public static final String TABLE_IMSAKIYAH = "imsakiyah";
    public static final String COLUMN_ID_IMSAKIYAH = "_id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_DATA_SATU = "data_satu";
    public static final String COLUMN_DATA_DUA = "data_dua";
    public static final String COLUMN_DATA_TIGA = "data_tiga";
    public static final String COLUMN_DATA_EMPAT = "data_empat";
    public static final String COLUMN_DATA_LIMA = "data_lima";
    public static final String COLUMN_DATA_ENAM = "data_enam";


    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NOTES + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_LAST_REVIEWED
            + " text not null);";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(" CREATE TABLE " + TABLE_NOTES + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT NOT NULL, " +
                        COLUMN_LAST_REVIEWED + " TEXT NOT NULL, " +
                        COLUMN_TOTAL_REVIEWS + " INT NOT NULL, " +
                        COLUMN_CONTENT + " TEXT NOT NULL);"
        );

        database.execSQL(" CREATE TABLE " + TABLE_IMSAKIYAH + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TYPE + " TEXT NOT NULL, " +
                COLUMN_DATA_SATU + " TEXT NOT NULL, " +
                COLUMN_DATA_DUA + " INT NOT NULL, " +
                COLUMN_DATA_TIGA+ " TEXT NOT NULL,"+
                COLUMN_DATA_EMPAT+ " TEXT NOT NULL,"+
                COLUMN_DATA_LIMA+ " TEXT NOT NULL,"+
                COLUMN_DATA_ENAM+ " TEXT NOT NULL);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMSAKIYAH);
        onCreate(db);
    }

}