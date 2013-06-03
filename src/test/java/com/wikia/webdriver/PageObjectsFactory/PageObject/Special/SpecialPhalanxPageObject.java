package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class SpecialPhalanxPageObject extends SpecialPageObject {

    private final String blockMessageContainerLocator = "#spamprotected_summary";
    private final String modifyLinkSelector = ".modify";
    private final String unblockButtonSelector = ".unblock";
    private final String rowWithContent = "//b[@class=\"blockContent\"][contains(text(), '%s')]/..";

    @FindBy (css = "#wpPhalanxFilter")
    private WebElement filterContent;
    @FindBy (css = "#wpPhalanxFormatRegex")
    private WebElement typeRegex;
    @FindBy (css = "#wpPhalanxFormatCase")
    private WebElement typeCase;
    @FindBy (css = "#wpPhalanxFormatExact")
    private WebElement typeExact;
    @FindBy (xpath = "//label[text()[contains(.,'page title')]]//input")
    private WebElement blockTitleCheckbox;
    @FindBy (css = "#phalanx-block-test #phalanx-block-text")
    private WebElement testTextInput;
    @FindBy (css = "#phalanx-block-optionals #wpPhalanxSubmit")
    private WebElement submitAddFilter;
    @FindBy (css = "#phalanx-block-test input[type='submit']")
    private WebElement submitTestFilter;
    @FindBy (css = blockMessageContainerLocator)
    private WebElement phalanxBlockMessageContainer;

    public SpecialPhalanxPageObject (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Method creates default filter (see PageContent file)
     * for page title. Type of filter is given in type argument
     *
     * @author Bogna 'bognix' Knychala
     * @param  String type 
     * @return HashMap with keys "content", "type", "author"
     */
    public HashMap addFilterForTitle(String type) {
        String content = fillFilterField();
        checkMatchTypeCheckbox(type);
        checkTitleCheckbox();
        submitAddFilterForm();
        HashMap block = new HashMap();
        block.put("content", content);
        block.put("type", type);
        block.put("author", Properties.userNameStaff);
        verifyBlockOnBlocksList(block);
        return block;
    }

    public void testBlock(HashMap block) {
        openTestBlockTab();
        sendKeys(testTextInput, (String) block.get("content"));
        submitTestFilterForm();
        verifyBlockOnBlocksList(block);
    }

    public void verifyBlockOnBlocksList(HashMap block) {
        WebElement row = getRowFromSearchResult((String) block.get("content"));
        PageObjectLogging.log(
            "BlockPresentOnList",
            "Block is present on list",
            true
        );
    }

    public void submitTestFilterForm() {
        waitForElementByElement(submitTestFilter);
        clickAndWait(submitTestFilter);
    }

    public HashMap changeFilterContent(HashMap block) {
        clickModifyFilter((String) block.get("content"));
        String content = fillFilterField();
        block.put("content", content);
        submitAddFilterForm();
        return block;
    }

    public void unblockFilter(String filter) {
        WebElement row = getRowFromSearchResult(filter);
        WebElement unblockButton = row.findElement(By.cssSelector(unblockButtonSelector));
        clickAndWait(unblockButton);
    }

    public void verifyMessageAboutBlockPresent() {
        waitForElementByElement(phalanxBlockMessageContainer);
        waitForTextToBePresentInElementByElement(
            phalanxBlockMessageContainer, PageContent.phalanxBlockMessage
        );
        PageObjectLogging.log(
            "MessageAboutBlockedContent",
            "Message that content was blocked is present",
            true, driver
        );
    }

    public void verifyMessageAboutBlockAbsent() {
        waitForElementNotPresent(blockMessageContainerLocator);
        PageObjectLogging.log(
            "MessageAboutBlockedContentAbsent",
            "Message that content was blocked is not present",
            true
        );
    }

    private WebElement getRowFromSearchResult(String text) {
        String rowXpath = String.format(rowWithContent, text);
        WebElement row = null;
        try {
            row = driver.findElement(By.xpath(rowXpath));
        } catch (NoSuchElementException e) {
            PageObjectLogging.log(
                "RowWithTextNotFound",
                "Row with given text not found on search results list",
                false
            );
        } finally {
            return row;
        }
    }

    private void submitAddFilterForm() {
        waitForElementByElement(submitAddFilter);
        clickAndWait(submitAddFilter);
    }

    private void openTestBlockTab() {
        getUrl(Global.DOMAIN + URLsContent.specialPhalanxTest);
        waitForElementByElement(testTextInput);
    }

    private void checkTitleCheckbox() {
        waitForElementByElement(blockTitleCheckbox);
        blockTitleCheckbox.click();
        PageObjectLogging.log(
            "TitleCheckboxChecked",
            "Title checkbox in Phalanx checked",
            true
        );
    }

    private void checkMatchTypeCheckbox(String type) {
        if (type.equals("plain")) {
            PageObjectLogging.log(
                "NoCheckboxesChecked",
                "No checkbox checked",
                true
            );
            return;
        } else if (type.equals("regex")) {
            clickAndWait(typeRegex);
        } else if (type.equals("caseSensitive")) {
            clickAndWait(typeCase);
        } else if (type.equals("exact")) {
            clickAndWait(typeExact);
        }
        PageObjectLogging.log(
            "CheckboxChecked",
            "Checkbox for match type checked",
            true
        );
    }

    private String fillFilterField() {
        waitForElementByElement(filterContent);
        String content = PageContent.titleFilterPlain + getTimeStamp().substring(5);
        sendKeys(filterContent, content);
        PageObjectLogging.log(
            "FilterFiledFilled",
            "Filter content filed is filled.",
            true
        );
        return content;
    }

    private void clickModifyFilter(String filter) {
        WebElement row = getRowFromSearchResult(filter);
        WebElement modifyLink = row.findElement(By.cssSelector(modifyLinkSelector));
        clickAndWait(modifyLink);
    }
}
