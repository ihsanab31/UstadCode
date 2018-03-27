// Generated code from Butter Knife. Do not modify!
package com.sundevs.ihsan.ustadapp.view.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.sundevs.ihsan.ustadapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProfileAkunActivity_ViewBinding implements Unbinder {
  private ProfileAkunActivity target;

  private View view2131230768;

  @UiThread
  public ProfileAkunActivity_ViewBinding(ProfileAkunActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ProfileAkunActivity_ViewBinding(final ProfileAkunActivity target, View source) {
    this.target = target;

    View view;
    target.nama = Utils.findRequiredViewAsType(source, R.id.nama_akun, "field 'nama'", TextView.class);
    target.no_hp = Utils.findRequiredViewAsType(source, R.id.nohp_akun, "field 'no_hp'", TextView.class);
    target.jurusan = Utils.findRequiredViewAsType(source, R.id.jurusan_akun, "field 'jurusan'", TextView.class);
    target.univ = Utils.findRequiredViewAsType(source, R.id.univ_akun, "field 'univ'", TextView.class);
    target.ttl = Utils.findRequiredViewAsType(source, R.id.tgllahir_akun, "field 'ttl'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_kembali_akun, "method 'kembali'");
    view2131230768 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.kembali();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ProfileAkunActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.nama = null;
    target.no_hp = null;
    target.jurusan = null;
    target.univ = null;
    target.ttl = null;

    view2131230768.setOnClickListener(null);
    view2131230768 = null;
  }
}
