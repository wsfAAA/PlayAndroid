// Generated by view binder compiler. Do not edit!
package com.playandroid.newbase.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.playandroid.newbase.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FratmentTestLayoutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView tvFragmentText;

  private FratmentTestLayoutBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView tvFragmentText) {
    this.rootView = rootView;
    this.tvFragmentText = tvFragmentText;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FratmentTestLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FratmentTestLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fratment_test_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FratmentTestLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    String missingId;
    missingId: {
      TextView tvFragmentText = rootView.findViewById(R.id.tv_fragment_text);
      if (tvFragmentText == null) {
        missingId = "tvFragmentText";
        break missingId;
      }
      return new FratmentTestLayoutBinding((ConstraintLayout) rootView, tvFragmentText);
    }
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}