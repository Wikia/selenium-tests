package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase;

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

import com.wikia.webdriver.Common.ContentPatterns.AdsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.NetworkTrafficInterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase.Helpers.AdsComparison;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
public class AdsBaseObject extends WikiBasePageObject {

	private final String wikiaMessageBuble = "#WikiaNotifications div[id*='msg']";
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
	protected WebElement presentLeaderboard;
	@FindBy(css="div[id*='TOP_RIGHT_BOXAD']")
	protected WebElement presentMedrec;
	@FindBy(css="div[id*='TOP_LEADERBOARD_gpt']")
	protected WebElement presentLeaderboardGpt;
	@FindBy(css="h3[id='headerWikis']")
	protected WebElement headerWhereIsMyExtensionPage;

	protected NetworkTrafficInterceptor networkTrafficInterceptor;
	protected String presentLeaderboardName;
	protected String presentLeaderboardSelector;
	protected String presentMedrecName;
	protected String presentMedrecSelector;

	private int skinWidth = 90;
	private int skinMarginTop = 100;
	private int skinMarginHorizontal = 5;

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
	}

	public AdsBaseObject(WebDriver driver, String testedPage, Dimension resolution) {
		super(driver);
		driver.manage().window().setSize(resolution);
		getUrl(testedPage);
	}

	private void setSlots() {
		if (checkIfElementOnPage(presentLeaderboard)) {
			presentLeaderboardName = presentLeaderboard.getAttribute("id");
			presentLeaderboardSelector = "#" + presentLeaderboardName;
		} else {
			presentLeaderboardName = null;
			presentLeaderboardSelector = null;
			presentLeaderboard = null;
		}
		if (checkIfElementOnPage(presentMedrec)) {
			presentMedrecName = presentMedrec.getAttribute("id");
			presentMedrecSelector = "#" + presentMedrecName;
		} else {
			presentMedrec = null;
			presentMedrecName = null;
			presentMedrecSelector = null;
		}
	}

	public void verifyRoadblockServedAfterMultiplePageViews(
			String adSkinUrl, String expectedAdSkinLeftPart, String expectedAdSkinRightPart, int numberOfPageViews
	) {
		setSlots();
		String leaderboardAd = getSlotImageAd(presentLeaderboard);
		String medrecAd = getSlotImageAd(presentMedrec);
		verifyAdSkinPresence(adSkinUrl, expectedAdSkinLeftPart, expectedAdSkinRightPart);

		for (int i=0; i <= numberOfPageViews; i++) {
			refreshPage();
			verifyAdSkinPresence(adSkinUrl, expectedAdSkinLeftPart, expectedAdSkinRightPart);
			Assertion.assertEquals(leaderboardAd, getSlotImageAd(presentLeaderboard));
			Assertion.assertEquals(medrecAd, getSlotImageAd(presentMedrec));
		}
	}

	public void verifyForcedSuccessScriptInSlots(List<String> slots) {
		for (String slot : slots) {
			WebElement slotElement = driver.findElement(By.id(slot));
			WebElement slotGptIframe = slotElement.findElement(By.cssSelector("div > iframe"));
			driver.switchTo().frame(slotGptIframe);
			WebElement iframeHtml = driver.findElement(By.tagName("html"));
			String adDriverForcedSuccessFormatted = String.format(
				AdsContent.adDriverForcedStatusSuccessScript, slot
			);
			if (isScriptPresentInElement(iframeHtml, adDriverForcedSuccessFormatted)) {
				PageObjectLogging.log(
					"AdDriver2ForceStatus script",
					"adDriverForcedSuccess script found in slot " + slot,
					true
				);
			} else {
				throw new NoSuchElementException(
					"AdDriver2ForcedStatus script not found in slot " + slot
				);
			}
			driver.switchTo().defaultContent();
		}
	}
	public void checkMedrec() {
		checkAdVisibleInSlot(presentMedrecSelector, presentMedrec);
	}

	public void checkTopLeaderboard() {
		checkAdVisibleInSlot(presentLeaderboardSelector, presentLeaderboard);
	}

	protected void checkAdVisibleInSlot(String slotSelector, WebElement slot ) {
		AdsComparison adsComparison = new AdsComparison();
		extractLiftiumTagId(slotSelector);
		boolean adVisible = adsComparison.isAdVisible(slot, slotSelector, driver);
		if (adVisible) {
			PageObjectLogging.log("CompareScreenshot", "Screenshots are different", true);
		} else {
			PageObjectLogging.log(
				"CompareScreenshot", "Screenshots look the same", false
			);
			throw new NoSuchElementException(
				"Screenshots of element on/off look the same."
				+ "Most probable ad is not present; CSS "
				+ slotSelector
			);
		}
	}

	/**
	 * Check Ad skin on page with provided resolution.
	 * Compare left and right sides of skin with provided Base64.
	 * @param adSkinUrl - DFP link with ad skin image
	 * @param expectedAdSkinLeftPart - path to file with expected skin encoded in Base64
	 * @param expectedAdSkinRightPart - path to file with expected skin encoded in Base64
	 */
	public void verifyAdSkinPresence(
			String adSkinUrl,
			String expectedAdSkinLeftPart, String expectedAdSkinRightPart
	) {
		AdsContent.setSlotsSelectors();

		String backgroundImageUrlAfter = getPseudoElementValue(
			body, ":after", "backgroundImage"
		);
		Assertion.assertStringContains(adSkinUrl, backgroundImageUrlAfter);

		String backgroundImageUrlBefore = getPseudoElementValue(
			body, ":before", "backgroundImage"
		);
		Assertion.assertStringContains(adSkinUrl, backgroundImageUrlBefore);

		PageObjectLogging.log(
			"ScreenshotPage",
			"Screenshot of the page taken",
			true, driver
		);

		AdsComparison adsComparison = new AdsComparison();
		adsComparison.hideSlot(AdsContent.getSlotSelector(AdsContent.wikiaBar), driver);
		hideMessage();

		int articleLocationX = wikiaArticle.getLocation().x;
		int articleWidth = wikiaArticle.getSize().width;
		Point articleLeftSideStartPoint = new Point(articleLocationX - skinMarginHorizontal - skinWidth, skinMarginTop);
		Point articleRightSideStartPoint = new Point(articleLocationX + articleWidth + skinMarginHorizontal, skinMarginTop);
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

	private void hideMessage() {
		if (checkIfElementOnPage(wikiaMessageBuble)) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("$(arguments[0]).css('visibility', 'hidden')", wikiaMessageBuble);
		}
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
		scrollToSelector(AdsContent.getSlotSelector(AdsContent.adsInContentContainer));
		scrollToSelector(AdsContent.getSlotSelector(AdsContent.prefootersContainer));
		verifyNoLiftiumAds();
		PageObjectLogging.log(
			"verifyNoLiftiumAdsOnPage",
			"No ads detected",
			true,
			driver
		);
	}

	public void verifyNoAdsOnPage() {
		scrollToSelector(AdsContent.getSlotSelector(AdsContent.adsInContentContainer));
		scrollToSelector(AdsContent.getSlotSelector(AdsContent.prefootersContainer));
		verifyNoAds();
		PageObjectLogging.log(
			"verifyNoAdsOnPage",
			"No ads detected",
			true,
			driver
		);
	}

	public void verifyNoAdsOnMobilePage() {
		scrollToSelector(AdsContent.getSlotSelector(AdsContent.mobileAdInContent));
		scrollToSelector(AdsContent.getSlotSelector(AdsContent.mobilePrefooter));
		verifyNoAds();
		PageObjectLogging.log(
			"verifyNoAdsOnMobilePage",
			"No ads detected",
			true,
			driver
		);

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

	protected boolean isScriptPresentInElement(WebElement element, String scriptText) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		scriptText = scriptText.replaceAll("\\s", "");

		for (WebElement scriptNode : element.findElements(By.tagName("script"))) {
			String result = (String) js.executeScript("return arguments[0].innerHTML", scriptNode);
			if (result.replaceAll("\\s", "").contains(scriptText)) {
				return true;
			}
		}

		return false;
	}

	private boolean checkScriptPresentInSlotScripts(String slotName, WebElement slotElement) throws Exception {
		String scriptExpectedResult = AdsContent.adsPushSlotScript.replace(
			"%slot%", slotName
		);
		boolean scriptFound = isScriptPresentInElement(slotElement, scriptExpectedResult);
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
					throw new WebDriverException("Ads found on page");
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
		if (checkIfElementOnPage(liftiumIframeSelector)) {
			throw new WebDriverException("Liftium ads found!");
		} else {
			PageObjectLogging.log("LiftiumAdsNotFound", "Liftium ads not found", true);
		}
	}

	private String extractLiftiumTagId(String slotSelector) {
		String liftiumTagId = null;
		WebElement slot = driver.findElement(By.cssSelector(slotSelector));
		if (checkIfElementInElement(liftiumIframeSelector, slot)) {
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

	protected String getSlotImageAd(WebElement slot) {
		WebElement iframeWithAd = slot.findElement(
			By.cssSelector("div > iframe:not([id*='hidden'])")
		);
		driver.switchTo().frame(iframeWithAd);
		String imageAd = driver.findElement(
			By.cssSelector("img")
		).getAttribute("src");
		driver.switchTo().defaultContent();
		return imageAd;
	}

	protected boolean checkIfSlotExpanded(WebElement slot) {
		return slot.getSize().getHeight() > 1 && slot.getSize().getWidth() > 1;
	}

	public void verifyNoLiftiumAdsInSlots(List<String> slots) {
		for (String slot : slots) {
			WebElement slotElement = driver.findElement(By.id(slot));
			if (checkIfElementInElement(liftiumIframeSelector, slotElement)) {
				throw new NoSuchElementException("Liftium found in slot " + slot);
			} else {
				PageObjectLogging.log("LiftiumNotFound", "Liftium not found in slot " + slot, true);
			}
		}
	}

	/**
	 * Test whether the correct GPT ad unit is called
	 *
	 * @param adUnit the ad unit passed to GPT, like wka.wikia/_wikiaglobal//home
	 */
	public void verifyGptIframe(String adUnit) {
		String slotName = presentLeaderboard.getAttribute("id");
		String iframeId = "google_ads_iframe_/5441/" + adUnit + "/" + slotName + "_gpt_0";
		String cssSelector = "iframe[id^='" + iframeId + "']";

		if (checkIfElementInElement(cssSelector, presentLeaderboard)) {
			String msg = "GPT iframe #" + iframeId + " found in slot " + slotName;
			PageObjectLogging.log("verifyGptIframe", msg, true, driver);
		} else {
			String msg = "GPT iframe #" + iframeId + " not found for slot " + slotName;
			PageObjectLogging.log("verifyGptIframe", msg, false, driver);
			throw new NoSuchElementException(msg);
		}
	}

	public void verifyTop1kParamState(Boolean isTop1k){
		waitForElementByElement(presentLeaderboard);
		String dataGptPageParams = presentLeaderboardGpt.getAttribute("data-gpt-page-params");
		String top1k = "\"top\":\"1k\"";

		if (isTop1k) {
			Assertion.assertTrue(dataGptPageParams.contains(top1k), "parameter 1k not found");
			PageObjectLogging.log(
					"verifyTop1kParamState",
					"Verification done, parameter found " + dataGptPageParams,
					true,
					driver
			);
		} else {
			Assertion.assertFalse(dataGptPageParams.contains(top1k), "parameter 1k found");
			PageObjectLogging.log(
					"verifyTop1kParamState",
					"Verification done, parameter not found " + dataGptPageParams,
					true,
					driver
			);
		}
	}

	public void verifyNumberOfTop1kWikis(Integer numberOfWikis){
		String pattern = "List of wikis with matched criteria ("+numberOfWikis+")";
		waitForElementByElement(headerWhereIsMyExtensionPage);
		PageObjectLogging.log(
				"verifyNumberOfTop1kWikis",
				"Verification of top 1k wikis",
				true,
				driver
		);
		Assertion.assertStringContains(pattern, headerWhereIsMyExtensionPage.getText());
	}
}
