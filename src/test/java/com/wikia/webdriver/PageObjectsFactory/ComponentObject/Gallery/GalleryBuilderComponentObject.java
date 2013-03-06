package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery;

import org.openqa.selenium.WebDriver;

import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class GalleryBuilderComponentObject extends BasePageObject{

	public GalleryBuilderComponentObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void adjustSize(int size){
		
	}
	
	public void adjustPosition(){
		
	}
	
	public void adjustColumns(){
		
	}
	
	public void adjustSpacing(){
		
	}
	
	public void adjustOrientation(){
		
	}
	
	public GalleryAddPhotoComponentObject clickAddPhoto(){
		return new GalleryAddPhotoComponentObject(driver);
	}
}

