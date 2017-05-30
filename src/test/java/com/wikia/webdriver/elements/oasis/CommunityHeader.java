package com.wikia.webdriver.elements.oasis;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.oasis.MainPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommunityHeader extends BasePageObject {

  @FindBy(css = ".wds-community-header__wordmark img")
  private WebElement wordmarkLink;

  public CommunityHeader() {
    super();

    this.waitForPageLoad();
  }

  public MainPage clickWordmark() {
    wait
        .forElementClickable(wordmarkLink)
        .click();

    PageObjectLogging.logInfo("clicked on wordmark image");

    return new MainPage();
  }

}
