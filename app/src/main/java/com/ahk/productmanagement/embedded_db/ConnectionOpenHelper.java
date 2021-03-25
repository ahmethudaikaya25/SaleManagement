package com.ahk.productmanagement.embedded_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ConnectionOpenHelper extends SQLiteOpenHelper {
    public ConnectionOpenHelper(Context context){
        super(context, String.valueOf(context.getDatabasePath("hugin")),null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        List<String> sqls = new ArrayList<>();
        sqls.add("CREATE TABLE IF NOT EXISTS products (\n"
                + "	name text(20) UNIQUE,\n"
                + "	price double,\n"
                + " val integer\n"
                + ");");
        sqls.add("CREATE TABLE IF NOT EXISTS sale (\n"
                + "	receiptCount integer,\n"
                + "	totalAmount double,\n"
                + "	cashPayment double,\n"
                + " creditPayment double\n"
                + ");");
        sqls.add("CREATE TABLE IF NOT EXISTS saleDetails (\n"
                + "	productId integer,\n"
                + "	name text(20),\n"
                + "	amount double\n"
                + ");");
        for (String sql : sqls) {
            sqLiteDatabase.execSQL(sql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
