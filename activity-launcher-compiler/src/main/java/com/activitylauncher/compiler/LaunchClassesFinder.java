package com.activitylauncher.compiler;

import com.activitylauncher.annotation.Launch;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;

/**
 * Created by huangming on 2017/3/7.
 */

class LaunchClassesFinder {

    private final RoundEnvironment ev;

    LaunchClassesFinder(RoundEnvironment ev) {
        this.ev = ev;
    }

    List<LaunchClass> find() {
        List<LaunchClass> classes = new ArrayList<>();
        for (Element element : ev.getElementsAnnotatedWith(Launch.class)) {
            TypeMirror mirror = element.asType();
            Launch launch = element.getAnnotation(Launch.class);
            LaunchClass launchClass = new LaunchClass(mirror.toString(), element.getSimpleName().toString(), launch);
            classes.add(launchClass);
        }
        return classes;
    }
}
