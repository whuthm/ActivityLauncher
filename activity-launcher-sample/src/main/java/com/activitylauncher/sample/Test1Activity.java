package com.activitylauncher.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.activitylauncher.annotation.Launch;
import com.activitylauncher.annotation.Parameter;
import com.activitylauncher.sample.R;


/**
 * Created by huangming on 2017/2/17.
 */

@Launch(parameters = {
        @Parameter(name = ParameterKeys.KEY_NAME, type = Parameter.Type.STRING),
        @Parameter(name = ParameterKeys.KEY_AGE, type = Parameter.Type.INT)
})
public class Test1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        TextView textView = (TextView) findViewById(R.id.tv_test1);

        Intent intent = getIntent();
        StringBuilder sb = new StringBuilder();
        sb.append("Test1 : ");
        sb.append("\n").append(ParameterKeys.KEY_NAME).append(" : ").append(intent.getStringExtra(ParameterKeys.KEY_NAME));
        sb.append("\n").append(ParameterKeys.KEY_AGE).append(" : ").append(intent.getIntExtra(ParameterKeys.KEY_AGE, 0));

        textView.setText(sb.toString());
    }
}
