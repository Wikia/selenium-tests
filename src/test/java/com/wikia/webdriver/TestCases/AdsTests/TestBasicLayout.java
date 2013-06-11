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
	groups={"Ads_Basic_Layout", "Ads"}
)
public class TestBasicLayout extends AdsTestTemplate {

	private String testedPage;

	@Factory(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="popularSites"
	)
	public TestBasicLayout(String wikiName, String path) {
		super();
		UrlBuilder urlBuilder = new UrlBuilder(
			(String) config.get("ENV"),
			(String) config.get("QS")
		);
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
	}

	@GeoEdgeProxy(country="US")
	@Test (
		groups={"Ads_Basic_Layout_001", "US"}
	)
	public void TestBasicLayout_US() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
	}

	@GeoEdgeProxy(country="DE")
	@Test (
		groups={"Ads_Basic_Layout_002", "DE"}
	)
	public void TestBasicLayout_DE() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
	}

	@GeoEdgeProxy(country="GB")
	@Test (
        groups={"Ads_Basic_Layout_003", "GB"}
	)
	public void TestBasicLayout_GB() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
	}

	@GeoEdgeProxy(country="CA")
	@Test (
		groups={"Ads_Basic_Layout_004", "CA"}
	)
	public void TestBasicLayout_CA() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
	}

	@GeoEdgeProxy(country="UA")
	@Test (
		groups={"Ads_Basic_Layout_005", "UA"}
	)
	public void TestBasicLayout_UA() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
	}

	@GeoEdgeProxy(country="PL")
	@Test (
		groups={"Ads_Basic_Layout_006", "PL"}
	)
	public void TestBasicLayout_PL() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
	}

	@GeoEdgeProxy(country="AU")
	@Test (
		groups={"Ads_Basic_Layout_007", "AU"}
	)
	public void TestBasicLayout_AU() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
	}

	@GeoEdgeProxy(country="JP")
	@Test (
		groups={"Ads_Basic_Layout_008", "JP"}
	)
	public void TestBasicLayout_JP() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
	}

	@GeoEdgeProxy(country="IT")
	@Test (
		groups={"Ads_Basic_Layout_009", "IT"}
	)
	public void TestBasicLayout_IT() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
	}

	@GeoEdgeProxy(country="RU")
	@Test (
		groups={"Ads_Basic_Layout_010", "RU"}
	)
	public void TestBasicLayout_RU() throws Exception {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyTopLeaderBoardAndMedrec();
	}
}
