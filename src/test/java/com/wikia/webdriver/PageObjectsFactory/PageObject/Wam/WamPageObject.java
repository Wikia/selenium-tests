package com.wikia.webdriver.PageObjectsFactory.PageObject.Wam;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class WamPageObject extends BasePageObject {
    final int FIRST_WAM_TAB_INDEX = 0;
    final int DEFAULT_WAM_INDEX_ROWS = 21;

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


    public WamPageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openWamPage() {
        getUrl(Global.LIVE_DOMAIN + URLsContent.wamPageUrl);
        PageObjectLogging.log("openWamPage", "WAM page opened", true);
    }

    public void verifyFirstTabSelected() {
        WebElement firstWamTab = wamTabs.get(FIRST_WAM_TAB_INDEX);
        WebElement firstWamTabAnchor = firstWamTab.findElement(By.className("selected"));

        waitForElementByElement(firstWamTab);
        PageObjectLogging.log("verifyFirstTabSelected", "first tab exist", true);

        waitForElementByElement(firstWamTabAnchor);
        PageObjectLogging.log("verifyFirstTabSelected", "first tab's anchor's selected", true);
    }

    public void verifyWamIndexIsNotEmpty() {
        List<WebElement> wamIndexRows = driver.findElements(By.cssSelector("#wam-index table tr"));
        waitForElementByElement(wamIndexRows.get(0));
        Assertion.assertNumber(wamIndexRows.size(), DEFAULT_WAM_INDEX_ROWS, "default talbe rows number is correct");
    }
}
