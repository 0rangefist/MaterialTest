package com.rancard.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.rancard.kudi.client.async.Kudi;
import com.rancard.materialtest.R;

/**
 * Created by rancard on 8/11/15.
 */
public class ConvertCurrencyFragment extends Fragment implements MainFragment{
    Spinner toCurrency;
    Spinner fromCurrency;
    EditText amount;
    TextView displayConversion;

    static Kudi kudiInstance = Kudi.newInstance("http://10.42.0.1:8080/wallet/api/v1");
    static Kudi.Session session = kudiInstance.getSession("877");
    String userProfile;
    TextView textView;
    public static Activity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_convert_currency,container,false);

        mActivity = getActivity();
        toCurrency = (Spinner)v.findViewById(R.id.to_currency);
        fromCurrency = (Spinner)v.findViewById(R.id.from_currency);
        amount = (EditText)v.findViewById(R.id.amount);
        displayConversion = (TextView)v.findViewById(R.id.convert_currency);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.currency,android.R.layout.simple_dropdown_item_1line);

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        toCurrency.setAdapter(adapter);
        fromCurrency.setAdapter(adapter);

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       String _amount = amount.getText().toString();
        Enum to;
        Enum from;

        toCurrency.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              if(parent.getPo)
            }
        });


        fromCurrency.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }
}
