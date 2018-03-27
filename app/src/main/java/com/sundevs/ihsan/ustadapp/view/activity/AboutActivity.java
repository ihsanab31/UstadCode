package com.sundevs.ihsan.ustadapp.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.sundevs.ihsan.ustadapp.R;
import com.sundevs.ihsan.ustadapp.view.baseconfig.NormalActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends NormalActivity{

    @Override
    protected int getActivityView() {
        return R.layout.activity_about;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_kembali)
    void kembali(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }
}
