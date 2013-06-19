package com.csumb.viewer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

import android.os.AsyncTask;
import android.util.Log;

public class ReverseGeo extends AsyncTask<String, Integer, GoogleJ> {
	
	
	GoogleJ datas= new GoogleJ();
	Reader reader;
	@Override
	protected GoogleJ doInBackground(String... params) 
	{
		String temp1=params[0];
		try
	    {
			InputStream source = retrieveStream(temp1);
			reader = new InputStreamReader(source);
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
		
		Gson gson= new Gson();
		
		datas= gson.fromJson(reader, GoogleJ.class);
						
		return datas;
	}
	
	
	private InputStream retrieveStream(String url) {
        
        DefaultHttpClient client = new DefaultHttpClient(); 
        
        HttpGet getRequest = new HttpGet(url);
          
        try {
           
           HttpResponse getResponse = client.execute(getRequest);
           final int statusCode = getResponse.getStatusLine().getStatusCode();
           
           if (statusCode != HttpStatus.SC_OK) { 
              Log.w(getClass().getSimpleName(), 
                  "Error " + statusCode + " for URL " + url); 
              return null;
           }

           HttpEntity getResponseEntity = getResponse.getEntity();
           return getResponseEntity.getContent();
           
        } 
        catch (IOException e) {
           getRequest.abort();
           Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
        }
        
        return null;
        
     }

}
