package com.frestoinc.gojekassignment.api.view.network;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.frestoinc.gojekassignment.R;
import com.frestoinc.gojekassignment.api.network.LoaderUI;
import com.frestoinc.gojekassignment.api.network.NetworkState;

/**
 * Created by frestoinc on 02,February,2020 for GoJekAssignment.
 */
public class ContentLoadingLayout extends RelativeLayout implements View.OnClickListener, LoaderUI {

    private View errorView;
    private NetworkState state = NetworkState.SUCCESS;
    private OnRequestRetryListener listener;

    public ContentLoadingLayout(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Content loading layout .
     * {@link com.frestoinc.gojekassignment.api.base.BaseActivity} will call here
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public ContentLoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void switchToError() {
        state = NetworkState.ERROR;
        switchToErrorView();
    }

    @Override
    public void switchToEmpty() {
        state = NetworkState.SUCCESS;
        switchToEmptyView();
    }

    @Override
    public NetworkState getState() {
        return state;
    }

    private void switchToErrorView() {
        if (errorView == null) {
            errorView = LayoutInflater.from(getContext()).inflate(R.layout.layout_error, this, false);
            addView(errorView);
            findViewById(R.id.error_btn).setOnClickListener(this);
        } else {
            errorView.setVisibility(View.VISIBLE);
        }
    }

    private void switchToEmptyView() {
        if (errorView != null) {
            errorView.setVisibility(View.GONE);
        }
    }

    public void setOnRequestRetryListener(OnRequestRetryListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onRequestRetry();
        }
    }

    public interface OnRequestRetryListener {

        void onRequestRetry();
    }
}
