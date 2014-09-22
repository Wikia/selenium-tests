package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.SonySideViewObject;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.Ads.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;

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
