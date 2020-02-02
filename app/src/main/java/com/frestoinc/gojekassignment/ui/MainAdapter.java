package com.frestoinc.gojekassignment.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.frestoinc.gojekassignment.api.base.BaseViewHolder;
import com.frestoinc.gojekassignment.data.model.GithubModel;
import com.frestoinc.gojekassignment.databinding.ViewholderDataBinding;
import com.frestoinc.gojekassignment.databinding.ViewholderPlaceholderBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by frestoinc on 01,February,2020 for GoJekAssignment.
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int STATE_LOADING = 0;

  private static final int STATE_LOADED = 1;

  private static final int STATE_EXPANDED = 2;

  private List<GithubModel> source;

  @Inject
  public MainAdapter() {
    source = new ArrayList<>();
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    switch (viewType) {
      case STATE_LOADED:
        return new MainViewHolder(ViewholderDataBinding.inflate(
            LayoutInflater.from(parent.getContext()), parent, false));
      case STATE_LOADING:
        //todo
        return new MainViewHolder(ViewholderDataBinding.inflate(
            LayoutInflater.from(parent.getContext()), parent, false));
      default:
        return new LoadingViewHolder(ViewholderPlaceholderBinding.inflate(
            LayoutInflater.from(parent.getContext()), parent, false));
    }
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof MainViewHolder) {
      ((MainViewHolder) holder).binder.setModel(source.get(position));
    } else {
      ((LoadingViewHolder) holder).binder.setPlaceholder(source.get(position));
    }
  }

  @Override
  public long getItemId(int position) {
    return source.get(position).getId();
  }

  @Override
  public int getItemViewType(int position) {
    /*if (source.get(position).isExpanded()) {
      return STATE_LOADED;
    } else {
      return STATE_LOADING;
    }*/
    return STATE_LOADED;
  }

  @Override
  public int getItemCount() {
    return source.size();
  }

  public void setEmptySource() {
    this.source.clear();
    this.source = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      source.add(new GithubModel());
    }
    notifyDataSetChanged();
  }

  public void setSource(List<GithubModel> list) {
    this.source.clear();
    this.source.addAll(list);
    notifyDataSetChanged();
  }

  protected class MainViewHolder extends BaseViewHolder implements View.OnClickListener {

    private ViewholderDataBinding binder;

    MainViewHolder(ViewholderDataBinding binding) {
      super(binding.getRoot());
      this.binder = binding;
      binder.getRoot().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      if (binder.vexExpandable.isExpanded()) {
        binder.vexExpandable.collapse(true);
      } else {
        binder.vexExpandable.expand(true);
      }
    }
  }

  protected class LoadingViewHolder extends BaseViewHolder {

    private ViewholderPlaceholderBinding binder;

    LoadingViewHolder(ViewholderPlaceholderBinding binding) {
      super(binding.getRoot());
      this.binder = binding;
    }
  }
}
