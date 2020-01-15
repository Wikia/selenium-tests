package com.wikia.webdriver.pageobjectsfactory.pageobject.wam;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WamPageObject extends BasePageObject {

  public static final int DEFAULT_WAM_INDEX_ROWS = 20;
  private static final int FIRST_WAM_TAB_INDEX = 0;
  private static final By WAM_INDEX_TABLE = By.cssSelector("#wam-index table");
  private static final String WAM_TAB_CSS_SELECTOR_FORMAT = "a[data-vertical-id='%s']";
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

  public WamPageObject(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

  /**
   * @desc Opens "WAM Scores" page: https://community.fandom.com/wiki/WAM
   */
  public void openWamPage(String wikiCorporateURL) {
    getUrl(urlBuilder.getUrlForWikiPage(URLsContent.WAM_PAGE));
    waitForPageLoad();
    Log.log("openWamPage", "WAM page opened", true);
  }

  /**
   * @param tab WamTab enum
   * @desc Checks if given tab has an anchor with "selected" class
   */
  public void verifyTabIsSelected(WamTab tab) {
    WebElement wamTab = driver.findElement(By.cssSelector(String.format(
        WAM_TAB_CSS_SELECTOR_FORMAT,
        tab.getId()
    )));
    Log.log("verifyTabIsSelected", "tab with index " + tab.getId() + " exist", true);

    if (wamTab.getAttribute("class").contains("icon-vertical-selected")) {
      Log.log("verifyTabIsSelected", "the tab's anchor's selected", true);
    } else {
      Log.log("verifyTabIsSelected", "the tab's anchor's NOT selected", false);
    }
  }

  public WamPageObject isLoaded() {
    waitForPageLoad();

    return this;
  }

  /**
   * @desc Checks if there is a table row different than head one in WAM index table
   */
  public void verifyWamIndexIsNotEmpty() {
    wait.forElementPresent(WAM_INDEX_TABLE);
    int rows = wamIndexRows.size();

    if (rows > 1) {
      Log.log(
          "verifyWamIndexIsNotEmpty",
          "there are more rows in the table than just a head row (" + rows + ")",
          true
      );
    } else {
      Log.log("verifyWamIndexIsNotEmpty", "there is only the head row", false);
    }
  }

  /**
   * @desc Checks if the table for the vertical tab is empty. Needed to monitor data in ANIME vertical
   */
  public void verifyWamIndexIsEmpty() {
    wait.forElementPresent(WAM_INDEX_TABLE);
    int rows = wamIndexRows.size();
    if (rows == 2 && wamIndexRows.get(1).getText().startsWith("The wiki you searched for is not in the top 5000")) {
      Log.log(
          "verifyWamIndexIsEmpty",
          "WamTab for this vertical is empty, as it should be",
          true
      );
    } else {
      Log.log("verifyWamIndexIsEmpty",
              "Rows in this tab started to appear! ANIME vertical is probably filled with data!", false);
    }
  }

  /**
   * @param expectedRowsNo the number of expecting table rows
   * @desc Checks if there are as many rows in the WAM index table as we expect Adds +1 to
   * expectedRowsNo to account for header of the table
   */
  public void verifyWamIndexHasExactRowsNo(int expectedRowsNo) {
    wait.forElementPresent(WAM_INDEX_TABLE);
    Assertion.assertNumber(
        wamIndexRows.size(),
        expectedRowsNo + 1,
        "wam index rows equals " + expectedRowsNo
    );
  }

  /**
   * @desc Checks if vertical filter except "All" option has options with our verticals' ids
   */
  public void verifyWamVerticalFilterOptions() {
    Boolean result = true;

    for (WebElement e : wamTabs) {
      String optionValue = e.getAttribute("data-vertical-id");
      if (!WamTab.contains(optionValue)) {
        // once an option is not in our ENUM the test is failed
        result = false;
        break;
      }
    }

    if (result.equals(true)) {
      Log.log(
          "verifyWamVerticalFilterOptions",
          "There are correct options in the vertical select box",
          true
      );
    } else {
      Log.log(
          "verifyWamVerticalFilterOptions",
          "There is invalid option in the vertical select box",
          false
      );
    }
  }

  /**
   * @desc Checks if "Vertical" column in WAM index has the same values for each row
   */
  public void verifyVerticalColumnValuesAreTheSame() {
    wait.forElementPresent(WAM_INDEX_TABLE);
    String selectedValue = tabSelected.getAttribute("data-vertical-id");

    for (int i = 1; i < wamIndexRows.size(); ++i) {
      Assertion.assertEquals(wamIndexRows.get(i).getAttribute("data-vertical-id"), selectedValue);
    }
  }

  private List<String> getCurrentIndexNo() {
    List<String> counter = new ArrayList<>();
    String no;
    for (WebElement cell : indexList) {
      no = cell.getText().replace(".", "");
      counter.add(no);
    }
    return counter;
  }

  public void verifyWamIndexPageFirstColumnInOrder(int startElement, int endElement) {
    wait.forElementPresent(WAM_INDEX_TABLE);
    List<String> current = getCurrentIndexNo();
    for (int i = 0; i <= endElement - startElement; i++) {
      Assertion.assertEquals(current.get(i), Integer.toString(i + startElement));
    }
    Assertion.assertEquals(current.size(), endElement - startElement + 1);
  }

  public void clickNextPaginator() {
    wait.forElementVisible(paginationNext);
    scrollAndClick(paginationNext);
    Log.log("clickNextPaginator", "next button in pagination was clicked", true);
  }

  public void selectTab(WamTab tab) {
    scrollAndClick(driver.findElement(By.cssSelector(String.format(
        WAM_TAB_CSS_SELECTOR_FORMAT,
        tab.getId()
    ))));
    isLoaded();
    verifyTabSelected(tab);
  }

  private void verifyTabSelected(WamTab tab) {
    Assertion.assertTrue(driver.findElement(By.cssSelector(String.format(
        WAM_TAB_CSS_SELECTOR_FORMAT,
        tab.getId()
    )))
                             .getAttribute("class")
                             .contains("icon-vertical-selected"));
    wait.forElementVisible(tabSelected);
  }

  /**
   * Checks if yesterday's, day before yesterday, or current day is selected by default in date picker
   * 3 days are checked due to lags in bulkimporter (providing data for WAM API)
   */
  public void verifyDateInDatePicker() {
    String currentDate = datePickerInput.getAttribute("value");
    String dayBeforeYesterday = DateTimeFormat.forPattern("MMMM d, yyyy")
        .withLocale(Locale.ENGLISH)
        .print(DateTime.now().minus(Period.days(2)).withZone(DateTimeZone.UTC));
    String yesterday = DateTimeFormat.forPattern("MMMM d, yyyy")
        .withLocale(Locale.ENGLISH)
        .print(DateTime.now().minus(Period.days(1)).withZone(DateTimeZone.UTC));
    String today = DateTimeFormat.forPattern("MMMM d, yyyy")
        .withLocale(Locale.ENGLISH)
        .print(DateTime.now().withZone(DateTimeZone.UTC));
    Assertion.assertTrue(
        dayBeforeYesterday.equals(currentDate)|| yesterday.equals(currentDate) || today.equals(currentDate),
        "Default date does not match day before yesterday, yesterday or today"
    );
  }

  public void verifyDateInDatePicker(String date) {
    isLoaded();
    String currentDate = datePickerInput.getAttribute("value");
    Assertion.assertEquals(
        date,
        currentDate,
        "Current date and entered manually date are not the same"
    );
  }

  public void typeDateInDatePicker(String date) {
    wait.forElementClickable(datePickerInput);
    jsActions.execute("$(arguments[0])[0].value=''", datePickerInput);
    scrollAndClick(datePickerInput);
    new Actions(driver).sendKeys(datePickerInput, date).sendKeys(datePickerInput, "\n").perform();
  }

  public void verifyIfOnWamFandomPage() {
    Assertion.assertTrue(this.isStringInURL("WAM"), "Not on WAM subpage (in URL");
    Assertion.assertTrue(this.isStringInURL("fandom"), "Not on fandom (in URL");
  }

  public void verifyIfVerticalIdSelectedInUrl(int verticalId) {
    Assertion.assertTrue(
        this.isStringInURL("verticalId=" + verticalId),
        "No VerticalId selection (in URL)"
    );
  }
}
