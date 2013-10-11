package com.efoad.androidhttpclient.connection;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.os.Handler;

import com.efoad.androidhttpclient.data.RequestData;

public class POSTRequestTask extends RequestTask{

	public POSTRequestTask(Handler h, BackgroundJobListener j) {
		super(h, j);
	}

	@Override
	protected HttpUriRequest getHttpRequest(RequestData[] params) throws UnsupportedEncodingException {
		HttpPost httppost = new HttpPost(params[0].getUrl());
		// Add your data
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
							params[0].getParameters().size());
					Set<String> keySet = params[0].getParameters().keySet();
					for (String key : keySet) {
						nameValuePairs.add(new BasicNameValuePair(key, params[0]
								.getParameters().get(key)));
						
					}
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,HTTP.UTF_8));
					return httppost;
	}


}
