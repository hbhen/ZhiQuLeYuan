package com.tongyuan.android.zhiquleyuan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.ControlToyVolumeReq;
import com.tongyuan.android.zhiquleyuan.bean.ControlToyVolumeRes;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tongyuan.android.zhiquleyuan.fragment.ToySelectorFragment.mToyId;

public class SetInitVolumeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mVolume_back;
    private TextView mVolumeValue;
    private Button mPlayReduce;
    private Button mPlay;
    private Button mPlayAdd;
    private Button mVolumeLock;
    private Button mVolumeConfirm;
    private ImageButton mVolumeReduce;
    private ImageButton mVolumeAdd;
    private int mVolumeInt = 50;
    private SeekBar mVolumeSeekBar;
    private String mToken;
    private static boolean isLock = false;
    private String mBabyid;


    public static void launch(Context context, String babyid) {
        Intent it = new Intent(context, SetInitVolumeActivity.class);
        it.putExtra("babyid", babyid);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setinitvolume);
        Intent intent = getIntent();
        mBabyid = intent.getStringExtra("babyid");
        //TODO   设置初始音量

        mVolume_back = (ImageView) findViewById(R.id.iv_initvolum_back);
        mVolumeValue = (TextView) findViewById(R.id.tv_activity_setinitvolume_value);
        mVolumeSeekBar = (SeekBar) findViewById(R.id.sb_activity_setinitvolume);
        mVolumeReduce = (ImageButton) findViewById(R.id.ib_activity_setinitvolume_reduce);
        mVolumeAdd = (ImageButton) findViewById(R.id.ib_activity_setinitvolume_add);

        mPlayReduce = (Button) findViewById(R.id.bt_activity_setinitvolume_reduce);
        mPlay = (Button) findViewById(R.id.bt_activity_setinitvolume_play);
        mPlayAdd = (Button) findViewById(R.id.bt_activity_setinitvolume_add);
        mVolumeLock = (Button) findViewById(R.id.bt_activity_setinitvolume_lock);
        mVolumeConfirm = (Button) findViewById(R.id.bt_activity_setinitvolume_confirm);

        mToken = SPUtils.getString(this, "token", "");
        if (isLock == true) {
            //显示一种lock图标
        }
        //显示另一种没有锁定的图标
        mVolumeSeekBar.setMax(100);
        //先从服务器获取value,如果没有,就取本地的
        getInitVolumeValue();

        mVolumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String volumeString = String.valueOf(progress);
                mVolumeValue.setText(volumeString);
                mVolumeInt = Integer.parseInt(volumeString);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                String volume = String.valueOf(progress);

            }
        });
        initData();
        initView();
        initListener();

    }

    private void getInitVolumeValue() {

    }

    private void initData() {

    }

    private void initView() {

    }

    private void initListener() {
        mVolumeReduce.setOnClickListener(this);
        mVolumeAdd.setOnClickListener(this);

        mPlayReduce.setOnClickListener(this);
        mPlay.setOnClickListener(this);
        mPlayAdd.setOnClickListener(this);
        mVolumeLock.setOnClickListener(this);
        mVolumeConfirm.setOnClickListener(this);
        mVolume_back.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_initvolum_back:
                finish();
                break;
            case R.id.ib_activity_setinitvolume_reduce:
                //先过去seekbar的progress的值

                if (mVolumeInt >= 0) {
                    mVolumeInt = mVolumeInt - 1;
                    if (mVolumeInt < 0) {
                        mVolumeInt = 0;
                    }
                    String volumeReduce = String.valueOf(mVolumeInt);
                    mVolumeValue.setText(volumeReduce);
                    mVolumeSeekBar.setProgress(mVolumeInt);
                }
                break;
            case R.id.ib_activity_setinitvolume_add:
                //先过去seekbar的progress的值

                if (mVolumeInt <= 100) {
                    mVolumeInt = mVolumeInt + 1;
                    if (mVolumeInt > 100) {
                        mVolumeInt = 100;
                    }

                    //mSeekBar.setProgress(mVolumeInt);
                    String volumeAdd = String.valueOf(mVolumeInt);
                    mVolumeValue.setText(volumeAdd);
                    mVolumeSeekBar.setProgress(mVolumeInt);
                }
                break;
            case R.id.bt_activity_setinitvolume_reduce:
                break;
            case R.id.bt_activity_setinitvolume_play:
                break;
            case R.id.bt_activity_setinitvolume_add:
                break;
            case R.id.bt_activity_setinitvolume_lock:
                //默认给一个flag,不锁定 false,在oncreate的时候,判断一下,回显lock的样子,如果拿到的sputils.getstring的flag是false或者是0,就是不锁定,如果是true或1
                //就是锁定,此时,点击事件不可点击,value不变
                if (isLock == true) {
                    mVolumeReduce.setClickable(false);
                    mVolumeAdd.setClickable(false);
                    mVolumeSeekBar.setClickable(false);
                    isLock = false;
                    SPUtils.remove(this, "islock");
                    ToastUtil.showToast(this, "当前音量锁定,请点击解锁键解锁");
//                    mPlayReduce
//                    mPlay
//                    mPlayAdd
//                    mVolumeLock
//                    mVolumeConfirm
                } else if (isLock == false) {
                    mVolumeReduce.setClickable(true);
                    mVolumeAdd.setClickable(true);
                    mVolumeSeekBar.setClickable(true);
                    isLock = true;
                    SPUtils.putBoolean(this, "islock", isLock);
                }
                break;
            case R.id.bt_activity_setinitvolume_confirm:
                String confirmValue = mVolumeValue.getText().toString();
                //最终的值上传到服务器
                confirmAndSendVolume(confirmValue);
                break;
            default:
                break;
        }
    }

    private void confirmAndSendVolume(String volume) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        ControlToyVolumeReq.ParamBean paramBean = new ControlToyVolumeReq.ParamBean(mToyId, volume);
        ControlToyVolumeReq controlToyVolumeReq = new ControlToyVolumeReq("volume", paramBean, mToken);
        Gson gson = new Gson();
        String s = gson.toJson(controlToyVolumeReq);
        Call<ControlToyVolumeRes> controlToyVolumeResCall = allInterface.CONTROL_TOY_VOLUME_RES_CALL(s);
        controlToyVolumeResCall.enqueue(new Callback<ControlToyVolumeRes>() {
            @Override
            public void onResponse(Call<ControlToyVolumeRes> call, Response<ControlToyVolumeRes> response) {
                if (response.body().getCode().equals("0")) {
                    ToastUtil.showToast(getApplicationContext(), "成功");
                    finish();
                } else if (response.body().getCode().equals("-10009")) {
                    ToastUtil.showToast(getApplicationContext(), "玩具未登录,为获取到玩具DEVCODE");
                } else if (response.body().getCode().equals("-10006")) {
                    ToastUtil.showToast(getApplicationContext(), "用户未登录");

                }
            }

            @Override
            public void onFailure(Call<ControlToyVolumeRes> call, Throwable t) {
                ToastUtil.showToast(getApplicationContext(), "网络异常");


            }
        });
    }
}
