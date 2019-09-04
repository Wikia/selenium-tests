package com.wikia.webdriver.pageobjectsfactory.pageobject.analytics;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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
  @FindBy(css = "#top_search_terms")
  private WebElement internalTopSearchTermsBox;
  @FindBy(css = "#geolocation")
  private WebElement geolocationBox;
  @FindBy(css = "#top_viewed_pages")
  private WebElement topViewedPagesBox;
  @FindBy(css = "#most_visited_files")
  private WebElement mostVistedFilesBox;

  private List<WebElement> TableBoxesToCheck = Arrays.asList(
      internalTopSearchTermsBox,
      geolocationBox,
      topViewedPagesBox,
      mostVistedFilesBox
  );

  private String tableSelector = "div.grid_box_inner > table";
  private String tableRowSelector = "tbody > tr";

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

  /**
   * Checks if a message informing user about confidential nature of Special:Analytics is displayed
   */
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

  /**
   * Checks if an invalid permissions message is shown
   */
  public void verifyPermissionsErrorsIsDisplayed() {
    Assertion.assertTrue(permissionsErrorText.isDisplayed(), "PermissionsErrorText is not visible");
    Assertion.assertNotNull(permissionsErrorText.getText(), "Permissions errors text is missing");
  }

  public void verifyIfOnAnalyticsSpecialPage() {
    Assertion.assertTrue(this.isStringInURL("Analytics"), "Not on 'Analytics' subpage (in URL");
  }

  /**
   * Checks if all charts that should always be displayed on every wiki are shown properly,
   * including Box's title and chart canvas
   */
  public void verifyIfAllMandatoryChartsAreDisplayed() {
    for (WebElement chartBox : mandatoryChartBoxesToCheck) {
      checkIfChartBoxIsDisplayed(chartBox);
    }
  }

  /**
   * Checks that all tables are displayed, with their Box's title, and proper content (links, views
   * verification)
   */
  public void verifyIfAllTablesAreDisplayed() {
    for (WebElement tableBox : TableBoxesToCheck) {
      checkCommonTableBoxContents(tableBox);
    }

    // check table-specific things, such as links
    checkTopSearchTermsTableSpecificContent(
        internalTopSearchTermsBox.findElement(
            By.cssSelector(tableSelector)));

    checkTopViewedPagesTableSpecificContent(
        topViewedPagesBox.findElement(
            By.cssSelector(tableSelector)));

    checkMostVisitedFilesTableSpecificContent(
        mostVistedFilesBox.findElement(
            By.cssSelector(tableSelector)));
  }

  /**
   * Checks if Logged In Vs Logged Out Edits Box and its chart is displayed This chart's existence
   * is dependent on anonymous editing being allowed on a wiki. See method
   * IsAnonymousEditingAllowed
   */
  public void verifyIfLoggedInVsLoggedOutEditsChartIsDisplayed() {
    checkIfChartBoxIsDisplayed(loggedInVsOutBox);
  }

  /**
   * Returns !wgDisableAnonymousEditing, important for determining if Logged In vs Logged Out Edits
   * chart should be displayed
   */
  public boolean IsAnonymousEditingAllowed() {
    String MWDisableAnonymousEditingVar = "wgDisableAnonymousEditing";
    Object disableAnonymousEditingObject;
    try {
      disableAnonymousEditingObject = jsActions.execute(MWDisableAnonymousEditingVar);
    } catch (WebDriverException exception) {
      if (exception.getMessage().contains(MWDisableAnonymousEditingVar + " is not defined")) {
        return true;
      }
      throw exception;
    }
    return !disableAnonymousEditingObject.toString().equalsIgnoreCase("true");
  }

  // Helper methods

  /// Table-specific checks

  private void checkTopSearchTermsTableSpecificContent(WebElement topSearchTable) {
    for (WebElement row : topSearchTable.findElements(By.cssSelector(tableRowSelector))) {
      WebElement aLinkElement = row.findElement(By.cssSelector("td > a"));
      Assertion.assertNotNull(
          aLinkElement,
          "Each column 1 in row Internal Top Search should contain a link"
      );
      String searchTerm = aLinkElement.getText();
      Assertion.assertStringNotContains(searchTerm, "Special:");
      Assertion.assertStringContains(
          aLinkElement.getAttribute("href"),
          "Special:Search?query="
      );
    }
  }

  private void checkTopViewedPagesTableSpecificContent(WebElement topViewedPagesTable) {

    for (WebElement row : topViewedPagesTable.findElements(By.cssSelector(tableRowSelector))) {
      WebElement aLinkElement = row.findElement(By.cssSelector("td > a"));
      Assertion.assertNotNull(
          aLinkElement,
          "Each column 1 in row Top Viewed Pages should contain a link"
      );
    }
  }

  private void checkMostVisitedFilesTableSpecificContent(WebElement mostVisitedFilesTable) {
    for (WebElement row : mostVisitedFilesTable.findElements(By.cssSelector(tableRowSelector))) {
      WebElement aLinkElement = row.findElement(By.cssSelector("td > a"));
      Assertion.assertNotNull(
          aLinkElement,
          "Each column 1 value in row in Most Visited Files Table should contain a link"
      );
      String searchTerm = aLinkElement.getText();
      Assertion.assertStringContains(
          aLinkElement.getAttribute("href"),
          "File:"
      );
    }
  }

  /**
   * This checks if a Chart Box contains a title header and a Chart Canvas.
   */
  private void checkIfChartBoxIsDisplayed(WebElement chartBox) {
    checkIfBoxIsDisplayed(chartBox);
    checkIfBoxTitleIsDisplayed(chartBox);
    WebElement chart = chartBox.findElement(By.cssSelector(
        "div.grid_box_chart > canvas.chart_canvas.chartjs-render-monitor"));
    Assertion.assertNotNull(chart, "Chart is not present");
    Assertion.assertTrue(chart.isDisplayed(), "Chart is not displayed");
  }

  /**
   * This checks if a Table Box contains: - a title header - a table with named column: where second
   * column has pageviews number-like entries
   */
  private void checkCommonTableBoxContents(WebElement tableBox) {
    checkIfBoxIsDisplayed(tableBox);
    checkIfBoxTitleIsDisplayed(tableBox);
    checkTableHeaderAndViewsColumn(tableBox);
  }

  private void checkTableHeaderAndViewsColumn(WebElement tableBox) {
    WebElement table = tableBox.findElement(By.cssSelector(tableSelector));
    Assertion.assertTrue(table.isDisplayed(), "Table is not displayed");

    // check table column names
    for (WebElement columnTitle : table.findElements(By.cssSelector("thead > tr > th"))) {
      Assertion.assertFalse(columnTitle.getText().isEmpty(), "Table's column name can't be empty");
    }

    // check Views column: if entries follow '123,333' pattern and are > 0}
    for (WebElement row : table.findElements(By.cssSelector(tableRowSelector))) {
      WebElement viewsElement = row.findElement(By.cssSelector("td:nth-child(2)"));
      int viewsValue;
      try {
        viewsValue = NumberFormat
            .getNumberInstance(Locale.US)
            .parse(viewsElement.getText())
            .intValue();
      } catch (ParseException e) {
        throw new AssertionError("Views format does not fit expected Locale,"
                                 + "views: "
                                 + viewsElement.getText());
      }
      Assertion.assertTrue(viewsValue > 0,
                           "Entries with views lower than 0 shouldn't be showed");
    }
  }

  private void checkIfBoxIsDisplayed(WebElement box) {
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
