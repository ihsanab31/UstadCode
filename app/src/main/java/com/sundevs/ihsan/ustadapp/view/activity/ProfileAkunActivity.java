package com.sundevs.ihsan.ustadapp.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.sundevs.ihsan.ustadapp.R;
import com.sundevs.ihsan.ustadapp.view.baseconfig.NormalActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileAkunActivity extends NormalActivity {
    @BindView(R.id.nama_akun)
    TextView nama;
    @BindView(R.id.nohp_akun)
    TextView no_hp;
    @BindView(R.id.jurusan_akun)
    TextView jurusan;
    @BindView(R.id.univ_akun)
    TextView univ;
    @BindView(R.id.tgllahir_akun)
    TextView ttl;
    SharedPreferences preference;
    String namaku = "", no_hpku = "", jurusanku = "", univku = "", ttlku = "";

    @Override
    protected int getActivityView() {
        return R.layout.activity_profile_akun;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }

    public void initData() {
        preference = getApplicationContext().getSharedPreferences("data", 0);
        namaku = preference.getString("nama", null);
        no_hpku = preference.getString("tlpn", null);
        ttlku = preference.getString("ttl", null);
        jurusanku = preference.getString("jurusan", null);
        univku = preference.getString("univ", null);
        nama.setText(namaku);
        no_hp.setText(no_hpku);
        ttl.setText(ttlku);
        jurusan.setText(jurusanku);
        univ.setText(univku);
    }

    @OnClick(R.id.btn_kembali_akun)
    void kembali(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
