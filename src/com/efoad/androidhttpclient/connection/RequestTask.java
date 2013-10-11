package com.efoad.androidhttpclient.connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.os.Handler;

import com.efoad.androidhttpclient.data.RequestData;

public abstract class RequestTask extends AsyncTask<RequestData, Void, String> {

	
	private BackgroundJobListener backgroundJob;
	private Handler handler;
//	private Activity activity;
//	private ProgressDialog dialog;
//	private String description;
//	private String title;

	public RequestTask(Handler h,BackgroundJobListener j) {
		this.handler = h;
		this.backgroundJob = j;
//		this.activity = a;
	}
	
	
	@Override
	protected String doInBackground(RequestData... params) {
		String result = null;
		HttpClient httpclient = new DefaultHttpClient();
		
		try {

			// Execute Request
			HttpResponse response = httpclient.execute(getHttpRequest(params));
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				result = out.toString();


			} else {
				// Closes the connection.
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	protected abstract HttpUriRequest getHttpRequest(RequestData[] params) throws IOException;


	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		this.backgroundJob.processResult(this.handler, result);
	}

}
