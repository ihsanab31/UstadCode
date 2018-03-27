package com.sundevs.ihsan.ustadapp;

import android.support.annotation.StringRes;

/**
 * Created by iihsa on 12/01/2018.
 */

public interface BaseView {

    void showProgressDialog(String message);

    void showProgressDialog(@StringRes int message);

    void dismissProgressDialog();

    void showMessage(String message);

    void showMessage(@StringRes int message);
}
