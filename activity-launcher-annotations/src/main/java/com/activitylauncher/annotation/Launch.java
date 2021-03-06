package com.activitylauncher.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by huangming on 2017/2/17.
 */

@Retention(RUNTIME)
@Target(TYPE)
@Documented
public @interface Launch {

    Parameter[] parameters();

    int requestCode() default 0;

}
