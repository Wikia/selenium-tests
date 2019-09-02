package com.wikia.webdriver.pageobjectsfactory.pageobject.analytics;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import com.gargoylesoftware.htmlunit.javascript.host.canvas.WebGLActiveInfo;
import javafx.util.Pair;
import org.apache.bcel.generic.RETURN;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

public class AnalyticsPageObject extends BasePageObject {

  private String MWDisableAnonymousEditingVar = "wgDisableAnonymousEditing";

  // WebElements and their Pairs declared

  @FindBy(css = "#analytics_confidential")
  private WebElement analyticsConfidentialBar;
  @FindBy(css = "#PageHeader > div.page-header__main > h1")
  private WebElement permissionsErrorText;
  @FindBy(css = "#number_of_pageviews > div.grid_box_header")
  private WebElement pageviewsTitleBar;
  @FindBy(css = "#number_of_pageviews_chart")
  private WebElement pageviewsChart;
  @FindBy(css = "#edits_per_day > div.grid_box_header")
  private WebElement editsTitleBar;
  @FindBy(css = "#edits_per_day_chart")
  private WebElement editsChart;
  @FindBy(css = "#desktop_vs_mobile > div.grid_box_header")
  private WebElement desktopVsMobileSessionsTitleBar;
  @FindBy(css = "#desktop_vs_mobile_chart")
  private WebElement desktopVsMobileSessionsChart;
  @FindBy(css = "#browser_breakdown > div.grid_box_header")
  private WebElement browserSessionsBreakdownTitle;
  @FindBy(css = "#browser_breakdown_chart")
  private WebElement browserSessionsBreakdownChart;

  private List<Pair<WebElement, WebElement>> mandatoryToCheckTitleChartPairs =
      Arrays.asList(
          new Pair<WebElement, WebElement>(pageviewsTitleBar,
                                           pageviewsChart),
          new Pair<WebElement, WebElement>(editsTitleBar,
                                           editsChart),
          new Pair<WebElement, WebElement>(
              desktopVsMobileSessionsTitleBar,
              desktopVsMobileSessionsTitleBar
          ),
          new Pair<WebElement, WebElement>(
              browserSessionsBreakdownTitle,
              browserSessionsBreakdownChart
          )
      );

  @FindBy(css = "#logged_in_out > div.grid_box_header")
  private WebElement loggedInVsOutEditsTitleBar;
  @FindBy(css = "#logged_in_out_chart")
  private WebElement loggedInVsOutChart;

  private Pair<WebElement, WebElement> optionalLoggedInVsOutTitleChartPair =
      new Pair<WebElement, WebElement>(loggedInVsOutEditsTitleBar, loggedInVsOutChart);


  JavascriptExecutor js = (JavascriptExecutor) driver;

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
    for (Pair<WebElement, WebElement> titleChartPair : mandatoryToCheckTitleChartPairs) {
      checkIfTitleChartWPairAreDisplayed(titleChartPair);
    }
  }

  public void verifyIfOptionalLoggedInVsLoggedOutEditsChartAreDisplayed(){
    checkIfTitleChartWPairAreDisplayed(optionalLoggedInVsOutTitleChartPair);
  }

  // Helper methods

  public boolean IsAnonymousEditingAllowed() {
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
   * Checks for a pair of WebElements<Title, Chart>, if text and other elements are displayed correctly
   */
  private void checkIfTitleChartWPairAreDisplayed(Pair<WebElement, WebElement> titleChartPair){
    Assertion.assertNotNull(titleChartPair, "Chart is not present");
    Assertion.assertNotNull(titleChartPair.getKey().getText(), "Chart's title is missing");
    Assertion.assertTrue(titleChartPair.getKey().isDisplayed(), "Chart's title is not displayed");
    Assertion.assertTrue(titleChartPair.getValue().isDisplayed(), "Chart is not displayed");
  }
}
