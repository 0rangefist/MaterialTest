package com.rancard.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rancard.kudi.client.async.Callback;
import com.rancard.kudi.domain.Account;
import com.rancard.materialtest.R;

/**
 * Created by rancard on 8/12/15.
 */
public class CreateAccountFragment extends Fragment implements MainFragment{
    static TextView createAccount;
    private static final String ARG_SECTION_NUMBER = "section_number";
    String result;

    public static CreateAccountFragment newInstance(int sectionNumber) {
        CreateAccountFragment fragment = new CreateAccountFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    public CreateAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_create_account, container, false);

        createAccount = (TextView)rootView.findViewById(R.id.accountNumber);

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Account account = new Account();
        account.setAccountName("Thomas");
        session.createAccount(account, new Callback<Long>() {
            @Override
            public void onFailure(String s, int i) {
                Log.d("account error", s);

            }

            @Override
            public void onSuccess(final Long aLong) {


                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("Creating account", aLong.toString());
                        result = aLong.toString();
                        Log.d("Long account", aLong.toString());

                        createAccount.setText(result);


                    }
                });


            }

        });


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Account account = new Account();
        account.setAccountName("Thomas");
        session.createAccount(account, new Callback<Long>() {
            @Override
            public void onFailure(String s, int i) {
                Log.d("account error", s);

            }

            @Override
            public void onSuccess(final Long aLong) {


                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("Creating account", aLong.toString());
                        result = aLong.toString();
                        Log.d("Long account", aLong.toString());

                        createAccount.setText(result);


                    }
                });


            }

        });
    }
}
