package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class VetComponentObject extends BasePageObject{

	
	/*
	 *This class will cover functionalities in VET modal 
	 *It's available from:
	 *Article placeholder (),
	 *MiniEditor - Blogs,
	 *MiniEditor - Comments,
	 *MiniEditor - Message Wall,
	 *
	 */
	
	//provider
	@FindBy(css="#VideoEmbedUrl")
	private WebElement urlField;	
	@FindBy(css="#VideoEmbedUrl")
	private WebElement addUrlButton;
	
	//wiki videos
	@FindBy(css="#VET-search-field")
	private WebElement findField;
	@FindBy(css="#VET-search-submit")
	private WebElement findButton;
	
	//generic
	@FindBy(css="#VideoEmbedHeader")
	private WebElement videoOptionsHeader;
	@FindBy(css=".vet-style-label.VideoEmbedThumbOption")
	private WebElement styleWithCation;
	@FindBy(css="#VideoEmbedCaption")
	private WebElement captionField;
	@FindBy(css=".vet-style-label.VideoEmbedNoThumbOption")
	private WebElement styleWithoutCation;
	
	
	
	public VetComponentObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,  this);
	}
	
	
	/**
	 * for provider
	 * @param url
	 */
	private void typeInUrl(String url){
		waitForElementByElement(urlField);
		urlField.sendKeys(url);
		PageObjectLogging.log("typeInUrl", url+" typed into url field", true);
	}
	
	/**
	 * for provider
	 */
	private void clickAddButton(){
		waitForElementByElement(addUrlButton);
		clickAndWait(addUrlButton);
		PageObjectLogging.log("clickAddButton", "add url button clicked", true, driver);
	}
	
	/**
	 * for provider
	 */
	/**
	 * @param i
	 * i = 1; with caption
	 * i = 2; without caption 
	 */
	public void adjustStyle(int i){
		waitForTextToBePresentInElementByElement(videoOptionsHeader, "Video display options");
		switch (i){
		case 1: styleWithCation.click();
				PageObjectLogging.log("adjustStyle", "first style selected",  true);
				break;
		case 2: styleWithoutCation.click();
				PageObjectLogging.log("adjustStyle", "second style selected",  true);
				break;
		default: PageObjectLogging.log("adjustStyle", "invalid style selected",  false);
		}
	}
	
	/**
	 * for wiki videos
	 * @param query
	 */
	private void typeInSearchQuery(String query){
		waitForElementByElement(findField);
		findField.sendKeys(query);
		PageObjectLogging.log("typeInSearchQuery", query+" query typed into search video field", true);
	}
	
	/**
	 * for wiki videos
	 */
	private void clickFindButton(){
		waitForElementByElement(findButton);
		clickAndWait(findButton);
		PageObjectLogging.log("clickFindButton", "find button clicked", true, driver);
	}

	/**
	 * for provider
	 * @param url
	 */
	public void addVideoByUrl(String url){
		typeInUrl(url);
		clickAddButton();
	}
	
	/**
	 * for wiki videos
	 * @param query
	 */
	public void addVideoByQuery(String query){
		typeInSearchQuery(query);
		clickFindButton();
	}
	

}
