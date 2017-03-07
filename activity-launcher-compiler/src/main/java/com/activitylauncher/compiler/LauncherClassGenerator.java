package com.activitylauncher.compiler;

import com.activitylauncher.annotation.Parameter;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.List;

import javax.annotation.processing.Filer;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

/**
 * Created by huangming on 2017/3/6.
 */

public class LauncherClassGenerator {

    private static final ClassName INTENT = ClassName.get("android.content", "Intent");
    private static final ClassName LAUNCHER = ClassName.get("com.activitylauncher.internal", "Launcher");

    private final Filer filer;
    private final String packageName;
    private final String className;
    private final List<LaunchClass> launchClasses;

    public LauncherClassGenerator(Filer filer, String packageName, String className, List<LaunchClass> launchClasses) {
        this.filer = filer;
        this.packageName = packageName;
        this.className = className;
        this.launchClasses = launchClasses;
    }

    public void generate() throws IOException {
        //添加类修饰
        TypeSpec.Builder result = TypeSpec.classBuilder(className)
                .addModifiers(PUBLIC, FINAL);
        for (LaunchClass launchClass : launchClasses) {
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
        MethodSpec.Builder builder = MethodSpec.methodBuilder("start" + launchClass.getSimpleName()).addModifiers(PUBLIC, STATIC);
        builder.addParameter(TypeName.OBJECT, "source");
        int parameterCount = launchClass.getParameterCount();
        builder.addStatement("$T intent = new Intent()", INTENT);
        builder.addStatement("intent.setClassName($S, $S)", packageName, launchClass.getName());
        for (int i = 0; i < parameterCount; i++) {
            Parameter parameter = launchClass.getParameter(i);
            String parameterName = parameter.name();
            builder.addParameter(getTypeName(parameter), parameterName);
            builder.addStatement("intent.putExtra($S, $N)", parameterName, parameterName);
        }
        builder.addStatement("$T launcher = Launcher.get(source)", LAUNCHER);
        if (launchClass.getRequestCode() > 0) {
            builder.addStatement("if (launcher != null) launcher.startActivityForResult(source, intent, $L)", launchClass.getRequestCode());
        } else {
            builder.addStatement("if (launcher != null) launcher.startActivity(source, intent)");
        }
        return builder.build();
    }

    private TypeName getTypeName(Parameter parameter) {
        switch (parameter.type()) {
            case INT:
                return TypeName.INT;
            case STRING:
                return ClassName.get("java.lang", "String");
            case BOOLEAN_ARRAY:
                return ArrayTypeName.of(TypeName.BOOLEAN);
            default:
                return null;
        }
    }

}
