package com.ahk.productmanagement.datas;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaleDetails {

    @SerializedName("productId")
    @Expose
    Integer productId;
    @SerializedName("productName")
    @Expose
    String productName;
    @SerializedName("amount")
    @Expose
    Float amount;

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public SaleDetails productId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public SaleDetails productName(String productName) {
        this.productName = productName;
        return this;
    }

    public SaleDetails amount(Float amount) {
        this.amount = amount;
        return this;
    }

}
