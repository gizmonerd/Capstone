package com.csumb.viewer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoogleJ {

	public String status;
	public List <Results> results; 


	public static class Results
	{		
		@SerializedName("formatted_address")
		public String formatted_address;
		
	}

}
