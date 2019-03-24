package com.httpclient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;



public class Restclient {
	
	
	public CloseableHttpResponse post(String url ,String entityString,HashMap<String , String> headerMap) throws ClientProtocolException, IOException 
	{
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(new StringEntity(entityString));
		for(Map.Entry<String, String>entry : headerMap.entrySet()) {
			httppost.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse closeableHttpresponse = httpClient.execute(httppost);
		return closeableHttpresponse;
		
	}

	

}
