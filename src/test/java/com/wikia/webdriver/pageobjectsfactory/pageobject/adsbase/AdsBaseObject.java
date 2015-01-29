package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsComparison;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Bogna 'bognix' Knychala
 * @ownership AdEngineering
 */
public class AdsBaseObject extends WikiBasePageObject {

	// Constants
	private static final String KRUX_CONTROL_TAG_URL_PREFIX = "http://cdn.krxd.net/controltag?confid=";
	private static final String LOGGING_PARAMS = "log_level=9&log_group=Wikia.Tracker";
	private static final int SKIN_WIDTH = 90;
	private static final int SKIN_MARGIN_TOP = 100;
	private static final int SKIN_MARGIN_HORIZONTAL = 5;
	private static final String[] GPT_DATA_ATTRIBUTES = {
		"data-gpt-line-item-id",
		"data-gpt-creative-id",
		"data-gpt-creative-size",
	};

	private static final String[] SPOTLIGHT_SLOTS = {
		"#SPOTLIGHT_FOOTER_1",
		"#SPOTLIGHT_FOOTER_2",
		"#SPOTLIGHT_FOOTER_3",
	};

	// Selectors
	private static final String WIKIA_MESSAGE_BUBLE = "#WikiaNotifications div[id*='msg']";
	private static final String LIFTIUM_IFRAME_SELECTOR = "iframe[id*='Liftium']";
	private static final String GPT_DIV_SELECTOR = "[data-gpt-creative-size]";
	private static final String TOP_INCONTENT_BOXAD_SELECTOR = "div[id*='TOP_INCONTENT_BOXAD']";

	// Elements
	@FindBy(css = AdsContent.WIKIA_BAR_SELECTOR)
	private WebElement toolbar;
	@FindBy(css = "#WikiaPage")
	private WebElement wikiaArticle;
	@FindBy(css = ".WikiaSpotlight")
	private List<WebElement> spotlights;
	@FindBy(css = LIFTIUM_IFRAME_SELECTOR)
	private List<WebElement> liftiumIframes;
	@FindBy(css = "div[id*='TOP_LEADERBOARD']")
	protected WebElement presentLeaderboard;
	@FindBy(css = "div[id*='TOP_RIGHT_BOXAD']")
	protected WebElement presentMedrec;
	@FindBy(css = "div[id*='TOP_LEADERBOARD_gpt']")
	protected WebElement presentLeaderboardGpt;
	@FindBy(css = TOP_INCONTENT_BOXAD_SELECTOR)
	protected WebElement topIncontentBoxad;

	@FindBy(css = "script[src^=\"" + KRUX_CONTROL_TAG_URL_PREFIX + "\"]")
	private WebElement kruxControlTag;

	protected NetworkTrafficInterceptor networkTrafficInterceptor;
	protected String presentLeaderboardName;
	protected String presentLeaderboardSelector;
	protected String presentMedrecName;
	protected String presentMedrecSelector;

	public AdsBaseObject(WebDriver driver, String page) {
		super(driver);
		AdsContent.setSlotsSelectors();
		getUrl(page, true);
		setSlots();
	}

	public AdsBaseObject(
		WebDriver driver, String page,
		NetworkTrafficInterceptor networkTrafficInterceptor
	) {
		super(driver);
		AdsContent.setSlotsSelectors();
		networkTrafficInterceptor.startIntercepting(page);
		getUrl(page, true);
		this.networkTrafficInterceptor = networkTrafficInterceptor;
		setSlots();
	}

	public AdsBaseObject(WebDriver driver) {
		super(driver);
		AdsContent.setSlotsSelectors();
		setSlots();
	}

	public AdsBaseObject(WebDriver driver, String testedPage, Dimension resolution) {
		super(driver);
		driver.manage().window().setSize(resolution);
		getUrl(testedPage, true);
		AdsContent.setSlotsSelectors();
		setSlots();
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
		String leaderboardAd = urlBuilder.removeProtocolServerNameFromUrl(getSlotImageAd(presentLeaderboard));
		String medrecAd = urlBuilder.removeProtocolServerNameFromUrl(getSlotImageAd(presentMedrec));
		verifyAdSkinPresence(adSkinUrl, expectedAdSkinLeftPart, expectedAdSkinRightPart);

		for (int i = 0; i <= numberOfPageViews; i++) {
			refreshPage();
			verifyAdSkinPresence(adSkinUrl, expectedAdSkinLeftPart, expectedAdSkinRightPart);
			Assertion.assertStringContains(leaderboardAd, getSlotImageAd(presentLeaderboard));
			Assertion.assertStringContains(medrecAd, getSlotImageAd(presentMedrec));
		}
	}

