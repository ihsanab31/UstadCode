// Generated code from Butter Knife. Do not modify!
package com.sundevs.ihsan.ustadapp.view.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.sundevs.ihsan.ustadapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterActivity_ViewBinding implements Unbinder {
  private RegisterActivity target;

  private View view2131230792;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(final RegisterActivity target, View source) {
    this.target = target;

    View view;
    target.nip = Utils.findRequiredViewAsType(source, R.id.nip, "field 'nip'", AutoCompleteTextView.class);
    target.nama = Utils.findRequiredViewAsType(source, R.id.nama, "field 'nama'", AutoCompleteTextView.class);
    target.ttl = Utils.findRequiredViewAsType(source, R.id.ttl, "field 'ttl'", EditText.class);
    target.jurusan = Utils.findRequiredViewAsType(source, R.id.jurusan, "field 'jurusan'", AutoCompleteTextView.class);
    target.univ = Utils.findRequiredViewAsType(source, R.id.univ, "field 'univ'", AutoCompleteTextView.class);
    target.nohp = Utils.findRequiredViewAsType(source, R.id.nohp, "field 'nohp'", EditText.class);
    target.username = Utils.findRequiredViewAsType(source, R.id.user, "field 'username'", AutoCompleteTextView.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.pass, "field 'password'", EditText.class);
    view = Utils.findRequiredView(source, R.id.daftar, "method 'daftar'");
    view2131230792 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.daftar();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.nip = null;
    target.nama = null;
    target.ttl = null;
    target.jurusan = null;
    target.univ = null;
    target.nohp = null;
    target.username = null;
    target.password = null;

    view2131230792.setOnClickListener(null);
    view2131230792 = null;
  }
}
