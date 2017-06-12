package com.tongyuan.android.zhiquleyuan.player;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.tongyuan.android.zhiquleyuan.IMusicAidl;
import com.tongyuan.android.zhiquleyuan.IRemoteServiceCallback;
import com.tongyuan.android.zhiquleyuan.service.MusicPlayerService;

import java.util.WeakHashMap;

/**
 *
 * Created by zgg on 2017/6/6.
 */
public class MusicPlayer {

    private static IMusicAidl mService;
    private static final WeakHashMap<Context, ServiceBinder> mConnectionMap;
    private static final long[] sEmptyList;

    public static final class ServiceToken {
        private ContextWrapper mWrappedContext;
        ServiceToken(final ContextWrapper context) {
            mWrappedContext = context;
        }
    }

    static {
        mConnectionMap = new WeakHashMap<Context, ServiceBinder>();
        sEmptyList = new long[0];
    }

    public static ServiceToken bindToService(Context context, ServiceConnection connection) {
        Activity realActivity = ((Activity) context).getParent();
        if (realActivity == null) {
            realActivity = (Activity) context;
        }
        final ContextWrapper contextWrapper = new ContextWrapper(realActivity);
        contextWrapper.startService(new Intent(contextWrapper, MusicPlayerService.class));
        final ServiceBinder binder = new ServiceBinder(connection);
        if (contextWrapper.bindService(
                new Intent().setClass(contextWrapper, MusicPlayerService.class), binder, Context.BIND_AUTO_CREATE)) {
            mConnectionMap.put(contextWrapper, binder);
            return new ServiceToken(contextWrapper);
        }
        return null;
    }

    public static void unBindFromService(final ServiceToken token) {
        if (token == null) {
            return;
        }
        final ContextWrapper mContextWrapper = token.mWrappedContext;
        final ServiceBinder mBinder = mConnectionMap.remove(mContextWrapper);
        if (mBinder == null) {
            return;
        }
        mContextWrapper.unbindService(mBinder);
        if (mConnectionMap.isEmpty()) {
            mService = null;
        }
    }

    public static void open(String path) {
        if(mService == null)
            return;
        try {
            mService.open(path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void play() {
        if(mService == null)
            return;
        try {
            mService.play();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void pause() {
        if(mService == null)
            return;
        try {
            mService.pause();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static boolean isPlaying() {
        if(mService == null)
            return false;
        try {
            return mService.isPlaying();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getCurrentPosition() {
        if(mService == null)
            return 0;
        try {
            return mService.getCurrentPosition();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void registerCallback(IRemoteServiceCallback callback) {
        if(mService == null)
            return ;
        try {
            mService.registerCallback(callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private static class ServiceBinder implements ServiceConnection {

        private ServiceConnection callback;
        ServiceBinder(ServiceConnection callback) {
            this.callback = callback;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IMusicAidl.Stub.asInterface(service);
            if (callback != null) {
                callback.onServiceConnected(name, service);
            }
            setShowAlbumArtOnLockScreen(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            if (callback != null) {
                callback.onServiceDisconnected(name);
            }
            mService = null;
        }
    }

    /**
     * 手机锁屏时显示在锁屏画面
     * @param enabled 是否显示
     */
    private static void setShowAlbumArtOnLockScreen(boolean enabled) {
        try {
            if (mService != null) {
                mService.setLockScreenAlbumArt(enabled);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
