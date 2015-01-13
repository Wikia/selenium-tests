package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.InteractiveMapsMercuryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

/*
* @ownership: Mobile Web
* @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
* */
public class InteractiveMapsTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@BeforeMethod(alwaysRun = true)
	public void optInMercury() {
		MercuryContent.turnOnMercurySkin(driver, wikiURL);
	}

	@Test(groups = {"MercuryInteractiveMaps_001", "MercuryInteractiveMapsTests", "Mercury"})
	public void MercuryInteractiveMaps_001_VerifyViewButtonWillOpenMapModal() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
		InteractiveMapsMercuryComponentObject maps = article.clickViewMapButton();
		maps.verifyMapModalIsVisible();
	}

	@Test(groups = {"MercuryInteractiveMaps_002", "MercuryInteractiveMapsTests", "Mercury"})
	public void MercuryInteractiveMaps_002_VerifyCloseButtonWillCloseModal() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
		InteractiveMapsMercuryComponentObject maps = article.clickViewMapButton();
		maps.verifyMapModalIsVisible();
		maps.clickCloseButton();
		maps.verifyMapModalIsNotVisible();
	}

	@Test(groups = {"MercuryInteractiveMaps_003", "MercuryInteractiveMapsTests", "Mercury"})
	public void MercuryInteractiveMaps_003_VerifyFilterBoxCanBeExpanded() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
		InteractiveMapsMercuryComponentObject maps = article.clickViewMapButton();
		maps.verifyMapModalIsVisible();
		maps.clickFilterBox();
		maps.verifyFilterBoxWasExpanded();
	}

	@Test(groups = {"MercuryInteractiveMaps_004", "MercuryInteractiveMapsTests", "Mercury"})
	public void MercuryInteractiveMaps_004_VerifyMapTitleInHeader() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
		InteractiveMapsMercuryComponentObject maps = article.clickViewMapButton();
		maps.verifyMapModalIsVisible();
		maps.verifyMapTitleInHeader();
	}

	@Test(groups = {"MercuryInteractiveMaps_005", "MercuryInteractiveMapsTests", "Mercury"})
	public void MercuryInteractiveMaps_005_VerifyMapIdInLink() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
		InteractiveMapsMercuryComponentObject maps = article.clickViewMapButton();
		maps.verifyMapModalIsVisible();
		maps.verifyMapIdInUrl();
	}

	@Test(groups = {"MercuryInteractiveMaps_006", "MercuryInteractiveMapsTests", "Mercury"})
	public void MercuryInteractiveMaps_006_VerifyZoomButtons() {
		MercuryBasePageObject base = new MercuryBasePageObject(driver);
		MercuryArticlePageObject article =  base.openMercuryArticleByName(wikiURL, MercuryContent.MERCURY_MAPS);
		InteractiveMapsMercuryComponentObject maps = article.clickViewMapButton();
		maps.verifyMapModalIsVisible();
		String leaflet = maps.getMapLeafletSrc();
		maps.clickZoomOut(6);
		String newLeaflet = maps.getMapLeafletSrc();
		maps.verifyMapZoomChangedView(leaflet, newLeaflet);

	}
}
