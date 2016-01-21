package com.wikia.webdriver.elements.mercury.old;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MercuryFooterComponentObject extends BasePageObject {

  @FindBy(css = ".external[href*='oasis']")
  private WebElement fullSiteLink;

  public MercuryFooterComponentObject(WebDriver driver) {
    super(driver);
  }

  /**
   * Click on full site option that opens the namespace in the oasis skin
   *
   * @return Oasis article page object
   */
  public ArticlePageObject clickFullSiteLink() {
    waitAndClick(fullSiteLink);
    return new ArticlePageObject(driver);
  }
}
