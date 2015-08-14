package com.rancard.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rancard.kudi.client.async.Callback;
import com.rancard.kudi.domain.Account;
import com.rancard.kudi.domain.Transaction;
import com.rancard.materialtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rancard on 8/12/15.
 */
public class ViewAccountsFragment extends Fragment implements MainFragment {
    private static Activity mActivity;
    private ListView accountListView;
    List accountList = new ArrayList();
    ArrayAdapter accountListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_view_accounts,container,false);
        accountListView = (ListView) v.findViewById(R.id.account_list);

        //create an arrayAdapter for the listView
        accountListAdapter = new ArrayAdapter(v.getContext(), android.R.layout.simple_list_item_1, accountList);

        //set the listView to use the arrayAdapter
        accountListView.setAdapter(accountListAdapter);

        mActivity = getActivity();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onCreate(savedInstanceState);

        populateList();
        Log.d("Debug", "session: " + session);
    }

    public void populateList() {
        Account.Query accountQuery = new Account.Query();
        accountQuery.setMinimumBalance(123);
        accountQuery.setAccountTypeId(123L);

        session.viewAccountList(accountQuery, new Callback<List<Account.View>>() {
            @Override
            public void onFailure(String s, int i) {
                Log.d("transaction error: s", i+"");
                //set dummy list data
                accountList.add("Failed to retrieve accounts");
                accountList.add("Failed to retrieve accounts");
                accountList.add("Failed to retrieve accounts");
                accountList.add("Failed to retrieve accounts");
            }

            @Override
            public void onSuccess(final List<Account.View> views) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        for (Account.View i : views) {
                            accountList.add(i);
                        }
                        accountListAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
