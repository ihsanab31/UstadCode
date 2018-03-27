// Generated code from Butter Knife. Do not modify!
package com.sundevs.ihsan.ustadapp.view.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.sundevs.ihsan.ustadapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view2131230909;

  private View view2131230769;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.username = Utils.findRequiredViewAsType(source, R.id.username, "field 'username'", AutoCompleteTextView.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.password, "field 'password'", EditText.class);
    target.mProgressView = Utils.findRequiredView(source, R.id.login_progress, "field 'mProgressView'");
    target.mLoginFormView = Utils.findRequiredView(source, R.id.login_form, "field 'mLoginFormView'");
    target.coordinatorLayout = Utils.findRequiredViewAsType(source, R.id.coordinator_login, "field 'coordinatorLayout'", CoordinatorLayout.class);
    view = Utils.findRequiredView(source, R.id.register, "method 'reg'");
    view2131230909 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.reg();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_login, "method 'log'");
    view2131230769 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.log();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.username = null;
    target.password = null;
    target.mProgressView = null;
    target.mLoginFormView = null;
    target.coordinatorLayout = null;

    view2131230909.setOnClickListener(null);
    view2131230909 = null;
    view2131230769.setOnClickListener(null);
    view2131230769 = null;
  }
}
