package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.dataprovider.ads.GermanAdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsGermanObject;
import org.testng.annotations.Test;

/**
 * @author Piotr 'PMG' Gackowski
 * @ownership AdEngineering
 */

public class TestIVW2AnalyticsProvider extends NewTestTemplate {

	private void testIVW2(String wikiName, String article, String ivw2Param) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsGermanObject wikiPage = new AdsGermanObject(driver, testedPage, networkTrafficIntereceptor);
		wikiPage.verifyCallToIVW2Issued();
		wikiPage.verifyParamFromIVW2Present(ivw2Param);
	}

	@NetworkTrafficDump
	@Test(
		groups = {"TestIVW2AnalyticsProviderCorporate_GEF"},
		dataProviderClass = GermanAdsDataProvider.class,
		dataProvider = "pagesForIVW2Corporate"
	)
	public void TestIVW2AnalyticsProviderCorporate_GEF(String wikiName, String article, String ivw2Param) {
		testIVW2(wikiName, article, ivw2Param);
	}

	@NetworkTrafficDump
	@Test(
		groups = {"TestIVW2AnalyticsProviderHubs_GEF"},
		dataProviderClass = GermanAdsDataProvider.class,
		dataProvider = "pagesForIVW2Hubs"
	)
	public void TestIVW2AnalyticsProviderHubs_GEF(String wikiName, String article, String ivw2Param) {
		testIVW2(wikiName, article, ivw2Param);
	}

	@NetworkTrafficDump
	@Test(
		groups = {"TestIVW2AnalyticsProviderOther_GEF"},
		dataProviderClass = GermanAdsDataProvider.class,
		dataProvider = "pagesForIVW2Other"
	)
	public void TestIVW2AnalyticsProviderOther_GEF(String wikiName, String article, String ivw2Param) {
		testIVW2(wikiName, article, ivw2Param);
	}
}
