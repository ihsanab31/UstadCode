package com.sundevs.ihsan.ustadapp.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.sundevs.ihsan.ustadapp.R;
import com.sundevs.ihsan.ustadapp.util.pref.SessionManager;
import com.sundevs.ihsan.ustadapp.view.baseconfig.NormalActivity;

import butterknife.OnClick;

public class MainActivity extends NormalActivity {
    SessionManager session;

    @Override
    protected int getActivityView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        if (!session.isLoggedIn()) {
            logoutUser();
        }
    }
    @OnClick(R.id.iv_menu)
    void kembali (){
        logoutUser();
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }

    private void logoutUser() {
        session.logoutUser();
        // Launching the login activity
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick(R.id.btn_tentang_app)
    void tentang() {
        startActivity(new Intent(getApplicationContext(), AboutActivity.class));
    }

    @OnClick(R.id.btn_cari_ustad)
    void cari_ustad() {
        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
    }

    @OnClick(R.id.bnt_daftar_ustad)
    void daftar() {
        startActivity(new Intent(getApplicationContext(), ListUstadActivity.class));
    }

    @OnClick(R.id.btn_informasi_akun)
    void infoAkun() {
        startActivity(new Intent(getApplicationContext(), ProfileAkunActivity.class));
    }

    @OnClick(R.id.btn_keluar)
    void keluar() {
        finish();
        System.exit(0);
    }

}
