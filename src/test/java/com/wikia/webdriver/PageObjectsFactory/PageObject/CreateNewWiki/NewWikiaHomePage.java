package com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
 *
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class NewWikiaHomePage extends WikiBasePageObject {

	@FindBy(css = "section#WikiWelcomeWrapper")
	private WebElement welcomeWrapper;
	@FindBy(css = "div#WikiWelcome")
	private WebElement welcomeLightbox;

	public NewWikiaHomePage(WebDriver driver){
		super(driver);
	}

	public void waitForCongratulationsLightBox(String wikiaName) {
		waitForElementByElement(welcomeWrapper);
		waitForElementByElement(welcomeLightbox);
		PageObjectLogging.log("waitForCongratulationsLightBox ", "congratulations lightbox verified", true);
	}

	public void closeCongratulationsLightBox() {
		waitForElementByElement(closeModalButton);
		scrollAndClick(closeModalButton);
		PageObjectLogging.log("closeCongratulationsLightBox ", "congratulations lightbox closed", true);
	}
}
