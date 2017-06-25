// IMusicAidl.aidl
package com.tongyuan.android.zhiquleyuan;
import com.tongyuan.android.zhiquleyuan.IRemoteServiceCallback;

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

    /**
     * Often you want to allow a service to call back to its clients.
     * This shows how to do so, by registering a callback interface with
     * the service.
     */
    void registerCallback(IRemoteServiceCallback cb);

    void release();
}
