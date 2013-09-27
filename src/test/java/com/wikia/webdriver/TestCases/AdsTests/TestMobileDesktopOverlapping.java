package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.AdsTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsMobileObject;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class TestMobileDesktopOverlapping extends AdsTestTemplate {

	private String testedPage;
	private UrlBuilder urlBuilder;
	//TODO - to be removed when AbTest is over
	private final String abTestQS = "AbTest.WIKIAMOBILEADSLOTS=D";
	//TODO - to be removed when skin is going to be set using cookie
	private final String mobileSkinQS = "useskin=wikiamobile";

	public TestMobileDesktopOverlapping() {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
	}

	@GeoEdgeProxy(country="US")
	@Test(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="mobileDesktopOverlapping",
		groups={"Ads", "MobileDesktopOverlapping_001"},
		invocationCount=5
	)
	public void TestMobileNotOverlapsDesktop_Medrec(
		String wikiName, String path, String desktopAd, String mobileAd
	) throws Exception {
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyCorrectAdInMedrec(desktopAd, mobileAd);
	}

	@Test(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="mobileDesktopOverlapping",
		groups={"Ads", "MobileDesktopOverlapping_002"},
		invocationCount=5
	)
	public void TestDesktopNotOverlapsMobile_InContent(
		String wikiName, String path, String desktopAd, String mobileAd
	) throws Exception {
		testedPage = urlBuilder.appendQueryStringToURL(
			urlBuilder.getUrlForPath(wikiName, path), mobileSkinQS
		);
		testedPage = urlBuilder.appendQueryStringToURL(
			testedPage, abTestQS
		);
		AdsMobileObject wikiPage = new AdsMobileObject(driver, testedPage);
		wikiPage.verifyCorrectAdInContent(mobileAd, desktopAd);
	}

	@GeoEdgeProxy(country="US")
	@Test(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="mobileDesktopOverlapping",
		groups={"Ads", "MobileDesktopOverlapping_003"},
		invocationCount=5
	)
	public void TestMobileNotOverlapsDesktop_FloatingMedrec(
		String wikiName, String path, String desktopAd, String mobileAd
	) throws Exception {
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyCorrectAdInFloatingMedrec(desktopAd, mobileAd);
	}

	@Test(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="mobileDesktopOverlapping",
		groups={"Ads", "MobileDesktopOverlapping_004"},
		invocationCount=5
	)
	public void TestDesktopNotOverlapsMobile_Interstitial(
		String wikiName, String path, String desktopAd, String mobileAd
	) throws Exception {
		testedPage = urlBuilder.appendQueryStringToURL(
			urlBuilder.getUrlForPath(wikiName, path), mobileSkinQS
		);
		testedPage = urlBuilder.appendQueryStringToURL(
			testedPage, abTestQS
		);
		AdsMobileObject wikiPage = new AdsMobileObject(driver, testedPage);
		wikiPage.verifyCorrectInterstitial(mobileAd, desktopAd);
	}
}
