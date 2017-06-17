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
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.BabyInfoRequestBean;
import com.tongyuan.android.zhiquleyuan.bean.BabyInfoResultBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.Utils;
import com.tongyuan.android.zhiquleyuan.view.ChangeDatePopwindow;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.os.Environment.DIRECTORY_PICTURES;

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
    @Bind(R.id.rg_addbabyinfo)
    RadioGroup mRgAddbabyinfo;
    @Bind(R.id.radioButtonBoy)
    RadioButton mRadioButtonBoy;
    @Bind(R.id.radiobuttonGirls)
    RadioButton mRadioButtonGirls;

    @Bind(R.id.tv_addbabyinfo_hint_up)
    TextView mTvAddbabyinfoHintUp;
    @Bind(R.id.tv_addbabyinfo_hint_down)
    TextView mTvAddbabyinfoHintDown;
    @Bind(R.id.tv_activity_addbabyinfo_date)
    TextView mTvActivityAddbabyinfoDate;
    @Bind(R.id.bt_activity_addbabyinfo_confirm)
    Button mBtActivityAddbabyinfoConfirm;

    private Uri mTempUri;
    private String uploadFilePath;
    private String uploadFileName;
    private ChangeDatePopwindow mChangeDatePopwindow;
    private String mBabyID;
    private String[] mBirthday;

    private int mBirthday1;
    private String mDatetime;
    private String mTimedate;
    private Intent uriIntent;
    private String sex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbabyinfo);
        ButterKnife.bind(this);
