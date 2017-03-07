package com.activitylauncher.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.activitylauncher.ActivityLauncherUtils;

/**
 * Created by huangming on 2017/2/17.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_activity_launch_test1_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("MainActivity", "before");
                //ActivityLauncherUtils.startActivity(MainActivity.this, Test1Activity.class, "whuthm", 10);
                ActivityLauncher.startTest1Activity(MainActivity.this, "whuthm", 11);
                Log.e("MainActivity", "after");
            }
        });

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_layout, new MainFragment());
        ft.commitAllowingStateLoss();
    }
}
