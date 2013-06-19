package com.csumb.viewer;

public class DisplayResults {
	
	private String name = "";
	private int imageNumber = 0;
	private int imageNumber2 = 0;
	
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setImageNumber(int imageNumber) {
		this.imageNumber = imageNumber;
	}
	public int getImageNumber() {
		return imageNumber;
	}
	public void setImageNumber2(int imageNumber) {
		this.imageNumber2 = imageNumber;
	}
	public int getImageNumber2() {
		return imageNumber2;
	}
	public void clear()
	{
		name = "";
		imageNumber = 0;
		imageNumber2 = 0;
	}

}
