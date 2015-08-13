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
//    static Kudi kudiInstance = Kudi.newInstance("http://10.42.0.1:8080/wallet/api/v1");
//    static Kudi.Session session = kudiInstance.getSession("877");
    String userProfile;
    TextView textView;
    public static Activity mActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        textView = (TextView) v.findViewById(R.id.textView);

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
        session.userProfile(new Callback<User>() {
            @Override
            public void onFailure(String s, int i) {
                Log.d("error", s + " " + i);

            }

            @Override
            public void onSuccess(final com.rancard.kudi.domain.User user) {

                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        userProfile = "First Name: " + user.getFirstName() + "\n";
                        userProfile = userProfile + "Last Name: " + user.getLastName() + "\n";
                        userProfile = userProfile + "Email: " + user.getEmail() + "\n";
                        userProfile = userProfile + "Country: " + user.getCountry() + "\n";
                        userProfile = userProfile + "Mobile Number: " + user.getMobileNumber();
                        String name = user.getFirstName();
                        textView.setText(userProfile);
                        Log.d("User Name", "*********================" + name);

                    }
                });

            }
        });

    }
}
