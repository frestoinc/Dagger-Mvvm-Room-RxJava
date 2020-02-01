package com.frestoinc.gojekassignment.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.frestoinc.gojekassignment.api.base.BaseViewHolder;
import com.frestoinc.gojekassignment.databinding.ViewholderDataBinding;
import com.frestoinc.gojekassignment.databinding.ViewholderPlaceholderBinding;

/**
 * Created by frestoinc on 01,February,2020 for GoJekAssignment.
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int STATE_LOADED = 1;
  private static final int STATE_LOADING = 0;

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (viewType == STATE_LOADED) {
      return new MainViewHolder(ViewholderDataBinding.inflate(
          LayoutInflater.from(parent.getContext()), parent, false));
    }
    return new LoadingViewHolder(ViewholderPlaceholderBinding.inflate(
        LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof MainViewHolder) {
      ((MainViewHolder) holder).bind(position);
    } else {
      ((LoadingViewHolder) holder).bind(position);
    }
  }

  @Override
  public int getItemViewType(int position) {
    return super.getItemViewType(position);
  }

  @Override
  public int getItemCount() {
    return 0;
  }

  protected class MainViewHolder extends BaseViewHolder implements View.OnClickListener {

    private ViewholderDataBinding binder;

    MainViewHolder(ViewholderDataBinding binding) {
      super(binding.getRoot());
      this.binder = binding;
      binding.getRoot().setOnClickListener(this);
    }

    @Override
    public void bind(int position) {

    }

    @Override
    public void onClick(View v) {

    }
  }

  protected class LoadingViewHolder extends BaseViewHolder {

    private ViewholderPlaceholderBinding binder;

    LoadingViewHolder(ViewholderPlaceholderBinding binding) {
      super(binding.getRoot());
      this.binder = binding;
    }

    @Override
    public void bind(int position) {

    }
  }
}