	public void verifyForcedSuccessScriptInSlots(List<String> slots) {
		for (String slot : slots) {
			WebElement slotElement = driver.findElement(By.id(slot));
			WebElement slotGptIframe = slotElement.findElement(By.cssSelector("div > iframe"));
			driver.switchTo().frame(slotGptIframe);
			WebElement iframeHtml = driver.findElement(By.tagName("html"));
			String adDriverForcedSuccessFormatted = String.format(
				AdsContent.AD_DRIVER_FORCED_STATUS_SUCCESS_SCRIPT, slot
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

	public void checkTopIncontentBoxad() {
		checkAdVisibleInSlot(TOP_INCONTENT_BOXAD_SELECTOR, topIncontentBoxad);
	}

	protected void checkAdVisibleInSlot(String slotSelector, WebElement slot) {
		verifySlotExpanded(slot);
		boolean adVisible = new AdsComparison().isAdVisible(slot, slotSelector, driver);
		extractLiftiumTagId(slotSelector);
		extractGptInfo(slotSelector);
		if (!adVisible) {
			throw new NoSuchElementException(
				"Ad is not present in " + slotSelector
			);
		}
		PageObjectLogging.log("ScreenshotsComparison", "Ad is present in " + slotSelector, true);
	}

	public void verifySlotExpanded(WebElement element) {
		if (!checkIfSlotExpanded(element)) {
			throw new NoSuchElementException(element.getAttribute("id") + " is collapsed");
		}
	}

	/**
	 * Check Ad skin on page with provided resolution.
	 * Compare left and right sides of skin with provided Base64.
	 *
	 * @param adSkinUrl               - DFP link with ad skin image
	 * @param expectedAdSkinLeftPart  - path to file with expected skin encoded in Base64
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
		adsComparison.hideSlot(AdsContent.getSlotSelector(AdsContent.WIKIA_BAR), driver);
		hideMessage();

		int articleLocationX = wikiaArticle.getLocation().x;
		int articleWidth = wikiaArticle.getSize().width;
		Point articleLeftSideStartPoint = new Point(articleLocationX - SKIN_MARGIN_HORIZONTAL - SKIN_WIDTH, SKIN_MARGIN_TOP);
		Point articleRightSideStartPoint = new Point(articleLocationX + articleWidth + SKIN_MARGIN_HORIZONTAL, SKIN_MARGIN_TOP);
		Dimension skinSize = new Dimension(SKIN_WIDTH, 500);

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
		hideElement(WIKIA_MESSAGE_BUBLE);
	}

	protected void hideElement(String cssSelector) {
		if (checkIfElementOnPage(cssSelector)) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("$(arguments[0]).css('visibility', 'hidden')", cssSelector);
		}
	}

	public void verifyHubTopLeaderboard() throws Exception {
		String hubLBName = AdsContent.HUB_LB;
		WebElement hubLB = driver.findElement(By.cssSelector(AdsContent.getSlotSelector(hubLBName)));
		checkScriptPresentInSlotScripts(hubLBName, hubLB);
		PageObjectLogging.log("HUB_TOP_LEADERBOARD found", "HUB_TOP_LEADERBOARD found", true);

		WebElement hubGPTLB = hubLB.findElement(By.cssSelector(AdsContent.getSlotSelector(AdsContent.HUB_LB_GPT)));
		PageObjectLogging.log("HUB_TOP_LEADERBOARD_gpt found", "HUB_TOP_LEADERBOARD_gpt found", true);

		if (hubGPTLB.findElements(By.cssSelector("iframe")).size() > 1) {
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
		scrollToSelector(AdsContent.getSlotSelector(AdsContent.ADS_IN_CONTENT_CONTAINER));
		scrollToSelector(AdsContent.getSlotSelector(AdsContent.PREFOOTERS_CONTAINER));
		verifyNoLiftiumAds();
		PageObjectLogging.log(
			"verifyNoLiftiumAdsOnPage",
			"No ads detected",
			true,
			driver
		);
	}

	public void verifyNoAdsOnPage() {
		scrollToSelector(AdsContent.getSlotSelector(AdsContent.ADS_IN_CONTENT_CONTAINER));
		scrollToSelector(AdsContent.getSlotSelector(AdsContent.PREFOOTERS_CONTAINER));
		verifyNoAds();
		PageObjectLogging.log(
			"verifyNoAdsOnPage",
			"No ads detected",
			true,
			driver
		);
	}

	public void verifyNoAdsOnMobilePage() {
		scrollToSelector(AdsContent.getSlotSelector(AdsContent.MOBILE_AD_IN_CONTENT));
		scrollToSelector(AdsContent.getSlotSelector(AdsContent.MOBILE_PREFOOTER));
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
		scriptText = scriptText.replaceAll("\\s", "");

		for (WebElement scriptNode : element.findElements(By.tagName("script"))) {
			String result = scriptNode.getAttribute("innerHTML");
			if (result.replaceAll("\\s", "").contains(scriptText)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkScriptPresentInSlotScripts(String slotName, WebElement slotElement) throws Exception {
		String scriptExpectedResult = AdsContent.ADS_PUSHSLOT_SCRIPT.replace(
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
		Collection<String> slotsSelectors = AdsContent.SLOTS_SELECTORS.values();
		for (String selector : slotsSelectors) {
			if (checkIfElementOnPage(selector)) {
				WebElement element = driver.findElement(By.cssSelector(selector));
				if (
					element.isDisplayed()
						&& element.getSize().getHeight() > 1
						&& element.getSize().getWidth() > 1
					) {
					throw new WebDriverException("Ads found on page in selector: " + selector);
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
		for (WebElement slot : slots) {
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
		if (checkIfElementOnPage(LIFTIUM_IFRAME_SELECTOR)) {
			throw new WebDriverException("Liftium ads found!");
		} else {
			PageObjectLogging.log("LiftiumAdsNotFound", "Liftium ads not found", true);
		}
	}

	private String extractLiftiumTagId(String slotSelector) {
		String liftiumTagId = null;
		WebElement slot = driver.findElement(By.cssSelector(slotSelector));
		if (checkIfElementInElement(LIFTIUM_IFRAME_SELECTOR, slot)) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement currentLiftiumIframe = (WebElement) js.executeScript(
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

	protected void extractGptInfo(String slotSelector) {
		WebElement slot = driver.findElement(By.cssSelector(slotSelector));
		String log = "GPT ad not found in slot: " + slotSelector;
		if (checkIfElementInElement(GPT_DIV_SELECTOR, slot)) {
			log = "GPT ad found in slot: " + slotSelector;
			WebElement gptDiv = slot.findElement(By.cssSelector(GPT_DIV_SELECTOR));
			for (String attribute : GPT_DATA_ATTRIBUTES) {
				log += "; " + attribute + " = " + gptDiv.getAttribute(attribute);
			}
		}
		PageObjectLogging.log("extractGptInfo", log, true, driver);
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

	public void waitForSlotCollapsed(WebElement slot) {
		waitForElementToHaveSize(0, 0, slot);
	}

	private void waitForElementToHaveSize(int width, int height, WebElement element) {
		changeImplicitWait(250, TimeUnit.MILLISECONDS);
		try {
			wait.until(CommonExpectedConditions.elementToHaveSize(element, width, height));
		} finally {
			restoreDeaultImplicitWait();
		}
	}

	public void verifyNoLiftiumAdsInSlots(List<String> slots) {
		for (String slot : slots) {
			WebElement slotElement = driver.findElement(By.id(slot));
			if (checkIfElementInElement(LIFTIUM_IFRAME_SELECTOR, slotElement)) {
				throw new NoSuchElementException("Liftium found in slot " + slot);
			} else {
				PageObjectLogging.log("LiftiumNotFound", "Liftium not found in slot " + slot, true);
			}
		}
	}

	public void waitForIframeLoaded(WebElement iframe) {
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeAsyncScript(
			"var callback = arguments[arguments.length - 1]; " +
				"var iframe = arguments[0];" +
				"if (iframe.contentWindow.document.readyState === 'complete'){ return callback(); } else {" +
				"iframe.contentWindow.addEventListener('load', function () {return callback(); }) " +
				"}",
			iframe
		);
	}

	/**
	 * Test whether the correct GPT ad unit is called
	 *
	 * @param adUnit the ad unit passed to GPT, like wka.wikia/_wikiaglobal//home
	 */
	public void verifyGptIframe(String adUnit, String slotName, String src) {
		String iframeId = "google_ads_iframe_/5441/" + adUnit + "/" + slotName + "_" + src + "_0";
		By cssSelector = By.cssSelector("iframe[id^='" + iframeId + "']");

		waitForElementPresenceByBy(cssSelector);

		String msg = "GPT iframe #" + iframeId + " found in slot " + slotName;
		PageObjectLogging.log("verifyGptIframe", msg, true, driver);

		waitForIframeLoaded(driver.findElement(cssSelector));

		msg = "Received \"load\" event from GPT iframe #" + iframeId + "  in slot " + slotName;
		PageObjectLogging.log("verifyGptIframe", msg, true, driver);
	}

	private String getGptParams(String slotName, String src, String attr) {
		WebElement gptIframeWrap = driver.findElement(By.id(slotName + "_" + src));
		return gptIframeWrap.getAttribute(attr);
	}

	/**
	 * Test whether the correct GPT ad parameters are passed
	 *
	 * @param slotName   Slotname
	 * @param pageParams List of gpt page-level params to test
	 * @param slotParams List of gpt slot-level params to test
	 */
	public void verifyGptParams(String slotName, String src, List<String> pageParams, List<String> slotParams) {
		String dataGptPageParams = getGptParams(slotName, src, "data-gpt-page-params");
		String dataGptSlotParams = getGptParams(slotName, src, "data-gpt-slot-params");

		for (String param : pageParams) {
			Assertion.assertStringContains(param, dataGptPageParams);
		}

		for (String param : slotParams) {
			Assertion.assertStringContains(param, dataGptSlotParams);
		}

		PageObjectLogging.log(
			"verifyGptParams",
			"All page-level and slot-level params present as expected " + dataGptPageParams + ", " + dataGptSlotParams,
			true,
			driver
		);
	}

	/**
	 * Test whether the correct GPT ad parameters are passed
	 *
	 * @param slotName   Slotname
	 * @param lineItemId expected line item id
	 * @param creativeId expected creative id
	 */
	public void verifyGptAdInSlot(String slotName, String src, String lineItemId, String creativeId) {

		String gptIframeWrapId = slotName + "_" + src;
		WebElement gptIframeWrap = driver.findElement(By.id(gptIframeWrapId));

		Assertion.assertEquals(gptIframeWrap.getAttribute("data-gpt-line-item-id"), lineItemId);

		if (creativeId.length() > 0) {
			Assertion.assertEquals(gptIframeWrap.getAttribute("data-gpt-creative-id"), creativeId);
		}

		PageObjectLogging.log(
			"verifyGptAdInSlot",
			"Line item id loaded: " + lineItemId + ", creativeId:" + creativeId,
			true,
			driver
		);
	}

	protected boolean isGptParamPresent(String key, String value) {
		waitForElementByElement(presentMedrec);
		String dataGptPageParams = presentLeaderboardGpt.getAttribute("data-gpt-page-params");
		String gptParamPattern = String.format("\"%s\":\"%s\"", key, value);

		PageObjectLogging.log(
			"GPT parameter search",
			"searching for: " + gptParamPattern + " in<br>" + dataGptPageParams,
			true
		);

		return dataGptPageParams.contains(gptParamPattern);
	}

	/**
	 * Test whether the top=1k parameter is passed (or not passed) to DART
	 *
	 * @param isTop1k should the top=1k parameter be passed to DART
	 */
	public void verifyTop1kParamState(Boolean isTop1k) {
		if (isTop1k) {
			Assertion.assertTrue(isGptParamPresent("top", "1k"), /* error msg: */ "parameter top=1k not found");
			PageObjectLogging.log(
				"verifyTop1kParamState",
				"parameter top=1k found",
				true,
				driver
			);
		} else {
			Assertion.assertFalse(isGptParamPresent("top", "1k"), /* error msg: */ "parameter top=1k found");
			PageObjectLogging.log(
				"verifyTop1kParamState",
				"parameter top=1k not found",
				true,
				driver
			);
		}
	}

	public void checkSpotlights() {
		// Removing comments section as it expands content downwards
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].parentNode.removeChild(arguments[0]);", waitForElementByCss("#WikiaArticleFooter"));

		AdsComparison adsComparison = new AdsComparison();

		scrollToElement(waitForElementByCss("#SPOTLIGHT_FOOTER"));

		for (String spotlightSelector : SPOTLIGHT_SLOTS) {
			WebElement slot = waitForElementByCss(spotlightSelector + " img");
			verifySlotExpanded(slot);

			adsComparison.isAdVisible(slot, spotlightSelector, driver);
		}
	}

	public AdsBaseObject verifySize(String slotName, int slotWidth, int slotHeight) {
		WebElement element = getWebElement(slotName);
		waitForElementToHaveSize(slotWidth, slotHeight, element);
		Dimension size = element.getSize();
		Assertion.assertEquals(size.getWidth(), slotWidth);
		Assertion.assertEquals(size.getHeight(), slotHeight);
		PageObjectLogging.log("verifySize", slotName + " has size " + size, true, driver);
		return this;
	}

	public AdsBaseObject verifyLineItemId(String slotName, String src, int lineItemId) {
		String lineItemParam = getGptParams(slotName, src, GPT_DATA_ATTRIBUTES[0]);
		Assertion.assertStringContains(String.valueOf(lineItemId), lineItemParam);
		PageObjectLogging.log("verifyLineItemId", slotName + " has following line item: " + lineItemParam, true);
		return this;
	}

	public AdsBaseObject verifyAdImage(String slotName, String imageUrl) {
		WebElement element = getWebElement(slotName);
		Assertion.assertTrue(new AdsComparison().compareImageWithScreenshot(imageUrl, element, driver));
		PageObjectLogging.log("verifyAdImage", "Ad looks good", true, driver);
		return this;
	}

	private WebElement getWebElement(String slotName) {
		String slotSelector = AdsContent.getSlotSelector(slotName);
		return driver.findElement(By.cssSelector(slotSelector));
	}
}
