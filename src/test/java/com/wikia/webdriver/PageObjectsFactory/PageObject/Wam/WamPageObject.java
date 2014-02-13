package com.wikia.webdriver.PageObjectsFactory.PageObject.Wam;




import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class WamPageObject extends BasePageObject {

	private final int FIRST_WAM_TAB_INDEX = 0;
	public final int DEFAULT_WAM_INDEX_ROWS = 21;
	private final int VERTICAL_COLUMN_INDEX = 5;

	@FindBy(id="verticalId")
	private WebElement wamVerticalFilterSelect;

	@FindBy(id="WamFilterDate")
	private WebElement wamDateInput;

	@FindBy(id="langCode")
	private WebElement wamLanguageFilterSelect;

	@FindBy(css=".wam-index-search .searching input[name=searchPhrase]")
	private WebElement wamSearchPhrase;

	@FindBy(css=".wam-tabs")
	private List<WebElement> wamTabs;

	@FindBy(css="#wam-index table tr")
	private List<WebElement> wamIndexRows;

	@FindBys(@FindBy(css="ul.wam-tabs li a"))
	private List<WebElement> tabsList;

	@FindBy(css="a.paginator-next")
	private WebElement paginationNext;

	@FindBy(css="div.wam-index tr td:nth-child(1)")
	private List<WebElement> indexList;

	@FindBy(css="div.wikia-paginator ul li a.paginator-page")
	private List<WebElement> paginationPageNo;

	@FindBy(css="a.selected")
	private WebElement tabSelected;

	@FindBy(css="div.wam-header h2")
	private WebElement selectedHeaderName;

	@FindBy(css="tr td:nth-child(5)")
	private List<WebElement> verticalColumn;

	By wamIndexTable = By.cssSelector("#wam-index table");

	/**
	 * @desc Wikia verticals (hubs)
	 * @todo think of a better place it might be moved to
	 */
	public enum VerticalsIds {
		VIDEO_GAMES(2), ENTERTAINMENT(3), LIFESTYLE(9);

		private int verticalId;

		private VerticalsIds(int id) {
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
		 *
		 * @return true if enum has the value; false otherwise
		 */
		public static Boolean contains(String value) {
			for( VerticalsIds vids : VerticalsIds.values() ) {
				if( vids.getIdAsString().equals(value)) {
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
		getUrl(wikiCorporateURL + URLsContent.wamPageUrl);
		PageObjectLogging.log("openWamPage", "WAM page opened", true);
	}

	/**
	 * @desc Checks if first tab has an anchor with "selected" class
	 */
	public void verifyFirstTabSelected() {
		verifyTabIsSelected(FIRST_WAM_TAB_INDEX);
	}

	/**
	 * @desc Checks if given tab has an anchor with "selected" class
	 *
	 * @param tabIndex number of a tab starting with 0
	 */
	public void verifyTabIsSelected(int tabIndex) {
		WebElement wamTab = wamTabs.get(tabIndex);
		WebElement wamTabAnchor = wamTab.findElement(By.className("selected"));

		waitForElementByElement(wamTab);

		PageObjectLogging.log("verifyTabIsSelected", "tab with index " + tabIndex + " exist", true);

		waitForElementByElement(wamTabAnchor);
		PageObjectLogging.log("verifyTabIsSelected", "the tab's anchor's selected", true);
	}

	/**
	 * @desc Checks if there is a table row different than head one in WAM index table
	 */
	public void verifyWamIndexIsNotEmpty() {
		waitForElementByBy(wamIndexTable);
		int rows = wamIndexRows.size();

		if( rows > 1 ) {
			PageObjectLogging.log("verifyWamIndexIsNotEmpty", "there are more rows in the table than just a head row (" + rows + ")", true);
		} else {
			PageObjectLogging.log("verifyTabIsSelected", "there is only the head row", false);
		}
	}

	/**
	 * @desc Checks if there are as many rows in the WAM index table as we expect
	 *
	 * @param expectedRowsNo the number of expecting table rows
	 */
	public void verifyWamIndexHasExactRowsNo(int expectedRowsNo) {
		waitForElementByBy(wamIndexTable);
		Assertion.assertNumber(expectedRowsNo, wamIndexRows.size(), "wam index rows equals " + expectedRowsNo );
	}

	/**
	 * @desc Checks if vertical filter except "All" option has options with our verticals' ids
	 */
	public void verifyWamVerticalFilterOptions() {
		waitForElementByElement(wamVerticalFilterSelect);
		Select verticalSelectBox = new Select(wamVerticalFilterSelect);
		List<WebElement> options = verticalSelectBox.getOptions();
		options.remove(0); // first option is "All" and we don't care about it here
		Boolean result = true;

		for( WebElement e : options ) {
			String optionValue = e.getAttribute("value");

			if( !VerticalsIds.contains(optionValue) ) {
				// once an option is not in our ENUM the test is failed
				result = false;
				break;
			}
		}

		if( result.equals(true) ) {
			PageObjectLogging.log("verifyWamVerticalFilterOptions", "There are correct options in the vertical select box", true);
		} else {
			PageObjectLogging.log("verifyWamVerticalFilterOptions", "There is invalid option in the vertical select box", false);
		}
	}

	/**
	 * @desc Selects vertical in vertical select box
	 * @param verticalId vertical id
	 */
	public void selectVertical(VerticalsIds verticalId) {
		waitForElementByElement(wamVerticalFilterSelect);
		Select verticalSelectBox = new Select(wamVerticalFilterSelect);
		verticalSelectBox.selectByValue( verticalId.getIdAsString() );
		waitForElementByBy(wamIndexTable);
	}

	/**
	 * @desc Checks if "Vertical" column in WAM index has the same values for each row
	 */
	public void verifyVerticalColumnValuesAreTheSame() {
		waitForElementByBy(wamIndexTable);
		waitForElementByElement(wamVerticalFilterSelect);
		Select verticalSelectBox = new Select(wamVerticalFilterSelect);
		String selectedValue = verticalSelectBox.getFirstSelectedOption().getText();
		for (WebElement item : verticalColumn) {
			Assertion.assertEquals(item.getText(), selectedValue);
		}
	}

	private List<String> getCurrentIndexNo() {
		List<String> counter = new ArrayList<String>();
		String no;
		for (WebElement cell : indexList) {
			no = cell.getText().replace(".", "");
			counter.add(no);
		}
		return counter;
	}

	public void verifyWamIndexPageFirstColumn(int startElement, int endElement) {
		waitForElementByBy(wamIndexTable);
		List<String> current = getCurrentIndexNo();
		for (int i=0; i<=endElement - startElement; i++) {
			Assertion.assertEquals(current.get(i), Integer.toString(i + startElement));
		}
		Assertion.assertEquals(current.size(), endElement - startElement + 1);
	}

	public void clickNextPaginator() {
		waitForElementByElement(paginationNext);
		scrollAndClick(paginationNext);
		PageObjectLogging.log("clickNextPaginator", "next button in pagination was clicked", true);
	}

	public void selectTab(int tabNumber){
		tabsList.get(tabNumber).click();
		verifyTabSelected(tabNumber);
	}

	private void verifyTabSelected(int tabNumber){
		tabNumber++;
		WebElement tabSelected = driver.findElement(By
				.cssSelector("ul.wam-tabs li:nth-child("+tabNumber+") a.selected"));
		waitForElementByElement(tabSelected);
	}

	public void checkTabAndHeaderName() {
		String selectedTabName = getSelectedTabName();
		String selectedHeaderName = getSelectedHeaderName();
		Assertion.assertEquals(selectedHeaderName.toLowerCase(), selectedTabName.toLowerCase());
	}

	private String getSelectedTabName() {
		String selectedTabName = tabSelected.getText();
		return selectedTabName;
	}

	private String getSelectedHeaderName() {
		String headerName = selectedHeaderName.getText();
		return headerName;
	}
}

