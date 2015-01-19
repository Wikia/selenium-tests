package com.wikia.webdriver.testcases.mercurytests;

import java.util.List;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.driverprovider.NewDriverProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.LightBoxMercuryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

	@Test(groups = {"MercuryLightboxTests_001", "MercuryLightboxTests", "Mercury"})
	public void MercuryLightboxTests_001_TappingImageOpenLightbox() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_TWO);
		LightBoxMercuryComponentObject lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.verifyCurrentImageIsVisible();
	}

	@Test(groups = {"MercuryLightboxTests_002", "MercuryLightboxTests", "Mercury"})
	public void MercuryLightboxTests_002_TappingCloseButtonCloseLightbox() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_TWO);
		LightBoxMercuryComponentObject lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.verifyCurrentImageIsVisible();
		lightbox.clickCloseButton();
		lightbox.verifyLightboxClosed();
	}

	//MT03
	@Test(groups = {"MercuryLightboxTests_003", "MercuryLightboxTests", "Mercury"})
	public void MercuryLightboxTests_003_SwipeChangeImages() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_TWO);
		PerformTouchAction touchAction = new PerformTouchAction(driver);
		LightBoxMercuryComponentObject lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.verifyCurrentImageIsVisible();
		lightbox.verifySwiping(touchAction, touchAction.DIRECTION_LEFT, 10);
		lightbox.verifySwiping(touchAction, touchAction.DIRECTION_RIGHT, 10);
	}

	//MT04 - NOT COMPLETED YET
	@Test(groups = {"MercuryLightboxTests_004", "MercuryLightboxTests", "Mercury"})
	public void MercuryLightboxTests_004_ZoomThroughPanninAndDoubleTapping() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_TWO);
		PerformTouchAction touchAction = new PerformTouchAction(driver);
		
		LightBoxMercuryComponentObject lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
			
		try {
			Thread.sleep(5000);
			System.out.println("Before...");
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		System.out.println("zoom");
		

		/*********************** FOR LUDWIK *****************************/
		touchAction.ZoomInOutPointXY(50, 50, 50, 100, "in", 2000); //It should zoom in center
		touchAction.ZoomInOutPointXY(50, 50, 50, 100, "out", 0); //It should zoom out but it doesn't
	
		
		try {
			Thread.sleep(5000);
			System.out.println("After...");
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		
	}
	
	//MT05
	@Test(groups = {"MercuryLightboxTests_005", "MercuryLightboxTests", "Mercury"})
	public void MercuryLightboxTests_005_TapOnCenterShowHideUI() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_TWO);
		PerformTouchAction touchAction = new PerformTouchAction(driver);
		LightBoxMercuryComponentObject lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.verifyCurrentImageIsVisible();
		lightbox.verifyVisibilityUI(touchAction);
	}

	//MT06
	@Test(groups = {"MercuryLightboxTests_006", "MercuryLightboxTests", "Mercury"})
	public void MercuryLightboxTests_006_TapOnLeftRightSpaceNearEdgeChangeImage() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_TWO);
		PerformTouchAction touchAction = new PerformTouchAction(driver);
		LightBoxMercuryComponentObject lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.verifyCurrentImageIsVisible();
		lightbox.verifyTappingOnImageEdge(touchAction, "left");
		lightbox.verifyTappingOnImageEdge(touchAction, "right");
	}
	
	//MT10 - NOT COMPLETED YET
	@Test(groups = {"MercuryLightboxTests_010", "MercuryLightboxTests", "Mercury"})
	public void MercuryLightboxTests_010_RunAllTestInLanscapeMode() {
		
		
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_TWO);
		AndroidDriver mobileDriver = NewDriverProvider.getMobileDriver();
		
		
		if (mobileDriver.getContext() != "NATIVE_APP") {
			mobileDriver.context("NATIVE_APP");
		}
		mobileDriver.rotate(ScreenOrientation.LANDSCAPE);
		if (mobileDriver.getContext() != "WEBVIEW_1") {
			mobileDriver.context("WEBVIEW_1");
		}
		LightBoxMercuryComponentObject lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.verifyCurrentImageIsVisible();
		if (mobileDriver.getContext() != "NATIVE_APP") {
			mobileDriver.context("NATIVE_APP");
		}
		mobileDriver.rotate(ScreenOrientation.PORTRAIT);
		if (mobileDriver.getContext() != "WEBVIEW_1") {
			mobileDriver.context("WEBVIEW_1");
		}
		
		
		
		
	}
		
	@Test(groups = {"MercuryLightboxTests_005", "MercuryLightboxTests", "Mercury"})
	public void MercuryLightboxTests_005_DoubleTapZoomImage() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_TWO);
		LightBoxMercuryComponentObject lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.testGestures();
	}
}
