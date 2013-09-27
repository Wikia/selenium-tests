package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase;

import com.wikia.webdriver.Common.ContentPatterns.AdsContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import java.util.Collection;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsBaseObject extends WikiBasePageObject {

	protected Boolean isWikiMainPage;

	private WebElement presentLB;
	protected String presentLBName;
	private WebElement presentMD;
	protected String presentMDName;

	public AdsBaseObject(WebDriver driver, String page) {
		super(driver);
		AdsContent.setSlotsSelectors();
		getUrl(page);
		isWikiMainPage = checkIfMainPage();
	}

	protected void setPresentTopLeaderboard() {
		if (isWikiMainPage) {
			presentLB = driver.findElement(
				By.cssSelector(AdsContent.getSlotSelector(AdsContent.homeTopLB))
			);
			presentLBName = AdsContent.homeTopLB;
		} else {
			presentLB = driver.findElement(
				By.cssSelector(AdsContent.getSlotSelector(AdsContent.topLB))
			);
			presentLBName = AdsContent.topLB;
		}
	}

	protected void setPresentMedrec() {
		if (isWikiMainPage) {
			presentMD = driver.findElement(
				By.cssSelector(AdsContent.getSlotSelector(AdsContent.homeMedrec))
			);
			presentMDName = AdsContent.homeMedrec;
		} else {
			presentMD = driver.findElement(
				By.cssSelector(AdsContent.getSlotSelector(AdsContent.medrec))
			);
			presentMDName = AdsContent.medrec;
		}
	}

	protected WebElement getPresentTopLeaderBoard() {
		return presentLB;
	}

	protected WebElement getPresentMedrec() {
		return presentMD;
	}


	public void verifyMedrecPresent() throws Exception {
		if (isWikiMainPage) {
			presentMD = driver.findElement(
				By.cssSelector(AdsContent.getSlotSelector(AdsContent.homeMedrec))
			);
			presentMDName = AdsContent.homeMedrec;
		} else {
			presentMD = driver.findElement(
				By.cssSelector(AdsContent.getSlotSelector(AdsContent.medrec))
			);
			presentMDName = AdsContent.medrec;
		}
		waitForElementByElement(presentMD);
		checkScriptPresentInSlotScripts(presentMDName, presentMD);
		checkTagsPresent(presentMD);
	}

	public void verifyPrefooters() {
		String prefooterSelector = AdsContent.getSlotSelector("Prefooters");
		WebElement prefooterElement = driver.findElement(By.cssSelector(prefooterSelector));

		//Scroll to AIC container and wait for <div> to be present inside it
		if (!scrollToSelector(prefooterSelector)) {
			PageObjectLogging.log(
				"SelectorNotFound",
				"Selector " + prefooterSelector + " not found on page",
				false,
				driver
			);
		}
		checkTagsPresent(prefooterElement);
	}

	public void verifyTopLeaderBoardAndMedrec() throws Exception {
		setPresentTopLeaderboard();
		waitForElementByElement(presentLB);
		checkScriptPresentInSlotScripts(presentLBName, presentLB);
		checkTagsPresent(presentLB);

		setPresentMedrec();
		waitForElementByElement(presentMD);
		checkScriptPresentInSlotScripts(presentMDName, presentMD);
		checkTagsPresent(presentMD);
	}

	public void verifyHubTopLeaderboard() throws Exception {
		String hubLBName = AdsContent.hubLB;
		WebElement hubLB = driver.findElement(By.cssSelector(AdsContent.getSlotSelector(hubLBName)));
		checkScriptPresentInSlotScripts(hubLBName, hubLB);
		PageObjectLogging.log("HUB_TOP_LEADERBOARD found", "HUB_TOP_LEADERBOARD found", true);

		WebElement hubGPT_LB = hubLB.findElement(By.cssSelector(AdsContent.getSlotSelector(AdsContent.hubLB_gpt)));
		PageObjectLogging.log("HUB_TOP_LEADERBOARD_gpt found", "HUB_TOP_LEADERBOARD_gpt found", true);

		if(hubGPT_LB.findElements(By.cssSelector("iframe")).size() > 1) {
			PageObjectLogging.log("IFrames found", "2 IFrames found in HUB_TOP_LEADERBOAD_gpt div", true);
		} else {
			PageObjectLogging.log(
				"IFrames not found",
				"2 IFrames expected to be found in HUB_TOP_LEADERBOAD_gpt div, found less",
				false, driver
			);
			throw new Exception("IFrames inside GPT div not found!");
		}
	}

	public void verifyNoLiftiumAdsOnPage() throws Exception {
		scrollToSelector(AdsContent.getSlotSelector("AdsInContent"));
		scrollToSelector(AdsContent.getSlotSelector("Prefooters"));
		verifyNoLiftiumAds();
	}

	public void verifyNoAdsOnPage() throws Exception {
		scrollToSelector(AdsContent.getSlotSelector("AdsInContent"));
		scrollToSelector(AdsContent.getSlotSelector("Prefooters"));
		verifyNoAds();
	}

	public boolean verifyAdsInContent() {
		String aicSelector = AdsContent.getSlotSelector("AdsInContent");
		WebElement aicContainer = driver.findElement(By.cssSelector(aicSelector));

		//Scroll to AIC container and wait for <div> to be present inside it
		if (!scrollToSelector(aicSelector)) {
			PageObjectLogging.log(
				"SelectorNotFound",
				"Selector " + aicSelector + " not found on page",
				false,
				driver
			);
			return false;
		}
		waitForElementByElement(aicContainer.findElement(By.cssSelector("div")));
		return checkTagsPresent(aicContainer);
	}

	public boolean verifyCorrectAdInMedrec(
		String acceptableAd, String unacceptableAd
	) throws Exception {
		int refreshNumber = 0;
		while(refreshNumber < 5) {
			verifyMedrecPresent();
			Dimension acceptableDimension = new Dimension(1, 1);
			List<WebElement> iframes = presentMD.findElements(By.cssSelector("iframe"));
			for (WebElement iframe : iframes) {
				if (
					iframe.isDisplayed()
					&&(iframe.getSize().height >= acceptableDimension.height
					&&iframe.getSize().width >= acceptableDimension.width)
				) {
					driver.switchTo().frame(iframe);
					if (checkIfElementOnPage("img[src='" + acceptableAd + "']")) {
						PageObjectLogging.log(
							"AdFound", "Expected ad found",
							true
						);
						return true;
					} else if (checkIfElementOnPage("img[src='" + unacceptableAd + "']")) {
						throw new Exception("Found element that was not expected!");
					} else {
						PageObjectLogging.log(
							"AdNotFound", "Expected ad not found, refreshing the page",
							true, driver
						);
						driver.navigate().refresh();
						refreshNumber += 1;
					}
				}
			}
		}
		return true;
	}

	public boolean verifyCorrectAdInFloatingMedrec (
		String acceptableAd, String unacceptableAd
	) throws Exception {
		int refreshNumber = 0;
		while(refreshNumber < 5) {
			verifyAdsInContent();
			WebElement floatingMedrec = driver.findElement(
				By.cssSelector(AdsContent.getSlotSelector("AdsInContent"))
			);
			Dimension acceptableDimension = new Dimension(1, 1);
			List<WebElement> iframes = floatingMedrec.findElements(By.cssSelector("iframe"));
			for (WebElement iframe : iframes) {
				if (
					iframe.isDisplayed()
					&&(iframe.getSize().height >= acceptableDimension.height
					&&iframe.getSize().width >= acceptableDimension.width)
				) {
					driver.switchTo().frame(iframe);
					if (checkIfElementOnPage("img[src='" + acceptableAd + "']")) {
						PageObjectLogging.log(
							"AdFound", "Expected ad found",
							true
						);
						return true;
					} else if (checkIfElementOnPage("img[src='" + unacceptableAd + "']")) {
						throw new Exception("Found element that was not expected!");
					} else {
						PageObjectLogging.log(
							"AdNotFound", "Expected ad not found, refreshing the page",
							true, driver
						);
						driver.navigate().refresh();
						refreshNumber += 1;
					}
				}
			}
		}
		PageObjectLogging.log(
			"AdNotFound",
			"Expected ad not found, after refreshing 5 times",
			false, driver
		);
		return true;
	}

	private boolean checkTagsPresent(WebElement slotElement) {
		try {
			waitForOneOfTagsPresentInElement(slotElement, "img", "iframe");
			PageObjectLogging.log(
				"IFrameOrImageFound",
				"Image or iframe was found in slot in less then 30 seconds",
				true,
				driver
			);
			return true;
		} catch (TimeoutException e) {
			PageObjectLogging.log(
				"IFrameOrImgNotFound",
				"Nor image or iframe was found in slot for 30 seconds",
				false,
				driver
			);
			return false;
		}
	}

	private List checkScriptPresentInSlotScripts(String slotName, WebElement slotElement) throws Exception {
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
			throw new Exception("Script for pushing ads not found in element");
		}
		return scriptsTags;
	}

	private String createSelectorAll () {
		Collection slotsSelectors = AdsContent.slotsSelectors.values();
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

    private void verifyNoAds() throws Exception {
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

	private void verifyNoLiftiumAds() throws Exception {
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
		} else {
			for (WebElement element : adsElements) {
				if (element.getAttribute("id").contains("liftium")) {
					PageObjectLogging.log(
						"LiftiumAdFound",
						"Liftium ads found on page",
						false,
						driver
					);
					throw new Exception("Found element that was not expected!");
				} else {
					PageObjectLogging.log(
						"LiftiumAdsNotFound",
						"Liftium Ads not found",
						true,
						driver
					);
				}
			}
		}
	}
}
