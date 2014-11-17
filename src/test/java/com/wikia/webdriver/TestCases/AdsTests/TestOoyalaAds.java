package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.DataProvider.Ads.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsOoyalaObject;
import org.testng.annotations.Test;

import java.awt.Color;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class TestOoyalaAds extends NewTestTemplate {
	private final static Color GREEN = new Color(0, 214, 0);
	private final static Color BLUE = new Color(3, 0, 252);
	private final static int AD_DURATION_SEC = 30;
	private final static int VIDEO_DURATION_SEC = 30;

	@Test(
		dataProviderClass = AdsDataProvider.class,
		groups = {"TestOoyalaAds_GeoEdgeFree"},
		dataProvider = "ooyalaAds"
	)
	public void TestOoyalaAds_GeoEdgeFree(String wikiName, String article) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, testedPage);
		wikiPage.verifyLightboxAd(BLUE, AD_DURATION_SEC);
		wikiPage.verifyLightboxVideo(GREEN, VIDEO_DURATION_SEC);
	}
}
