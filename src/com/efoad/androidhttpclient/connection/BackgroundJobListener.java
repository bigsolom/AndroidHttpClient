package com.efoad.androidhttpclient.connection;

import android.os.Handler;

public interface BackgroundJobListener {
	
	void processResult(Handler handler,final String response);
	
}
