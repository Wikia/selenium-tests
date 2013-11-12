package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase;

import com.wikia.webdriver.Common.ContentPatterns.AdsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.ImageUtilities.Shooter;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Helpers.AdsComparison;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AdsBaseObject extends WikiBasePageObject {

	@FindBy(css=AdsContent.wikiaBarSelector)
	protected WebElement toolbar;
	@FindBy(css="#WikiaPage")
	private WebElement wikiaArticle;

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
		setPresentTopLeaderboard();
		setPresentMedrec();
	}

	protected final void setPresentTopLeaderboard() {
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

	protected final void setPresentMedrec() {
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

	public void checkMedrec() {
		AdsComparison adsComparison = new AdsComparison();
		boolean result = adsComparison.compareSlotOnOff(
			presentMD, AdsContent.getSlotSelector(presentMDName), driver
		);
		if(result) {
			PageObjectLogging.log(
				"CompareScreenshot", "Screenshots look the same", false
			);
			throw new NoSuchElementException(
				"Screenshots of element on/off look the same."
				+ "Most probable ad is not present; CSS "
				+ AdsContent.getSlotSelector(presentMDName)
			);
		} else {
			PageObjectLogging.log(
				"CompareScreenshot", "Screenshots are different", true
			);
		}
	}

	public void checkTopLeaderboard() {
		AdsComparison adsComparison = new AdsComparison();
		boolean result = adsComparison.compareSlotOnOff(
			presentLB, AdsContent.getSlotSelector(presentLBName), driver
		);
		if(result) {
			PageObjectLogging.log(
				"CompareScreenshot", "Screenshots look the same", false
			);
			throw new NoSuchElementException(
				"Screenshots of element on/off look the same."
				+ "Most probable ad is not present; CSS "
				+ AdsContent.getSlotSelector(presentLBName)
			);
		} else {
			PageObjectLogging.log(
				"CompareScreenshot", "Screenshots are different", true
			);
		}
	}

	/**
	 * Check Ad skin on page with provided resolution.
	 * Compare left and right sides of skin with provided Base64.
	 * @param adSkinUrl - DFP link with ad skin image
	 * @param windowResolution - resolution
	 * @param skinWidth - skin width on the sides of the article
	 * @param expectedAdSkinLeftPart - path to file with expected skin encoded in Base64
	 * @param expectedAdSkinRightPart - path to file with expected skin encoded in Base64
	 * @throws IOException
	 */
	public void checkAdSkinPresenceOnGivenResolution(
		String adSkinUrl, Dimension windowResolution, int skinWidth,
		String expectedAdSkinLeftPart, String expectedAdSkinRightPart
	) throws IOException {
		Shooter shooter = new Shooter();

		String backgroundImageUrlAfter = getPseudoElementValue(
			body, ":after", "backgroundImage"
		);
		Assertion.assertStringContains(backgroundImageUrlAfter, adSkinUrl);

		String backgroundImageUrlBefore = getPseudoElementValue(
			body, ":before", "backgroundImage"
		);
		Assertion.assertStringContains(backgroundImageUrlBefore, adSkinUrl);


		driver.manage().window().setSize(windowResolution);

		PageObjectLogging.log(
			"ScreenshotPage",
			"Screenshot of the page taken",
			true, driver
		);

		AdsComparison adsComparison = new AdsComparison();
		int articleLocationX = wikiaArticle.getLocation().x;
		int articleWidth = wikiaArticle.getSize().width;
		Point articleLeftSideStartPoint = new Point(articleLocationX - skinWidth,100);
		Point articleRightSideStartPoint = new Point(articleLocationX + articleWidth,100);
		Dimension skinSize = new Dimension(skinWidth, 500);

		boolean successLeft = adsComparison.compareImageWithScreenshot(
			expectedAdSkinLeftPart, skinSize, articleLeftSideStartPoint, driver
		);
		if (successLeft) {
			PageObjectLogging.log(
				"ExpectedSkinFound", "Expected ad skin found on page - left side of skin", true
			);
		} else {
			PageObjectLogging.log(
				"ExpectedSkinNotFound", "Expected ad skin not found on page - left side of skin", false, driver
			);
		}

		boolean successRight = adsComparison.compareImageWithScreenshot(
			expectedAdSkinRightPart, skinSize, articleRightSideStartPoint, driver
		);
		if (successRight) {
			PageObjectLogging.log(
				"ExpectedSkinFound", "Expected ad skin found on page - right side of skin", true
			);
		} else {
			PageObjectLogging.log(
				"ExpectedSkinNotFound", "Expected ad skin not found on page - right side of skin", false, driver
			);
		}

		if (!successLeft || !successRight) {
			throw new NoSuchElementException("Skin not found on page");
		}
	}

	public void checkExpectedToolbar(
		String expectedToolbarFilePath, Dimension expectedToolbarSize
	) throws IOException {
		AdsComparison adsComparison = new AdsComparison();
		boolean result = adsComparison.compareElementWithScreenshot(
			toolbar, expectedToolbarFilePath, expectedToolbarSize, driver
		);
		if (result) {
			PageObjectLogging.log(
				"ExpectedAdFound", "Expected ad found in toolbar", true
			);
		} else {
			PageObjectLogging.log(
				"ExpectedAdNotFound", "Expected ad not found in toolbar", false
			);
			throw new NoSuchElementException(
				"Expected ad not found on page"
				+ "CSS: "
				+ AdsContent.wikiaBarSelector
			);
		}
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

	protected void verifyAdsFromProvider(String providerName, List<WebElement> slots) {
		String providerSpecificSelector = AdsContent.getElementForProvider(providerName);
		for (WebElement slot: slots) {
			if (!checkIfElementInElement(providerSpecificSelector, slot)) {
				PageObjectLogging.log(
					"NoAdsFromProvider",
					"Ads from " + providerName
					+ " not found in slot: " + slot.getAttribute("id"),
					false
				);
				throw new NoSuchElementException(
					"Call to provider: " + providerName
					+ " in slot: " + slot.getAttribute("id") + " not found!"
				);
			}
			PageObjectLogging.log(
				"AdsFromProviderFound",
				"Ads from " + providerName
				+ " found in slot: " + slot.getAttribute("id"),
				true
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
