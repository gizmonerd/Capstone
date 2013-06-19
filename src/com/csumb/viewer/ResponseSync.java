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

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

public class ResponseSync extends AsyncTask<String, Integer, Response> {

	Response response1= new Response();
	Reader reader;
	Context mContext;
	ProgressDialog dialog; 
	
	public ResponseSync(Context context){
		mContext=context;
		dialog= new ProgressDialog(mContext);
	}
		
    @Override
    protected void onPreExecute()
    {
    	/*dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    	dialog.setCancelable(false);
    	dialog.setMessage("Retrieving...");
        dialog.show();*/
        //do initialization of required objects objects here                
    };      
	
	
	@Override
	protected Response doInBackground(String... urls) {
		String temp1 = urls[0];
		
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
		
		response1= gson.fromJson(reader, Response.class);
						
		return response1;
	}
	
	@Override
	protected void onPostExecute(Response response) {

		/*
        if (dialog.isShowing()) {
            dialog.dismiss();
        }*/
          
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
