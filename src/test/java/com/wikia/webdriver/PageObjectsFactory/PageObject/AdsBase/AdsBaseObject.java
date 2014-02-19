package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase;

import com.wikia.webdriver.Common.ContentPatterns.AdsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.ImageUtilities.Shooter;
import com.wikia.webdriver.Common.Core.NetworkTrafficInterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Helpers.AdsComparison;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Bogna 'bognix' Knychala
 */
public class AdsBaseObject extends WikiBasePageObject {

	private final String liftiumIframeSelector = "iframe[id*='Liftium']";

	@FindBy(css=AdsContent.wikiaBarSelector)
	private WebElement toolbar;
	@FindBy(css="#WikiaPage")
	private WebElement wikiaArticle;
	@FindBy(css=".WikiaSpotlight")
	private List<WebElement> spotlights;
	@FindBy(css=liftiumIframeSelector)
	private List<WebElement> liftiumIframes;
	@FindBy(css="div[id*='TOP_LEADERBOARD']")
	private WebElement presentLeaderboard;
	@FindBy(css="div[id*='TOP_RIGHT_BOXAD']")
	private WebElement presentMedrec;

	protected Boolean isWikiMainPage;
	protected NetworkTrafficInterceptor networkTrafficInterceptor;

	private String presentLeaderboardName;
	private String presentLeaderboardSelector;
	private String presentMedrecName;
	private String presentMedrecSelector;

	public AdsBaseObject(WebDriver driver, String page) {
		super(driver);
		AdsContent.setSlotsSelectors();
		getUrl(page);
		setSlots();
	}

	public AdsBaseObject(
		WebDriver driver, String page,
		NetworkTrafficInterceptor networkTrafficInterceptor
	) {
		super(driver);
		AdsContent.setSlotsSelectors();
		networkTrafficInterceptor.startIntercepting(page);
		getUrl(page);
		this.networkTrafficInterceptor = networkTrafficInterceptor;
		setSlots();
	}

	public AdsBaseObject(WebDriver driver) {
		super(driver);
		AdsContent.setSlotsSelectors();
		setSlots();
	}

	private void setSlots() {
		presentLeaderboardName = presentLeaderboard.getAttribute("id");
		presentLeaderboardSelector = "#" + presentLeaderboardName;
		presentMedrecName = presentMedrec.getAttribute("id");
		presentMedrecSelector = "#" + presentMedrecName;
	}

	public void checkMedrec() {
		AdsComparison adsComparison = new AdsComparison();
		extractLiftiumTagId(presentMedrecSelector);
		boolean isHidden = checkIfSlotHiddenBySlotTweaker(presentMedrec, presentMedrecName);

		if (!isHidden) {
			boolean result = adsComparison.compareSlotOnOff(
				presentMedrec, presentMedrecSelector, driver
			);
			if(result) {
				PageObjectLogging.log(
					"CompareScreenshot", "Screenshots look the same", false
				);
				throw new NoSuchElementException(
					"Screenshots of element on/off look the same."
					+ "Most probable ad is not present; CSS "
					+ presentMedrecName
				);
			} else {
				PageObjectLogging.log(
					"CompareScreenshot", "Screenshots are different", true
				);
			}
		} else {
			PageObjectLogging.log(
				"SlotHiddenBySlotTweaker",
				"Slot is hidden by slotTweaker, slotTweaker scrpit found inside",
				true
			);
		}
	}

