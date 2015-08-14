package com.rancard.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rancard.kudi.client.async.Callback;
import com.rancard.kudi.domain.Transaction;
import com.rancard.kudi.domain.User;
import com.rancard.materialtest.R;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Created by rancard on 8/11/15.
 */
public class ExchangeRatesFragment extends Fragment implements MainFragment{
    private TextView usdTextView;
    private TextView eurTextView;
    private TextView gbpTextView;
    private TextView ghsTextView;
    private static Activity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_exchange_rates, container, false);
        usdTextView = (TextView) v.findViewById(R.id.usd);
        eurTextView = (TextView) v.findViewById(R.id.eur);
        gbpTextView = (TextView) v.findViewById(R.id.gbp);
        ghsTextView = (TextView) v.findViewById(R.id.ghs);

        mActivity = getActivity();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onCreate(savedInstanceState);

        kudiInstance.getExchangeRates(new Callback<Map>() {
            @Override
            public void onFailure(String s, int i) {
                Log.d("error", s + " " + i);
            }

            @Override
            public void onSuccess(final Map map) {

                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Iterator entries = map.entrySet().iterator();
                        while (entries.hasNext()) {
                            Map.Entry entry = (Map.Entry) entries.next();
                            String key = (String) entry.getKey();
                            String value = (String) entry.getValue();
                            if (key.matches("USD")) {
                                usdTextView.setText(value);
                            } else if (key.matches("EUR")) {
                                eurTextView.setText(value);
                            } else if (key.matches("GBP")) {
                                gbpTextView.setText(value);
                            } else if (key.matches("GHS")) {
                                ghsTextView.setText(value);
                            }
                        }
                        Log.d("Exchange Rates", "*********================" + map);
                    }
                });

            }
        });
    }
}
