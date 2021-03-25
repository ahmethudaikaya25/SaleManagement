package com.ahk.productmanagement.embedded_db.managers;

import android.content.Context;
import android.database.Cursor;

import com.ahk.productmanagement.datas.Sale;
import com.ahk.productmanagement.datas.SaleDetails;
import com.ahk.productmanagement.embedded_db.QueryManager;

import java.util.ArrayList;
import java.util.List;

public class SaleDetailsDBManager {
    private final Context context;

    public SaleDetailsDBManager(Context context) {
        this.context = context;
    }

    public Boolean deleteAllSaleDetails() {
        QueryManager manager = new QueryManager();
        String sql = "delete from saleDetails";
        return manager.noResponseQuery(sql, context);
    }

    public Boolean save(SaleDetails saleDetail) {
        QueryManager manager = new QueryManager();
        String sql = "insert into saleDetails (name,amount) values ('" + saleDetail.getProductName() +
                "'," + saleDetail.getAmount() + ")";
        return manager.noResponseQuery(sql, context);
    }

    public SaleDetails getWithProductName(String productName) {
        QueryManager manager = new QueryManager();
        String sql = "select * from saleDetails where name='" + productName + "'";
        Cursor cursor = manager.query(sql, context);
        if (cursor.moveToFirst()) {
            return new SaleDetails().productName(cursor.getString(1)).amount(cursor.getFloat(2));
        } else {
            return null;
        }

    }

    public Boolean update(SaleDetails saleDetail) {
        QueryManager manager = new QueryManager();
        SaleDetails saleDetail2 = getWithProductName(saleDetail.getProductName());
        if (saleDetail2 == null) {
            return save(saleDetail);
        } else {
            String sql = String.format("update saleDetails set amount = %f where name= '%s'"
                    , saleDetail.getAmount() + saleDetail2.getAmount()
                    , saleDetail.getProductName());
            return manager.noResponseQuery(sql, context);
        }
    }

    public List<SaleDetails> getAll() {
        QueryManager manager = new QueryManager();
        String sql = "select * from saleDetails";
        Cursor cunsor = manager.query(sql, context);
        List<SaleDetails> saleDetails = new ArrayList<>();
        if (cunsor.moveToFirst()) {
            do {
                saleDetails.add(new SaleDetails().productName(cunsor.getString(1)).amount(cunsor.getFloat(2)));
            } while (cunsor.moveToNext());
        }
        return saleDetails;
    }

}
