package com.efoad.androidhttpclient.data;

import java.util.HashMap;
import java.util.Map;

public class RequestData {

	private String url;
	private Map<String,String> parameters;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, String> parameters) {
		if(null == parameters) {
			this.parameters = new HashMap<String, String>();
		}else {
			this.parameters = parameters;
		}
	}
	
}
