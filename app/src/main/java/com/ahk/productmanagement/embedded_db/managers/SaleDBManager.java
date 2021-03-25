package com.ahk.productmanagement.embedded_db.managers;

import android.content.Context;
import android.database.Cursor;

import com.ahk.productmanagement.datas.Sale;
import com.ahk.productmanagement.embedded_db.QueryManager;

import java.util.ArrayList;
import java.util.List;

public class SaleDBManager {
    private final Context context;

    public SaleDBManager(Context context) {
        this.context = context;
    }

    public Boolean deleteAllSales() {
        QueryManager manager = new QueryManager();
        String sql = "delete from sale";
        return manager.noResponseQuery(sql, context);
    }

    public Boolean save(Sale sale) {
        QueryManager manager = new QueryManager();
        String sql = "insert into sale values (" +  1 + "," + sale.getTotalAmount() + "," + sale.getCashPayment() + "," + sale.getCreditPayment() + ")";
        return manager.noResponseQuery(sql, context);
    }

    public Boolean update(Sale sale) {
        QueryManager manager = new QueryManager();
        List<Sale> sales = getAllSales();
        if (sales.size() == 0) {
            save(sale);
            return false;
        } else {
            int a = sales.get(0).getReceiptCount()+1;
            String sql = String.format("update sale set receiptCount=%d,totalAmount=%f" +
                            ",cashPayment=%f,creditPayment=%f",
                    a,
                    sale.getTotalAmount() + sales.get(0).getTotalAmount(),
                    sale.getCashPayment() + sales.get(0).getCashPayment(),
                    sale.getCreditPayment() + sales.get(0).getCreditPayment());
            System.out.println(sql);
            return manager.noResponseQuery(sql, context);
        }
    }

    public List<Sale> getAllSales() {
        QueryManager manager = new QueryManager();
        String sql = "select * from sale";
        Cursor cunsor = manager.query(sql, context);
        List<Sale> sales = new ArrayList<>();
        if (cunsor.moveToFirst()) {
            do {
                sales.add(new Sale().receiptCount(cunsor.getInt(0)).totalAmount(cunsor.getFloat(1))
                        .cashPayment(cunsor.getFloat(2)).creditPayment(cunsor.getFloat(3)));
            } while (cunsor.moveToNext());
        }
        return sales;
    }
}
