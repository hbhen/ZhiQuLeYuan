package com.tongyuan.android.zhiquleyuan.player;

import android.os.RemoteException;

import com.tongyuan.android.zhiquleyuan.IMusicAidl;
import com.tongyuan.android.zhiquleyuan.IRemoteServiceCallback;
import com.tongyuan.android.zhiquleyuan.service.MusicPlayerService;

import java.lang.ref.WeakReference;

/**
 *
 * Created by zgg on 2017/6/7.
 */

public class MusicAidlStub extends IMusicAidl.Stub {

//    private WeakReference<MusicPlayerService> mService;
    private MultiPlayer multiPlayer;

    public MusicAidlStub() {
//        mService = new WeakReference<MusicPlayerService>(service);
    }

    @Override
    public void open(String path) throws RemoteException {

    }

    @Override
    public void play() throws RemoteException {

    }

    @Override
    public boolean isPlaying() throws RemoteException {
        return false;
    }

    @Override
    public void playNext() throws RemoteException {

    }

    @Override
    public void playPre() throws RemoteException {

    }

    @Override
    public int getDuration() throws RemoteException {
        return 0;
    }

    @Override
    public int getCurrentPosition() throws RemoteException {
        return 0;
    }

    @Override
    public void pause() throws RemoteException {

    }

    @Override
    public void stop() throws RemoteException {

    }

    @Override
    public void setLockScreenAlbumArt(boolean enable) throws RemoteException {

    }

    @Override
    public void registerCallback(IRemoteServiceCallback cb) throws RemoteException {

    }
}
