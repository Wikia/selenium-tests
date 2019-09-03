package com.wikia.webdriver.pageobjectsfactory.pageobject.analytics;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

public class AnalyticsPageObject extends BasePageObject {

  @FindBy(css = "#analytics_confidential")
  private WebElement analyticsConfidentialBar;
  @FindBy(css = "#PageHeader > div.page-header__main > h1")
  private WebElement permissionsErrorText;

  //  Charts
  @FindBy(css = "#number_of_pageviews")
  private WebElement pageviewsBox;
  @FindBy(css = "#edits_per_day")
  private WebElement editsBox;
  @FindBy(css = "#desktop_vs_mobile")
  private WebElement desktopVsMobileSessionsBox;
  @FindBy(css = "#browser_breakdown")
  private WebElement browserSessionsBreakdownBox;

  private List<WebElement> mandatoryChartBoxesToCheck = Arrays.asList(
      pageviewsBox,
      editsBox,
      desktopVsMobileSessionsBox,
      browserSessionsBreakdownBox
  );

  //  Logged Out vs Logged In Optional Box
  @FindBy(css = "#logged_in_out")
  private WebElement loggedInVsOutBox;

  //  Tables
  private String tableCssSelector = "div.grid_box_inner > table";

  @FindBy(css = "#top_search_terms")
  private WebElement internalTopSearchTermsBox;
  @FindBy(css = "#geolocation")
  private WebElement geolocationBox;
  @FindBy(css = "#top_viewed_pages")
  private WebElement topViewedPagesBox;
  @FindBy(css = "#most_visited_files")
  private WebElement mostVistedFilesBox;

  private List<WebElement> mandatoryTableBoxesToCheck = Arrays.asList(
      internalTopSearchTermsBox,
      geolocationBox,
      topViewedPagesBox,
      mostVistedFilesBox
  );

  // Logic driving operation/tests on Special:Analytics

  public AnalyticsPageObject(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

  /**
   * @desc Opens "Analytics" page on a given wiki: e.g. https://undertale.fandom.com/wiki/Special:Analytics
   */
  public void openAnalyticsPage(String wikiCorporateURL) {
    getUrl(urlBuilder.getUrlForWikiPage(URLsContent.SPECIAL_ANALYTICS));
    waitForPageLoad();
    Log.log("openAnalyticsPage", "Analytics Page opened", true);
  }

  public void verifyIfConfidentialWarningIsDisplayed() {
    Assertion.assertNotNull(
        analyticsConfidentialBar,
        "Couldn't find Analytics confidential warning"
    );
    Assertion.assertNotNull(
        analyticsConfidentialBar.getText(),
        "Text of Analytics confidential warning can't be empty"
    );
  }

  public void verifyPermissionsErrorsIsDisplayed() {
    Assertion.assertTrue(permissionsErrorText.isDisplayed(), "PermissionsErrorText is not visible");
    Assertion.assertNotNull(permissionsErrorText.getText(), "Permissions errors text is missing");
  }

  public void verifyIfOnAnalyticsSpecialPage() {
    Assertion.assertTrue(this.isStringInURL("Analytics"), "Not on 'Analytics' subpage (in URL");
  }

  public void verifyIfAllMandatoryChartsAreDisplayed() {
    for (WebElement chartBox : mandatoryChartBoxesToCheck) {
      checkIfChartBoxIsDisplayed(chartBox);
    }
  }

  public void verifyIfAllTablesAreDisplayed() {
    for (WebElement tableBox : mandatoryTableBoxesToCheck) {
      checkIfTableBoxIsDisplayed(tableBox);
    }
  }

  public void verifyIfOptionalLoggedInVsLoggedOutEditsChartAreDisplayed() {
    checkIfChartBoxIsDisplayed(loggedInVsOutBox);
  }

  public boolean IsAnonymousEditingAllowed() {
    String MWDisableAnonymousEditingVar = "wgDisableAnonymousEditing";
    try {
      Object disableAnonymousEditingObject = jsActions.execute(MWDisableAnonymousEditingVar);
      return !disableAnonymousEditingObject.toString().equalsIgnoreCase("true");
    } catch (WebDriverException exception) {
      if (exception.getMessage().contains(MWDisableAnonymousEditingVar + " is not defined")) {
        return true;
      }
      throw exception;
    }
  }

  /**
   * This checks if a Box contains a title header and a chart canvas.
   */
  private void checkIfChartBoxIsDisplayed(WebElement chartBox) {
    checkIfBoxIsDisplayed(chartBox);
    checkIfBoxTitleIsDisplayed(chartBox);
    WebElement chart = chartBox.findElement(By.cssSelector("div.grid_box_chart > canvas.chart_canvas.chartjs-render-monitor"));
    Assertion.assertNotNull(chart, "Chart is not present");
    Assertion.assertTrue(chart.isDisplayed(), "Chart is not displayed");
  }

  /**
   * This checks if a Box contains a title header and a table with column names.
   */
  private void checkIfTableBoxIsDisplayed(WebElement tableBox) {
    checkIfBoxIsDisplayed(tableBox);
    checkIfBoxTitleIsDisplayed(tableBox);

    WebElement table = tableBox.findElement(By.cssSelector(tableCssSelector));
    Assertion.assertTrue(table.isDisplayed(), "Table is not displayed");
    for (WebElement columnTitle : table.findElements(By.cssSelector("thead > tr > th"))) {
      Assertion.assertFalse(columnTitle.getText().isEmpty(), "Table's column name can't be empty ");
    }
  }

  private void checkIfBoxIsDisplayed(WebElement box){
    Assertion.assertNotNull(box, "Box is not present");
    Assertion.assertTrue(box.isDisplayed(), "Box is not displayed");
  }
  private void checkIfBoxTitleIsDisplayed(WebElement box) {
    String gridBoxHeaderCssSelector = "div.grid_box_header";
    WebElement boxTitle = box.findElement(By.cssSelector(gridBoxHeaderCssSelector));
    Assertion.assertNotNull(
        boxTitle.getText(),
        "Box's title is missing"
    );
    Assertion.assertTrue(boxTitle.isDisplayed(), "Box's title is not displayed");
  }
}
