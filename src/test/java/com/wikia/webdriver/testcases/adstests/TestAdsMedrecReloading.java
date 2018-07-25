package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.UnsafePageLoad;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestAdsMedrecReloading extends TemplateNoFirstLoad {

  private static final By RECIRCULATION_SELECTOR = By.id("recirculation-rail");
  private static final By FMR_SELECTOR = By.id(AdsContent.FLOATING_MEDREC);

  @Test(groups = {"AdsRefreshFMR"})
  @UnsafePageLoad
  public void regularFloatingMedrecIsReloadingWithRecirculationModule() {
    Page page = new Page("project43", "SyntheticTests/LongPage");
    AdsBaseObject ads = new AdsBaseObject(page.getUrl());

    ads.scrollToPosition(RECIRCULATION_SELECTOR);
    ads.wait.forElementVisible(RECIRCULATION_SELECTOR);
    checkIfNextModuleWillBe(ads, FMR_SELECTOR);
    checkIfNextModuleWillBe(ads, RECIRCULATION_SELECTOR);
    checkIfNextModuleWillBe(ads, FMR_SELECTOR);
    checkIfNextModuleWillBe(ads, RECIRCULATION_SELECTOR);
    checkIfNextModuleWillBe(ads, FMR_SELECTOR);
    checkIfNextModuleWillBe(ads, FMR_SELECTOR);
  }

  @Test(groups = {"AdsRefreshFMRWithUAP"})
  public void uapFloatingMedrecIsReloadingOnceWithRecirculationModule() {
    AdsBaseObject ads = new AdsBaseObject(AdsDataProvider.UAP_PAGE.getUrl());

    ads.wait.forElementPresent(RECIRCULATION_SELECTOR);
    ads.scrollToPosition(RECIRCULATION_SELECTOR);

    checkIfNextModuleWillBe(ads, FMR_SELECTOR);
    checkIfNextModuleWillBe(ads, FMR_SELECTOR);
    checkIfNextModuleWillBe(ads, FMR_SELECTOR);
  }

  private void checkIfNextModuleWillBe(AdsBaseObject ads, By moduleSelector) {
    imitateUserActionUntilModuleVisible(ads, moduleSelector);
  }

  private void imitateUserActionUntilModuleVisible(AdsBaseObject ads, By moduleSelector) {
    for (int i = 0; i < 15; i++) {
      scrollAndWait(Duration.ofSeconds(1));

      if (isModuleVisible(ads, moduleSelector)) {
        Log.log("Status", String.format("Module with %s visible", moduleSelector.toString()), true);
        return;
      }
    }

    Assertion.fail("Can't find next module with selector " + moduleSelector.toString());
  }

  private boolean isModuleVisible(AdsBaseObject ads, By moduleSelector) {
    try {
      ads.wait.forElementVisible(moduleSelector, 1);
      return true;
    } catch (TimeoutException ignored) {
    }

    return false;
  }

  private void scrollAndWait(Duration duration) {
    JavascriptActions jsActions = new JavascriptActions(driver);

    Log.log("User action", String.format("Scroll %d", 200), true);
    jsActions.scrollBy(0, 200);

    waitSomeTime(duration);

    Log.log("User action", String.format("Scroll %d", -200), true);
    jsActions.scrollBy(0, -200);
  }

  private void waitSomeTime(Duration reloadDelay) {
    try {
      Log.log("Sleep", String.format("Wait for %d ms", reloadDelay.toMillis()), true);
      Thread.sleep(reloadDelay.toMillis());
    } catch (InterruptedException ignored) {
    }
  }
}
