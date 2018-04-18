package com.sundevs.ihsan.ustadapp.view.activity;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sundevs.ihsan.ustadapp.App;
import com.sundevs.ihsan.ustadapp.R;
import com.sundevs.ihsan.ustadapp.util.param.Params;
import com.sundevs.ihsan.ustadapp.util.param.URL;
import com.sundevs.ihsan.ustadapp.util.pref.SessionManager;
import com.sundevs.ihsan.ustadapp.view.baseconfig.NormalActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends NormalActivity {
    @BindView(R.id.username)
    AutoCompleteTextView username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login_progress)
    View mProgressView;
    @BindView(R.id.login_form)
    View mLoginFormView;
    @BindView(R.id.coordinator_login)
    CoordinatorLayout coordinatorLayout;
    SharedPreferences preference;
    SessionManager session;
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected int getActivityView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

    }

    @OnClick(R.id.register)
    void reg() {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }

    @OnClick(R.id.btn_login)
    void log() {
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
            login(username.getText().toString(), password.getText().toString());
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.length() > 5;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 6;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private void login(final String username, final String password_login) {
        showProgress(true);
        StringRequest strReq = new StringRequest(Request.Method.POST, URL.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());
                try {
                    JSONObject jObj = new JSONObject(response);
                    Params.success = jObj.getInt(Params.TAG_SUCCESS);

                    // Cek error node pada json
                    if (Params.success == 1) {
                        Log.d("get edit data", jObj.toString());
                        String nip = jObj.getString(Params.nip);
                        String nama = jObj.getString(Params.nama);
                        String ttl = jObj.getString(Params.ttl);
                        String jurusan = jObj.getString(Params.jutusan);
                        String univ = jObj.getString(Params.univ);
                        String tlpn = jObj.getString(Params.tlpn);
                        String username = jObj.getString(Params.username);
                        String password = jObj.getString(Params.password);
                        preference =
                                getApplicationContext().getSharedPreferences("data", 0);
                        SharedPreferences.Editor editor = preference.edit();
                        editor.putString("nip", nip);
                        editor.putString("nama", nama);
                        editor.putString("ttl", ttl);
                        editor.putString("jurusan", jurusan);
                        editor.putString("univ", univ);
                        editor.putString("tlpn", tlpn);
                        editor.putString("username", username);
                        editor.putString("password", password);
                        editor.commit();
                        showProgress(false);
                        session.createLoginSession(username, password_login);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        loginadmin(username, password_login);

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError instanceof NetworkError) {
                    Snackbar snacka = Snackbar.make(coordinatorLayout, R.string.networkerror, Snackbar.LENGTH_LONG);
                    snacka.show();
                    showProgress(false);
                } else if (volleyError instanceof ServerError) {
                    Snackbar snackb = Snackbar.make(coordinatorLayout, R.string.ServerError, Snackbar.LENGTH_LONG);
                    snackb.show();
                    showProgress(false);
                } else if (volleyError instanceof AuthFailureError) {
                    Snackbar snackc = Snackbar.make(coordinatorLayout, R.string.AuthFailureError, Snackbar.LENGTH_LONG);
                    snackc.show();
                    showProgress(false);
                } else if (volleyError instanceof ParseError) {
                    Snackbar snackd = Snackbar.make(coordinatorLayout, R.string.ParseError, Snackbar.LENGTH_LONG);
                    snackd.show();
                    showProgress(false);
                } else if (volleyError instanceof NoConnectionError) {
                    Snackbar snacke = Snackbar.make(coordinatorLayout, R.string.NoConnectionError, Snackbar.LENGTH_LONG);
                    snacke.show();
                    showProgress(false);
                } else if (volleyError instanceof TimeoutError) {
                    Snackbar snackf = Snackbar.make(coordinatorLayout, R.string.TimeoutError, Snackbar.LENGTH_LONG);
                    snackf.show();
                    showProgress(false);
                }
                showProgress(false);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put(Params.username, username);
                params.put(Params.password, password_login);
                return params;
            }

        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                120000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        App.getInstance().addToRequestQueue(strReq, Params.tag_json_obj);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void loginadmin(final String username, final String password_login) {
        showProgress(true);
        StringRequest strReq = new StringRequest(Request.Method.POST, URL.URL_LOGIN_ADMIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());
                try {
                    JSONObject jObj = new JSONObject(response);
                    Params.success = jObj.getInt(Params.TAG_SUCCESS);

                    if (Params.success == 1) {
                        Log.d("get edit data", jObj.toString());
                        session.createLoginSession(username, password_login);
                        startActivity(new Intent(getApplicationContext(), MenuAdminActivity.class));
                    } else {
                        Snackbar snacka = Snackbar.make(coordinatorLayout, jObj.getString(Params.TAG_MESSAGE), Snackbar.LENGTH_LONG);
                        snacka.show();
                        showProgress(false);
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError instanceof NetworkError) {
                    Snackbar snacka = Snackbar.make(coordinatorLayout, R.string.networkerror, Snackbar.LENGTH_LONG);
                    snacka.show();
                    showProgress(false);
                } else if (volleyError instanceof ServerError) {
                    Snackbar snackb = Snackbar.make(coordinatorLayout, R.string.ServerError, Snackbar.LENGTH_LONG);
                    snackb.show();
                    showProgress(false);
                } else if (volleyError instanceof AuthFailureError) {
                    Snackbar snackc = Snackbar.make(coordinatorLayout, R.string.AuthFailureError, Snackbar.LENGTH_LONG);
                    snackc.show();
                    showProgress(false);
                } else if (volleyError instanceof ParseError) {
                    Snackbar snackd = Snackbar.make(coordinatorLayout, R.string.ParseError, Snackbar.LENGTH_LONG);
                    snackd.show();
                    showProgress(false);
                } else if (volleyError instanceof NoConnectionError) {
                    Snackbar snacke = Snackbar.make(coordinatorLayout, R.string.NoConnectionError, Snackbar.LENGTH_LONG);
                    snacke.show();
                    showProgress(false);
                } else if (volleyError instanceof TimeoutError) {
                    Snackbar snackf = Snackbar.make(coordinatorLayout, R.string.TimeoutError, Snackbar.LENGTH_LONG);
                    snackf.show();
                    showProgress(false);
                }
                showProgress(false);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put(Params.username, username);
                params.put(Params.password, password_login);
                return params;
            }

        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                120000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        App.getInstance().addToRequestQueue(strReq, Params.tag_json_obj);
    }

    public void initData() {
        String username;
        preference = getApplicationContext().getSharedPreferences("BelajarPref", 0);
        username = preference.getString("username", null);
        session = new SessionManager(getApplicationContext());
        if (session.isLoggedIn()) {
            if (username.equals("mizwar")) {
                Intent intent = new Intent(getApplicationContext(), MenuAdminActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }

    }

}

