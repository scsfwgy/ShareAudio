package com.matt.shareaudio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class ThirdActivity extends TemplateActivity {

    Button showBtn;

    public static void goIntent(Context context) {
        Intent intent = new Intent(context, ThirdActivity.class);
        context.startActivity(intent);
    }

    @Override
    int layoutId() {
        return R.layout.activity_third;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showBtn = findViewById(R.id.at_b_show);
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility = mStatusContainer.getVisibility();
                setAudioEnable(visibility != View.VISIBLE);
            }
        });
    }

    @Override
    protected boolean audioEnable() {
        return false;
    }
}