package com.ahk.productmanagement.requests.sale_details;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.ahk.productmanagement.datas.Sale;
import com.ahk.productmanagement.datas.SaleDetails;
import com.ahk.productmanagement.embedded_db.managers.SaleDetailsDBManager;
import com.ahk.productmanagement.requests.ConnectionInformation;
import com.ahk.productmanagement.requests.IRequestListener;
import com.ahk.productmanagement.requests.sale.SaleService;
import com.ahk.productmanagement.requests.sale.SendSales;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendSaleDetails implements Runnable, IRequestListener {

    Context context;

    List<SaleDetails> saleDetails ;

    public SendSaleDetails(Context context, List<SaleDetails> saleDetails) {
        this.context = context;
        this.saleDetails = saleDetails;
    }

    @Override
    public void run() {
        if((saleDetails.size()>0)){
            String url = "http://"+ ConnectionInformation.IP+":"+ConnectionInformation.PORT+"/";
            Retrofit retrofit = new Retrofit
                    .Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create()).build();

            SaleDetailsService apiService = retrofit.create(SaleDetailsService.class);
            Call<Boolean> req = apiService.uploadSaleDetails(saleDetails);

            req.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    System.out.println(response.body());
                    onSuccess();
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    System.out.println(t.getStackTrace());
                }
            });
        }else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    //do stuff like remove view etc
                    onError(null);
                }
            });

        }
    }

    @Override
    public void onSuccess() {
        SaleDetailsDBManager dm = new SaleDetailsDBManager(context);
        dm.deleteAllSaleDetails();
    }

    @Override
    public void onError(Exception e) {

    }


}
