package com.frestoinc.gojekassignment.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by frestoinc on 31,January,2020 for GoJekAssignment.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface ActivityScope {

}
