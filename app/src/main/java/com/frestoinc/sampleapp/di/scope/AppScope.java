package com.frestoinc.sampleapp.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Replacement scope for {@link javax.inject.Singleton}
 * Created by frestoinc on 31,January,2020 for SampleApp.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface AppScope {
}
