package com.ahk.productmanagement.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ahk.productmanagement.R;
import com.ahk.productmanagement.datas.Sale;
import com.ahk.productmanagement.datas.SaleDetails;
import com.ahk.productmanagement.embedded_db.managers.SaleDBManager;
import com.ahk.productmanagement.embedded_db.managers.SaleDetailsDBManager;
import com.ahk.productmanagement.util.CreateProductButtons;

import java.util.HashMap;
import java.util.Set;

import static java.lang.Float.parseFloat;

public class SaleFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private ScrollView scrollView;
    private Button cashButton,creditButton,previousButton;
    private TextView message1, message2, message3, message4;
    private Float total;
    private HashMap<String,Float> map = new HashMap<>();

    public void setMainContext(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sale_fragment, container, false);
        total = Float.valueOf(0);
        scrollView = v.findViewById(R.id.buttonScroll);
        TableLayout tableLayout = v.findViewById(R.id.buttonTableLayout);
        message1 = v.findViewById(R.id.message1);
        message2 = v.findViewById(R.id.message2);
        message3 = v.findViewById(R.id.message3);
        message4 = v.findViewById(R.id.message4);
        cashButton = v.findViewById(R.id.cash_button);
        cashButton.setOnClickListener(this);
        creditButton = v.findViewById(R.id.credit_button);
        creditButton.setOnClickListener(this);
        previousButton = v.findViewById(R.id.previousButton);
        previousButton.setOnClickListener(this);
        CreateProductButtons createButtons = new CreateProductButtons(context, tableLayout, this);
        Thread thread = new Thread(createButtons);
        thread.start();
        return v;
    }

    @Override
    public void onClick(View view) {
        if(!(view.getId() == R.id.cash_button ||view.getId() == R.id.credit_button ||view.getId() ==R.id.previousButton)){
            Button button = (Button) view;
            String buttonText = button.getText().toString();
            String a [] = buttonText.split("\n");
            if(map.get(a[0])==null){
                map.put(a[0], parseFloat(a[1]));
                message1.setText(String.valueOf(a[0]));
                message2.setText(String.valueOf(a[1]));
                message3.setText("TOTAL");
                total = total+Float.parseFloat(a[1]);
                message4.setText(String.valueOf(total));
            }else {
                Float amount =map.get(a[0]);
                amount = amount+ parseFloat(a[1]);
                map.remove(a[0]);
                map.put(a[0],amount);
                message1.setText(String.valueOf(a[0]));
                message2.setText(String.valueOf(a[1]));
                message3.setText("TOTAL");
                total = total+Float.parseFloat(a[1]);
                message4.setText(String.valueOf(total));
            }
        }else {
            switch (view.getId()){
                case R.id.cash_button:
                    if(map.size()==0){
                        Toast.makeText(context,"Please sale products",Toast.LENGTH_SHORT);
                    }else {
                        message1.setText("CASH");
                        message2.setText(String.valueOf(total));
                        message3.setText("PAYMENT HAS DONE...");
                        message4.setText("");
                        Set<String> names = map.keySet();
                        SaleDetailsDBManager dbManager = new SaleDetailsDBManager(context);
                        for (String name:names){
                            dbManager.update(new SaleDetails().productName(name).amount(map.get(name)));
                        }
                        Sale sale = new Sale().totalAmount(total).cashPayment(total).creditPayment(Float.parseFloat( "0"));
                        SaleDBManager saleDBManager = new SaleDBManager(context);
                        saleDBManager.update(sale);
                        total = Float.valueOf(0);
                        map.clear();

                    }
                    break;
                case R.id.credit_button:
                    if(map.size()==0){
                        Toast.makeText(context,"Please sale products",Toast.LENGTH_SHORT);
                    }else {
                        message1.setText("CREDIT");
                        message2.setText(String.valueOf(total));
                        message3.setText("PAYMENT HAS DONE...");
                        message4.setText("");
                        Set<String> names = map.keySet();
                        SaleDetailsDBManager dbManager = new SaleDetailsDBManager(context);
                        for (String name:names){
                            dbManager.update(new SaleDetails().productName(name).amount(map.get(name)));
                        }
                        Sale sale = new Sale().totalAmount(total).cashPayment(Float.parseFloat( "0")).creditPayment(total);
                        SaleDBManager saleDBManager = new SaleDBManager(context);
                        saleDBManager.update(sale);
                        total = Float.valueOf(0);
                        map.clear();
                    }
                    break;
                case R.id.previousButton:
                    SyncFragment syncFragment = new SyncFragment();
                    syncFragment.setMainContext(context);
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fragmentLayout,syncFragment,"syncFragment");
                    transaction.commit();
                    break;
            }
        }
    }
}
