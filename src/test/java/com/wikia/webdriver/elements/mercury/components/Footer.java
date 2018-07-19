package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Footer extends WikiBasePageObject {

  @FindBy(css = ".wds-global-footer")
  private WebElement footer;

  @FindBy(css = ".wds-global-footer__button-link")
  private WebElement viewFullSiteLink;

  @FindBy(css = ".mobile-site-link")
  private WebElement viewMobileSiteLink;

  public void clickViewFullSiteLink() {
    wait.forElementVisible(footer);
    wait.forElementClickable(viewFullSiteLink);
    viewFullSiteLink.click();
  }

  public void clickViewMobileSite() {
    wait.forElementVisible(footer);
    wait.forElementClickable(viewMobileSiteLink);
    scrollAndClick(viewMobileSiteLink);
  }
}
