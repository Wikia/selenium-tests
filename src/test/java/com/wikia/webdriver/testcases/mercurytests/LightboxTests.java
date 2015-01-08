package com.wikia.webdriver.testcases.mercurytests;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.driverprovider.NewDriverProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.gallery.GalleryBuilderComponentObject.Orientation;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.LightBoxMercuryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import com.wikia.webdriver.common.templates.NewTestTemplate;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.TouchAction;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
* @ownership: Mobile Web
* @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
* */
public class LightboxTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@BeforeMethod(alwaysRun = true)
	public void optInMercury() {
		MercuryContent.turnOnMercurySkin(driver, wikiURL);
	}

	//MT01
	@Test(groups = {"MercuryLightboxTests_001", "MercuryLightboxTests", "Mercury"})
	public void MercuryLightboxTests_001_TappingImageOpenLightbox() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_ARTICLE);
		LightBoxMercuryComponentObject lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.verifyCurrentImageIsVisible();
	}

	//MT02
	@Test(groups = {"MercuryLightboxTests_002", "MercuryLightboxTests", "Mercury"})
	public void MercuryLightboxTests_002_TappingCloseButtonCloseLightbox() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_ARTICLE);
		LightBoxMercuryComponentObject lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.verifyCurrentImageIsVisible();
		lightbox.clickCloseButton();
		lightbox.verifyLightboxClosed();
	}

	@Test(groups = {"MercuryLightboxTests_003", "MercuryLightboxTests", "Mercury"})
	public void MercuryLightboxTests_003_SwipeLeft() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_ARTICLE);

		//LightBoxMercuryComponentObject lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		//lightbox.verifyCurrentImageIsVisible();
		//MobileDriver driver2 = (MobileDriver)driver;
		//MobileDriver mobileDriver = (MobileDriver) NewDriverProvider.getDriverInstanceForBrowser("ANDROID");
//		PageObjectLogging.log("Scroll", "Before", true, driver);
		try {
			Thread.sleep(5000);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		AndroidDriver mobileDriver = NewDriverProvider.getMobileDriver();

		WebElement element = mobileDriver.findElement(By.xpath("//img[contains(@class, 'loaded')]"));
		int x = element.getLocation().x;
		int y = element.getLocation().y;
		int poz = (int)((322 / 559f) * 1708f) + 212;
		System.out.println(x);
		System.out.println(y);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println(js.executeScript("return $(window).height()"));
		System.out.println(js.executeScript("return $(document).height()"));
		System.out.println("C: " + 322 / 559f + " Start: " + poz);
		//System.out.println(mobileDriver.manage().window().getSize());
//		new TouchActions((WebDriver)mobileDriver).singleTap(element);
		mobileDriver.context("NATIVE_APP");

//		mobileDriver.tap(1, 100, poz+15, 500);
		mobileDriver.tap(1, 100, 2100, 500);
//		mobileDriver.zoom(x, y*2+100);
//		mobileDriver.swipe(0, poz, 0, 212, 1707);
//		mobileDriver.scrollTo("Top Contributors");
		
		//mobileDriver.lockScreen(10);
		//mobileDriver.rotate(ScreenOrientation.PORTRAIT);
		//mobileDriver.pi
		System.out.println(mobileDriver.manage().window().getSize());
//		mobileDriver.scrollTo("Comments");
//		TouchActions touch = new TouchActions(driver);
//		touch.scroll(0, 50);
		mobileDriver.context("WEBVIEW_1");
//		System.out.println(js.executeScript("return $(document).height()"));
		//touch.scroll(0, 50);
		//lightbox.tapOnElement(element,0,200);
		try {
			Thread.sleep(5000);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
//		System.out.println(js.executeScript("return $(document).height()"));
//		PageObjectLogging.log("Scroll", "After", true, driver);

	}

	@Test(groups = {"MercuryLightboxTests_005", "MercuryLightboxTests", "Mercury"})
	public void MercuryLightboxTests_005_DoubleTapZoomImage() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_ARTICLE);
		LightBoxMercuryComponentObject lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.testGestures();
	}
}
