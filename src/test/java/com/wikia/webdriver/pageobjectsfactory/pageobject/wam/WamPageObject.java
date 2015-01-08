package com.wikia.webdriver.pageobjectsfactory.pageobject.wam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
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

	public final int DEFAULT_WAM_INDEX_ROWS = 21;

	@FindBy(className = "wam-verticals-tabs")
	private WebElement wamVerticalFilterSelect;

	@FindBy(css = ".wam-filtering-tab")
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

	private static final By WAM_INDEX_TABLE = By.cssSelector("#wam-index table");

	/**
	 * @desc Wikia verticals (hubs)
	 * @todo think of a better place it might be moved to
	 */
	public enum WamTab {
		VIDEO_GAMES(2), BOOKS(3), LIFESTYLE(5), COMICS(4), ALL(0), TV(1), MOVIES(7), MUSIC(6), ;

		private final int verticalId;

		private WamTab(int id) {
			verticalId = id;
		}

		public int getId() {
			return verticalId;
		}

		public String getIdAsString() {
			return Integer.toString(verticalId);
		}

		/**
		 * @desc Checks if passed value is in enum
		 * @param value mostly a select-box option
		 * @return true if enum has the value; false otherwise
		 */
		public static Boolean contains(String value) {
			for (WamTab vids : WamTab.values()) {
				if (vids.getIdAsString().equals(value)) {
					return true;
				}
			}

			return false;
		}
	}

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
	 * @desc Checks if first tab has an anchor with "selected" class
	 */
	public void verifyFirstTabSelected() {
		verifyTabIsSelected(WamTab.ALL.getId());
	}

	/**
	 * @desc Checks if given tab has an anchor with "selected" class
	 *
	 * @param tabIndex number of a tab starting with 0
	 */
	public void verifyTabIsSelected(int tabIndex) {
		WebElement wamTab = wamTabs.get(tabIndex);
		PageObjectLogging.log("verifyTabIsSelected", "tab with index " + tabIndex + " exist", true);
		try {
			wamTab.findElement(By.className("icon-vertical-selected"));
		}catch (ElementNotFoundException e) {
			PageObjectLogging.log("verifyTabIsSelected", "the tab's anchor's NOT selected", false);
		}
		PageObjectLogging.log("verifyTabIsSelected", "the tab's anchor's selected", true);
	}

	/**
	 * @desc Checks if there is a table row different than head one in WAM index table
	 */
	public void verifyWamIndexIsNotEmpty() {
		waitForElementByBy(WAM_INDEX_TABLE);
		int rows = wamIndexRows.size();

		if (rows > 1) {
			PageObjectLogging.log(
				"verifyWamIndexIsNotEmpty",
				"there are more rows in the table than just a head row (" + rows + ")",
				true
			);
		} else {
			PageObjectLogging.log(
				"verifyTabIsSelected",
				"there is only the head row",
				false
			);
		}
	}

	/**
	 * @desc Checks if there are as many rows in the WAM index table as we expect
	 * @param expectedRowsNo the number of expecting table rows
	 */
	public void verifyWamIndexHasExactRowsNo(int expectedRowsNo) {
		waitForElementByBy(WAM_INDEX_TABLE);
		Assertion.assertNumber(
			expectedRowsNo,
			wamIndexRows.size(),
			"wam index rows equals " + expectedRowsNo
		);
	}

	/**
	 * @desc Checks if vertical filter except "All" option has options with our verticals' ids
	 */
	public void verifyWamVerticalFilterOptions() {
		waitForElementByElement(wamVerticalFilterSelect);

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
			PageObjectLogging.log(
				"verifyWamVerticalFilterOptions",
				"There are correct options in the vertical select box",
				true
			);
		} else {
			PageObjectLogging.log(
				"verifyWamVerticalFilterOptions",
				"There is invalid option in the vertical select box",
				false
			);
		}
	}

	/**
	 * @desc Selects vertical in vertical select box
	 * @param verticalId vertical id
	 */
	public void selectVertical(WamTab verticalId) {
		waitForElementByElement(wamVerticalFilterSelect);
			wamTabs.get(verticalId.getId()).click();
		waitForElementByBy(WAM_INDEX_TABLE);
	}

	/**
	 * @desc Checks if "Vertical" column in WAM index has the same values for each row
	 */
	public void verifyVerticalColumnValuesAreTheSame() {
		waitForElementByBy(WAM_INDEX_TABLE);
		String selectedValue = tabSelected.getAttribute("data-vertical-id");

		for (int i =1; i<wamIndexRows.size(); ++i) {
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
			Assertion.assertEquals(
				current.get(i),
				Integer.toString(i + startElement)
			);
		}
		Assertion.assertEquals(current.size(), endElement - startElement + 1);
	}

	public void clickNextPaginator() {
		waitForElementByElement(paginationNext);
		scrollAndClick(paginationNext);
		PageObjectLogging.log(
			"clickNextPaginator",
			"next button in pagination was clicked",
			true
		);
	}

	public void selectTab(int tabNumber) {
		wamTabs.get(tabNumber).click();
		verifyTabSelected(tabNumber);
	}

	private void verifyTabSelected(int tabNumber) {
		WebElement tabSelected = wamTabs.get(tabNumber);

		Assertion.assertTrue(tabSelected.getAttribute("class").contains("selected"));
		waitForElementByElement(tabSelected);
	}

	public void checkTabAndHeaderName() {
		String selectedTabName = getSelectedTabName();
		String selectedHeaderName = getSelectedHeaderName();
		Assertion.assertEquals(
			selectedHeaderName.toLowerCase(),
			selectedTabName.toLowerCase()
		);
	}

	private String getSelectedTabName() {
		return tabSelected.getText();
	}

	private String getSelectedHeaderName() {
		return selectedHeaderName.getText();
	}

	public void verifyTodayDateInDatePicker() {
		String currentDate = datePickerInput.getAttribute("value");
		Date date = new Date();
		String todayDate = getFormattedDate(date, "MMMM d, yyyy");
		Assertion.assertEquals(
			todayDate,
			currentDate,
			"Current date and today date are not the same"
		);
	}

	public String changeDateToLastMonth() {
		scrollAndClick(datePickerInput);
		waitForElementVisibleByElement(calendarElement);
		previousMonthArrow.click();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		Date date = calendar.getTime();
		String previousMonth = getFormattedDate(date, "MMMM");
		waitForTextToBePresentInElementByElement(monthInCalendar, previousMonth);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// first day of the current month
		WebElement firstDay = (WebElement) js.executeScript(
			"return $(arguments[0]).find('.ui-state-default:not(.ui-priority-secondary):nth(0)')[0]",
		 	calendarElement
		 );
		 firstDay.click();

		String year = getFormattedDate(date, "YYYY");

		return previousMonth + " 1, " + year;
	}

	public void verifyDateInDatePicker(String date) {
		String currentDate = datePickerInput.getAttribute("value");
		Assertion.assertEquals(
			date,
			currentDate,
			"Current date and expected date are not the same"
		);
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
