package com.matt.shareaudio.status;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Author : wgyscsf@163.com
 * Github : https://github.com/scsfwgy
 * Date   : 2020/8/9 7:01 PM
 * 描 述 ：
 **/
public class StatusManager {
    private volatile static StatusManager singleton;

    private StatusManager() {
    }

    public static StatusManager getSingleton() {
        if (singleton == null) {
            synchronized (StatusManager.class) {
                if (singleton == null) {
                    singleton = new StatusManager();
                }
            }
        }
        return singleton;
    }

    private Status mStatus;
    public List<IStatus> mStatusList;
    private boolean mShowAudio;

    public void init(Status defStatus, boolean defShowAudio) {
        mShowAudio = defShowAudio;
        mStatus = defStatus;
        mStatusList = new ArrayList<>();
    }

    public void register(IStatus iStatus) {
        if (mStatusList == null) throw new IllegalArgumentException("请先调用StatusManager#init()方法");
        mStatusList.add(iStatus);
    }

    public void unregister(IStatus iStatus) {
        if (mStatusList == null) throw new IllegalArgumentException("请先调用StatusManager#init()方法");
        mStatusList.remove(iStatus);
    }

    /**
     * 同步状态
     *
     * @param status
     */
    public void updateStatus(Status status, float progress) {
        if (mStatusList == null) throw new IllegalArgumentException("请先调用StatusManager#init()方法");
        mStatus = status;
        for (IStatus iStatus : mStatusList) {
            if (status == Status.STATUS_PLAY) {
                iStatus.play(progress);
            } else if (status == Status.STATUS_PAUSE) {
                iStatus.pause();
            }
        }
    }

    @NonNull
    public Status getCurrStatus() {
        if (mStatus == null) throw new IllegalArgumentException("请先调用StatusManager#init()方法");
        return mStatus;
    }

    public boolean isShowAudio() {
        return mShowAudio;
    }

    public void setShowAudio(boolean showAudio) {
        mShowAudio = showAudio;
        if (mStatusList == null) throw new IllegalArgumentException("请先调用StatusManager#init()方法");
        for (IStatus iStatus : mStatusList) {
            iStatus.audioVisible(mShowAudio);
        }
    }
}
