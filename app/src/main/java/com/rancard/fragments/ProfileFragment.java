package com.rancard.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rancard.kudi.client.async.Callback;
import com.rancard.kudi.domain.User;
import com.rancard.materialtest.R;

/**
 * Created by rancard on 8/12/15.
 */
public class ProfileFragment extends Fragment implements MainFragment {
    private String pDetailsString;
    private String pNameString;
    private TextView profileDetails;
    private TextView profileName;
    private static Activity mActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        profileDetails = (TextView) v.findViewById(R.id.details);
        profileName = (TextView) v.findViewById(R.id.name);

        mActivity = getActivity();

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onCreate(savedInstanceState);

        Log.d("Debug", "session: " + session);
        session.userProfile(new Callback<User>() {
            @Override
            public void onFailure(String s, int i) {
                Log.d("error", s + " " + i);

            }

            @Override
            public void onSuccess(final User user) {

                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        pNameString = user.getFirstName() +" "+ user.getLastName();
                        pDetailsString = "Email: " + user.getEmail() + "\n";
                        pDetailsString = pDetailsString + "Country: " + user.getCountry() + "\n";
                        pDetailsString = pDetailsString + "Phone: " + user.getMobileNumber();
                        profileName.setText(pNameString);
                        profileDetails.setText(pDetailsString);
                        Log.d("User Name", "*********================" + pNameString);

                    }
                });

            }
        });

    }
}
