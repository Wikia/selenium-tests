package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class VetAddVideoComponentObject extends BasePageObject{

	
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
	@FindBy(css="#VideoEmbedUrlSubmit")
	private WebElement addUrlButton;
	
	//wiki videos
	@FindBy(css="#VET-search-field")
	private WebElement findField;
	@FindBy(css="#VET-search-submit")
	private WebElement findButton;	
	
	public VetAddVideoComponentObject(WebDriver driver) {
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
