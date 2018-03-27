// Generated code from Butter Knife. Do not modify!
package com.sundevs.ihsan.ustadapp.view.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.sundevs.ihsan.ustadapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ListUstadActivity_ViewBinding implements Unbinder {
  private ListUstadActivity target;

  private View view2131230824;

  @UiThread
  public ListUstadActivity_ViewBinding(ListUstadActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ListUstadActivity_ViewBinding(final ListUstadActivity target, View source) {
    this.target = target;

    View view;
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.list_data_ustad, "field 'mRecyclerView'", RecyclerView.class);
    target.swipe = Utils.findRequiredViewAsType(source, R.id.swipe_histor, "field 'swipe'", SwipeRefreshLayout.class);
    target.search = Utils.findRequiredViewAsType(source, R.id.search, "field 'search'", EditText.class);
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
    ListUstadActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerView = null;
    target.swipe = null;
    target.search = null;

    view2131230824.setOnClickListener(null);
    view2131230824 = null;
  }
}
