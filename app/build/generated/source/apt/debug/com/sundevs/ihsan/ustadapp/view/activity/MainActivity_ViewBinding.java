// Generated code from Butter Knife. Do not modify!
package com.sundevs.ihsan.ustadapp.view.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.sundevs.ihsan.ustadapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131230833;

  private View view2131230771;

  private View view2131230764;

  private View view2131230761;

  private View view2131230765;

  private View view2131230766;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_menu, "method 'kembali'");
    view2131230833 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.kembali();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_tentang_app, "method 'tentang'");
    view2131230771 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.tentang();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_cari_ustad, "method 'cari_ustad'");
    view2131230764 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.cari_ustad();
      }
    });
    view = Utils.findRequiredView(source, R.id.bnt_daftar_ustad, "method 'daftar'");
    view2131230761 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.daftar();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_informasi_akun, "method 'infoAkun'");
    view2131230765 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.infoAkun();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_keluar, "method 'keluar'");
    view2131230766 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.keluar();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131230833.setOnClickListener(null);
    view2131230833 = null;
    view2131230771.setOnClickListener(null);
    view2131230771 = null;
    view2131230764.setOnClickListener(null);
    view2131230764 = null;
    view2131230761.setOnClickListener(null);
    view2131230761 = null;
    view2131230765.setOnClickListener(null);
    view2131230765 = null;
    view2131230766.setOnClickListener(null);
    view2131230766 = null;
  }
}
