package com.csumb.viewer;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{
	
	public Data()
	{}
	
	public List<features> features;
	
	public static class displayFieldName
	{
		public displayFieldName()
		{}
		
	}
	public static class fieldAliases
	{
		public fieldAliases()
		{}
	}
	public static class fields
	{
		public fields()
		{}
		
	}
	public static class features
	{
		public features()
		{}
		
		attributes attributes;
	}
	public static class attributes
	{
		public attributes()
		{}
		
		 @SerializedName("DBANAME")
		 String dbANAME;
		 @SerializedName("MAXADDOWN")
		 String maxADDOWN;
		 @SerializedName("MAXADUP")
		 String maxADUP;
		 @SerializedName("ServiceTyp")
		 String ServiceTyp;
		
	}
	
	
	
	
	

}
