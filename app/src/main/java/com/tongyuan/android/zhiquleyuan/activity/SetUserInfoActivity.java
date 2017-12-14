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
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tongyuan.android.zhiquleyuan.R;
import com.tongyuan.android.zhiquleyuan.bean.UserInfoReqBean;
import com.tongyuan.android.zhiquleyuan.bean.UserInfoResBean;
import com.tongyuan.android.zhiquleyuan.interf.AllInterface;
import com.tongyuan.android.zhiquleyuan.interf.Constant;
import com.tongyuan.android.zhiquleyuan.utils.SPUtils;
import com.tongyuan.android.zhiquleyuan.utils.Utils;
import com.tongyuan.android.zhiquleyuan.view.ChangeDatePopwindow;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
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
 * Created by Android on 2017/7/3.
 */
public class SetUserInfoActivity extends AppCompatActivity {
    private static final String TAG = "userinfo";
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;

    @BindView(R.id.iv_setuserinfo)
    ImageView mIvSetuserinfo;
    @BindView(R.id.et_setuserinfo)
    EditText mEtSetuserinfo;
    @BindView(R.id.radioButtonBoy)
    RadioButton mRadioButtonBoy;
    @BindView(R.id.radiobuttonGirls)
    RadioButton mRadiobuttonGirls;
    @BindView(R.id.rg_setuserinfo)
    RadioGroup mRgSetuserinfo;
    @BindView(R.id.tv_setuserinfo_hint_up)
    TextView mTvSetuserinfoHintUp;
    @BindView(R.id.tv_activity_setuserinfo_date)
    TextView mTvActivitySetuserinfoDate;
    @BindView(R.id.bt_activity_setuserinfo_confirm)
    Button mBtActivitySetuserinfoConfirm;
    @BindView(R.id.baby_back)
    ImageView mBabyBack;
    @BindView(R.id.tb_setuserinfo)
    RelativeLayout mTbSetuserinfo;

    private Uri mTempUri;
    private String uploadFilePath;
    private String uploadFileName;
    private ChangeDatePopwindow mChangeDatePopwindow;
    private String[] mBirthday;
    private String mDatetime;
    private String mTimedate;
    private Intent uriIntent;
    private String mSex;
    private String mUserName;
    private String mUserid;
    private String mPhoneNum;
    private String mToken;
    private String mUserimg;
    private String mYear;
    private String mMonth;
    private String mDay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setuserinfo);
        ButterKnife.bind(this);

        mUserid = SPUtils.getString(this, "userID", "");
        mPhoneNum = SPUtils.getString(this, "phoneNum", "");
        mToken = SPUtils.getString(this, "token", "");
        uploadFilePath = getExternalFilesDir(DIRECTORY_PICTURES).getAbsolutePath();
        uploadFileName = "icon.png";
        mUserimg = SPUtils.getString(getApplicationContext(), "userimg", "");
        Log.d(TAG, "onCreate: mUserimg"+mUserimg);
        Glide.with(getApplicationContext()).load(mUserimg).placeholder(R.drawable.player_cover_default).into
                (mIvSetuserinfo);
        mEtSetuserinfo.setText(SPUtils.getString(getApplicationContext(), "username", ""));

//        String usersex = SPUtils.getString(getApplicationContext(), "usersex", "");
//        Log.d(TAG, "onCreate: setuserinfoactivity - usersex" + usersex);
        String usersex = SPUtils.getString(getApplicationContext(), "usersex", "");
        Log.d(TAG, "onCreate: setuserinfoactivity - usersex" + usersex);
        switch (usersex) {
            case "男":
                mSex = "M";
                mRadioButtonBoy.setChecked(true);
                break;
            case "女":
                mSex = "W";
                mRadiobuttonGirls.setChecked(true);
                break;
            default:
                break;
        }
        //这个日期应该从哪拿??
        String birthday = SPUtils.getString(getApplicationContext(), "userbirthday", "");
        Log.i(TAG, "<setuserinfoactivity>onCreate: birthday11" + birthday);
        //但是这样有个问题,如果,再次进入用户信息页面的时候,如果没有选择日期,那么这个时候,birthday就是空,上传的和保存的不一致????
