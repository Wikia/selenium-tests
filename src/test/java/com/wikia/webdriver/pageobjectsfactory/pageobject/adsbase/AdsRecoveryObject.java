package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.Assertion;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdsRecoveryObject extends AdsBaseObject {

  private static final String LOCK_CSS_PACKAGE = "ARecoveryEngine/css/recoveryLock.scss";
  private static final String MAIN_CSS_PACKAGE = "skins/oasis/css/oasis.scss";

  public AdsRecoveryObject(WebDriver driver, String page) {
    super(driver, page);
  }

  public AdsRecoveryObject(WebDriver driver, String page, Dimension resolution) {
    super(driver, page, resolution);
  }

  public void verifyUnlockCSS() {
    // Stylesheets with main scss package in href
    String mainCssSelector = "link[href*=\"" + MAIN_CSS_PACKAGE + "\"]";
    // Sibling to above selector which is link
    String unlockCssSelector = mainCssSelector + " + link";

    wait.forElementPresent(By.cssSelector(mainCssSelector));
    WebElement element = driver.findElement(By.cssSelector(unlockCssSelector));
    String unlockCssUrl = element.getAttribute("href");

    verifyNotProxiedUrl(unlockCssUrl);
    verifyIfUnlockCSSIsValid(unlockCssUrl);
  }

  public void verifyRecoveryUnlockCSS() {
    // Stylesheets with lock scss package in href
    String lockCssSelector = "link[href*=\"" + LOCK_CSS_PACKAGE + "\"]";
    // Sibling to above selector which is link and contains href in wikia.com/__are endpoint
    String unlockCssSelector = lockCssSelector + " + link[href*=\"wikia.com/__are\"]";

    wait.forElementPresent(By.cssSelector(lockCssSelector));
    WebElement element = driver.findElement(By.cssSelector(unlockCssSelector));

    verifyIfUnlockCSSIsValid(element.getAttribute("href"));
  }

  public String getRecoveredAdUnitId(String adUnitId) {
    return jsActions.execute(String.format("window._sp_.getElementId('%s')", adUnitId)).toString();
  }

  private void verifyIfUnlockCSSIsValid(String cssUrl) {
    final String articleHeaderSelector = "#WikiaArticle h2";
    final String partOfValidCss = "#WikiaPageHeader:after{content:none;";

    wait.forElementVisible(By.cssSelector(articleHeaderSelector));

    getUrl(cssUrl);
    Assertion.assertTrue(driver.getPageSource().contains(partOfValidCss), "Unlock css output");
  }

  private void verifyNotProxiedUrl(String url) {
    Assertion.assertStringNotContains(url, "wikia.com/__are");
  }
}
