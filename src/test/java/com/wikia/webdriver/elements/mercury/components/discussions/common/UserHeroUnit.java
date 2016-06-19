package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserHeroUnit extends BasePageObject {

  @FindBy(css = "#WikiaUserPagesHeader")
  private WebElement userPageHeader;

}
