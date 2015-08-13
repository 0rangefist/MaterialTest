package com.rancard.fragments;

import android.app.Activity;
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
public class ViewAccountFragment extends Fragment implements MainFragment{
    static TextView accountText;
    String _account;
    Activity mActivity;

    private static final String ARG_SECTION_NUMBER = "section_number";
    String result;

    public static ViewAccountFragment newInstance(int sectionNumber) {
        ViewAccountFragment fragment = new ViewAccountFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    public ViewAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_view_account, container, false);
        accountText= (TextView) rootView.findViewById(R.id.account);

        mActivity = getActivity();

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Long accountNum = 123L;
        session.viewAccount(accountNum, new Callback<Account>() {
            @Override
            public void onFailure(String s, int i) {
                Log.d("On Failure", s + " " + i);
            }

            @Override
            public void onSuccess(final Account account) {
                Log.d("On Success", account.toString());
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        _account="Account Name: "+account.getAccountName().toString()+ "\n";
                        _account=_account+"Account Number: "+account.getAccountNumber().toString()+ "\n";
                        _account=_account+account.getAccountTypeId().toString()+ "\n";

                        accountText.setText(_account);

                    }
                });


            }
        });

    }


}
