package com.ahk.productmanagement.requests.sale_details;

import com.ahk.productmanagement.datas.SaleDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SaleDetailsService {

    @POST("/saleDetails/save")
    Call<Boolean> uploadSaleDetails(@Body List<SaleDetails> saleDetails);

}
