package com.matt.shareaudio.status;

/**
 * Author : wgyscsf@163.com
 * Github : https://github.com/scsfwgy
 * Date   : 2020/8/9 6:59 PM
 * 描 述 ：
 **/
public interface IStatus {
    /**
     * 播放
     *
     * @param progress 0~1 表示当前播放进度
     */
    void play(float progress);

    /**
     * 暂停
     */
    void pause();
}
