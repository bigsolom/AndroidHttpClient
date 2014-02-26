package com.efoad.androidhttpclient.connection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import android.os.Handler;

import com.efoad.androidhttpclient.data.RequestData;

public abstract class SimpleRequest {
	
	public static final int HTTP_GET = 0;
	public static final int HTTP_POST = 1;
	private int requestType = HTTP_GET;
	
	/**
	 * 
	 * @param requestType indicates HTTP GET or POST request, use SimpleRequest.HTTP_GET or SimpleRequest.HTTP_POST
	 */
	public SimpleRequest(int requestType) {
		this.requestType  = requestType;
	}

	public void doRequest(String URL, Map<String,? extends Object> parameters, Handler handler) {
			RequestData request = new RequestData();
			request.setUrl(URL);
			request.setParameters(parameters);
			try {
				getRequestClass().newInstance(handler, new BackgroundJobListener() {

					@Override
					public void processResult(Handler handler, final String response) {
						if (response != null) {
							handler.post(new Runnable() {
								@Override
								public void run() {
									//response processing by client goes here
									processResponse(response);
								}
							});
						}
					}


				}).execute(request);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
	}

	abstract public void processResponse(String response);
	
	public Constructor<? extends RequestTask> getRequestClass() throws NoSuchMethodException {
		Class<?>[] cArgs = new Class[2];
		cArgs[0] = Handler.class;
		cArgs[1] = BackgroundJobListener.class;
		Constructor<? extends RequestTask> constructor = null;
		switch (this.requestType) {
		case HTTP_POST:
			constructor = POSTRequestTask.class.getDeclaredConstructor(cArgs);
			break;

		default:
			constructor = GETRequestTask.class.getDeclaredConstructor(cArgs);
			break;
		}
		return constructor;
	}
}
