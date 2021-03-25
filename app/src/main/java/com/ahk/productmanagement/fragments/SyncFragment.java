package com.ahk.productmanagement.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.ahk.productmanagement.R;
import com.ahk.productmanagement.datas.Sale;
import com.ahk.productmanagement.datas.SaleDetails;
import com.ahk.productmanagement.embedded_db.managers.SaleDBManager;
import com.ahk.productmanagement.embedded_db.managers.SaleDetailsDBManager;
import com.ahk.productmanagement.requests.products.ProductGetAndSave;
import com.ahk.productmanagement.requests.sale.SendSales;
import com.ahk.productmanagement.requests.sale_details.SendSaleDetails;

import java.util.List;

public class SyncFragment extends Fragment implements View.OnClickListener {

    private Context context;
    private Button getProducts;
    private Button updateSales;
    private Button nextPage;

    public void setMainContext(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sync_fragment, container, false);
        getProducts = v.findViewById(R.id.getProducts);
        updateSales = v.findViewById(R.id.updateSales);
        nextPage = v.findViewById(R.id.nextPage);

        getProducts.setOnClickListener(this);
        updateSales.setOnClickListener(this);
        nextPage.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.getProducts:
                ProductGetAndSave getter = new ProductGetAndSave(context);
                new Thread(getter).start();
                break;
            case R.id.updateSales:
                SaleDBManager manager = new SaleDBManager(context);
                SaleDetailsDBManager manager1 = new SaleDetailsDBManager(context);
                List<Sale> sales = manager.getAllSales();
                List<SaleDetails> saleDetails = manager1.getAll();
                Thread thread = new Thread(new SendSales(context,sales));
                thread.start();
                Thread thread1 = new Thread(new SendSaleDetails(context,saleDetails));
                thread1.start();

                break;
            case R.id.nextPage:
                SaleFragment saleFragment = new SaleFragment();
                saleFragment.setMainContext(context);
                FragmentManager fManager = getFragmentManager();
                FragmentTransaction transaction = fManager.beginTransaction();
                transaction.replace(R.id.fragmentLayout,saleFragment,"saleFragment");
                transaction.commit();
                break;

        }
    }
}
