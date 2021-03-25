package com.ahk.productmanagement.requests.sale;

import com.ahk.productmanagement.requests.ConnectionInformation;
import com.ahk.productmanagement.requests.RetrofitClient;

public class SaleAPIUtil {
    public static SaleService getSaleService() {
        String baseUrl = ConnectionInformation.IP + ":" + ConnectionInformation.PORT;
        return RetrofitClient.getClient().create(SaleService.class);
    }
}
