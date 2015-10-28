package com.wikia.webdriver.pageobjectsfactory.componentobject.mercury;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership Content X-Wing Wikia
 */
public class MercuryFooterComponentObject extends BasePageObject {

  @FindBy(css = ".external[href*='oasis']")
  private WebElement fullSiteLink;

  public MercuryFooterComponentObject(WebDriver driver) {
    super(driver);
  }

  /**
   * Click on full site option that opens the namespace in the oasis skin
   * @return Oasis article page object
   */
  public ArticlePageObject clickFullSiteLink() {
    waitAndClick(fullSiteLink);
    return new ArticlePageObject(driver);
  }
}
