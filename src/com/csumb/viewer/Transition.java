package com.csumb.viewer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

public class Transition extends AsyncTask<String, Integer, Intent> {

	 ProgressDialog dialog;
	 Context myContext;
	 Intent myIntent;

	 public Transition(Context appcontext, Intent intent)
	 {
	    	myIntent=intent;
	    	myContext=appcontext;
	 }
	 
	 
	@Override
    protected void onPreExecute()
    {
		dialog= new ProgressDialog(myContext);
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    	dialog.setCancelable(false);
    	dialog.setMessage("Retrieving...");
        dialog.show();
    };      
		
	@Override
	protected Intent doInBackground(String... arg0) 
	{
		return null;
	}
	
	@Override
	protected void onPostExecute(Intent datas) 
	{
		if(dialog.isShowing())
		{
			dialog.dismiss();
		}
		myContext.startActivity(myIntent);
	}
	
	
	
	

}
