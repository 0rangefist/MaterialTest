package com.rancard.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.rancard.kudi.client.async.Callback;
import com.rancard.kudi.domain.Currency;
import com.rancard.materialtest.R;

/**
 * Created by rancard on 8/11/15.
 */
public class ConvertCurrencyFragment extends Fragment implements MainFragment{
    Spinner toCurrency;
    Spinner fromCurrency;
    EditText amountEditText;
    TextView conversionTextView;
    Button convert;
    Enum to;
    Enum from;

    String userProfile;
    TextView textView;
    public static Activity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_convert_currency,container,false);

        mActivity = getActivity();
        toCurrency = (Spinner)v.findViewById(R.id.to_currency);
        fromCurrency = (Spinner)v.findViewById(R.id.from_currency);
        amountEditText = (EditText)v.findViewById(R.id.amount);
        conversionTextView = (TextView)v.findViewById(R.id.convert_currency);
        convert = (Button)v.findViewById(R.id.convert);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.currency,android.R.layout.simple_dropdown_item_1line);

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        toCurrency.setAdapter(adapter);
        fromCurrency.setAdapter(adapter);

        toCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Println",parent.getItemAtPosition(position).toString());

                if (parent.getItemAtPosition(position).toString().equals("USD") ) {
                    to = Currency.USD;
                }
                if (parent.getItemAtPosition(position).toString().equals("EUR")) {
                    to = Currency.EUR;
                }

                if (parent.getItemAtPosition(position).toString().equals("GBP")) {
                    to = Currency.GBP;
                }

                if (parent.getItemAtPosition(position).toString().equals("NGN")) {
                    to = Currency.NGN;
                }

                if (parent.getItemAtPosition(position).toString().equals("GHS")) {
                    to = Currency.GHS;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fromCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Print", parent.getItemAtPosition(position).toString());

                if (parent.getItemAtPosition(position).toString().equals("USD")) {
                    from = Currency.USD;
                }
                if (parent.getItemAtPosition(position).toString().equals("EUR")) {
                    from = Currency.EUR;
                }

                if (parent.getItemAtPosition(position).toString().equals("GBP")) {
                    from = Currency.GBP;
                }

                if (parent.getItemAtPosition(position).toString().equals("NGN")) {
                    from = Currency.NGN;
                }

                if (parent.getItemAtPosition(position).toString().equals("GHS")) {
                    from = Currency.GHS;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }


    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onCreate(savedInstanceState);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountString = amountEditText.getText().toString();
                if(TextUtils.isEmpty(amountString)) {
                    amountEditText.setError("Cannot be empty");
                    return;
                }
                final Double _amount = Double.parseDouble(amountEditText.getText().toString());
                Log.d("Enum from", from.toString());
                Log.d("Amount", _amount.toString());
                Log.d("Enum to", to.toString());

                kudiInstance.convertCurrency(_amount, from, to, new Callback<Double>() {
                    @Override
                    public void onFailure(String s, int i) {
                        Log.d("On Failure", s + " " + i);
                    }

                    @Override
                    public void onSuccess(final Double aDouble) {

                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("On Success", aDouble.toString());
                                conversionTextView.setText(aDouble.toString());
                            }
                        });


                    }
                });
            }
        });
    }
}
