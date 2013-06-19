package com.csumb.viewer;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ProjectedData {

	
	ProjectedData()
	{}
	
	public List<Features> features;
	
	

	public static class Features {
		
		Features()
		{}
		
		Attributes attributes;
		
	}
	
	
	public static class Attributes {
		
		 @SerializedName("OBJECTID")
		 int objectID;
		 
		 @SerializedName("MAXADDOWN")
		 String maxADDOWN;
		 
		 @SerializedName("MAXADUP")
		 String maxADUP; 
		
	}
	
}
