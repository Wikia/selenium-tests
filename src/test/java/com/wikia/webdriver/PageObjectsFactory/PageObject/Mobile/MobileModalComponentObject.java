package com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MobileModalComponentObject extends MobileBasePageObject {

	public MobileModalComponentObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css="#wkMdlClo")
	private WebElement closeModalButton;

	@FindBy(css="#prvImg")
	private WebElement previousImageButton;

	@FindBy(css="#nxtImg")
	private WebElement nextImageButton;

	@FindBy(css="section.current>img")
	private WebElement currentImage;

	@FindBy(css=".imgMdl.zoomer.open")
	private WebElement topBarVisible;

	@FindBy(css=".imgMdl.zoomer.open.hdn")
	private WebElement topBarHidden;


	public MobileArticlePageObject closeModal(){
		waitForElementByElement(closeModalButton);
		return new MobileArticlePageObject(driver);
	}

	public void goToPreviousImage(){
		waitForElementByElement(previousImageButton);
		previousImageButton.click();
	}

	public void goToNextImage(){
		waitForElementByElement(nextImageButton);
		nextImageButton.click();
	}

	public String getCurrentImageUrl(){
		return currentImage.getAttribute("src");
	}


	public void verifyTopBarVisible(){
		waitForElementByElement(topBarVisible);
	}

	public void verifyTopBarHidden(){
		waitForElementByElement(topBarHidden);
		waitForElementNotVisibleByElement(topBarVisible);
	}


	public void hideTopBar(){
		waitForElementByElement(topBarVisible);
		topBarVisible.click();
	}

	public void showTopBar(){
		waitForElementByElement(topBarHidden);
		topBarHidden.click();
	}




}
