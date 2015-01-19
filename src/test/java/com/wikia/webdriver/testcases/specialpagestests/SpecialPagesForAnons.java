package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.dataprovider.SpecialPagesDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;
import org.testng.annotations.Test;

public class SpecialPagesForAnons extends NewTestTemplateBeforeClass {

	@Test(
		dataProviderClass = SpecialPagesDataProvider.class,
		dataProvider = "getSpecialPagesForAnons",
		groups = {"SpecialPagesForAnons_001", "SpecialPages"}
	)
	public void SpecialPagesForAnons_001_verifyHeaders(String specialPageName, String specialPageHeader) {
		String testedPage = urlBuilder.getUrlForPath(config.getWikiName(), specialPageName);
		SpecialPageObject specialPage = new SpecialPageObject(driver);
		specialPage.getUrl(testedPage);
		specialPage.verifyPageHeader(specialPageHeader);
	}
}
