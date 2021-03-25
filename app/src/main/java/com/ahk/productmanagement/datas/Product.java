package com.ahk.productmanagement.datas;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private Float price;
    @SerializedName("val")
    @Expose
    private Integer val;

    public String toJson(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name",name);
        jsonObject.addProperty("price",price);
        jsonObject.addProperty("val",val);
        return jsonObject.toString();
    }

    public String getName() {
        return name;
    }

    public Product name(String name) {
        this.name = name;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public Product price(float price) {
        this.price = price;
        return this;
    }

    public int getVal() {
        return val;
    }

    public Product val(int val) {
        this.val = val;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

}
