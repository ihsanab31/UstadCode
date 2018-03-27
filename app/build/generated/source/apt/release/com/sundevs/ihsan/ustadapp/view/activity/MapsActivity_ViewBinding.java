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

public class MapsActivity_ViewBinding implements Unbinder {
  private MapsActivity target;

  private View view2131230824;

  @UiThread
  public MapsActivity_ViewBinding(MapsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MapsActivity_ViewBinding(final MapsActivity target, View source) {
    this.target = target;

    View view;
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
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131230824.setOnClickListener(null);
    view2131230824 = null;
  }
}