	public void checkTopLeaderboard() {
		AdsComparison adsComparison = new AdsComparison();
		extractLiftiumTagId(presentLeaderboardSelector);
		boolean result = adsComparison.compareSlotOnOff(
			presentLeaderboard, presentLeaderboardSelector, driver
		);
		if(result) {
			PageObjectLogging.log(
				"CompareScreenshot", "Screenshots look the same", false
			);
			throw new NoSuchElementException(
				"Screenshots of element on/off look the same."
				+ "Most probable ad is not present; CSS "
				+ presentLeaderboardSelector
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
	 * @param page - url
	 * @param adSkinUrl - DFP link with ad skin image
	 * @param windowResolution - resolution
	 * @param skinWidth - skin width on the sides of the article
	 * @param expectedAdSkinLeftPart - path to file with expected skin encoded in Base64
	 * @param expectedAdSkinRightPart - path to file with expected skin encoded in Base64
	 * @throws IOException
	 */
	public void checkAdSkinPresenceOnGivenResolution(
		String page, String adSkinUrl, Dimension windowResolution, int skinWidth,
		String expectedAdSkinLeftPart, String expectedAdSkinRightPart
	) throws IOException {
		Shooter shooter = new Shooter();
		driver.manage().window().setSize(windowResolution);
		getUrl(page);
		AdsContent.setSlotsSelectors();

		String backgroundImageUrlAfter = getPseudoElementValue(
			body, ":after", "backgroundImage"
		);
		Assertion.assertStringContains(backgroundImageUrlAfter, adSkinUrl);

		String backgroundImageUrlBefore = getPseudoElementValue(
			body, ":before", "backgroundImage"
		);
		Assertion.assertStringContains(backgroundImageUrlBefore, adSkinUrl);

		PageObjectLogging.log(
			"ScreenshotPage",
			"Screenshot of the page taken",
			true, driver
		);

		AdsComparison adsComparison = new AdsComparison();
		adsComparison.hideSlot(AdsContent.getSlotSelector(AdsContent.wikiaBar), driver);

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
				"ExpectedSkinFound",
				"Expected ad skin found on page - left side of skin",
				true
			);
		} else {
			PageObjectLogging.log(
				"ExpectedSkinNotFound",
				"Expected ad skin not found on page - left side of skin",
				false, driver
			);
		}

		boolean successRight = adsComparison.compareImageWithScreenshot(
			expectedAdSkinRightPart, skinSize, articleRightSideStartPoint, driver
		);
		if (successRight) {
			PageObjectLogging.log(
				"ExpectedSkinFound",
				"Expected ad skin found on page - right side of skin",
				true
			);
		} else {
			PageObjectLogging.log(
				"ExpectedSkinNotFound",
				"Expected ad skin not found on page - right side of skin",
				false, driver
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
		waitForElementByElement(presentLeaderboard);
		checkScriptPresentInSlotScripts(presentLeaderboardName, presentLeaderboard);
		checkTagsPresent(presentLeaderboard);

		waitForElementByElement(presentMedrec);
		checkScriptPresentInSlotScripts(presentMedrecName, presentMedrec);
		checkTagsPresent(presentMedrec);
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

	public void verifyNoLiftiumAdsOnPage() {
		scrollToSelector(AdsContent.getSlotSelector("AdsInContent"));
		scrollToSelector(AdsContent.getSlotSelector("Prefooters"));
		verifyNoLiftiumAds();
	}

	public void verifyNoAdsOnPage() {
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

	private boolean checkScriptPresentInSlot(WebElement slot, String script) {
		List<WebElement> scriptsTags = slot.findElements(By.tagName("script"));
		return checkIfScriptInsideScripts(scriptsTags, script);
	}

	protected boolean checkIfScriptInsideScripts(List<WebElement> scripts, String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (WebElement scriptNode : scripts) {
			String result = (String) js.executeScript(
				"return arguments[0].innerHTML", scriptNode
			);
			String trimedResult = result.replaceAll("\\s", "");
			if (trimedResult.contains(script)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkScriptPresentInSlotScripts(String slotName, WebElement slotElement) throws Exception {
		String scriptExpectedResult = AdsContent.adsPushSlotScript.replace(
			"%slot%", slotName
		);
		boolean scriptFound = checkScriptPresentInSlot(slotElement, scriptExpectedResult);
		if (scriptFound) {
			PageObjectLogging.log(
				"PushSlotsScriptFound",
				"Script " + scriptExpectedResult + " found",
				true
			);
		} else {
			PageObjectLogging.log(
				"PushSlotsScriptNotFound",
				"Script " + scriptExpectedResult + " not found",
				false,
				driver
			);
			throw new Exception("Script for pushing ads not found in element");
		}
		return scriptFound;
	}

	protected boolean checkIfSlotHiddenBySlotTweaker(WebElement slot, String slotName) {
		WebElement firstLevelIframe = slot.findElement(
			By.cssSelector("iframe[id*=" + slotName + "]")
		);

		//Prepate slotTweaker script's draft and look for it inside slot's iframe
		driver.switchTo().frame(firstLevelIframe);
		String slotTweakerHideMedrecScript = AdsContent.slotTweakerHideSlotScript.replaceAll(
			"%slot%", slotName
		);
		boolean result = checkScriptPresentInSlot(body, slotTweakerHideMedrecScript);

		driver.switchTo().defaultContent();
		return result;
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

    private void verifyNoAds() {
		Collection<String> slotsSelectors = AdsContent.slotsSelectors.values();
		for (String selector: slotsSelectors) {
			if (checkIfElementOnPage(selector)) {
				WebElement element = driver.findElement(By.cssSelector(selector));
				if (
					element.isDisplayed()
					&& element.getSize().getHeight() > 1
					&& element.getSize().getWidth() > 1
				) {
					PageObjectLogging.log(
						"AdsFound",
						"Ads found on page with selector: " + selector,
						false,
						driver
					);
				} else {
					PageObjectLogging.log(
						"AdsFoundButNotVisible",
						"Ads found on page with selector: "
						+ selector
						+ " but is smaller then 1x1 or hidden",
						true
					);
				}
			} else {
				PageObjectLogging.log(
					"AdNotFound",
					"Ad with selector: "
					+ selector
					+ " not found on page",
					true
				);
			}
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

	private void verifyNoLiftiumAds() {
		if (liftiumIframes.size() > 0) {
			throw new WebDriverException("Liftium ads found!");
		}
	}

	public void verifyNoSpotlights() {
		for (WebElement spotlight: spotlights) {
			if (spotlight.isDisplayed()) {
				PageObjectLogging.log("SpotlightVisible", "Spotlight visible, should be hidden", false);
				throw new WebDriverException("Spotlight visible, should be hidden");
			}
		}
		PageObjectLogging.log("SpotlightsHidden", "Spotlights are hidden", true);
	}

	private String extractLiftiumTagId(String slotSelector) {
		String liftiumTagId = null;
		WebElement slot = driver.findElement(By.cssSelector(slotSelector));
		if (slot.findElements(By.cssSelector(liftiumIframeSelector)).size() > 0) {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			WebElement currentLiftiumIframe = (WebElement)js.executeScript(
				"return $(arguments[0] + ' iframe[id*=\\'Liftium\\']:visible')[0];",
				slotSelector
			);
			String liftiumAdSrc = currentLiftiumIframe.getAttribute("src");
			Pattern pattern = Pattern.compile("tag_id=\\d*");
			Matcher matcher = pattern.matcher(liftiumAdSrc);
			if (matcher.find()) {
				liftiumTagId = matcher.group().replaceAll("[^\\d]", "");
			}
		}

		if (liftiumTagId != null) {
			PageObjectLogging.log(
				"LiftiumTagId",
				"Present liftium tag id is: "
				+ liftiumTagId + "; in slot: " + slotSelector,
				true
			);
		} else {
			PageObjectLogging.log(
				"LiftiumTagId",
				"Liftium not present in slot: " + slotSelector,
				true
			);
		}

		return liftiumTagId;
	}
}
