package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.SonySideViewObject;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

/**
 * @author Piotr 'Rychu' Gabryjeluk
 * @ownership AdEngineering
 */
public class TestNoAdsOnSonyReferrer extends NewTestTemplate {

	private String testedPage;

	@Factory(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="noAdsForSonyReferrer"
	)
	public TestNoAdsOnSonyReferrer(String wikiName, String path) {
		super();
		UrlBuilder urlBuilder = new UrlBuilder(config.getEnv());
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
		if (config.getQS() != null) {
			testedPage = urlBuilder.appendQueryStringToURL(testedPage, config.getQS());
		}
	}

	@Test (
			groups={"TestNoAdsOnSonyReferrer_Desktop"}
	)
	public void TestNoAdsOnSonyReferrer_Desktop() {
		SonySideViewObject sonyPage = new SonySideViewObject(driver);
		AdsBaseObject wikiPage = sonyPage.goToDestinationPage(testedPage);

		wikiPage.verifyNoAdsOnPage();
	}

	@Test (
			groups={"TestNoAdsOnSonyReferrer_Mobile"}
	)
	public void TestNoAdsOnSonyReferrer_Mobile() {
		SonySideViewObject sonyPage = new SonySideViewObject(driver);
		AdsBaseObject wikiPage = sonyPage.goToDestinationPage(testedPage);

		wikiPage.verifyNoAdsOnMobilePage();
	}
}
