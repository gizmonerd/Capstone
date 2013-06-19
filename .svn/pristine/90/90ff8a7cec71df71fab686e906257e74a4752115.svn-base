package com.csumb.viewer;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Response {
	
	public Response()
	{}
	
	SpatialReference spatialReference;
	
	public List<Candidates> candidates;
	
	public static class Candidates
	{
		public Candidates()
		{}
		
		@SerializedName("address")
		public String address;
		
		Location location;
		
		@SerializedName("score")
		public double score;
		
		Attribute attributes;

		Double getScore()
		{
			return score;
		}
		
	}

	public static class Attribute {
		
		public Attribute()
		{}
		
		@SerializedName("Disp_Lon")
		public double dispLong;
		
		@SerializedName("Disp_Lat")
		public double dispLat;

	}
	
	public static class Location {
		
		public Location()
		{}
		
		@SerializedName("x")
		public double x;
		
		@SerializedName("y")
		public double y;

	}

	public static class SpatialReference {
		
		SpatialReference()
		{}
		
		@SerializedName("wkid")
		public int wkid;

	}

}
