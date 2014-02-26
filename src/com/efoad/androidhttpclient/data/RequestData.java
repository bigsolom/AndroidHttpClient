package com.efoad.androidhttpclient.data;

import java.util.HashMap;
import java.util.Map;

public class RequestData {

	private String url;
	private Map<String,? extends Object> parameters;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, ? extends Object> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, ? extends Object> parameters) {
		if(null == parameters) {
			this.parameters = new HashMap<String, Object>();
		}else {
			this.parameters = parameters;
		}
	}
	
}
