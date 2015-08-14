package com.rancard.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rancard.main.R;

/**
 * Created by rancard on 8/12/15.
 */
public class ViewAccountsFragment extends Fragment implements MainFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_view_accounts,container,false);
        return v;
    }
}
