package com.frestoinc.gojekassignment.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.frestoinc.gojekassignment.BR;
import com.frestoinc.gojekassignment.R;
import com.frestoinc.gojekassignment.api.base.BaseActivity;
import com.frestoinc.gojekassignment.databinding.ActivityMainBinding;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

  @Override
  public int getLayoutId() {
    return R.layout.activity_main;
  }

  @Override
  public MainViewModel getViewModel() {
    return new ViewModelProvider(this, getFactory()).get(MainViewModel.class);
  }

  @Override
  public int getBindingVariable() {
    return BR.mainViewModel;
  }


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initView();
    initObservers();
  }

  private void initView() {
    setSupportActionBar(getViewDataBinding().toolbar.toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayShowTitleEnabled(true);
      getSupportActionBar().setTitle("Trending");
    }
  }

  private void initObservers() {

  }

}

