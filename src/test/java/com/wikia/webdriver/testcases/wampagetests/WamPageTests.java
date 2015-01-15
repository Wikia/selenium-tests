package com.wikia.webdriver.testcases.wampagetests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wam.WamPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wam.WamTab;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.EnumSet;

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
		wam.verifyTabIsSelected(WamTab.ALL);
		wam.verifyWamIndexIsNotEmpty();
		wam.verifyWamIndexHasExactRowsNo(wam.DEFAULT_WAM_INDEX_ROWS);
	}

	@Test(groups = {"WamPage002", "WamPageTests"})
	public void wam_002_verifyFilteringByVertical() {
		wam.verifyWamIndexIsNotEmpty();
		wam.verifyWamVerticalFilterOptions();

		for (WamTab tab : EnumSet.complementOf(EnumSet.of(WamTab.ALL))) {
			wam.selectTab(tab);
			wam.verifyWamIndexIsNotEmpty();
			wam.verifyVerticalColumnValuesAreTheSame();
		}
	}

	@Test(groups = {"WamPage003", "WamPageTests", "Smoke5"})
	public void wam_003_verifyPaginationByNextButton() {
		wam.verifyWamIndexPageFirstColumn(1, 20);
		wam.clickNextPaginator();
		wam.verifyWamIndexPageFirstColumn(21, 40);
		wam.clickNextPaginator();
		wam.verifyWamIndexPageFirstColumn(41, 60);
	}

	@Test(groups = {"WamPage004", "WamPageTests"})
	public void wam_004_compareTabAndHeaderName() {
		for (WamTab tab : WamTab.values()) {
			wam.selectTab(tab);
			Assertion.assertEquals(wam.getSelectedHeaderName().toUpperCase(), tab.getExpectedHeaderName());
		}
	}

	@Test(groups = {"wamPage_005", "WamPageTests"})
	public void wam_005_testDatePicker() {
		wam.verifyTodayDateInDatePicker();
		String lastMonthDate = wam.changeDateToLastMonth();
		wam.verifyDateInDatePicker(lastMonthDate);
		String date = "July 12, 2014";
		wam.typeDateInDatePicker(date);
		wam.verifyDateInDatePicker(date);
	}
}
