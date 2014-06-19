package com.wikia.webdriver.PageObjectsFactory.PageObject.VideoHomePage;

import com.google.sitebricks.client.Web;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by liz_lux on 6/18/14.
 */
public class FeaturedVideoAdminPageObject extends WikiBasePageObject {

	@FindBy(css=".featured-video:first-child")
	private WebElement featuredVideoForm;
	@FindBy(css=".WikiaForm input[type=submit]")
	private WebElement saveButton;

	public FeaturedVideoAdminPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public VetAddVideoComponentObject clickAddVideo() {
		waitForElementByElement(featuredVideoForm);
		WebElement addVideoButton = featuredVideoForm.findElement(By.cssSelector(".add-video-button"));
		addVideoButton.click();
		PageObjectLogging.log("VetAddVideoComponentObject", "Add video button clicked", true );
		return new VetAddVideoComponentObject(driver);
	}

	public void verifyVideoAdded(String name) {
		waitForElementByElement(featuredVideoForm);
		WebElement videoTitle = featuredVideoForm.findElement(By.cssSelector(".video-title"));
		String title = videoTitle.getText();
		Assertion.assertEquals(title, name);
		PageObjectLogging.log("verifyVideoAdded", "Video" + name + " was successfully added.", true);
	}

	public LatestVideoAdminPageObject clickSaveFeaturedVideoForm(WebDriver driver) {
		scrollAndClick(saveButton);
		PageObjectLogging.log("clickSaveFeaturedVideoForm", "Featured video form has been saved", true);
		return new LatestVideoAdminPageObject(driver);
	}
}
