package com.webdriver.common.skin;

import com.webdriver.common.logging.PageObjectLogging;
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

  public SkinHelper(WebDriver driver) {
    super();
  }

  public boolean isSkin(Skin skin) {
    boolean isExpectedSkin;

    switch (skin) {
      case OASIS:
        isExpectedSkin = wait.forElementInViewPort(oasisClassInBody);
        break;
      case MERCURY:
        isExpectedSkin = wait.forElementInViewPort(mercuryClassInBody);
        break;
      case MOBILE_WIKI:
        isExpectedSkin = wait.forElementInViewPort(mobileWikiClassInBody);
        break;
      default:
        isExpectedSkin = false;
    }

    if (isExpectedSkin) {
      PageObjectLogging.logInfo("Expected skin is loaded: " + skin.toString());
    } else {
      PageObjectLogging.logWarning("isSkin", "Expected skin is not loaded: " + skin.toString());
    }

    return isExpectedSkin;
  }
}

