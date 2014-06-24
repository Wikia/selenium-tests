package com.wikia.webdriver.TestCases.AdsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.Annotations.NetworkTrafficDump;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.Ads.GermanAdsDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsGermanObject;

public class TestIVW2AnalyticsProvider extends NewTestTemplate{

	public TestIVW2AnalyticsProvider( ) {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
	}

	@NetworkTrafficDump
	@Test(
		groups = {"IVW2_GeoEdgeFree"},
		dataProviderClass=GermanAdsDataProvider.class,
		dataProvider="pagesForIVW2"
	)
	public void TestIVW2AnalyticsProvider_GeoEdgeFree(String wikiName, String article, String ivw2Param )
	{
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsGermanObject wikiPage = new AdsGermanObject(driver, testedPage, networkTrafficIntereceptor);
		wikiPage.verifyCallToIVW2Issued();
		wikiPage.verifyParamFromIVW2Present(ivw2Param);
	}
}
