package com.csumb.viewer;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

public  class DataResponse extends AsyncTask<String, Integer, Data>{
        public interface Callback{
        	void onComplete(Data datas);
        	void onFailure();
        }
        
        Data data= new Data();
        Reader reader;
        Context myContext;
        ProgressDialog dialog;
        String temp1;
        
        private Set<Callback> callbacks = new HashSet<Callback>();
        
        public void addObserver(Callback cb) {callbacks.add(cb);}

    public DataResponse(Activity activity) {
    	myContext=activity;
    	
		}


	public DataResponse() {
		// TODO Auto-generated constructor stub
	}

	@Override
    protected void onPreExecute()
    {
		dialog = new ProgressDialog(myContext);
    	dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    	dialog.setCancelable(false);
    	dialog.setMessage("Retrieving...");
        dialog.show(); 
		
    };      
        
    
        @Override
        protected Data doInBackground(String... params) {
            temp1=params[0]; 
            return getTheData();
        }
         
                    
        @Override
        protected void onPostExecute(Data data) 
        {  
        	if(dialog.isShowing())
        	{
        		dialog.dismiss();
        	}
        	//if(data!=null)
        	//{
        		for(Callback cb: callbacks){
        			cb.onComplete(data);
        		}
        //	}
        	//else{
        		//for(Callback cb: callbacks){
        			//cb.onFailure();
        		//}
        	//}
        		
        	

        }
        

        private Data getTheData()
        {
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
                data= gson.fromJson(reader, Data.class);  
                return data;
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