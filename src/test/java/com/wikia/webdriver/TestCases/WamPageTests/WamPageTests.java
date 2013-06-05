package com.wikia.webdriver.TestCases.WamPageTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Wam.WamPageObject;

public class WamPageTests extends TestTemplate {
	@Test(groups = {"WamPage001", "WamPageTests"})
	public void verifyDefaultPage() {
		WamPageObject pageObject = new WamPageObject(driver);
		pageObject.openWamPage();
		pageObject.verifyFirstTabSelected();
		pageObject.verifyWamIndexIsNotEmpty();
		pageObject.verifyWamIndexHasExactRowsNo( pageObject.DEFAULT_WAM_INDEX_ROWS );
	}

	@Test(groups = {"WamPage002", "WamPageTests"})
	public void verifyFilteringByVertical() {
		WamPageObject pageObject = new WamPageObject(driver);
		pageObject.openWamPage();
		pageObject.verifyWamIndexIsNotEmpty();
		pageObject.verifyWamVerticalFilterOptions();

		for( WamPageObject.VerticalsIds verticalId : WamPageObject.VerticalsIds.values() ) {
			pageObject.selectVertical( verticalId );
			pageObject.verifyWamIndexIsNotEmpty();
			pageObject.verifyVerticalColumnValuesAreTheSame();
		}
	}

	@Test(groups = {"WamPage003", "WamPageTests"})
	public void verifyPaginationByNextButton() {
		WamPageObject pageObject = new WamPageObject(driver);
		pageObject.openWamPage();
		pageObject.verifyWAMindexPageFirstColumn(1, 20);
		pageObject.clickNextPaginator();
		pageObject.verifyWAMindexPageFirstColumn(21, 40);
		pageObject.clickNextPaginator();
		pageObject.verifyWAMindexPageFirstColumn(41, 60);
	}
}
