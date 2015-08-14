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
import com.rancard.kudi.domain.Account;
import com.rancard.materialtest.R;

/**
 * Created by rancard on 8/12/15.
 */
public class ViewAccountFragment extends Fragment implements MainFragment, View.OnClickListener{
    private long accountNum;
    private String accountString;
    private TextView accountTextView;
    private EditText accountEditText;
    Button submitButton;
    private static Activity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_account, container, false);
        accountTextView = (TextView) v.findViewById(R.id.account);
        accountEditText = (EditText) v.findViewById(R.id.account_entry);
        submitButton = (Button) v.findViewById(R.id.account_submit_button);
        submitButton.setOnClickListener(this);

        mActivity = getActivity();
        return v;
    }

    public void viewAccount() {
        session.viewAccount(accountNum, new Callback<Account>() {
            @Override
            public void onFailure(String s, int i) {
                Log.d("error", s + " " + i);

            }

            @Override
            public void onSuccess(final Account account) {

                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        accountString = "Account Number: " + account.getAccountNumber() + "\n\n";
                        accountString = accountString + "Account Name: " + account.getAccountName() + "\n\n";
                        accountString = accountString + "Account Type: " + account.getAccountType() + "\n\n";
                        accountString = accountString + "Complete Date: " + account.getAccountTypeId() + "\n\n";
                        accountString = accountString + "State: " + account.getState() + "\n\n";
                        accountString = accountString + "Current Balance: " + account.getCurrentBalance() + "\n\n";
                        accountString = accountString + "Previous Balance: " + account.getPreviousBalance() + "\n\n";
                        accountString = accountString + "Last Transaction: " + account.getLastTransaction();
                        accountTextView.setText(accountString);
                        Log.d("Account Number", "*********================" + account.getAccountNumber());
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        //set the transaction id
        String accountString = accountEditText.getText().toString();
        if(TextUtils.isEmpty(accountString)) {
            accountEditText.setError("Cannot be empty");
            return;
        }
        accountNum = Long.parseLong(accountEditText.getText().toString());
        viewAccount();
    }
}
