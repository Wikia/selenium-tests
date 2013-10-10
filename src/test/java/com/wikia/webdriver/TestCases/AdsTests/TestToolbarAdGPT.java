package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.AdsTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsComparisonObject;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class TestToolbarAdGPT extends AdsTestTemplate {

	private UrlBuilder urlBuilder;

	public TestToolbarAdGPT() {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
	}

	@GeoEdgeProxy(country="US")
	@Test(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="gptAdsInToolbar",
		groups="Ads_In_Toolbar_GPT_001"
	)
	public void TestToolbarAdGPT_US(
		String wikiName, String path, String base64FilePath, Dimension adSize
	) throws Exception {
		String page = urlBuilder.getUrlForPath(wikiName, path);
		AdsComparisonObject adsComparison = new AdsComparisonObject(driver, page);
		adsComparison.checkToolbarAdBySize(base64FilePath, adSize);
	}

	@GeoEdgeProxy(country="GB")
	@Test(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="gptAdsInToolbar",
		groups="Ads_In_Toolbar_GPT_002"
	)
	public void TestToolbarAdGPT_GB(
		String wikiName, String path, String base64FilePath, Dimension adSize
	) throws Exception {
		String page = urlBuilder.getUrlForPath(wikiName, path);
		AdsComparisonObject adsComparison = new AdsComparisonObject(driver, page);
		adsComparison.checkToolbarAdBySize(base64FilePath, adSize);
	}
}
