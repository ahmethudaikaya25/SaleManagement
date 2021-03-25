package com.ahk.productmanagement.requests.products;

import com.ahk.productmanagement.datas.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {

    @GET("/products/getProduct")
    Call<List<Product>> getAllProducts();

}


