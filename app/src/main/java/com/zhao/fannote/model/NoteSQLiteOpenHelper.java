package com.zhao.fannote.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zhao on 2016/5/25.
 */
public class NoteSQLiteOpenHelper extends SQLiteOpenHelper {
    private final String CREATE_TABLE_NOTES = "CREATE TABLE notes(" +
            "_id INTEGER PRIMARY KEY," +
            "title TEXT," +
            "content TEXT)";
    public NoteSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(db);
    }
}
