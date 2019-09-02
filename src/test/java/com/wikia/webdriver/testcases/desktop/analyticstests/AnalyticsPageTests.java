package com.wikia.webdriver.testcases.desktop.analyticstests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.analytics.AnalyticsPageObject;

import org.testng.annotations.Test;

@Execute(onWikia = "muppet")
public class AnalyticsPageTests extends NewTestTemplate {

  // Special wiki for testing, of which DE_USER_ADMIN_SPECIAL_ANALYTICS_TEST_WIKI is an admin
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
  @Execute(asUser = User.HELPER)
  public void chartsAreDisplayedTest() {
    verifyAnalyticsShouldBeAccessible();
    analyticsPage.verifyIfAllMandatoryChartsAreDisplayed();
  }
}
