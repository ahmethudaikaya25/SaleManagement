package com.ahk.productmanagement.requests;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.ahk.productmanagement.R;
import com.ahk.productmanagement.fragments.SyncFragment;

import java.net.InetSocketAddress;
import java.net.Socket;

public class CheckConnection implements Runnable, IRequestListener {

    private String IP;

    private Integer PORT;

    private Context context;
 private FragmentManager manager;

    /***
     *
     * @param context
     * @param IP
     * @param PORT
     * @param manager
     */
    public CheckConnection(Context context, String IP, Integer PORT, FragmentManager manager) {
        this.IP = IP;
        this.PORT = PORT;
        this.context = context;
        this.manager = manager;
    }


    @Override
    public void run() {
        try {
            InetSocketAddress sa = new InetSocketAddress(IP, PORT);
            Socket ss = new Socket();
            ss.connect(sa, 100);
            ss.close();
            onSuccess();
        } catch (Exception e) {
            onError(e);
        }
    }

    @Override
    public void onSuccess() {
        ConnectionInformation.IP = IP;
        ConnectionInformation.PORT = PORT;
        SyncFragment fragment = new SyncFragment();
        fragment.setMainContext(context);
        FragmentManager manager = this.manager;
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentLayout, fragment, "syncFragment");
        transaction.commit();
    }

    @Override
    public void onError(Exception e) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                Toast.makeText(context, "Connection Error", Toast.LENGTH_LONG).show();
            }
        });
    }
}
