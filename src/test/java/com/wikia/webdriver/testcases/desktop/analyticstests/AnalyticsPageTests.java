package com.wikia.webdriver.testcases.desktop.analyticstests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.analytics.AnalyticsPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Execute(onWikia = "muppet")
public class AnalyticsPageTests extends NewTestTemplate {

  private AnalyticsPageObject analyticsPage;

  public void goToAnalyticsPageAndVerifyUrl() {
    analyticsPage = new AnalyticsPageObject(driver);
    analyticsPage.openAnalyticsPage(wikiCorporateURL);
    analyticsPage.verifyIfOnAnalyticsSpecialPage();
  }


//  @BeforeMethod(alwaysRun = false, groups = {"AnalyticsPageAsHelper"})
//  @Execute(asUser = User.HELPER)
//  private void setupAnalyticsAsHelper() {
//    // TODO: Helpers for some reason don't have access
//    goToAnalyticsPageAndVerifyUrl();
//    analyticsPage.verifyIfConfidentialWarningIsDisplayed();
//  }

//  @BeforeMethod(alwaysRun = false, groups = {"AnalyticsPageAsAdmin"})
//  @Execute(asUser = User.HELPER)
//  private void setupAnalyticsAsAdmin() {
//    // TODO: Setup Admins process, see Special:AdminDashboard admin dashboard tests to see how qa admin is set up
//    goToAnalyticsPageAndVerifyUrl();
//    analyticsPage.verifyIfConfidentialWarningIsDisplayed();
//  }

//  @BeforeMethod(alwaysRun = false, groups = {"AnalyticsPageAsUser"})
//  @Execute(asUser = User.USER_3)
//  private void setupAnalyticsAsHelper() {
//    goToAnalyticsPageAndVerifyUrl();
//  }

  @Test
  @Execute(asUser = User.USER_3)
  public void userCannotAccessTest() {
    goToAnalyticsPageAndVerifyUrl();
    analyticsPage.verifyPermissionsErrorsIsDisplayed();
  }

  @Test
  @Execute(asUser = User.LOGIN_STAFF)
  public void staffCanAccessTest() {
    goToAnalyticsPageAndVerifyUrl();
    analyticsPage.verifyIfConfidentialWarningIsDisplayed();
  }

//
//  @Test(groups = {"WamPage002", "WamPageTests"})
//  public void wam_002_verifyFilteringByVertical() {
//    wam.verifyWamIndexIsNotEmpty();
//    wam.verifyWamVerticalFilterOptions();
//
//    for (WamTab tab : EnumSet.complementOf(EnumSet.of(WamTab.ALL))) {
//      wam.selectTab(tab);
//      wam.verifyIfVerticalIdSelectedInUrl(tab.getId());
//      wam.verifyWamIndexIsNotEmpty();
//      wam.verifyVerticalColumnValuesAreTheSame();
//    }
//  }
//
//  /**
//   * Test pagination and if WAM ranks are displayed in order
//   */
//  @RelatedIssue(issueID = "DE-4379", comment = "If fails, notify DE team.")
//  @Test(groups = {"WamPage003", "WamPageTests", "Smoke5"})
//  public void wam_003_verifyPaginationByNextButton() {
//    wam.verifyWamIndexPageFirstColumnInOrder(1, wam.DEFAULT_WAM_INDEX_ROWS);
//    wam.clickNextPaginator();
//    wam.verifyWamIndexPageFirstColumnInOrder(
//        wam.DEFAULT_WAM_INDEX_ROWS + 1,
//        2 * wam.DEFAULT_WAM_INDEX_ROWS
//    );
//    wam.clickNextPaginator();
//    wam.verifyWamIndexPageFirstColumnInOrder(
//        2 * wam.DEFAULT_WAM_INDEX_ROWS + 1,
//        3 * wam.DEFAULT_WAM_INDEX_ROWS
//    );
//    wam.clickNextPaginator();
//    wam.verifyWamIndexPageFirstColumnInOrder(
//        3 * wam.DEFAULT_WAM_INDEX_ROWS + 1,
//        4 * wam.DEFAULT_WAM_INDEX_ROWS
//    );
//  }
//
//  /**
//   * Tests behaviour of date picker
//   */
//  @Test(groups = {"wamPage_005", "WamPageTests"})
//  public void wam_005_testDatePicker() {
//    wam.verifyDateInDatePicker();
//    // test behaviour of selecting a date
//    String date = "June 1, 2019";
//    wam.typeDateInDatePicker(date);
//    wam.verifyDateInDatePicker(date);
//    wam.verifyWamIndexIsNotEmpty();
//  }
//
//  /**
//   * Tests order of ranks on date that was previously out of order
//   */
//  @Test(groups = {"wamPage_005", "WamPageTests"})
//  public void wam_006_testJune32019DataCorrectness() {
//    String date = "June 3, 2019";
//    wam.typeDateInDatePicker(date);
//    wam.verifyDateInDatePicker(date);
//
//    wam.verifyWamIndexPageFirstColumnInOrder(1, wam.DEFAULT_WAM_INDEX_ROWS);
//    wam.clickNextPaginator();
//    wam.verifyWamIndexPageFirstColumnInOrder(
//        wam.DEFAULT_WAM_INDEX_ROWS + 1,
//        2 * wam.DEFAULT_WAM_INDEX_ROWS
//    );
//    wam.clickNextPaginator();
//    wam.verifyWamIndexPageFirstColumnInOrder(
//        2 * wam.DEFAULT_WAM_INDEX_ROWS + 1,
//        3 * wam.DEFAULT_WAM_INDEX_ROWS
//    );
//  }
}
