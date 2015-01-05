package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.LightBoxMercuryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import com.wikia.webdriver.common.templates.NewTestTemplate;
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
		LightBoxMercuryComponentObject lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		String imageOne = lightbox.getCurrentImagePath();
		lightbox.swipeImageLeft();
		String imageTwo = lightbox.getCurrentImagePath();
		lightbox.verifyImageWasChanged(imageOne, imageTwo);
	}

	@Test(groups = {"MercuryLightboxTests_004", "MercuryLightboxTests", "Mercury"})
	public void MercuryLightboxTests_004_SwipeRight() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_ARTICLE);
		LightBoxMercuryComponentObject lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		String imageOne = lightbox.getCurrentImagePath();
		lightbox.swipeImageRight();
		String imageTwo = lightbox.getCurrentImagePath();
		lightbox.verifyImageWasChanged(imageOne, imageTwo);
	}

	@Test(groups = {"MercuryLightboxTests_005", "MercuryLightboxTests", "Mercury"})
	public void MercuryLightboxTests_005_DoubleTapZoomImage() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_ARTICLE);
		LightBoxMercuryComponentObject lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.testGestures();
	}
}
