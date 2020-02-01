package com.frestoinc.gojekassignment.ui;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.frestoinc.gojekassignment.api.base.BaseViewHolder;
import com.frestoinc.gojekassignment.data.GithubModel;
import com.frestoinc.gojekassignment.databinding.ViewholderDataBinding;
import com.frestoinc.gojekassignment.databinding.ViewholderExpandableBinding;
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
      ((MainViewHolder) holder).bind(position);
    } else {
      ((LoadingViewHolder) holder).bind(position);
    }
  }

  @Override
  public int getItemViewType(int position) {
    if (source.get(position).isExpanded()) {
      return STATE_LOADED;
    } else {
      return STATE_LOADING;
    }
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

  private static boolean isStringValid(String string) {
    return string != null && !TextUtils.isEmpty(string);
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
      GithubModel model = source.get(position);

      setText(binder.vhdAuthor, model.getAuthor());
      setText(binder.vhdName, model.getName());

      ViewholderExpandableBinding innerbinding = binder.vhdExpandable;

      setText(innerbinding.vhdLanguage, model.getLanguage());
      if (innerbinding.vhdLanguage.getVisibility() == View.VISIBLE) {
        parseColor(model, innerbinding);
      }

      setText(innerbinding.vhdDescription, model.getDescription());
      setText(innerbinding.vhdStars, String.valueOf(model.getStars()));
      setText(innerbinding.vhdForks, String.valueOf(model.getForks()));
    }

    private void parseColor(GithubModel model, ViewholderExpandableBinding binding) {
      String stringColor =
          model.getLanguageColor() != null ? model.getLanguageColor() : "#FFFFFFFF";
      int color = Color.parseColor(stringColor);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        binding.vhdLanguage.getCompoundDrawablesRelative()[0].setTint(color);
      } else {
        binding.vhdLanguage.getCompoundDrawablesRelative()[0].setColorFilter(
            color, PorterDuff.Mode.SRC_IN);
      }
    }

    private void setText(AppCompatTextView view, String s) {
      if (isStringValid(s)) {
        view.setText(s);
        view.setVisibility(View.VISIBLE);
      } else {
        view.setVisibility(View.GONE);
      }
    }

    @Override
    public void onClick(View v) {
      if (binder.vhdExpandable.vexExpandable.isExpanded()) {
        binder.vhdExpandable.vexExpandable.collapse(true);
      } else {
        binder.vhdExpandable.vexExpandable.expand(true);
      }
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
      //nothing to do here
    }
  }
}
