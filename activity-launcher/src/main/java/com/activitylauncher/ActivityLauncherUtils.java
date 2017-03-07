package com.activitylauncher;

import android.content.Intent;

import com.activitylauncher.annotation.Launch;
import com.activitylauncher.annotation.Parameter;
import com.activitylauncher.internal.Launcher;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangming on 2017/2/17.
 * <p>
 * 发射台
 */

public class ActivityLauncherUtils {

    private static Map<Class<?>, Parameter[]> parameterAnnotationsByType = new HashMap<>();

    public static void startActivity(Object source, Class<?> target, Object... parameters) {
        Launcher launcher = Launcher.get(source);
        Intent intent = createIntent(launcher, source, target, parameters);
        int requestCode = obtainRequestCode(target);
        if (requestCode > 0) {
            launcher.startActivityForResult(source, intent, requestCode);
        } else {
            launcher.startActivity(source, intent);
        }
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

    private static int obtainRequestCode(Class<?> target) {
        Launch launch = target.getAnnotation(Launch.class);
        return launch.requestCode();
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
