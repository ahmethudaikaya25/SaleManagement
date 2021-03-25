package com.ahk.productmanagement.embedded_db;


import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class ConnectionProvider {

    private static SQLiteOpenHelper openHelper;

    public static SQLiteOpenHelper getInstance(Context context) {
        if (openHelper == null) {
            openHelper = new ConnectionOpenHelper(context);
            return openHelper;
        } else {
            return openHelper;
        }
    }

}