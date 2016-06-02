package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership Social Wikia
 */
public class OptionsPostAndReply extends WikiBasePageObject{

  @FindBy(css = ".pop-over-compass")
  private WebElement optionsPopOver;

}
