package com.matt.shareaudio;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.matt.shareaudio.status.IStatus;
import com.matt.shareaudio.status.Status;
import com.matt.shareaudio.status.StatusManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Author : wgyscsf@163.com
 * Github : https://github.com/scsfwgy
 * Date   : 2020/8/9 6:33 PM
 * 描 述 ：
 **/
abstract class TemplateActivity extends AppCompatActivity implements IStatus {

    String TAG = getClass().getSimpleName();
    Context mContext;
    TemplateActivity mActivity;
    LinearLayout mRootLinearLayout;
    LinearLayout mStatusContainer;
    TextView mStatusTextView;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //反注册
        StatusManager.getSingleton().unregister(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册
        StatusManager.getSingleton().register(this);
        mContext = this;
        mActivity = this;
        setContentView(R.layout.activity_template);
        initTemplateView();
        initTemplateListener();
    }

    private void initTemplateView() {
        mRootLinearLayout = findViewById(R.id.at_ll_rootContainer);
        mStatusContainer = findViewById(R.id.at_tv_statusContainer);
        mStatusTextView = findViewById(R.id.at_tv_status);
        renderAudioText(0f);
        //默认状态是否可见
        setAudioEnable(audioEnable());

        //将子类view添加到模板类的mRootLinearLayout中
        LayoutInflater.from(this).inflate(layoutId(), mRootLinearLayout);
    }

    private void initTemplateListener() {
        mStatusContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Status currStatus = StatusManager.getSingleton().getCurrStatus();
                if (currStatus == Status.STATUS_PLAY) {
                    //这里是你播放进度，把进度传进来就好了，其他地方就可以都收到了
                    StatusManager.getSingleton().updateStatus(Status.STATUS_PAUSE, 0f);
                } else if (currStatus == Status.STATUS_PAUSE) {
                    StatusManager.getSingleton().updateStatus(Status.STATUS_PLAY, 5f);
                }
            }
        });
    }

    /**
     * 所有子类的布局通过这个方法实现
     *
     * @return
     */
    abstract int layoutId();

    /**
     * 播放按钮是否可见，可重写
     *
     * @return
     */
    protected boolean audioEnable() {
        if (StatusManager.getSingleton().isFirstPriority()) {
            return StatusManager.getSingleton().isShowAudio();
        } else {
            return true;
        }
    }

    /**
     * 动态调整是否可见，子类可调用
     *
     * @param enable
     */
    public void setAudioEnable(boolean enable) {
        mStatusContainer.setVisibility(enable ? View.VISIBLE : View.GONE);
    }

    /**
     * 在这里处理播放的逻辑
     */
    @Override
    public void play(float progress) {
        renderAudioText(progress);
        Log.d(TAG, "play:正在播放 " + progress);
        //播放时ui的变化

    }

    /**
     * 在这里处理暂停的逻辑
     */
    @Override
    public void pause() {
        renderAudioText(0f);
        Log.d(TAG, "pause:正在暂停 ");
        //暂停后ui的变化
    }

    public void renderAudioText(float progress) {
        Status currStatus = StatusManager.getSingleton().getCurrStatus();
        if (currStatus == Status.STATUS_PLAY) {
            mStatusTextView.setText("播放,进度" + progress);
        } else if (currStatus == Status.STATUS_PAUSE) {
            mStatusTextView.setText("暂停");
        }
    }
}
