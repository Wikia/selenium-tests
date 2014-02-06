package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.Annotations.NetworkTrafficDump;
import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsAmazonObject;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class TestAmazonAds extends NewTestTemplate {

	private String testedPage;
	private final String amazonForceResponse = "amzn_debug_mode=1";

	@Factory(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="popularSites"
	)
	public TestAmazonAds(String wikiName, String path) {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
		if (config.getQS() != null) {
			testedPage = urlBuilder.appendQueryStringToURL(testedPage, config.getQS());
		}
	}

	@GeoEdgeProxy(country="US")
	@NetworkTrafficDump
	@Test(groups = {"AmazonAds", "AmazonAds_US", "Ads"})
	public void AmazonAdsTest_US() {
		AdsAmazonObject amazonAds = new AdsAmazonObject(driver, testedPage, networkTrafficIntereceptor);
		amazonAds.verifyAmazonScriptIncluded();
		amazonAds.verifyCallToAmazonIssued();
		amazonAds.verifyResponseFromAmazonPresent();
	}

	@GeoEdgeProxy(country="UK")
	@NetworkTrafficDump
	@Test(groups = {"AmazonAds", "AmazonAds_UK", "Ads"})
	public void AmazonAdsTest_UK() {
		AdsAmazonObject amazonAds = new AdsAmazonObject(driver, testedPage, networkTrafficIntereceptor);
		amazonAds.verifyAmazonScriptIncluded();
		amazonAds.verifyCallToAmazonIssued();
		amazonAds.verifyResponseFromAmazonPresent();
	}

	@GeoEdgeProxy(country="US")
	@NetworkTrafficDump
	@Test(groups = {"AmazonAds", "AmazonAds_US_debugMode", "Ads"})
	public void AmazonAdsTest_US_debugMode() {
		testedPage = urlBuilder.appendQueryStringToURL(testedPage, amazonForceResponse);
		AdsAmazonObject amazonAds = new AdsAmazonObject(driver, testedPage, networkTrafficIntereceptor);
		amazonAds.verifyAmazonScriptIncluded();
		amazonAds.verifyCallToAmazonIssued();
		amazonAds.verifyGPTParams();
		amazonAds.verifyResponseFromAmazonPresent();
	}
}
