package com.wikia.webdriver.pageobjectsfactory.pageobject.wam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

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
    PageObjectLogging.log("openWamPage", "WAM page opened", true);
  }

  /**
   * @param tab WamTab enum
   * @desc Checks if given tab has an anchor with "selected" class
   */
  public void verifyTabIsSelected(WamTab tab) {
    WebElement wamTab =
        driver.findElement(By.cssSelector(String.format(WAM_TAB_CSS_SELECTOR_FORMAT, tab.getId())));
    PageObjectLogging.log("verifyTabIsSelected", "tab with index " + tab.getId() + " exist", true);

    if (wamTab.getAttribute("class").contains("icon-vertical-selected")) {
      PageObjectLogging.log("verifyTabIsSelected", "the tab's anchor's selected", true);
    } else {
      PageObjectLogging.log("verifyTabIsSelected", "the tab's anchor's NOT selected", false);
    }
  }

  /**
   * @desc Checks if there is a table row different than head one in WAM index table
   */
  public void verifyWamIndexIsNotEmpty() {
    waitForElementByBy(WAM_INDEX_TABLE);
    int rows = wamIndexRows.size();

    if (rows > 1) {
      PageObjectLogging.log("verifyWamIndexIsNotEmpty",
          "there are more rows in the table than just a head row (" + rows + ")", true);
    } else {
      PageObjectLogging.log("verifyTabIsSelected", "there is only the head row", false);
    }
  }

  /**
   * @param expectedRowsNo the number of expecting table rows
   * @desc Checks if there are as many rows in the WAM index table as we expect
   */
  public void verifyWamIndexHasExactRowsNo(int expectedRowsNo) {
    waitForElementByBy(WAM_INDEX_TABLE);
    Assertion.assertNumber(expectedRowsNo, wamIndexRows.size(), "wam index rows equals "
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
      PageObjectLogging.log("verifyWamVerticalFilterOptions",
          "There are correct options in the vertical select box", true);
    } else {
      PageObjectLogging.log("verifyWamVerticalFilterOptions",
          "There is invalid option in the vertical select box", false);
    }
  }

  /**
   * @desc Checks if "Vertical" column in WAM index has the same values for each row
   */
  public void verifyVerticalColumnValuesAreTheSame() {
    waitForElementByBy(WAM_INDEX_TABLE);
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
    waitForElementByBy(WAM_INDEX_TABLE);
    List<String> current = getCurrentIndexNo();
    for (int i = 0; i <= endElement - startElement; i++) {
      Assertion.assertEquals(current.get(i), Integer.toString(i + startElement));
    }
    Assertion.assertEquals(current.size(), endElement - startElement + 1);
  }

  public void clickNextPaginator() {
    waitForElementByElement(paginationNext);
    scrollAndClick(paginationNext);
    PageObjectLogging.log("clickNextPaginator", "next button in pagination was clicked", true);
  }

  public void selectTab(WamTab tab) {
    scrollAndClick(driver.findElement(By.cssSelector(String.format(WAM_TAB_CSS_SELECTOR_FORMAT,
        tab.getId()))));
    verifyTabSelected(tab);
  }

  private void verifyTabSelected(WamTab tab) {
    Assertion.assertTrue(driver
        .findElement(By.cssSelector(String.format(WAM_TAB_CSS_SELECTOR_FORMAT, tab.getId())))
        .getAttribute("class").contains("icon-vertical-selected"));
    waitForElementByElement(tabSelected);
  }

  public String getSelectedHeaderName() {
    return selectedHeaderName.getText();
  }

  public void verifyTodayDateInDatePicker() {
    String currentDate = datePickerInput.getAttribute("value");
    String todayDate =
        DateTimeFormat.forPattern("MMMM d, yyyy").withLocale(Locale.ENGLISH).print(DateTime.now());
    Assertion.assertEquals(todayDate, currentDate, "Current date and today date are not the same");
  }

  public String changeDateToLastMonth() {
    scrollAndClick(datePickerInput);
    waitForElementVisibleByElement(calendarElement);
    previousMonthArrow.click();
    DateTime date = DateTime.now().minusMonths(1);
    String previousMonth = DateTimeFormat.forPattern("MMMM").withLocale(Locale.ENGLISH).print(date);
    waitForTextToBePresentInElementByElement(monthInCalendar, previousMonth);
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
    String currentDate = datePickerInput.getAttribute("value");
    Assertion.assertEquals(date, currentDate, "Current date and expected date are not the same");
  }

  public void typeDateInDatePicker(String date) {
    waitForElementClickableByElement(datePickerInput);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("$(arguments[0])[0].value=''", datePickerInput);
    scrollAndClick(datePickerInput);
    datePickerInput.sendKeys(date);
    Actions actions = new Actions(driver);
    actions.sendKeys(datePickerInput, "\n");
    actions.perform();
  }

  private String getFormattedDate(Date date, String format) {
    SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
    return dateFormat.format(date);
  }
}
