package com.ahk.productmanagement.requests.sale;

import com.ahk.productmanagement.datas.Sale;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SaleService {

    @POST("/sale/save")
    Call<Boolean> uploadSaleServices(@Body List<Sale> sales);

}
