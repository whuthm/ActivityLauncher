package com.activitylauncher.compiler;

import java.util.List;

/**
 * Created by huangming on 2017/2/20.
 */

final class LaunchClass {

    /**
     * 启动包名
     */
    private final String packageName;
    /**
     * 启动类名
     */
    private final String className;
    /**
     * 启动参数
     */
    private final List<LaunchParameter> parameters;

    LaunchClass(String packageName, String className, List<LaunchParameter> parameters) {
        this.packageName = packageName;
        this.className = className;
        this.parameters = parameters;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public int getParameterCount() {
        return parameters != null ? parameters.size() : 0;
    }

    public LaunchParameter getParameter(int index) {
        return parameters.get(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LaunchClass(");
        sb.append("packageName=").append(packageName).append("; ");
        sb.append("className=").append(className).append("; ");
        sb.append("parameters").append(parameters);
        sb.append(")");
        return sb.toString();
    }
}
