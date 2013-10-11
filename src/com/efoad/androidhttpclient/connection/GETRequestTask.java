package com.efoad.androidhttpclient.connection;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import android.os.Handler;

import com.efoad.androidhttpclient.data.RequestData;

public class GETRequestTask extends RequestTask{
	
	public GETRequestTask(Handler h,BackgroundJobListener j) {
		super(h, j);
	}

	@Override
	protected HttpUriRequest getHttpRequest(RequestData[] params) {
		Map<String, String> requestParams = params[0].getParameters();
		Set<String> keySet = requestParams.keySet();
		String[] paramsStrings = new String[keySet.size()];
		int i=0;
		for (String key : keySet) {
			paramsStrings[i++] = key+"="+requestParams.get(key);
		}
		String joinParams = StringUtils.join(paramsStrings, "&");
		HttpGet httpget = new HttpGet(params[0].getUrl()+(StringUtils.isEmpty(joinParams)?"":"?"+joinParams));
		return httpget;
	}

}
