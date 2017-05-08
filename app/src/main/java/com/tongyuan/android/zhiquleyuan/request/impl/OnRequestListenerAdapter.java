package com.tongyuan.android.zhiquleyuan.request.impl;

import java.io.File;
import java.util.Map;

import com.tongyuan.android.zhiquleyuan.request.base.Request;
import com.tongyuan.android.zhiquleyuan.request.config.DataType;
import com.tongyuan.android.zhiquleyuan.request.interfaces.OnRequestListener;
import com.tongyuan.android.zhiquleyuan.request.network.HttpException;
import com.tongyuan.android.zhiquleyuan.request.response.NetworkResponse;

/**
 * implements "OnRequestListener" interface, if you need to rewrite a callback function, can realize this
 * @author Robin
 * @since 2015/5/27 18:55.
 */
public abstract class OnRequestListenerAdapter<T> implements OnRequestListener<T> {

	@Override
	public void onRequestPrepare(Request<?> request) {
	}

	@Override
	public void onRequestFailed(Request<?> request, HttpException httpException) {
	}

	@Override
	public void onRequestRetry(Request<?> request, int currentRetryCount, HttpException previousError) {
	}
	
	@Override
	public void onRequestDownloadProgress(Request<?> request, long transferredBytesSize, long totalSize) {
	}
	
	@Override
	public void onRequestUploadProgress(Request<?> request, long transferredBytesSize, long totalSize, int currentFileIndex,File currentFile) {
	}

	@Override
	public void onRequestFinish(Request<?> request, Map<String, String> headers, T result) {
	}

	@Override
	public void onCacheDataLoadFinish(Request<?> request, Map<String, String> headers, T result) {
	}
	
	@Override
	public boolean onParseNetworkResponse(Request<?> request, NetworkResponse networkResponse, T result) {
		return true;
	}

	@Override
	public void onDone(Request<?> request, Map<String, String> headers, T result, DataType dataType) {
	}
}
