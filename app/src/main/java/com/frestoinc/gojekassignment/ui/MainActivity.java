package com.frestoinc.gojekassignment.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.frestoinc.gojekassignment.BR;
import com.frestoinc.gojekassignment.R;
import com.frestoinc.gojekassignment.api.base.BaseActivity;
import com.frestoinc.gojekassignment.data.GithubModel;
import com.frestoinc.gojekassignment.databinding.ActivityMainBinding;

import javax.inject.Inject;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
    implements View.OnClickListener {

  @Inject
  LinearLayoutManager layoutManager;

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

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menu_name:
        break;
      case R.id.menu_stars:
        break;
      default:
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onClick(View v) {

  }

  private void initView() {
    initToolbar();
    initRecyclerview();
  }

  private void initToolbar() {
    setSupportActionBar(getViewDataBinding().toolbar.toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayShowTitleEnabled(false);
      getViewDataBinding().toolbar.toolbarTitle.setText(getString(R.string.toolbar_title));
    }
  }

  private void initRecyclerview() {
    getViewDataBinding().content.containerRc.setLayoutManager(layoutManager);

  }

  private void initObservers() {
    observeData();
  }

  private void observeData() {
    getViewModel().getSource().observe(this,
        githubModels -> {
          for (GithubModel githubModel : githubModels) {
            Log.e("TAG", "auhtor: " + githubModel.getAuthor());
          }
        });
  }
}

