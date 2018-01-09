package com.wikia.webdriver.pageobjectsfactory.pageobject.special.block;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SpecialBlockListPage extends WikiBasePageObject {

  private static final String SPECIAL_BLOCKLIST_PATH = "Special:BlockList";

  @FindBy(id = "mw-input-wpTarget")
  private WebElement userNameField;
  @FindBy(className = "mw-htmlform-submit")
  private WebElement searchButton;
  @FindBy(css = "#mw-content-text p")
  private WebElement userUnblockedMessage;
  @FindBy(css = ".TablePager_col_ipb_target .mw-userlink")
  private List<WebElement> blockedUserNames;

  public SpecialBlockListPage open() {
    getUrl(urlBuilder.getUrlForPath(SPECIAL_BLOCKLIST_PATH));
    return this;
  }

  public void searchForUser(String userName) {
    userNameField.sendKeys(userName);
    scrollAndClick(searchButton);
  }

  public boolean verifyUserUnblocked(String userName) {
    return userUnblockedMessage.getText().startsWith("The requested IP address or username is not blocked") &&
           blockedUserNames.isEmpty();
  }

  public boolean verifyUserBlocked(String userName) {
    return blockedUserNames.stream().map(WebElement::getText).anyMatch(userName::equals);
  }
}
