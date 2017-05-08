package com.tongyuan.android.zhiquleyuan.request.ex;

import com.tongyuan.android.zhiquleyuan.request.config.RequestCacheConfig;
import com.tongyuan.android.zhiquleyuan.request.interfaces.OnRequestListener;
import com.tongyuan.android.zhiquleyuan.request.response.NetworkResponse;
import com.tongyuan.android.zhiquleyuan.request.response.Response;

/**
 * Get char sequence from network
 * @author Robin
 * @since 2016-01-05 19:15:06
 *
 */
public class StringRequest extends MultipartRequest<String> {
	
	public StringRequest() {
		super();
	}

	public StringRequest(RequestCacheConfig cacheConfig, String url, String cacheKey,
			OnRequestListener<String> onRequestListener) {
		super(cacheConfig, url, cacheKey, onRequestListener);
	}

	@Override
	public Response<String> parseNetworkResponse(NetworkResponse response) {
		   return Response.success(new String(response.data), response.headers);
	}

}
