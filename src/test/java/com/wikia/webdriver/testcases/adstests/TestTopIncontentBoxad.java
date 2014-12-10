package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class TestTopIncontentBoxad extends NewTestTemplate {
	@Test(
			dataProviderClass = AdsDataProvider.class,
			dataProvider = "topIncontentBoxad",
			groups = {"TestTopIncontentBoxad_GeoEdgeFree"}
	)
	public void TestTopIncontentBoxad_GeoEdgeFree(String wikiName, String article, Dimension windowResolution) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage, windowResolution);
		wikiPage.checkTopIncontentBoxad();
	}
}
