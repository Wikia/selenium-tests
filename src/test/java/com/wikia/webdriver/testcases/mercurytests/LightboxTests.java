package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.LightBoxMercuryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;

/**
* @ownership: Mobile Web
* @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
*/
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
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_TWO);
		LightBoxMercuryComponentObject lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.verifyCurrentImageIsVisible();
	}

	//MT02
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
		lightbox.verifySwiping(touchAction, PerformTouchAction.DIRECTION_LEFT, 10);
		lightbox.verifySwiping(touchAction, PerformTouchAction.DIRECTION_RIGHT, 10);
	}
	
	//MT04
	@Test(groups = {"MercuryLightboxTests_004", "MercuryLightboxTests", "Mercury"})
	public void MercuryLightboxTests_004_ZoomThroughPanninAndDoubleTapping() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_TWO);
		PerformTouchAction touchAction = new PerformTouchAction(driver);
		LightBoxMercuryComponentObject lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.verifyCurrentImageIsVisible();
		lightbox.verifyZoomingByGesture(touchAction, LightBoxMercuryComponentObject.ZOOM_METHOD_GESTURE);
		lightbox.clickCloseButton();
		lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.verifyCurrentImageIsVisible();
		lightbox.verifyZoomingByGesture(touchAction, LightBoxMercuryComponentObject.ZOOM_METHOD_TAP);
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
		lightbox.verifyTappingOnImageEdge(touchAction, LightBoxMercuryComponentObject.EDGE_LEFT);
		lightbox.verifyTappingOnImageEdge(touchAction, LightBoxMercuryComponentObject.EDGE_RIGHT);
	}
	
	//MT08
	@Test(groups = {"MercuryLightboxTests_008", "MercuryLightboxTests", "Mercury"})
	public void MercuryLightboxTests_008_MovingImageAfterZooming() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_GALLERY_TEST_TWO);
		PerformTouchAction touchAction = new PerformTouchAction(driver);
		LightBoxMercuryComponentObject lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.verifyCurrentImageIsVisible();
		lightbox.verifyMovingImageAfterZoomingToDirection(touchAction, LightBoxMercuryComponentObject.DIRECTION_LEFT);
		lightbox.clickCloseButton();
		lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.verifyCurrentImageIsVisible();
		lightbox.verifyMovingImageAfterZoomingToDirection(touchAction, LightBoxMercuryComponentObject.DIRECTION_RIGHT);
		lightbox.clickCloseButton();
		lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.verifyCurrentImageIsVisible();
		lightbox.verifyMovingImageAfterZoomingToDirection(touchAction, LightBoxMercuryComponentObject.DIRECTION_UP);
		lightbox.clickCloseButton();
		lightbox = article.clickGalleryImage(MercuryContent.MERCURY_GALLERY_IMAGE_INDEX);
		lightbox.verifyCurrentImageIsVisible();
		lightbox.verifyMovingImageAfterZoomingToDirection(touchAction, LightBoxMercuryComponentObject.DIRECTION_DOWN);
	}
}
