package com.sundevs.ihsan.ustadapp.view.baseconfig;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * base class for implement fragment
 * <p>You create a subclass implementing {@link #getFragmentLayout()}
 * this method for define which layout you want set into fragment,
 * and {@link #onBindView()} this method for handle between view and data
 *
 * @author Andrian.Lippox
 * @version 1.0.0
 * @since 8/26/17
 */

public abstract class BaseFragment extends Fragment {
    /**
     * @return layout resource of fragment
     */
    @LayoutRes
    protected abstract int getFragmentLayout();

    /**
     * this method for handle between view and data
     * for example you want fill string into textView, you should add on this method
     * and also if you want retrieve data from api, call in this method
     */
    protected abstract void onBindView();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onBindView();
    }
}
