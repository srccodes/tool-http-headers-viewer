package com.srccodes.tools.html;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @author Abhijit Ghosh
 * @version 1.0
 */
public class HttpHeaderViewer {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpHeaderViewer httpHeaderViewer = new HttpHeaderViewer();
		Header[] headers = httpHeaderViewer.getHeaders("http://somesite.com");
		
		System.out.println("HTTP Headers:\n");
		for (int i = 0; i < headers.length; i++) {
			System.out.println(headers[i].getName() + " : " + headers[i].getValue());
		}

	}

	public Header[] getHeaders(String url) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = httpClient.execute(httpGet);
		
		return response.getAllHeaders();
	}
}
