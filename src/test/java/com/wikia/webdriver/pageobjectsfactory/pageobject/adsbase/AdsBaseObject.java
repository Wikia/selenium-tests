package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import static com.wikia.webdriver.pageobjectsfactory.componentobject.TrackingOptInPage.setGeoCookie;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsComparison;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsSkinHelper;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdsBaseObject extends WikiBasePageObject {

  public static final String PAGE_TYPE_ARTICLE = "article";
  public static final String PAGE_TYPE_SPECIAL = "special";
  public static final String PAGE_TYPE_FILE = "file";
  public static final String PAGE_TYPE_CATEGORY = "category";
  public static final String ADS_IFRAME = "google_ads_iframe_";
  // Constants
  private static final int WIKIA_DFP_CLIENT_ID = 5441;
  private static final String HOP_AD_TYPE = "AdEngine_adType='collapse';";
  private static final String[] GPT_DATA_ATTRIBUTES = {"data-gpt-line-item-id",
                                                       "data-gpt-creative-id",
                                                       "data-gpt-creative-size",};
  private static final String[] SPOTLIGHT_SLOTS = {"#SPOTLIGHT_FOOTER_1", "#SPOTLIGHT_FOOTER_2",
                                                   "#SPOTLIGHT_FOOTER_3",};
  private static final String[] PROVIDERS = {"DirectGpt", "DirectGptMobile", "RemnantGpt",
                                             "RemnantGptMobile"};
  private static final String GPT_DIV_SELECTOR = "[data-gpt-creative-size]";
  private static final String FLOATING_MEDREC_SELECTOR = "div[id*='" + AdsContent.FLOATING_MEDREC
                                                         + "']";
  private static final String GLOBAL_NAVIGATION_SELECTOR = "#globalNavigation,.site-head-container";
  private static final String MIX_CONTENT_FOOTER_ROW_SELECTOR = ".mcf-row";
  private String pageType = PAGE_TYPE_ARTICLE;
  private String environment = AdsContent.ENV_DESKTOP;
  @FindBy(css = MIX_CONTENT_FOOTER_ROW_SELECTOR)
  private WebElement mixContentFooterItem;
  private long tStart;
  @FindBy(css = "div[id*='hivi_leaderboard']")
  private WebElement presentLeaderboard;
  @FindBy(css = "div[id*='top_boxad']")
  private WebElement presentMedrec;
  @FindBy(css = FLOATING_MEDREC_SELECTOR)
  private WebElement presentFloatingMedrec;
  @FindBy(css = "#WikiaFooter")
  private WebElement wikiaFooter;
  @FindBy(css = "#incontent_boxad_1")
  private WebElement mobileInContent;
  @FindBy(css = ".mobile-prefooter")
  private WebElement mobilePrefooter;
  @FindBy(css = ".mobile-bottom-leaderboard")
  private WebElement mobileBottomLeaderboard;
  private static final String ARTICLE_FOOTER = ".article-footer";
  private static final String
      VAL_MORGAN_TLB_MEGA_AD_UNIT
      = "vm1b.LB/top_leaderboard/desktop/oasis-article-ic/_top1k_wiki-life";
  private static final String
      VAL_MORGAN_HIVI_TLB_MEGA_AD_UNIT
      = "vm1b.LB/hivi_leaderboard/desktop/oasis-article-ic/_top1k_wiki-life";
  private static final String
      VAL_MORGAN_TB_MEGA_AD_UNIT
      = "vm1b.MR/top_boxad/desktop/oasis-article-ic/_top1k_wiki-life";
  private static final String
      VAL_MORGAN_BLB_MEGA_AD_UNIT
      = "vm1b.PF/bottom_leaderboard/desktop/oasis-article-ic/_top1k_wiki-life";

  private static final String
      VAL_MORGAN_TLB_MEGA_AD_UNIT_MERCURY
      = "vm1b.LB/top_leaderboard/smartphone/mercury-article/_top1k_wiki-life";

  private static final String
      VAL_MORGAN_BLB_MEGA_AD_UNIT_MERCURY
      = "vm1b.PF/bottom_leaderboard/smartphone/mercury-article/_top1k_wiki-life";

  public AdsBaseObject() {
    super();
  }

  public AdsBaseObject(String page) {
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

  public void setPageType(String type) {
    pageType = type;
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

  public void verifyNoAdsOnPage(Boolean isMobile) {
    verifyNoAds(isMobile);
    Log.log("verifyNoAdsOnPage", "No ads detected", true, driver);
  }

  public void verifyIframeSize(String slotName, int slotWidth, int slotHeight) {
    waitForElementToHaveSize(slotWidth, slotHeight, getIframe(slotName));
  }

  public AdsBaseObject verifyLineItemId(String slotName, String lineItemId) {
    String lineItemParam = getSlotAttribute(slotName, GPT_DATA_ATTRIBUTES[0]);
    Assertion.assertStringContains(lineItemParam, lineItemId);
    Log.log("verifyLineItemId", slotName + " has following line item: " + lineItemParam, true);
    return this;
  }

  /**
   * Overloading for backwards compatibility
   */
  public void verifyLineItemId(String slotName, int lineItemId) {
    verifyLineItemId(slotName, Integer.toString(lineItemId));
  }

  public void verifySlotAttribute(String slotName, String attribute, String value) {
    String slotParam = getSlotAttribute(slotName, attribute);
    Assertion.assertStringContains(slotParam, value);
    Log.log("verifySlotAttribute",
            String.format("%s has following [%s] attribute: %s", slotName, attribute, slotParam),
            true
    );
  }

  /**
   * Test whether the correct GPT ad unit is called
   *
   * @param adUnit   the ad unit passed to GPT, like wka.wikia/_top1k_wiki//home
   * @param slotName the name of the slot an ad is going to be inserted into
   * @param src      the source of an ad, for example gpt or remnant
   */
  public void verifyGptIframe(String adUnit, String slotName, String src) {
    verifyGptIframe(WIKIA_DFP_CLIENT_ID, adUnit, slotName, src);
  }

  /**
   * Test whether the correct GPT MEGA ad unit is called
   *
   * @param adUnit   the ad unit passed to GPT, like wka.wikia/_top1k_wiki//home
   * @param slotName the name of the slot an ad is going to be inserted into
   */
  public void verifyGptMEGAIframe(String adUnit, String slotName) {
    verifyGptMEGAIframe(WIKIA_DFP_CLIENT_ID, adUnit, slotName);
  }

  /**
   * Builds GPT iframe id
   *
   * @param dfpClientId in most cases it's Wikia id
   * @param adUnit      the ad unit passed to GPT, like wka.wikia/_top1k_wiki//home
   * @param slotName    the name of the slot an ad is going to be inserted into
   * @param src         the source of an ad, for example gpt, remnant or empty
   */
  public String buildGptIframeId(int dfpClientId, String adUnit, String slotName, String... src) {
    return Joiner.on("/").skipNulls().join(ADS_IFRAME,
                                           String.valueOf(dfpClientId),
                                           adUnit,
                                           src.length > 0 ? src[0] : null,
                                           slotName + "_0"
    );
  }

  public String buildMEGAGptIframeId(int dfpClientId, String adUnit) {
    return Joiner.on("/").skipNulls().join(ADS_IFRAME, String.valueOf(dfpClientId), adUnit + "_0");
  }

  /**
   * Test whether the correct GPT ad unit is called
   *
   * @param dfpClientId in most cases it's Wikia id
   * @param adUnit      the ad unit passed to GPT, like wka.wikia/_top1k_wiki//home
   * @param slotName    the name of the slot an ad is going to be inserted into
   * @param src         the source of an ad, for example gpt, remnant or empty
   */
  public void verifyGptIframe(int dfpClientId, String adUnit, String slotName, String... src) {
    verifyIframe(slotName, buildGptIframeId(dfpClientId, adUnit, slotName, src));
  }

  /**
   * Test whether the correct GPT MEGA ad unit is called
   *
   * @param dfpClientId in most cases it's Wikia id
   * @param adUnit      the ad unit passed to GPT, like wka.wikia/_top1k_wiki//home
   * @param slotName    the name of the slot an ad is going to be inserted into
   */
  public void verifyGptMEGAIframe(int dfpClientId, String adUnit, String slotName) {
    verifyIframe(slotName, buildMEGAGptIframeId(dfpClientId, adUnit));
  }

  private void verifyIframe(String slotName, String iframeId) {
    By cssSelector = By.cssSelector("iframe[id*='" + iframeId + "']");

    wait.forElementPresent(cssSelector);

    String msg = "GPT iframe #" + iframeId + " found in slot " + slotName;
    Log.log("verifyGptIframe", msg, true, driver);

    waitForIframeLoaded(driver.findElement(cssSelector));

    msg = "Received \"load\" event from GPT iframe #" + iframeId + "  in slot " + slotName;
    Log.log("verifyGptIframe", msg, true, driver);
  }

  public void waitForSlotCollapsed(WebElement slot) {
    waitForElementToHaveSize(0, 0, slot);
  }

  public void waitForSlotExpanded(final String slotSelector) {
    waitForSlotExpanded(By.cssSelector(slotSelector));
  }

  public void waitForSlotExpanded(final By slotSelector) {
    WebElement slot = wait.forElementPresent(slotSelector);
    waitForSlotExpanded(slot);
  }

  public void waitForSlotExpanded(final WebElement slot) {
    waitFor.until(new ExpectedCondition<Object>() {
      @Override
      public Object apply(WebDriver webDriver) {
        return checkIfSlotExpanded(slot);
      }
    });
  }

  private String getSlotAttribute(String slotName, String attr) {
    try {
      String slotWithAttribute = "#" + slotName + " [" + attr + "]" + "," + "#" + slotName + "["
                                 + attr + "]";
      WebElement adsDiv = driver.findElement(By.cssSelector(slotWithAttribute));
      return adsDiv.getAttribute(attr);
    } catch (NoSuchElementException elementNotFound) {
      Log.logError(String.format("Slot %s with attribute [%s] not found", slotName, attr),
                   elementNotFound
      );
      return null;
    }
  }

  private String getSlotAttribute(List<String> slots, String attr) {
    for (String slotName : slots) {
      String slotWithAttribute = getSlotAttribute(slotName, attr);
      if (slotWithAttribute != null) {
        return slotWithAttribute;
      }
    }
    return null;
  }

  public String getGptPageParams(String slotName) {
    return getSlotAttribute(slotName, "data-gpt-page-params");
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
    Log.log("GPT Loaded", "after " + String.valueOf(getNumberOfSecondsFromStart()) + " s", true);
    return this;
  }

  public boolean hasTopBoxad() {
    try {
      return driver.findElement(By.cssSelector(AdsContent.getSlotSelector(AdsContent.TOP_BOXAD)))
             != null;
    } catch (NoSuchElementException e) {
      Log.log("Slot top_boxad not found on the page", e, true);
      return false;
    }
  }

  /**
   * Check if AdEngine loaded the ad web elements inside slot.
   */
  public boolean checkSlotOnPageLoaded(String slotName) {
    WebElement slot;

    changeImplicitWait(IMPLICIT_SHORT, TimeUnit.MILLISECONDS);

    try {
      String slotSelector = AdsContent.getSlotSelector(slotName);
      triggerAdSlot(slotName);

      try {
        slot = driver.findElement(By.cssSelector(slotSelector));
      } catch (NoSuchElementException elementNotFound) {
        Log.logError(String.format("Slot %s not found on the page", slotName), elementNotFound);

        return false;
      }

      List<WebElement> adWebElements = slot.findElements(By.cssSelector("iframe"));

      Log.log("Slot found",
              String.format("%s found on the page with selector: %s", slotName, slotSelector),
              true
      );

      return adWebElements.size() >= 1;
    } finally {
      restoreDefaultImplicitWait();
    }
  }

  /**
   * Check if AdEngine loaded the ad web elements inside slot with mobile state
   */
  public boolean checkSlotOnPageLoaded(String slotName, Boolean isMobile) {
    WebElement slot;

    changeImplicitWait(IMPLICIT_SHORT, TimeUnit.MILLISECONDS);

    try {
      String slotSelector = AdsContent.getSlotSelector(slotName, isMobile);
      triggerAdSlotWithMobileState(slotName, isMobile);

      try {
        slot = driver.findElement(By.cssSelector(slotSelector));
      } catch (NoSuchElementException elementNotFound) {
        Log.logError(String.format("Slot %s not found on the page", slotName), elementNotFound);

        return false;
      }

      List<WebElement> adWebElements = slot.findElements(By.cssSelector("iframe"));

      Log.log("Slot found",
              String.format("%s found on the page with selector: %s", slotName, slotSelector),
              true
      );

      return adWebElements.size() >= 1;
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
    WebElement iframeWithAd = slot.findElement(By.cssSelector("div > iframe:not([id*='hidden'])"));
    driver.switchTo().frame(iframeWithAd);
    String imageAd = driver.findElement(By.cssSelector("img")).getAttribute("src");
    driver.switchTo().defaultContent();
    return imageAd;
  }

  protected boolean checkIfSlotExpanded(WebElement slot) {
    return slot.getSize().getHeight() > 1 && slot.getSize().getWidth() > 1;
  }

  public void waitForElementToHaveSize(int width, int height, WebElement element) {
    changeImplicitWait(IMPLICIT_SHORT, TimeUnit.MILLISECONDS);
    try {
      waitFor.until(CommonExpectedConditions.elementToHaveSize(element, width, height));
    } finally {
      restoreDefaultImplicitWait();
    }
  }

  private void waitForIframeLoaded(WebElement iframe) {
    Log.log("waitForIframeLoaded", "Switching to adslot iframe", true);
    driver.switchTo().frame(iframe);
    waitForPageLoaded();
    Log.log("waitForIframeLoaded", "Switching back to the page", true);
    driver.switchTo().defaultContent();
  }

  private WebElement getIframe(String slotName) {
    return driver.findElement(By.cssSelector("#" + slotName + " iframe"));
  }

  public void verifyNoAd(final String slotName, final String slotSelector, boolean isMobile) {
    Log.log("verifyNoAd", "Triggering " + slotName, true, driver);
    triggerAdSlotWithMobileState(slotName, isMobile);
    verifyNoAdWithoutTrigger(slotSelector);
  }

  public void verifyNoAdWithoutTrigger(final String slotSelector) {
    if (isElementOnPage(By.cssSelector(slotSelector))) {
      WebElement element = driver.findElement(By.cssSelector(slotSelector));

      if (element.isDisplayed() && element.getSize().getHeight() > 1
          && element.getSize().getWidth() > 1) {
        throw new WebDriverException("Ads found on page in selector: " + slotSelector);
      } else {
        Log.log("AdsFoundButNotVisible",
                "Ads found on page with selector: " + slotSelector
                + " but is smaller then 1x1 or hidden",
                true
        );
      }
    } else {
      Log.log("AdNotFound", "Ad with selector: " + slotSelector + " not found on page", true);
    }
  }

  public AdsBaseObject triggerAdSlot(String slotName) {
    if (slotName.equals(AdsContent.BOTTOM_LB) && driver.isChromeMobile()) {
      waitForSlotExpanded(By.id(AdsContent.MOBILE_AD_IN_CONTENT));

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        Log.log("InterruptedException occurred", e, false);
      }

      triggerBLB();

      return this;
    }

    if (slotName.equals(AdsContent.BOTTOM_LB)) {
      triggerBLB();
      return this;
    }

    if (slotName.equals(AdsContent.FLOATING_MEDREC)) {
      triggerFMR(AdsContent.FLOATING_MEDREC);
      return this;
    }

    if (slotName.equals(AdsContent.FLOATING_MEDREC_2)) {
      triggerFMR(AdsContent.FLOATING_MEDREC_2);
      return this;
    }

    String javaScriptTrigger = AdsContent.getSlotTrigger(slotName);

    if (StringUtils.isNotEmpty(javaScriptTrigger)) {
      jsActions.execute(javaScriptTrigger);
    }

    return this;
  }

  public AdsBaseObject triggerAdSlotWithMobileState(String slotName, Boolean isMobile) {
    if (slotName.equals(AdsContent.MOBILE_AD_IN_CONTENT) && isMobile) {
      triggerMobileInContent();
      return this;
    }

    if (slotName.equals(AdsContent.MOBILE_BOTTOM_LB) && isMobile) {
      triggerBLB();
      return this;
    }

    if (slotName.equals(AdsContent.BOTTOM_LB)) {
      triggerBLB();
      return this;
    }

    if (slotName.equals(AdsContent.FLOATING_MEDREC) && !isMobile) {
      triggerFMR(AdsContent.FLOATING_MEDREC);
      return this;
    }

    String javaScriptTrigger = AdsContent.getSlotTrigger(slotName);

    if (StringUtils.isNotEmpty(javaScriptTrigger)) {
      jsActions.execute(javaScriptTrigger);
    }

    return this;
  }

  private void triggerFMR(String slotName) {
    scrollToPosition(By.cssSelector("#wikia-recent-activity"));
    wait.forX(Duration.ofSeconds(2));
    jsActions.scrollBy(0, 500);
    wait.forX(Duration.ofSeconds(1));

    try {
      doUntilElementVisible(By.cssSelector(AdsContent.getSlotSelector(slotName)), () -> {
        jsActions.scrollBy(0, 100);
        wait.forX(Duration.ofSeconds(1));
      });
    } catch (NoSuchElementException ex) {
      Log.log(AdsContent.FLOATING_MEDREC + " is not displayed", ex, true);
    }
  }

  private void doUntilElementVisible(By by, Runnable f, final int maxNumberOfRepetitions) {
    Boolean isElementDisplayed = false;
    changeImplicitWait(IMPLICIT_SHORT, TimeUnit.MILLISECONDS);
    int i = 0;

    do {
      if (maxNumberOfRepetitions < i) {
        throw new NoSuchElementException("No visible element:" + by.toString());
      }

      try {
        isElementDisplayed = driver.findElement(by).isDisplayed();
      } catch (NoSuchElementException ignored) {
        f.run();
      }

      i++;
    } while (!isElementDisplayed);

    restoreDefaultImplicitWait();
  }

  private void doUntilElementVisible(By by, Runnable f) {
    doUntilElementVisible(by, f, 20);
  }

  private void triggerMobileInContent() {
    scrollToInContent();

    simulateUserActivity(Duration.ofSeconds(3));
  }

  private void triggerBLB() {
    scrollToFooter();

    simulateUserActivity(Duration.ofSeconds(3));
  }

  private void scrollToInContent() {
    jsActions.scrollIntoView(mobileInContent);

    Log.log("scrollToFooter", "Scroll to the footer of the page", true);
  }

  private void simulateUserActivity(Duration duration) {
    simulateUserActivity(duration, Duration.ofMillis(500));
  }

  private void simulateUserActivity(Duration duration, Duration waitDuration) {
    while (!duration.isNegative()) {
      jsActions.scrollBy(0, 100);
      wait.forX(waitDuration);
      jsActions.scrollBy(0, -100);
      duration = duration.minus(waitDuration);
    }
  }

  public long getLineItemId(String slotName) {
    JavascriptExecutor js = driver;
    try {
      return (long) js.executeScript(
          "var slots = googletag.pubads().getSlots(); for (var i = 0; i < slots.length; i++) { "
          + "if (slots[i].getTargeting('pos').indexOf('" + slotName + "') !== -1) { "
          + "return slots[i].getResponseInformation().lineItemId;" + "} }");
    } catch (WebDriverException e) {
      Log.log("Get " + slotName + " line item", e, false);
      return 0;
    }
  }

  protected void verifyAdVisibleInSlot(String slotSelector, WebElement slot) {

    if (!checkIfSlotExpanded(slot)) {

      Optional<WebElement> lastGptDiv = getLastGptDiv(slotSelector);
      if (lastGptDiv.isPresent() && checkIfGptSlotHasCreativeContent(lastGptDiv.get(),
                                                                     HOP_AD_TYPE
      )) {
        Log.log("verifyAdVisibleInSlot", "Slot has " + HOP_AD_TYPE, true);
        return;
      }

      throw new WebDriverException(slot.getAttribute("id") + " is collapsed");
    }

    boolean adVisible = new AdsComparison().isAdVisible(slot, slotSelector, driver);

    extractGptInfo(slotSelector);

    if (!adVisible) {
      throw new WebDriverException("Ad is not present in " + slotSelector);
    }

    Log.log("ScreenshotsComparison", "Ad is present in " + slotSelector, true);
  }

  private boolean checkIfGptSlotHasCreativeContent(WebElement element, String hopAdType) {
    String slotName = element.getAttribute("id").replace("wikia_gpt", ADS_IFRAME);
    String iframeSelector = "iframe[id*='" + slotName + "_0']";
    String adTypeScriptXpath = String.format("//script[contains(text(), \"%s\")]", hopAdType);
    WebElement iframe = element.findElement(By.cssSelector(iframeSelector));
    driver.switchTo().frame(iframe);
    List<WebElement> adTypeScripts = driver.findElements(By.xpath(adTypeScriptXpath));
    driver.switchTo().defaultContent();
    return !adTypeScripts.isEmpty();
  }

  private Map<String, String> getSlotsSelectorMap() {
    switch (pageType) {
      case PAGE_TYPE_SPECIAL:
        return AdsContent.getSpecialPageSlotsSelectorsMap();
      case PAGE_TYPE_FILE:
        return AdsContent.getFilePageSlotsSelectors();
      case PAGE_TYPE_CATEGORY:
        return AdsContent.getCategoryPageSlotsSelectors();
      case PAGE_TYPE_ARTICLE:
      default:
        return AdsContent.getSlotsSelectorsMap(environment);
    }
  }

  /**
   * Verify if there are no ads on the page for ad slots in given pageType
   */
  private void verifyNoAds(Boolean isMobile) {
    Map<String, String> slots = getSlotsSelectorMap();
    for (Map.Entry<String, String> entry : slots.entrySet()) {
      verifyNoAd(entry.getKey(), entry.getValue(), isMobile);
    }
  }

  /**
   * Verify if slots for set pageType are on the page
   */
  public void verifyAds(Boolean isMobile) {
    Map<String, String> slots = getSlotsSelectorMap();
    for (Map.Entry<String, String> entry : slots.entrySet()) {
      checkSlotOnPageLoaded(entry.getKey(), isMobile);
    }
  }

  private void extractGptInfo(String slotSelector) {
    Optional<WebElement> lastGptDiv = getLastGptDiv(slotSelector);

    String log = "GPT ad not found in slot: " + slotSelector;

    if (lastGptDiv.isPresent()) {
      log = "GPT ad found in slot: " + lastGptDiv.get().getAttribute("id");
      for (String attribute : GPT_DATA_ATTRIBUTES) {
        log += "; " + attribute + " = " + lastGptDiv.get().getAttribute(attribute);
      }
    }

    Log.log("extractGptInfo", log, true, driver);
  }

  public void scrollToPosition(By element) {
    jsActions.scrollToSpecificElement(driver.findElement(element));
    Log.log("scrollToSelector", "Scroll to the web selector " + element.toString(), true);
  }

  public void scrollToPosition(String selector) {
    scrollToPosition(By.cssSelector(selector));
  }

  public void scrollBy(int x, int y) {
    jsActions.scrollBy(x, y);
  }

  public void scrollTo(By selector) {
    jsActions.scrollToElement(selector);
  }

  public void scrollTo(WebElement element) {
    jsActions.scrollToElement(element);
  }

  public void scrollTo(String cssSelector) {
    scrollTo(By.cssSelector(cssSelector));
  }

  public String getTrackingUrl(NetworkTrafficInterceptor networkTrafficInterceptor, String pos) {
    final String pattern = ".*adinfo.*kv_pos=" + pos.toLowerCase() + ".*";
    wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, pattern, 45);
    return networkTrafficInterceptor.getEntryByUrlPattern(pattern).getRequest().getUrl();
  }

  public boolean wasRequestForAdSend(NetworkTrafficInterceptor networkTrafficInterceptor) {
    final String pattern = ".*doubleclick\\.net\\/gampad.*ads.*";
    return networkTrafficInterceptor.getEntryByUrlPattern(pattern) != null;
  }
}
