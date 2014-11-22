package com.wikia.webdriver.TestCases.AdsTests;

import com.wikia.webdriver.Common.DataProvider.Ads.AdsDataProvider;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.AdsKruxObject;
import org.testng.annotations.Test;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class TestKruxSegment extends NewTestTemplate {
	private static final String KRUX_SEGMENT_ID = "n4mr5r49z";

	@Test(
		dataProviderClass = AdsDataProvider.class,
		dataProvider = "popularSites",
		groups = {"TestKruxSegment_GeoEdgeFree", "Ads"}
	)
	public void TestKruxSegment_GeoEdgeFree(String wikiName, String article) {
		String testedPage = urlBuilder.getUrlForPath(wikiName, article);
		AdsKruxObject ads = new AdsKruxObject(driver);
		ads.setKruxSegment(KRUX_SEGMENT_ID);
		ads.verifyKruxSegmentByKrux(KRUX_SEGMENT_ID);
		ads.getUrl(testedPage, true);
		ads.refreshPage();
		ads.refreshPage();
		ads.verifyKruxSegment(KRUX_SEGMENT_ID);
	}
}
