package com.rancard.fragments;

import android.app.Activity;

import com.rancard.kudi.client.async.Kudi;

/**
 * Created by rancard on 8/13/15.
 */
public interface  MainFragment {
    Kudi kudiInstance = Kudi.newInstance("http://10.42.0.1:8080/wallet/api/v1");
    Kudi.Session session = kudiInstance.getSession("877");
    public Activity mActivity=null;
}
