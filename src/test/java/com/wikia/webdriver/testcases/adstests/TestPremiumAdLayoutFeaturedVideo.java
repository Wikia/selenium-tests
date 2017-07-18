package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.PremiumAdLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestPremiumAdLayoutFeaturedVideo extends TemplateNoFirstLoad {
  private static final By RECIRCULATION_SELECTOR = By.id("recirculation-rail");
  private static final By FMR_SELECTOR = By.id(AdsContent.FLOATING_MEDREC);

  @Test(
          groups = {"AdMixFeaturedVideoOasis", "PremiumAdLayoutRefreshFMR"},
          dataProviderClass = AdsDataProvider.class,
          dataProvider = "premiumLayoutPages"
  )
  public void regularFloatingMedrecIsReloadingWithRecirculationModule(Page page) {
    AdsBaseObject ads = new AdsBaseObject(driver, PremiumAdLayout.addTurnOnParams(urlBuilder, page));

    ads.scrollToPosition(RECIRCULATION_SELECTOR);
    ads.wait.forElementVisible(RECIRCULATION_SELECTOR);
    checkIfNextModuleWillBe(ads, FMR_SELECTOR);
    checkIfNextModuleWillBe(ads, RECIRCULATION_SELECTOR);
    checkIfNextModuleWillBe(ads, FMR_SELECTOR);
    checkIfNextModuleWillBe(ads, RECIRCULATION_SELECTOR);
    checkIfNextModuleWillBe(ads, FMR_SELECTOR);
    checkIfNextModuleWillBe(ads, FMR_SELECTOR);
  }

  @Test(
          groups = {"PremiumAdLayout", "PremiumAdLayoutRefreshFMRWithUAP"}
  )
  public void uapFloatingMedrecIsReloadingOnceWithRecirculationModule() {
    AdsBaseObject ads = new AdsBaseObject(driver, PremiumAdLayout.addTurnOnParams(urlBuilder, AdsDataProvider.UAP_PAGE));

    ads.scrollToPosition(RECIRCULATION_SELECTOR);
    ads.wait.forElementVisible(RECIRCULATION_SELECTOR);
    checkIfNextModuleWillBe(ads, FMR_SELECTOR);
    checkIfNextModuleWillBe(ads, FMR_SELECTOR);
    checkIfNextModuleWillBe(ads, FMR_SELECTOR);
  }

  private void checkIfNextModuleWillBe(AdsBaseObject ads, By moduleSelector) {
    imitateUserActionUntilModuleVisible(ads, moduleSelector);
  }

  private void imitateUserActionUntilModuleVisible(AdsBaseObject ads, By moduleSelector) {
    for (int i = 0; i < 15 ; i++) {
      scrollAndWait(Duration.ofSeconds(1));

      if (isModuleVisible(ads, moduleSelector)) {
        PageObjectLogging.log("Status", String.format("Module with %s visible", moduleSelector.toString()), true);
        return;
      }
    }

    Assertion.fail("Can't find next module with selector " + moduleSelector.toString());
  }

  private boolean isModuleVisible(AdsBaseObject ads, By moduleSelector) {
    try {
      ads.wait.forElementVisible(moduleSelector, 1);
      return true;
    } catch (TimeoutException ignored) {}

    return false;
  }
  private void scrollAndWait(Duration duration) {
    JavascriptActions jsActions = new JavascriptActions(driver);

    PageObjectLogging.log("User action", String.format("Scroll %d", 200), true);
    jsActions.scrollBy(0, 200);

    waitSomeTime(duration);

    PageObjectLogging.log("User action", String.format("Scroll %d", -200), true);
    jsActions.scrollBy(0, -200);
  }

  private void waitSomeTime(Duration reloadDelay) {
    try {
      PageObjectLogging.log("Sleep", String.format("Wait for %d ms", reloadDelay.toMillis()), true);
      Thread.sleep(reloadDelay.toMillis());
    } catch (InterruptedException ignored) {}
  }
}
