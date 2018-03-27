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

public class DetailActivity_ViewBinding implements Unbinder {
  private DetailActivity target;

  private View view2131230763;

  private View view2131230770;

  private View view2131230772;

  private View view2131230824;

  @UiThread
  public DetailActivity_ViewBinding(DetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailActivity_ViewBinding(final DetailActivity target, View source) {
    this.target = target;

    View view;
    target.nama_detail = Utils.findRequiredViewAsType(source, R.id.nama_detail, "field 'nama_detail'", TextView.class);
    target.nohp_detail = Utils.findRequiredViewAsType(source, R.id.nohp_detail, "field 'nohp_detail'", TextView.class);
    target.email_detail = Utils.findRequiredViewAsType(source, R.id.email_detail, "field 'email_detail'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_call, "method 'call'");
    view2131230763 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.call();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_sms, "method 'sms'");
    view2131230770 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sms();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_whatsapp, "method 'whatsapp'");
    view2131230772 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.whatsapp();
      }
    });
    view = Utils.findRequiredView(source, R.id.ic_menu, "method 'kembali'");
    view2131230824 = view;
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
    DetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.nama_detail = null;
    target.nohp_detail = null;
    target.email_detail = null;

    view2131230763.setOnClickListener(null);
    view2131230763 = null;
    view2131230770.setOnClickListener(null);
    view2131230770 = null;
    view2131230772.setOnClickListener(null);
    view2131230772 = null;
    view2131230824.setOnClickListener(null);
    view2131230824 = null;
  }
}
