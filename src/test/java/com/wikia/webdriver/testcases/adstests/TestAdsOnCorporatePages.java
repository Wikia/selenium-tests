package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.geoedge.GeoEdgeProxy;
import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @author Piotr Gackowski
 * @author Piotr Gabryjeluk
 * @ownership AdEngineering
 */
@Test (
	groups={"Ads_Corporate_Page"}
)
public class TestAdsOnCorporatePages extends NewTestTemplate {

	private String testedPage;
	private String adUnit;
	private String slotName;

	@Factory(
		dataProviderClass=AdsDataProvider.class,
		dataProvider="corporatePages"
	)
	public TestAdsOnCorporatePages(String wikiName, String path, String adUnit, String slotName) {
		super();
		this.adUnit = adUnit;
		this.slotName = slotName;
		urlBuilder = new UrlBuilder(config.getEnv());
		testedPage = urlBuilder.getUrlForPath(wikiName, path);
		if (config.getQS() != null) {
			testedPage = urlBuilder.appendQueryStringToURL(testedPage, config.getQS());
		}
	}

	@GeoEdgeProxy(country="VE")
	@Test (
			groups={"TestCorporatePage_VE"}
	)
	public void TestCorporatePage_VE() {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoLiftiumAdsOnPage();

		// Not verifying GPT iframes in low value countries
	}

	@Test (
			groups={"TestCorporatePageHVC_GEF"}
	)
	public void TestCorporatePage_GEF() {
		AdsBaseObject wikiPage = new AdsBaseObject(driver, testedPage);
		wikiPage.verifyNoLiftiumAdsOnPage();

		// Verifying GPT iframes in high value countries:
		wikiPage.verifyGptIframe(adUnit, slotName, "gpt");
	}
}
