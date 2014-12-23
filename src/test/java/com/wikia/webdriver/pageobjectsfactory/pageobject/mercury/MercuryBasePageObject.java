package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebElement;

public class MercuryBasePageObject extends WikiBasePageObject{

	public MercuryBasePageObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public SpecialMercuryPageObject openSpecialMercury(String wikiURL) {
		getUrl(wikiURL + MercuryContent.mercurySpecialPage);
		PageObjectLogging.log("openSpecialMercury", MercuryContent.mercurySpecialPage + " opened", true);
		return new SpecialMercuryPageObject(driver);
	}

	public MercuryArticlePageObject openMercuryArticleByName(String wikiURL, String articleName) {
		getUrl(wikiURL + URLsContent.WIKI_DIR + articleName);
		PageObjectLogging.log("openMercuryArticleByName", articleName + " was opened", true);
		return new MercuryArticlePageObject(driver);
	}
	/* That how should it work with AndroidDriver
	* element.tap()
	element.zoom()
	element.pinch()
	element.swipe(direction, int)
	*/
	public void tapOnElement(WebElement element) {
		JavascriptExecutor jsexec = (JavascriptExecutor)driver;
		jsexec.executeScript("arguments[0].click();", element);
	}

}
