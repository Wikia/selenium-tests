package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
@Test (
	groups={"Ads_Corporate_Page", "Ads"}
)
public class TestAdsOnCorporatePages extends NewTestTemplate {

	private String testedPage;

	@Factory(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="corporatePages"
	)
	public TestAdsOnCorporatePages(String wikiName, String path) {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
		if (config.getQS() != null) {
			testedPage = urlBuilder.appendQueryStringToURL(testedPage, config.getQS());
		}
	}

	@GeoEdgeProxy(country="US")
	@Test (
		groups={"Ads_Corporate_Page_001", "US"}
	)
	public void TestCorporatePage_US() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoLiftiumAdsOnPage();
	}

	@GeoEdgeProxy(country="GB")
	@Test (
		groups={"Ads_Corporate_Page_002", "GB"}
	)
	public void TestCorporatePage_GB() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoLiftiumAdsOnPage();
	}

	@GeoEdgeProxy(country="CA")
	@Test (
		groups={"Ads_Corporate_Page_003", "CA"}
	)
	public void TestCorporatePage_CA() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoLiftiumAdsOnPage();
	}

	@GeoEdgeProxy(country="PL")
	@Test (
		groups={"Ads_Corporate_Page_004", "PL"}
	)
	public void TestCorporatePage_PL() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoLiftiumAdsOnPage();
	}

	@GeoEdgeProxy(country="RU")
	@Test (
		groups={"Ads_Corporate_Page_005", "RU"}
	)
	public void TestCorporatePage_RU() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoLiftiumAdsOnPage();
	}

	@GeoEdgeProxy(country="JP")
	@Test (
		groups={"Ads_Corporate_Page_006", "JP"}
	)
	public void TestCorporatePage_JP() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoLiftiumAdsOnPage();
	}
}
