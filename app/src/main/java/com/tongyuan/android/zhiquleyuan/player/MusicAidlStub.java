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

    private MultiPlayer multiPlayer;

    public MusicAidlStub(MusicPlayerService service) {
        multiPlayer = new MultiPlayer(service);
    }

    @Override
    public void open(String path) throws RemoteException {
        multiPlayer.setDataSource(path);
    }

    @Override
    public void start() throws RemoteException {
        multiPlayer.start();
    }

    @Override
    public boolean isPlaying() throws RemoteException {
        return multiPlayer.isPlaying();
    }

    @Override
    public void playNext() throws RemoteException {

    }

    @Override
    public void playPre() throws RemoteException {

    }

    @Override
    public int getDuration() throws RemoteException {
        return multiPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() throws RemoteException {
        return multiPlayer.getCurrentPosition();
    }

    @Override
    public void pause() throws RemoteException {
        multiPlayer.pause();
    }

    @Override
    public void stop() throws RemoteException {
        multiPlayer.stop();
    }

    @Override
    public void setLockScreenAlbumArt(boolean enable) throws RemoteException {

    }

    @Override
    public void setLooping(boolean looping) throws RemoteException {
        multiPlayer.setLooping(looping);
    }

    @Override
    public void registerCallback(IRemoteServiceCallback cb) throws RemoteException {

    }
}
