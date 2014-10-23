package com.wikia.webdriver.TestCases.WamPageTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Wam.WamPageObject;

/**
 * @author Qaga
 * @ownership Consumer
 */

public class WamPageTests extends NewTestTemplateBeforeClass {

	private WamPageObject wam;

	@BeforeMethod(alwaysRun = true)
	public void wam_000_setup() {
		wam = new WamPageObject(driver);
		wam.openWamPage(wikiCorporateURL);
	}

	@Test(groups = {"WamPage001", "WamPageTests"})
	public void wam_001_verifyDefaultPage() {
		wam.verifyFirstTabSelected();
		wam.verifyWamIndexIsNotEmpty();
		wam.verifyWamIndexHasExactRowsNo( wam.DEFAULT_WAM_INDEX_ROWS );
	}

	@Test(groups = {"WamPage002", "WamPageTests"})
	public void wam_002_verifyFilteringByVertical() {
		wam.verifyWamIndexIsNotEmpty();
		wam.verifyWamVerticalFilterOptions();

		for( WamPageObject.VerticalsIds verticalId : WamPageObject.VerticalsIds.values() ) {
			wam.selectVertical( verticalId );
			wam.verifyWamIndexIsNotEmpty();
			wam.verifyVerticalColumnValuesAreTheSame();
		}
	}

	/*
	 * skipped per CON-324 task
	 */
	@Test(enabled = false, groups = {"WamPage003", "WamPageTests", "Smoke5"})
	public void wam_003_verifyPaginationByNextButton() {
		wam.verifyWamIndexPageFirstColumn(1, 20);
		wam.clickNextPaginator();
		wam.verifyWamIndexPageFirstColumn(21, 40);
		wam.clickNextPaginator();
		wam.verifyWamIndexPageFirstColumn(41, 60);
	}

	@Test(groups = {"WamPage004", "WamPageTests"})
	public void wam_004_compareTabAndHeaderName() {
		wam.selectTab(0);
		wam.checkTabAndHeaderName();
		wam.selectTab(1);
		wam.checkTabAndHeaderName();
		wam.selectTab(2);
		wam.checkTabAndHeaderName();
		wam.selectTab(3);
		wam.checkTabAndHeaderName();
		wam.selectTab(4);
		wam.checkTabAndHeaderName();
	}

	@Test(groups = {"wamPage_005", "WamPageTests"})
	public void wamPage_005_testDatePicker_MAIN_3024() {
		wam.verifyTodayDateInDatePicker();
		String lastMonthDate = wam.changeDateToLastMonth();
		wam.verifyDateInDatePicker(lastMonthDate);
		String date = "July 12, 2014";
		wam.typeDateInDatePicker(date);
		wam.verifyDateInDatePicker(date);
	}
}
