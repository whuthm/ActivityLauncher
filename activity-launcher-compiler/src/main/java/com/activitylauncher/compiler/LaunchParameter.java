package com.activitylauncher.compiler;

/**
 * Created by huangming on 2017/3/6.
 */

final class LaunchParameter {

    private final String type;
    private final String name;

    public LaunchParameter(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
