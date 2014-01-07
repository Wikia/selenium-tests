package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.Core.GeoEdge.GeoEdgeProxy;
import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.DataProvider.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.AdsTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsBaseObject;
import java.io.IOException;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class TestAdSkinPresence extends AdsTestTemplate {

	UrlBuilder urlBuilder;

	public TestAdSkinPresence() {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
	}

	/**
	 * Test ad skin presence on given resolution.
	 * @param wikiName - name of the wiki
	 * @param article - name of the article
	 * @param screenImageUrl - DFP link with ad skin image
	 * @param windowResolution - window resolution
	 * @param skinWidth - ad skin width on both sides of the article
	 * @param skinLeftSide - path to file with decoded using Base64 ad skin
	 * @param skinRightSide - path to file with decoded using Base64 ad skin
	 * @throws IOException
	 */
	@GeoEdgeProxy(country="US")
	@Test(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="skin",
		groups={"Skin"},
		invocationCount=5
	)
	public void TestSkinPresence_001(
		String wikiName, String article, String screenImageUrl,
		Dimension windowResolution, int skinWidth, String skinLeftSide, String skinRightSide
	) throws IOException {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsBaseObject wikiPage = new AdsBaseObject(driver);
		wikiPage.checkAdSkinPresenceOnGivenResolution(
			testedPage, screenImageUrl, windowResolution, skinWidth, skinLeftSide, skinRightSide
		);
	}
}
