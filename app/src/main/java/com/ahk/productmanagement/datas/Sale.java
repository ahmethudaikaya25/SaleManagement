package com.ahk.productmanagement.datas;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sale {
    @SerializedName("receiptCount")
    @Expose
    Integer receiptCount;
    @SerializedName("totalAmount")
    @Expose
    Float totalAmount;
    @SerializedName("cashPayment")
    @Expose
    Float cashPayment;
    @SerializedName("creditPayment")
    @Expose
    Float creditPayment;



    public String toJson(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("receiptCount",receiptCount);
        jsonObject.addProperty("totalAmount",totalAmount);
        jsonObject.addProperty("cashPayment",cashPayment);
        jsonObject.addProperty("creditPayment",creditPayment);
        return jsonObject.toString();
    }

    public Integer getReceiptCount() {
        return receiptCount;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public Float getCashPayment() {
        return cashPayment;
    }

    public Float getCreditPayment() {
        return creditPayment;
    }

    public Sale receiptCount(Integer receiptCount) {
        this.receiptCount = receiptCount;
        return this;
    }

    public Sale totalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public Sale cashPayment(Float cashPayment) {
        this.cashPayment = cashPayment;
        return this;
    }

    public Sale creditPayment(Float creditPayment) {
        this.creditPayment = creditPayment;
        return this;
    }
    public void setReceiptCount(Integer receiptCount) {
        this.receiptCount = receiptCount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setCashPayment(Float cashPayment) {
        this.cashPayment = cashPayment;
    }

    public void setCreditPayment(Float creditPayment) {
        this.creditPayment = creditPayment;
    }

}
