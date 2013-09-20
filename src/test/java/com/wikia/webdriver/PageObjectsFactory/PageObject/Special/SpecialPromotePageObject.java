package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticleHomePage;

public class SpecialPromotePageObject extends BasePageObject {

	@FindBy(css="#curMainImageName")
	protected WebElement specialPromoteThumbnail;
	@FindBy(css=".description-wrapper")
	protected WebElement specialPromoteDescription;
	
	
	public SpecialPromotePageObject(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void verifyCrossWikiSearchDescription(String firstDescription) {
		waitForElementByElement(specialPromoteDescription);
		Assertion.assertStringContains(
			specialPromoteDescription.getText(),
			firstDescription.substring(0,
			firstDescription.length()-3)
		);
	}
	
	public void verifyCrossWikiSearchImage(String firstImage) {
		waitForElementByElement(specialPromoteThumbnail);
		String secondImage = getUniqueThumbnailTextSpecialPromotePage();
		Assertion.assertEquals(firstImage, secondImage);
	}
	
	public String getUniqueThumbnailTextSpecialPromotePage() {
		int indexComparisonStart = specialPromoteThumbnail.getAttribute("src").indexOf("px-");
		int indexComparisonFinish = specialPromoteThumbnail.getAttribute("src").indexOf("-Wikia-Visualization-Main");
		return specialPromoteThumbnail.getAttribute("src").substring(indexComparisonStart + 3, indexComparisonFinish - 1);
	}

}
