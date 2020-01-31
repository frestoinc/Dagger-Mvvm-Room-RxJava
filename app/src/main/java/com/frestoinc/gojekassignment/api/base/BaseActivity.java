package com.frestoinc.gojekassignment.api.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.frestoinc.gojekassignment.api.network.LoaderUI;
import com.frestoinc.gojekassignment.api.network.NetworkLoader;
import com.frestoinc.gojekassignment.api.network.NetworkReceiver;
import com.frestoinc.gojekassignment.di.module.ViewModelProviderFactory;
import com.google.gson.Gson;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel>
        extends DaggerAppCompatActivity implements NetworkLoader, NetworkReceiver {

  @Inject
  ViewModelProviderFactory factory;

  @Inject
  Gson gson;

  public abstract @LayoutRes
  int getLayoutId();

  public abstract V getViewModel();

  private T viewDataBinding;

  private V viewModel;

  public abstract int getBindingVariable();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setDataBinding();
  }

  @Override
  public LoaderUI getNetworkFrameLayout() {
    return null;
  }

  @Override
  public void onNetworkStateChanged(boolean connected) {

  }

  private void setDataBinding() {
    viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
    this.viewModel = viewModel == null ? getViewModel() : viewModel;
    viewDataBinding.setVariable(getBindingVariable(), viewModel);
    viewDataBinding.executePendingBindings();
  }

  public T getViewDataBinding() {
    return viewDataBinding;
  }

  public void navigateTo(Class className) {
    Intent intent = new Intent(this, className);
    ActivityCompat.startActivity(this, intent, null);
  }

  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public ViewModelProviderFactory getFactory() {
    return factory;
  }
}
