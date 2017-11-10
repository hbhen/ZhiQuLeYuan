package com.tongyuan.android.zhiquleyuan.http;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.tongyuan.android.zhiquleyuan.request.XRequest;
import com.tongyuan.android.zhiquleyuan.request.base.Request;
import com.tongyuan.android.zhiquleyuan.request.config.DataType;
import com.tongyuan.android.zhiquleyuan.request.impl.OnRequestListenerAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;


/**
 * Created by android on 2016/12/25.
 */

public class HttpUtils {


    public static void HttpConnection(final Context context, JSONObject obj, final Handler handler, String url, int TAG) {
        try {
            obj.put("token","");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestUrl = url + obj.toString();
        XRequest.getInstance().sendGet(TAG, requestUrl, new OnRequestListenerAdapter<String>() {
            @Override
            public void onDone(Request<?> request, Map<String, String> headers, String result, DataType dataType) {
                super.onDone(request, headers, result, dataType);
                Log.i("done", "onDone: _________" + result);
                try {
                    JSONObject js=new JSONObject(result);
                    if (js.get("CODE").equals("0")){
                        Message msg = Message.obtain();
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }else{
                        Toast.makeText(context,"error",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
