package com.ahk.productmanagement.requests.products;

import com.ahk.productmanagement.requests.ConnectionInformation;
import com.ahk.productmanagement.requests.RetrofitClient;

public class ProductAPIUtil {
    public static ProductService getProductService() {
        String baseUrl = "http://"+ConnectionInformation.IP + ":" + ConnectionInformation.PORT+"/";
        return RetrofitClient.getClient().create(ProductService.class);
    }
}
