package com.ahk.productmanagement.requests.products;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ahk.productmanagement.datas.Product;
import com.ahk.productmanagement.embedded_db.managers.ProductDBManager;
import com.ahk.productmanagement.requests.ConnectionInformation;
import com.ahk.productmanagement.requests.IRequestListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductGetAndSave implements Runnable, IRequestListener {

    private Context context;

    public ProductGetAndSave(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        String url = "http://" + ConnectionInformation.IP + ":" + ConnectionInformation.PORT + "/";
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        ProductService apiService = retrofit.create(ProductService.class);
        Call<List<Product>> products = apiService.getAllProducts();
        products.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> products1 = new ArrayList<>();
                for (Product product : response.body()) {
                    products1.add(product);
                }
                new Thread() {
                    {
                        ProductDBManager manager = new ProductDBManager(context);
                        manager.deleteAllProducts();
                        manager.saveAllProducts(products1);
                        Toast.makeText(context, "Products are get", Toast.LENGTH_SHORT).show();

                    }
                };
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(t.getMessage(), t.getStackTrace().toString());
            }

        });
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError(Exception e) {

    }

}

