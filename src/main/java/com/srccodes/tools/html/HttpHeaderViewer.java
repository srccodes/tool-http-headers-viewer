package com.srccodes.tools.html;

import java.io.Closeable;
import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author Abhijit Ghosh
 * @version 1.0
 */
public class HttpHeaderViewer {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpHeaderViewer httpHeaderViewer = new HttpHeaderViewer();
		Header[] headers = httpHeaderViewer.getHeaders("http://somesite.com", true);
		
		System.out.println("HTTP Headers:\n");
		for (int i = 0; i < headers.length; i++) {
			System.out.println(headers[i].getName() + " : " + headers[i].getValue());
		}

	}
	
	public Header[] getHeaders(String url, boolean followRedirection) throws ClientProtocolException, IOException  {
		Header[] headers = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		
		try {			
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			
			if(!followRedirection) {
				httpClientBuilder.disableRedirectHandling();
			}
			
			httpclient = httpClientBuilder.build();
			
			HttpGet httpGet = new HttpGet(url);
			response = httpclient.execute(httpGet);			
			headers = response.getAllHeaders();
		} finally {
			close(response);
			close(httpclient);
		}
		
		return headers;
	}
	
	private static void close(Closeable resource) throws IOException {
		if(resource != null) {
			resource.close();
		}
	}
}
