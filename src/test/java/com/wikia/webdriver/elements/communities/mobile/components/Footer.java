package com.wikia.webdriver.elements.communities.mobile.components;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Footer extends WikiBasePageObject {

  @FindBy(css = ".wds-global-footer")
  private WebElement footer;

  @FindBy(css = ".global-footer-bottom__link")
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
