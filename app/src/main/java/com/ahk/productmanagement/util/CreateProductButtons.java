package com.ahk.productmanagement.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.ahk.productmanagement.datas.Product;
import com.ahk.productmanagement.embedded_db.managers.ProductDBManager;
import com.ahk.productmanagement.fragments.SaleFragment;
import com.ahk.productmanagement.requests.IRequestListener;

import java.util.ArrayList;
import java.util.List;

public class CreateProductButtons implements Runnable, IRequestListener {
    Context context;
    TableLayout tableLayout;
    SaleFragment saleFragment;
    List<Product> products;
    List<Button> buttons;

    public CreateProductButtons(Context context, TableLayout tableLayout, SaleFragment saleFragment) {
        this.context = context;
        this.tableLayout = tableLayout;
        this.saleFragment = saleFragment;
    }

    @Override
    public void run() {
        ProductDBManager dbManager = new ProductDBManager(context);
        products = dbManager.getAllProducts();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                //do stuff like remove view etc
                onSuccess();
            }
        });

    }

    @Override
    public void onSuccess() {
        buttons = new ArrayList<>();
        for (Product product : products) {
            Button button = new Button(context);
            button.setText(product.getName() + "\n" + product.getPrice());
            button.setOnClickListener(saleFragment);
            buttons.add(button);
        }
        Integer count = buttons.size();
        if (count % 3 == 0) {
            int a = 0;
            for (int i = 0; i < count / 3; i++) {
                TableRow tableRow = new TableRow(context);
                tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                for (int j = 0; j < 3; j++) {
                    tableRow.addView(buttons.get(a));
                    a++;
                }
                tableLayout.addView(tableRow);
            }

        } else {
            int a = 0;
            for (int i = 0; i < count / 3; i++) {
                TableRow tableRow = new TableRow(context);
                tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));
                tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
                tableRow.setMinimumHeight(100);
                for (int j = 0; j < 3; j++) {
                    tableRow.addView(buttons.get(a));
                    a++;
                }
                tableLayout.addView(tableRow);
            }
        }

    }

    @Override
    public void onError(Exception e) {

    }
}
