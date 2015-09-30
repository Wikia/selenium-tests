package com.wikia.webdriver.pageobjectsfactory.componentobject.editprofile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class EditProfileComponentObject extends WikiBasePageObject {

  @FindBy(css = "li[data-tab='avatar']")
  protected WebElement avatarTab;
  @FindBy(css = "li[data-tab='about']")
  protected WebElement aboutTab;

  public EditProfileComponentObject(WebDriver driver) {
    super(driver);
  }

  protected void clickAvatarTab() {
    avatarTab.click();
    LOG.success("clickAvatarTab", "avatar tab clicked");
  }

  protected void clickAboutTab() {
    aboutTab.click();
    LOG.success("clickAboutTab", "about tab clicked");
  }
}
