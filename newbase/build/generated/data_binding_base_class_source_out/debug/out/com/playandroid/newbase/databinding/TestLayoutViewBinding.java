// Generated by view binder compiler. Do not edit!
package com.playandroid.newbase.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.playandroid.newbase.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class TestLayoutViewBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView tvTextView;

  private TestLayoutViewBinding(@NonNull RelativeLayout rootView, @NonNull TextView tvTextView) {
    this.rootView = rootView;
    this.tvTextView = tvTextView;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static TestLayoutViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static TestLayoutViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.test_layout_view, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static TestLayoutViewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    String missingId;
    missingId: {
      TextView tvTextView = rootView.findViewById(R.id.tv_text_view);
      if (tvTextView == null) {
        missingId = "tvTextView";
        break missingId;
      }
      return new TestLayoutViewBinding((RelativeLayout) rootView, tvTextView);
    }
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}