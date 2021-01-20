package com.wikia.webdriver.testcases.desktop.analyticstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.analytics.AnalyticsPageObject;

import org.testng.annotations.Test;

@Execute(onWikia = "community")
@Test(groups = {"DataEngineering", "AnalyticsPageTests"})
public class AnalyticsPageTests extends NewTestTemplate {

  // Special wiki for testing, of which DE_USER_ADMIN_SPECIAL_ANALYTICS_TEST_WIKI is an admin
  // and both logged in and logged out users can edit
  private final static String ANALYTICS_ADMIN_EMPTY_TEST_WIKI = "specialanalyticstestwiki";

  private AnalyticsPageObject analyticsPage;

  private void goToAnalyticsPageAndVerifyUrl() {
    analyticsPage = new AnalyticsPageObject(driver);
    analyticsPage.openAnalyticsPage(wikiCorporateURL);
    analyticsPage.verifyIfOnAnalyticsSpecialPage();
  }

  private void verifyAnalyticsShouldBeAccessible() {
    goToAnalyticsPageAndVerifyUrl();
    analyticsPage.verifyIfConfidentialWarningIsDisplayed();
  }

  private void verifyAnalyticsShouldBePermissionDenied() {
    goToAnalyticsPageAndVerifyUrl();
    analyticsPage.verifyPermissionsErrorsIsDisplayed();
  }

  /**
   * Helper testing method for data (titles, charts) displayed by Analytics, checks if:
   * - all mandatory charts are displayed
   * - all tables are displayed and their content matches expectations
   * depending on MW wgXXX.. variables, check if LoggedInVsLoggedOut chart is displayed
   */
  private void verifyIfAllDataElementsAreDisplayed() {
    // check all mandatory charts
    analyticsPage.verifyIfAllMandatoryChartsAreDisplayed();

    // check all tables
    analyticsPage.verifyIfAllTablesAreDisplayed();

    // check if anonymous users can edit on this wiki
    if (analyticsPage.IsAnonymousEditingAllowed()) {
      // if so, logged in vs logged out edits charts should be displayed
      analyticsPage.verifyIfLoggedInVsLoggedOutEditsChartIsDisplayed();
    } else {
      // else no chart should be displayed and we get an exception
      Assertion.assertThrows(analyticsPage::verifyIfLoggedInVsLoggedOutEditsChartIsDisplayed);
    }
  }

  @Test
  @Execute(asUser = User.ANONYMOUS)
  public void anonymousCannotAccessTest() {
    verifyAnalyticsShouldBePermissionDenied();
  }

  @Test
  @Execute(asUser = User.USER_3)
  public void userCannotAccessTest() {
    verifyAnalyticsShouldBePermissionDenied();
  }

  @Test
  @Execute(asUser = User.LOGIN_STAFF)
  public void staffCanAccessTest() {
    verifyAnalyticsShouldBeAccessible();
  }

  @Test
  @Execute(asUser = User.HELPER)
  public void helperCanAccessTest() {
    verifyAnalyticsShouldBeAccessible();
  }

  @Test
  @Execute(asUser = User.DE_USER_ADMIN_SPECIAL_ANALYTICS_TEST_WIKI, onWikia = ANALYTICS_ADMIN_EMPTY_TEST_WIKI)
  public void adminOfWikiCanAccessTest() {
    verifyAnalyticsShouldBeAccessible();
  }

  @Test
  @Execute(asUser = User.DE_USER_ADMIN_SPECIAL_ANALYTICS_TEST_WIKI, onWikia = ANALYTICS_ADMIN_EMPTY_TEST_WIKI)
  public void loggedInVsOutEditsChartsAlignedWithAnonymousEditingTest() {
    verifyAnalyticsShouldBeAccessible();
    // check if all mandatory charts are displayed
    analyticsPage.verifyIfAllMandatoryChartsAreDisplayed();

    // on this Wiki we know to expect Anonymous Editing to be allowed
    Assertion.assertTrue(
        analyticsPage.IsAnonymousEditingAllowed(),
        "Anonymous editing is not allowed on the prepared test wiki when it should,"
        + "test wiki variable value or MediaWiki's mechanism has changed"
    );
    // and therefore Logged In vs Logged out Edits chart
    analyticsPage.verifyIfLoggedInVsLoggedOutEditsChartIsDisplayed();
  }

  @Test
  @Execute(asUser = User.HELPER)
  public void dataIsDisplayedTest() {
    verifyAnalyticsShouldBeAccessible();
    verifyIfAllDataElementsAreDisplayed();
  }
}
