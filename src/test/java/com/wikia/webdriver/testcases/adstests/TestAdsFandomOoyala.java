package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.WindowSize;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.OoyalaPrerollAd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.awt.*;

public class TestAdsFandomOoyala extends AdsFandomTestTemplate {
  private static final String PLAY_BUTTON_SELECTOR = ".ooyala-video .oo-action-icon";
  private static final String PLAYER_AD_SELECTOR = ".ooyala-video iframe[src*=imasdk]";
  private static final String AUTOPLAY_PLAYERER_AD_SELECTOR =
      ".ooyala-video[data-autoplay] iframe[src*=imasdk]";

  private static final String CLICK_TO_PLAY_PAGE = "the-best-movies-of-2017-so-far";
  private static final String AUTOPLAY_PAGE = "orphan-black-clones-names";

  private static final Color BLUE = new Color(0, 1, 253);

  private static final int AD_DURATION_SEC = 30;

  @Test(
      groups = {"AdsFandomOoyalaClickToPlayPrerollDesktop"}
  )
  public void adsFandomOoyalaPrerollClickToPlayDesktop() {
    loadPage(
        CLICK_TO_PLAY_PAGE,
        AdsFandomTestTemplate.PAGE_TYPE_ARTICLE,
        WindowSize.DESKTOP
    );
    testOoyalaClickToPlayPreroll();
  }

  @Test(
      groups = {"AdsFandomOoyalaAutoplayPrerollDesktop"}
  )
  public void adsFandomOoyalaAutoplayPrerollDesktop() {
    loadPage(AUTOPLAY_PAGE,
             AdsFandomTestTemplate.PAGE_TYPE_ARTICLE,
             WindowSize.DESKTOP
    );
    testOoyalaAutoplayPreroll();
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      groups = {"AdsFandomOoyalaClickToPlayPrerollMobile"}
  )
  public void adsFandomOoyalaPrerollClickToPlayMobile() {
    loadPage(
        CLICK_TO_PLAY_PAGE,
        AdsFandomTestTemplate.PAGE_TYPE_ARTICLE,
        WindowSize.PHONE
    );
    testOoyalaClickToPlayPreroll();
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      groups = {"AdsFandomOoyalaAutoplayPrerollMobile"}
  )
  public void adsFandomOoyalaAutoplayPrerollMobile() {
    loadPage(AUTOPLAY_PAGE,
             AdsFandomTestTemplate.PAGE_TYPE_ARTICLE,
             WindowSize.PHONE
    );
    testOoyalaAutoplayPreroll();
  }

  public void testOoyalaClickToPlayPreroll() {
    Wait wait = new Wait(driver);
    WebElement playButton = driver.findElement(By.cssSelector(PLAY_BUTTON_SELECTOR));
    By adLayer = By.cssSelector(PLAYER_AD_SELECTOR);

    wait.forElementVisible(playButton);
    playButton.click();

    wait.forElementVisible(adLayer);
    verifyColorAd(driver.findElement(adLayer), BLUE, AD_DURATION_SEC);
    wait.forElementNotVisible(adLayer);
  }

  public void testOoyalaAutoplayPreroll() {
    Wait wait = new Wait(driver);
    By autoplayAdLayer = By.cssSelector(AUTOPLAY_PLAYERER_AD_SELECTOR);

    wait.forElementVisible(autoplayAdLayer);
    verifyColorAd(driver.findElement(autoplayAdLayer), BLUE, AD_DURATION_SEC);

    verifyColorAd(
        driver.findElement(autoplayAdLayer),
        BLUE,
        AD_DURATION_SEC
    );

    wait.forElementNotVisible(autoplayAdLayer);
  }

  private void verifyColorAd(WebElement element, Color color, int duration) {
    OoyalaPrerollAd ooyala = new OoyalaPrerollAd(driver);

    ooyala.verifyColorAd(element, color, duration);
  }
}
