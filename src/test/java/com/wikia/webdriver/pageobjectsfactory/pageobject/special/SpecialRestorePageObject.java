package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class SpecialRestorePageObject extends WikiBasePageObject {

  @FindBy(css = ".mw-undelete-pagetitle")
  private WebElement articleToRestore;
  @FindBy(css = "#wpComment")
  private WebElement reasonInput;
  @FindBy(css = "#mw-undelete-submit")
  private WebElement submitRestore;

  public SpecialRestorePageObject(WebDriver driver) {
    super(driver);
  }

  public void verifyArticleName(String articleName) {
    wait.forElementVisible(articleToRestore);
    Assertion.assertStringContains(articleToRestore.getText(), articleName);
  }

  public void giveReason(String reason) {
    reasonInput.sendKeys(reason);
  }

  public void restorePage() {
    scrollAndClick(submitRestore);
    LOG.log("ArticleRestored", "Article restored", LOG.Type.SUCCESS);
  }
}
