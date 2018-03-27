package com.sundevs.ihsan.ustadapp.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.sundevs.ihsan.ustadapp.R;
import com.sundevs.ihsan.ustadapp.util.param.Params;
import com.sundevs.ihsan.ustadapp.view.baseconfig.NormalActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends NormalActivity {
    public static String id__ustad, nama, no_hp, email;
    @BindView(R.id.nama_detail)
    TextView nama_detail;
    @BindView(R.id.nohp_detail)
    TextView nohp_detail;
    @BindView(R.id.email_detail)
    TextView email_detail;
    @Override
    protected int getActivityView() {
        return R.layout.activity_detail;
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

    @SuppressLint("SetTextI18n")
    public void initData(){
        id__ustad= getBundle().getString(Params.id_ustad);
        nama = getBundle().getString(Params.nama);
        no_hp= getBundle().getString(Params.no_hp);
        email = getBundle().getString(Params.email);
        nama_detail.setText(": "+nama);
        nohp_detail.setText(": "+no_hp);
        email_detail.setText(": "+email);
    }
    @OnClick(R.id.btn_call)
    void call(){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", no_hp, null));
        startActivity(intent);
    }
    @OnClick(R.id.btn_sms)
    void sms (){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", no_hp, null)));
    }

    @OnClick(R.id.btn_whatsapp)
    void whatsapp(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Assalamu'alaikum Pak Ustad");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
    @OnClick(R.id.ic_menu)
    void kembali (){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
