package com.wikia.webdriver.elements.fandom.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.helpers.FandomUser;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public class FeaturedImageBox extends BasePageObject {

  @FindBy(css = "#set-post-thumbnail")
  private WebElement setImageLink;

  @FindBy(css = "#user_pass")
  private WebElement password;

  @FindBy(css = "#wp-submit")
  private WebElement sumbmit;

  public boolean openImageDialog() {
    setImageLink.click();
    wait.forElementVisible(By.cssSelector(".media-modal"));
    return true;
  }

  public WPLoginBox login(FandomUser user) {
    username.sendKeys(user.getUsername());
    password.sendKeys(user.getPassword());
    sumbmit.click();

    return this;
  }
}
