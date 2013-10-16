package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.AdsTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsCorpPageObject;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class TestAdsOnGermanCorpPages extends AdsTestTemplate {

	private String testedPage;

	@Factory(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="GermanCorpPages"
	)
	public TestAdsOnGermanCorpPages(String wikiName, String path) {
		UrlBuilder urlBuilder = new UrlBuilder(config.getEnv());
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
		if (config.getQS() != null) {
			testedPage = urlBuilder.appendQueryStringToURL(testedPage, config.getQS());
		}
	}

	@GeoEdgeProxy(country="US")
	@Test(
		groups={"Ads_on_German_corp_pages", "Ads_on_German_corp_pages_DART"}
	)
	public void TestAdsOnGermanCorpPages_DART() throws Exception {
		AdsCorpPageObject wikiCorpPage = new AdsCorpPageObject(driver, testedPage);
		wikiCorpPage.verifyNoLiftiumAdsOnPage();
		wikiCorpPage.verifyProviderAds("IDG");
	}

	@GeoEdgeProxy(country="CA")
	@Test(
		groups={"Ads_on_German_corp_pages", "Ads_on_German_corp_pages_Evolve"}
	)
	public void TestAdsOnGermanCorpPages_Evolve_CA() throws Exception {
		AdsCorpPageObject wikiCorpPage = new AdsCorpPageObject(driver, testedPage);
		wikiCorpPage.verifyNoLiftiumAdsOnPage();
		wikiCorpPage.verifyProviderAds("IDG");
	}

	@GeoEdgeProxy(country="AU")
	@Test(
		groups={"Ads_on_German_corp_pages", "Ads_on_German_corp_pages_Evolve"}
	)
	public void TestAdsOnGermanCorpPages_Evolve_AU() throws Exception {
		AdsCorpPageObject wikiCorpPage = new AdsCorpPageObject(driver, testedPage);
		wikiCorpPage.verifyNoLiftiumAdsOnPage();
		wikiCorpPage.verifyProviderAds("IDG");
	}

	@GeoEdgeProxy(country="DE")
	@Test(
		groups={"Ads_on_German_corp_pages", "Ads_on_German_corp_pages_IDG"}
	)
	public void TestAdsOnGermanCorpPages_IDG() throws Exception {
		AdsCorpPageObject wikiCorpPage = new AdsCorpPageObject(driver, testedPage);
		wikiCorpPage.verifyNoLiftiumAdsOnPage();
		wikiCorpPage.verifyProviderAds("IDG");
	}

	@GeoEdgeProxy(country="HR")
	@Test(
		groups={"Ads_on_German_corp_pages", "Ads_on_German_corp_pages_Liftium"}
	)
	public void TestAdsOnGermanCorpPages_Liftium() throws Exception {
		AdsCorpPageObject wikiCorpPage = new AdsCorpPageObject(driver, testedPage);
		wikiCorpPage.verifyNoLiftiumAdsOnPage();
		wikiCorpPage.verifyProviderAds("IDG");
	}
}
