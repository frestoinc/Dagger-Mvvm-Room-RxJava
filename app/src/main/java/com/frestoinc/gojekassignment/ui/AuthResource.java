package com.frestoinc.gojekassignment.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.frestoinc.gojekassignment.api.network.NetworkState;


/**
 * Created by frestoinc on 09,December,2019 for MailDemo.
 */

public class AuthResource<T> {

    @NonNull
    public final NetworkState status;

    @Nullable
    public final T data;


    public AuthResource(@NonNull NetworkState status, @Nullable T data) {
        this.status = status;
        this.data = data;
    }

    public static <T> AuthResource<T> success(@Nullable T data) {
        return new AuthResource<>(NetworkState.SUCCESS, data);
    }

    public static <T> AuthResource<T> error() {
        return new AuthResource<>(NetworkState.ERROR, null);
    }

    public static <T> AuthResource<T> loading(@Nullable T data) {
        return new AuthResource<>(NetworkState.LOADING, data);
    }

}
