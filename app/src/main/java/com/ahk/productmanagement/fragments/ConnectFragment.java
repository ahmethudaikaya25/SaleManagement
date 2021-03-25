package com.ahk.productmanagement.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ahk.productmanagement.R;
import com.ahk.productmanagement.requests.CheckConnection;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectFragment extends Fragment implements OnClickListener {

    private Button connectButton;
    private EditText ipET, portET;
    private Context context;


    public void setContextForThis(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.connectButton:
                connectButtonClicked();
                break;
        }
    }
//\b\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}\b    ip regex
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.connect_fragment, container, false);
        portET = v.findViewById(R.id.portET);
        ipET = v.findViewById(R.id.ipET);

        connectButton = v.findViewById(R.id.connectButton);
        connectButton.setOnClickListener(this);
        return v;
    }

    public void connectButtonClicked() {
        Pattern ipPattern = Pattern.compile("\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b");
        Pattern portPattern = Pattern.compile("\\d{4,5}");
        Matcher matcher = ipPattern.matcher(ipET.getText().toString());
        Matcher matcher2 = portPattern.matcher(portET.getText().toString());
        if (ipET.getText().length() < 1 || portET.getText().length() < 1) {
            Toast.makeText(context, "Port or ip can't empty", Toast.LENGTH_SHORT).show();
        } else if(!matcher2.matches()){
            Toast.makeText(context, "Get correct port", Toast.LENGTH_SHORT).show();
        }else if(!matcher.matches()){
            Toast.makeText(context, "Get correct ip format", Toast.LENGTH_SHORT).show();
        } else {
            Thread thread = new Thread(new CheckConnection(context, ipET.getText().toString(),
                    Integer.parseInt(portET.getText().toString()), getFragmentManager()));
            thread.start();
        }
    }

}





    /*    if (ipET.getText().length() < 1 || portET.getText().length() < 1) {
            Toast.makeText(context, "Port or ip can't empty", Toast.LENGTH_SHORT).show();
        } else {
            try(){

            }
        }*/



