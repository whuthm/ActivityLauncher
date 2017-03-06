package com.activitylauncher.internal;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by huangming on 2017/2/16.
 */

public enum Launcher {

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
    };


    public abstract void startActivity(Object source, Intent intent);

    public abstract void startActivityForResult(Object source, Intent intent, int requestCode);

    public abstract Context getContext(Object source);

}
