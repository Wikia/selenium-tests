package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class SpecialPhalanxPageObject extends SpecialPageObject {

    @FindBy (css = "#wpPhalanxFilter")
    private WebElement filterContent;
    @FindBy (css = "#wpPhalanxFormatRegex")
    private WebElement typeRegex;
    @FindBy (css = "#wpPhalanxFormatCase")
    private WebElement typeCase;
    @FindBy (css = "#wpPhalanxFormatExact")
    private WebElement typeExact;
    @FindBy (css = "#wpPhalanxTypeTitle")
    private WebElement blockTitleCheckbox;
    @FindBy (css = "#phalanx-block-test #phalanx-block-text")
    private WebElement testTextInput;
    @FindBy (css = "#phalanx-block-optionals #wpPhalanxSubmit")
    private WebElement submitAddFilter;
    @FindBy (css = "#phalanx-block-test input[type='submit']")
    private WebElement submitTestFilter;
    @FindBy (css = "#spamprotected_summary")
    private WebElement phalanxBlockMessageContainer;

    public SpecialPhalanxPageObject (WebDriver driver, String Domain) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Method creates default filter (see PageContent file)
     * for page title. Type of filter is plain
     *
     * @author Bogna 'bognix' Knychala
     * @return HashMap with keys "content", "type", "author"
     */
    public HashMap addStandardFilterForTitle() {
        String content = fillFilterField();
        checkTitleCheckbox();
        submitAddFilterForm();
        HashMap block = new HashMap();
        block.put("content", content);
        block.put("type", "plain");
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
        waitForTextToBePresentInElementByElement(row, (String) block.get("type"));
        PageObjectLogging.log(
            "BlockPresentOnList",
            "Block is present on list",
            true, driver
        );
    }

    public void submitTestFilterForm() {
        waitForElementByElement(submitTestFilter);
        clickAndWait(submitTestFilter);
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

    private WebElement getRowFromSearchResult(String text) {
        String script = String .format(
            "return $(\".blockContent:contains('%s')\").parent()[0]", text
        );
        WebElement row = executeScriptReturnElement(script);
        return row;
    }

    private void submitAddFilterForm() {
        waitForElementByElement(submitAddFilter);
        clickAndWait(submitAddFilter);
    }

    private void openTestBlockTab() {
        getUrl(Domain + URLsContent.specialPhalanxTest);
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
}
