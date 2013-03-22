package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase;

import com.wikia.webdriver.Common.ContentPatterns.AdsContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsBaseObject extends WikiBasePageObject {

    private final String anyLeaderboardSelector = "[id$='TOP_LEADERBOARD']";
    private String presentLeaderboard;

    private String wikiPage;

    @FindBy (css = anyLeaderboardSelector)
    private WebElement anyLeaderboardContainer;
    @FindBy (css = "#TOP_RIGHT_BOXAD")
    private WebElement topRightBoxAdContainer;

    public AdsBaseObject(WebDriver driver, String page) {
        super(driver, page);
        PageFactory.initElements(driver, this);
        wikiPage = setEnviroment(page);
    }

    public void openPage() {
        getUrl(wikiPage);
    }

    public void verifyTopLeaderBoardPresent() {
        if (checkIfMainPage()) {
            presentLeaderboard = AdsContent.homeTopLeaderboard;
        } else {
            presentLeaderboard = AdsContent.topLeaderboard;
        }
        waitForElementByElement(anyLeaderboardContainer);
        checkScriptPresentInSlot(presentLeaderboard, anyLeaderboardContainer);
        checkTagsPresent(AdsContent.topLeaderboard, anyLeaderboardContainer);
    }

    private void checkTagsPresent(String slotName, WebElement slotElement) {
        Global.LOG_ENABLED  = false;
        try {
            waitForOneOfTagsPresentInElement(slotElement, "img", "iframe");
        } catch (TimeoutException e) {
            PageObjectLogging.log(
                "IFrameOrImgNotFound",
                "Nor image or iframe was found in slot",
                false,
                driver
            );
        } finally {
            PageObjectLogging.log(
                "IFrameOrImageFound",
                "Image or iframe was found in slot",
                true,
                driver
            );
        }
        Global.LOG_ENABLED  = true;
    }

    private void checkScriptPresentInSlot(String slotName, WebElement slotElement) {
        List<WebElement> scriptsTags = slotElement.findElements(By.tagName("script"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Boolean scriptFound = false;
        String scriptExpectedResult = AdsContent.adsPushSlotScript.replace(
            "%slot%", slotName
        );

        for (WebElement scriptNode : scriptsTags) {
            String result = (String) js.executeScript(
                "return arguments[0].innerHTML", scriptNode
            );
            if (scriptExpectedResult.equals(result)) {
                PageObjectLogging.log(
                    "PushSlotsScriptFound",
                    "Script " + scriptExpectedResult + " found",
                    true
                );
                scriptFound = true;
            }
        }
        if (!scriptFound) {
            PageObjectLogging.log(
                "PushSlotsScriptNotFound",
                "Script " + scriptExpectedResult + " not found",
                false,
                driver
            );
        }
    }

    private String setEnviroment(String page) {
        String enviroment = determineEnviroment();
        String pageToOpen = page.replace("http://", "http://" + enviroment);
        return pageToOpen;
    }
}
