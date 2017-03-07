package com.activitylauncher.compiler;

import com.activitylauncher.annotation.Launch;
import com.activitylauncher.annotation.Parameter;

/**
 * Created by huangming on 2017/2/20.
 */

final class LaunchClass {

    /**
     * 启动类名
     */
    private final String name;

    private final Launch launch;

    private final String simpleName;

    LaunchClass(String name, String simpleName, Launch launch) {
        this.name = name;
        this.simpleName = simpleName;
        this.launch = launch;
    }

    public String getName() {
        return name;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public int getParameterCount() {
        return  launch.parameters() != null ? launch.parameters().length : 0;
    }

    public Parameter getParameter(int index) {
        return launch.parameters()[index];
    }

    public int getRequestCode() {
        return launch.requestCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LaunchClass(");
        sb.append("name=").append(name).append("; ");
        sb.append("simpleName=").append(simpleName).append("; ");
        if(launch.parameters() != null) {
            sb.append("parameters").append(launch.parameters()).append("; ");
        }
        sb.append("requestCode").append(launch.requestCode());
        sb.append(")");
        return sb.toString();
    }
}
