package com.activitylauncher.compiler;

import com.activitylauncher.annotation.Launch;
import com.activitylauncher.annotation.Parameter;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Filer;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;

/**
 * Created by huangming on 2017/3/6.
 */

public class LauncherClassGenerator {

    private final Filer filer;
    private final String packageName;
    private final String className;
    private final List<LaunchClass> launchClasses;

    public LauncherClassGenerator(Filer filer, String packageName, String className) {
        this.filer = filer;
        this.packageName = packageName;
        this.className = className;
        this.launchClasses = new ArrayList<>(1);
    }

    public void addLaunchClass(LaunchClass launchClass) {
        launchClasses.add(launchClass);
    }

    public void generate() throws IOException {
        //添加类修饰
        TypeSpec.Builder result = TypeSpec.classBuilder(className)
                .addModifiers(PUBLIC, FINAL);
        for(LaunchClass launchClass : launchClasses) {
            result.addMethod(createLaunchMethod(launchClass));
        }

        //创建私有构造方法
        result.addMethod(MethodSpec.constructorBuilder().addModifiers(PRIVATE).build());

        JavaFile.builder(packageName, result.build())
                .addFileComment("Generated code from Activity Launcher. Do not modify!")
                .build()
                .writeTo(filer);
    }


    private MethodSpec createLaunchMethod(LaunchClass launchClass) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder("start" + launchClass.getClassName());
        int parameterCount = launchClass.getParameterCount();
        for(int i = 0; i < parameterCount; i++) {
            LaunchParameter parameter = launchClass.getParameter(i);
            builder.addParameter()
        }
    }

    private TypeName getTypeName(LaunchParameter parameter) {
        switch (parameter.getType()) {
        }
    }

}
