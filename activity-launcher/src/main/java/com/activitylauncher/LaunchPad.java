package com.activitylauncher;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.activitylauncher.annotation.Launch;
import com.activitylauncher.annotation.Parameter;
import com.activitylauncher.internal.Launcher;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangming on 2017/2/17.
 *
 * 发射台
 */

public class LaunchPad {

    private static Map<Class<?>, Parameter[]> parameterAnnotationsByType = new HashMap<>();

    public static void launchForResult(Activity source, Class<?> target, int requestCode, Object... parameters) {
        launchForResult(Launcher.ACTIVITY, source, target, requestCode, parameters);
    }

    public static void launch(Activity source, Class<?> target, Object... parameters) {
        Log.e("MainActivity", "launch");
        launch(Launcher.ACTIVITY, source, target, parameters);
    }

    public static void launchForResult(Fragment source, Class<?> target, int requestCode, Object... parameters) {
        launchForResult(Launcher.FRAGMENT, source, target, requestCode, parameters);
    }

    public static void launch(Fragment source, Class<?> target, Object... parameters) {
        launch(Launcher.FRAGMENT, source, target, parameters);
    }

    public static void launchForResult(Launcher launcher, Object source, Class<?> target, int requestCode, Object... parameters) {
        Intent intent = createIntent(launcher, source, target, parameters);
        launcher.startActivityForResult(source, intent, requestCode);
    }

    public static void launch(Launcher launcher, Object source, Class<?> target, Object... parameters) {
        Intent intent = createIntent(launcher, source, target, parameters);
        launcher.startActivity(source, intent);
    }

    private static Parameter[] obtainParameterAnnotations(Class<?> target) {
        if (parameterAnnotationsByType.containsKey(target)) {
            return parameterAnnotationsByType.get(target);
        }
        Launch launch = target.getAnnotation(Launch.class);
        Parameter[] parameterAnnotations = launch != null ? launch.parameters() : null;
        parameterAnnotationsByType.put(target, parameterAnnotations);
        return parameterAnnotations;
    }

    private static Intent createIntent(Launcher launcher, Object source, Class<?> target, Object... parameters) {
        Intent intent = new Intent(launcher.getContext(source), target);
        Parameter[] parameterAnnotations = obtainParameterAnnotations(target);
        if (parameterAnnotations != null && parameterAnnotations.length > 0) {
            for (int i = 0; i < parameterAnnotations.length; i++) {
                Parameter parameterAnnotation = parameterAnnotations[i];
                String name = parameterAnnotation.name();
                Parameter.Type type = parameterAnnotation.type();
                switch (type) {
                    case INT:
                        intent.putExtra(name, (Integer) parameters[i]);
                        break;
                    case STRING:
                        intent.putExtra(name, (String) parameters[i]);
                        break;
                    default:
                        break;
                }
            }
        }
        return intent;
    }

}
