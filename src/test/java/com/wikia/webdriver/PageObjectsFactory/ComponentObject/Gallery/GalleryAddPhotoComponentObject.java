package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery;

import org.openqa.selenium.WebDriver;

import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class GalleryAddPhotoComponentObject extends BasePageObject{

	public GalleryAddPhotoComponentObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private void typeSearchQuery(){
		
	}
	
	private void clickSearchButton(){
		
	}
	
	public void choosePhoto(int photoNum){
		
	}
	
	public GalleryBuilderComponentObject clickSelect(){
		return new GalleryBuilderComponentObject(driver);
	}
}
