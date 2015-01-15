package com.wikia.webdriver.testcases.mercurytests;

import java.util.List;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
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
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;
import com.wikia.webdriver.common.templates.NewTestTemplate;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.internal.TouchAction;
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
		int poz = (int)((322 / 559f) * 1708f) + 212; //212 - bar z godziną
		System.out.println(x);
		System.out.println(y);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println(js.executeScript("return $(window).height()")); //Wysokość contentu który widoczny
		System.out.println(js.executeScript("return $(document).height()")); //wysokość aplikacji w rozdzielczości faktycznej np. FULL HD to 1000 pare
		System.out.println("C: " + 322 / 559f + " Start: " + poz);
		//System.out.println(mobileDriver.manage().window().getSize());
//		new TouchActions((WebDriver)mobileDriver).singleTap(element);
		mobileDriver.context("NATIVE_APP");


		mobileDriver.tap(1, 100, poz+15, 500);
		
//		mobileDriver.tap(1, 100, 2100, 500);
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
	
	//MT04 - NOT COMPLETED YET
		@Test(groups = {"MercuryLightboxTests_004", "MercuryLightboxTests", "Mercury"})
		public void MercuryLightboxTests_004_SwipeToNavigate() {
			MercuryBasePageObject base = new MercuryBasePageObject(driver);
			base.getUrl("http://wikia.com/");
			List<WebElement> hubs = driver.findElements(By.cssSelector("img.wkhome-img"));
			//hubs.get(4).click();
			AndroidDriver mobileDriver = NewDriverProvider.getMobileDriver();
			WebElement hub = hubs.get(0);
			try {
				Thread.sleep(3000);
				System.out.println("Before...");
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			PerformTouchAction touchAction = new PerformTouchAction(driver, mobileDriver);
//			touchAction.SwipeFromCenterToDirection(driver, mobileDriver, "left");
			try {
				Thread.sleep(3000);
				System.out.println("After...");
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
//			System.out.println(By.cssSelector("img.wkhome-img"));
//			PerformTouchAction.SwipeToWebElement(driver, mobileDriver, hub);
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//			PageObjectLogging.log("screen", "before", true, driver);
//			js.executeScript("window.scrollTo(0, 1338)");
//			PageObjectLogging.log("screen", "after", true, driver);
//			mobileDriver.context("NATIVE_APP");
			
//			mobileDriver.tap(1, hub, 300);
//			mobileDriver.
//			mobileDriver.context("WEBVIEW_1");
			
		}
		
		//MT04 - NOT COMPLETED YET
		@Test(groups = {"MercuryLightboxTests_004", "MercuryLightboxTests", "Mercury"})
		public void MercuryLightboxTests_005_SwipeToNavigate() {
			AndroidDriver mobileDriver = NewDriverProvider.getMobileDriver();
			MercuryBasePageObject base = new MercuryBasePageObject(driver);
			MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_ARTICLE);
			PerformTouchAction touchAction = new PerformTouchAction(driver, mobileDriver);
			
			LightBoxMercuryComponentObject lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
			
			
			
			
			try {
				Thread.sleep(5000);
				System.out.println("S1...");
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
//			System.out.println(lightbox.currentImageSrcPath());
//			System.out.println(lightbox.currentImageSrcPath());
			System.out.println("zoom");
			
			
//			touchAction.TapOnPointXY(mobileDriver, 40, 60, 500, 0);
//			touchAction.TapOnWebElement(driver, mobileDriver, By.cssSelector("img.loaded"), 0, 500, 0);
			
//			mobileDriver.context("NATIVE_APP");
//			mobileDriver.tap(1, 400, 1100, 500);
//			mobileDriver.context("WEBVIEW_1");
			
			
//			
			
			/*********************** FOR LUDWIK *****************************/
			touchAction.ZoomInOutPointXY(mobileDriver, 50, 50, 50, 100, "in", 2000); //It should zoom in center
			touchAction.ZoomInOutPointXY(mobileDriver, 50, 50, 50, 100, "out", 0); //It should zoom out but it doesn't
			
			
			
			
			
//			TouchAction ta1 = new TouchAction(mobileDriver);
//			TouchAction ta2 = new TouchAction(mobileDriver);
//			MultiTouchAction mt = new MultiTouchAction(mobileDriver);
//			
//			mobileDriver.context("NATIVE_APP");
//			ta1.press(400, 1000).moveTo(200, 1000).release();
//			ta2.press(500, 1000).moveTo(700, 1000).release();
//			ta2.moveTo(700, 1000);
//			ta1.perform();
//			ta2.perform();
			
//			mt.add(new TouchAction(mobileDriver).press(400, 1000).moveTo(0, 1000).release());
////			mt.add(new TouchAction(mobileDriver).press(500, 1000).moveTo(900, 1000).release());
//			mt.add(ta1);
//			mt.add(ta2);
//			mt.perform();
//			mobileDriver.zoom(500, 1000);
//			touchAction.SwipeFromCenterToDirection(mobileDriver, PerformTouchAction.DIRECTION_LEFT, 200, 300, 0);
//			touchAction.SwipeFromCenterToDirection(mobileDriver, PerformTouchAction.DIRECTION_RIGHT, 200, 300, 0);
//			mobileDriver.context("WEBVIEW_1");
			
			
			
//			mobileDriver.context("NATIVE_APP");
//			mobileDriver.zoom(500, 1000);
//			mobileDriver.performMultiTouchAction(multiAction);
//			mobileDriver.context("WEBVIEW_1");
			
//			touchAction.SwipeFromCenterToDirection(mobileDriver, "up", 500, 1000, 2000);
//			touchAction.SwipeFromCenterToDirection(mobileDriver, "down", 500, 1000, 2000);
//			touchAction.SwipeFromCenterToDirection(mobileDriver, PerformTouchAction.DIRECTION_LEFT, 500, 300, 5000);
//			touchAction.SwipeFromCenterToDirection(mobileDriver, "right", 500, 300, 5000);
//			touchAction.SwipeFromCenterToDirection(mobileDriver, "right", 500, 300, 5000);
//			touchAction.SwipeFromCenterToDirection(mobileDriver, "right", 500, 300, 5000);
//			touchAction.SwipeFromCenterToDirection(mobileDriver, "right", 500, 300, 5000);
//			touchAction.SwipeFromCenterToDirection(mobileDriver, "left", 500, 300, 2000);
			
//			lightbox.verifyCurrentImageIsVisible();
//			touchAction.SwipeFromPointToPoint(mobileDriver, 50, 80, 50, 20, 1000, 3000);
//			touchAction.SwipeFromCenterToDirection(driver, mobileDriver, "left", 150, 100);
//			mobileDriver.context("NATIVE_APP");
//			mobileDriver.swipe(500, 1700, 500, 500, 1000);
//			mobileDriver.context("WEBVIEW_1");
			
			//lightbox.verifyImageWasChanged(imageOnePath, imageTwoPath);
//			try {
//				Thread.sleep(5000);
//				System.out.println("S2...");
//			} catch(InterruptedException ex) {
//				Thread.currentThread().interrupt();
//			}
//			touchAction.SwipeFromCenterToDirection(driver, mobileDriver, "left", 100);
			
//			mobileDriver.context("NATIVE_APP");
//			mobileDriver.swipe(500, 500, 500, 1700, 1200);
//			mobileDriver.context("WEBVIEW_1");
			
//			touchAction.SwipeFromPointToPoint(mobileDriver, 50, 20, 50, 80, 1000, 3000);
			
			try {
				Thread.sleep(5000);
				System.out.println("After...");
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
//			System.out.println(lightbox.currentImageSrcPath());
			
		}

		
	@Test(groups = {"MercuryLightboxTests_005", "MercuryLightboxTests", "Mercury"})
	public void MercuryLightboxTests_005_DoubleTapZoomImage() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_ARTICLE);
		LightBoxMercuryComponentObject lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.testGestures();
	}
}
