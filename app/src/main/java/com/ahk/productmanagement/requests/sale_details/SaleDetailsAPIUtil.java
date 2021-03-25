package com.ahk.productmanagement.requests.sale_details;

import com.ahk.productmanagement.requests.ConnectionInformation;
import com.ahk.productmanagement.requests.RetrofitClient;

public class SaleDetailsAPIUtil {
    public static SaleDetailsService getSaleDetailsService() {
        String baseUrl = ConnectionInformation.IP + ":" + ConnectionInformation.PORT;
        return RetrofitClient.getClient().create(SaleDetailsService.class);
    }
}
