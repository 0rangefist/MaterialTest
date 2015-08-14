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
import android.widget.Toast;

import com.rancard.adapters.TransactionListAdapter;
import com.rancard.kudi.client.async.Callback;
import com.rancard.kudi.domain.Transaction;
import com.rancard.materialtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rancard on 8/12/15.
 */
public class ViewTransactionsFragment extends Fragment implements MainFragment {
    private static Activity mActivity;
    List<Transaction> transactionList = new ArrayList();
    TransactionListAdapter transactionListAdapter;
    private ListView transactionListView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_transactions, container, false);
        transactionListView = (ListView) v.findViewById(R.id.transaction_list);

        //create an arrayAdapter for the listView
        transactionListAdapter = new TransactionListAdapter(v.getContext(), transactionList);

        //set the listView to use the arrayAdapter
        transactionListView.setAdapter(transactionListAdapter);

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
        Transaction.Query query = new Transaction.Query();
        query.setEvent(Transaction.Event.CREDIT);
        query.setLimit(200);
        query.setAccount(12345432323234343L);
        query.setFrom(23534L);
        query.setTo(1233L);
        query.setState(Transaction.State.PENDING);
        query.setOffset(1234L);
        session.viewTransactionList(query, new Callback<List<Transaction>>() {
            @Override
            public void onFailure(String s, int i) {
                Log.d("transaction error: s", i + "");

            }

            @Override
            public void onSuccess(final List<Transaction> transactions) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (Transaction i : transactions) {
                            transactionList.add(i);
                        }
                        transactionListAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
