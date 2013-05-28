package com.wikia.webdriver.PageObjectsFactory.PageObject.Wam;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WamPageObject extends BasePageObject {
    final int FIRST_WAM_TAB_INDEX = 0;
    public final int DEFAULT_WAM_INDEX_ROWS = 21;

    @FindBy(id="verticalId")
    private WebElement wamVerticalFilterSelect;

    @FindBy(id="WamFilterDate")
    private WebElement wamDateInput;

    @FindBy(id="langCode")
    private WebElement wamLanguageFilterSelect;

    @FindBy(css=".wam-index-search .searching input[name=searchPhrase]")
    private WebElement wamSearchPhrase;

    @FindBy(css="#wam-index table")
    private WebElement wamIndexTable;

    @FindBy(css=".wam-tabs")
    private List<WebElement> wamTabs;

    @FindBy(css="#wam-index table tr")
    private List<WebElement> wamIndexRows;

    public WamPageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * @desc Opens "WAM Scores" page in example: www.wikia.com/WAM
     */
    public void openWamPage() {
        getUrl(Global.LIVE_DOMAIN + URLsContent.wamPageUrl);
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
        waitForElementByElement(wamIndexTable);
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
        waitForElementByElement(wamIndexTable);
        Assertion.assertNumber(wamIndexRows.size(), expectedRowsNo, "wam index rows equals " + expectedRowsNo );
    }
}
