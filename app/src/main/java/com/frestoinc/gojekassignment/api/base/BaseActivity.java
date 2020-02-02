package com.frestoinc.gojekassignment.api.base;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.frestoinc.gojekassignment.api.network.ConnectionTool;
import com.frestoinc.gojekassignment.api.network.LoaderUI;
import com.frestoinc.gojekassignment.api.network.NetworkLoader;
import com.frestoinc.gojekassignment.api.network.NetworkReceiver;
import com.frestoinc.gojekassignment.api.network.NetworkState;
import com.frestoinc.gojekassignment.api.view.network.ContentLoadingLayout;
import com.frestoinc.gojekassignment.di.module.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel>
        extends DaggerAppCompatActivity implements NetworkLoader, NetworkReceiver, ContentLoadingLayout.OnRequestRetryListener {

  @Inject
  ViewModelProviderFactory factory;

  private ContentLoadingLayout loadingContainer;

  private ConnectionTool receiver;

  public abstract @LayoutRes
  int getLayoutId();

  public abstract V getViewModel();

  private T viewDataBinding;

  private V viewModel;

  public abstract int getBindingVariable();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    receiver = new ConnectionTool(this);
    setDataBinding();
  }

  @Override
  public LoaderUI getNetworkFrameLayout() {
    return getLoadingContainer();
  }

  @Override
  public Context getContext() {
    return this;
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

  public boolean isNetworkConnected() {
    return ConnectionTool.isNetworkAvailable(getApplicationContext());
  }

  public ViewModelProviderFactory getFactory() {
    return factory;
  }

  public void setLoadingContainer(ContentLoadingLayout loadingContainer) {
    this.loadingContainer = loadingContainer;
  }

  private ContentLoadingLayout getLoadingContainer() {
    if (loadingContainer == null) {
      throw new RuntimeException("No loadingContainer found");
    }
    loadingContainer.setOnRequestRetryListener(this);
    return loadingContainer;
  }

  @Override
  protected void onResume() {
    super.onResume();
    registerReceiver(receiver, getNetworkFilter());
  }

  @Override
  protected void onPause() {
    super.onPause();
    unregisterReceiver(receiver);
  }

  private IntentFilter getNetworkFilter() {
    final IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
    return intentFilter;
  }

  @Override
  public void onBackPressed() {
    if (getNetworkFrameLayout().getState() == NetworkState.ERROR) {
      getNetworkFrameLayout().switchToEmpty();
    } else {
      super.onBackPressed();
    }
  }
}