//        mRgAddbabyinfo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId) {
//                    case R.id.radioButtonBoy:
//                        sex = "M";
//                        break;
//                    case R.id.radiobuttonGirls:
//                        sex = "W";
//                }
//            }
//        });

        uploadFilePath = getExternalFilesDir(DIRECTORY_PICTURES).getAbsolutePath();
        uploadFileName="icon.png";
    }

    @OnClick({R.id.iv_addbabyinfo, R.id.et_addbabyinfo, R.id.rg_addbabyinfo,
            R.id.tv_activity_addbabyinfo_date, R.id.bt_activity_addbabyinfo_confirm, R.id.baby_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_addbabyinfo:
                //拿到宝宝的头像
                showChoosePicDialog();
                break;
            case R.id.et_addbabyinfo:
                //添加宝宝的姓名或者说是宝宝的ID
                mBabyID = mEtAddbabyinfo.getText().toString().trim();
                break;
            case R.id.radioButtonBoy:
                //宝宝的性别选择(男), 如果选中,就给定一个值
                sex = "M";
                break;
            case R.id.radiobuttonGirls:
                //宝宝的性别选择(女)
                sex = "W";
                break;
            case R.id.tv_activity_addbabyinfo_date:
                mBirthday = selectDate();
                break;
            case R.id.bt_activity_addbabyinfo_confirm:
                //确认键,将头像,宝宝的姓名(babyID),性别,出生年月日上传到服务器
                //把头像,上传到服务器
                uploadPic(new File(uploadFilePath + File.separator + uploadFileName));
                break;
            case R.id.baby_back:
                finish();
                break;
            default:
                break;
        }
    }


    //上传宝宝的信息
    private void confirmInfo(String phoneNum, String time, final String token, String babyID, String sex) {

//        Date date1=new Date(mDatetime);
//        SimpleDateFormat si=new SimpleDateFormat("yyyyMMddHHmmssSSS");
//        String formatdate = si.format(date1);
//        Log.i("111", "confirmInfo: "+formatdate);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        BabyInfoRequestBean.BODYBean babyInfoBody = new BabyInfoRequestBean.BODYBean("BABY", time, babyID, babyID, mTimedate, sex);
        BabyInfoRequestBean babyInfoRequestBean = new BabyInfoRequestBean("REQ", "INFO", phoneNum, time, babyInfoBody, "", token, "1");

        Gson gson = new Gson();
        String s = gson.toJson(babyInfoRequestBean);
        Call<BabyInfoResultBean> babyInfoResultBeanCall = allInterface.sendBabyInfoResult(s);
//        Call<BabyInfoResultBean> babyInfoResultBeanCall = allInterface.sendBabyInfoResult(babyInfoRequestBean);
        babyInfoResultBeanCall.enqueue(new Callback<BabyInfoResultBean>() {
            @Override
            public void onResponse(Call<BabyInfoResultBean> call, Response<BabyInfoResultBean> response) {
                Log.i("111", "token====" + token);
                Log.i("111", "onResponse: ======================" + response.body().toString());
                Log.i("1111", "onResponse: " + response.body().toString());
//                Intent intent =new Intent();
//                intent.setClass(getApplicationContext(),BabyInfoListActivity.class);

            }

            @Override
            public void onFailure(Call<BabyInfoResultBean> call, Throwable t) {

            }
        });
//        babyInfoResult.enqueue(new Callback<BabyInfoResultBean>() {
//            @Override
//            public void onResponse(Call<BabyInfoResultBean> call, Response<BabyInfoResultBean> response) {
//                Log.i("111", "token====" + token);
//                Log.i("111", "onResponse: ======================" + response.body().toString());
//                Log.i("1111", "onResponse: " + response.body().toString());
//
//            }
//
//            @Override
//            public void onFailure(Call<BabyInfoResultBean> call, Throwable t) {
//                ToastUtil.showToast(getApplicationContext(),"失败了");
//            }
//        });
    }

    private String[] selectDate() {
        final String[] str = new String[10];
        mChangeDatePopwindow = new ChangeDatePopwindow(this);
        mChangeDatePopwindow.setDate("2017", "01", "01");
        mChangeDatePopwindow.showAtLocation(mTvActivityAddbabyinfoDate, Gravity.BOTTOM, 0, 0);
        mChangeDatePopwindow.setBirthdayListener(new ChangeDatePopwindow.OnBirthListener() {
            @Override
            public void onClick(String year, String month, String day) {
                Toast.makeText(MyBabyActivity.this, year + " " + month + " " + day, Toast.LENGTH_LONG).show();
                StringBuilder sb = new StringBuilder();
//                sb.append(year.substring(0, year.length() - 1)).append("-").append(month.substring(0, day.length() - 1)).append("-").append(day);
                sb.append(year.substring(0, year.length() - 1)).append(month.substring(0, day.length() - 1)).append(day.substring(0, day.length() -
                        1));
                str[0] = year + "" + month + "" + day;
                mDatetime = year + month + day;
                Log.i("111", "sb=========: " + sb);
                Log.i("111", "mDatetime=========: " + mDatetime);
                Log.i("111", "str[0]=========: " + str[0]);

                str[1] = sb.toString();
                Log.i("111", "str[1]=========: " + str[1]);
                //对mDatetime去进行筛选,剔除里面的年月日,拼接成日期,然后在integer.parse


                mTvActivityAddbabyinfoDate.setText(year + " " + month + " " + day);
                mTimedate = sb + "0000000";
                Log.i("111", "timedate: " + mTimedate);
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
        uriIntent = data;
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
            photo = Utils.toRoundBitmap(photo, uploadFilePath, uploadFileName);
            Utils.savePhoto(photo, uploadFilePath, uploadFileName);
            mIvAddbabyinfo.setImageBitmap(photo);

        }
    }

    //用retrofit上传头像
    private void uploadPic(File file) {
        String phoneNum = SPUtils.getString(this, "phoneNum", "");
        String babyToken = SPUtils.getString(this, "TOKEN", "");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.27.41.179:8081/zqpland/m/iface/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        Gson gson = new Gson();
        String babyID = mEtAddbabyinfo.getText().toString().trim();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time = simpleDateFormat.format(date);

        BabyInfoRequestBean.BODYBean babyInfoBody = new BabyInfoRequestBean.BODYBean("BABY", time, babyID, babyID, mTimedate, sex);
        BabyInfoRequestBean babyInfoRequestBean = new BabyInfoRequestBean("REQ", "INFO", phoneNum, time, babyInfoBody, "", babyToken, "1");

        String s = gson.toJson(babyInfoRequestBean);
//        Log.i("upload", "request =" + s);
//        RequestBody requestFile = RequestBody.create(MediaType.parse("image/png"), file);
//        RequestBody description = RequestBody.create(MediaType.parse("multipart/form_data"),"宝宝的头像");
//        MultipartBody.Part body = MultipartBody.Part.createFormData("IMG", file.getName(), requestFile);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        MultipartBody.Part body = MultipartBody.Part.createFormData("IMG", file.getName(), requestFile);

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("params", s);
        builder.addFormDataPart("IMG", file.getName(), requestFile);

        List<MultipartBody.Part> parts = builder.build().parts();
        Call<BabyInfoResultBean> babyInfoResultBeanCall = allInterface.BABY_INFO_RESULT_BEAN_CALL(parts);
        babyInfoResultBeanCall.enqueue(new Callback<BabyInfoResultBean>() {
            @Override
            public void onResponse(Call<BabyInfoResultBean> call, Response<BabyInfoResultBean> response) {
//                Log.i("upload", "body=" + response.message() + " " + response.body().getMSG());
                String code = response.body().getCODE();
//                Log.i("upload", response.body().toString());
                if(code.equals("0")) {
                    finish();
                    setResult(BabyInfoListActivity.SuccessCode);
                }
            }

            @Override
            public void onFailure(Call<BabyInfoResultBean> call, Throwable t) {
                Log.i("upload", "onFailure...");
            }
        });
//        Call<BabyInfoResultBean> call = allInterface.BABY_INFO_RESULT_BEAN_CALL(s, body);
//        call.enqueue(new Callback<BabyInfoResultBean>() {
//            @Override
//            public void onResponse(Call<BabyInfoResultBean> call, Response<BabyInfoResultBean> response) {
//                Log.i("upload", "body=" + response.message() + " " + response.body().getMSG());
//                String code = response.body().getCODE();
//                Log.i("upload", response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<BabyInfoResultBean> call, Throwable t) {
//                Log.i("upload", "onFailure...");
//            }
//        });

    }


}
