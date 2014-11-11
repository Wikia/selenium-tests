package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsOoyalaObject;
import org.testng.annotations.Test;

import java.awt.Color;

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
