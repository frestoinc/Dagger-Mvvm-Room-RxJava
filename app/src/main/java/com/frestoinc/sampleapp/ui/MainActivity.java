package com.frestoinc.sampleapp.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.frestoinc.sampleapp.R;
import com.frestoinc.sampleapp.api.base.BaseActivity;
import com.frestoinc.sampleapp.api.view.network.ContentLoadingLayout;
import com.frestoinc.sampleapp.databinding.ActivityMainBinding;

import javax.inject.Inject;

/**
 * Created by frestoinc on 31,January,2020 for SampleApp.
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements ContentLoadingLayout.OnRequestRetryListener {

    @Inject
    LinearLayoutManager layoutManager;

    @Inject
    DividerItemDecoration decoration;

    @Inject
    MainAdapter adapter;

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
        return com.frestoinc.sampleapp.BR.mainViewModel;
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
                adapter.setSortedSource(true);
                return true;
            case R.id.menu_stars:
                adapter.setSortedSource(false);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initView() {
        initToolbar();
        initRecyclerview();
        initRefreshLayout();
        setLoadingContainer(getViewDataBinding().loadingContainer);
    }

    private void initToolbar() {
        Toolbar toolbar = getViewDataBinding().toolbar.customToolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        getViewDataBinding().toolbar.toolbarTitle.setText(getString(R.string.toolbar_title));
    }

    private void initRecyclerview() {
        adapter.setHasStableIds(true);
        getViewDataBinding().content.containerRc.setHasFixedSize(true);
        getViewDataBinding().content.containerRc.setLayoutManager(layoutManager);
        getViewDataBinding().content.containerRc.addItemDecoration(decoration);
        getViewDataBinding().content.containerRc.setAdapter(adapter);
        getViewDataBinding().content.containerRc.setItemViewCacheSize(10);
    }

    private void initRefreshLayout() {
        getViewDataBinding().content.container.setOnRefreshListener(
                () -> getViewModel().getOnlineRepo());
    }

    private void initObservers() {
        observeData();
    }

    private void observeData() {
        getViewModel().getSource().observe(this, listAuthResource -> {
            if (listAuthResource != null) {
                switch (listAuthResource.status) {
                    case LOADING:
                        adapter.setEmptySource();
                        getNetworkFrameLayout().switchToEmpty();
                        break;
                    case SUCCESS:
                        adapter.setSource(listAuthResource.data);
                        removeSwipeRefreshing();
                        getNetworkFrameLayout().switchToEmpty();
                        break;
                    case ERROR:
                        removeSwipeRefreshing();
                        getNetworkFrameLayout().switchToError();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void removeSwipeRefreshing() {
        if (getViewDataBinding().content.container.isRefreshing()) {
            getViewDataBinding().content.container.setRefreshing(false);
        }
    }

    @Override
    public void onRequestRetry() {
        getViewModel().getOnlineRepo();
    }
}

