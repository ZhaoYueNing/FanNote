package com.zhao.fannote.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zhao on 2016/5/25.
 */
public class NoteSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "notes";
    private final String CREATE_TABLE_NOTES = "CREATE TABLE "+TABLE_NAME+"(" +
            "_id INTEGER PRIMARY KEY," +
            "title TEXT," +
            "content TEXT," +
            "date INTEGER)";
    public static final String SQL_NAME = "notesData.db";

    public NoteSQLiteOpenHelper(Context context, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, SQL_NAME, factory, version);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        db.execSQL(CREATE_TABLE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(db);
    }
}