//        mTvActivitySetuserinfoDate.setText(year + " " + month + " " + day + " ");
        if (birthday.equals("")) {
            mTvActivitySetuserinfoDate.setText("日期选择");
            String newUserBirthday = new SimpleDateFormat("yyyyMMddHHssSSS").format(new Date());
            birthday = newUserBirthday;
            mYear = birthday.substring(0, 4);
            mMonth = birthday.substring(4, 6);
            mDay = birthday.substring(6, 8);
        } else {
            mYear = birthday.substring(0, 4);
            mMonth = birthday.substring(4, 6);
            mDay = birthday.substring(6, 8);
            mTvActivitySetuserinfoDate.setText(mYear + " 年 " + mMonth + " 月 " + mDay + " 日 ");
        }
    }

    @OnClick({R.id.iv_setuserinfo, R.id.et_setuserinfo, R.id.radioButtonBoy, R.id.radiobuttonGirls, R.id
            .rg_setuserinfo, R.id
            .tv_setuserinfo_hint_up, R.id.tv_activity_setuserinfo_date, R.id.bt_activity_setuserinfo_confirm, R.id
            .baby_back, R.id.tb_setuserinfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setuserinfo:
                //拿到用户的头像
                showChoosePicDialog();
                break;
//            case R.id.et_setuserinfo:
//                break;
            case R.id.radioButtonBoy:
                Log.i(TAG, "onViewClicked: boy went");
                //用户的性别选择(男), 如果选中,就给定一个值
                mSex = "M";
                break;
            case R.id.radiobuttonGirls:
                //用户的性别选择(女)
                Log.i(TAG, "onViewClicked: girl went");
                mSex = "W";
//                mUserName = mEtSetuserinfo.getText().toString().trim();
//                Log.i(TAG, "<setuserinfoactivity>onViewClicked1: " + mUserName);
                break;
//            case R.id.rg_setuserinfo:
//                break;
//            case R.id.tv_setuserinfo_hint_up:
//
//                break;
            case R.id.tv_activity_setuserinfo_date:
                mBirthday = selectDate();
                break;
            case R.id.bt_activity_setuserinfo_confirm:
                //添加用户的姓名或者说是用户的ID
                mUserName = mEtSetuserinfo.getText().toString().trim();
                SPUtils.putString(getApplicationContext(), "username", mUserName);
                Log.i(TAG, "<setuserinfoactivity>onViewClicked: " + mUserName);
                //确认键,将头像,用户的姓名(id),性别,出生年月日上传到服务器
                //把头像,上传到服务器
//                uploadPic(new File(uploadFilePath + File.separator + uploadFileName));
                confirmInfo();
                break;
            case R.id.baby_back:
                finish();
                break;
            default:
                break;
        }
    }

    private String[] selectDate() {
        final String[] str = new String[10];
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//        String format = simpleDateFormat.format(date);
//        String defaultYear = format.substring(0, 4);
//        String defaultMonth = format.substring(4, 6);
//        String defaultDay = format.substring(6, format.length());
//        mChangeDatePopwindow.setDate(defaultYear, defaultMonth, defaultDay);

        mChangeDatePopwindow = new ChangeDatePopwindow(this);
        mChangeDatePopwindow.setDate(mYear, mMonth, mDay);
//        mChangeDatePopwindow.setDate("2017", "01", "01");
        mChangeDatePopwindow.showAtLocation(mTvActivitySetuserinfoDate, Gravity.BOTTOM, 0, 0);
        mChangeDatePopwindow.setBirthdayListener(new ChangeDatePopwindow.OnBirthListener() {
            @Override
            public void onClick(String year, String month, String day) {
                Toast.makeText(SetUserInfoActivity.this, year + " " + month + " " + day, Toast.LENGTH_LONG).show();
                StringBuilder sb = new StringBuilder();
//                sb.append(year.substring(0, year.length() - 1)).append("-").append(month.substring(0, day.length()
// - 1)).append("-").append(day);
                sb.append(year.substring(0, year.length() - 1)).append(month.substring(0, day.length() - 1)).append
                        (day.substring(0, day.length() -
                                1));
                str[0] = year + "" + month + "" + day;
                mDatetime = year + month + day;
                Log.i("111", "<setuserinfoactivity>sb=========: " + sb);
                Log.i("111", "<setuserinfoactivity>mDatetime=========: " + mDatetime);
                Log.i("111", "<setuserinfoactivity>str[0]=========: " + str[0]);

                str[1] = sb.toString();
                Log.i("111", "<setuserinfoactivity>str[1]=========: " + str[1]);
                //对mDatetime去进行筛选,剔除里面的年月日,拼接成日期,然后在integer.parse

                mTvActivitySetuserinfoDate.setText(year + " " + month + " " + day);
                mTimedate = sb + "0000000";
                Log.i("111", "<setuserinfoactivity>timedate: " + mTimedate);
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
            mIvSetuserinfo.setImageBitmap(photo);
            uploadPic(new File(uploadFilePath + File.separator + uploadFileName));
        }
    }

    //上传用户的信息
    private void confirmInfo() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time = simpleDateFormat.format(date);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        UserInfoReqBean.BODYBean userInfoBody = new UserInfoReqBean.BODYBean("USER", mUserid, mUserName, mUserName,
                mTimedate, mSex);
        Log.i(TAG, "confirmInfo: sex"+mSex);
        UserInfoReqBean userInfoReqBean = new UserInfoReqBean("REQ", "INFO", mPhoneNum, time, userInfoBody, "",
                mToken, "1");
        Gson gson = new Gson();
        String s = gson.toJson(userInfoReqBean);
//        Call<UserInfoResBean> userInfoResBeanCall = allInterface.sendUserInfoResult(s);
        Call<UserInfoResBean> userInfoResBeanCall = allInterface.sendUserInfoResult(s);
        userInfoResBeanCall.enqueue(new Callback<UserInfoResBean>() {
            @Override
            public void onResponse(Call<UserInfoResBean> call, Response<UserInfoResBean> response) {
                Log.i("111", "<setuserinfoactivity>onResponse: ======================" + response.body().toString());
                Log.i("1111", "<setuserinfoactivity>onResponse: " + response.body().toString());
                String code = response.body().getCODE();
                if (code.equals("0")) {
                    String name = response.body().getBODY().getNAME();
                    String img = response.body().getBODY().getIMG();
                    String sex = response.body().getBODY().getSEX();
                    String birthday = response.body().getBODY().getBIRTHDAY();
                    Log.i(TAG, "<setuserinfoactivity>onResponse: birthday22" + birthday);
                    Log.i(TAG, "<setuserinfoactivity>onResponse: sex22" + sex);

                    SPUtils.putString(getApplicationContext(), "userimg", img);
                    SPUtils.putString(getApplicationContext(), "username", name);
                    SPUtils.putString(getApplicationContext(), "sex", sex);
                    SPUtils.putString(getApplicationContext(), "userbirthday", birthday);
                    Log.i(TAG, "<setuserinfoactivityconfirm>onResponse:set ---imgimg" + img);
                    Log.i(TAG, "<setuserinfoactivityconfirm>onResponse:set ---sexsex" + sex);
                    Log.i(TAG, "<setuserinfoactivityconfirm>onResponse:set ---birthdaybirthday" + birthday);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UserInfoResBean> call, Throwable t) {
            }
        });
    }

    //用retrofit上传头像
    private void uploadPic(File file) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AllInterface allInterface = retrofit.create(AllInterface.class);
        Gson gson = new Gson();

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time = simpleDateFormat.format(date);

        UserInfoReqBean.BODYBean userInfoBody = new UserInfoReqBean.BODYBean("USER", mUserid, mUserName, mUserName,
                mTimedate, mSex);
        UserInfoReqBean userInfoReqBean = new UserInfoReqBean("REQ", "INFO", mPhoneNum, time, userInfoBody, "",
                mToken, "1");
        Log.i(TAG, "uploadPic: " + userInfoBody.toString());
        String s = gson.toJson(userInfoReqBean);

        final RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("params", s);
        builder.addFormDataPart("IMG", file.getName(), requestFile);

        List<MultipartBody.Part> parts = builder.build().parts();
        Call<UserInfoResBean> userInfoResultBeanCall = allInterface.USER_INFO_RES_BEAN_CALL(parts);
        userInfoResultBeanCall.enqueue(new Callback<UserInfoResBean>() {
            @Override
            public void onResponse(Call<UserInfoResBean> call, Response<UserInfoResBean> response) {
                Log.i("pengyounihen6", "<setuserinfoactivity>onResponse: " + response.body().toString());
                String code = response.body().getCODE();
                if (code.equals("0")) {
                    String name = response.body().getBODY().getNAME();
                    String img = response.body().getBODY().getIMG();
                    String sex = response.body().getBODY().getSEX();
                    String birthday = response.body().getBODY().getBIRTHDAY();
//                    Log.i(TAG, "<setuserinfoactivity>onResponse: name22" + birthday);
//                    Log.i(TAG, "<setuserinfoactivity>onResponse: img22" + birthday);
//                    Log.i(TAG, "<setuserinfoactivity>onResponse: sex22" + birthday);
//                    Log.i(TAG, "<setuserinfoactivity>onResponse: birthday22" + birthday);

                    SPUtils.putString(getApplicationContext(), "userimg", img);
                    SPUtils.putString(getApplicationContext(), "username", name);
                    SPUtils.putString(getApplicationContext(), "sex", sex);
                    SPUtils.putString(getApplicationContext(), "userbirthday", birthday);
                    Log.i(TAG, "<setuserinfoactivityupload>onResponse:set ---img" + img);
                    Log.i(TAG, "<setuserinfoactivityupload>onResponse:set ---sex" + sex);
                    Log.i(TAG, "<setuserinfoactivityupload>onResponse:set ---birthday" + birthday);
                }
            }

            @Override
            public void onFailure(Call<UserInfoResBean> call, Throwable t) {
                Log.i("upload", "onFailure...");
            }
        });
    }
}