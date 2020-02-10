package com.frestoinc.sampleapp.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.frestoinc.sampleapp.api.network.NetworkState;


/**
 * Created by frestoinc on 09,December,2019 for MailDemo.
 *
 * @param <T> the type parameter
 */
public class AuthResource<T> {

    @NonNull
    public final NetworkState status;

    @Nullable
    public final T data;

    @Nullable
    private final String msg;

    /**
     * Instantiates a new Auth resource.
     *
     * @param status the status
     * @param data   the data
     * @param msg    the msg
     */
    private AuthResource(@NonNull NetworkState status, @Nullable T data, @Nullable String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    /**
     * Success auth resource.
     *
     * @param <T>  the type parameter
     * @param data the data
     * @return the auth resource
     */
    static <T> AuthResource<T> success(@Nullable T data) {
        return new AuthResource<>(NetworkState.SUCCESS, data, null);
    }

    /**
     * Error auth resource.
     *
     * @param <T> the type parameter
     * @param msg the msg
     * @return the auth resource
     */
    public static <T> AuthResource<T> error(@Nullable String msg) {
        return new AuthResource<>(NetworkState.ERROR, null, msg);
    }

    /**
     * Loading auth resource.
     *
     * @param <T>  the type parameter
     * @param data the data
     * @return the auth resource
     */
    static <T> AuthResource<T> loading(@Nullable T data) {
        return new AuthResource<>(NetworkState.LOADING, data, null);
    }

}
