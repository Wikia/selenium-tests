/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.AdsTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
@Test (
	groups={"Ads_Corporate_Page", "Ads"}
)
public class TestAdsOnCorporatePages extends AdsTestTemplate {

	private String testedPage;

	@Factory(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="corporatePages"
	)
	public TestAdsOnCorporatePages(String wikiName, String path) {
		super();
		UrlBuilder urlBuilder = new UrlBuilder(
			(String) config.get("ENV"),
			(String) config.get("QS")
		);
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
	}

	@GeoEdgeProxy(country="US")
	@Test (
		groups={"Ads_Corporate_Page_001", "US"}
	)
	public void TestCorporatePage_US() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}

	@GeoEdgeProxy(country="GB")
	@Test (
		groups={"Ads_Corporate_Page_002", "GB"}
	)
	public void TestCorporatePage_GB() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}

	@GeoEdgeProxy(country="DE")
	@Test (
		groups={"Ads_Corporate_Page_003", "DE"}
	)
	public void TestCorporatePage_DE() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}

	@GeoEdgeProxy(country="CA")
	@Test (
		groups={"Ads_Corporate_Page_004", "CA"}
	)
	public void TestCorporatePage_CA() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}

	@GeoEdgeProxy(country="AU")
	@Test (
		groups={"Ads_Corporate_Page_005", "AU"}
	)
	public void TestCorporatePage_AU() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}

	@GeoEdgeProxy(country="PL")
	@Test (
		groups={"Ads_Corporate_Page_006", "PL"}
	)
	public void TestCorporatePage_PL() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}

	@GeoEdgeProxy(country="UA")
	@Test (
		groups={"Ads_Corporate_Page_007", "UA"}
	)
	public void TestCorporatePage_UA() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}

	@GeoEdgeProxy(country="IT")
	@Test (
		groups={"Ads_Corporate_Page_008", "IT"}
	)
	public void TestCorporatePage_IT() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}

	@GeoEdgeProxy(country="RU")
	@Test (
		groups={"Ads_Corporate_Page_009", "RU"}
	)
	public void TestCorporatePage_RU() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}

	@GeoEdgeProxy(country="JP")
	@Test (
		groups={"Ads_Corporate_Page_010", "JP"}
	)
	public void TestCorporatePage_JP() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoAdsOnPage();
	}
}
