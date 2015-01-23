package com.wikia.webdriver.testcases.adstests.mobileadstests;

import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Test;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class TestSynthetic extends NewTestTemplate {
	@Test(
		groups = {"TestSynthetic"},
		dataProviderClass = MobileAdsDataProvider.class,
		dataProvider = "testSynthetic"
	)
	public void testSynthetic(String wikiPage, String article,
							  String slotName, int slotWidth, int slotHeight,
							  int lineItemId, String src,
							  String imageUrl) {
		new AdsBaseObject(driver, urlBuilder.getUrlForPath(wikiPage, article))
			.verifySize(slotName, slotWidth, slotHeight)
			.verifyLineItemId(slotName, src, lineItemId)
			.verifyAdImage(slotName, imageUrl);
	}
}
