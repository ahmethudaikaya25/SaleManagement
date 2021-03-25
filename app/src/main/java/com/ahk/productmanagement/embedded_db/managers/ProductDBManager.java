package com.ahk.productmanagement.embedded_db.managers;

import android.content.Context;
import android.database.Cursor;

import com.ahk.productmanagement.datas.Product;
import com.ahk.productmanagement.embedded_db.QueryManager;

import java.util.ArrayList;
import java.util.List;

public class ProductDBManager {

    private Context context;

    public ProductDBManager(Context context) {
        this.context = context;
    }

    public Boolean deleteAllProducts() {
        QueryManager manager = new QueryManager();
        String sql = "delete from products";
        return manager.noResponseQuery(sql, context);
    }

    public Boolean saveAllProducts(List<Product> products) {
        deleteAllProducts();
        QueryManager manager = new QueryManager();
        String sql = "insert into products values ";
        for (Product product : products) {
            sql = sql + "('" + product.getName() + "'," + product.getPrice() + "," + product.getVal() + "),";
        }
        sql = sql.substring(0, sql.length()-1);
        System.out.println(sql);

        return manager.noResponseQuery(sql, context);
    }

    public Product getProductWithName(String name) {
        QueryManager manager = new QueryManager();
        String sql = "select * from products where name= '" + name + "'";
        Cursor cunsor = manager.query(sql, context);
        Product product = null;
        if (cunsor.moveToFirst()) {
            do {
                product = new Product();
                product.name(cunsor.getString(0)).price(cunsor.getFloat(1)).val(cunsor.getInt(2));
            } while (cunsor.moveToNext());
        }
        return product;
    }

    public List<Product> getAllProducts() {
        QueryManager manager = new QueryManager();
        String sql = "select * from products";
        Cursor cunsor = manager.query(sql, context);
        List<Product> products = new ArrayList<>();
        if (cunsor.moveToFirst()) {
            do {
                products.add(new Product().name(cunsor.getString(0)).price(cunsor.getFloat(1))
                        .val(cunsor.getInt(2)));
            } while (cunsor.moveToNext());
        }
        return products;
    }

}
