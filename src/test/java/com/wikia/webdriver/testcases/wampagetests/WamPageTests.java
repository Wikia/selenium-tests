package com.wikia.webdriver.testcases.wampagetests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wam.WamPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wam.WamTab;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.EnumSet;

public class WamPageTests extends NewTestTemplate {

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

  @RelatedIssue(issueID = "CONCF-6", comment = "Test manually."
            + " Test is failing because WAM page sometimes has wrong order due to duplicate WAM scores.")
          @Test(groups = {"WamPage003", "WamPageTests", "Smoke5"})
  public void wam_003_verifyPaginationByNextButton() {
    wam.verifyWamIndexPageFirstColumn(1, 20);
    wam.clickNextPaginator();
    wam.verifyWamIndexPageFirstColumn(21, 40);
  }

  @Test(groups = {"WamPage004", "WamPageTests"})
  @RelatedIssue(issueID = "SUS-923")
  public void wam_004_compareTabAndHeaderName() {
    for (WamTab tab : WamTab.values()) {
      wam.selectTab(tab);
      Assertion
          .assertEquals(wam.getSelectedHeaderName().toUpperCase(), tab.getExpectedHeaderName());
    }
  }

  @Test(groups = {"wamPage_005", "WamPageTests"})
  @RelatedIssue(issueID = "MAIN-7392", comment = "test manually. Unable to reproduce defect")
  public void wam_005_testDatePicker() {
    wam.verifyLatestDateInDatePicker();
    String date = "July 12, 2014";
    wam.typeDateInDatePicker(date);
    wam.verifyDateInDatePicker(date);
  }
}
