package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.geoedge.GeoEdgeProxy;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsEvolveObject;
import org.testng.annotations.Test;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class TestEvolveAds extends NewTestTemplate {

	private static final String EVOLVE_ERROR_MESSAGE = "Verify Evolve call in ";

	@GeoEdgeProxy(country = "CA")
	@Test(
		dataProviderClass = AdsDataProvider.class,
		groups = {"TestEvolveAds"},
		dataProvider = "evolveTestPage"
	)
	public void testEvolveCall_CA(String wikiName, String article) {
		testEvolveCall(wikiName, article);
	}

	@GeoEdgeProxy(country = "AU")
	@Test(
		dataProviderClass = AdsDataProvider.class,
		groups = {"TestEvolveAds"},
		dataProvider = "evolveTestPage"
	)
	public void testEvolveCall_AU(String wikiName, String article) {
		testEvolveCall(wikiName, article);
	}

	@GeoEdgeProxy(country = "NZ")
	@Test(
		dataProviderClass = AdsDataProvider.class,
		groups = {"TestEvolveAds"},
		dataProvider = "evolveTestPage"
	)
	public void testEvolveCall_NZ(String wikiName, String article) {
		testEvolveCall(wikiName, article);
	}

	private void testEvolveCall(String wikiName, String article) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsEvolveObject wikiPage = new AdsEvolveObject(driver, testedPage);
		Assertion.assertTrue(wikiPage.evolveCalledInSlot(AdsContent.TOP_LB),
			EVOLVE_ERROR_MESSAGE + AdsContent.TOP_LB);
		Assertion.assertTrue(wikiPage.evolveCalledInSlot(AdsContent.MEDREC),
			EVOLVE_ERROR_MESSAGE + AdsContent.MEDREC);
		Assertion.assertTrue(wikiPage.evolveCalledInSlot(AdsContent.LEFT_SKYSCRAPPER_2),
			EVOLVE_ERROR_MESSAGE + AdsContent.LEFT_SKYSCRAPPER_2);
		Assertion.assertFalse(wikiPage.evolveCalledInSlot(AdsContent.FLOATING_MEDREC),
			EVOLVE_ERROR_MESSAGE + AdsContent.FLOATING_MEDREC);
		Assertion.assertFalse(wikiPage.evolveCalledInSlot(AdsContent.PREFOOTER_LEFT),
			EVOLVE_ERROR_MESSAGE + AdsContent.PREFOOTER_LEFT);
		Assertion.assertFalse(wikiPage.evolveCalledInSlot(AdsContent.PREFOOTER_RIGHT),
			EVOLVE_ERROR_MESSAGE + AdsContent.PREFOOTER_RIGHT);
	}
}
