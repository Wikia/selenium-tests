package com.wikia.webdriver.common.skin;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SkinHelper extends WikiBasePageObject {

  @FindBy(css = "body.skin-oasis")
  private WebElement oasisClassInBody;

  @FindBy(css = "body.ember-application")
  private WebElement mercuryClassInBody;

  @FindBy(css = "body.ember-application.mobile-wiki")
  private WebElement mobileWikiClassInBody;

  @FindBy(css = "body.wkMobile")
  private WebElement wikiaMobileClassInBody;

  public SkinHelper(WebDriver driver) {
    super();
  }

  public boolean isSkin(Skin skin) {
    switch (skin) {
      case OASIS:
        return wait.forElementInViewPort(oasisClassInBody);
      case MERCURY:
        return wait.forElementInViewPort(mercuryClassInBody);
      case MOBILE_WIKI:
        return wait.forElementInViewPort(mobileWikiClassInBody);
      case WIKIAMOBILE:
        return wait.forElementInViewPort(wikiaMobileClassInBody);
      default:
        return false;
    }
  }
}

