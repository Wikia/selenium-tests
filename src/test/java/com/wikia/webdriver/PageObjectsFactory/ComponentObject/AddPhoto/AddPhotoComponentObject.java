package com.wikia.webdriver.PageObjectsFactory.ComponentObject.AddPhoto;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slider.SliderBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slideshow.SlideshowBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
/**
 * class for adding photo to gallery, slider and slideshow
 * @author Karol 'kkarolk' Kujawiak
 */
public class AddPhotoComponentObject extends BasePageObject{

	@FindBy(css=".WikiaSearch input[name='search']")
	private WebElement searchField; 
	@FindBy(css="#WikiaPhotoGallerySearch img.search")
	private WebElement searchButton; 
	@FindBy(css="#WikiaPhotoGallerySearchResultsSelect")
	private WebElement selectButton; 
	
	private By searchThrobber = By
			.cssSelector(".WikiaPhotoGalleryProgress#WikiaPhotoGallerySearchResults");
	private By galleryDialogPhotosList = By
			.cssSelector("ul[class='WikiaPhotoGalleryResults'][type='results'] li input");
	
	public AddPhotoComponentObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private void typeSearchQuery(String query){
		waitForElementByElement(searchField);
		searchField.sendKeys(query);
		PageObjectLogging.log("typeSearchQuery", query+" search query typed in", true);
	}
	
	private void clickSearchButton(){
		waitForElementByElement(searchButton);
		searchButton.click();
		waitForElementByBy(searchThrobber);
		PageObjectLogging.log("clickSearchButton", "search button clicked", true);
	}
	
	public void search(String query){
		typeSearchQuery(query);
		clickSearchButton();
	}
	
	public void choosePhotos(int photoNum){
		driver.findElement(galleryDialogPhotosList);
		List<WebElement> List = driver.findElements(galleryDialogPhotosList);
		for (int i = 0; i < photoNum; i++) {
			clickAndWait(List.get(i));
		}
		PageObjectLogging.log("CheckGalleryImageInputs", "Check first " + photoNum
				+ " image inputs", true, driver);
	}
	
	public enum Components {
		Gallery, Slideshow, Slider}
	
	public Object clickSelect(Components comp){
		waitForElementByElement(selectButton);
		selectButton.click();
		PageObjectLogging.log("clickSelect", "select button clicked", true);
		switch(comp){
		case Gallery: 
			return new GalleryBuilderComponentObject(driver);
		case Slideshow:
			return new SlideshowBuilderComponentObject(driver);
		case Slider:
			return new SliderBuilderComponentObject(driver);
		default:
			return null;
		}
	}
}
