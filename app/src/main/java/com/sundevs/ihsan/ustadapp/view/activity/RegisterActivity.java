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
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends NormalActivity {
    @BindView(R.id.nip)
    AutoCompleteTextView nip;
    @BindView(R.id.nama)
    AutoCompleteTextView nama;
    @BindView(R.id.ttl)
    EditText ttl;
    @BindView(R.id.jurusan)
    AutoCompleteTextView jurusan;
    @BindView(R.id.univ)
    AutoCompleteTextView univ;
    @BindView(R.id.nohp)
    EditText nohp;
    @BindView(R.id.user)
    AutoCompleteTextView username;
    @BindView(R.id.pass)
    EditText password;
    private static final String TAG1 = RegisterActivity.class.getSimpleName();
    @Override
    protected int getActivityView() {
            return R.layout.activity_register;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.daftar)
    void daftar(){
        attemptLogin();
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }

    private void attemptLogin() {

        // Reset errors.
        username.setError(null);
        password.setError(null);

        // Store values at the time of the login attempt.
        String email = username.getText().toString();
        String pass = password.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(pass) && !isPasswordValid(pass)) {
            password.setError(getString(R.string.error_invalid_password));
            focusView = password;
            cancel = true;
        }

        // Check for a valid email address.
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
         register();

        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.length() > 6;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 6;
    }

    private void register() {
        final ProgressDialog loading = new ProgressDialog(this);
        loading.setMessage("Proses Registrasi");
        loading.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL.URL_REGISTER,
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
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
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
                params.put(Params.nip, nip.getText().toString());
                params.put(Params.nama, nama.getText().toString());
                params.put(Params.ttl, ttl.getText().toString());
                params.put(Params.jutusan, jurusan.getText().toString());
                params.put(Params.univ, univ.getText().toString());
                params.put(Params.tlpn, nohp.getText().toString());
                params.put(Params.username, username.getText().toString());
                params.put(Params.password, password.getText().toString());

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
        univ.setText("");
        nohp.setText("");
        username.setText("");
        password.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
