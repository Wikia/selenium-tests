package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase;

import com.wikia.webdriver.Common.ContentPatterns.AdsContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import java.util.Collection;
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
    private final String anyMedrecSelector = "[id$='TOP_RIGHT_BOXAD']";
    private String presentLeaderboard;
    private String presentMedrec;

    private String wikiPage;
    private AdsContent adsContent;

    @FindBy (css = anyLeaderboardSelector)
    private WebElement anyLeaderboardContainer;
    @FindBy (css = anyMedrecSelector)
    private WebElement anyMedrecContainer;

    public AdsBaseObject(WebDriver driver, String page) {
        super(driver, page);
        PageFactory.initElements(driver, this);
        wikiPage = setEnviroment(page);
	adsContent = new AdsContent();
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
        checkTagsPresent(presentLeaderboard, anyLeaderboardContainer);
    }

    public void verifyMedrecPresent() {
        if (checkIfMainPage()) {
            presentMedrec = AdsContent.homeMedrec;
        } else {
            presentMedrec = AdsContent.medrec;
        }
        waitForElementByElement(anyMedrecContainer);
        checkScriptPresentInSlot(presentMedrec, anyMedrecContainer);
        checkTagsPresent(presentMedrec, anyMedrecContainer);
    }

    public void verifyTopLeaderBoardAndMedrec() {
	openPage();
	verifyTopLeaderBoardPresent();
	verifyMedrecPresent();
    }

    private void checkTagsPresent(String slotName, WebElement slotElement) {
	//Turn off logging exceptions
	//TimeoutException is meant to be cought
	//and should not break the test
        Global.LOG_ENABLED  = false;
        try {
            waitForOneOfTagsPresentInElement(slotElement, "img", "iframe");
            PageObjectLogging.log(
                "IFrameOrImageFound",
                "Image or iframe was found in slot in less then 30 seconds",
                true,
                driver
            );
        } catch (TimeoutException e) {
            PageObjectLogging.log(
                "IFrameOrImgNotFound",
                "Nor image or iframe was found in slot for 30 seconds",
                false,
                driver
            );
        }
	//Turn on logging
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
	    String trimedResult = result.replaceAll("\\s", "");
            if (scriptExpectedResult.equals(trimedResult)) {
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

    private String createSelectorAll () {
	Collection slotsSelectors = adsContent.slotsSelectors.values();
	Integer size = slotsSelectors.size();
	Integer i = 1;
	String selectorAll = "";
	for (Object selector : slotsSelectors) {
	    selectorAll += (String) selector;
	    if (!i.equals(size)) {
		 selectorAll += ",";
	    }
	    i += 1;
	}
	return selectorAll;
    }

   public void verifyNoAds() throws Exception {
       openPage();
	List <WebElement> adsElements = driver.findElements(
	    By.cssSelector(createSelectorAll())
	);
	if (adsElements.isEmpty()) {
	    PageObjectLogging.log(
		"AdsNotFound",
		"Ads not found",
		true,
		driver
	    );
	    return;
	} else {
	    for (WebElement element : adsElements) {
		if (element.isDisplayed()
		    && (element.getSize().height > 1 && element.getSize().width > 1)
		) {
		    PageObjectLogging.log(
			"AdsFound",
			"Ads found on page",
			false,
			driver
		    );
		    throw new Exception("Found element that was not expected!");
		}
	    }
	PageObjectLogging.log(
	    "AdsNotFound",
	    "Ads not found",
	    true,
	    driver
	);
	}
    }
}
