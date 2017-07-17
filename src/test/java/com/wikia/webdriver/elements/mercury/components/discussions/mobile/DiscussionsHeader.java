package com.wikia.webdriver.elements.mercury.components.discussions.mobile;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class DiscussionsHeader extends BasePageObject {

  @FindBy(css = ".header-dropdown-button")
  private WebElement sortEntryPointMobile;

  public DiscussionsHeader clickSortButtonOnMobile() {
    sortEntryPointMobile.click();
    return this;
  }
}
