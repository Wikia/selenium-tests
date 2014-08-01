package com.wikia.webdriver.TestCases.AdsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.Ads.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;

public class TestAdsCorpPagesGPTTags extends NewTestTemplate{

	public TestAdsCorpPagesGPTTags() {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
	}

	@Test(
		groups = {"TestAdsCorpPagesGPTTags_GeoEdgeFree"},
		dataProviderClass=AdsDataProvider.class,
		dataProvider="pagesForCorpGPTCheck"
	)

	public void TestAdsCorpPagesGPTTags_GeoEdgeFree(String wikiName, String article, String gptParam) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyCorrectGPTSlotNameOnCorpPages(gptParam);
	}
}
