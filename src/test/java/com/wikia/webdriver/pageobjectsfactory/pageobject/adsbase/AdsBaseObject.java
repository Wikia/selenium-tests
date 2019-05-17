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
  private static final int MIN_MIDDLE_COLOR_PAGE_WIDTH = 1600;
  private static final int PROVIDER_CHAIN_TIMEOUT_SEC = 30;
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
  private static final String PRESENT_LEADERBOARD_SELECTOR = "div[id*='hivi_leaderboard']";
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

  public void setEnvironment(String env) {
    environment = env;
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

  public void verifyMedrec() {
    verifyAdVisibleInSlot("div[id*='top_boxad']", presentMedrec);
  }

  public void verifyTopLeaderboard() {
    if (!checkIfSlotExpanded(presentLeaderboard)
        && isElementOnPage(By.cssSelector("#jpsuperheader"))) {
      Log.warning("Special ad", "Ad in #jpsuperheader detected");
      return;
    }
    verifyAdVisibleInSlot(PRESENT_LEADERBOARD_SELECTOR, presentLeaderboard);
  }

  public void verifyNoAdsOnPage(Boolean isMobile) {
    verifyNoAds(isMobile);
    Log.log("verifyNoAdsOnPage", "No ads detected", true, driver);
  }

  /**
   * Test whether the correct GPT ad parameters are passed
   *
   * @param slotName   Slotname
   * @param pageParams List of gpt page-level params to test
   * @param slotParams List of gpt slot-level params to test
   */
  public void verifyGptParams(
      String slotName, List<String> pageParams, List<String> slotParams
  ) {
    String dataGptPageParams = getSlotAttribute(slotName, "data-gpt-page-params");
    String dataGptSlotParams = getSlotAttribute(slotName, "data-gpt-slot-params");

    for (String param : pageParams) {
      Assertion.assertStringContains(dataGptPageParams, param);
    }

    for (String param : slotParams) {
      Assertion.assertStringContains(dataGptSlotParams, param);
    }

    Log.log("verifyGptParams",
            "All page-level and slot-level params present as expected " + dataGptPageParams + ", "
            + dataGptSlotParams,
            true,
            driver
    );
  }

  public boolean slotHasSize(String slotName, int width, int height) {
    return getSlotAttribute(slotName, "data-sizes").contains(String.format("%d,%d", width, height));
  }

  public boolean slotParamHasValue(String slotName, String paramName, String value) {
    String dataGptSlotParams = getSlotAttribute(slotName, "data-gpt-slot-params");
    return dataGptSlotParams.contains(String.format("\"%s\":\"%s\"", paramName, value));
  }

  /**
   * Test whether the correct GPT ad parameters are passed
   *
   * @param slotName   Slotname
   * @param lineItemId expected line item id
   * @param creativeId expected creative id
   */
  public void verifyGptAdInSlot(String slotName, String lineItemId, String creativeId) {

    Assertion.assertEquals(getSlotAttribute(slotName, "data-gpt-line-item-id"), lineItemId);

    if (creativeId.length() > 0) {
      Assertion.assertEquals(getSlotAttribute(slotName, "data-gpt-creative-id"), creativeId);
    }

    Log.log("verifyGptAdInSlot",
            "Line item id loaded: " + lineItemId + ", creativeId:" + creativeId,
            true,
            driver
    );
  }

  public void verifyGptAdInSlot(String slotName, String lineItemId) {
    waitForSlotExpanded("#" + slotName);
    Assertion.assertEquals(getSlotAttribute(slotName, "data-gpt-line-item-id"), lineItemId);

    Log.log("verifyGptAdInSlot", "Line item id loaded: " + lineItemId, true, driver);
  }

  public void verifyAdUnit(String slotName, String adUnit) {
    verifyGptIframe(adUnit, slotName, "gpt");
  }

  public void verifyMEGAAdUnit(String slotName, String adUnit) {
    verifyIframe(slotName, buildMEGAGptIframeId(WIKIA_DFP_CLIENT_ID, adUnit));
  }

  public void verifyValMorgan(String continent, String country, Boolean isMobile) {
    String MEGA_LB;
    String MEGA_BLB;

    if (!isMobile) {
      MEGA_LB = VAL_MORGAN_TLB_MEGA_AD_UNIT;
      MEGA_BLB = VAL_MORGAN_BLB_MEGA_AD_UNIT;
    } else {
      MEGA_LB = VAL_MORGAN_TLB_MEGA_AD_UNIT_MERCURY;
      MEGA_BLB = VAL_MORGAN_BLB_MEGA_AD_UNIT_MERCURY;
    }

    setGeoCookie(driver, continent, country);
    refreshPage();
    setPageType(AdsBaseObject.PAGE_TYPE_ARTICLE);

    verifyMEGAAdUnit(AdsContent.TOP_LB, MEGA_LB);
    if (!isMobile) {
      verifyMEGAAdUnit(AdsContent.TOP_BOXAD, VAL_MORGAN_TB_MEGA_AD_UNIT);
      triggerAdSlot(AdsContent.BOTTOM_LB);
    } else {
      scrollTo(ARTICLE_FOOTER);
      triggerAdSlotWithMobileState(AdsContent.MOBILE_BOTTOM_LB, true);
    }
    verifyMEGAAdUnit(AdsContent.BOTTOM_LB, MEGA_BLB);
  }

  public void verifySpotlights() {
    AdsComparison adsComparison = new AdsComparison();

    for (String spotlightSelector : SPOTLIGHT_SLOTS) {
      WebElement slot = wait.forElementVisible(By.cssSelector(spotlightSelector + " img"));
      verifySlotExpanded(slot);

      Assertion.assertTrue(adsComparison.isAdVisible(slot, spotlightSelector, driver));
    }
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
  public AdsBaseObject verifyLineItemId(List<String> slots, String lineItemId) {
    String lineItemParam = getSlotAttribute(slots, GPT_DATA_ATTRIBUTES[0]);
    Assertion.assertStringContains(lineItemParam, lineItemId);
    Log.log("verifyLineItemId", slots + " has following line item: " + lineItemParam, true);
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
      return driver.findElement(By.cssSelector(AdsContent.getSlotSelector(AdsContent.TOP_BOXAD))) != null;
    } catch (NoSuchElementException e) {
      Log.log("Slot top_boxad not found on the page", e, true);
      return false;
    }
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
      waitFor.until(ExpectedConditions.titleContains(desiredArticleTitle));
    } finally {
      restoreDefaultImplicitWait();
    }
  }

  public void verifySkin(
      String adSkinLeftPath, String adSkinRightPath, String backgroundColor, String middleColor
  ) {
    AdsSkinHelper skinHelper = new AdsSkinHelper(adSkinLeftPath, adSkinRightPath, driver);
    Assertion.assertTrue(skinHelper.skinPresent(), "Skin was not present");
    Log.log("SKIN", "SKIN presents on the page", true);

    if (!Strings.isNullOrEmpty(backgroundColor)) {
      Assertion.assertEquals(skinHelper.getBackgroundColor(),
                             backgroundColor,
                             "Background colors differ"
      );
      Log.log("SKIN", "SKIN has correct background color", true);
    }

    if (!Strings.isNullOrEmpty(middleColor)
        && getWindowSize().getWidth() > MIN_MIDDLE_COLOR_PAGE_WIDTH) {
      Assertion.assertEquals(skinHelper.getMiddleColor(), middleColor);
      Log.log("SKIN", "SKIN has correct middle color", true);
    }
  }

  /**
   * Check if AdEngine loaded the ad web elements inside slot.
   */
  public boolean checkSlotOnPageLoaded(String slotName) {
    WebElement slot;

    changeImplicitWait(250, TimeUnit.MILLISECONDS);

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

    changeImplicitWait(250, TimeUnit.MILLISECONDS);

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
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
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

  private void waitForProvidersChain(
      final String slotName, final String expectedProviders, int timeoutSec
  ) {
    new WebDriverWait(driver, timeoutSec).until(new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver webDriver) {
        String providers = Joiner.on("; ").join(getProvidersChain(slotName));
        Log.log("waitForProvidersChain", String.format("Providers %s found", providers), true);
        return expectedProviders.equals(providers);
      }

      @Override
      public String toString() {
        extractGptInfo(AdsContent.getSlotSelector(slotName));
        return String.format("Expected: [%s], Actual: [%s]",
                             expectedProviders,
                             Joiner.on("; ").join(getProvidersChain(slotName))
        );
      }
    });
  }

  private List<String> getProvidersChain(String slotName) {
    List<String> providersChain = new ArrayList<>();
    String slotSelector = AdsContent.getSlotSelector(slotName);
    for (WebElement providerSlot : driver.findElements(By.cssSelector(slotSelector + " > div"))) {
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
    changeImplicitWait(0, TimeUnit.MILLISECONDS);
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

  private void verifySlotExpanded(WebElement element) {
    if (!checkIfSlotExpanded(element)) {
      throw new WebDriverException(element.getAttribute("id") + " is collapsed");
    }
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

  /**
   * Verify if slots for set pageType are on the page and have correct line-items and ad units
   *
   * @param lineItems slot and expected line item pairs
   * @param adUnits   slot and expected ad unit pairs
   */
  public void verifyAds(Map<String, String> lineItems, Map<String, String> adUnits) {
    Map<String, String> slots = getSlotsSelectorMap();
    for (Map.Entry<String, String> entry : slots.entrySet()) {
      String slotName = entry.getKey();

      checkSlotOnPageLoaded(slotName);
      verifyGptIframe(adUnits.get(slotName), slotName, "gpt");
      verifyGptAdInSlot(slotName, lineItems.get(slotName), "");
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
    WebElement link = driver.findElement(By.cssSelector("a[title='" + linkName + "']"));
    link.click();

    waitTitleChangesTo(linkName);
  }

  public void scrollToPosition(By element) {
    jsActions.scrollToSpecificElement(driver.findElement(element));
    Log.log("scrollToSelector", "Scroll to the web selector " + element.toString(), true);
  }

  public void scrollToPosition(String selector) {
    scrollToPosition(By.cssSelector(selector));
  }

  public void scrollToSlot(String slotName) {
    checkSlotOnPageLoaded(slotName);
    jsActions.scrollToElement(By.id(slotName));
  }

  public void scrollTwitch() {
    simulateUserActivity(Duration.ofMillis(500));
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

  public void fixScrollPositionByNavbar() {
    int navbarHeight = -1 * driver.findElement(By.cssSelector(GLOBAL_NAVIGATION_SELECTOR))
        .getSize()
        .getHeight();

    if (isBannerNotificationContainerPresent()) {
      int notificationsHeight = -1 * getBannerNotificationsHeight();
      jsActions.scrollBy(0, navbarHeight + notificationsHeight);
    } else {
      jsActions.scrollBy(0, navbarHeight);
    }
  }

  public boolean isMobileInContentAdDisplayed() {
    try {
      wait.forElementVisible(mobileInContent);
      return true;
    } catch (TimeoutException | NoSuchElementException ex) {
      Log.log("Mobile in content ad is not displayed", ex, true);
      return false;
    }
  }

  public boolean isMobileBottomLeaderboardAdDisplayed() {
    try {
      wait.forElementVisible(By.id(AdsContent.MOBILE_BOTTOM_LB));
      return true;
    } catch (TimeoutException | NoSuchElementException ex) {
      Log.log("Mobile bottom leaderboard ad is not displayed", ex, true);
      return false;
    }
  }

  private void waitForAdInSlot(String creativeId, String slotName) {
    wait.forElementPresent(By.cssSelector(
        "#" + slotName + "[data-gpt-creative-id='" + creativeId + "']"));
  }

  public void verifyAdChainForSlot(String[] creativeIdChain, String slotName, AdsBaseObject page) {
    for (String creativeId : creativeIdChain) {
      page.waitForAdInSlot(creativeId, slotName);
    }
  }

  /**
   * Iframe finder for desktop slots WARNING: it's able to find only first call result!
   */
  public By findFirstIframeWithAd(String slotName) {
    return By.cssSelector("#" + slotName + " iframe[title='3rd party ad content']");
  }

  public void waitForVASTRequestWithAdUnit(
      NetworkTrafficInterceptor networkTrafficInterceptor, String adUnit
  ) throws UnsupportedEncodingException {
    final String encodedAdUnit = URLEncoder.encode(adUnit, "UTF-8");
    final String pattern = ".*output=xml_vast.*iu=" + encodedAdUnit + ".*";
    wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, pattern, 45);
  }

  public String getValueFromTracking(
      NetworkTrafficInterceptor networkTrafficInterceptor, String slotName, String paramName
  ) {
    final String fvTrackingUrl = getTrackingUrl(networkTrafficInterceptor, slotName);
    Matcher matcher = Pattern.compile(".*" + paramName + "=([^&]+)&.*").matcher(fvTrackingUrl);
    return matcher.matches() ? matcher.group(1) : "";
  }

  public String getFVLineItem() {
    final By FV_SLOT_SELECTOR = By.cssSelector("div.featured-video__player-container");
    wait.forAttributeToBePresent(driver.findElement(FV_SLOT_SELECTOR), "data-vast-line-item-id");
    return driver.findElement(FV_SLOT_SELECTOR).getAttribute("data-vast-line-item-id");
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
