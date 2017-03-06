package com.activitylauncher.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.activitylauncher.LaunchPad;

/**
 * Created by huangming on 2017/2/17.
 */

public class MainFragment extends Fragment {

    private static final int REQUEST_CODE_TEST2 = 1001;

    TextView resultTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        resultTextView = (TextView) view.findViewById(R.id.tv_result);
        view.findViewById(R.id.btn_frag_launch_test2_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LaunchPad.launchForResult(MainFragment.this, Test2Activity.class, REQUEST_CODE_TEST2, "whuthm 1", "whuthm");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE_TEST2 == requestCode) {
            StringBuilder sb = new StringBuilder("Fragment Launch Result");
            sb.append("\nrequestCode=").append(requestCode);
            sb.append("\nresultCode=").append(resultCode);
            resultTextView.setText(sb.toString());
        }
    }
}
