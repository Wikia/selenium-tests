package com.wikia.webdriver.TestCases.WamPageTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Wam.WamPageObject;

public class WamPageTests extends NewTestTemplate {
	@Test(groups = {"WamPage001", "WamPageTests"})
	public void wam_001_verifyDefaultPage() {
		WamPageObject wam = new WamPageObject(driver);
		wam.openWamPage(wikiCorporateURL);
		wam.verifyFirstTabSelected();
		wam.verifyWamIndexIsNotEmpty();
		wam.verifyWamIndexHasExactRowsNo( wam.DEFAULT_WAM_INDEX_ROWS );
	}

	@Test(groups = {"WamPage002", "WamPageTests"})
	public void wam_002_verifyFilteringByVertical() {
		WamPageObject wam = new WamPageObject(driver);
		wam.openWamPage(wikiCorporateURL);
		wam.verifyWamIndexIsNotEmpty();
		wam.verifyWamVerticalFilterOptions();

		for( WamPageObject.VerticalsIds verticalId : WamPageObject.VerticalsIds.values() ) {
			wam.selectVertical( verticalId );
			wam.verifyWamIndexIsNotEmpty();
			wam.verifyVerticalColumnValuesAreTheSame();
		}
	}

	@Test(groups = {"WamPage003", "WamPageTests", "Smoke5"})
	public void wam_003_verifyPaginationByNextButton() {
		WamPageObject wam = new WamPageObject(driver);
		wam.openWamPage(wikiCorporateURL);
		wam.verifyWamIndexPageFirstColumn(1, 20);
		wam.clickNextPaginator();
		wam.verifyWamIndexPageFirstColumn(21, 40);
		wam.clickNextPaginator();
		wam.verifyWamIndexPageFirstColumn(41, 60);
	}

	@Test(groups = {"WamPage004", "WamPageTests"})
	public void wam_004_compareTabAndHeaderName() {
		WamPageObject WAMpage = new WamPageObject(driver);
		WAMpage.openWamPage(wikiCorporateURL);
		WAMpage.selectTab(0);
		WAMpage.checkTabAndHeaderName();
		WAMpage.selectTab(1);
		WAMpage.checkTabAndHeaderName();
		WAMpage.selectTab(2);
		WAMpage.checkTabAndHeaderName();
		WAMpage.selectTab(3);
		WAMpage.checkTabAndHeaderName();
		WAMpage.selectTab(4);
		WAMpage.checkTabAndHeaderName();
	}
}