/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tongyuan.android.zhiquleyuan.zxing.decode;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.activity.VideoActivity;
import com.tongyuan.android.zhiquleyuan.bean.CallToToyReq;
import com.tongyuan.android.zhiquleyuan.bean.CallToToyRes;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.tongyuan.android.zhiquleyuan.zxing.app.CaptureActivity;
import com.tongyuan.android.zhiquleyuan.zxing.camera.CameraManager;
import com.tongyuan.android.zhiquleyuan.zxing.view.ViewfinderResultPointCallback;

import java.util.Vector;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tongyuan.android.zhiquleyuan.fragment.ToySelectorFragment.mToyId;


/**
 * This class handles all the messaging which comprises the state machine for
 * activity_qrcode_capture_layout.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
public final class CaptureActivityHandler extends Handler {

    private static final String TAG = CaptureActivityHandler.class.getSimpleName();

    private CaptureActivity activity;
    private DecodeThread decodeThread;
    private State state;
    private int flag;
    private String babyImg;
    private String babyName;
    private String token;
    private String mRoomid;
    private String toyId;

    public CaptureActivityHandler(CaptureActivity captureActivity, Vector<BarcodeFormat> decodeFormats, String characterSet, int flag, String
            babyImg, String babyName, String token, String roomId, String toyId) {
        this.babyImg = babyImg;
        this.babyName = babyName;
        this.babyName = token;
        this.babyName = roomId;
        this.babyName = toyId;
        this.flag = flag;
        this.activity = captureActivity;
        decodeThread = new DecodeThread(activity, decodeFormats, characterSet, new ViewfinderResultPointCallback(
                activity.getViewfinderView()));
        decodeThread.start();
        state = State.SUCCESS;

        // Start ourselves capturing previews and decoding.
        CameraManager.get().startPreview();
        restartPreviewAndDecode();

    }

    private enum State {
        PREVIEW, SUCCESS, DONE
    }

    public CaptureActivityHandler(CaptureActivity activity, Vector<BarcodeFormat> decodeFormats, String characterSet, int flag) {
        this.flag = flag;
        this.activity = activity;
        decodeThread = new DecodeThread(activity, decodeFormats, characterSet, new ViewfinderResultPointCallback(
                activity.getViewfinderView()));
        decodeThread.start();
        state = State.SUCCESS;

        // Start ourselves capturing previews and decoding.
        CameraManager.get().startPreview();
        restartPreviewAndDecode();
    }

    @Override
    public void handleMessage(Message message) {

        if (message.what == R.id.auto_focus) {
            // Log.d(TAG, "Got auto-focus message");
            // When one auto focus pass finishes, start another. This is the
            // closest thing to
            // continuous AF. It does seem to hunt a bit, but I'm not sure what
            // else to do.
            if (state == State.PREVIEW) {
                CameraManager.get().requestAutoFocus(this, R.id.auto_focus);
            }
        } else if (message.what == R.id.restart_preview) {
            Log.d(TAG, "Got restart preview message");
            restartPreviewAndDecode();
        } else if (message.what == R.id.decode_succeeded) {
            Log.d(TAG, "Got decode succeeded message");
            state = State.SUCCESS;
            Bundle bundle = message.getData();
            Bitmap barcode = bundle == null ? null : (Bitmap) bundle.getParcelable(DecodeThread.BARCODE_BITMAP);
            activity.handleDecode((Result) message.obj, barcode);
            final Result result = (Result) message.obj;
            final Intent intent = new Intent();

            intent.putExtra("SCAN_RESULT", result.getText());
            if (flag == 1) {
                activity.setResult(Activity.RESULT_OK, intent);
                activity.finish();
            } else if (flag == 2) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
                alertDialog.setTitle("提示");
                alertDialog.setMessage("扫描成功,是否进入视频通话");
                alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }
                });
                alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callToToy(result.getText());
                        Log.i("captureactivity", "onClick33: --" + babyImg + "--");
                        Log.i("captureactivity", "onClick33: --" + babyName + "--");
                        Log.i("captureactivity", "onClick33: --" + mRoomid + "--");
                        Log.i("captureactivity", "onClick33: --" + token + "--");
                        Log.i("captureactivity", "onClick33: --" + toyId + "--");

//
                        activity.finish();
                    }
                });
                alertDialog.show();

//    activity.setResult(Activity.RESULT_OK, mIntent);
            } else if (flag == 3) {//这个是在玩具和手机已经进入视频通话的时候,添加扫描二维码功能的时候,要做的处理
                activity.finish();
            }

//            activity.finish();
        } else if (message.what == R.id.decode_failed) {
            // We're decoding as fast as possible, so when one decode fails,
            // start another.
            state = State.PREVIEW;
            CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), R.id.decode);
        } else if (message.what == R.id.return_scan_result) {
            Log.d(TAG, "Got return scan result message");
            activity.setResult(Activity.RESULT_OK, (Intent) message.obj);
            activity.finish();
        } else if (message.what == R.id.launch_product_query) {
            Log.d(TAG, "Got product query message");
            String url = (String) message.obj;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            activity.startActivity(intent);
        }
    }

    private void callToToy(String thirdId) {
        final String token = SPUtils.getString(activity, "TOKEN", "");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        CallToToyReq.ParamBean param = new CallToToyReq.ParamBean(mToyId, "1", "", thirdId);
        CallToToyReq callToToyReq = new CallToToyReq("contact_toy", param, token);
        Gson gson = new Gson();
        String s = gson.toJson(callToToyReq);
        Call<CallToToyRes> callToToyResCall = allInterface.CALL_TO_TOY_RES_CALL(s);
        callToToyResCall.enqueue(new retrofit2.Callback<CallToToyRes>() {
            @Override
            public void onResponse(Call<CallToToyRes> call, Response<CallToToyRes> response) {
                Log.i("555555", "onResponse:+response " + response.body().toString());
                mRoomid = response.body().getRoomid();
                if (mRoomid == null) {
                    ToastUtil.showToast(activity, "房间号不存在");

                    if (response.body().getCode().equals("-10008")) {
                        ToastUtil.showToast(activity, "推送失败");
                    } else if (response.body().getCode().equals("-10009")) {
                        ToastUtil.showToast(activity, "玩具未登录");
                    } else if (response.body().getCode().equals("-10012")) {
                        ToastUtil.showToast(activity, "玩具通话中");
                    }
                    return;
                }
                VideoActivity.launch(activity, babyImg, babyName, mRoomid, token, mToyId, null);
//                Bundle bundle = new Bundle();
//                bundle.putString("roomid", mRoomid);
//                bundle.putString("token", token);
//                bundle.putString("toyid", mId);
//                bundle.putString("babyimg",mBabyimg);
//                bundle.putString("babyname",mBabyName);
//
//                startActivity(new Intent(getActivity(), VideoActivity.class).putExtras(bundle));
                ToastUtil.showToast(activity, "roomid" + mRoomid);
                Log.i("555555", "onResponse:+mRoomid " + mRoomid);
            }

            @Override
            public void onFailure(Call<CallToToyRes> call, Throwable t) {
                Log.i("111111", t.toString());

            }
        });
    }

    public void quitSynchronously() {
        state = State.DONE;
        CameraManager.get().stopPreview();
        Message quit = Message.obtain(decodeThread.getHandler(), R.id.quit);
        quit.sendToTarget();
        try {
            decodeThread.join();
        } catch (InterruptedException e) {
            // continue
        }

        // Be absolutely sure we don't send any queued up messages
        removeMessages(R.id.decode_succeeded);
        removeMessages(R.id.decode_failed);
    }

    private void restartPreviewAndDecode() {
        if (state == State.SUCCESS) {
            state = State.PREVIEW;
            CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), R.id.decode);
            CameraManager.get().requestAutoFocus(this, R.id.auto_focus);
            activity.drawViewfinder();
        }
    }

}