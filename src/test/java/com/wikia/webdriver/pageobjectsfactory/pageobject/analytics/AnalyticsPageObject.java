package com.wikia.webdriver.pageobjectsfactory.pageobject.analytics;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import javafx.util.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

public class AnalyticsPageObject extends BasePageObject {

  public static final int DEFAULT_WAM_INDEX_ROWS = 20;
  private static final int FIRST_WAM_TAB_INDEX = 0;
  private static final By WAM_INDEX_TABLE = By.cssSelector("#wam-index table");
  private static final String WAM_TAB_CSS_SELECTOR_FORMAT = "a[data-vertical-id='%s']";
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
          new Pair<WebElement, WebElement>(pageviewsTitleBar, pageviewsChart),
          new Pair<WebElement, WebElement>(editsTitleBar, editsChart),
          new Pair<WebElement, WebElement>(
              desktopVsMobileSessionsTitleBar,
              desktopVsMobileSessionsTitleBar
          ),
          new Pair<WebElement, WebElement>(
              browserSessionsBreakdownTitle,
              browserSessionsBreakdownChart
          )
      );

  @FindBy(css = ".wam-filtering-tab a")
  private List<WebElement> wamTabs;
  @FindBy(css = "#wam-index table tr")
  private List<WebElement> wamIndexRows;
  @FindBy(css = "a.paginator-next")
  private WebElement paginationNext;
  @FindBy(css = "div.wam-index tr td:nth-child(1)")
  private List<WebElement> indexList;
  @FindBy(css = "li.selected a")
  private WebElement tabSelected;
  @FindBy(css = "div.wam-header h2")
  private WebElement selectedHeaderName;
  @FindBy(id = "WamFilterHumanDate")
  private WebElement datePickerInput;
  @FindBy(id = "ui-datepicker-div")
  private WebElement calendarElement;
  @FindBy(css = ".ui-datepicker-prev.ui-corner-all")
  private WebElement previousMonthArrow;
  @FindBy(css = ".ui-datepicker-month")
  private WebElement monthInCalendar;

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

  public void verifyIfPageviewsChartIsDisplayed() {
    Assertion.assertNotNull(pageviewsTitleBar.getText(), "Pageviews chart's title is missing");
    Assertion.assertTrue(pageviewsChart.isDisplayed(), "Chart with pageviews is not displayed");
  }

  public void verifyIfOnAnalyticsSpecialPage() {
    Assertion.assertTrue(this.isStringInURL("Analytics"), "Not on 'Analytics' subpage (in URL");
  }

  public void verifyIfVerticalIdSelectedInUrl(int verticalId) {
    Assertion.assertTrue(
        this.isStringInURL("verticalId=" + verticalId),
        "No VerticalId selection (in URL)"
    );
  }

  public void verifyIfAllMandatoryChartsAreDisplayed() {
    for (Pair<WebElement, WebElement> titleChartPair : mandatoryToCheckTitleChartPairs) {
      Assertion.assertTrue(titleChartPair.getKey().isDisplayed(), "Chart's title is not displayed");
      Assertion.assertNotNull(titleChartPair.getKey().getText(), "Chart's title is missing");

      Assertion.assertTrue(titleChartPair.getValue().isDisplayed(), "Chart is not displayed");
      Assertion.assertNotNull(titleChartPair, "Chart is not present");
    }
  }
}
