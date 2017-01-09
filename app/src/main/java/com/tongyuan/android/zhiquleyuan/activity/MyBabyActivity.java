package com.tongyuan.android.zhiquleyuan.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.utils.ToastUtil;
import com.tongyuan.android.zhiquleyuan.utils.Utils;
import com.tongyuan.android.zhiquleyuan.view.ChangeDatePopwindow;

import java.io.ByteArrayOutputStream;
import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Created by android on 2016/12/23.
 */

public class MyBabyActivity extends AppCompatActivity {

    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;

    @Bind(R.id.tb_addbatyinfo)
    Toolbar mTbAddbatyinfo;
    @Bind(R.id.iv_addbabyinfo)
    ImageView mIvAddbabyinfo;
    @Bind(R.id.et_addbabyinfo)
    EditText mEtAddbabyinfo;
    @Bind(R.id.ll_addbabyinfo)
    LinearLayout mLlAddbabyinfo;
    @Bind(R.id.tv_addbabyinfo_hint_up)
    TextView mTvAddbabyinfoHintUp;
    @Bind(R.id.tv_addbabyinfo_hint_down)
    TextView mTvAddbabyinfoHintDown;
    @Bind(R.id.bt_activity_addbabyinfo_confirm)
    Button mBtActivityAddbabyinfoConfirm;
    @Bind(R.id.tv_activity_addbabyinfo_date)
    TextView mTvActivityAddbabyinfoDate;
    private Uri mTempUri;
    private ChangeDatePopwindow mChangeDatePopwindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbabyinfo);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.iv_addbabyinfo, R.id.et_addbabyinfo, R.id.ll_addbabyinfo,
            R.id.tv_activity_addbabyinfo_date, R.id.bt_activity_addbabyinfo_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_addbabyinfo:
                showChoosePicDialog();
                break;
            case R.id.et_addbabyinfo:
                break;
            case R.id.ll_addbabyinfo:
                break;
            case R.id.tv_activity_addbabyinfo_date:
                selectDate();
                break;
            case R.id.bt_activity_addbabyinfo_confirm:
                break;
            default:
                break;
        }
    }

    private String[] selectDate() {
        final String[] str = new String[10];
        mChangeDatePopwindow = new ChangeDatePopwindow(this);
        mChangeDatePopwindow.setDate("2017","1","1");
        mChangeDatePopwindow.showAtLocation(mTvActivityAddbabyinfoDate, Gravity.BOTTOM,0,0);
        mChangeDatePopwindow.setBirthdayListener(new ChangeDatePopwindow.OnBirthListener() {
            @Override
            public void onClick(String year, String month, String day) {
                Toast.makeText(MyBabyActivity.this,year + "-" + month + "-" + day,Toast.LENGTH_LONG).show();
                StringBuilder sb = new StringBuilder();
                sb.append(year.substring(0, year.length() - 1)).append("-").append(month.substring(0, day.length() - 1)).append("-").append(day);
                str[0] = year + "-" + month + "-" + day;
                str[1] = sb.toString();
                mTvActivityAddbabyinfoDate.setText(year + "-" + month + "-" + day);
            }
        });
        return str;
    }

    private void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                switch (which) {
                    case CHOOSE_PICTURE://选择本地照片
                        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE://拍照
                        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        mTempUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mTempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(mTempUri);
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData());
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data);
                    }
                    break;
            }
        }
    }


    //裁剪图片方法实现,这里用的是系统自带的裁剪,可以用circleimageview来裁剪.
    private void startPhotoZoom(Uri tempUri) {
        if (tempUri == null) {
            Log.i("tag", "uri 不存在");
        }
        //不为空就把uri赋值给mTempUri
        mTempUri = tempUri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(mTempUri, "image/*");
        //设置裁剪
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    //保存裁剪后的图片数据
    private void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            photo = Utils.toRoundBitmap(photo, mTempUri);
            mIvAddbabyinfo.setImageBitmap(photo);

            //把头像,上传到服务器
            uploadPic(photo);
        }
    }

    private void uploadPic(Bitmap photo) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.PNG, 60, stream);
        byte[] bytes = stream.toByteArray();
        String img = new String(Base64.encode(bytes, Base64.DEFAULT));
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.add("img", img);
        client.post("http://120.27.41.179:8081/", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                ToastUtil.showToast(getApplicationContext(), "上传成功");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                ToastUtil.showToast(getApplicationContext(), "上传失败");
            }
        });
    }


}
