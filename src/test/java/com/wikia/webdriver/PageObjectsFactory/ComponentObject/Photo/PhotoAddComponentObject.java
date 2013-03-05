package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class PhotoAddComponentObject extends BasePageObject{

	@FindBy(css="#ImageQuery")
	private WebElement searchField;
	@FindBy(css="#ImageUploadFind [value='Find']")
	private WebElement searchButton;
	@FindBy(css="#ImageUploadProgress2")
	private WebElement searchProgressThrobber;
	@FindBys(@FindBy(css="tr.ImageUploadFindLinks td a"))
	private List<WebElement> addThisPhotoList;
	
	
	public PhotoAddComponentObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private void typeSearchQuery(String photoName){
		waitForElementByElement(searchField);
		searchField.sendKeys(photoName);
		PageObjectLogging.log("typeSearchQuery", photoName+" searching", true);
	}
	
	private void clickSearch(){
		waitForElementByElement(searchButton);
		clickAndWait(searchButton);
		waitForElementVisibleByElement(searchProgressThrobber);
		waitForElementNotVisibleByElement(searchProgressThrobber);
		PageObjectLogging.log("clickSearch", "search button clicked", true);
	}
	
	private void clickAddPhoto(int photoNumber){
		waitForElementByElement(addThisPhotoList.get(photoNumber));
		clickAndWait(addThisPhotoList.get(photoNumber));
		PageObjectLogging.log("clickAddPhoto", "add photo button clicked", true);
	}
	
	public PhotoOptionsComponentObject addPhotoFromWiki(String photoName, int photoNumber){
		typeSearchQuery(photoName);
		clickAddPhoto(photoNumber);
		return new PhotoOptionsComponentObject(driver);
	}
	
	private void clickThisWiki(){
		
	}
	
	private void clickFlickr(){
		
	}
}