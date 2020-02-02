package com.frestoinc.gojekassignment.ui;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.frestoinc.gojekassignment.api.base.BaseViewHolder;
import com.frestoinc.gojekassignment.api.network.NetworkState;
import com.frestoinc.gojekassignment.data.model.GithubModel;
import com.frestoinc.gojekassignment.databinding.ViewholderDataBinding;
import com.frestoinc.gojekassignment.databinding.ViewholderPlaceholderBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by frestoinc on 01,February,2020 for GoJekAssignment.
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int STATE_LOADING = 0;

  private static final int STATE_LOADED = 1;

  private AuthResource<List<GithubModel>> source;

  @Inject
  public MainAdapter() {
    source = AuthResource.loading(new ArrayList<>());
  }

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
    if (source.data != null) {
      if (holder instanceof MainViewHolder) {
        ((MainViewHolder) holder).binder.setModel(source.data.get(position));
      } else {
        ((LoadingViewHolder) holder).binder.setPlaceholder(source.data.get(position));
      }
    }
  }

  @Override
  public long getItemId(int position) {
    if (source.data != null) {
      return source.data.get(position).getId();
    }
    return 0;
  }

  @Override
  public int getItemViewType(int position) {
    if (source.status == NetworkState.LOADING) {
      return STATE_LOADING;
    }
    return STATE_LOADED;
  }

  @Override
  public int getItemCount() {
    if (source.data != null) {
      return source.data.size();
    }
    return 0;
  }

  public void setEmptySource() {
    this.source.data.clear();
    List<GithubModel> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(new GithubModel());
    }
    this.source = AuthResource.loading(list);
    notifyDataSetChanged();
  }

  public void setSource(List<GithubModel> list) {
    this.source.data.clear();
    this.source = AuthResource.success(list);
    Timber.e("source size: %s", source.data.size());
    Timber.e("list size: %s", list.size());
    notifyDataSetChanged();
  }

  void setSortedSource(boolean isName) {
    if (source.data == null || source.data.isEmpty()) {
      return;
    }

    Comparator<GithubModel> comparator = (o1, o2) -> {
      if (isName) {
        return o1.getName().compareToIgnoreCase(o2.getName());
      }
      return o2.getStars() - o1.getStars();
    };

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      source.data.sort(comparator);
    } else {
      Collections.sort(source.data, comparator);
    }

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
