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

    public List<IStatus> mStatusList;
    private AudioModel mAudioModel;

    public void init(AudioModel defAudioModel) {
        mAudioModel = defAudioModel;
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
    public void updateStatus(Status status) {
        if (mStatusList == null) throw new IllegalArgumentException("请先调用StatusManager#init()方法");
        getAudioModel().currStatus = status;
        for (IStatus iStatus : mStatusList) {
            if (status == Status.STATUS_PLAY) {
                iStatus.play();
            } else if (status == Status.STATUS_PAUSE) {
                iStatus.pause();
            }
        }
    }

    @NonNull
    public Status getCurrStatus() {
        return getAudioModel().currStatus;
    }

    public boolean isShowAudio() {
        return getAudioModel().showAudio;
    }

    public void setShowAudio(boolean showAudio) {
        getAudioModel().showAudio = showAudio;
        if (mStatusList == null) throw new IllegalArgumentException("请先调用StatusManager#init()方法");
        for (IStatus iStatus : mStatusList) {
            iStatus.changeVisible();
        }
    }

    public AudioModel getAudioModel() {
        if (mAudioModel == null) throw new IllegalArgumentException("请先调用StatusManager#init()方法");
        return mAudioModel;
    }
}
