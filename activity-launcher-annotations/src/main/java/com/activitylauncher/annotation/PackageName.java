package com.activitylauncher.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by huangming on 2017/2/20.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PACKAGE)
public @interface PackageName {
}
