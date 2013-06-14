package com.wikia.webdriver.PageObjectsFactory.ComponentObject.AddPhoto;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
/**
 * class for adding photo to gallery, slider and slideshow
 * @author Karol 'kkarolk' Kujawiak
 */
public class AddPhotoComponentObject extends BasePageObject{

	@FindBy(css=".WikiaSearch#WikiaPhotoGallerySearch input[name='search']")
	private WebElement searchField;
	@FindBy(css="#WikiaPhotoGallerySearch img.search")
	private WebElement searchButton;
	@FindBy(css="#WikiaPhotoGallerySearchResultsSelect")
	private WebElement selectButton;

	private By galleryDialogPhotosList = By
			.cssSelector("ul[class='WikiaPhotoGalleryResults'][type='results'] li");

	public AddPhotoComponentObject(WebDriver driver) {
		super(driver);
	}

	private void typeSearchQuery(String query){
		waitForElementByElement(searchField);
		searchField.sendKeys(query);
		PageObjectLogging.log("typeSearchQuery", query+" search query typed in", true);
	}

	private void clickSearchButton(){
		waitForElementByElement(searchButton);
		searchButton.click();
		PageObjectLogging.log("clickSearchButton", "search button clicked", true);
	}

	public void search(String query){
		typeSearchQuery(query);
		clickSearchButton();
	}

	public List<String> choosePhotos(int photoNum){
		driver.findElement(galleryDialogPhotosList);
		List photoNames = new ArrayList<String>();
		List<WebElement> List = driver.findElements(galleryDialogPhotosList);
		for (int i = 0; i < photoNum; i++) {
			clickAndWait(List.get(i).findElement(By.cssSelector("[type=checkbox]")));
			photoNames.add(List.get(i).getAttribute("title"));
		}
		PageObjectLogging.log("CheckGalleryImageInputs", "Check first " + photoNum
				+ " image inputs", true, driver);
		return photoNames;
	}


	public void clickSelect(){
		waitForElementByElement(selectButton);
		selectButton.click();
		PageObjectLogging.log("clickSelect", "select button clicked", true);
	}
}
