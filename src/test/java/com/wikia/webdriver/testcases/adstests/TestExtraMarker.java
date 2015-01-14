package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Test;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class TestExtraMarker extends NewTestTemplate {
	@Test(
		dataProviderClass = AdsDataProvider.class,
		groups = {"TestExtraMarker_GHOST"},
		dataProvider = "extraMarker"
	)
	public void TestExtraMarker_GHOST(String wikiName, String article, String slot, String extraMarkerMessage) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		String logs = wikiPage.getBrowserLogs(slot).toString();
		Assertion.assertStringNotEmpty(logs);
		Assertion.assertStringContains(extraMarkerMessage, logs);
	}
}
