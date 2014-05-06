package com.wikia.webdriver.TestCases.Navigation;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Navigation.GlobalNavigationAndWikiaBarPageObject;
import org.testng.annotations.Test;


public class GlobalNavigationAndWikiaBarTests extends NewTestTemplate {
	@Test(groups = {"GlobalNavigationAndWikiaBar001", "Navigation"})
	public void globalNavigationVerticals() {
		GlobalNavigationAndWikiaBarPageObject pageObject = new GlobalNavigationAndWikiaBarPageObject(driver);

		for ( GlobalNavigationAndWikiaBarPageObject.vertical vert : GlobalNavigationAndWikiaBarPageObject.vertical.values() ) {
			pageObject.openCorporateHomePage(wikiCorporateURL);
			String url = pageObject.clickVerticalInGlobalNavigation(vert);
			Assertion.assertTrue(pageObject.checkCurrentPageIsVertical(vert, url));
		}
	}

	@Test(groups = {"GlobalNavigationAndWikiaBar002", "Navigation"})
	public void wikiaBarVerticals() {
		GlobalNavigationAndWikiaBarPageObject pageObject = new GlobalNavigationAndWikiaBarPageObject(driver);

		for ( GlobalNavigationAndWikiaBarPageObject.vertical vert : GlobalNavigationAndWikiaBarPageObject.vertical.values() ) {
			pageObject.openCorporateHomePage(wikiCorporateURL);
			String url = pageObject.clickVerticalInWikiaBar(vert);
			Assertion.assertTrue(pageObject.checkCurrentPageIsVertical(vert, url));
		}
	}
}
