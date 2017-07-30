package com.tongyuan.android.zhiquleyuan.base;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.tongyuan.android.zhiquleyuan.activity.MainActivity;
import com.tongyuan.android.zhiquleyuan.player.MusicPlayer;

import java.lang.ref.WeakReference;

/**
 * Created by android on 2016/12/5.
 */

public abstract class BaseRecordingFragment extends Fragment /*implements ServiceConnection*/ {

//    public static final String PLAYER_PREPARED = "toy.player.prepared";
//    public static final String PLAYER_ERROR    = "toy.player.error";
//    public static final String PLAYER_COMPLETE = "toy.player.complete";
//    public static final String PLAYER_PLAYING  = "toy.player.playing";

//    public FragmentActivity mActivity;
//    private MusicPlayer.ServiceToken mServiceToken;
//    private PlaybackStatus mPlaybackStatus;
//    protected boolean isBound = false;

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //拿到acitivity对象,获得context
        mActivity = getActivity();
        mServiceToken = MusicPlayer.bindToService(mActivity, this);
        mPlaybackStatus = new PlaybackStatus(this);
        IntentFilter f = new IntentFilter();
        f.addAction(PLAYER_PREPARED);
        f.addAction(PLAYER_ERROR);
        f.addAction(PLAYER_COMPLETE);
        f.addAction(PLAYER_PLAYING);
        mActivity.registerReceiver(mPlaybackStatus, f);

    }*/

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        //初始化布局view
//        View view = initview();
//        return view;
//    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        initData();
//    }
    //初始化数据,也是交给子类实现,,可以重写
//    protected void initData() {
//    }
    //初始化布局 交给子类实现
//    public abstract View initview();

    public void showFragment(String name) {

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showFragment(name);

        }

//        transaction.replace(R.id.fl_fragmentcontainer, f);
//        transaction.commit();

    }

    public void showFragment(BaseRecordingFragment fragment) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showFragment(fragment);

        }
    }

    /*@Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        isBound = true;
        bindSuccess();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        isBound = false;
    }

    private final static class PlaybackStatus extends BroadcastReceiver {

        private final WeakReference<BaseRecordingFragment> mReference;

        public PlaybackStatus(final BaseRecordingFragment activity) {
            mReference = new WeakReference<>(activity);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            BaseRecordingFragment baseActivity = mReference.get();
            if (baseActivity == null)
                return;
            if (action.equals(PLAYER_PREPARED)) {
                baseActivity.onPrepared();
            } else if (action.equals(PLAYER_ERROR)) {
                baseActivity.onError();
            } else if (action.equals(PLAYER_COMPLETE)) {
                baseActivity.onCompleted();
            } else if (action.equals(PLAYER_PLAYING)) {
                baseActivity.isSimplePlayUrl();
            }
        }
    }*/

    protected abstract void isSimplePlayUrl();

    protected abstract void onCompleted();

    protected abstract void onError();

    protected abstract void onPrepared();

    protected abstract void bindSuccess();

    /*@Override
    public void onDestroy() {
        super.onDestroy();
        MusicPlayer.unBindFromService(mServiceToken);
        mActivity.unregisterReceiver(mPlaybackStatus);
    }*/
}
