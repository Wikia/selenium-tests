package com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class MobileCategoryPageObject extends MobileBasePageObject {

	public MobileCategoryPageObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// UI Mapping

	@FindBy(css = "#expAll:not(.exp)")
	private WebElement showAllButton;

	@FindBy(css = "#expAll.exp")
	private WebElement hideAllButton;

	@FindBy(css = "h2.collSec")
	private List<WebElement> chevronList;

	public MobileArticlePageObject openCategory(){
		getUrl(Global.DOMAIN+"wiki/Category:PMG");
		waitForElementByElement(showAllButton);
		PageObjectLogging.log("openCategory", "category page was opened", true, driver);
		return new MobileArticlePageObject(driver);
	}

	public void clickShowAllButton(){
		waitForElementByElement(showAllButton);
		showAllButton.click();
	}

	public void clickHideAllButton (){
		waitForElementByElement(hideAllButton);
		hideAllButton.click();
	}

	public void verifyChevronOpened(){
		for(WebElement elem:chevronList){
			Assertion.assertEquals("collSec open", elem.getAttribute("class"));
		}
	}

	public void verifyChevronClosed(){
		for(WebElement elem:chevronList){
			Assertion.assertNotEquals("collSec open", elem.getAttribute("class"));
		}
	}

	public void verifyShowAll (){
		waitForElementByElement(showAllButton);
	}

	public void verifyHideAll() {
		waitForElementByElement(hideAllButton);
	}

}
