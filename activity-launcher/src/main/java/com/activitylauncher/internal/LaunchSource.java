package com.activitylauncher.internal;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by huangming on 2017/2/20.
 */

public enum LaunchSource {

    VIEW {
        @Override
        public void startActivity(Object source, Intent intent) {
            getContext(source).startActivity(intent);
        }

        @Override
        public void startActivityForResult(Object source, Intent intent, int requestCode) {
            if (getContext(source) instanceof Activity) {
                ((Activity) getContext(source)).startActivityForResult(intent, requestCode);
            } else {
                startActivity(source, intent);
            }
        }

        @Override
        public Context getContext(Object source) {
            return ((View) source).getContext();
        }
    },

    DIALOG {
        @Override
        public void startActivity(Object source, Intent intent) {
            getContext(source).startActivity(intent);
        }

        @Override
        public void startActivityForResult(Object source, Intent intent, int requestCode) {
            if (getContext(source) instanceof Activity) {
                ((Activity) getContext(source)).startActivityForResult(intent, requestCode);
            } else {
                startActivity(source, intent);
            }
        }

        @Override
        public Context getContext(Object source) {
            return ((Dialog) source).getContext();
        }
    },

    CONTEXT {
        @Override
        public void startActivity(Object source, Intent intent) {
            getContext(source).startActivity(intent);
        }

        @Override
        public void startActivityForResult(Object source, Intent intent, int requestCode) {
            if (getContext(source) instanceof Activity) {
                ((Activity) getContext(source)).startActivityForResult(intent, requestCode);
            } else {
                startActivity(source, intent);
            }
        }

        @Override
        public Context getContext(Object source) {
            return (Context) source;
        }
    },

    ACTIVITY {
        @Override
        public void startActivity(Object source, Intent intent) {
            getContext(source).startActivity(intent);
        }

        @Override
        public void startActivityForResult(Object source, Intent intent, int requestCode) {
            getContext(source).startActivityForResult(intent, requestCode);
        }

        @Override
        public Activity getContext(Object source) {
            return (Activity) source;
        }
    },

    FRAGMENT {
        @Override
        public void startActivity(Object source, Intent intent) {
            getContext(source).startActivity(intent);
        }

        @Override
        public void startActivityForResult(Object source, Intent intent, int requestCode) {
            ((Fragment) source).startActivityForResult(intent, requestCode);
        }

        @Override
        public Context getContext(Object source) {
            return ((Fragment) source).getActivity();
        }
    },

    UNKNOWN {
        @Override
        public void startActivity(Object source, Intent intent) {

        }

        @Override
        public void startActivityForResult(Object source, Intent intent, int requestCode) {

        }

        @Override
        public Context getContext(Object source) {
            return null;
        }
    };


    public abstract void startActivity(Object source, Intent intent);

    public abstract void startActivityForResult(Object source, Intent intent, int requestCode);

    protected abstract Context getContext(Object source);

    public static LaunchSource get(Object source) {
        if (source instanceof Activity) {
            return ACTIVITY;
        } else if (source instanceof Fragment) {
            return FRAGMENT;
        } else if (source instanceof Dialog) {
            return DIALOG;
        } else if (source instanceof View) {
            return VIEW;
        } else if (source instanceof Context) {
            return CONTEXT;
        } else if (source instanceof Activity) {
            return ACTIVITY;
        } else {
            return UNKNOWN;
        }
    }

}
