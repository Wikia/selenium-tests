package com.wikia.webdriver.pageobjectsfactory.pageobject.videohomepage;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Liz Lee on 6/18/14.
 */
public class FeaturedVideoAdminPageObject extends WikiBasePageObject {

	@FindBy(css = ".featured-video:first-child")
	private WebElement featuredVideoForm;
	@FindBy(css = ".vpt-form button[type=submit]")
	private WebElement saveButton;

	public FeaturedVideoAdminPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public VetAddVideoComponentObject clickAddVideo() {
		waitForElementByElement(featuredVideoForm);
		WebElement addVideoButton = featuredVideoForm.findElement(By.cssSelector(".add-video-button"));
		addVideoButton.click();
		PageObjectLogging.log("VetAddVideoComponentObject", "Add video button clicked", true);
		return new VetAddVideoComponentObject(driver);
	}

	public void verifyVideoAdded(String name) {
		verifyVideoTitleUpdated(name);
		verifyVideoDisplayTitleUpdated(name);
		PageObjectLogging.log("verifyVideoAdded", "Video" + name + " was successfully added.", true);
	}

	public void verifyVideoTitleUpdated(String name) {
		waitForElementByElement(featuredVideoForm);
		WebElement videoTitle = featuredVideoForm.findElement(By.cssSelector(".video-title"));
		String title = videoTitle.getText();
		Assertion.assertEquals(title, name);
		PageObjectLogging.log("verifyVideoTitleUpdated", "Video title was updated", true);
	}

	public void verifyVideoDisplayTitleUpdated(String name) {
		waitForElementByElement(featuredVideoForm);
		WebElement displayTitle = featuredVideoForm.findElement(By.cssSelector(".display-title"));
		String title = displayTitle.getAttribute("value");
		Assertion.assertEquals(title, name);
		PageObjectLogging.log("verifyVideoDisplayTitleUpdated", "Video display title input was populated", true);
	}

	public LatestVideoAdminPageObject clickSaveFeaturedVideoForm(WebDriver driver) {
		scrollAndClick(saveButton);
		PageObjectLogging.log("clickSaveFeaturedVideoForm", "Featured video form has been saved", true);
		return new LatestVideoAdminPageObject(driver);
	}
}
