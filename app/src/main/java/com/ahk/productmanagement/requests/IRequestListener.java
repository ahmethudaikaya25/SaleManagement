package com.ahk.productmanagement.requests;

public interface IRequestListener {

    void onSuccess();

    void onError(Exception e);
}
