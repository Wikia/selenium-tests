package com.wikia.webdriver.TestCases.AdsTests;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.Annotations.NetworkTrafficDump;
import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.Ads.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsAmazonObject;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
public class TestAmazonAds extends NewTestTemplate {

	private String testedPage;
	private final String amazonForceResponse = "amzn_debug_mode=1";

	@Factory(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="amazonSites"
	)
	public TestAmazonAds(String wikiName, String path) {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
		if (config.getQS() != null) {
			testedPage = urlBuilder.appendQueryStringToURL(testedPage, config.getQS());
		}
	}

	@NetworkTrafficDump
	@Test(groups = {"AmazonAds", "AmazonAds_GeoEdgeFree", "Ads"})
	public void AmazonAds_GeoEdgeFree() {
		AdsAmazonObject amazonAds = new AdsAmazonObject(driver, testedPage, networkTrafficIntereceptor);
		amazonAds.verifyAmazonScriptIncluded();
		amazonAds.verifyCallToAmazonIssued();
		amazonAds.verifyResponseFromAmazonPresent();
	}

	@GeoEdgeProxy(country="GB")
	@NetworkTrafficDump
	@Test(groups = {"AmazonAds", "AmazonAds_GB", "Ads"})
	public void AmazonAdsTest_GB() {
		AdsAmazonObject amazonAds = new AdsAmazonObject(driver, testedPage, networkTrafficIntereceptor);
		amazonAds.verifyAmazonScriptIncluded();
		amazonAds.verifyCallToAmazonIssued();
		amazonAds.verifyResponseFromAmazonPresent();
	}

	@NetworkTrafficDump
	@Test(groups = {"AmazonAds", "AmazonAds_GeoEdgeFree_debugMode", "Ads"})
	public void AmazonAds_GeoEdgeFree_debugMode() {
		testedPage = urlBuilder.appendQueryStringToURL(testedPage, amazonForceResponse);
		AdsAmazonObject amazonAds = new AdsAmazonObject(driver, testedPage, networkTrafficIntereceptor);
		amazonAds.verifyAmazonScriptIncluded();
		amazonAds.verifyCallToAmazonIssued();
		amazonAds.verifyGPTParams();
		amazonAds.verifyResponseFromAmazonPresent();
	}

	@GeoEdgeProxy(country="GB")
	@NetworkTrafficDump
	@Test(groups = {"AmazonAds", "AmazonAds_GB_debugMode", "Ads"})
	public void AmazonAdsTest_GB_debugMode() {
		testedPage = urlBuilder.appendQueryStringToURL(testedPage, amazonForceResponse);
		AdsAmazonObject amazonAds = new AdsAmazonObject(driver, testedPage, networkTrafficIntereceptor);
		amazonAds.verifyAmazonScriptIncluded();
		amazonAds.verifyCallToAmazonIssued();
		amazonAds.verifyGPTParams();
		amazonAds.verifyResponseFromAmazonPresent();
	}
}
