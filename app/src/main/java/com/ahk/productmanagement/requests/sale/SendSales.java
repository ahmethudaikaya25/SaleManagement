package com.ahk.productmanagement.requests.sale;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.ahk.productmanagement.datas.Sale;
import com.ahk.productmanagement.embedded_db.managers.SaleDBManager;
import com.ahk.productmanagement.requests.ConnectionInformation;
import com.ahk.productmanagement.requests.IRequestListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendSales implements Runnable {
    Context context;

    List<Sale> sales;


    public SendSales(Context context, List<Sale> sales) {
        this.context = context;
        this.sales = sales;
    }


    @Override
    public void run() {
        if ((sales.size() > 0)) {
            String url = "http://" + ConnectionInformation.IP + ":" + ConnectionInformation.PORT + "/";
            Retrofit retrofit = new Retrofit
                    .Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create()).build();

            SaleService apiService = retrofit.create(SaleService.class);
            Call<Boolean> req = apiService.uploadSaleServices(sales);
            SaleDBManager manager = new SaleDBManager(context);
            manager.deleteAllSales();
            req.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    System.out.println(response.body());
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    System.out.println(t.getStackTrace());
                }
            });
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    //do stuff like remove view etc
                }
            });

        }

    }


}
