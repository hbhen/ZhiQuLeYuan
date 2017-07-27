// IMusicAidl.aidl
package com.tongyuan.android.zhiquleyuan;

// Declare any non-default types here with import statements

interface IMusicAidl {

    void open(String path);

    void openAndStart(String path);

    boolean isPrepared();

    void start();

    boolean isPlaying();

    void playNext();

    void playPre();

    int getDuration();

    int getCurrentPosition();

    void pause();

    void stop();

    void setLockScreenAlbumArt(boolean enable);

    void setLooping(boolean looping);

    void release();

    boolean isPlayUrl(String musicId);
}
