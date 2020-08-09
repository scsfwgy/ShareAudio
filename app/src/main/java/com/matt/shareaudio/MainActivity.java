package com.matt.shareaudio;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.matt.shareaudio.status.Status;
import com.matt.shareaudio.status.StatusManager;

import androidx.annotation.Nullable;

public class MainActivity extends TemplateActivity {

    Button secondBtn;
    Button thirdBtn;

    @Override
    int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //这句话放app类中
        StatusManager.getSingleton().init(Status.STATUS_PLAY);

        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        secondBtn = findViewById(R.id.am_b_second);
        thirdBtn = findViewById(R.id.am_b_third);
        secondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondActivity.goIntent(mContext);
            }
        });
        thirdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdActivity.goIntent(mContext);
            }
        });
    }
}