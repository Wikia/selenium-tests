package com.wikia.webdriver.elements.oasis;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.oasis.MainPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommunityHeader extends BasePageObject {

  @FindBy(css = ".wds-community-header__wordmark img")
  private WebElement wordmark;

  @FindBy(css = ".wds-community-header__sitename a")
  private WebElement wikiName;


  public CommunityHeader() {
    super();

    this.waitForPageLoad();
  }

  public MainPage clickWordmark() {
    wait.forElementClickable(wordmark).click();

    PageObjectLogging.logInfo("clicked on wordmark image");

    return new MainPage();
  }

  public MainPage clickWikiName() {
    wait.forElementClickable(wikiName).click();

    PageObjectLogging.logInfo("clicked on wiki name");

    return new MainPage();
  }
}
