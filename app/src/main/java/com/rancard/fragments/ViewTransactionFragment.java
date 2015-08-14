package com.rancard.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rancard.kudi.client.async.Callback;
import com.rancard.kudi.domain.Transaction;
import com.rancard.materialtest.R;

/**
 * Created by rancard on 8/12/15.
 */
public class ViewTransactionFragment extends Fragment implements MainFragment, View.OnClickListener {
    private long transactionId;
    private String transactionString;
    private TextView transactionTextView;
    private EditText transactionEditText;
    Button submitButton;
    private static Activity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_transaction, container, false);
        transactionTextView = (TextView) v.findViewById(R.id.transaction);
        transactionEditText = (EditText) v.findViewById(R.id.transaction_entry);
        submitButton = (Button) v.findViewById(R.id.transaction_submit_button);
        submitButton.setOnClickListener(this);

        mActivity = getActivity();
        return v;
    }

    public void viewTransaction() {
        session.viewTransaction(transactionId, new Callback<Transaction>() {
            @Override
            public void onFailure(String s, int i) {
                Log.d("error", s + " " + i);

            }

            @Override
            public void onSuccess(final Transaction transaction) {

                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        transactionString = "Account Number: " + transaction.getAccountNumber() + "\n\n";
                        transactionString = transactionString + "Transaction ID: " + transaction.getTransactionId() + "\n\n";
                        transactionString = transactionString + "Amount: " + transaction.getAmount() + "\n\n";
                        transactionString = transactionString + "Event: " + transaction.getEvent() + "\n\n";
                        transactionString = transactionString + "Ref Code: " + transaction.getRefCode() + "\n\n";
                        transactionString = transactionString + "State: " + transaction.getState() + "\n\n";
                        transactionString = transactionString + "Start Date: " + transaction.getStartedAt() + "\n\n";
                        transactionString = transactionString + "Complete Date: " + transaction.getCompletedAt();
                        transactionTextView.setText(transactionString);
                        Log.d("Transaction ID", "*********================" + transaction.getTransactionId());
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        //set the transaction id
        String transactionString = transactionEditText.getText().toString();
        if(TextUtils.isEmpty(transactionString)) {
            transactionEditText.setError("Cannot be empty");
            return;
        }
        transactionId = Long.parseLong(transactionEditText.getText().toString());
        viewTransaction();
    }
}
