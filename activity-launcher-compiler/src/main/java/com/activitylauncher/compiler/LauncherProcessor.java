package com.activitylauncher.compiler;

import com.activitylauncher.annotation.Launch;
import com.activitylauncher.annotation.PackageName;
import com.activitylauncher.annotation.Parameter;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import static javax.tools.Diagnostic.Kind.ERROR;
import static javax.tools.Diagnostic.Kind.NOTE;
import static javax.tools.Diagnostic.Kind.WARNING;

/**
 * Created by huangming on 2017/2/20.
 */

@AutoService(Processor.class)
public class LauncherProcessor extends AbstractProcessor {

    private static final String DEFAULT_CLASS_NAME = "ActivityLauncher";

    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        filer = env.getFiler();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();

        types.add(Launch.class.getCanonicalName());
        types.add(Parameter.class.getCanonicalName());
        types.add(PackageName.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment ev) {
        String launcherPackageName = getLauncherPackageName(ev);
        String launcherClassName = getLauncherClassName(ev);
        if (launcherPackageName != null && !"".equals(launcherPackageName) && launcherClassName != null && !"".equals(launcherClassName)) {
            note("process : launcherPackageName=" + launcherPackageName + ", launcherClassName=" + launcherClassName);
            LaunchClassesFinder finder = new LaunchClassesFinder(ev);
            List<LaunchClass> launchClasses = finder.find();
            for (LaunchClass launchClass : launchClasses) {
                note(launchClass.getName() + "," + launchClass.getSimpleName());
            }
            LauncherClassGenerator generator = new LauncherClassGenerator(filer, launcherPackageName, launcherClassName, launchClasses);
            try {
                generator.generate();
            } catch (IOException e) {
                e.printStackTrace();
                error("Unable to write activity launcher!!!");
            }
        } else {
            note("process : launcherPackageName is empty");
        }
        return true;
    }

    private String getLauncherPackageName(RoundEnvironment ev) {
        for (Element element : ev.getElementsAnnotatedWith(PackageName.class)) {
            return element.toString();
        }
        return null;
    }

    private String getLauncherClassName(RoundEnvironment ev) {
        return DEFAULT_CLASS_NAME;
    }

    private void error(String message) {
        processingEnv.getMessager().printMessage(ERROR, message);
    }

    private void note(String message) {
        processingEnv.getMessager().printMessage(NOTE, message);
    }

    private void warning(String message) {
        processingEnv.getMessager().printMessage(WARNING, message);
    }

}

