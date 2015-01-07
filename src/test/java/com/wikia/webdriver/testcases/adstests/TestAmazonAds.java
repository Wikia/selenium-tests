package com.wikia.webdriver.testcases.adstests;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeProxy;
import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsAmazonObject;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
public class TestAmazonAds extends NewTestTemplate {

	private String testedPage;
	private static final String AMAZON_FORCE_RESPONSE = "amzn_debug_mode=1";

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
		testedPage = urlBuilder.appendQueryStringToURL(testedPage, AMAZON_FORCE_RESPONSE);
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
		testedPage = urlBuilder.appendQueryStringToURL(testedPage, AMAZON_FORCE_RESPONSE);
		AdsAmazonObject amazonAds = new AdsAmazonObject(driver, testedPage, networkTrafficIntereceptor);
		amazonAds.verifyAmazonScriptIncluded();
		amazonAds.verifyCallToAmazonIssued();
		amazonAds.verifyGPTParams();
		amazonAds.verifyResponseFromAmazonPresent();
	}
}
