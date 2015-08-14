package com.rancard.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rancard.kudi.client.async.Callback;
import com.rancard.materialtest.R;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by rancard on 8/11/15.
 */
public class ExchangeRatesFragment extends Fragment implements MainFragment{
    TextView textView;
    String _map="";
    Activity mActivity;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_exchange_rates,container,false);
        mActivity = getActivity();

        textView = (TextView)v.findViewById(R.id.textView);

        kudiInstance.getExchangeRates(new Callback<Map>() {
            @Override
            public void onFailure(String s, int i) {

            }

            @Override
            public void onSuccess(final Map map) {

                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Iterator entries = map.entrySet().iterator();
                        while (entries.hasNext()){
                            Map.Entry entry = (Map.Entry) entries.next();
                            String key = (String)entry.getKey();
                            String value = (String)entry.getValue();
                            _map+=(key+" : "+value)+"\n";
                        }

                        textView.setText(_map);
                    }
                });




            }
        });

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
