package com.tongyuan.android.zhiquleyuan.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.GetNewestVersionReqBean;
import com.tongyuan.android.zhiquleyuan.bean.GetNewestVersionResBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.ConfirmDialog;
import com.tongyuan.android.zhiquleyuan.utils.LogUtil;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.tongyuan.android.zhiquleyuan.utils.UpdateAppUtils;
import com.tongyuan.android.zhiquleyuan.utils.VersionCodeAndVersionNameUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DTC on 2016/12/23.
 */

public class MyUpdateActivity extends AppCompatActivity {
    private static final String TAG = "tag";
    private int code = 0;
//    String apkPath = Constant.apkDownload;
        String apkPath = "http://sj.qq.com/myapp/detail.htm?apkName=com.tongyuan.android.zhiquleyuan";
    public static final int NORMAL_DOWNLOAD = 1;
    public static final int WEB_DOWNLOAD = 2;
    public static final int FORCE_DOWNLOAD = 3;
    public static final int CHECK_DOWNLOAD = 4;
//    private int mVersionCode = 5;
    private String mVersion;
//    private String mVersionName = "1.5";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        TextView versionname = (TextView) findViewById(R.id.versionname);
        TextView versioncode = (TextView) findViewById(R.id.versioncode);
        String localVersionName = VersionCodeAndVersionNameUtils.getLocalVersionName(this);
        int localVersionCode = VersionCodeAndVersionNameUtils.getLocalVersionCode(this);
        versionname.setText(localVersionName);
        versioncode.setText(String.valueOf(localVersionCode));

        getRemoteVersionCode();

    }

    private void getRemoteVersionCode() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        GetNewestVersionReqBean.BODYBean bodyBean = new GetNewestVersionReqBean.BODYBean("A", "APP");
        GetNewestVersionReqBean getNewestVersionReqBean = new GetNewestVersionReqBean("REQ", "LASTV", "", new SimpleDateFormat("yyyyMMddHHmmssSSS")
                .format(new Date()), bodyBean, "", "", "1");
        Gson gson = new Gson();
        String s = gson.toJson(getNewestVersionReqBean);
        Call<GetNewestVersionResBean> getNewestVersionResBeanCall = allInterface.GET_NEWEST_VERSION_CALL(s);
        getNewestVersionResBeanCall.enqueue(new Callback<GetNewestVersionResBean>() {
            @Override
            public void onResponse(Call<GetNewestVersionResBean> call, Response<GetNewestVersionResBean> response) {
                LogUtil.i(TAG, "onResponse: " + response);
                LogUtil.i(TAG, "onResponse: response_newest:" + response.body().getBODY());
                mVersion = response.body().getBODY().getVERSION();
                String url = response.body().getBODY().getURL();
                String isforce = response.body().getBODY().getISFORCE();
                //这里返回一个version版本号     remoteVersionCode
                String localVersionName = VersionCodeAndVersionNameUtils.getLocalVersionName(MyUpdateActivity.this);
                LogUtil.i(TAG, "onResponse: localversionname:" + localVersionName);
                if (!localVersionName.equals(mVersion)) {
                    checkAndUpdate(CHECK_DOWNLOAD);
//                    if (response.body().getBODY().getISFORCE().equals("0")) {
//                        checkAndUpdate(CHECK_DOWNLOAD);
//                    }else{
//                        checkAndUpdate(FORCE_DOWNLOAD);
//                    }
                } else {
                    ToastUtil.showToast(MyUpdateActivity.this, "当前已经是最新版本");
                }
            }

            @Override
            public void onFailure(Call<GetNewestVersionResBean> call, Throwable t) {

            }
        });

    }

    private void checkAndUpdate(int code) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            realUpdate(code);
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                realUpdate(code);
            } else {//申请权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            }
        }
    }

    private void realUpdate(int code) {
        this.code = code;
        switch (code) {
            case NORMAL_DOWNLOAD://1
                updat1();
                break;
            case WEB_DOWNLOAD://2
                update2();
                break;
            case FORCE_DOWNLOAD://3
                update3();
                break;
            case CHECK_DOWNLOAD://4
                update4();
                break;


        }
    }

    //基本更新
    private void updat1() {
        UpdateAppUtils.from(this)
//                .serverVersionCode(mVersionCode)
                .serverVersionName(mVersion)
                .apkPath(apkPath)
                .updateInfo("1.修复若干bug\n2.美化部分页面")
                .showNotification(true)
                .needFitAndroidN(true)
                .update();
    }

    //通过浏览器下载
    private void update2() {
        UpdateAppUtils.from(this)
//                .serverVersionCode(mVersionCode)
                .serverVersionName(mVersion)
                .apkPath(apkPath)
                .downloadBy(UpdateAppUtils.DOWNLOAD_BY_BROWSER)
                .update();
    }

    //强制更新
    private void update3() {
        UpdateAppUtils.from(this)
                .checkBy(UpdateAppUtils.CHECK_BY_VERSION_NAME)
//                .serverVersionCode(mVersionCode)
                .serverVersionName(mVersion)
                .apkPath(apkPath)
                .isForce(true)
                .downloadBy(UpdateAppUtils.DOWNLOAD_BY_BROWSER)
                .update();
    }

    //根据versionName判断跟新
    private void update4() {
        UpdateAppUtils.from(this)
                .checkBy(UpdateAppUtils.CHECK_BY_VERSION_NAME)
                .serverVersionName(mVersion)
//                .serverVersionCode(mVersionCode)
                .apkPath(apkPath)
                .downloadBy(UpdateAppUtils.DOWNLOAD_BY_BROWSER)
                .isForce(false)
                .update();
    }

    //权限请求结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LogUtil.i(TAG, "onRequestPermissionsResult: ");
                    realUpdate(code);
                } else {
                    new ConfirmDialog(this, new com.tongyuan.android.zhiquleyuan.interf.Callback() {
                        @Override
                        public void callback(int position) {
                            if (position == 1) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse("package:" + getPackageName())); // 根据包名打开对应的设置界面
                                startActivity(intent);
                            }
                        }
                    }).setContent("暂无读写SD卡权限\n是否前往设置？").show();
                }
                break;
        }
    }
}
