package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IntroducingFollowingModal extends BasePageObject {

  @FindBy(css = ".introducing-following-modal .find-posts-button")
  private WebElement okGotItButton;

  public void confirmSeeingModal() {
    okGotItButton.click();
  }
}
