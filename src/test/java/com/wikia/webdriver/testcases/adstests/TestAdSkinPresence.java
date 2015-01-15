package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
public class TestAdSkinPresence extends NewTestTemplate {

	public TestAdSkinPresence() {
		super();
		urlBuilder = new UrlBuilder(config.getEnv());
	}

	/**
	 * Test ad skin presence on given resolution.
	 *
	 * @param wikiName         - name of the wiki
	 * @param article          - name of the article
	 * @param screenImageUrl   - DFP link with ad skin image
	 * @param windowResolution - window resolution
	 * @param skinLeftSide     - path to file with decoded using Base64 ad skin
	 * @param skinRightSide    - path to file with decoded using Base64 ad skin
	 * @throws IOException
	 */
	@Test(
		dataProviderClass = AdsDataProvider.class,
		dataProvider = "skin",
		groups = {"TestSkinPresence_GeoEdgeFree"},
		invocationCount = 3
	)
	public void TestSkinPresence_GeoEdgeFree(
		String wikiName, String article, String screenImageUrl,
		Dimension windowResolution, String skinLeftSide, String skinRightSide
	) throws IOException {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage, windowResolution);
		wikiPage.verifyAdSkinPresence(screenImageUrl, skinLeftSide, skinRightSide);
	}
}
