package com.rancard.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rancard.kudi.client.async.Callback;
import com.rancard.kudi.domain.Transaction;
import com.rancard.main.R;

/**
 * Created by rancard on 8/12/15.
 */
public class CreateTransactionFragment extends Fragment implements MainFragment, View.OnClickListener {
    private EditText accountFromEditText;
    private EditText accountToEditText;
    private EditText amountEditText;
    private Button submitButton;
    private static Activity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_transaction, container, false);
        accountFromEditText = (EditText) v.findViewById(R.id.account_source_entry);
        accountToEditText = (EditText) v.findViewById(R.id.account_target_entry);
        amountEditText = (EditText) v.findViewById(R.id.amount_entry);
        submitButton = (Button) v.findViewById(R.id.create_transaction_button);
        submitButton.setOnClickListener(this);

        mActivity = getActivity();
        return v;
    }

    public void createTransaction(long from, long to, double amount){
        Transaction.SimpleRequest transactionRequest = new Transaction.SimpleRequest();
        transactionRequest.setAccountFrom(from);
        transactionRequest.setAccountTo(to);
        transactionRequest.setAmount(amount);

        session.createTransaction(transactionRequest, new Callback<Long>() {
            @Override
            public void onFailure(String s, int i) {
                Log.d("transaction error", s);
                Toast.makeText(mActivity, "Failed to Create Transaction", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(final Long aLong) {

                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("Creating transaction", aLong.toString());
                        String result = aLong.toString();
                        Log.d("Long transaction", aLong.toString());
                        Toast toast = Toast.makeText(mActivity, "Success! " + amountEditText.getText()
                                + " sent from account number " + accountFromEditText.getText() + " to "
                                + accountToEditText.getText() + "\n"
                                + "Transaction ID: " + result, Toast.LENGTH_LONG);
                        fireLongToast(toast);
                        accountFromEditText.setText("");
                        accountToEditText.setText("");
                        amountEditText.setText("");
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        String sourceString = accountFromEditText.getText().toString();
        String targetString = accountToEditText.getText().toString();
        String amountString = amountEditText.getText().toString();
        if(TextUtils.isEmpty(sourceString)) {
            accountFromEditText.setError("Cannot be empty");
            return;
        }
        if(TextUtils.isEmpty(targetString)) {
            accountToEditText.setError("Cannot be empty");
            return;
        }
        if(TextUtils.isEmpty(amountString)) {
            amountEditText.setError("Cannot be empty");
            return;
        }
        createTransaction(Long.parseLong(sourceString), Long.parseLong(targetString), Double.parseDouble(amountString));
    }

    private void fireLongToast(final Toast toast) {
        Thread t = new Thread() {
            public void run() {
                int count = 0;
                try {
                    while (true && count < 4) {
                        toast.show();
                        sleep(1850);
                        count++;
                    }
                } catch (Exception e) {
                    Log.e("LongToast", "", e);
                }
            }
        };
        t.start();
    }
}
