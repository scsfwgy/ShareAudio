package com.matt.shareaudio.status;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 创建日期 ：2020/8/10 12:00 PM
 * 描 述 ：
 * ============================================================
 **/
public class AudioModel {
    public String path;
    public float progress;
    public Status currStatus;
    public boolean showAudio;

    public AudioModel(String path, float progress, Status currStatus, boolean showAudio) {
        this.path = path;
        this.progress = progress;
        this.currStatus = currStatus;
        this.showAudio = showAudio;
    }
}
