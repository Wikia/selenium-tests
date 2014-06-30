package com.wikia.webdriver.TestCases.AdsTests;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.Ads.GermanAdsDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsGermanObject;

/**
 * Class contains tests checking ad provider on German corporate pages
 *
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 *
 * @description
 * 1. Test no ads on corporate pages
 */
public class TestAdsOnGermanCorpPages extends NewTestTemplate {

	private String testedPage;

	@Factory(
		dataProviderClass=GermanAdsDataProvider.class,
		dataProvider="germanCorpPages"
	)
	public TestAdsOnGermanCorpPages(String wikiName, String path) {
		urlBuilder = new UrlBuilder(config.getEnv());
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
		if (config.getQS() != null) {
			testedPage = urlBuilder.appendQueryStringToURL(testedPage, config.getQS());
		}
	}

	@Test(
		groups={"TestAdsOnGermanCorpPages_GeoEdgeFree"}
	)
	public void TestAdsOnGermanCorpPages_GeoEdgeFree() {
		AdsGermanObject wikiCorpPage = new AdsGermanObject(driver, testedPage);
		wikiCorpPage.verifyNoAdsOnPage();
		wikiCorpPage.verifyNo71MediaAds();
	}
}
