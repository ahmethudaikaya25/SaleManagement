package com.ahk.productmanagement.embedded_db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QueryManager {

    public Cursor query(String query, Context context) {
        try {
            SQLiteOpenHelper helper = ConnectionProvider.getInstance(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor cursor = database.rawQuery(query, null);
            return cursor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Boolean noResponseQuery(String query, Context context) {
        try {
            SQLiteOpenHelper helper = ConnectionProvider.getInstance(context);
            SQLiteDatabase database = helper.getWritableDatabase();
            database.execSQL(query);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
