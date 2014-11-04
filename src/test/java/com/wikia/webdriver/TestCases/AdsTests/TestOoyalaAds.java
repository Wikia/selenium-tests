package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.DataProvider.Ads.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsOoyalaObject;
import org.testng.annotations.Test;

import java.awt.*;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class TestOoyalaAds extends NewTestTemplate {
	@Test(
		dataProviderClass = AdsDataProvider.class,
		groups = {"TestOoyalaAds_GeoEdgeFree"},
		dataProvider = "ooyalaAds"
	)
	public void TestOoyalaAds_GeoEdgeFree(String wikiName, String article, Color thumbnailColor, Color lightboxAdColor,
										  int adDurationSec, Color lightboxVideoColor, int videoDurationSec) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, testedPage);
		wikiPage.verifyVideoThumbnail(thumbnailColor);
		wikiPage.openLightbox();
		wikiPage.verifyLightboxAd(lightboxAdColor, adDurationSec);
		wikiPage.verifyLightboxVideo(lightboxVideoColor, videoDurationSec);
	}
}
