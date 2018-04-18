package com.sundevs.ihsan.ustadapp.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.sundevs.ihsan.ustadapp.R;
import com.sundevs.ihsan.ustadapp.util.pref.SessionManager;
import com.sundevs.ihsan.ustadapp.view.baseconfig.NormalActivity;
import butterknife.OnClick;

public class MenuAdminActivity extends NormalActivity {
    SessionManager session;

    @Override
    protected int getActivityView() {
        return R.layout.activity_menu_admin;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        if (!session.isLoggedIn()) {
            logoutUser();
        }
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }

    @OnClick(R.id.btn_edit)
    void edit(){
        startActivity(new Intent(getApplicationContext(), ListEditActivity.class));
    }
    @OnClick(R.id.btn_input)
    void input(){
        startActivity(new Intent(getApplicationContext(), InputUstadActivity.class));
    }

    @OnClick(R.id.btn_keluar_ustad)
    void keluar() {
        finish();
        System.exit(0);
    }


    private void logoutUser() {
        session.logoutUser();
        // Launching the login activity
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @OnClick(R.id.iv_menu)
    void kembali (){
        logoutUser();
    }

}
