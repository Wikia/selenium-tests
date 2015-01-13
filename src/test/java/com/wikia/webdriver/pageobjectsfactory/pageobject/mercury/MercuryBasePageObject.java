package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.HashMap;

public class MercuryBasePageObject extends MobileBasePageObject{

	public MercuryBasePageObject(WebDriver driver) {
		super(driver);
	}

	public SpecialMercuryPageObject openSpecialMercury(String wikiURL) {
		getUrl(wikiURL + MercuryContent.mercurySpecialPage);
		PageObjectLogging.log("openSpecialMercury", MercuryContent.mercurySpecialPage + " opened", true);
		return new SpecialMercuryPageObject(driver);
	}

	public MercuryArticlePageObject openMercuryArticleByName(String wikiURL, String articleName) {
		getUrl(wikiURL + URLsContent.WIKI_DIR + articleName);
		PageObjectLogging.log("openMercuryArticleByName", "Article" + articleName + " was opened", true);
		return new MercuryArticlePageObject(driver);
	}

	public void openMercuryWiki(String wikiURL, String wikiName){
		wikiURL = urlBuilder.getUrlForWiki(wikiName);
		getUrl(wikiURL);
		//return new MercuryArticlePageObject(driver);
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
//		Actions flick = new Actions(driver);
//		flick.click(element).perform();
	}

	public void doubleTapZoom(WebElement element) {
		Actions doubleTapZoom = new Actions(driver);
		doubleTapZoom.doubleClick(element).build().perform();
	}

	public void swipeLeft(WebElement element) {
		Actions swipeAction = new Actions(driver);
		swipeAction.dragAndDropBy(element, -100, 0).build().perform();
	}

	public void swipeRight(WebElement element) {
		Actions swipeAction = new Actions(driver);
		swipeAction.dragAndDropBy(element, +100, 0).build().perform();
	}

}
