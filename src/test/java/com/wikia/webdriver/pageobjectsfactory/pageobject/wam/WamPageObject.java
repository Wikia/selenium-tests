package com.wikia.webdriver.pageobjectsfactory.pageobject.wam;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WamPageObject extends BasePageObject {

  public static final int DEFAULT_WAM_INDEX_ROWS = 21;
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
    super(driver);
    PageFactory.initElements(driver, this);
  }

  /**
   * @desc Opens "WAM Scores" page in example: www.wikia.com/WAM
   */
  public void openWamPage(String wikiCorporateURL) {
    getUrl(wikiCorporateURL + URLsContent.WAM_PAGE);
    waitForPageLoad();
    LOG.success("openWamPage", "WAM page opened");
  }

  /**
   * @param tab WamTab enum
   * @desc Checks if given tab has an anchor with "selected" class
   */
  public void verifyTabIsSelected(WamTab tab) {
    WebElement wamTab =
        driver.findElement(By.cssSelector(String.format(WAM_TAB_CSS_SELECTOR_FORMAT, tab.getId())));
    LOG.success("verifyTabIsSelected", "tab with index " + tab.getId() + " exist");

    if (wamTab.getAttribute("class").contains("icon-vertical-selected")) {
      LOG.success("verifyTabIsSelected", "the tab's anchor's selected");
    } else {
      LOG.error("verifyTabIsSelected", "the tab's anchor's NOT selected");
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
      LOG.log("verifyWamIndexIsNotEmpty",
              "there are more rows in the table than just a head row (" + rows + ")",
              LOG.Type.SUCCESS);
    } else {
      LOG.error("verifyTabIsSelected", "there is only the head row");
    }
  }

  /**
   * @param expectedRowsNo the number of expecting table rows
   * @desc Checks if there are as many rows in the WAM index table as we expect
   */
  public void verifyWamIndexHasExactRowsNo(int expectedRowsNo) {
    wait.forElementPresent(WAM_INDEX_TABLE);
    Assertion.assertNumber(wamIndexRows.size(), expectedRowsNo, "wam index rows equals "
        + expectedRowsNo);
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
      LOG.success("verifyWamVerticalFilterOptions",
              "There are correct options in the vertical select box");
    } else {
      LOG.error("verifyWamVerticalFilterOptions",
              "There is invalid option in the vertical select box");
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

  public void verifyWamIndexPageFirstColumn(int startElement, int endElement) {
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
    LOG.success("clickNextPaginator", "next button in pagination was clicked");
  }

  public void selectTab(WamTab tab) {
    scrollAndClick(driver.findElement(By.cssSelector(String.format(WAM_TAB_CSS_SELECTOR_FORMAT,
        tab.getId()))));
    isLoaded();
    verifyTabSelected(tab);
  }

  private void verifyTabSelected(WamTab tab) {
    Assertion.assertTrue(driver
        .findElement(By.cssSelector(String.format(WAM_TAB_CSS_SELECTOR_FORMAT, tab.getId())))
        .getAttribute("class").contains("icon-vertical-selected"));
    wait.forElementVisible(tabSelected);
  }

  public String getSelectedHeaderName() {
    return selectedHeaderName.getText();
  }

  public void verifyTodayDateInDatePicker() {
    String currentDate = datePickerInput.getAttribute("value");
    String todayDate =
        DateTimeFormat.forPattern("MMMM d, yyyy").withLocale(Locale.ENGLISH)
            .print(DateTime.now().withZone(DateTimeZone.UTC));
    Assertion.assertEquals(todayDate, currentDate, "Current date and today date are not the same");
  }

  public String changeDateToLastMonth() {
    scrollAndClick(datePickerInput);
    wait.forElementVisible(calendarElement);
    previousMonthArrow.click();
    DateTime date = DateTime.now().minusMonths(1);
    String previousMonth = DateTimeFormat.forPattern("MMMM").withLocale(Locale.ENGLISH).print(date);
    wait.forTextInElement(monthInCalendar, previousMonth);
    JavascriptExecutor js = (JavascriptExecutor) driver;

    // first day of the current month
    WebElement firstDay =
        (WebElement) js
            .executeScript(
                "return $(arguments[0]).find('.ui-state-default:not(.ui-priority-secondary):nth(0)')[0]",
                calendarElement);
    firstDay.click();

    String year = DateTimeFormat.forPattern("YYYY").print(date);

    return previousMonth + " 1, " + year;
  }

  public void verifyDateInDatePicker(String date) {
    isLoaded();
    String currentDate = datePickerInput.getAttribute("value");
    Assertion.assertEquals(date, currentDate, "Current date and expected date are not the same");
  }

  public void typeDateInDatePicker(String date) {
    wait.forElementClickable(datePickerInput);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("$(arguments[0])[0].value=''", datePickerInput);
    scrollAndClick(datePickerInput);
    new Actions(driver).sendKeys(datePickerInput, date).sendKeys(datePickerInput, "\n").perform();
  }

  private String getFormattedDate(Date date, String format) {
    SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
    return dateFormat.format(date);
  }
}
