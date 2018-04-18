package com.sundevs.ihsan.ustadapp.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sundevs.ihsan.ustadapp.App;
import com.sundevs.ihsan.ustadapp.R;
import com.sundevs.ihsan.ustadapp.util.param.Params;
import com.sundevs.ihsan.ustadapp.util.param.URL;
import com.sundevs.ihsan.ustadapp.view.baseconfig.NormalActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class InputUstadActivity extends NormalActivity {
    private static final String TAG1 = InputUstadActivity.class.getSimpleName();
    @BindView(R.id.nip)
    AutoCompleteTextView nip;
    @BindView(R.id.nama)
    AutoCompleteTextView nama;
    @BindView(R.id.bid)
    EditText ttl;
    @BindView(R.id.jurusan)
    AutoCompleteTextView jurusan;
    @BindView(R.id.nohp)
    EditText nohp;
    @BindView(R.id.user)
    AutoCompleteTextView username;
    String id_ustad;
    @Override
    protected int getActivityView() {
        return R.layout.activity_input_ustad;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id_ustad = getIntent().getStringExtra("id_ustad");
        if (id_ustad ==null){
            Toast.makeText(getApplicationContext(), "Masukkan Data Ustad", Toast.LENGTH_SHORT).show();
        }else {
            initData();
        }
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }

    @OnClick(R.id.daftar)
    void daftar() {
        attemptLogin();
    }


    private void attemptLogin() {

        // Reset errors.
        username.setError(null);

        // Store values at the time of the login attempt.
        String email = username.getText().toString();

        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(email)) {
            username.setError(getString(R.string.error_field_required));
            focusView = username;
            cancel = true;
        } else if (!isEmailValid(email)) {
            username.setError(getString(R.string.error_invalid_email));
            focusView = username;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();

        } else {
            if (id_ustad == null) {
                register();

            }else {
                editregister();
            }
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.length() > 6;
    }


    private void register() {
        final ProgressDialog loading = new ProgressDialog(this);
        loading.setMessage("Proses Registrasi");
        loading.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.URL_REGISTER_USTAD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG1, "Response: " + response.toString());
                        try {
                            JSONObject jObj = new JSONObject(response);
                            Params.success = jObj.getInt(Params.TAG_SUCCESS);
                            if (Params.success == 1) {
                                Log.d("v Add", jObj.toString());
                                kosong();
                                Toast.makeText(getApplicationContext(), getString(R.string.sukses), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MenuAdminActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            } else {
                                loading.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        loading.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        Log.d(TAG1, error.getMessage().toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_ustad", String.valueOf(0));
                params.put("nama", nip.getText().toString());
                params.put("harga", nama.getText().toString());
                params.put("bidang", ttl.getText().toString());
                params.put("alamat", jurusan.getText().toString());
                params.put("no_hp", nohp.getText().toString());
                params.put("email", username.getText().toString());
                Log.d(TAG1, "" + params);
                return params;
            }
        };

        App.getInstance().addToRequestQueue(stringRequest, Params.tag_json_obj);
    }

    public void kosong() {
        nip.setText("");
        nama.setText("");
        ttl.setText("");
        jurusan.setText("");
        nohp.setText("");
        username.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initData(){
        nip.setText(getIntent().getStringExtra("nama"));
        nama.setText(getIntent().getStringExtra("harga"));
        ttl.setText(getIntent().getStringExtra("bidang"));
        jurusan.setText(getIntent().getStringExtra("alamat"));
        nohp.setText(getIntent().getStringExtra("no_hp"));
        username.setText(getIntent().getStringExtra("email"));
    }
    private void editregister() {
        final ProgressDialog loading = new ProgressDialog(this);
        loading.setMessage("Proses Registrasi");
        loading.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.URL_EDIT_USTAD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG1, "Response: " + response.toString());
                        try {
                            JSONObject jObj = new JSONObject(response);
                            Params.success = jObj.getInt(Params.TAG_SUCCESS);
                            if (Params.success == 1) {
                                Log.d("v Add", jObj.toString());
                                kosong();
                                Toast.makeText(getApplicationContext(), getString(R.string.sukses), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MenuAdminActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            } else {
                                loading.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        loading.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        Log.d(TAG1, error.getMessage().toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_ustad", id_ustad);
                params.put("nama", nip.getText().toString());
                params.put("harga", nama.getText().toString());
                params.put("bidang", ttl.getText().toString());
                params.put("alamat", jurusan.getText().toString());
                params.put("no_hp", nohp.getText().toString());
                params.put("email", username.getText().toString());
                Log.d(TAG1, "" + params);
                return params;
            }
        };

        App.getInstance().addToRequestQueue(stringRequest, Params.tag_json_obj);
    }

}

