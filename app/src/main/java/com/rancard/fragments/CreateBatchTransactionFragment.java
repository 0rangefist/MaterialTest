package com.rancard.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.rancard.kudi.client.async.Callback;
import com.rancard.kudi.domain.Transaction;
import com.rancard.materialtest.R;

import java.util.ArrayList;

/**
 * Created by rancard on 8/12/15.
 */
public class CreateBatchTransactionFragment extends Fragment implements MainFragment, View.OnClickListener {
    private EditText accountFromEditText;
    private EditText accountToEditText;
    private EditText amountEditText;
    private Button submitButton;
    private Button addButton;
    private Button cancelButton;
    ListView bTransactionListView;
    ArrayAdapter mArrayAdapter;
    ArrayList transactionList = new ArrayList();
    Transaction.BatchRequest transactionRequest = new Transaction.BatchRequest();;
    private static Activity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity = getActivity();
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_btransaction, container, false);
        accountFromEditText = (EditText) v.findViewById(R.id.baccount_source_entry);
        accountToEditText = (EditText) v.findViewById(R.id.baccount_target_entry);
        amountEditText = (EditText) v.findViewById(R.id.bamount_entry);

        submitButton = (Button) v.findViewById(R.id.submit_btransaction_button);
        addButton = (Button) v.findViewById(R.id.add_btransaction_button);
        cancelButton = (Button) v.findViewById(R.id.cancel_btransaction_button);

        //get the listView
        bTransactionListView = (ListView) v.findViewById(R.id.btransaction_list);

        //create an arrayAdapter for the listView
        mArrayAdapter = new ArrayAdapter(v.getContext(), android.R.layout.simple_list_item_1, transactionList);

        //set the listView to use the arrayAdapter
        bTransactionListView.setAdapter(mArrayAdapter);

        submitButton.setOnClickListener(this);
        addButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        return v;
    }

    public void createTransaction() {
        session.createBatchTransaction(transactionRequest, new Callback<Long>() {
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
                        Toast toast = Toast.makeText(mActivity, "Batch Transactions successful\n"
                                + "Batch Transaction ID: " + result, Toast.LENGTH_LONG);
                        fireLongToast(toast);
                        clearList();
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

        if(view.getId() == R.id.add_btransaction_button) {
            addToList(Long.parseLong(sourceString), Long.parseLong(targetString), Double.parseDouble(amountString));
        }else if(view.getId() == R.id.submit_btransaction_button) {
            createTransaction();
        }else if(view.getId() == R.id.cancel_btransaction_button) {
            clearList();
        }
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

    public void addToList(long from, long to, double amount) {
        transactionRequest.addSource(from);
        transactionRequest.addTarget(to, amount);

        //show on the listView
        transactionList.add(amount + " from " + from + " to " + to);
        mArrayAdapter.notifyDataSetChanged();
    }

    public void clearList() {
        transactionRequest = new Transaction.BatchRequest();

        //clear the listView
        transactionList.clear();
        mArrayAdapter.notifyDataSetChanged();

        //clear the textbox entries
        accountFromEditText.setText("");
        accountToEditText.setText("");
        amountEditText.setText("");
    }
}
