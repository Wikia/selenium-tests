package com.wikia.webdriver.elements.communities.mobile.components.discussions.common;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OptionsPostAndReply extends BasePageObject {

  @FindBy(css = ".pop-over-compass")
  private WebElement optionsPopOver;
}
