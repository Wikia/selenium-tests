package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsComparison;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsSkinHelper;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AdsBaseObject extends WikiBasePageObject {

  // Constants
  private static final int MIN_MIDDLE_COLOR_PAGE_WIDTH = 1600;
  private static final int PROVIDER_CHAIN_TIMEOUT_SEC = 30;
  private static final int SLOT_TRIGGER_TIMEOUT_SEC = 10;
  protected static final int GLOBAL_NAV_HEIGHT = 60;
  protected static final int WIKIA_DFP_CLIENT_ID = 5441;
  private static final String HOP_AD_TYPE = "AdEngine_adType='collapse';";
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
  private static final String[] PROVIDERS = {
      "DirectGpt",
      "DirectGptMobile",
      "RemnantGpt",
      "RemnantGptMobile"
  };
  private static final String GPT_DIV_SELECTOR = "[data-gpt-creative-size]";
  private static final String MIDDLE_PREFOOTER_CSS_SELECTOR = "#PREFOOTER_MIDDLE_BOXAD";
  private static final String FLOATING_MEDREC_SELECTOR = "div[id*='" + AdsContent.FLOATING_MEDREC + "']";

  private static final String GLOBAL_NAVIGATION_SELECTOR = "#globalNavigation, .site-head";

  private long tStart;

  protected String presentLeaderboardSelector = "div[id*='TOP_LEADERBOARD']";

  @FindBy(css = "div[id*='TOP_LEADERBOARD']")
  protected WebElement presentLeaderboard;
  @FindBy(css = "div[id*='TOP_RIGHT_BOXAD']")
  private WebElement presentMedrec;
  @FindBy(css = FLOATING_MEDREC_SELECTOR)
  private WebElement presentFloatingMedrec;
  @FindBy(css = MIDDLE_PREFOOTER_CSS_SELECTOR)
  private WebElement middlePrefooter;
  @FindBy(css = "#WikiaFooter")
  private WebElement wikiaFooter;
  @FindBy(css = ".mobile-in-content")
  private WebElement mobileInContent;
  @FindBy(css = ".mobile-prefooter")
  private WebElement mobilePrefooter;
  @FindBy(css = ".mobile-bottom-leaderboard")
  private WebElement mobileBottomLeaderboard;

  public AdsBaseObject(WebDriver driver) {
    super();
  }

  public AdsBaseObject(WebDriver driver, String page) {
    super();
    getUrl(page, true);
  }

  public AdsBaseObject(WebDriver driver, String testedPage, Dimension resolution) {
    super();
    driver.manage().window().setSize(resolution);
    getUrl(testedPage, true);
  }

  public AdsBaseObject(WebDriver driver, Dimension resolution) {
    super();
    driver.manage().window().setSize(resolution);
  }

  public void timerStart() {
    tStart = System.currentTimeMillis();
  }

  public String getNumberOfSecondsFromStart() {
    long tEnd = System.currentTimeMillis();
    long tDelta = tEnd - tStart;
    double elapsedSeconds = tDelta / 1000.0;
    return String.valueOf(elapsedSeconds);
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
      if (checkScriptPresentInElement(iframeHtml, adDriverForcedSuccessFormatted)) {
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

  public void verifyMedrec() {
    verifyAdVisibleInSlot("div[id*='TOP_RIGHT_BOXAD']", presentMedrec);
  }

  public void verifyFloatingMedrec() {
    verifyAdVisibleInSlot(FLOATING_MEDREC_SELECTOR, presentMedrec);
  }

  public void verifyTopLeaderboard() {
    if (!checkIfSlotExpanded(presentLeaderboard) && isElementOnPage(
        By.cssSelector("#jpsuperheader"))) {
      PageObjectLogging.logWarning("Special ad", "Ad in #jpsuperheader detected");
      return;
    }
    verifyAdVisibleInSlot(presentLeaderboardSelector, presentLeaderboard);
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

  /**
   * Test whether the correct GPT ad parameters are passed
   *
   * @param slotName   Slotname
   * @param pageParams List of gpt page-level params to test
   * @param slotParams List of gpt slot-level params to test
   */
  public void verifyGptParams(String slotName, List<String> pageParams,
                              List<String> slotParams) {
    String dataGptPageParams = getGptParams(slotName, "data-gpt-page-params");
    String dataGptSlotParams = getGptParams(slotName, "data-gpt-slot-params");

    for (String param : pageParams) {
      Assertion.assertStringContains(dataGptPageParams, param);
    }

    for (String param : slotParams) {
      Assertion.assertStringContains(dataGptSlotParams, param);
    }

    PageObjectLogging.log(
        "verifyGptParams",
        "All page-level and slot-level params present as expected " + dataGptPageParams + ", "
        + dataGptSlotParams,
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
  public void verifyGptAdInSlot(String slotName, String lineItemId, String creativeId) {

    Assertion.assertEquals(getGptParams(slotName, "data-gpt-line-item-id"), lineItemId);

    if (creativeId.length() > 0) {
      Assertion.assertEquals(getGptParams(slotName, "data-gpt-creative-id"), creativeId);
    }

    PageObjectLogging.log(
        "verifyGptAdInSlot",
        "Line item id loaded: " + lineItemId + ", creativeId:" + creativeId,
        true,
        driver
    );
  }

  public void verifySpotlights() {
    AdsComparison adsComparison = new AdsComparison();

    for (String spotlightSelector : SPOTLIGHT_SLOTS) {
      WebElement slot = wait.forElementVisible(By.cssSelector(spotlightSelector + " img"));
      verifySlotExpanded(slot);

      Assertion.assertTrue(adsComparison.isAdVisible(slot, spotlightSelector, driver));
    }
  }

  public AdsBaseObject verifyIframeSize(String slotName,
                                        String src,
                                        int slotWidth,
                                        int slotHeight) {
    waitForElementToHaveSize(slotWidth, slotHeight, getIframe(slotName, src));
    return this;
  }

  public AdsBaseObject verifyLineItemId(String slotName, int lineItemId) {
    String lineItemParam = getGptParams(slotName, GPT_DATA_ATTRIBUTES[0]);
    Assertion.assertStringContains(lineItemParam, String.valueOf(lineItemId));
    PageObjectLogging
        .log("verifyLineItemId", slotName + " has following line item: " + lineItemParam, true);
    return this;
  }

  public AdsBaseObject verifyProvidersChain(String slotName, String providers) {
    PageObjectLogging.log("SlotName", slotName, true);
    waitForProvidersChain(slotName, providers, PROVIDER_CHAIN_TIMEOUT_SEC);
    return this;
  }

  /**
   * Test whether the correct GPT ad unit is called
   *
   * @param adUnit   the ad unit passed to GPT, like wka.wikia/_wikiaglobal//home
   * @param slotName the name of the slot an ad is going to be inserted into
   * @param src      the source of an ad, for example gpt or remnant
   */
  public void verifyGptIframe(String adUnit, String slotName, String src) {
    verifyGptIframe(WIKIA_DFP_CLIENT_ID, adUnit, slotName, src);
  }

  /**
   * Builds GPT iframe id
   *
   * @param dfpClientId in most cases it's Wikia id but we have other partners like Evolve or
   *                    Turtle
   * @param adUnit      the ad unit passed to GPT, like wka.wikia/_wikiaglobal//home
   * @param slotName    the name of the slot an ad is going to be inserted into
   * @param src         the source of an ad, for example gpt, remnant or empty
   */
  public String buildGptIframeId(int dfpClientId, String adUnit, String slotName, String... src) {
    return Joiner.on("/").skipNulls().join(
        "google_ads_iframe_",
        String.valueOf(dfpClientId),
        adUnit,
        src.length > 0 ? src[0] : null,
        slotName + "_0"
    );
  }

  /**
   * Test whether the correct GPT ad unit is called
   *
   * @param dfpClientId in most cases it's Wikia id but we have other partners like Evolve or
   *                    Turtle
   * @param adUnit      the ad unit passed to GPT, like wka.wikia/_wikiaglobal//home
   * @param slotName    the name of the slot an ad is going to be inserted into
   * @param src         the source of an ad, for example gpt, remnant or empty
   */
  public void verifyGptIframe(int dfpClientId, String adUnit, String slotName, String... src) {
    String iframeId = buildGptIframeId(dfpClientId, adUnit, slotName, src);
    By cssSelector = By.cssSelector("iframe[id^='" + iframeId + "']");

    wait.forElementPresent(cssSelector);

    String msg = "GPT iframe #" + iframeId + " found in slot " + slotName;
    PageObjectLogging.log("verifyGptIframe", msg, true, driver);

    waitForIframeLoaded(driver.findElement(cssSelector));

    msg = "Received \"load\" event from GPT iframe #" + iframeId + "  in slot " + slotName;
    PageObjectLogging.log("verifyGptIframe", msg, true, driver);
  }

  public void waitForSlotCollapsed(WebElement slot) {
    waitForElementToHaveSize(0, 0, slot);
  }

  public void waitForSlotExpanded(final WebElement slot) {
    waitFor.until(new ExpectedCondition<Object>() {
      @Override
      public Object apply(WebDriver webDriver) {
        return checkIfSlotExpanded(slot);
      }
    });
  }

  public String getGptParams(String slotName, String attr) {
    WebElement
        adsDiv =
        driver.findElement(
            By.cssSelector("#"+slotName+" [" + attr + "]"));
    return adsDiv.getAttribute(attr);
  }

  public String getGptPageParams(String slotName) {
    return getGptParams(slotName, "data-gpt-page-params");
  }

  public AdsBaseObject waitForPageLoaded() {
    jsActions.waitForJavaScriptTruthy("document.readyState === 'complete'");
    return this;
  }

  public AdsBaseObject waitForPageLoadedWithGpt() {
    timerStart();
    waitForPageLoaded();

    String waitForGPTJS = "typeof window.googletag === 'object'";
    jsActions.waitForJavaScriptTruthy(waitForGPTJS);
    PageObjectLogging.log("GPT Loaded", "after " +
                                        String.valueOf(getNumberOfSecondsFromStart()) + " s", true);
    return this;
  }

  /**
   * Mercury is a single page application (SPA) and if you want to test navigating between different
   * pages in the application you might want to use this method after clicking anything which is not
   * on the first page.
   *
   * First page in Mercury loads just as a regular web page but next articles in Mercury just change
   * part of loaded DOM. We tried few things but waiting for the page title to change was so far the
   * best way to make sure we can move on with our tests.
   */
  public void waitTitleChangesTo(String desiredArticleTitle) {
    driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
    try {
      waitFor.until(
          ExpectedConditions.titleContains(desiredArticleTitle)
      );
    } finally {
      restoreDefaultImplicitWait();
    }
  }

  public void verifySkin(String adSkinLeftPath,
                         String adSkinRightPath,
                         String backgroundColor,
                         String middleColor) {
    AdsSkinHelper skinHelper = new AdsSkinHelper(adSkinLeftPath, adSkinRightPath, driver);
    Assertion.assertTrue(skinHelper.skinPresent());
    PageObjectLogging.log("SKIN", "SKIN presents on the page", true);

    if (!Strings.isNullOrEmpty(backgroundColor)) {
      Assertion.assertEquals(skinHelper.getBackgroundColor(), backgroundColor);
      PageObjectLogging.log("SKIN", "SKIN has correct background color", true);
    }

    if (!Strings.isNullOrEmpty(middleColor) &&
        getWindowSize().getWidth() > MIN_MIDDLE_COLOR_PAGE_WIDTH) {
      Assertion.assertEquals(skinHelper.getMiddleColor(), middleColor);
      PageObjectLogging.log("SKIN", "SKIN has correct middle color", true);
    }
  }

  /**
   * Check if AdEngine loaded the ad web elements inside slot.
   */
  public boolean checkSlotOnPageLoaded(String slotName) {
    WebElement slot;
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      if (slotName.equals(AdsContent.FLOATING_MEDREC)) {
        triggerAdSlot(AdsContent.FLOATING_MEDREC);
      }

      String slotSelector = AdsContent.getSlotSelector(slotName);
      try {
        slot = driver.findElement(By.cssSelector(slotSelector));
      } catch (NoSuchElementException elementNotFound) {
        return false;
      }

      List<WebElement> adWebElements = slot.findElements(By.cssSelector("div"));
      return adWebElements.size() > 1;
    } finally {
      restoreDefaultImplicitWait();
    }
  }

  private Optional<WebElement> getLastGptDiv(String slotSelector) {
    WebElement slot = driver.findElement(By.cssSelector(slotSelector));

    if (isElementInContext(GPT_DIV_SELECTOR, slot)) {
      List<WebElement> gptDivs = slot.findElements(By.cssSelector(GPT_DIV_SELECTOR));
      return Optional.of(gptDivs.get(gptDivs.size() - 1));
    }

    return Optional.absent();
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

  public void waitForElementToHaveSize(int width, int height, WebElement element) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      waitFor.until(CommonExpectedConditions.elementToHaveSize(element, width, height));
    } finally {
      restoreDefaultImplicitWait();
    }
  }

  private void waitForIframeLoaded(WebElement iframe) {
    PageObjectLogging.log("waitForIframeLoaded", "Switching to adslot iframe", true);
    driver.switchTo().frame(iframe);
    waitForPageLoaded();
    PageObjectLogging.log("waitForIframeLoaded", "Switching back to the page", true);
    driver.switchTo().defaultContent();
  }

  private void waitForProvidersChain(final String slotName,
                                     final String expectedProviders,
                                     int timeoutSec) {
    new WebDriverWait(driver, timeoutSec).until(
        new ExpectedCondition<Boolean>() {
          @Override
          public Boolean apply(WebDriver webDriver) {
            return expectedProviders.equals(Joiner.on("; ").join(getProvidersChain(slotName)));
          }

          @Override
          public String toString() {
            extractGptInfo(AdsContent.getSlotSelector(slotName));
            return String.format("Expected: [%s], Actual: [%s]", expectedProviders,
                                 Joiner.on("; ").join(getProvidersChain(slotName)));
          }
        }
    );
  }

  private List<String> getProvidersChain(String slotName) {
    List<String> providersChain = new ArrayList<>();
    String slotSelector = AdsContent.getSlotSelector(slotName);
    for (WebElement providerSlot : driver
        .findElements(By.cssSelector(slotSelector + " > div"))) {
      String providerSlotName = providerSlot.getAttribute("id").split("_")[0];
      String provider = "";
      for (String providerName : PROVIDERS) {
        String providerSearch = "/" + providerName + "/";
        if (providerSlotName.contains(providerSearch)) {
          provider = providerName;
          break;
        }
      }
      providersChain.add(provider.isEmpty() ? providerSlotName : provider);
    }
    return providersChain;
  }

  private WebElement getIframe(String slotName, String src) {
    return driver.findElement(By.cssSelector("iframe[id*='" + src + "/" + slotName + "']"));
  }

  public void verifyNoAd(final String slotSelector) {
    if (isElementOnPage(By.cssSelector(slotSelector))) {
      WebElement element = driver.findElement(By.cssSelector(slotSelector));
      if (
          element.isDisplayed()
          && element.getSize().getHeight() > 1
          && element.getSize().getWidth() > 1
          ) {
        throw new WebDriverException("Ads found on page in selector: " + slotSelector);
      } else {
        PageObjectLogging.log(
            "AdsFoundButNotVisible",
            "Ads found on page with selector: "
            + slotSelector
            + " but is smaller then 1x1 or hidden",
            true
        );
      }
    } else {
      PageObjectLogging.log(
          "AdNotFound",
          "Ad with selector: "
          + slotSelector
          + " not found on page",
          true
      );
    }
  }

  public AdsBaseObject triggerAdSlot(final String slotName) {
    final String adSlotSelector = AdsContent.getSlotSelector(slotName);
    final String javaScriptTrigger = AdsContent.getSlotTrigger(slotName);

    if (StringUtils.isEmpty(javaScriptTrigger)) {
      return this;
    }

    try {
      new WebDriverWait(driver, SLOT_TRIGGER_TIMEOUT_SEC).until(new ExpectedCondition<Object>() {
        @Override
        public Object apply(WebDriver webDriver) {
          jsActions.execute(javaScriptTrigger);
          return driver.findElements(By.cssSelector(adSlotSelector)).size() > 0;
        }
      });
    } catch (org.openqa.selenium.TimeoutException e) {
      PageObjectLogging.logError(adSlotSelector + " slot", e);
    }

    return this;
  }

  public void verifyExpandedAdVisibleInSlot(String slotSelector, WebElement slot) {
    waitForSlotExpanded(slot);

    boolean adVisible = new AdsComparison().isAdVisible(slot, slotSelector, driver);

    extractGptInfo(slotSelector);

    if (!adVisible) {
      throw new WebDriverException("Ad is not present in " + slotSelector);
    }

    PageObjectLogging.log("ScreenshotsComparison", "Ad is present in " + slotSelector, true);
  }

  public long getLineItemId(String slotName) {
    JavascriptExecutor js = driver;
    try {
      return (long) js.executeScript(
          "var slots = googletag.pubads().getSlots(); for (var i = 0; i < slots.length; i++) { " +
          "if (slots[i].getTargeting('pos').indexOf('" + slotName + "') !== -1) { " +
          "return slots[i].getResponseInformation().lineItemId;" +
          "} }"
      );
    } catch (WebDriverException e) {
      PageObjectLogging.log("Get " + slotName + " line item", e, false);
      return 0;
    }
  }

  protected void verifyAdVisibleInSlot(String slotSelector, WebElement slot) {

    if (!checkIfSlotExpanded(slot)) {

      Optional<WebElement> lastGptDiv = getLastGptDiv(slotSelector);
      if (lastGptDiv.isPresent() &&
          checkIfGptSlotHasCreativeContent(lastGptDiv.get(), HOP_AD_TYPE)) {
        PageObjectLogging.log("verifyAdVisibleInSlot", "Slot has " + HOP_AD_TYPE, true);
        return;
      }

      throw new WebDriverException(slot.getAttribute("id") + " is collapsed");
    }

    boolean adVisible = new AdsComparison().isAdVisible(slot, slotSelector, driver);

    extractGptInfo(slotSelector);

    if (!adVisible) {
      throw new WebDriverException("Ad is not present in " + slotSelector);
    }

    PageObjectLogging.log("ScreenshotsComparison", "Ad is present in " + slotSelector, true);
  }

  private boolean checkIfGptSlotHasCreativeContent(WebElement element, String hopAdType) {
    String slotName = element.getAttribute("id").replace("wikia_gpt", "google_ads_iframe_");
    String iframeSelector = "iframe[id*='" + slotName + "_0']";
    String adTypeScriptXpath = String.format("//script[contains(text(), \"%s\")]", hopAdType);
    WebElement iframe = element.findElement(By.cssSelector(iframeSelector));
    driver.switchTo().frame(iframe);
    List<WebElement> adTypeScripts = driver.findElements(By.xpath(adTypeScriptXpath));
    driver.switchTo().defaultContent();
    return !adTypeScripts.isEmpty();
  }

  private void verifySlotExpanded(WebElement element) {
    if (!checkIfSlotExpanded(element)) {
      throw new WebDriverException(element.getAttribute("id") + " is collapsed");
    }
  }

  private void verifyNoAds() {
    Collection<String> slotsSelectors = AdsContent.getAllSlotsSelectors();
    for (String selector : slotsSelectors) {
      verifyNoAd(selector);
    }
  }

  protected void extractGptInfo(String slotSelector) {
    Optional<WebElement> lastGptDiv = getLastGptDiv(slotSelector);

    String log = "GPT ad not found in slot: " + slotSelector;

    if (lastGptDiv.isPresent()) {
      log = "GPT ad found in slot: " + lastGptDiv.get().getAttribute("id");
      for (String attribute : GPT_DATA_ATTRIBUTES) {
        log += "; " + attribute + " = " + lastGptDiv.get().getAttribute(attribute);
      }
    }

    PageObjectLogging.log("extractGptInfo", log, true, driver);
  }

  protected boolean checkScriptPresentInElement(WebElement element, String scriptText) {
    String formattedScriptText = scriptText.replaceAll("\\s", "");

    for (WebElement scriptNode : element.findElements(By.tagName("script"))) {
      String result = scriptNode.getAttribute("innerHTML");
      if (result.replaceAll("\\s", "").contains(formattedScriptText)) {
        return true;
      }
    }
    return false;
  }

  public void clickOnArticleLink(String linkName) {
    WebElement link = driver.findElement(
        By.cssSelector("a[title='" + linkName + "']"));
    link.click();

    waitTitleChangesTo(linkName);
  }

  protected void hideElementIfPresent(String cssSelector) {
    if (isElementOnPage(By.cssSelector(cssSelector))) {
      PageObjectLogging.log("Hiding element", cssSelector, true);
      WebElement element = driver.findElement(By.cssSelector(cssSelector));
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("$(arguments[0]).css('display', 'none')", element);
      waitForElementNotVisibleByElement(element);
    }
  }

  public boolean isMiddlePrefooterOnPage() {
    return isElementOnPage(middlePrefooter);
  }

  public void verifyMiddlePrefooterAdPresent() {
    verifyAdVisibleInSlot(MIDDLE_PREFOOTER_CSS_SELECTOR, middlePrefooter);
  }

  public void triggerComments() {
    scrollToFooter();
    jsActions.waitForJavaScriptTruthy("window.ArticleComments.initCompleted");
    scrollToFooter();
  }

  public void scrollToPosition(By element) {
    jsActions.scrollToElement(driver.findElement(element));
    PageObjectLogging.log("scrollToSelector", "Scroll to the web selector " + element.toString(), true);
  }

  public void simulateScrollingToElement(By selector, int jumpSize, Duration duration, By elementToCheck) {
    JavascriptActions jsActions = new JavascriptActions(driver);

    final WebElement element = driver.findElement(selector);
    final boolean scrollingToBottom = element.getLocation().getY() > driver.manage().window().getPosition().getY();
    int jumpSizeWithDirection = scrollingToBottom ? jumpSize : -jumpSize;

    while (!CommonExpectedConditions.elementInViewPort(driver.findElement(elementToCheck)).apply(driver)) {
      jsActions.scrollBy(0, jumpSizeWithDirection);
      wait.forX(duration);
    }

    PageObjectLogging.log("scrollToSelector", "Scroll to the web selector " + selector.toString(), true);
  }

  public void simulateScrollingToElement(By selector, By elementToCheck) {
    simulateScrollingToElement(selector, 300, Duration.ofSeconds(1), elementToCheck);
  }

  public void scrollToPosition(String selector) {
    scrollToPosition(By.cssSelector(selector));
  }

  public void scrollToSlot(String slotName) {
    if (slotName.equals(AdsContent.BOTTOM_LB)) {
      triggerComments();
    } else if (slotName.equals(AdsContent.MOBILE_BOTTOM_LB)) {
      scrollToFooter();
    }

    scrollToPosition("#" + slotName);
  }

  public void fixScrollPositionByNavbar() {
    jsActions.scrollBy(0, -1 * driver.findElement(By.cssSelector(GLOBAL_NAVIGATION_SELECTOR)).getSize().getHeight());
  }

  public boolean isMobileInContentAdDisplayed() {
    try{
      wait.forElementVisible(mobileInContent);
      return true;
    } catch (TimeoutException | NoSuchElementException ex) {
      PageObjectLogging.log("Mobile in content ad is not displayed", ex, true);
      return false;
    }
  }

  public boolean isMobilePrefooterAdDisplayed() {
    try{
      wait.forElementVisible(mobilePrefooter);
      return true;
    } catch (TimeoutException | NoSuchElementException ex) {
      PageObjectLogging.log("Mobile prefooter ad is not displayed", ex, true);
      return false;
    }
  }

  public boolean isMobileBottomLeaderboardAdDisplayed() {
    try{
      wait.forElementVisible(mobileBottomLeaderboard);
      return true;
    } catch (TimeoutException | NoSuchElementException ex) {
      PageObjectLogging.log("Mobile bottom leaderboard ad is not displayed", ex, true);
      return false;
    }
  }

  public boolean areRubiconDfpParamsPresent(String currentGptSlotParams, String patternParamTier) {
    try {
      return currentGptSlotParams.matches(patternParamTier);
    } catch (AssertionError ass) {
      PageObjectLogging.log(currentGptSlotParams + " does not contains all expected dfp params", ass, true);
      return false;
    }
  }

  public void waitForAdInSlot(String creativeId, String slotName) {
    wait.forElementPresent(By.cssSelector("#" + slotName + " div[data-gpt-creative-id='" + creativeId + "']"));
  }

  public void verifyAdChainForSlot(String[] creativeIdChain, String slotName, AdsBaseObject page) {
    for (String creativeId : creativeIdChain) {
      page.waitForAdInSlot(creativeId, slotName);
    }
  }

  /**
   * Iframe finder for oasis slots
   * WARNING: it's able to find only first call result!
   */
  public By findFirstIframeWithAd(String slotName) {
    return By.cssSelector("#" + slotName + " iframe[title='3rd party ad content']");
  }
}
