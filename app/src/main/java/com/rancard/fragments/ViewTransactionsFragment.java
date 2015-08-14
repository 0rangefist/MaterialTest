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
import com.rancard.kudi.domain.Transaction;
import com.rancard.materialtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rancard on 8/12/15.
 */
public class ViewTransactionsFragment extends Fragment implements MainFragment{
    private static Activity mActivity;
    private ListView transactionListView;
    ArrayAdapter transactionListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_view_transactions,container,false);
        transactionListView = (ListView) v.findViewById(R.id.transaction_list);

        mActivity = getActivity();
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onCreate(savedInstanceState);

        Log.d("Debug", "session: " + session);
        Transaction.Query query = new Transaction.Query();
        query.setLimit(200);

        session.viewTransactionList(query, new Callback<List<Transaction>>() {
            @Override
            public void onFailure(String s, int i) {
                Log.d("error", s + " " + i);

            }

            @Override
            public void onSuccess(final List<Transaction> transactions) {
                mActivity.runOnUiThread(new Runnable() {

                    ArrayList<Transaction> trans = (ArrayList<Transaction>) transactions;

                    @Override
                    public void run() {
                        //create an arrayAdapter for the listView
                        transactionListAdapter = new ArrayAdapter(mActivity, android.R.layout.simple_list_item_1, trans);

                        //set the listView to use the arrayAdapter
                        transactionListView.setAdapter(transactionListAdapter);
                        transactionListAdapter.notifyDataSetChanged();
                        Log.d("YAY", "Successful");

                    }
                });
            }
        });

    }
}
