package com.wikia.webdriver.pageobjectsfactory.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FandomPageObject extends WikiBasePageObject {

  @FindBy(css = ".article-header__video")
  private WebElement featuredVideo;

  public boolean isFeaturedVideo() {
    wait.forElementVisible(featuredVideo);
    return featuredVideo.isDisplayed(); }

}
