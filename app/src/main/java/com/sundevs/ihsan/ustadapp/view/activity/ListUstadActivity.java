package com.sundevs.ihsan.ustadapp.view.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.sundevs.ihsan.ustadapp.App;
import com.sundevs.ihsan.ustadapp.R;
import com.sundevs.ihsan.ustadapp.adapter.UstadAdapter;
import com.sundevs.ihsan.ustadapp.adapter.model.UstadModel;
import com.sundevs.ihsan.ustadapp.util.param.Params;
import com.sundevs.ihsan.ustadapp.util.param.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListUstadActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = ListUstadActivity.class.getSimpleName();
    @BindView(R.id.list_data_ustad)
    RecyclerView mRecyclerView;
    public List<UstadModel> contactList;
    private UstadAdapter mAdapter;
    @BindView(R.id.swipe_histor)
    SwipeRefreshLayout swipe;
    @BindView(R.id.search)
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ustad);
        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactList = new ArrayList<>();
        mAdapter = new UstadAdapter(contactList, this);
        mRecyclerView.setAdapter(mAdapter);
        swipe.setOnRefreshListener(this);
        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(false);
                contactList.clear();
                callVolley();

            }
        });
        addTextListener();
    }

    private void callVolley() {
        contactList.clear();
        mAdapter.notifyDataSetChanged();
        final ProgressDialog loading = new ProgressDialog(this);
        loading.setMessage("Please Wait...");
        loading.show();
        JsonArrayRequest jArr = new JsonArrayRequest(URL.URL_JARAK, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        UstadModel item = new UstadModel();
                        item.setNama(obj.getString(Params.nama));
                        item.setAlamat(obj.getString(Params.alamat));
                        item.setBidang(obj.getString(Params.bidang));
                        item.setEmail(obj.getString(Params.email));
                        item.setNohp(obj.getString(Params.no_hp));
                        item.setHarga(obj.getInt(Params.harga));
                        item.setId(obj.getString(Params.id_ustad));
                        // menambah item ke array
                        contactList.add(item);
                        swipe.setRefreshing(false);
                        loading.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        App.getInstance().addToRequestQueue(jArr);
    }

    @Override
    public void onRefresh() {
        contactList.clear();
        callVolley();
    }

    public void addTextListener() {

        search.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();

                final List<UstadModel> filteredList = new ArrayList<>();

                for (int i = 0; i < contactList.size(); i++) {

                    final String bidang = contactList.get(i).getBidang().toLowerCase();
                    final String nama = contactList.get(i).getNama().toLowerCase();
                    final String alamat = contactList.get(i).getAlamat().toLowerCase();
                    if (bidang.contains(query)) {

                        filteredList.add(contactList.get(i));
                    } else if (nama.contains(query)) {

                        filteredList.add(contactList.get(i));
                    } else if (alamat.contains(query)) {

                        filteredList.add(contactList.get(i));
                    }
                }

                mRecyclerView.setLayoutManager(new LinearLayoutManager(ListUstadActivity.this));
                mAdapter = new UstadAdapter(filteredList, ListUstadActivity.this);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();  // data set changed
            }
        });
    }

    @OnClick(R.id.ic_menu)
    void kembali() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

}
