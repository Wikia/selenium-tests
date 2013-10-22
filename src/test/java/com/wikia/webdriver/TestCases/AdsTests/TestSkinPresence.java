package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.AdsTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsComparisonObject;
import java.io.IOException;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class TestSkinPresence extends AdsTestTemplate {

	UrlBuilder urlBuilder;

	public TestSkinPresence() {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
	}

	@Test(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="skin",
		groups={"Skin"},
		invocationCount=5
	)
	public void TestSkinPresence_001(
		String wikiName, String article, String screenImageUrl,
		Dimension windowSize, int adWidth, String leftPart, String rightPart
	) throws IOException {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsComparisonObject adsComparison = new AdsComparisonObject(driver, testedPage);
		adsComparison.checkSkinOnResolution(screenImageUrl, windowSize, adWidth, leftPart, rightPart);
	}
}
