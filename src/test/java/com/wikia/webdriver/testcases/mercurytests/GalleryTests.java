package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.LightBoxMercuryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryArticlePageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.properties.Credentials;


/*
* @ownership: Mobile Web
* @authors: Rodrigo Gomez, Łukasz Nowak
* */
public class GalleryTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@BeforeMethod(alwaysRun = true)
	public void optInMercury() {
		MercuryContent.turnOnMercurySkin(driver, MercuryContent.MERCURY_WIKI);
	}

	@Test(groups = {"MercuryGalleryTests_001", "MercuryGalleryTests", "Mercury"})
	public void MercuryGalleryTests_001_PhotoInLightboxIsVisible() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_ARTICLE);
		MercuryArticlePageObject gallery = new MercuryArticlePageObject(driver);
		LightBoxMercuryComponentObject lightbox = gallery.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.verifyCurrentImageIsVisible();
	}

	@Test(groups = {"MercuryGallerYTests_002", "MercuryGalleryTests", "Mercury"})
	public void MercuryGalleryTests_002_VerifyCloseButtonWillCloseLightbox() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_ARTICLE);
		MercuryArticlePageObject gallery = new MercuryArticlePageObject(driver);
		LightBoxMercuryComponentObject lightbox = gallery.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.verifyCurrentImageIsVisible();
		base = lightbox.clickCloseButton();
		lightbox.verifyLightboxClosed();
	}

}
