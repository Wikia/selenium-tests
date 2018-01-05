package com.wikia.webdriver.common.skin;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SkinHelper extends WikiBasePageObject {

  @FindBy(css = "body.skin-oasis")
  private WebElement oasisSkin;

  @FindBy(css = "body.discussions")
  private WebElement discussionsSkin;

  @FindBy(css = "body.mobile-wiki")
  private WebElement mobileWikiSkin;

  public SkinHelper(WebDriver driver) {
    super();
  }

  public boolean isSkin(Skin skin) {
    boolean isExpectedSkin;

    switch (skin) {
      case OASIS:
        isExpectedSkin = wait.forElementInViewPort(oasisSkin);
        break;
      case DISCUSSIONS:
        isExpectedSkin = wait.forElementInViewPort(discussionsSkin);
        break;
      case MOBILE_WIKI:
        isExpectedSkin = wait.forElementInViewPort(mobileWikiSkin);
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

