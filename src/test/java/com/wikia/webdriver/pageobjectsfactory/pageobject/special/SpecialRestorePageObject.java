package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpecialRestorePageObject extends WikiBasePageObject {

  @FindBy(css = ".mw-undelete-pagetitle")
  private WebElement articleToRestore;
  @FindBy(css = "#wpComment")
  private WebElement reasonInput;
  @FindBy(css = "#mw-undelete-submit")
  private WebElement submitRestore;

  public SpecialRestorePageObject(WebDriver driver) {
    super();
  }

  public void verifyRestoredArticleName(String articleName) {
    wait.forElementVisible(articleToRestore);
    Assertion.assertStringContains(articleToRestore.getText(), articleName);
  }

  public void giveReason(String reason) {
    wait.forElementClickable(reasonInput);
    reasonInput.sendKeys(reason);
  }

  public void restorePage() {
    wait.forElementClickable(submitRestore);
    scrollAndClick(submitRestore);
    PageObjectLogging.log("ArticleRestored", "Article restored", true);
  }
}
