package com.ahk.productmanagement;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.ahk.productmanagement.fragments.ConnectFragment;
import com.ahk.productmanagement.requests.ConnectionInformation;

import java.io.File;
import java.nio.file.Files;

public class MainActivity extends AppCompatActivity {

    public FragmentManager manager;
    private FrameLayout fL;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectFragment connectFragment = new ConnectFragment();
        connectFragment.setContextForThis(context);
        manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentLayout,connectFragment,"connectFragment");
        transaction.commit();
    }


}