package com.wikia.webdriver.testcases.desktop.wampagetests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wam.WamPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wam.WamTab;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.EnumSet;

@Execute(onWikia = "community")
public class WamPageTests extends NewTestTemplate {

  private WamPageObject wam;

  @BeforeMethod(alwaysRun = true)
  public void wam_000_setup() {
    wam = new WamPageObject(driver);
    wam.openWamPage(wikiCorporateURL);
    wam.verifyIfOnWamFandomPage();
  }

  @Test(groups = {"WamPage001", "WamPageTests"})
  public void wam_001_verifyDefaultPage() {
    wam.verifyTabIsSelected(WamTab.ALL);
    wam.verifyWamIndexIsNotEmpty();
    wam.verifyWamIndexHasExactRowsNo(wam.DEFAULT_WAM_INDEX_ROWS);
    wam.verifyWamIndexPageFirstColumnInOrder(1, wam.DEFAULT_WAM_INDEX_ROWS);
  }

  @Test(groups = {"WamPage002", "WamPageTests"})
  public void wam_002_verifyFilteringByVertical() {
    wam.verifyWamIndexIsNotEmpty();
    wam.verifyWamVerticalFilterOptions();

    for (WamTab tab : EnumSet.complementOf(EnumSet.of(WamTab.ALL))) {
      wam.selectTab(tab);
      wam.verifyIfVerticalIdSelectedInUrl(tab.getId());
      wam.verifyWamIndexIsNotEmpty();
      wam.verifyVerticalColumnValuesAreTheSame();
    }
  }

  /**
   * Test pagination and if WAM ranks are displayed in order
   */
  @RelatedIssue(issueID = "DE-4379", comment = "If fails, notify DE team.")
  @Test(groups = {"WamPage003", "WamPageTests", "Smoke5"})
  public void wam_003_verifyPaginationByNextButton() {
    wam.verifyWamIndexPageFirstColumnInOrder(1, wam.DEFAULT_WAM_INDEX_ROWS);
    wam.clickNextPaginator();
    wam.verifyWamIndexPageFirstColumnInOrder(
        wam.DEFAULT_WAM_INDEX_ROWS + 1,
        2 * wam.DEFAULT_WAM_INDEX_ROWS
    );
    wam.clickNextPaginator();
    wam.verifyWamIndexPageFirstColumnInOrder(
        2 * wam.DEFAULT_WAM_INDEX_ROWS + 1,
        3 * wam.DEFAULT_WAM_INDEX_ROWS
    );
  }

  /**
   * Tests behaviour of date picker
   */
  @Test(groups = {"wamPage_005", "WamPageTests"})
  public void wam_005_testDatePicker() {
    // test if today's or yesterday's date is set by default
    wam.verifyYesterdaysOrCurrentDateInDatePicker();
    // test behaviour of selecting a date
    String date = "June 1, 2019";
    wam.typeDateInDatePicker(date);
    wam.verifyDateInDatePicker(date);
    wam.verifyWamIndexIsNotEmpty();
  }
}
