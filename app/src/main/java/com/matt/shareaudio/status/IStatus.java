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
     */
    void play();

    /**
     * 暂停
     */
    void pause();

    void changeVisible();
}
