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
@Test(groups = {"DataEngineering", "WamPageTests"})
public class WamPageTests extends NewTestTemplate {

  private WamPageObject wam;

  @BeforeMethod(alwaysRun = true)
  public void wam_000_setup() {
    wam = new WamPageObject(driver);
    wam.openWamPage(wikiCorporateURL);
    wam.verifyIfOnWamFandomPage();
  }

  @Test
  public void wam_001_verifyDefaultPage() {
    wam.verifyTabIsSelected(WamTab.ALL);
    wam.verifyWamIndexIsNotEmpty();
    wam.verifyWamIndexHasExactRowsNo(wam.DEFAULT_WAM_INDEX_ROWS);
    wam.verifyWamIndexPageFirstColumnInOrder(1, wam.DEFAULT_WAM_INDEX_ROWS);
  }

  @Test
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
  @Test
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
    wam.clickNextPaginator();
    wam.verifyWamIndexPageFirstColumnInOrder(
        3 * wam.DEFAULT_WAM_INDEX_ROWS + 1,
        4 * wam.DEFAULT_WAM_INDEX_ROWS
    );
  }

  /**
   * Tests behaviour of date picker
   */
  @Test
  public void wam_005_testDatePicker() {
    wam.verifyDateInDatePicker();
    // test behaviour of selecting a date
    String date = "June 1, 2019";
    wam.typeDateInDatePicker(date);
    wam.verifyDateInDatePicker(date);
    wam.verifyWamIndexIsNotEmpty();
  }

  /**
   * Tests order of ranks on date that was previously out of order
   */
  @Test
  public void wam_006_testJune32019DataCorrectness() {
    String date = "June 3, 2019";
    wam.typeDateInDatePicker(date);
    wam.verifyDateInDatePicker(date);

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
}
