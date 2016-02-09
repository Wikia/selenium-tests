package com.wikia.webdriver.elements.mercury.old;

import com.wikia.webdriver.common.core.elemnt.Wait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MercuryFooterComponentObject {

  @FindBy(css = ".external[href*='oasis']")
  private WebElement fullSiteLink;

  private Wait wait;
  private WebDriver driver;

  public MercuryFooterComponentObject(WebDriver driver) {
    this.wait = new Wait(driver);
    this.driver = driver;

    PageFactory.initElements(driver, this);
  }

  public ArticlePageObject clickFullSiteLink() {
    wait.forElementVisible(fullSiteLink);
    fullSiteLink.click();

    return new ArticlePageObject(driver);
  }
}
